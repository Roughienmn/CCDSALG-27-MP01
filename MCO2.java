import java.util.Scanner;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;

public class MCO2 {
    //Murmurhash
    public static int hashFunction1(String substring){
        HashFunction hf = Hashing.murmur3_128();
        return hf.hashString(substring, StandardCharsets.UTF_8).hashCode();
    }

    public static int hashFunction2(String substring){
        return Xxhash.hash32(substring.getBytes(), 0, substring.length(), 0);
    }
    /* 
     * Notes:
     * Hash table class already exists in java;
     * Number of possible permutation of length k from (a,c,g,t) = 4^k
     */
    public static void main(String[] args){
        //get inputs
        Scanner input = new Scanner(System.in);

        //get sequence
        System.out.print("Input DNA Sequence: ");
        String sequence = input.nextLine();
        
        //get substring length
        System.out.print("Input k-mer length: ");
        int k = input.nextInt(); 
        input.close();

        //declaring needed variables
        int n = sequence.length(); //length of sequence
        int subcount = n - k + 1; //number of substrings of length k from string of length n

        Cell hashtable[] = new Cell[n]; //declare hash table

        //add substrings to hash table, including counts
        for(int i = 0; i < subcount; i++){
            //get substring
            String subString = sequence.substring(i, i+k);
            boolean found = false;
            int offset = 0;
            
            //repeat hashfunction until hash table slot is free or key is found
            while(!found){

                //get index of substring based on hashing function
                int index;
                index = (hashFunction2(subString) + offset) % n; //insert hashing function here
                if(index < 0){ //if index is negative, set index as n - |index|
                    index = n+index;
                }

                //if substring is not in hash table yet, add it to appropriate index and increment count
                if(hashtable[index] == null) {
                    hashtable[index] = new Cell(subString);
                    found = true;
                }

                //if exists and is the same key, increment count (existing in hash table implies that collision occurs)
                else if(hashtable[index].getName().equals(subString)){
                    hashtable[index].addCount();
                    found = true;
                }

                //if neither, update case for hashing function (linear probing)
                else{
                    offset++;
                }
            }
        }

        //display k-mer distribution
        System.out.println("Displaying " + k + "-mer distribution");
        System.out.println(k + "-mer     (count)");
        for(Cell c : hashtable){
            if (c != null){
                System.out.println(c.getName() + " (" + c.getCount() + ")");
            }
        }
    }
}
