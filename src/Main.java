import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        String fileStr = getFileStr("test.txt");    // the file to be encoded

        Huffman huffman = new Huffman();

        BST tree = huffman.encode(fileStr);
        HashMap<Character, String> map = new HashMap<>();
        tree.getMap(map, tree.getRoot());

        System.out.println();
        map.forEach((character, string) -> System.out.println(character + " -> " + string));

        String code = huffman.toCode(fileStr, map);
        int oldSize = fileStr.length() * 8;
        int newSize = code.length();
        System.out.println("\nCODE:\n" + code);

        System.out.println("\nOld Size: " + oldSize + " bit  ---  New Size: " + newSize + " bit");
        String percent = "%";
        System.out.printf("You improved the size by %.2f%s\n", (1.0 * newSize / oldSize * 100), percent);

        System.out.println("\n////////////////////   End of Encoder and start of Decoder   ////////////////////////\n");

        String original = huffman.decode(code, tree);
        System.out.println("Original: \n" + original);
    }

    /**
     * Returns the file specified by the filename as a string
     *
     * @param fileName The name of the file (the file must be located in the project directory)
     */
    private static String getFileStr(String fileName) {
        StringBuilder str = new StringBuilder();
        File file = new File(fileName);
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

}
