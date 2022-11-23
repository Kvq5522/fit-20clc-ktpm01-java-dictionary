package Business;

import Method.Functionality;
import Object.Dictionary;

public class Operation {
    public static void crudOperation(Dictionary myDict) {
        int choice = 0;

        System.out.println("1. Add new slang");
        System.out.println("2. Edit slang");
        System.out.println("3. Delete slang");
        System.out.println("4. Reset dictionary");

        while (choice < 1 || choice > 4) {
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(Functionality.scanner.nextLine());
        }

        switch (choice) {
            case 1:
                Functionality.addSlangFunc(myDict);
                break;
            case 2:
                Functionality.editSlangFunc(myDict);
                break;
            case 3:
                Functionality.deleteSlangFunc(myDict);
                break;
            case 4:
                Functionality.resetDictionaryFunc(myDict);
                break;
        }
    }

    public static void searchOperation(Dictionary myDict) {
        int choice = 0;

        System.out.println("1. Search slang by word");
        System.out.println("2. Search slang by meaning");
        System.out.println("3. See search history");
        System.out.println("4. Random a slang");

        while (choice < 1 || choice > 4) {
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(Functionality.scanner.nextLine());
        }

        switch (choice) {
            case 1:
                Functionality.searchBySlangFunc(myDict);
                break;
            case 2:
                Functionality.searchByDefinitionFunc(myDict);
                break;
            case 3:
                Functionality.seeSearchHistoryFunc(myDict);
                break;
            case 4:
                Functionality.randomSlangFunc(myDict);
                break;
        }
    }

    public static void gameOperation(Dictionary myDict) {
        int choice = 0;

        System.out.println("1. Guess the meaning of slang");
        System.out.println("2. Guess the slang by meaning");

        while (choice < 1 || choice > 2) {
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(Functionality.scanner.nextLine());
        }

        switch (choice) {
            case 1:
                Functionality.guessSlangFunc(myDict);
                break;
            case 2:
                Functionality.guessMeaningFunc(myDict);
                break;
        }
    }
}
