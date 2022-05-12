public class Node implements Comparable<Node> {

    private String key = "";    // code
    private final Data data;
    private Node leftC;
    private Node rightC;

    public Node(Data data) {
        this.data = data;
    }

    public Node(Data data, Node leftC, Node rightC) {
        this.data = data;
        this.leftC = leftC;
        this.rightC = rightC;
    }

    public String getKey() {
        return key;
    }

    public void appendToKey(int n) {
        key = n + key;

        if (leftC != null)
            leftC.appendToKey(n);

        if (rightC != null)
            rightC.appendToKey(n);
    }

    public Data getData() {
        return data;
    }

    public Node getLeftC() {
        return leftC;
    }

    public Node getRightC() {
        return rightC;
    }

    public boolean isLeaf() {
        return leftC == null && rightC == null;
    }

    @Override
    public String toString() {
        return "Node{" + "key=" + key + ", data=" + data + ", leftC=" + leftC + ", rightC=" + rightC + '}';
    }

    @Override
    public int compareTo(Node o) {
        return key.compareTo(o.key);
    }
}
