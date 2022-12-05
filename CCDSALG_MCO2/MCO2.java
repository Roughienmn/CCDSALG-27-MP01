/**
 *  CCDSALG Group 2
 *  Gamboa, Rafael
 *  Montenegro, Jaycee
 *  Ureta, Therese
 *  Yu, Marco
 *  ----------------------------------------------
 *  Notes:
 *  (1) Hash table class already exists in Java;
 *  (2) Number of possible permutation of length k from (a,c,g,t) = 4^k
 */

import java.util.Scanner;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;

public class MCO2 {

    // Main function:
    public static void main(String[] args)
    {
        // Inputting the string (DNA sequence):
        Scanner input = new Scanner(System.in);
        System.out.println("-----------------------------");
        System.out.print("Input DNA Sequence: ");
        String sequence = input.nextLine();

        // Inputting the k-mer length (length of the substring):
        System.out.print("Input k-mer length: ");
        int k = input.nextInt();

        // Deciding on the algorithm:
        int algorithm = getAlgorithm();
        if(algorithm == 1 || algorithm == 2)
            hashFunction(algorithm, sequence, k);
        else if(algorithm == 3)
            System.out.println("Binary Search Tree");

    }

    // Function that prompts the user to decide the algorithm to be used:
    public static int getAlgorithm()
    {
        // Prompting the user to input the algorithm chosen:
        Scanner input = new Scanner(System.in);
        System.out.println("-----------------------------");
        System.out.println("Algorithms:");
        System.out.println("(1) xxHash");
        System.out.println("(2) Murmurhash");
        System.out.println("(3) Binary Search Tree");
        System.out.print("Which algorithm: ");
        int nAlgorithm = input.nextInt();
        System.out.println("-----------------------------");
        input.close();

        // Returning the input
        return nAlgorithm;
    }

    // xxHash:
    public static int xxHash(String substring)
    {
        return Xxhash.hash32(substring.getBytes(), 0, substring.length(), 0);
    }

    // Murmurhash:
    public static int murmurhash(String substring)
    {
        HashFunction hf = Hashing.murmur3_128();
        return hf.hashString(substring, StandardCharsets.UTF_8).hashCode();
    }

    // Function that creates a hash table:
    public static void hashFunction(int algorithm, String sequence, int k)
    {
        // Declaring variables:
        long start = System.nanoTime();
        int m = sequence.length(); // Length of the sequence
        int n = m - k + 1; // Number of substrings of length k from string of length m

        // Declaring hash table:
        Cell[] hashtable = new Cell[m];

        // Adding the substrings in the hash table (including the count):
        for(int i = 0; i < n; i++)
        {
            // Getting the substring:
            String substring = sequence.substring(i, i+k);
            boolean found = false;
            int offset = 0;

            // Repeating hashing function until hash table slot is free or if key is found:
            while(!found){

                // Declaring index of string based on the hashing function:
                int index;

                // If xxHash:
                if(algorithm == 1)
                    index = (xxHash(substring) + offset) % m;
                // Else Murmurhash:
                else
                    index= (murmurhash(substring) + offset) % m;

                // If index is negative, set index as m - |index|:
                if(index < 0)
                    index = m + index;

                // If substring is not in hash table, add to appropriate index and increment count:
                if(hashtable[index] == null)
                {
                    hashtable[index] = new Cell(substring);
                    found = true;
                }
                // If it exists and is the same key, increment count (implying collision):
                else if(hashtable[index].getName().equals(substring))
                {
                    hashtable[index].addCount();
                    found = true;
                }
                // If neither, update case for hashing function (linear probing):
                else
                    offset++;
            }
        }

        // Displaying k-mer distribution:
        System.out.println("Displaying " + k + "-mer distribution");
        System.out.println(k + "-mer (count)");
        for(Cell c : hashtable)
            if (c != null)
                System.out.println(c.getName() + " (" + c.getCount() + ")");

        // End of timer:
        long end = System.nanoTime();
        System.out.println("-----------------------------");
        System.out.print("Location: " + (end - start));
    }
}
