import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
public class cpu{

    private static ArrayList<Edge> edges = new ArrayList<Edge>();
    private static ArrayList<Edge> minTree = new ArrayList<Edge>();
    private static ArrayList<Edge> disjointSet1 = new ArrayList<Edge>();
    private static ArrayList<Edge> disjointSet2 = new ArrayList<Edge>();

    public static void main(String[] args){
        Scanner stdin = new Scanner(System.in);
        int cases = Integer.parseInt(stdin.nextLine());


        for (int c = 0; c < cases; c++){
            int matrixSize = Integer.parseInt(stdin.nextLine());
            int[][] graph = new int[matrixSize][matrixSize];
            for (int i = 0; i < matrixSize; i++){
                for (int j = 0; j < matrixSize; j++){
                    graph[i][j] = stdin.nextInt();
                }
            }
            int[][] testGraph = {{0,1,2},{1,0,4},{2,4,0}};

            for (int i = 0; i < matrixSize; i++){
                for (int j = i+1; j < matrixSize; j++){
                    if (graph[i][j] != 0){
                        edges.add(new Edge(i, j, graph[i][j])); 
                    }
                }
            }

            Collections.sort(edges,new ByWeight());
            minTree.add(edges.get(0));
            for (int i = 1; i < edges.size(); i++){
                Edge e = edges.get(i);
                disjointSet1.clear();
                for (Edge ee : minTree){
                    disjointSet1.add(ee);
                }
                boolean edgeXFound = false;
                boolean edgeYFound = false;
                for (Edge minTreeEdge : disjointSet1){

                    if (e.x == minTreeEdge.x || e.x == minTreeEdge.y) edgeXFound = true;
                    if (e.y == minTreeEdge.y || e.y == minTreeEdge.y) edgeYFound = true;
                    if (edgeXFound && edgeYFound){
                        
                    }
                    else{
                        minTree.add(e);
                    }
                }
            }

            for (Edge e : minTree){
                System.out.println(e);
            }
            int sum = 0;
            for (int i = 0; i < minTree.size()-1; i++){
                sum += minTree.get(i).weight;
            }
            System.out.println(sum);
        }
    }
}

class Edge{
    static int idCounter = 0;
    int uniqueId = 0;
    int x;
    int y;
    int weight;

    Edge(int x, int y, int weight){
        this.x = x;
        this.y = y;
        this.weight = weight;
        this.uniqueId = idCounter++;
    }

    public String toString(){
        return x + "---" + weight + "---" + y;
    }
    
}

class ByWeight implements Comparator<Edge>{
    public int compare(Edge e1, Edge e2){
        if (e1.weight < e2.weight){
            return -1;
        }
        else if (e1.weight > e2.weight){
            return 1;
        }
        else{
            return 0;
        }
    
    }
}
