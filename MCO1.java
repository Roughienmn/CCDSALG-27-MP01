import java.util.Scanner;

public class MCO1 {

    // Main function:
    public static void main(String[] args)
    {
        // Inputting the string:
        String sString = getInput();

        // Declaring the unsorted suffix array:
        String[] arrSuffixes = new String[sString.length()];

        // Storing the suffixes in the array:
        getSuffix(sString, arrSuffixes);

        // Sorting the suffixes in the array:

    }

    // Function that prompts the user to input the string:
    static String getInput()
    {
        String sInput; // Contains the inputted string
        boolean bValid; // Checks if the input is valid
        do
        {
            // Prompting the user to input:
            bValid = true;
            System.out.print("Input a string with only {a,c,g,t}: ");
            Scanner input = new Scanner(System.in);
            sInput = input.nextLine();

            // Error checking to ensure all elements of the string are from {a,c,g,t}:
            for(int nCounter = 0; nCounter < sInput.length(); nCounter++)
                if(sInput.charAt(nCounter) != 'a' && sInput.charAt(nCounter) != 't' && sInput.charAt(nCounter) != 'c' && sInput.charAt(nCounter) != 'g')
                    bValid = false;

            // Informs the user if their string has other characters:
            if(bValid == false)
                System.out.println("Invalid input");

        } while (bValid == false); // Prompts user to input again if invalid

        return sInput;
    }

    // Function that creates an array containing all the suffixes:
    static void getSuffix(String sString, String[] arrSuffixes)
    {
        // Iterates a substring to be stored in the array
        for(int nCounter = 0; nCounter < arrSuffixes.length; nCounter++)
            arrSuffixes[nCounter] = sString.substring(nCounter, arrSuffixes.length);
    }
}
