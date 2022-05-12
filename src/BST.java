import java.util.HashMap;

public class BST {

    private final Node root;
    private Node current;

    /**
     * Constructor that sets the root and current node of the tree
     *
     * @param root The node to be set as root
     */
    public BST(Node root) {
        this.root = this.current = root;
    }

    /**
     * Returns the data of the current node
     */
    public Data getDate() {
        return current.getData();
    }

    /**
     * Returns the root of the tree
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Sets the value of current to the root of the tree
     */
    public void findRoot() {
        current = root;
    }

    /**
     * Sets the value of current to the left child of the current node
     */
    public void findLeftC() {
        current = current.getLeftC();
    }

    /**
     * Sets the value of current to the right child of the current node
     */
    public void findRightC() {
        current = current.getRightC();
    }

    /**
     * Returns true if the given node has no children (leaf)
     */
    public boolean isLeaf() {return current.isLeaf();}

    /**
     * Fills the HashMap with the characters and their codes using recursion when given the root as node
     *
     * @param map The Hash map to be filled
     * @param node The current node it's printing (initially the root)
     */
    public void getMap(HashMap<Character, String> map, Node node) {
        if (node == null)
            return;

        if (node.isLeaf())
            map.put(node.getData().getC().charAt(0), node.getKey());
        else {
            getMap(map, node.getLeftC());
            getMap(map, node.getRightC());
        }
    }

    /**
     * Displays the BST using recursion when given the root
     *
     * @param node The current node it's printing (initially the root)
     */
    public void display(Node node) {
        if (node == null)
            return;

        System.out.println(node);

        display(node.getLeftC());
        display(node.getRightC());
    }
}
