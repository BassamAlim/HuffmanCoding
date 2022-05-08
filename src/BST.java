import java.util.HashMap;
import java.util.Stack;

public class BST {

    private final Node root;

    public BST(Node root) {
        this.root = root;
    }

    public HashMap<Character, String> getMap() {
        HashMap<Character, String> map = new HashMap<>();

        Stack<Node> stack = new Stack<>();

        Node node = root;

        while (true) {
            if (node.getLeftC() == null && node.getRightC() == null) {
                map.put(node.getData().getC().charAt(0), node.getKey());

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

        return map;
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
