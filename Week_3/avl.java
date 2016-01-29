import java.util.Scanner;
import java.util.StringTokenizer;
public class avl{
    public static void main(String[] args){
        Scanner stdin = new Scanner(System.in);
        
        int numCases = Integer.parseInt(stdin.nextLine());
        for (int caseNum = 1; caseNum < numCases+1; caseNum++){
            StringTokenizer strTok = new StringTokenizer(stdin.nextLine());

            int numInserts = Integer.parseInt(strTok.nextToken());

            boolean unbalanced = false;

            if (numInserts <= 0){
                System.out.println("Tree #" + caseNum + ": KEEP");
            }
            else{
                Node root = new Node(Integer.parseInt(strTok.nextToken()));
                for (int i = 1; i < numInserts; i++){
                    root.insert(root,Integer.parseInt(strTok.nextToken()));
//                    System.out.println("LHeight: " + root.height(root.left));
//                    System.out.println("RHeight: " + root.height(root.right));

                    if (needsBalancing(root)){
                        unbalanced = true;
                        break;
                    }
                }
//                root.print(root);

                if (unbalanced){
                    System.out.println("Tree #" + caseNum + ": REMOVE");
                }
                else{
                    System.out.println("Tree #" + caseNum + ": KEEP");
                }
            }

            

        }
    }

    public static boolean needsBalancing(Node root){
        int heightLeft = root.height(root.left);
        int heightRight = root.height(root.right);
        int heightDifference;
        if (heightLeft - heightRight >= 0){
            heightDifference = heightLeft - heightRight;
        }
        else{
            heightDifference = heightRight - heightLeft;
        }
        return heightDifference > 1;
    }
}

class Node{
    int value;
    Node left;
    Node right;

    Node(int value){
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public Node insert(Node root, int value){
        if (root == null){
            return new Node(value);
        }
        if (value < root.value){//go left
            root.left = insert(root.left, value);
        }
        else if (value > root.value){//go right
            root.right = insert(root.right, value);
        }
        return root;
    }
    
    public void inOrder(Node root){
        if (root.left != null) inOrder(root.left); 
        System.out.print(root.value + " ");
        if (root.right != null) inOrder(root.right); 
    }
    
    public void preOrder(Node root){
        System.out.print(root.value + " ");
        if (root.left != null) preOrder(root.left); 
        if (root.right != null) preOrder(root.right); 
    }

    public void postOrder(Node root){
        if (root.left != null) postOrder(root.left); 
        if (root.right != null) postOrder(root.right); 
        System.out.print(root.value + " ");
    }

    public void print(Node root){
        System.out.print("In Order: ");
        inOrder(root);System.out.println();
        System.out.print("Pre Order: ");
        preOrder(root);System.out.println();
        System.out.print("Post Order: ");
        postOrder(root);System.out.println();
    }

    public int height(Node root){
        if (root == null){
            return 0;
        }
        int sumLeft = height(root.left);
        int sumRight = height(root.right);
        return sumLeft > sumRight ? sumLeft + 1 : sumRight + 1;
    }

    

}

