import java.util.HashMap;
import java.util.Stack;

public class BST {

    private final Node root;
    private Node current;

    public BST(Node root) {
        this.root = this.current = root;
    }

    public Node get() {
        return current;
    }

    public Node getRoot() {
        return root;
    }

    public void findRoot() {
        current = root;
    }

    public void findLeftC() {
        current = current.getLeftC();
    }

    public void findRightC() {
        current = current.getRightC();
    }

    public boolean isLeaf() {return current.isLeaf();}

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

    public void displayTree() {
        Stack<Node> stack = new Stack<>();

        Node node = root;

        while (true) {
            if (node.getLeftC() == null && node.getRightC() == null) {
                System.out.println(node);

                if (stack.isEmpty())
                    break;
                else
                    node = stack.pop();
            }
            else if (node.getLeftC() != null && node.getRightC() == null)
                node = node.getLeftC();
            else if (node.getLeftC() == null && node.getRightC() != null)
                node = node.getRightC();
            else {
                stack.push(node.getRightC());
                node = node.getLeftC();
            }
        }
    }
}
