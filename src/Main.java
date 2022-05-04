import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Encoder encoder = new Encoder(getFileStr("test.txt"));
        HashMap<Character, String> dict = encoder.encode();
        System.out.println();
        display(dict);
    }

    private static String getFileStr(String fileName) {
        StringBuilder str = new StringBuilder();
        File file = new File("src/" + fileName);
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;

                str.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    private static void display(HashMap<Character, String> dict) {
        dict.forEach((character, string) -> System.out.println(character + " -> " + string));
    }
}
