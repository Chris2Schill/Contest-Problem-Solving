import java.util.*;
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

            TurnController tc = new TurnController();
            for (int j = 1; j < numEmployees; j++){
                int supervisor = intInput[j-1];
                tc.tree.insert(tc.tree.root, j, supervisor);
            }
            tc.tree.root.setCallsToMake(tc.tree.root.children.size());
            tc.calledNodesSizeTarget = tc.tree.size() - 1;

            System.out.println("Called Nodes Target Size: " + tc.calledNodesSizeTarget);
            printSupervisors(tc.tree.root);
            System.out.println("Minutes: " + tc.findMinTimeItTakes());
        }
    }


    public static int height(Node root){
        boolean isLeaf = (root == null);
        if (isLeaf){
            return 0;
        }
        int maxDepth = 0;
        for (Node child : root.children){
            maxDepth = Math.max(maxDepth, height(child));
        }
        return maxDepth+1;
    }

    public static int max(ArrayList<Integer> nums){
        int max = 0; 
        for (Integer n : nums){
            if (n > max){
                max = n;
            }
        }
        return max;
    }

    public static void printSupervisors(Node root){
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while (queue.size() > 0){
            Node n = queue.remove();
            System.out.print("ID: " + n.id + " ");
            System.out.print("SV: " + n.supervisor + "\n");
            for (Node child : n.children){
                queue.add(child);
            }
        }
        System.out.println();
    }

}

class TurnController{
    Tree tree;
    ArrayList<Node> calledNodes;
    ArrayList<Node> newlyCalledNodes;
    int calledNodesSizeTarget;
    boolean finished = false;

    public void printCalledNodes(){
        for (Node node : calledNodes){
            System.out.println(node);
        }
    }

    TurnController(){
        tree = new Tree();
        calledNodes = new ArrayList<Node>();
        newlyCalledNodes = new ArrayList<Node>();
        calledNodes.add(tree.root);
    }

    public int findMinTimeItTakes(){
        int minutes = 0;
        while (!finished){
            System.out.println("-----Minute " + minutes+ "------");
            System.out.println("Called Nodes Size: " + calledNodes.size()); 
            simulateTurn();
            mergeCalledNodes();
            System.out.println("-----End of Minute "+ minutes + "------\n");
            minutes++;
            if (minutes > 10) break;
        }
        return minutes;
    }

    public void mergeCalledNodes(){
        for (Node n : newlyCalledNodes){
            calledNodes.add(n);
        }
        newlyCalledNodes.clear();
    }
    
    public void simulateTurn(){
        for (Node node : calledNodes){
            notifyNextChild(node);
        }
        finished = allNodesCalled();
        System.out.println("Finished: " + finished);
    }

    public void notifyNextChild(Node node){
        if (node.callsToMake > 0){
            Node child = node.children.remove();
            System.out.println("Node ID: " + node.id + ", callsToMake: " + node.callsToMake + 
                                ", calling Node " + child.id);
            call(child);
            node.callsToMake--;
        }
    }

    public void call(Node node){
        node.setNotified(true); 
        node.setCallsToMake(node.children.size());
        newlyCalledNodes.add(node);
        System.out.println("Calling Node ID: " + node.id + ", numChilds: " + node.children.size() + 
                           ", Notified: " + node.notified);
    }

    public boolean allNodesCalled(){
        if (calledNodes.size() >= calledNodesSizeTarget){
            return true;
        }
        return false;
    }

    

    
}

class Tree{
    Node root;

    Tree(){
        root = new Node(0,0);
        root.notified = true;
    }

    public void insert(Node currNode, int id, int supervisor){
        if (currNode == null){
            return;
        }else if (supervisor == currNode.id){
            currNode.children.add(new Node(id,supervisor));
        }else{
            for (Node node : currNode.children){
                insert(node, id, supervisor);
            }
        }
    }

    public int size(){
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        int i = 0;
        while (queue.size() > 0){
            Node currNode = queue.remove();
            i++;
            for (Node child : currNode.children){
                queue.add(child);
            }
        }
        return i;
    }


}

class Node{
    int id;
    int supervisor;
    int callsToMake = 0;
    boolean notified = false;
    static TurnController turn = new TurnController();
    PriorityQueue<Node> children = new PriorityQueue<Node>(new MostChildren());

    Node(int id, int supervisor){
        this.id = id;
        this.supervisor = supervisor;
    }

    public void setNotified(boolean b){
        notified = b;
    }

    public void setCallsToMake(int n){
        callsToMake = n;
    }

    public String toString(){
        return "" + id; 
    }
}

class MostChildren implements Comparator<Node>{

    public int compare(Node n1, Node n2){
        if (n1.children.size() > n2.children.size()){
            return -1;
        }else if (n1.children.size() < n2.children.size()){
            return 1;
        }else{
            return 0;
        }
    } 
}



