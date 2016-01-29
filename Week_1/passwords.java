import java.util.Scanner;
import java.util.ArrayList;
public class passwords{


    private static ArrayList<String> passwords = new ArrayList<String>();
    private static ArrayList<String> possibleChars = new ArrayList<String>();
    private static int rank = 0;

    private static void permutePasswords(int passLength, String password, int listIndex, int wantedRank){
        if (password.length() == passLength){
//            System.out.println("Password: " + password);
            passwords.add(password);
            rank++;
        } 
        else{
            String letters = possibleChars.get(listIndex);
            for (int i = 0; i < letters.length(); i++){
                if (rank == wantedRank+1) break; 
                password += letters.charAt(i);
                permutePasswords(passLength, password, listIndex+1, wantedRank);
                password = password.substring(0,password.length()-1);
            }
        }
    }


    public static void main(String[] args){


        Scanner stdin = new Scanner(System.in);
        int numCases = Integer.parseInt(stdin.nextLine());
        for (int currentCase = 0; currentCase < numCases; currentCase++){
            int passLength = Integer.parseInt(stdin.nextLine());
            
            for (int i = 0; i < passLength; i++){
                String chars = stdin.nextLine(); 
                possibleChars.add(chars);
            }
            int wantedRank = Integer.parseInt(stdin.nextLine()) - 1;

            permutePasswords(passLength, "", 0, wantedRank);
            System.out.println(passwords.get(wantedRank));
            passwords.clear();
            possibleChars.clear();
            rank = 0;
        }
    }
}

