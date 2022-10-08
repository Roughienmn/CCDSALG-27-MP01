import java.util.Scanner;

public class MCO1 {

    // Main function:
    public static void main(String[] args)
    {
        // Inputting the string:
        String sString = getInput();

        // Declaring an array to store the suffixes:
        String[] arrSuffixes = new String[sString.length()];

        // Storing the suffixes in an array:
        getSuffix(sString, arrSuffixes);

        // Deciding sorting algorithm to use:
        String sSort = getSorting();

        // Calling on the algorithm chosen:
        if(sSort.equals("1"))
            insertionSort(arrSuffixes); // Insertion Sort
        else
            mergeSort(arrSuffixes, 0, arrSuffixes.length-1); // Merge Sort

        // Displaying the sorted suffix array:
        for(int i = 0; i < arrSuffixes.length; i++)
            System.out.println(arrSuffixes.length-arrSuffixes[i].length() + ": " + arrSuffixes[i]);
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
            for(int i = 0; i < sInput.length(); i++)
                if(sInput.charAt(i) != 'a' && sInput.charAt(i) != 't' && sInput.charAt(i) != 'c' && sInput.charAt(i) != 'g')
                {
                    bValid = false;
                    i = sInput.length();
                }

            // Informs the user if their string has other characters:
            if(!bValid)
                System.out.println("Invalid input");

        } while (!bValid); // Prompts user to input again if invalid

        return sInput;
    }

    // Function that creates an array containing all the suffixes:
    static void getSuffix(String sString, String[] arrSuffixes)
    {
        // Iterates a substring to be stored in the array
        for(int i = 0; i < arrSuffixes.length; i++)
            arrSuffixes[i] = sString.substring(i, arrSuffixes.length);
    }

    // Function that prompts the user to decide a sorting algorithm:
    static String getSorting()
    {
        // Displaying the algorithm options:
        System.out.println("Sorting Algorithms:");
        System.out.println("(1) Insertion Sort");
        System.out.println("(2) Merge Sort");

        String sSort; // Contains the number of corresponding sorting algorithm
        do
        {
            // Prompting the user to input:
            System.out.print("Type your desired sorting type: ");
            Scanner input = new Scanner(System.in);
            sSort = input.nextLine();

        } while (!sSort.equals("1") && !sSort.equals("2")); // Prompts user to input again if invalid

        return sSort;
    }

    // Function that sorts the array with Insertion Sort:
    static void insertionSort(String[] arrSuffixes)
    {
        // From the 2nd element to the last element of the array:
        for(int i = 1; i < arrSuffixes.length; i++)
        {
            String sKey = arrSuffixes[i]; // Holds each element of the suffix to compare
            int j = i-1; // Contains the value of the first element for every iteration

            // Shifts the hold element to the left if less than the other elements compared to:
            while(j > -1 && (arrSuffixes[j].compareTo(sKey) > 0))
            {
                arrSuffixes[j+1] = arrSuffixes[j];
                j--; // Moves index to the left
            }

            arrSuffixes[j+1] = sKey; // New location for the hold element
        }
    }

    // Function that sorts the array with Merge Sort:
    static void mergeSort(String[] A, int p, int r)
    {
        // If there is at least 1 suffix:
        if (p < r)
        {
            // Divide:
            int q = (p + r) / 2; // Contains the midpoint of the array
            // Conquer:
            mergeSort(A, p, q); // Sorts the first half of the array
            mergeSort(A, q + 1, r); // Sorts the second half of the array
            // Combine:
            merge(A, p, q, r);
        }
    }

    // Function that combines the halves of the array:
    static  void merge(String[] arrSuffixes, int p, int q, int r)
    {
        // Length of each half of the unsorted array:
        int n1 = q - p + 1;
        int n2 = r - q;

        // Declaring arrays to store each half of the unsorted array:
        String[] L = new String[n1]; // Left-half
        String[] R= new String[n2]; // Right-half

        // Storing each half of the unsorted array:
        for (int i = 0; i < n1; i++)
            L[i] = arrSuffixes[p + i];
        for (int j = 0; j < n2; j++)
            R[j] = arrSuffixes[q + 1 + j];

        // Declaring counter variables:
        int i = 0, j = 0, k = p;

        // For every index of both the left and right halves:
        while(i < n1 && j < n2)
        {
            // If the left-half element should come after the right-half element in the alphabet:
            if (L[i].compareTo(R[j]) <= 0)
            {
                arrSuffixes[k] = L[i]; // Sorted array will have the left-half element
                i++; // Next index of the left-half element
            }
            // Otherwise:
            else
            {
                arrSuffixes[k] = R[j]; // Sorted array will have the right-half element
                j++; // Next index of the right-half element
            }

            k++; // Goes to the next index of the sorted array
        }

        // Adds the remaining elements to the array:
        while(i < n1) // If there is an extra element in the left-half
        {
            arrSuffixes[k] = L[i];
            i++;
            k++;
        }
        while(j < n2) // If there is an extra element in the right half
        {
            arrSuffixes[k] = R[j];
            j++;
            k++;
        }
    }
}
