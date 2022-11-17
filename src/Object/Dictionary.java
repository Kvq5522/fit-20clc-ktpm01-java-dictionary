package Object;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.*;

public class Dictionary {
    public HashMap<String, String> dictionary;

    Scanner scanner;

    public Dictionary() {
        dictionary = new HashMap<>();
    }

    public boolean add(String word, String meaning) {
        if (word.isEmpty() || meaning.isEmpty() || dictionary.containsKey(word)) {
            return false;
        }

        dictionary.put(word.toUpperCase(), meaning);
        return true;
    }

    public boolean replace(String word, String meaning) {
        if (word.isEmpty() || meaning.isEmpty() || !dictionary.containsKey(word)) {
            return false;
        }

        dictionary.replace(word, meaning);
        return true;
    }

    public boolean addNewMeaning(String word, String meaning) {
        if (word.isEmpty() || meaning.isEmpty() || !dictionary.containsKey(word)) {
            return false;
        }

        String newMeaning = dictionary.get(word) + "&&" + meaning;

        dictionary.put(word.toUpperCase(), newMeaning);
        return true;
    }

    public boolean delete(String word) {
        if (!dictionary.containsKey(word.toUpperCase())) {
            return false;
        }

        dictionary.remove(word);
        return true;
    }

    public String search(String word) {
        if (!dictionary.containsKey(word.toUpperCase())) {
            return null;
        }

        return dictionary.get(word.toUpperCase());
    }

    public int getSize() {
        return dictionary.size();
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

                add(word, meaning);
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
            TreeMap<String, String> treeMap = new TreeMap<>(dictionary);

            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write("Slag`Meaning\n");

            for (String word : treeMap.keySet()) {
                String meaning = treeMap.get(word);
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
                }
                writer.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Write to file "+filename+" successfully!");
        }
    }

}
