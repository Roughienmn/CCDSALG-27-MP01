import java.util.ArrayList;

public class BST {

    private Node root;
    private ArrayList<Node> substrings = new ArrayList<Node>();

    public ArrayList<Node> getSubstrings() {
        return substrings;
    }

    public BST(){
        this.root = null;
    }

    public BST(int k, String sDNA){
        this.create();
        for (int i = 0; i + k <= sDNA.length(); i++){
            this.insert(sDNA.substring(i,i+k));
        }
    }

    public void create(){
        this.root = null;
    }

    public boolean search(String substring){
        Node sKey = this.root;

        while (sKey != null) {
            if (sKey.getsDNA().equals(substring)) {
                int count = sKey.getOccurrences();
                sKey.setOccurrences(count+1);
                return true;
            } else if (sKey.getsDNA().compareTo(substring) > 0) {
                sKey = sKey.getlNode();
            } else {
                sKey = sKey.getrNode();
            }
        }

        return false;
    }


    public void insert(String substring){
        Node iKey = new Node(substring);
        Node compare = this.root;
        Node hold = null;

        if (this.root == null){
            this.root = iKey;
            this.substrings.add(iKey);
        } else {
            /*if (search(iKey.getsDNA())){
                int count = search(iKey.getsDNA());
                iKey.setOccurrences(count+1);
            } else if (!search(iKey.getsDNA())) {
                while (compare != null){
                    hold = compare;
                    if (compare.getsDNA().compareTo(substring) > 0){
                        compare = compare.getlNode();
                    } else if (compare.getsDNA().compareTo(substring) < 0) {
                        compare = compare.getrNode();
                    }
                }

                if (hold.getsDNA().compareTo(substring) > 0){
                    hold.setlNode(iKey);
                } else if (hold.getsDNA().compareTo(substring) < 0) {
                    hold.setrNode(iKey);
                }

                this.substrings.add(iKey);
            }*/

            if (!search(iKey.getsDNA())) {
                while (compare != null){
                    hold = compare;
                    if (compare.getsDNA().compareTo(substring) > 0){
                        compare = compare.getlNode();
                    } else if (compare.getsDNA().compareTo(substring) < 0) {
                        compare = compare.getrNode();
                    }
                }

                if (hold.getsDNA().compareTo(substring) > 0){
                    hold.setlNode(iKey);
                } else if (hold.getsDNA().compareTo(substring) < 0) {
                    hold.setrNode(iKey);
                }

                this.substrings.add(iKey);
            }
        }
    }
}