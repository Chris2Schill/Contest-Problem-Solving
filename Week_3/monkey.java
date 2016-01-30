import java.util.Scanner;
import java.util.ArrayList;

public class monkey{

    public static double countMonkeys(String input){
        double max = 0;

        double count = 0;
        char[] letters = input.toCharArray();
        for (char c : letters){
            if (c == '['){
                count++;
            }else if(c == ']'){
                count--;
                count = count < 0 ? 0 : count;
            }
            
            if (count > max){
                max = count;
            }
        }
        return Math.pow(2,max);
    }

    public static void main(String[] args){
        Scanner stdin = new Scanner(System.in);
        int numCases = Integer.parseInt(stdin.nextLine());
        ArrayList<String> outputs = new ArrayList<String>();
        for (int caseNum = 1; caseNum <= numCases; caseNum++){
            String input = stdin.nextLine(); 
            System.out.println(caseNum + " " + (int)countMonkeys(input)); 
        }
    }

}
