import java.util.*;
public class bullseye{

    public void doit(){
        Scanner stdin = new Scanner(System.in);
        int cases = Integer.parseInt(stdin.nextLine());

        for (int testCase = 1; testCase <= cases; testCase++){

            StringTokenizer strTok = new StringTokenizer(stdin.nextLine());
            int B = Integer.parseInt(strTok.nextToken());
            long N = Integer.parseInt(strTok.nextToken());

            ArrayList<Barber> barbers = new ArrayList<Barber>();
            strTok = new StringTokenizer(stdin.nextLine());
            for (int i = 0; i < B; i++){
                int Mk = Integer.parseInt(strTok.nextToken());
                barbers.add(new Barber(i, Mk));
            }

            Collections.sort(barbers);

            System.out.println(barbers.toString());
            

        }

    }

    public static void main(String[] args){

    }
}

class Barber implements Comparable<Barber>{ 
    int id;
    int Mk;
    Barber(int id, int Mk){
        this.id = id;
        this.Mk = Mk;
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

    public String toString(){
        return "id: " + id + ", Mk: " + Mk;
    }
}

