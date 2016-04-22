import java.util.*;
public class balloons{

    public void doit(){
        Scanner stdin = new Scanner(System.in);
        int cases = Integer.parseInt(stdin.nextLine());
        for (int testCase = 0; testCase < cases; testCase++){
            String[] nxy = stdin.nextLine().split(" ");
            int N = Integer.parseInt(nxy[0]);
            int X = Integer.parseInt(nxy[1]);
            int Y = Integer.parseInt(nxy[2]);
            
            String[] line = stdin.nextLine().split(" ");
            int[] colors = new int[line.length];
            for (int i = 0; i < line.length; i++){
                colors[i] = Integer.parseInt(line[i]);
            }

            boolean easiestWrong;
            boolean hardestWrong;

            if (colors[0] == X){
                easiestWrong = true;
            }else{
                easiestWrong = false;
            }

            if (colors[N-1] == Y){
                hardestWrong = true;
            }else{
                hardestWrong = false;
            }
            // System.out.println("X: " + X + ", Y: " + Y);
            //
            // System.out.println("easy: " + easiestWrong + ", hard: " + hardestWrong);

            if (easiestWrong && hardestWrong){
                System.out.println("BOTH");
            }else if (easiestWrong){
                System.out.println("EASY");
            }else if (hardestWrong){
                System.out.println("HARD");
            }else{
                System.out.println("OKAY");
            }

        }
    }

    public static void main(String[] args){
        new balloons().doit();
    }
}
