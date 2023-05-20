// Class that supports the usage of the binary tree
/* Class BTNode */
class BTNodeCopy {
    BTNodeCopy left, right;
    int data;

    /* Constructor */
    public BTNodeCopy() {
        left = null;
        right = null;
        data = 0;
    }

    /* Constructor */
    public BTNodeCopy(int n) {
        left = null;
        right = null;
        data = n;
    }

    /* Function to set left node */
    public void setLeft(BTNodeCopy n) {
        left = n;
    }

    /* Function to set right node */
    public void setRight(BTNodeCopy n) {
        right = n;
    }

    /* Function to get left node */
    public BTNodeCopy getLeft() {
        return left;
    }

    /* Function to get right node */
    public BTNodeCopy getRight() {
        return right;
    }

    /* Function to set data to node */
    public void setData(int d) {
        data = d;
    }

    /* Function to get data from node */
    public int getData() {
        return data;
    }
}
