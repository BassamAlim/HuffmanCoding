public class Heap {    // Min Heap

    private final Node[] heap;
    private final int maxSize;
    private int size;

    public Heap(int maxSize) {
        this.maxSize = maxSize;
        heap = new Node[maxSize + 1];
        heap[0] = new Node(new Data("$$$", 0, 0));
    }

    private int parent(int pos) {
        return pos / 2;
    }

    private int leftChild(int pos) {
        return pos * 2;
    }

    private int rightChild(int pos) {
        return (pos * 2) + 1;
    }

    private boolean isLeaf(int pos) {
        return pos > (size / 2) && pos <= size;
    }

    private void swap(int i1, int i2) {
        Node tmp = heap[i1];
        heap[i1] =  heap[i2];
        heap[i2] = tmp;
    }

    private void heapify(int pos) {
        if (isLeaf(pos))
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

    public void insert(Node element) {
        if (size >= maxSize)
            return;

        heap[++size] = element;

        int current = size;
        while (heap[current].getData().getFreq() < heap[parent(current)].getData().getFreq()) {  // maybe <=
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public Node remove() {
        Node popped = heap[1];
        heap[1] = heap[size--];
        if (size != 0)
            heapify(1);
        return popped;
    }

    public int getSize() {
        return size;
    }

    public void display() {
        int left,right;
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
