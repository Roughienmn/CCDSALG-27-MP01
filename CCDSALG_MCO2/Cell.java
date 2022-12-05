// Cell contains the record of the substring:
public class Cell {

    // Substring:
    private String name;
    // Number of occurrences:
    private int count;

    // Constructor:
    public Cell(String name){
        this.name = name;
        this.count = 1;
    }

    // Getters and Setters:
    public void addCount(){
        this.count++;
    }
    public String getName(){
        return this.name;
    }
    public int getCount(){
        return this.count;
    }
}
