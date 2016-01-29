import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
public class news{

    public static void main(String[] args){
        Scanner stdin = new Scanner(System.in);
        int numCases = Integer.parseInt(stdin.nextLine());
        
        for (int i = 0; i < numCases; i++){
            int numEmployees = Integer.parseInt(stdin.nextLine());

            String[] textInput = stdin.nextLine().split(" ");
            int[] intInput = new int[textInput.length];
            for (int loop = 0; loop < textInput.length; loop++){
                intInput[loop] = Integer.parseInt(textInput[loop]);
            }

            Node root = new Node(0);
            for (int j = 1; j < numEmployees; j++){
                int supervisor = intInput[j-1];
                root.insert(root, supervisor, j);
            }
//            root.printBFS(root);

            System.out.println(root.height(root));
        }
    }
}

class Node{
    int id;
    ArrayList<Node> children;

    Node(int id){
        this.id = id;
        this.children = new ArrayList<Node>();
    }

    public void insert(Node root, int value, int id){
        if (root == null){
            return;
        } 
        else if (value == root.id){
            root.children.add(new Node(id));
        }
        else{
            for (Node node : root.children){
                insert(node, value, id);
            }
        }
    }

    public void printBFS(Node root){
        if (root == null){
            return;
        }
        for (Node node : root.children){
            System.out.print(" " + node.id);
        }
        System.out.println();
        for (Node node : root.children){
            printBFS(node);
        }
    }
    

    public int height(Node root){
        ArrayList<Integer> heights = new ArrayList<Integer>();
        if (root == null){
            return 0;
        }
        for (Node node : root.children){
            heights.add(height(node));
        }
        int height = max(heights);
        return height+1;
    }

    public int max(ArrayList<Integer> nums){
        int max = 0; 
        for (Integer n : nums){
            if (n > max){
                max = n;
            }
        }
        return max;
    }
    

}

