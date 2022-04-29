public class Node implements Comparable<Node> {

    private String key = "";    // code
    private Data data;
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

    public Node(String key, Data data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public void setData(Data data) {
        this.data = data;
    }

    public Node getLeftC() {
        return leftC;
    }

    public void setLeftC(Node leftC) {
        this.leftC = leftC;
    }

    public Node getRightC() {
        return rightC;
    }

    public void setRightC(Node rightC) {
        this.rightC = rightC;
    }

    public int getSize() {
        int size = 1;

        if (leftC != null)
            size += leftC.getSize();

        if (rightC != null)
            size += rightC.getSize();

        return size;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", data=" + data +
                ", leftC=" + leftC +
                ", rightC=" + rightC +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return key.compareTo(o.key);
    }
}
