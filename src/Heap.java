public class Heap {    // Min Heap

    private final Node[] heap;
    private final int maxSize;
    private int size;

    /**
     * Constructor of heap
     *
     * @param maxSize The maximum size of the heap to be created
     */
    public Heap(int maxSize) {
        this.maxSize = maxSize;
        heap = new Node[maxSize + 1];    // +1 for the extra node we don't use in the heap
        heap[0] = new Node(new Data("$$$", 0, 0));    // the node that we don't use in the heap
    }

    /**
     * Returns the index of the parent of the node at the given position
     *
     * @param pos The index of the node which we want to find the parent for
     */
    private int parent(int pos) {
        return pos / 2;
    }

    /**
     * Returns the index of the left child of the node at the given position
     *
     * @param pos The index of the node which we want to find the left child for
     */
    private int leftChild(int pos) {
        return pos * 2;
    }

    /**
     * Returns the index of the right child of the node at the given position
     *
     * @param pos The index of the node which we want to find the right child for
     */
    private int rightChild(int pos) {
        return (pos * 2) + 1;
    }

    /**
     * Returns true if the node at the given position has no children . false otherwise
     *
     * @param pos The index of the node we want to check
     */
    private boolean isLeaf(int pos) {
        return pos > (size / 2) && pos <= size;
    }

    /**
     * Swaps two nodes
     *
     * @param i1 The index of the first node
     * @param i2 The index of the second node
     */
    private void swap(int i1, int i2) {
        Node tmp = heap[i1];
        heap[i1] =  heap[i2];
        heap[i2] = tmp;
    }

    /**
     * Recursive method to restore the heap property after a remove
     *
     * @param pos The index of the node we want to check
     */
    private void heapify(int pos) {
        if (isLeaf(pos))    // if we arrive to a node that is a leaf then the process is done
            return;

        if (heap[pos].getData().getFreq() > heap[leftChild(pos)].getData().getFreq() ||
                heap[pos].getData().getFreq() > heap[rightChild(pos)].getData().getFreq()) {

            if (heap[leftChild(pos)].getData().getFreq() < heap[rightChild(pos)].getData().getFreq()) {
                swap(pos, leftChild(pos));
                heapify(leftChild(pos));
            }
            else {
                swap(pos, rightChild(pos));
                heapify(rightChild(pos));
            }
        }
    }

    /**
     * Inserts an element to the heap if the heap is not full
     *
     * @param element The node to be inserted
     */
    public void insert(Node element) {
        if (size >= maxSize)    // check if the heap is full
            return;

        heap[++size] = element;

        int current = size;
        while (heap[current].getData().getFreq() < heap[parent(current)].getData().getFreq()) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    /**
     * Removes an element from the heap and returns it
     */
    public Node remove() {
        Node popped = heap[1];
        heap[1] = heap[size--];
        if (size != 0)
            heapify(1);
        return popped;
    }

    /**
     * Returns the current size of the heap
     */
    public int getSize() {
        return size;
    }

    /**
     * Displays the heap's contents
     */
    public void display() {
        int left, right;
        for (int i = 1; i <= size / 2; i++) {
            System.out.print("PARENT: C: " + heap[i].getData().getC() + ", Freq: " + heap[i].getData().getFreq());

            left = 2 * i;
            right = 2 * i + 1;

            if (left <= size)
                System.out.print(" -- LEFT CHILD: C: " + heap[left].getData().getC() +
                        ", Freq: " + heap[left].getData().getFreq());
            else
                System.out.print(" -- LEFT CHILD: NULL");

            if (right <= size)
                System.out.print(" -- RIGHT CHILD: C: " + heap[right].getData().getC() +
                        ", Freq: " + heap[right].getData().getFreq());
            else
                System.out.print(" -- RIGHT CHILD: NULL");

            System.out.println();
        }
    }
}
