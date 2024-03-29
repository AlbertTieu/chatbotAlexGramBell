 import java.util.Random;

/**
 * A program to carry on conversations with a human user.
 * This version:
 *<ul><li>
 *         Uses advanced search for keywords 
 *</li><li>
 *         Will transform statements as well as react to keywords
 *</li></ul>
 * This version uses an array to hold the default responses.
 * @author Laurie White
 * @version April 2012
 */
public class AlexanderGrahamBell
{
    /**
     * Get a default greeting     
     * @return a greeting
     */    
    public String getGreeting()
    {
        return "Ask questions about me.";
    }
    
    /**
     * Gives a response to a user statement
     * 
     * @param statement
     *            the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement)
    {
        String response = "";
        if (statement.length() == 0)
        {
            response = "Say something, please.";
        }

        else if (findKeyword(statement, "famous") >= 0
                    || findKeyword(statement, "known") >= 0)
        {
            response = "I am known for my invention of the telephone.";
        }
        else if (findKeyword(statement,"age") >= 0)
        {
            response = "I died at 75.";
        }
        else if (findKeyword(statement, "married") >= 0 ||
                    findKeyword(statement, "spouse") >= 0 ||
                    findKeyword(statement, "wife") >= 0)
        {
            response = "I was married to Mabel Gardiner Hubbard.";
        }
        else if (findKeyword(statement, "children") >= 0)
        {
            response = "I had four children.";
        }

        else
        {   
            int psn = 0;
            if (psn >= 0
                    && findKeyword(statement, "name", psn) >= 0)
            {
                response = "My name is Alexander Graham Bell.";
            }
            
            else
            {
                //  Part of student solution
                // Look for a two word (I <something> you)
                // pattern
                psn = findKeyword(statement, "i", 0);

                if (psn >= 0
                        && findKeyword(statement, "you", psn) >= 0)
                {
                    response = transformIYouStatement(statement);
                }
                else
                {
                    psn = findKeyword(statement, "where", 0);
                    if (psn >= 0 && findKeyword(statement, "born", psn) >= 0
                            || psn >= 0 && findKeyword (statement, "from", psn) >= 0)
                    {
                        response = "I was born in Edinburgh, Scotland.";
                    } else if (psn >= 0 && findKeyword (statement, "live", psn) >= 0) {
                        response = "I used to live in the peninsula of Beinn Bhreagh.";
                    } else if (psn >= 0 && findKeyword (statement, "die", psn) >= 0) {
                        response = "I died in the peninsula of Beinn Bhreagh.";
                    }
                    else
                    {
                    psn = findKeyword(statement, "when", 0);
                    if (psn >= 0 && findKeyword(statement, "die", psn) >= 0)
                    {
                        response = "I died August 2, 1922.";
                    } else if (psn >= 0 && findKeyword (statement, "born", psn) >= 0) {
                        response = "I was born on March 3, 1847";
                    } else if (psn >= 0 && findKeyword (statement, "marry", psn) >= 0) {
                        response = "I married my wife on July 11, 1877.";
                    }
                    else
                    {
                    psn = findKeyword(statement, "who", 0);
                    if (psn >= 0 && findKeyword(statement, "you", psn) >= 0)
                    {
                        response = "I am Alexander Graham Bell.";
                    }
                    else {
                    psn = findKeyword(statement, "how", 0);
                    if (psn >= 0 && findKeyword(statement, "die", psn) >= 0)
                    {
                        response = "I died due to complications from diabetes.";
                    } else if (psn >= 0 && findKeyword(statement, "old", psn) >= 0) {
                        
                        response = "I died at 75.";
                    } else if (psn >= 0 && findKeyword(statement, "are you", psn) >= 0) {
                        response = "I'm doing well, thank you.";
                    }
                    else
                    {
                    psn = findKeyword(statement, "what", 0);
                    if (psn >= 0 && findKeyword(statement, "do", psn) >= 0)
                    {
                        response = "I made the telephone after my work with the deaf and human speech.";
                    } else if (psn >= 0 && findKeyword(statement, "are you", psn) >= 0) {
                        response = "I am the person who invented the telephone.";
                    } else if (psn >= 0 && findKeyword(statement, "names", psn) >= 0) {
                        response = "Their names were Elise, Marian, Edward, and Robert.";
                    }
                    else
                    {
                    psn = findKeyword(statement, "were", 0);
                    if (psn >= 0 && findKeyword(statement, "married", psn) >= 0)
                    {
                        response = "I was married to Mabel Gardiner Hubbard.";
                    } else if (psn >= 0 && findKeyword(statement, "?aDwads", psn) >= 0) {
                        response = "I am the person who invented the telephone.";
                    }
                    else
                    {
                        response = getRandomResponse() + " Also please check for typos.";
                    }
                    }
                    }
                    }
                    }
                    }
                    
                }
            }
        }
        return response;
    }
    
    /**
     * Take a statement with "I want to <something>." and transform it into 
     * "What would it mean to <something>?"
     * @param statement the user statement, assumed to contain "I want to"
     * @return the transformed statement
     */
    private String transformIWantToStatement(String statement)
    {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword (statement, "I want to", 0);
        String restOfStatement = statement.substring(psn + 9).trim();
        return "What would it mean to " + restOfStatement + "?";
    }

    
    /**
     * Take a statement with "I want <something>." and transform it into 
     * "Would you really be happy if you had <something>?"
     * @param statement the user statement, assumed to contain "I want"
     * @return the transformed statement
     */
    private String transformIWantStatement(String statement)
    {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword (statement, "I want", 0);
        String restOfStatement = statement.substring(psn + 6).trim();
        return "Would you really be happy if you had " + restOfStatement + "?";
    }
    
