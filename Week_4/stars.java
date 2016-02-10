import java.util.*;

public class stars{

    private ArrayList<ArrayList<Edge>> constellations;
    private int numCycles = 0;

    public void classify(Edge edge){
        int sourceIndex = -1;
        int destinationIndex = -1;
        boolean foundSource = false;
        boolean foundDestination = false;
        boolean foundBothInSameList = false;
        for (int i = 0; i < constellations.size(); i++){
            boolean foundSourceHere = false;
            boolean foundDestHere = false;
            ArrayList<Edge> currConstellation = constellations.get(i);
            for (int j = 0; j < currConstellation.size(); j++){
                int found = edge.compareTo(currConstellation.get(j));
                if (found == 3){
                    sourceIndex = i;
                    destinationIndex = i;
                    foundSource = true;
                    foundDestination = true;
                    foundSourceHere = true;
                    foundDestHere = true;
                }else if (found == 2){
                    destinationIndex = i;
                    foundDestination = true; 
                    foundDestHere = true;
                }else if (found == 1){
                    sourceIndex = i;
                    foundSource = true;
                    foundSourceHere = true;
                }
                
            }
            if (foundSourceHere && foundDestHere){
                foundBothInSameList = true;
            }
        }
//        System.out.println("Source " + foundSource + ", Destination " + foundDestination);
        if (foundSource && foundDestination){
 //           System.out.println("FoundBothInSame: " + foundBothInSameList);
            if (foundBothInSameList){
                constellations.get(sourceIndex).add(new Edge(-1,-1));
                constellations.get(sourceIndex).add(edge);
            }else{
                ArrayList<Edge> tmp = new ArrayList<Edge>();
                for (Edge e : constellations.get(sourceIndex)){
                    tmp.add(e);
                } 

                for (Edge e : tmp){
                    constellations.get(destinationIndex).add(e);
                }
                constellations.get(destinationIndex).add(edge);
                constellations.get(sourceIndex).clear();
            }
        }else if (foundSource){
            constellations.get(sourceIndex).add(edge);
        }else if (foundDestination){
            constellations.get(destinationIndex).add(edge);
        }else{
            ArrayList<Edge> newConstellation = new ArrayList<Edge>();
            newConstellation.add(edge);
            constellations.add(newConstellation);
        }
  //      printConstellations(constellations);
   //     System.out.println("------------------");
    }

    public int numConstellations(ArrayList<ArrayList<Edge>> constellations){
        int num = 0;
        for (ArrayList<Edge> list : constellations){
            try{
                Edge e = list.get(0);
                num++;
            }catch(Exception e){}
        }
        return num;
    }

    public int numCycles(ArrayList<ArrayList<Edge>> constellations){
        int num = 0;
        for (ArrayList<Edge> list : constellations){
            for (Edge e : list){
                if (e.compareTo(new Edge(-1,-1)) == 3){
                    num++; 
                    break;
                }
            }
        }
        return num;
    }

    stars(){
       constellations = new ArrayList<ArrayList<Edge>>(); 
       Scanner stdin = new Scanner(System.in);
       int n = Integer.parseInt(stdin.nextLine());
       for (int i = 1; i <= n; i++){
           StringTokenizer strTok1 = new StringTokenizer(stdin.nextLine());
           int numStars = Integer.parseInt(strTok1.nextToken());
           int numConnections = Integer.parseInt(strTok1.nextToken());
           for (int j = 0; j < numConnections; j++){
               StringTokenizer strTok2 = new StringTokenizer(stdin.nextLine()); 
               int source = Integer.parseInt(strTok2.nextToken());
               int destination = Integer.parseInt(strTok2.nextToken());
               Edge edge = new Edge(source,destination);
               classify(edge);
           }
           System.out.println("Night sky #" +i+ ": " + numConstellations(constellations) +
                " constellations" + ", of which " + numCycles(constellations) + " need to be fixed.");
           constellations.clear();
           System.out.println();
       }
    }

    public static void main(String[] args){
        new stars();
    }

    public void printConstellations(ArrayList<ArrayList<Edge>> constellations){
        for (int i = 0; i < constellations.size(); i++){
            System.out.println("Constellation: " + i);
            ArrayList<Edge> currList = constellations.get(i);
            for (int j = 0; j < currList.size(); j++){
                System.out.print(currList.get(j) + " ");
            }
            System.out.println();
        }
    }
}

class Edge implements Comparable<Edge>{
    int source;
    int destination;

    Edge(int s, int d){
        this.source = s;
        this.destination = d;
    }

    public int compareTo(Edge e){
        if ((source == e.source && destination == e.destination) ||
                (source == e.destination && destination == e.source)){
            return 3;
        }else if (source == e.source || source == e.destination){
            return 1;
        }else if (destination == e.source || destination == e.destination){
            return 2;
        }else {
            return 0;
        }
    }

    public String toString(){
        return "("+source+","+destination+")";
    }
}
