package Object;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.*;

public class Dictionary {
    private TreeMap<String, String> dictionary;
    private Scanner scanner;
    private Queue<String> history = new LinkedList<String>();
    private Queue<String> addedWords = new LinkedList<String>();
    private HashMap<String, String> deletedWords = new HashMap<String, String>();
    private HashMap<String, String> editedWords = new HashMap<String, String>();

    public Dictionary() {
        dictionary = new TreeMap<>();
    }

    public boolean addNewSlang(String word, String meaning) {
        if (word.isEmpty() || meaning.isEmpty() || dictionary.containsKey(word.toUpperCase())) {
            return false;
        }

        dictionary.put(word.toUpperCase(), meaning);
        addedWords.add(word.toUpperCase());
        return true;
    }

    public boolean replaceExistedSlang(String word, String meaning) {
        if (word.isEmpty() || meaning.isEmpty() || !dictionary.containsKey(word.toUpperCase())) {
            return false;
        }

        if (!editedWords.containsKey(word.toUpperCase())) {
            editedWords.put(word.toUpperCase(), dictionary.get(word.toUpperCase()));
        }

        System.out.println("Replace "+dictionary.get(word.toUpperCase())+" with "+meaning);
        dictionary.replace(word.toUpperCase(), meaning);
        return true;
    }

    public boolean addNewMeaningToSlang(String word, String meaning) {
        if (word.isEmpty() || meaning.isEmpty() || !dictionary.containsKey(word.toUpperCase())) {
            return false;
        }

        String curMeaning = dictionary.get(word.toUpperCase());

        if (!editedWords.containsKey(word.toUpperCase())) {
            editedWords.put(word.toUpperCase(), curMeaning);
        }

        dictionary.replace(word.toUpperCase(), curMeaning + "&&" + meaning);
        return true;
    }

    public boolean deleteExistedSlang(String word) {
        if (!dictionary.containsKey(word.toUpperCase())) {
            return false;
        }

        if (!deletedWords.containsKey(word.toUpperCase())) {
            deletedWords.put(word.toUpperCase(), dictionary.get(word.toUpperCase()));
        }

        dictionary.remove(word.toUpperCase());
        return true;
    }

    public String searchBySlang(String word) {
        if (!dictionary.containsKey(word.toUpperCase())) {
            return null;
        }

        if (history.size() < 10) {
            history.add(word);
        } else {
            history.remove();
            history.add(word);
        }

        return dictionary.get(word.toUpperCase());
    }

    public String[] searchByMeaning(String meaning) {
        if (meaning.isEmpty()) {
            return null;
        }

        ArrayList<String> result = new ArrayList<>();

        for (String word : dictionary.keySet()) {
            if (dictionary.get(word).contains(meaning)) {
                result.add(word);
            }
        }

        return result.toArray(new String[result.size()]);
    }

    public boolean editBySlang(String word, String meaning) {
        if (word.isEmpty() || meaning.isEmpty() || !dictionary.containsKey(word.toUpperCase())) {
            return false;
        }

        dictionary.replace(word.toUpperCase(), meaning);
        return true;
    }

    public boolean resetDictionary() {
        if (dictionary.isEmpty()) {
            return false;
        }

        for (String word : addedWords) {
            dictionary.remove(word.toUpperCase());
        }

        for (String word : deletedWords.keySet()) {
            dictionary.put(word.toUpperCase(), deletedWords.get(word.toUpperCase()));
        }

        for (String word : editedWords.keySet()) {
            dictionary.replace(word.toUpperCase(), editedWords.get(word).toUpperCase());
        }

        addedWords.clear();
        deletedWords.clear();
        editedWords.clear();

        return true;
    }

    public String[] getHistory() {
        return history.toArray(new String[history.size()]);
    }

    public int getSize() {
        return dictionary.size();
    }

    public ArrayList<String> getKeySet() {
        return new ArrayList<>(dictionary.keySet());
    }

    public void readFromFile(String filename) {
        try {
            scanner = new Scanner(new File(filename));
            scanner.nextLine();
            String trace = null;

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.isBlank()) {
                    continue;
                }

                int breakPoint = line.indexOf("`");

                if (breakPoint == -1) {
                    if (trace != null) {
                        String newMeaning = dictionary.get(trace);
                        newMeaning += "&&" + line;
                        dictionary.replace(trace, newMeaning);
                    }

                    continue;
                }

                String word = line.substring(0, breakPoint);
                String meaning = line.substring(breakPoint + 1);
                trace = word;

                if (dictionary.containsKey(word)) {
                    if (dictionary.get(word) == meaning) {
                        continue;
                    }

                    String newMeaning = dictionary.get(word) + "&&" + line;
                    dictionary.replace(word, newMeaning);
                    continue;
                }

                dictionary.put(word, meaning);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Read from file "+filename+" successfully!");
            scanner.close();
        }
    }

    public void writeToFile(String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write("Slag`Meaning\n");

            for (String word : dictionary.keySet()) {
                String meaning = dictionary.get(word);
                String anotherMeaning = null;

                if (!meaning.contains("&&")) {
                    writer.write(word+"`"+meaning+"\n");
                    writer.flush();
                    continue;
                }

                writer.write(word+"`"+meaning.substring(0, meaning.indexOf("&"))+"\n");
                meaning = meaning.substring(meaning.indexOf("&") + 2);

                while (meaning.contains("&&")) {
                    anotherMeaning = meaning.substring(0, meaning.indexOf("&"));
                    meaning = meaning.substring(meaning.indexOf("&") + 2);
                    writer.write(anotherMeaning+"\n");
                    writer.flush();
                }

                writer.write(meaning+"\n");
                writer.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Write to file "+filename+" successfully!");
        }
    }

}
