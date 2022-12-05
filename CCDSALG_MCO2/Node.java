class Node {
    private String sDNA;
    private int occurrences;
    private Node lNode;

    private Node rNode;


    public Node (String sDNA){
        this.sDNA = sDNA;
        this.rNode = null;
        this.lNode = null;
        this.occurrences = 1;
    }

    public String getsDNA() {
        return sDNA;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(int occurrences) {
        this.occurrences = occurrences;
    }

    public Node getlNode() {
        return lNode;
    }

    public void setlNode(Node lNode) {
        this.lNode = lNode;
    }

    public Node getrNode() {
        return rNode;
    }

    public void setrNode(Node rNode) {
        this.rNode = rNode;
    }

}
