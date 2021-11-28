package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    private final ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private Integer id, stock, min, max;
    private String name;
    private Double price;

    public Product(Integer id, String name, Double price, Integer stock, Integer min, Integer max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setName (String name) {
        this.name = name;
    }
    
    public void setPrice(Double price){
        this.price = price;
    }
    
    public void setStock(Integer stock){
        this.stock = stock;
    }
    
    public void setMin(Integer min) {
        this.min = min;
    }
    
    public void setMax(Integer max) {
        this.max = max;
    }
    
    public void setPrice(int max){
        this.price = (double)max;
    }
    
    public Integer getId(){
        return this.id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public Double getPrice(){
        return this.price;
    }
    
    public Integer getStock(){
        return this.stock;
    }
    
    public Integer getMin(){
        return this.min;
    }
    
    public Integer getMax(){
        return this.max;
    }
    
    public void addAssociatedPart (Part part){
        associatedParts.add(part);
    }
    
    public void deleteAssociatedPart(Part associatedPart){
        associatedParts.remove(associatedPart);
    }
    
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }
    
}
