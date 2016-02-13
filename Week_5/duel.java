import java.util.*;
import java.io.*;

public class duel{
    public static void main(String[] args)throws Exception{
        Scanner stdin = new Scanner(new File("input3.txt"));

        while (true){
            int numEssays, numRelationships;
            StringTokenizer strTok = new StringTokenizer(stdin.nextLine());
            numEssays = Integer.parseInt(strTok.nextToken());
            numRelationships = Integer.parseInt(strTok.nextToken());
            if (numEssays == 0 && numRelationships == 0){
                break;
            }
            Graph g = new Graph(numEssays);
            for (int i = 0; i < numRelationships; i++){
                strTok = new StringTokenizer(stdin.nextLine());
                int defined = Integer.parseInt(strTok.nextToken());
                int used = Integer.parseInt(strTok.nextToken());
                g.addEdge(defined, used);
            }
            g.topologicalSort();
        }

    }
}

class Graph{
    private int V;
    private LinkedList<LinkedList<Integer>> adj;

    Graph(int v){
        V = v;
        adj = new LinkedList<LinkedList<Integer>>();
        for (int i=0; i<v; i++)
            adj.add(new LinkedList<Integer>());
    }

    public void addEdge(int v,int w) {
        System.out.println("V: " + v + ", W: " + w);
        adj.get(v-1).add(w);
    }

    public void topologicalSortUtil(int v, boolean visited[],Stack<Integer> stack){
        visited[v] = true;
        Integer i;

        Iterator<Integer> it = adj.get(v).iterator();
        while (it.hasNext()){
            i = it.next();
            if (!visited[v])
                topologicalSortUtil(i, visited, stack);
        }
        stack.push(new Integer(v));
    }

    public void topologicalSort(){
        Stack<Integer> stack = new Stack<Integer>();

        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;

        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                topologicalSortUtil(i, visited, stack);

        while (stack.empty()==false)
            System.out.print(stack.pop()+1 + " ");
        
    }
}
