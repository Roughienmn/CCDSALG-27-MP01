import java.util.ArrayList;
import java.util.Scanner;

public class KMer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter DNA sequence S:");
        String sequence = sc.nextLine();

        System.out.println("Enter length k:");
        int k = sc.nextInt();

        BST distribution = new BST (k, sequence);
        ArrayList<Node> substrings = distribution.getSubstrings();

        System.out.println("\t" + "-----------------------");
        System.out.println("\t" + k + "-mer  | No. of Occurrences");

        System.out.println("\t" + "-----------------------");

            // For each Bucket in Bucket List
        for (int i = 0; i < substrings.size(); i++) {
            System.out.println("\t| " + substrings.get(i).getsDNA() + " | " + substrings.get(i).getOccurrences()  +  "|");
        }

            System.out.println("\t" + "-----------------------");
    }
}
