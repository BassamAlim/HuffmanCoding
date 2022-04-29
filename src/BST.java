import java.util.HashMap;
import java.util.Stack;

public class BST {

    private Node root, current;

    public BST(Node root) {
        this.root = current = root;
    }

    public void gotoRoot() {
        current = root;
    }

    public void gotoLeftC() {
        current = current.getLeftC();
    }

    public void gotoRightC() {
        current = current.getRightC();
    }

    public boolean insert(String key, Data data) {
        Node p, q = current;
        if (findKey(key)) {
            current = q;
            return false;
        }
        p = new Node(data);
        if (root == null)
            root = current = p;
        else {
            if (key.compareTo(current.getKey()) < 0)
                current.setLeftC(p);
            else
                current.setRightC(p);
            current = p;
        }
        return true;
    }

    public boolean removeKey(String tKey) {
        Boolean removed = false;
        Node p;
        p = removeAux(tKey, root, removed);
        current = root = p;
        return removed;
    }

    private Node removeAux(String key, Node p, Boolean flag) {
        Node q, child;
        if (p == null)
            return null;

        if (key.compareTo(p.getKey()) < 0)
            p.setLeftC(removeAux(key, p.getLeftC(), flag));
        else if (key.compareTo(p.getKey()) > 0)
            p.setRightC(removeAux(key, p.getRightC(), flag));
        else {
            flag = Boolean.TRUE;

            if (p.getLeftC() != null && p.getRightC() != null) {
                q = findMin(p.getRightC());
                p.setKey(q.getKey());
                p.getData().setC(q.getData().getC());
                p.getData().setFreq(q.getData().getFreq());
                p.setRightC(removeAux(q.getKey(), p.getRightC(), flag));
            }
            else {
                if (p.getRightC() == null)
                    child = p.getLeftC();
                else
                    child = p.getRightC();
                return child;
            }
        }
        return p;
    }

    public boolean update(String key, Data data) {
        removeKey(key);
        return insert(key, data);
    }

    public boolean findKey(String tKey) {
        Node p = root, q = root;
        if (root == null)
            return false;

        while (p != null) {
            q = p;
            if (p.getKey().equals(tKey)) {
                current = p;
                return true;
            }
            else if (tKey.compareTo(p.getKey()) < 0)
                p = p.getLeftC();
            else
                p = p.getRightC();
        }
        current = q;
        return false;
    }

    public boolean recFindKey(String k, Node r) {
        if (r == null)
            return false;
        if (r.getKey().equals(k)) {
            current = r;
            return true;
        }
        current = r;
        if (r.getKey().compareTo(k) < 0)
            return recFindKey(k, r.getRightC());
        return recFindKey(k, r.getLeftC());
    }

    private Node findMin(Node p) {
        if (p == null)
            return null;
        while (p.getLeftC() != null)
            p = p.getLeftC();
        return p;
    }

    public int getSize() {
        return root.getSize();
    }

    public HashMap<Character, String> getMap() {
        HashMap<Character, String> map = new HashMap<>();

        Stack<Node> stack = new Stack<>();

        Node node = root;

        while (node != null) {
            if (node.getLeftC() == null && node.getRightC() == null) {
                map.put(node.getData().getC().charAt(0), node.getKey());
                if (stack.isEmpty())
                    node = null;
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
}
