import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Huffman {

    /**
     * The method that converts the string from the file to a huffman tree
     *
     * @param fileStr The string from the file
     */
    public BST encode(String fileStr) {
        List<Data> charList = extractChars(fileStr);
        PriorityQueue<Data> pq = new PriorityQueue<>(charList);
        System.out.println("\nPQ Size: " + pq.size());

        System.out.println("PQ:");
        Heap heap = createHeap(pq);

        System.out.println("\nHeap:");
        heap.display();

        BST bst = createBST(heap);
        System.out.println("\nBST: ");
        bst.print("", bst.getRoot(), false);

        return bst;
    }

    /**
     * Extracts the characters from the string and returns them in the form of a list
     *
     * @param str The string to extract the characters from
     */
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

    /**
     * Searches in the list for a character and returns the index of it if found. otherwise -1
     *
     * @param c The character to look for
     * @param chars the list of characters to look in
     */
    private int find(char c, List<Data> chars) {
        for (int i = 0; i < chars.size(); i++) {
            if (chars.get(i).getC().equals(String.valueOf(c)))
                return i;
        }
        return -1;
    }

    /**
     * Creates a heap and inserts all the elements of the priority queue into to
     *
     * @param chars The priority queue of characters
     */
    private Heap createHeap(PriorityQueue<Data> chars) {
        Heap heap = new Heap(chars.size());

        while (!chars.isEmpty()) {
            Data c = chars.poll();
            System.out.println("C: " + c);
            heap.insert(new Node(c));
        }

        return heap;
    }

    /**
     * Creates a huffman tree from the given heap.
     * It does this by removing two elements from the heap, creating a new node, assigning the two nodes as the right
     * and left children of the new node, and the sum of the two nodes as the new node's frequency,
     * then inserting the new node in the heap.
     * It also appends 0 and 1 to the key of the nodes, which would form the code.
     *
     * @param heap The heap to create the huffman tree from
     */
    private BST createBST(Heap heap) {
        int counter = 0;
        while (heap.getSize() > 1) {
            Node node1 = heap.remove();
            node1.appendToKey(0);
            Node node2 = heap.remove();
            node2.appendToKey(1);

            heap.insert(new Node(new Data("N" + counter,
                    node1.getData().getFreq() + node2.getData().getFreq(), counter), node1, node2));

            counter++;
        }

        return new BST(heap.remove());
    }

    /**
     * Uses a HashMap of every character and it's code to get the encoded 01 string
     *
     * @param original The string to be encoded
     * @param map The HashMap that we use to get the code string of every character
     */
    public String toCode(String original, HashMap<Character, String> map) {
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < original.length(); i++)
            code.append(map.get(original.charAt(i)));

        return code.toString();
    }


    /**
     * The method that decodes the encoded 01 string back to the original text
     *
     * @param str The String to be decoded
     * @param tree The Huffman tree which wee need to decode the string
     */
    public String decode(String str, BST tree) {
        StringBuilder original = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == '0')
                tree.findLeftC();
            else
                tree.findRightC();

            if (tree.isLeaf()) {
                original.append(tree.getDate().getC());
                tree.findRoot();
            }
        }

        return original.toString();
    }

}
