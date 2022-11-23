import Method.Functionality;
import Object.Dictionary;
import Business.Operation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Dictionary myDict = new Dictionary();
        myDict.readFromFile("test.txt");
        int choice = 0;

        while (choice != -1) {
            System.out.println("-----------------------------------------------------------------");
            System.out.println("1. CRUD operation");
            System.out.println("2. Search operation");
            System.out.println("3. Play game");
            System.out.println("4. Exit");

            while (choice < 1 || choice > 4) {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(Functionality.scanner.nextLine());
            }

            switch (choice) {
                case 1:
                    Operation.crudOperation(myDict);
                    choice = 0;
                    break;
                case 2:
                    Operation.searchOperation(myDict);
                    choice = 0;
                    break;
                case 3:
                    Operation.gameOperation(myDict);
                    choice = 0;
                    break;
                case 4:
                    myDict.writeToFile("test.txt");
                    choice = -1;
                    break;
            }
            System.out.println("-----------------------------------------------------------------\n");
        }


    }
}