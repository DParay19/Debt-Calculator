// Class that supports the usage of the binary tree
/* Class BTNode */
class BTNode {
    BTNode left, right;
    double increase;
    Debt debt;

    /* Constructor */
    public BTNode() {
        left = null;
        right = null;
        increase = 0;
        debt = new Debt();
    }

    /* Constructor */
    public BTNode(double increase, Debt debt) {
        left = null;
        right = null;
        this.increase = increase;
        this.debt = debt;
    }

    /* Function to set left node */
    public void setLeft(BTNode n) {
        left = n;
    }

    /* Function to set right node */
    public void setRight(BTNode n) {
        right = n;
    }

    /* Function to get left node */
    public BTNode getLeft() {
        return left;
    }

    /* Function to get right node */
    public BTNode getRight() {
        return right;
    }

    /* Function to set data to node */
    public void setIncrease(double increase)
    {
        this.increase = increase;
    }
    public double getIncrease()
    {
        return increase;
    }
    public void setDebt(Debt debt)
    {
        this.debt=debt;
    }
    public Debt getDebt()
    {
        return this.debt;
    }
}
