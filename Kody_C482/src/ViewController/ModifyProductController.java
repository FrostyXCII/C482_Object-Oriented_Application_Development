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
import javafx.scene.input.MouseEvent;

public class ModifyProductController implements Initializable {
    
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
    
    private Product selectedProduct;

    public void SaveButton(MouseEvent e) throws IOException {
        
        if (partTable2.getItems().isEmpty()) {
            Main.ErrorMessages.partRequirement();
        }
        
        else {
            selectedProduct.setId(Integer.parseInt(idField.getText()));
            selectedProduct.setName(nameField.getText());
            selectedProduct.setPrice(Double.parseDouble(priceField.getText()));
            selectedProduct.setStock(Integer.parseInt(invField.getText()));
            selectedProduct.setMin(Integer.parseInt(minField.getText()));
            selectedProduct.setMax(Integer.parseInt(maxField.getText()) );

            Model.Inventory.updateProduct(Integer.parseInt(idField.getText()), selectedProduct);
            MainScreen(e);  
        }
    }
    
    public void CancelButton(MouseEvent e) throws IOException {
        if (Main.ErrorMessages.confirmAction("Cancel")) {
            MainScreen(e);
        }
    }
    
    public void AddButton() {
        
        Part part = partTable.getSelectionModel().getSelectedItem();
        
        if (part != null) {
            selectedProduct.addAssociatedPart(part);
            partTable2.setItems(selectedProduct.getAllAssociatedParts());
        }
    }
    
    public void DeleteButton() {
        
        Part part = partTable2.getSelectionModel().getSelectedItem();
        
        if (part != null) {
            if (Main.ErrorMessages.confirmAction("Delete")) {    
                selectedProduct.deleteAssociatedPart(part);
            }
        }
    }
    
    public void SearchButton() {
        
        if (!searchField.getText().trim().isEmpty()) {
           partTable.setItems(Model.Inventory.lookupPart(searchField.getText().trim()));
       }
       
       else {
           partTable.setItems(Model.Inventory.getAllParts());
       }
    }
    
    public void MainScreen(MouseEvent e) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(parent);
        
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        
        window.setScene(scene);
        window.show();
    }

    public void productToModify(Product product){
        selectedProduct = product;
        idField.setText(String.valueOf(selectedProduct.getId()));
        nameField.setText(selectedProduct.getName());
        invField.setText(String.valueOf(selectedProduct.getStock()));
        priceField.setText(String.valueOf(selectedProduct.getPrice()));
        maxField.setText(String.valueOf(selectedProduct.getMax()));
        minField.setText(String.valueOf(selectedProduct.getMin()));
        partTable2.setItems(selectedProduct.getAllAssociatedParts());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        partTable.setItems(Model.Inventory.getAllParts());
        partTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        partIdColumn2.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn2.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvColumn2.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn2.setCellValueFactory(new PropertyValueFactory<>("price"));

        partTable2.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }    
    
}
