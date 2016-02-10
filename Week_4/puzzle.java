import java.util.*;
import java.io.*;
public class puzzle {

    private static int[] startingBoard = {1,2,3,
                                          4,5,6,
                                          7,8,0};

    private static Hashtable<String,Integer> seenBoardStates = new Hashtable<String,Integer>();

    private static Tree getAllStates() throws Exception{
        Tree board = new Tree(startingBoard, 0);
        Queue<Tree> queue = new LinkedList<Tree>();
        queue.add(board);
        int currentDepth = 0;
        int timeToDepthIncrease = 1;
        int pendingDepthIncrease = 0;
        while (currentDepth < 31){
            Tree currState = queue.remove();
            if (seenBoardStates.get(Arrays.toString(currState.board)) == null){
                seenBoardStates.put(Arrays.toString(currState.board),currentDepth);
            }
            System.out.println("Depth: " + currentDepth + ", " + Arrays.toString(currState.board));

            int[][] futureStates = getFutureStates(currState.board);
            pendingDepthIncrease += futureStates.length;

            timeToDepthIncrease--;
            if (timeToDepthIncrease == 0){
                currentDepth++;
                timeToDepthIncrease = pendingDepthIncrease;
                pendingDepthIncrease = 0;
            }

            for (int i = 0; i < futureStates.length; i++){
                Tree aFutureState = new Tree(futureStates[i],currentDepth);
                currState.futureStates.add(aFutureState);
                queue.add(aFutureState);
            }
        }
        return board;
    }


    private static int[][] getFutureStates(int[] currBoard) throws Exception{
        int zeroIndex = 0;
        for (int i = 0; i < 9; i++){
            if (currBoard[i] == 0){
                zeroIndex = i;
                break;
            }
        }
        int[] swappedUpwards = swapUp(currBoard, zeroIndex);
        int[] swappedDownwards = swapDown(currBoard,zeroIndex);
        int[] swappedLeftwards = swapLeft(currBoard,zeroIndex);
        int[] swappedRightwards = swapRight(currBoard,zeroIndex);

        if (seenBoardStates.containsKey(Arrays.toString(swappedUpwards)))
            swappedUpwards = null;
        if (seenBoardStates.containsKey(Arrays.toString(swappedDownwards)))
            swappedDownwards = null;
        if (seenBoardStates.containsKey(Arrays.toString(swappedLeftwards)))
            swappedLeftwards = null;
        if (seenBoardStates.containsKey(Arrays.toString(swappedRightwards)))
            swappedRightwards = null;

        int numStates = 0;
        if (swappedUpwards != null) numStates++;
        if (swappedDownwards != null) numStates++;
        if (swappedLeftwards != null) numStates++;
        if (swappedRightwards != null) numStates++;
        int[][] futureStates = new int[numStates][9];

        numStates = 0;
        if (!Arrays.equals(currBoard,swappedUpwards) && swappedUpwards != null) 
            futureStates[numStates++] = swappedUpwards;
        if (!Arrays.equals(currBoard,swappedDownwards) && swappedDownwards != null) 
            futureStates[numStates++] = swappedDownwards;
        if (!Arrays.equals(currBoard,swappedLeftwards) && swappedLeftwards != null) 
            futureStates[numStates++] = swappedLeftwards;
        if (!Arrays.equals(currBoard,swappedRightwards) && swappedRightwards != null) 
            futureStates[numStates++] = swappedRightwards;

        return futureStates;
    }

    private static int[] swapUp(int[] board, int zeroIndex) throws Exception{
        try{
            int[] newArray = new int[9];    
            for (int i = 0; i < board.length; i++){
                newArray[i] = board[i];
            }
            newArray = swap(newArray, zeroIndex, zeroIndex-3);
            return newArray;
        }catch(Exception e){
            return null;
        }
    }

    private static int[] swapDown(int[] board, int zeroIndex) throws Exception{
        try{
            int[] newArray = new int[9];    
            for (int i = 0; i < board.length; i++){
                newArray[i] = board[i];
            }
            newArray = swap(newArray, zeroIndex, zeroIndex+3);
            return newArray;
        }catch(Exception e){
            return null;
        }
    }

    private static int[] swapLeft(int[] board, int zeroIndex) throws Exception{
        if (zeroIndex == 0 || zeroIndex == 3 || zeroIndex == 6) 
            return null;
        try{
            int[] newArray = new int[9];    
            for (int i = 0; i < board.length; i++){
                newArray[i] = board[i];
            }
            newArray = swap(newArray, zeroIndex, zeroIndex-1);
            return newArray;
        }catch(Exception e){
            return null;
        }
    }

    private static int[] swapRight(int[] board, int zeroIndex) throws Exception{
        if (zeroIndex == 2 || zeroIndex == 5 || zeroIndex == 8)
            return null;
        try{
            int[] newArray = new int[9];    
            for (int i = 0; i < board.length; i++){
                newArray[i] = board[i];
            }
            newArray = swap(newArray, zeroIndex, zeroIndex+1);
            return newArray;
        }catch(Exception e){
            return null;
        }
    }
    
    private static int[] swap(int[] array, int x, int y){
        try{
            int tmp = array[x];
            array[x] = array[y];
            array[y] = tmp;
            return array;
        }catch(Exception e){
            return null;
        }
    }

    public static void main(String[] args) throws Exception{

        Tree possibleBoardStates = getAllStates();
        Scanner stdin = new Scanner(System.in);// new Scanner(new File("input.txt"));
        int numCases = Integer.parseInt(stdin.nextLine());
        for (int caseNum = 0; caseNum < numCases; caseNum++){
            int boardIndex = 0;
            int[] board = new int[9];
            for (int i = 0; i < 3; i++){
                String[] row = stdin.nextLine().split(" ");
                for (int j = 0; j < row.length; j++){

                    board[boardIndex++] = Integer.parseInt(row[j]);
                }
            }
            int minMoves = search(board);
            System.out.println(minMoves);
        }
    }

    public static int search(int[] board){
        return seenBoardStates.get(Arrays.toString(board));
    }
}

class Tree{
    int[] board;
    int numMoves;
    List<Tree> futureStates;

    Tree(int[] board, int numMoves){
        this.board = board;
        this.numMoves = numMoves;
        this.futureStates = new ArrayList<Tree>();
    }

    public int search(int[] board){
        Queue<Tree> queue = new LinkedList<Tree>();
        queue.add(this);
        int depth = 0;
        int timeToNextDepth = 1;
        int pendingDepthIncrease = 0;
        while (queue.size() > 0){
            Tree currBoard = queue.remove();
            pendingDepthIncrease += currBoard.futureStates.size();
            
            System.out.println("Depth: " + depth + " " + Arrays.toString(currBoard.board));
            if (Arrays.equals(currBoard.board, board)){
                return depth;
            }else{
                if (--timeToNextDepth == 0){
                    depth++;
                    timeToNextDepth = pendingDepthIncrease;
                    pendingDepthIncrease = 0;
                }

                for (Tree nextState : currBoard.futureStates){
                    queue.add(nextState);
                }
            }
        }
        return -1;
    }

}