    /**
     * Take a statement with "you <something> me" and transform it into 
     * "What makes you think that I <something> you?"
     * @param statement the user statement, assumed to contain "you" followed by "me"
     * @return the transformed statement
     */
    private String transformYouMeStatement(String statement)
    {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        
        int psnOfYou = findKeyword (statement, "you", 0);
        int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
        
        String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
        return "What makes you think that I " + restOfStatement + " you?";
    }
    
    /**
     * Take a statement with "I <something> you" and transform it into 
     * "Why do you <something> me?"
     * @param statement the user statement, assumed to contain "I" followed by "you"
     * @return the transformed statement
     */
    private String transformIYouStatement(String statement)
    {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        
        int psnOfI = findKeyword (statement, "I", 0);
        int psnOfYou = findKeyword (statement, "you", psnOfI);
        
        String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
        return "Why do you " + restOfStatement + " me?";
    }
    

    
    
    /**
     * Search for one word in phrase.  The search is not case sensitive.
     * This method will check that the given goal is not a substring of a longer string
     * (so, for example, "I know" does not contain "no").  
     * @param statement the string to search
     * @param goal the string to search for
     * @param startPos the character of the string to begin the search at
     * @return the index of the first occurrence of goal in statement or -1 if it's not found
     */
    private int findKeyword(String statement, String goal, int startPos)
    {
        String phrase = statement.trim();
        //  The only change to incorporate the startPos is in the line below
        int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);
        
        //  Refinement--make sure the goal isn't part of a word 
        while (psn >= 0) 
        {
            //  Find the string of length 1 before and after the word
            String before = " ", after = " "; 
            if (psn > 0)
            {
                before = phrase.substring (psn - 1, psn).toLowerCase();
            }
            if (psn + goal.length() < phrase.length())
            {
                after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
            }
            
            //  If before and after aren't letters, we've found the word
            if (((before.compareTo ("a") < 0 ) || (before.compareTo("z") > 0))  //  before is not a letter
                    && ((after.compareTo ("a") < 0 ) || (after.compareTo("z") > 0)))
            {
                return psn;
            }
            
            //  The last position didn't work, so let's find the next, if there is one.
            psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
            
        }
        
        return -1;
    }
    
    /**
     * Search for one word in phrase.  The search is not case sensitive.
     * This method will check that the given goal is not a substring of a longer string
     * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.  
     * @param statement the string to search
     * @param goal the string to search for
     * @return the index of the first occurrence of goal in statement or -1 if it's not found
     */
    private int findKeyword(String statement, String goal)
    {
        return findKeyword (statement, goal, 0);
    }
    


    /**
     * Pick a default response to use if nothing else fits.
     * @return a non-committal string
     */
    private String getRandomResponse ()
    {
        Random r = new Random ();
        return randomResponses [r.nextInt(randomResponses.length)];
    }
    
    private String [] randomResponses = {
            "Please ask me something I can answer.",
            "I don't understand.",
            "Ask a question, please."
    };
    
}
