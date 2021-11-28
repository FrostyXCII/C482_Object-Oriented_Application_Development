package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    public static void addPart (Part part){
        allParts.add(part);
    }
    
    public static void addProduct (Product product){
        allProducts.add(product);
    }
    
    public static Part lookupPart (int partID) {
        for (Part part : allParts) {
            if (part.getId() == partID) {
                return part;
            }
        }
        return null;
    }
    
    public static Product lookupProduct (int productID) {
        for (Product product : allProducts) {
            if (product.getId() == productID) {
                return product;
            }
        }
        return null;
    }
    
    public static ObservableList<Part> lookupPart (String partName) {
        ObservableList<Part> parts = FXCollections.observableArrayList();
        
        allParts.stream().filter((part) -> (partName.compareTo(part.getName()) == 0)).forEachOrdered((part) -> {
            parts.add(part);
        });
        return parts;
    }

    public static ObservableList<Product> lookupProduct (String productName) {
        ObservableList<Product> products = FXCollections.observableArrayList();
        
        allProducts.stream().filter((product) -> (productName.compareTo(product.getName()) == 0)).forEachOrdered((product) -> {
            products.add(product);
        });
        return products;
    }
  
    public static void updatePart (int i, Part associatedPart) {
        Part part = Inventory.lookupPart(i);

        Inventory.deletePart(part);
        Inventory.addPart(associatedPart);
    }
   
    public static void updateProduct (int i, Product associatedProduct) {
        Product product = Inventory.lookupProduct(i);

        Inventory.deleteProduct(product);
        Inventory.addProduct(associatedProduct);
    }
    
    public static void deletePart (Part part) {
        allParts.remove(part);
    }
    
    public static void deleteProduct (Product product) {
        allProducts.remove(product);
    }
    
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
    
    public static boolean selectedPartDelete(Part part){
        boolean find = false;
        
        for (int i = 0; i < allProducts.size(); i++){
            if (allProducts.get(i).getAllAssociatedParts().contains(part)) {
                find = true;
            }
        }
        return find;
    }
}
