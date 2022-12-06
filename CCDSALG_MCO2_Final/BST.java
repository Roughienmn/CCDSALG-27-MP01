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
        // Creates the root:
        this.create();
        // Inserts all the substrings in the BST:
        for (int i = 0; i + k <= sequence.length(); i++)
            this.insert(sequence.substring(i,i+k));
    }

    // Method that creates the root of an empty BST:
    public void create()
    {
        this.root = null;
    }

    // Method that looks for a node in the BST:
    public boolean search(String substring){
        // Sets the root/parent as the search key:
        Node sKey = this.root;

        // Loop ends if either substring is found or reaches a leaf node:
        while (sKey != null)
        {
            // If substring is found:
            if (sKey.getSubstring().equals(substring))
            {
                int count = sKey.getOccurrences();
                sKey.setOccurrences(count+1);
                return true;
            }
            // If substring has a value less than the parent:
            else if (sKey.getSubstring().compareTo(substring) > 0)
                sKey = sKey.getLeftChild();
            // If substring has a value greater than the parent:
            else
                sKey = sKey.getRightChild();
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
        // Node to identify the position of the new node (parent):
        Node parentNode = null;

        // If the BST is empty, newly added node becomes the root:
        if (this.root == null)
        {
            this.root = newNode;
            this.substrings.add(newNode);
        }
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