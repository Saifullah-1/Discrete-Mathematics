class Node{
    String value;
    Node right;
    Node left;

}

class TreeLinkedList {
    private static Node root = new Node();
    public void addRoot(String value){
        this.root.value =value;
    }
    public void addRightNode(Node parent, Node node){
        parent.right=node;
    }
    public void addLeftNode(Node parent, Node node){
        parent.left=node;
    }
    public Node getRoot(){
        return root;
    }
    public Node getRightChiled(Node node){
        return node.right;
    }
    public Node getLeftChiled(Node node){
        return node.left;
    }

    public void preOrder(Node root) {
        if (root != null){
            System.out.print(root.value+" ");
        }
        if (root.left != null){
            preOrder (root.left);
        }
        if (root.right != null){
            preOrder (root.right);
        }
    }
    public void inOrder(Node root) {
        if (root.left != null){
            inOrder(root.left);
        }
        if (root != null){
            System.out.print(root.value+" ");
        }
        if (root.right != null){
            inOrder(root.right);
        }

    }
    public void postOrder(Node root) {
        if (root.left != null){
            postOrder(root.left);
        }
        if (root.right != null){
            postOrder(root.right);
        }
        if (root != null){
            System.out.print(root.value+" ");
        }
    }
}
