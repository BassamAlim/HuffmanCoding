import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Encoder {

    private final String fileName;

    public Encoder(String fileName) {
        this.fileName = fileName;
    }

    public HashMap<Character, String> encode() {
        String fileStr = getFileStr();
        List<Data> charList = extractChars(fileStr);
        PriorityQueue<Data> pq = new PriorityQueue<>(charList);

        Heap heap = createHeap(pq);
        System.out.println("Heap Size: " + heap.getSize());

        BST bst = createBST(heap);
        System.out.println("BST Size: " + bst.getSize());

        HashMap<Character, String> map = bst.getMap();
        System.out.println("MapSize: " + map.size());

        return map;
    }

    private String getFileStr() {
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

    private List<Data> extractChars(String str) {
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

    private int find(char c, List<Data> chars) {
        for (int i = 0; i < chars.size(); i++) {
            if (chars.get(i).getC().equals(String.valueOf(c)))
                return i;
        }
        return -1;
    }

    private Heap createHeap(PriorityQueue<Data> chars) {
        Heap heap = new Heap(chars.size());

        while (!chars.isEmpty()) {
            Data data = chars.poll();
            heap.insert(new Node(data));
        }

        return heap;
    }

    private PriorityQueue<Node> createPQ(PriorityQueue<Data> chars) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        while (!chars.isEmpty()) {
            Data data = chars.poll();
            pq.add(new Node(data));
        }

        return pq;
    }

    private BST createBST(Heap heap) {
        int counter = 0;
        while (heap.getSize() > 1) {
            Node node1 = heap.remove();
            node1.appendToKey(0);
            Node node2 = heap.remove();
            node2.appendToKey(1);

            Node newNode = new Node(new Data("N" + counter++, 0), node1, node2);

            heap.insert(newNode);
        }

        return new BST(heap.remove());
    }

}
