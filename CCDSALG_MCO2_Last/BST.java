import java.util.ArrayList;

// BST contains the record of the binary search tree (with all the nodes):
public class BST
{
    // Root of the BST:
    private Node root;
    // List of all the nodes in the BST
    private ArrayList<Node> substrings = new ArrayList<>();

    // Default Constructor:
    public BST()
    {
        this.root = null;
    }

    // Constructor:
    public BST(String sequence, int k)
    {
        // Declaration of variables (for added readability):
        int m = sequence.length();
        int n = m - k + 1;
        // Creates the root:
        this.create();
        // Inserts all the substrings in the BST:
        for (int i = 0; i < n; i++)
            this.insert(sequence.substring(i,i+k));
    }

    // Method that creates the root of an empty BST:
    public void create()
    {
        this.root = null;
    }

    // Method that looks for a node in the BST:
    public boolean search(String substring){
        // Sets the root as the initial search key:
        Node searchKey = this.root;

        // Loop ends if either substring is found or reaches a leaf node:
        while (searchKey != null)
        {
            // If substring is found to have same value as an existing node:
            if (searchKey.getSubstring().equals(substring))
            {
                int count = searchKey.getOccurrences();
                searchKey.setOccurrences(count+1);
                return true;
            }
            // If substring has a value less than the parent:
            else if (searchKey.getSubstring().compareTo(substring) > 0)
                searchKey = searchKey.getLeftChild();
            // If substring has a value greater than the parent:
            else
                searchKey = searchKey.getRightChild();
        }

        // If not found:
        return false;
    }

    // Method that adds a node in the BST:
    public void insert(String substring){
        // Node to be added:
        Node newNode = new Node(substring);
        // Node to be compared to (for traversal):
        Node traverseNode = this.root;
        // Node to identify the position of the new node (possible parent):
        Node parentNode = null;

        // If the BST is empty, newly added node becomes the root:
        if (this.root == null)
        {
            this.root = newNode;
            this.substrings.add(newNode);
        }
        // Otherwise:
        else
        {
            // If the value of the substring is not in the BST:
            if (!search(newNode.getSubstring()))
            {
                // Pre-order traversal:
                while (traverseNode != null)
                {
                    parentNode = traverseNode;

                    if (traverseNode.getSubstring().compareTo(substring) > 0)
                        traverseNode = traverseNode.getLeftChild();
                    else if (traverseNode.getSubstring().compareTo(substring) < 0)
                        traverseNode = traverseNode.getRightChild();
                }

                // Once it reaches a leaf node:
                if (parentNode.getSubstring().compareTo(substring) > 0)
                    parentNode.setLeftChild(newNode);
                else if (parentNode.getSubstring().compareTo(substring) < 0)
                    parentNode.setRightChild(newNode);

                // New node given a parent:
                newNode.setParent(parentNode);
                // Added to the list:
                this.substrings.add(newNode);
            }
        }
    }

    // Getter:
    public ArrayList<Node> getSubstrings()
    {
        return substrings;
    }
}
