import java.util.*;

public class Encoder {

    private final String fileStr;

    public Encoder(String fileStr) {
        this.fileStr = fileStr;
    }

    public HashMap<Character, String> encode() {
        List<Data> charList = extractChars(fileStr);
        PriorityQueue<Data> pq = new PriorityQueue<>(charList);
        System.out.println("\nPQ Size: " + pq.size());

        System.out.println("PQ:");
        Heap heap = createHeap(pq);

        System.out.println("\nHeap:");
        heap.display();

        BST bst = createBST(heap);
        System.out.println("\nBST: ");
        bst.displayTree();

        return bst.getMap();
    }

    private List<Data> extractChars(String str) {
        List<Data> chars = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            int lookup = find(c, chars);
            if (lookup == -1)
                chars.add(new Data(String.valueOf(c), 1, i));
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
            Data c = chars.poll();
            System.out.println("C: " + c);
            heap.insert(new Node(c));
        }

        return heap;
    }

    private BST createBST(Heap heap) {
        int counter = 0;
        while (heap.getSize() > 1) {
            Node node1 = heap.remove();
            node1.appendToKey(0);
            Node node2 = heap.remove();
            node2.appendToKey(1);

            System.out.println("L: " + node1.getData());
            System.out.println("R: " + node2.getData());

            heap.insert(new Node(new Data("N" + counter,
                    node1.getData().getFreq() + node2.getData().getFreq(), counter), node1, node2));
            counter++;
        }

        return new BST(heap.remove());
    }

}
