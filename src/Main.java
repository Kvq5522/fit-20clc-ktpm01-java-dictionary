import Object.Dictionary;
public class Main {
    public static void main(String[] args) {
        Dictionary myDict = new Dictionary();
        int count = 1;

        myDict.readFromFile("test.txt");
        System.out.println(myDict.search("Æ"));
        myDict.writeToFile("test.txt");
    }
}