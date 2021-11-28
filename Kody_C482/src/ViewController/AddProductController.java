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
import Main.ErrorMessages;
import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

public class AddProductController implements Initializable {
    
    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField invField;
    @FXML private TextField priceField;
    @FXML private TextField maxField;
    @FXML private TextField minField;
    @FXML private TextField searchField;
    
    @FXML private TableView<Part> partTable;
    @FXML private TableColumn<Part, Integer> partIdColumn;
    @FXML private TableColumn<Part, String> partNameColumn;
    @FXML private TableColumn<Part, Integer> partInvColumn;
    @FXML private TableColumn<Part, Double> partPriceColumn;
   
    @FXML private TableView<Part> partTable2;
    @FXML private TableColumn<Part, Integer> partIdColumn2;
    @FXML private TableColumn<Part, String> partNameColumn2;
    @FXML private TableColumn<Part, Integer> partInvColumn2;
    @FXML private TableColumn<Part, Double> partPriceColumn2;
    
    Product product = new Product(0, "", 0.0, 0, 0, 0);
    
    public void SaveButton(MouseEvent e) throws IOException {
        
        int garbage = 0;
        String min = minField.getText();
        String max = maxField.getText();
        String inv = invField.getText();
                
        if (partTable2.getItems().isEmpty()) {
            ErrorMessages.partRequirement();
        }
        
        else {
            product.setId(Integer.parseInt(idField.getText()));
            if (nameField.getText().isEmpty()) {
                garbage--;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Name field cannot be blank.");
                alert.showAndWait();
                return;  
            }else{
                product.setName(nameField.getText());
                garbage = 1;
            }
            
            if (invField.getText().isEmpty()) {
                garbage--;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Inventory field cannot be blank.");
                alert.showAndWait();
                return;
            }else if(Integer.parseInt(inv) < Integer.parseInt(min) || Integer.parseInt(inv) > Integer.parseInt(max)){
                garbage--;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Please insure that the minimum and maximum are within the limits of the inventory.");
                alert.showAndWait();
                return;
            }else{
                product.setStock(Integer.parseInt(invField.getText()));
                garbage = 1;
            }
            
            if (priceField.getText().isEmpty()) {
                garbage--;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Price field cannot be blank.");
                alert.showAndWait(); 
                return;
            }else{
                product.setPrice(Double.parseDouble(priceField.getText()));
                garbage = 1;
            }
                       
            if (maxField.getText().isEmpty()) {
                garbage--;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Maximum field cannot be blank.");
                alert.showAndWait();
                return;
            }else if(Integer.parseInt(max) < Integer.parseInt(min)){
                garbage--;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Maximum cannot be less than minimum.");
                alert.showAndWait();  
                return;
            }else{
                product.setMax(Integer.parseInt(maxField.getText()));
                garbage = 1;
            }
            
            if (minField.getText().isEmpty()) {
                garbage--;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Minimum field cannot be blank.");
                alert.showAndWait(); 
                return;
            }else if(Integer.parseInt(min) > Integer.parseInt(max)){ 
                garbage--;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Minimum cannot be more than maximum.");
                alert.showAndWait();  
                return;
            }else{
                product.setMin(Integer.parseInt(minField.getText()));
                garbage = 1;
            }
             
            Inventory.addProduct(product);
            
            if(garbage == 1){
            MainScreen(e);
            }
        }
    }
    
    public void CancelButton(MouseEvent e) throws IOException {
        if (ErrorMessages.confirmAction("Cancel")) {
            MainScreen(e);
        }
    }
    
    public void AddButton() {
        
        Part selectedPart = partTable.getSelectionModel().getSelectedItem();
        
        if (selectedPart != null) {
            product.addAssociatedPart(selectedPart);
            partTable2.setItems(product.getAllAssociatedParts());
        }
        
    }
    
    public void DeleteButton() {
        Part selectedPart = partTable2.getSelectionModel().getSelectedItem();
        
        if (selectedPart != null) {
            if (ErrorMessages.confirmAction("Delete")) {
                product.deleteAssociatedPart(selectedPart);
            }
        }
    }
    
    public void SearchButton() {
        
        if (!searchField.getText().trim().isEmpty()) {
           partTable.setItems(Inventory.lookupPart(searchField.getText().trim()));
       }
       
       else {
           partTable.setItems(Inventory.getAllParts());
       }    
    }
    
    public void MainScreen(MouseEvent e) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(parent);
        
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        
        window.setScene(scene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        this.idField.setText(String.valueOf(Inventory.getAllProducts().size()+1));
        
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        partTable.setItems(Inventory.getAllParts());
        partTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        partIdColumn2.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn2.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvColumn2.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn2.setCellValueFactory(new PropertyValueFactory<>("price"));

        partTable2.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }    
}