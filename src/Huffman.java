import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

class HuffmanNode {
    int data;
    String c;
    HuffmanNode left;
    HuffmanNode right;
}

class MyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {
        return x.data - y.data;
    }
}

public class Huffman {

    public static void printCode(HuffmanNode root, String s) {
        if (root.left == null && root.right == null) {
            System.out.println(root.c + ":" + s);
            return;
        }

        assert root.left != null;
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }

    private static String getFileStr() {
        StringBuilder str = new StringBuilder();
        File file = new File("src/test.txt");
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

    private static List<Data> extractChars(String str) {
        List<Data> chars = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            int lookup = find(c, chars);
            if (lookup == -1)
                chars.add(new Data(String.valueOf(c), 1));
            else
                chars.get(lookup).incFreq();
        }

        return chars;
    }

    private static int find(char c, List<Data> chars) {
        for (int i = 0; i < chars.size(); i++) {
            if (chars.get(i).getC().equals(String.valueOf(c)))
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        String fileStr = getFileStr();
        List<Data> chars = extractChars(fileStr);

        int n = chars.size();

        PriorityQueue<HuffmanNode> q = new PriorityQueue<>(n, new MyComparator());

        for (int i = 0; i < n; i++) {
            HuffmanNode hn = new HuffmanNode();

            hn.c = chars.get(i).getC();
            hn.data = chars.get(i).getFreq();

            hn.left = hn.right = null;

            q.add(hn);
        }

        HuffmanNode root = null;

        while (q.size() > 1) {
            HuffmanNode x = q.peek();
            q.poll();

            HuffmanNode y = q.peek();
            q.poll();

            HuffmanNode f = new HuffmanNode();

            f.data = x.data + y.data;
            f.c = String.valueOf('-');

            f.left = x;

            f.right = y;

            root = f;

            q.add(f);
        }

        printCode(root, "");
    }
}
