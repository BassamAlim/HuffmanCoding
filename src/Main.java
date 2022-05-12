import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        String fileStr = getFileStr("nm.txt");
        int betaSize = fileStr.length() * 8;
        Encoder encoder = new Encoder(fileStr);
        BST tree = encoder.encode();
        HashMap<Character, String> map = new HashMap<>();
        tree.getMap(map, tree.getRoot());

        System.out.println();
        map.forEach((character, string) -> System.out.println(character + " -> " + string));

        String code = toCode(fileStr, map);
        int alphaSize = code.length();
        System.out.println("\nCODE:\n" + code);

        System.out.println("\nNaive Size: " + betaSize + " bit  ---  Compressed Size: " + alphaSize + " bit");
        String percent = "%";
        System.out.printf("You improved the size by %.2f%s", (1.0 * alphaSize / betaSize * 100), percent);


        System.out.println("\n////////////////////   Ejnd of Encoder and start of Decoder   ////////////////////////\n");

        Decoder decoder = new Decoder(code, tree);
        String original = decoder.decode();
        System.out.println("Original: \n" + original);
    }

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

    private static String toCode(String original, HashMap<Character, String> dict) {
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < original.length(); i++)
            code.append(dict.get(original.charAt(i)));

        return code.toString();
    }

}
