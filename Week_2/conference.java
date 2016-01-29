import java.util.Scanner;
import java.util.ArrayList;
public class conference{

    private static int todaysCost = 536870912;

    private static ArrayList<Conf> confs = new ArrayList<Conf>();

    public static void main(String[] args){
        Scanner stdin = new Scanner(System.in);
        int cases = Integer.parseInt(stdin.nextLine());
        
        for (int c = 0; c < cases; c++){
            int conferences = Integer.parseInt(stdin.nextLine());

            for (int d = 0; d < conferences; d++){
                confs.add(new Conf(stdin.nextInt(), stdin.nextInt()));
            }

            for (int day = 0; day < 30; day++){
                
                todaysCost /= 2;
            }
        }
    }
}
class Conf{
    int startDay;
    int length;

    Conf(int startDay, int length){
        this.startDay = startDay;
        this.length = length;
    }

    public String toString(){
        return "Start: " + startDay + ", Length: " + length;
    }
}
