 

import java.util.Scanner;

/**
 * A simple class to run the Magpie class.
 * @author Laurie White
 * @version April 2012
 */
public class AlexanderGrahamBellRunner
{

    /**
     * Create a Magpie, give it user input, and print its replies.
     */
    public static void main(String[] args)
  
{
        AlexanderGrahamBell alexBell = new AlexanderGrahamBell();
        
        System.out.println (alexBell.getGreeting());
        Scanner in = new Scanner (System.in);
        String statement = in.nextLine();
        
        while (!statement.toLowerCase().trim().equals("bye"))

        {
            System.out.println (alexBell.getResponse(statement));
            statement = in.nextLine();
            
        }
        if (statement.toLowerCase().trim().equals("bye")) {
                System.out.println ("Goodbye.");
            }   
    }

}
