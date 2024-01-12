import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        TreeLinkedList tree = new TreeLinkedList();
        Queue<Node> nodes = new LinkedList<Node>();

        System.out.println("Enter The Value of The Root Node : ");
        String root = in.nextLine();
        if (root.equals("-1")){
            System.out.println("There is No Tree.");
            System.exit(0);
        }
        tree.addRoot(root);
        nodes.add(tree.getRoot());

        while (!nodes.isEmpty()){
            Node node = nodes.peek();
            Node toAddLeft = new Node();
            Node toAddRight= new Node();
            System.out.println("Enter the Left Child of "+node.value+" (or -1 to skip) : ");
            String left = in.nextLine();
            System.out.println("Enter the Right Child of "+node.value+" (or -1 to skip) : ");
            String right = in.nextLine();

            nodes.remove();
            if (left.equals("-1")){
                tree.addLeftNode(node,null);
            }
            else{
                toAddLeft.value = left;
                tree.addLeftNode(node,toAddLeft);
                nodes.add(toAddLeft);
            }

            if (right.equals("-1")){
                tree.addRightNode(node,null);
            }
            else{
                toAddRight.value = right;
                tree.addRightNode(node,toAddRight);
                nodes.add(toAddRight);
            }
        }

        System.out.println("--------------------------------");

        System.out.print ("Preorder Traversal : ");
        tree.preOrder(tree.getRoot());

        System.out.print ("\nInorder Traversal : ");
        tree.inOrder(tree.getRoot());

        System.out.print ("\nPostrder Traversal : ");
        tree.postOrder(tree.getRoot());


    }
}

/*
DS:
1- Linked List
2- Queue
*/