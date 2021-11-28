package Model;

public abstract class Part {
    private Integer id, stock, min, max;
    private String name;
    private Double price;

    
    public Part (int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setName (String name) {
        this.name = name;
    }
    
    public void setPrice (double price) {
        this.price = price;
    }
    
    public void setStock (int stock) {
        this.stock = stock;
    }
    
    public void setMin (int min) {
        this.min = min;
    }
    
    public void setMax (int max) {
        this.max = max;
    }
    
    public void setPrice (int max) {
        this.price = (double)max;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public double getPrice(){
        return this.price;
    }
    
    public int getStock(){
        return this.stock;
    }
    
    public int getMin(){
        return this.min;
    }
    
    public int getMax() {
        return this.max;
    }
    
}
