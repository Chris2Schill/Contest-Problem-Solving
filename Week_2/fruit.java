import java.util.Scanner;
import java.util.Arrays;
public class fruit{

    private static int dailyAmount;

    public static void main(String[] args){
        Scanner stdin = new Scanner(System.in);
        int fruitStands = Integer.parseInt(stdin.nextLine());

        for (int i = 0; i < fruitStands; i++){
            String[] line = stdin.nextLine().split(" "); 
            int[] fruitStand = new int[line.length]; 
            for (int j = 0; j < line.length; j++){
                fruitStand[j] = Integer.parseInt(line[j]);
            }
            //System.out.println(Arrays.toString(fruitStand));
            dailyAmount = average(fruitStand);
            
            int maxStored = testAmount(fruitStand, dailyAmount);
            while (maxStored < 0){
                dailyAmount++;
                maxStored = testAmount(fruitStand, dailyAmount);
            }

            System.out.println(dailyAmount + " " + maxStored);
            
        }
    }

    public static int average(int[] array){
        int sum = 0;
        for (int i = 1; i < array.length; i++){
            sum += array[i];
        }
        return sum/(array.length-1);
    }

    public static int testAmount(int[] fruitStand, int amount){
        int numFruit = 0;
        int maxStored = 0;
        for (int i = 1; i < fruitStand.length; i++){
            numFruit += amount;
            numFruit -= fruitStand[i];
            if (numFruit < 0){
                maxStored = -1;
                break;
            }
            if (numFruit > maxStored){
                maxStored = numFruit;
            }
            //System.out.println("numFruit: " + numFruit + ", maxStored: " + maxStored + ", iteration: " + i);

        } 
        return maxStored;
    }
}

