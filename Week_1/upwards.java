import java.util.Scanner;
public class upwards{

    private static boolean isDistinctAndOrdered(String word){ 
        if (word.length() == 0){
            return true;
        }

        char previous = word.charAt(0);

        for (int i = 1; i < word.length(); i++){
            char current = word.charAt(i);
            if (current <= previous) return false;
            previous = current;
        }

        return true;
    }

    public static void main(String[] args){

        Scanner stdin = new Scanner(System.in);
        int numCases = Integer.parseInt(stdin.nextLine());
        for (int i = 0; i < numCases; i++){
            String word = stdin.nextLine();
            if (isDistinctAndOrdered(word)){
                System.out.println("YES");
            }
            else{
                System.out.println("NO");
            }
        } 
    }
}
