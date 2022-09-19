
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author pdawkins
 */
public class Main {
    static int score = 0;

    static ArrayList<String> wordList1 = new ArrayList<String>(
            Arrays.asList("hot", "summer", "hard", "dry", "heavy","light", "weak",
                    "male", "sad", "win", "small", "ignore", "buy", "succeed",
                    "reject", "prevent", "exclude"));

    static ArrayList<String> wordList2 = new ArrayList<String>(
            Arrays.asList("cold", "winter", "soft", "wet", "light", "darkness",
                    "strong", "female", "happy", "lose", "big", "pay attention",
                    "sell", "fail", "accept", "allow", "include"));
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random rn = new Random();

        System.out.print("What is your name? ");
        String name = input.next();

        // Extension class name to write to file later
        String group ;
        do{
            System.out.print("What class are you in [bee, bear, duck] ? ");
            group = input.next();

        }while ( !(group.equals("bear") || group.equals("bee") || group.equals("duck") ) );

        System.out.println("Welcome " + name + " of Class "+ group);

        int first, second;
        String answer;

        // Task 2 stop repeats
        ArrayList<Integer> firstUsed = new ArrayList<Integer>();


        for (int i = 0; i < 3; i++) {
            first = rn.nextInt( wordList1.size()-1 );
            second = rn.nextInt(wordList1.size() - 1);


            // check that second hasn't been in the stem before
            while( firstUsed.contains(second) ){
                second = rn.nextInt(wordList1.size() - 1);
                System.out.println("Second "+ second );

            }

            makeQuestion(first, second);
            answer = input.next();

            // now check if correct
            if (answer.equals(wordList2.get(second))) {
                score++;
                System.out.println("Correct \n");
            } else{
                System.out.println("WRONG --> " + wordList2.get(second) + "\n");
            }

        }
        System.out.println(name + " your score is "+ score);
        writeToFile(name,group, score);

    }

    public static void makeQuestion(int pos1, int pos2){
        System.out.print(wordList1.get(pos1) + " is to " +
                wordList2.get(pos1) + " as " + wordList1.get(pos2) +
                " is to _______ ");
    }

    public static void writeToFile(String name, String group, int score){
        try(
                FileWriter classFile = new FileWriter(group+"Scores.txt", true);
                PrintWriter classWriter = new PrintWriter(classFile);
        )
        {
            // try to write to the file
            classWriter.println(name +","+ score);
        }

        catch(IOException e)
        {
            System.out.println("Couldn't write to file");
        }

        System.out.println("File written ..."  );
    }

}

