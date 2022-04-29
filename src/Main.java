import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Encoder encoder = new Encoder("test.txt");
        HashMap<Character, String> dict = encoder.encode();
        System.out.println();
        display(dict);
    }

    private static void display(HashMap<Character, String> dict) {
        dict.forEach((character, string) -> System.out.println("Character: " + character + ", Code: " + string));
    }
}
