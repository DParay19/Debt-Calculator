// Class that supports the usage of a binary tree
class BT {
    private BTNode root;

    /* Constructor */
    public BT() {
        root = null;
    }

    /* Function to check if tree is empty */
    public boolean isEmpty() {
        return root == null;
    }

    /* Functions to insert data */
    public void insert(double increase,Debt debt) {
        root = insert(root, increase,debt);
    }

    /* Function to insert data recursively */
    private BTNode insert(BTNode node, double increase, Debt debt) {
        if (node == null)
            node = new BTNode(increase, debt);
        else {
            if (node.getRight() == null)
                node.right = insert(node.right, increase, debt);
            else
                node.left = insert(node.left, increase, debt);
        }
        return node;
    }

    /* Function to count number of nodes */
    public int countNodes() {
        return countNodes(root);
    }

    /* Function to count number of nodes recursively */
    private int countNodes(BTNode r) {
        if (r == null)
            return 0;
        else {
            int l = 1;
            l += countNodes(r.getLeft());
            l += countNodes(r.getRight());
            return l;
        }
    }

    /* Function to search for an element */
    public boolean search(double val) {
        return search(root, val);
    }

    /* Function to search for an element recursively */
    private boolean search(BTNode r, double val) {
        if (r.getIncrease() == val)
            return true;
        if (r.getLeft() != null)
            if (search(r.getLeft(), val))
                return true;
        if (r.getRight() != null)
            if (search(r.getRight(), val))
                return true;
        return false;
    }

    /* Function for inorder traversal */
    public void inorder() {
        inorder(root);
    }

    private void inorder(BTNode r) {
        if (r != null) {
            inorder(r.getLeft());
            System.out.print(r.getIncrease() + ", "+r.getDebt());
            inorder(r.getRight());
        }
    }

    /* Function for preorder traversal */
    public void preorder() {
        preorder(root);
    }

    private void preorder(BTNode r) {
        if (r != null) {
            System.out.print(r.getIncrease() + ", "+r.getDebt());
            preorder(r.getLeft());
            preorder(r.getRight());
        }
    }

    /* Function for postorder traversal */
    public void postorder() {
        postorder(root);
    }

    private void postorder(BTNode r) {
        if (r != null) {
            postorder(r.getLeft());
            postorder(r.getRight());
            System.out.print(r.getIncrease() + ", "+r.getDebt());
        }
    }
}
