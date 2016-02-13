import java.util.*;
import java.io.*;

public class relatives{

    static List<Person> people = new ArrayList<Person>(); 
    static Hashtable<String,Person> hashTable = new Hashtable<String, Person>();
    static String personWithMinConnection;
    static int maxPeople;

    public static void main(String[] args)throws Exception{
        Scanner stdin = new Scanner(new File("input1.txt"));
        int networkNumber = 1;
        while (true){
            StringTokenizer strTok = new StringTokenizer(stdin.nextLine());
            int numPeople = Integer.parseInt(strTok.nextToken());
            int numRelationships = Integer.parseInt(strTok.nextToken());
            maxPeople = numPeople;
            if (numPeople == 0 && numRelationships == 0) 
                break;
            try{
                strTok = new StringTokenizer(stdin.nextLine());
                for (int i = 0; i < numRelationships; i++){
                    String p1 = strTok.nextToken(); 
                    String p2 = strTok.nextToken(); 
                    Person person1;
                    Person person2;

                    if (hashTable.containsKey(p1)){
                        person1 = hashTable.get(p1);
                    }else{
                        person1 = new Person(p1);
                        hashTable.put(p1, person1);
                        people.add(person1);
                    }

                    if (hashTable.containsKey(p2)){
                        person2 = hashTable.get(p2);
                    }else{
                        person2 = new Person(p2);
                        hashTable.put(p2, person2);
                        people.add(person2);
                    }
//                    System.out.println(person1.name + " is " + person2.name + " friend");
                    person1.addRelative(person2);
                    person2.addRelative(person1);
                }
                personWithMinConnection = people.get(0).name;

  //              System.out.println("KIYOSHI RELATIVE SIZE: " + hashTable.get("Kiyoshi").relatives.size());

                for (Person p : people){
                    if (p.relatives.size() > hashTable.get(personWithMinConnection).relatives.size())
                        personWithMinConnection = p.name;
                }

                for (Person p : hashTable.get("Kiyoshi").relatives){
//                    System.out.println("rel to kiyoshi: " + p.name);
                } 
                int separation = maxSeparation();
                if (separation == -1){
                    System.out.println("Network " + networkNumber++ + ": DISCONNECTED");
                }else{
                    System.out.println("Network " + networkNumber++ + ": " + maxSeparation());
                }
                System.out.println();
                hashTable.clear();
            }catch(Exception e){
            }
        }
    }

    private static int maxSeparation(){
        Set<String> uniqueSet = new HashSet<String>();
        Person root = hashTable.get(personWithMinConnection);
        Queue<Person> queue = new LinkedList<Person>();
        queue.add(root);
        int depth = 1;
        int timeToNextDepthIncrease = 1;
        int pendingDepthIncrease = 0;
        while (queue.size() > 0){
            Person currPerson = queue.remove();
            if (!uniqueSet.contains(currPerson.name)){
//                System.out.println("ADDING TO UNIQUE SET: " + currPerson.name);
                uniqueSet.add(currPerson.name); 
            }
            if (uniqueSet.size() == maxPeople){
                return depth;
            }
            pendingDepthIncrease += currPerson.relatives.size();

            if (--timeToNextDepthIncrease == 0){
                depth++; 
                timeToNextDepthIncrease = pendingDepthIncrease;
                pendingDepthIncrease = 0;
            }
            for (Person relative : currPerson.relatives){
                if (!uniqueSet.contains(relative.name)){
                    queue.add(relative);
                }
            }
        }
//        System.out.println("SET SIZE: " + uniqueSet.size());
        if (uniqueSet.size() < maxPeople) 
            return -1;
        else
            return depth;
    }
}



class Person{
    String name;
    List<Person> relatives = new ArrayList<Person>();

    Person(String name){
        this.name = name;
    }

    public void addRelative(Person person){
        relatives.add(person);
    }
}

