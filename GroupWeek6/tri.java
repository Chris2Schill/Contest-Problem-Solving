import java.util.*;
import java.io.*;
public class tri{



    int[][] graph;

    public void doit()throws Exception{
        Scanner stdin = new Scanner(new File("input.txt"));

        for (;;){
            int numRows = Integer.parseInt(stdin.nextLine());
            graph = new int[numRows][3];

            for (int row = 0; row < numRows; row++){
                StringTokenizer strTok = new StringTokenizer(stdin.nextLine());
                graph[row][0] = Integer.parseInt(strTok.nextToken());
                graph[row][1] = Integer.parseInt(strTok.nextToken());
                graph[row][2] = Integer.parseInt(strTok.nextToken());
            }


            Graph g;
            
            int[][] matrix = new int[numRows*3][numRows*3];
            for (int i = 0; i < graph.length; i++){
                for (int j = 0; j < graph[i].length; j++){
                    if (j == 0){
                        matrix[i][j+1];                    
                        matrix[i+1][j];
                        matrix[i+1][j+];
                    }else if (j == 1){
                        v.out.add(matrix[i][j+1]);
                        v.out.add(matrix[i+1][j-1]);
                        v.out.add(matrix[i+1][j]);
                        v.out.add(matrix[i+1][j+1]);
                    }else{
                        v.out.add(matrix[i+1][j-1]);
                        v.out.add(matrix[i+1][j]);
                    }
                }
            }

            Vertex start = graph[0][1];
            dijkstras shortestPathAlg = new dijkstras(graph);
            int answer = shortestPathAlg.shortestPath;
            System.out.println(answer);

        }

    }

    public static void main(String[] args)throws Exception{
        new tri().doit();
    }
}


// class dijkstras{
//     static int INT_MAX = Integer.MAX_VALUE;
//     static int n;
//     ArrayList<Vertex> g;
//     Vertex[][] graph;
//     int shortestPath;
//
//     dijkstras(Vertex[][] vertex){
//         this.graph = vertex; 
//         shortestPath = run(vertex[0][1].id, vertex[vertex.length-1][1].id);
//     }
//
//     public int run(int s, int d){
//         boolean[] visited = new boolean[graph.length * graph[0].length];
//         PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
//         pq.add(graph[0][1]);
//         int totalDist = 0;
//         while (!pq.isEmpty()){
//             Vertex at = pq.poll();
//             if (visited[at.id]){
//                 continue;
//             }
//             System.out.println("Vertex ID: " + at.id);
//             totalDist += at.value;
//             visited[at.id] = true;
//
//             if (at.id == d) return totalDist;
//
//             for (Vertex adj : at.out){
//                 if (!visited[adj.id]){
//                     pq.add(adj);
//                 }
//             }
//         }
//         return INT_MAX;
//     }
// }
//
// // class Edge{
// //     int e, w;
// //     int si, sj;
// //     int di, dj;
// //
// //     public Edge(int e, int w){
// //         this.e = e;
// //         this.w = w;
// //     }
// //
// //     public int compareTo(Edge o){
// //         return w - o.w;
// //     }
// // }
//
// // class Vertex implements Comparable<Vertex>{
// //     int id;
// //     int value;
// //     static int numVertex = 0; 
// //     ArrayList<Vertex> out;
// //
// //     Vertex(int value){
// //         this.id = numVertex++;
// //         this.value = value;
// //         out = new ArrayList<Vertex>();
// //     }
// //
// //     public int compareTo(Vertex v){
// //         if (this.id < v.id){
// //             return -1;
// //         }else if (this.id == v.id){
// //             return 0;
// //         }else {
// //             return 1;
// //         }
// //     }
// // }

class TopologicalSort{

    public static void topologicalSort(Graph graph){
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        boolean[] visited = new boolean[graph.vertices.length];
        visited[0] = true;
        Vertex[] vertices = graph.vertices;
        while (!stack.isEmpty()){
            int currVertex = stack.pop();
            // Process here.
            System.out.println("Vertex: " + currVertex + 
                    ", Edges: " + vertices[currVertex].toString());
            for (Integer i : vertices[currVertex]){
                if (!visited[i]){
                    stack.push(i);
                    visited[i] = true;
                }
            }
        }
    }
}

class Graph{
    Vertex[] vertices;

    Graph(int[][] matrix){
        this.vertices = graphFromAdjacencyMatrix(matrix);
    }

    public Vertex[] graphFromAdjacencyMatrix(int[][] matrix){
        //Add all vertices
        Vertex[] list = new Vertex[matrix.length];
        for (int vertex = 0; vertex < matrix.length; vertex++){
            Vertex newVertex = new Vertex(); 
            for (int edge = 0; edge < matrix[vertex].length; edge++){
                if (matrix[vertex][edge] != 0){
                    newVertex.add(edge);
                }
            }
            list[vertex] = newVertex;
        } 

        // We reverse the order of the edges so that the dfs works as a topological sort.
        // It makes the stack process vertex A before Vertex B if 
        // encountered at the same time. (Lexicographical Order)
        for (Vertex v : list){
            Collections.reverse(v);
        }
        return list;
        
    }
}
class Vertex extends ArrayList<Integer>{
    
}
