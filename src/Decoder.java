public class Decoder {

    private final String fileStr;
    private final BST tree;

    public Decoder(String fileStr, BST tree) {
        this.fileStr = fileStr;
        this.tree = tree;
    }

    public String decode() {
        StringBuilder original = new StringBuilder();

        for (int i = 0; i < fileStr.length(); i++) {
            char c = fileStr.charAt(i);

            if (c == '0')
                tree.findLeftC();
            else
                tree.findRightC();

            if (tree.isLeaf()) {
                original.append(tree.get().getData().getC());
                tree.findRoot();
            }
        }

        return original.toString();
    }

}
