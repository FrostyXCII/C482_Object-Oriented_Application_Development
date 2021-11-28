package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Model.Part;
import Model.Product;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;


public class MainScreenController implements Initializable {
    
    @FXML private TextField partSearchField;
    @FXML private TableView<Part> partTable;
    @FXML private TableColumn<Part, Integer> partIdColumn;
    @FXML private TableColumn<Part, String> partNameColumn;
    @FXML private TableColumn<Part, Integer> partInvColumn;
    @FXML private TableColumn<Part, Double> partPriceColumn;
   
    @FXML private TextField productSearchField;
    @FXML private TableView<Product> productTable;
    @FXML private TableColumn<Product, Integer> productIdColumn;
    @FXML private TableColumn<Product, String> productNameColumn;
    @FXML private TableColumn<Product, Integer> productInvColumn;
    @FXML private TableColumn<Product, Double> productPriceColumn;
    
    public void addPartButton (MouseEvent e) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        Scene scene = new Scene(parent);
        
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        
        window.setScene(scene);
        window.show();
    }
    
    public void modifyPartButton (MouseEvent e) throws IOException {
        
        if (partTable.getSelectionModel().getSelectedItem() != null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ModifyPart.fxml"));
            Parent modifyPartParent = loader.load();
            Scene modifyPartScene = new Scene(modifyPartParent);
        
            ModifyPartController controller = loader.getController();
            controller.partToModify(partTable.getSelectionModel().getSelectedItem());
        
            Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
            window.setScene(modifyPartScene);
            window.show();
        }
        
        else {
            Main.ErrorMessages.noSelection("Part");
        }
    }
    
    public void deletePartButton (MouseEvent e) throws IOException {
        
        Part selectedPart = partTable.getSelectionModel().getSelectedItem();
        
        if(Model.Inventory.selectedPartDelete(selectedPart)){
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Part Delete Error!");
            alert.setHeaderText("Part cannot be removed!");
            alert.setContentText("This part is used in a product.");
            alert.showAndWait();
        }else if (selectedPart != null) {
            if (Main.ErrorMessages.confirmAction("Delete")) {
                Model.Inventory.deletePart(selectedPart);
            }
        }
    }
    
    public void searchPartButton () {
        
       if (!partSearchField.getText().trim().isEmpty()) {
           partTable.setItems(Model.Inventory.lookupPart(partSearchField.getText().trim()));
       }
       
       else {
           partTable.setItems(Model.Inventory.getAllParts());
       }    
    }
    
    public void addProductButton (MouseEvent e) throws IOException {
        Parent addProductParent = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        Scene addProductScene = new Scene(addProductParent);
        
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        
        window.setScene(addProductScene);
        window.show();
    }
    
    public void modifyProductButton (MouseEvent e) throws IOException {
        
        if (productTable.getSelectionModel().getSelectedItem() != null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ModifyProduct.fxml"));
            Parent modifyProductParent = loader.load();
        
            Scene modifyProductScene = new Scene(modifyProductParent);
        
            ModifyProductController controller = loader.getController();
            controller.productToModify(productTable.getSelectionModel().getSelectedItem());
        
            Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
            window.setScene(modifyProductScene);
            window.show();
        }
        
        else {
            Main.ErrorMessages.noSelection("Product");
        } 
    }
    
    public void deleteProductButton (MouseEvent e) {
        
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        
        if (selectedProduct != null){
            if (Main.ErrorMessages.confirmAction("Delete")) {
                Model.Inventory.deleteProduct(selectedProduct);
            }
        }   
    }
    
    public void searchProductButton () {
        
       if (!productSearchField.getText().trim().isEmpty()) {
           productTable.setItems(Model.Inventory.lookupProduct(productSearchField.getText().trim()));
       }
       
       else {
           productTable.setItems(Model.Inventory.getAllProducts());
       }    
    }
    
    public void exitButton(MouseEvent e) {
        if (Main.ErrorMessages.confirmAction("Exit")) {
            Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
            stage.close();
        }
    }
    
    public void ClearSearchParts(MouseEvent e) throws IOException {
        updatePartTableView();
        partSearchField.setText("");
    }

    public void ClearSearchProducts(MouseEvent e) throws IOException {
        updateProductTableView();
        productSearchField.setText("");
    }
    
    public void updatePartTableView() {
        partTable.setItems(Model.Inventory.getAllParts());
    }

    public void updateProductTableView() {
      productTable.setItems(Model.Inventory.getAllProducts());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        partTable.setItems(Model.Inventory.getAllParts());
        partTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTable.setItems(Model.Inventory.getAllProducts());
        productTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }    
}