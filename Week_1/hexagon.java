import java.util.Scanner;
import java.util.Arrays;
public class hexagon{





    private static String solvedHex = "1 5 6 2 4 3 3 5 6 1 2 4 4 2 3 1 5 6 1 2 4 5 3 6 2 3 6 4 5 1 5 4 1 3 6 2 6 2 3 5 4 1";







    
    private static final int NUM_HEXAGONS = 7; 
    private static final int NUM_HEX_SIDES = 6; 

    private static String input1 = "3 5 6 1 2 4 5 1 2 3 6 4 2 3 5 4 1 6 3 1 5 6 2 4 5 4 1 3 6 2 4 2 3 1 5 6 3 6 1 2 4 5";
    private static String input2 = "6 3 4 1 2 5 6 4 3 2 5 1 6 5 3 2 4 1 5 4 6 3 1 2 2 5 6 1 4 3 4 6 3 5 2 1 1 3 5 2 6 4";

    private static int[][] hexagons = new int[NUM_HEXAGONS][NUM_HEX_SIDES];

    private static int[] rotateClockwise(int[] hexagon){

        int[] newHexagon = new int[NUM_HEX_SIDES];
        newHexagon[0] = hexagon[5];
        for (int i = 1; i < NUM_HEX_SIDES; i++){
            newHexagon[i] = hexagon[i-1];
        }
        return newHexagon;
        
    }

    private static int[] rotateCounterClockwise(int[] hexagon){
        int[] newHexagon = new int[NUM_HEX_SIDES];
        newHexagon[5] = hexagon[0];
        for (int i = NUM_HEX_SIDES-2; i >= 0; i--){
            newHexagon[i] = hexagon[i+1];
        }
        return newHexagon;
        
    }

    // Not enough time, in the works
    private static boolean permuteHexagons(int[][] hex, boolean[] hexUsed,
                                            boolean[] rotationUsed, int k, int l){
        boolean solved = false;
        if (k == NUM_HEXAGONS){
            solved = isSolved(hex);
            if (l == NUM_HEX_SIDES){
                if (isSolved(hex)){
                    solved = true;
                }
            }
            else if (!rotationUsed[l]){
                for (int i = 0; i < NUM_HEX_SIDES; i++){
                    hex[i] = rotateClockwise(hex[i]);
                    rotationUsed[l] = true;
                    solved = permuteHexagons(hex, hexUsed, rotationUsed, k, l+1);
                    rotationUsed[l] = false;
                }
            }
            
        }
        else{
            System.out.println(Arrays.toString(hex));
            if (!hexUsed[k]){
                for (int i = 0; i < NUM_HEXAGONS; i++){
                    hex[k] = hexagons[i];
                    hexUsed[k] = true;
                    permuteHexagons(hex,hexUsed,rotationUsed, k+1, l);
                    hexUsed[k] = false;
                }
            } 
        }
        return solved;
    }

    private static boolean isSolved(int[][] hex){
        if (hex[0][0] == hex[1][3] && 
                hex[0][1] == hex[2][4] && 
                hex[0][2] == hex[3][5] && 
                hex[0][3] == hex[4][0] && 
                hex[0][4] == hex[5][1] && 
                hex[0][5] == hex[6][2]) {

            if (hex[1][2] == hex[2][5] &&
                    hex[2][3] == hex[3][0] &&
                    hex[3][4] == hex[4][1] &&
                    hex[4][5] == hex[5][2] &&
                    hex[5][0] == hex[6][3] &&
                    hex[6][1] == hex[1][4]){

                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        /*
        Scanner stdin = new Scanner(System.in);
        int numCases = Integer.parseInt(stdin.nextLine());
        for (int i = 0; i < numCases; i++){
            
        }
        */
        int inputIndex = 0;
        String[] inputArray = solvedHex.split(" ");
        for (int i = 0; i < NUM_HEXAGONS; i++){
            for (int j = 0; j < NUM_HEX_SIDES; j++){
                hexagons[i][j] = Integer.parseInt(inputArray[inputIndex]);
                inputIndex++;
            }
        }
        boolean[] hexUsed = new boolean[NUM_HEXAGONS];
        boolean[] rotationUsed = new boolean[NUM_HEX_SIDES];

        boolean solved = false;
        solved = permuteHexagons(hexagons, hexUsed, rotationUsed, 0, 0);
        System.out.println(solved);

 /*       for (int i = 0; i < NUM_HEXAGONS; i++){
            System.out.println(Arrays.toString(hexagons[1])); 
            System.out.println(Arrays.toString(rotateCounterClockwise(hexagons[1]))); 
            System.out.println();
        }
*/

    }
}

