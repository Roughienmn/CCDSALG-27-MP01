// Node contains the record of the substring (for BST):
public class Node
{
    // Substring of the DNA sequence:
    private String substring;
    // Number of occurrences:
    private int occurrences;
    // Parent:
    private Node parent;
    // Left child:
    private Node leftChild;
    // Right child:
    private Node rightChild;

    // Constructor:
    public Node (String sequence){
        this.substring = sequence;
        this.parent = null;
        this.rightChild = null;
        this.leftChild = null;
        this.occurrences = 1;
    }

    // Getters and Setters:
    public String getSubstring() {
        return substring;
    }
    public int getOccurrences() {
        return occurrences;
    }
    public Node getLeftChild() {
        return leftChild;
    }
    public Node getRightChild() {
        return rightChild;
    }
    public void setOccurrences(int occurrences) {
        this.occurrences = occurrences;
    }
    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }
    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }
    public void setParent(Node parent) {
        this.parent = parent;
    }
}
