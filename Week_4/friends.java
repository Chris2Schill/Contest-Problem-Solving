import java.util.*;
import java.io.*;
public class friends{

    static List<User> users = new ArrayList<User>();
    static Hashtable<String,User> usersTable = new Hashtable<String,User>();

    public static void main(String[] args) throws Exception{
        Scanner stdin = new Scanner(System.in);
        int numCases = Integer.parseInt(stdin.nextLine());
        for (int caseNum = 1; caseNum <= numCases; caseNum++){
            int numUsers = Integer.parseInt(stdin.nextLine());

            String[] names = stdin.nextLine().split(" ");
            for (int i = 0; i < names.length; i++){
                User newUser = new User(names[i]);
                usersTable.put(names[i], newUser);
                users.add(newUser);
                
            }

            int connections = Integer.parseInt(stdin.nextLine());
            for (int i = 0; i < connections; i++){
                StringTokenizer strTok = new StringTokenizer(stdin.nextLine());
                User x = usersTable.get(strTok.nextToken());
                User y = usersTable.get(strTok.nextToken());
                x.addFriend(y);
                y.addFriend(x);
            }

            int numRivals = Integer.parseInt(stdin.nextLine());
                System.out.println("Social Network " + caseNum + ":");
            for (int i = 0; i < numRivals; i++){
                String rivalName = stdin.nextLine();
                User rival = usersTable.get(rivalName);
                User me = usersTable.get("You");
                int myCoolness = getCoolness(me);
                int rivalCoolness = rival == null ? 0 : getCoolness(rival);
                int difference = myCoolness-rivalCoolness;
                System.out.print("   " + rivalName + ": ");
                System.out.println("Difference of " + difference + " point(s).");
            }
            System.out.println();
            users.clear();
            usersTable.clear();
        }

    }

    public static int getCoolness(User user){
        return user.friends.size() + sizeExtendedNetwork(user);
    }

    public static int sizeExtendedNetwork(User user){
        Set<String> encounteredUsers = new HashSet<String>();
        Stack<User> stack = new Stack<User>();
        stack.push(user);
        while (stack.size() > 0){
            User currUser = stack.pop();
            encounteredUsers.add(user.name);
            for (User friend : currUser.friends){
                if (!encounteredUsers.contains(friend.name)){
                    stack.push(friend); 
                    encounteredUsers.add(friend.name);
                }
            }
        }
        return encounteredUsers.size()-1;
    }

}

class User{
    String name;
    List<User> friends;

    User(String name){
        this.name = name;
        friends = new ArrayList<User>();
    }

    public void addFriend(User user){
        friends.add(user);
    }
}
