import java.util.*;
public class haircut{

    public void doit(){
        Scanner stdin = new Scanner(System.in);
        int cases = Integer.parseInt(stdin.nextLine());

        for (int testCase = 1; testCase <= cases; testCase++){

            StringTokenizer strTok = new StringTokenizer(stdin.nextLine());
            int B = Integer.parseInt(strTok.nextToken());
            int N = Integer.parseInt(strTok.nextToken());

            ArrayList<Barber> barbers = new ArrayList<Barber>();
            strTok = new StringTokenizer(stdin.nextLine());
            for (int i = 0; i < B; i++){
                int Mk = Integer.parseInt(strTok.nextToken());
                barbers.add(new Barber(i, Mk));
            }

            Collections.sort(barbers);

            Stack<Barber> queue = new Stack<Barber>();
            
            int minTime = barbers.get(0).Mk;

            Barber ourGuy = null;
            while (N > 0){
                for (Barber b : barbers){
                System.out.println(barbers.toString());
                    b.timeLeft -= 1;
                    if (b.timeLeft == 0){
                        b.resetTime();
                        N--;
                        if (N == 0){
                            ourGuy = b;
                        }
                    }
                }
            }

            System.out.println(ourGuy.id);
            
        }

    }
    public static void main(String[] args){
        new haircut().doit();
    }
}

            // int index = 0;
            // while (queue[N-1] == null){
            //     for (int i = 0; i < barbers.size(); i++){
            //         if (index < N)
            //             queue[index++] = barbers.get(i);
            //     }
            // }

class Barber implements Comparable<Barber>{ 
    int id;
    int Mk;
    int timeLeft;

    Barber(int id, int Mk){
        this.id = id;
        this.Mk = Mk;
        this.timeLeft = Mk;
    }

    public int compareTo(Barber b){
        if (this.Mk < b.Mk){
           return -1; 
        }else if (this.Mk == b.Mk){
            return 0;
        }else{
            return 1;
        }
    }

    public void resetTime(){
        this.timeLeft = Mk;
    }

    public String toString(){
        return "id: " + id + ", Mk: " + Mk;
    }
}

