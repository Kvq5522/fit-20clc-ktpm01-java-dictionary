package Method;

import java.util.*;
import Object.Dictionary;
import org.jetbrains.annotations.NotNull;

public class Functionality {
    public static Scanner scanner = new Scanner(System.in);

    public static void searchBySlangFunc(@NotNull Dictionary myDict) {
        String word = null; String meaning = null;
        scanner = new Scanner(System.in);

        System.out.print("Enter the word you want to search: ");
        word = scanner.nextLine();

        meaning = myDict.searchBySlang(word);

        if (meaning == null) {
            System.out.println("The word you entered is not in the dictionary.");
        } else {
            System.out.println("The meaning of the word you entered is: " + meaning);
        }
    }

    public static void searchByDefinitionFunc(@NotNull Dictionary myDict) {
        String[] result = null; String meaning = null;

        System.out.print("Enter the meaning you want to search: ");
        meaning = scanner.nextLine();

        result = myDict.searchByMeaning(meaning);

        if (result == null) {
            System.out.println("The meaning you entered is not in the dictionary.");
        } else {
            System.out.println("The words with the meaning you entered are:");

            for (String word : result) {
                System.out.println(word);
            }
        }
    }

    public static void seeSearchHistoryFunc(@NotNull Dictionary myDict) {
        String[] history = myDict.getHistory();

        if (history == null) {
            System.out.println("The history is empty.");
        } else {
            System.out.println("10 recent searches:");

            for (int i = 0; i < history.length; i++) {
                System.out.println(history[i]);
            }
        }
    }

    public static void addSlangFunc(@NotNull Dictionary myDict) {
        String word = null; String meaning = null;

        System.out.print("Enter the word you want to add: ");
        word = scanner.nextLine();

        System.out.print("Enter the meaning of the word you want to add: ");
        meaning = scanner.nextLine();

        boolean result = myDict.addNewSlang(word, meaning);

        if (!result) {
            if (word == null || meaning == null) {
                System.out.println("The word or meaning you entered is invalid.");
                return;
            }

            int choice = 0;

            do {
                System.out.println("The word you entered is already in the dictionary. " +
                        "\n1. Do you want to add a new meaning to it?" +
                        "\n2. Do you want to replace the old meaning with the new one?");
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
            } while (choice != 1 && choice != 2);

            if (choice == 1) {
                myDict.addNewMeaningToSlang(word, meaning);
            } else {
                myDict.replaceExistedSlang(word, meaning);
            }
        }

        System.out.println("The word and meaning you entered have been added to the dictionary.");
    }

    public static void editSlangFunc(@NotNull Dictionary myDict) {
        String word = null; String meaning = null;

        System.out.print("Enter the word you want to edit: ");
        word = scanner.nextLine();

        System.out.print("Enter the new meaning of the word you want to edit: ");
        meaning = scanner.nextLine();

        boolean result = myDict.replaceExistedSlang(word, meaning);

        if (!result) {
            System.out.println("The word you entered is not in the dictionary.");
        } else {
            System.out.println("The word and meaning you entered have been edited.");
        }
    }

    public static void deleteSlangFunc(@NotNull Dictionary myDict) {
        String word = null;

        System.out.println("Enter the word you want to delete: ");
        word = scanner.nextLine();

        boolean confirm = false;

        do {
            System.out.print("Are you sure you want to delete this word? (Y/N): ");
            String choice = scanner.nextLine().toUpperCase();

            if (choice.equals("Y")) {
                confirm = true;
            } else if (choice.equals("N")) {
                return;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        } while (!confirm);

        boolean result = myDict.deleteExistedSlang(word);

        if (!result) {
            System.out.println("The word you entered is not in the dictionary.");
        } else {
            System.out.println("The word you entered has been deleted.");
        }

    }

    public static void resetDictionaryFunc(@NotNull Dictionary myDict) {
        boolean confirm = false;

        do {
            System.out.print("Are you sure you want to reset the dictionary? (Y/N) : ");
            String choice = scanner.nextLine().toUpperCase();

            if (choice.equals("Y")) {
                confirm = true;
            } else if (choice.equals("N")) {
                System.out.println("The dictionary has not been reset.");
                return;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        } while (!confirm);

        if (!myDict.resetDictionary()) {
            System.out.println("The dictionary has not been reset.");
        } else {
            System.out.println("The dictionary has been reset.");
        }
    }

    public static void randomSlangFunc(@NotNull Dictionary myDict) {
        ArrayList<String> keySet = myDict.getKeySet();
        Random random = new Random();
        String result = keySet.get(random.nextInt(keySet.size()));

        System.out.println("The random slang is: " + result + " - " + myDict.searchBySlang(result));
    }

    public static void guessSlangFunc(@NotNull Dictionary myDict) {
        ArrayList<String> keySet = myDict.getKeySet();
        Random random = new Random();
        String result = keySet.get(random.nextInt(keySet.size()));
        String word_1 = keySet.get(random.nextInt(keySet.size()));
        String word_2 = keySet.get(random.nextInt(keySet.size()));
        String word_3 = keySet.get(random.nextInt(keySet.size()));

        Hashtable<String,String> table = new Hashtable<>();
        table.put(result, myDict.searchBySlang(result));
        table.put(word_1, myDict.searchBySlang(word_1));
        table.put(word_2, myDict.searchBySlang(word_2));
        table.put(word_3, myDict.searchBySlang(word_3));

        System.out.println("Can you guess what "+result+" means?");
        int i = 0;
        ArrayList<String> list = new ArrayList<>();

        for (String key : table.keySet()) {
            System.out.println((i+1)+". "+table.get(key));
            list.add(i, table.get(key));
            i++;
        }

        System.out.print("Enter the number of the meaning you think is correct: ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (table.get(result).equals(list.get(choice-1))) {
            System.out.println("You are correct!");
        } else {
            System.out.println("You are wrong!");
        }
    }

    public static void guessMeaningFunc(@NotNull Dictionary myDict) {
        ArrayList<String> keySet = myDict.getKeySet();
        Random random = new Random();
        String result = keySet.get(random.nextInt(keySet.size()));
        String word_1 = keySet.get(random.nextInt(keySet.size()));
        String word_2 = keySet.get(random.nextInt(keySet.size()));
        String word_3 = keySet.get(random.nextInt(keySet.size()));

        Hashtable<String,String> table = new Hashtable<>();
        table.put(result, result);
        table.put(word_1, word_1);
        table.put(word_2, word_2);
        table.put(word_3, word_3);

        System.out.println("Can you guess what "+myDict.searchBySlang(result)+" stands for?");
        int i = 0;
        ArrayList<String> list = new ArrayList<>();

        for (String key : table.keySet()) {
            System.out.println((i+1)+". "+table.get(key));
            list.add(i, table.get(key));
            i++;
        }

        System.out.print("Enter the number of the meaning you think is correct: ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (table.get(result).equals(list.get(choice-1))) {
            System.out.println("You are correct!");
        } else {
            System.out.println("You are wrong!");
        }
    }
}
