import java.util.*;
public class generations{


    long[] memo;

    public void doit(){
        Scanner stdin = new Scanner(System.in);
        memo = new long[68];
        memo[0] = 1;
        memo[1] = 1;
        memo[2] = 2;
        memo[3] = 4;

        int cases = Integer.parseInt(stdin.nextLine());

        for (int i = 4; i < memo.length; i++){
            memo[i] = memo[i-1] + memo[i-2] + memo[i-3] + memo[i-4];
        }

        for (int testCase = 0; testCase < cases; testCase++){
            int test = Integer.parseInt(stdin.nextLine());
            System.out.println(memo[test]);
        }
    }

    public static void main(String[] args){
        new generations().doit();
    }
}
