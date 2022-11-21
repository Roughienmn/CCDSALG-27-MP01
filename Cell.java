public class Cell {
    private String name;
    private int count;

    public Cell(String name){
        this.name = name;
        this.count = 1;
    }

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
