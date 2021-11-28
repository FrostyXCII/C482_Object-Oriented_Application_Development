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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import Main.ErrorMessages;
import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import javafx.scene.input.MouseEvent;

public class AddPartController implements Initializable {
    
    @FXML private RadioButton InHouseRadioButton;
    @FXML private RadioButton OutsourcedRadioButton;
    private ToggleGroup toggleGroup;
    
    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField invField;
    @FXML private TextField priceField;
    @FXML private TextField maxField;
    @FXML private TextField minField;
    @FXML private Label compMachLabel;
    @FXML private TextField compMachField;
    
    public void SaveButton(MouseEvent e) throws IOException {
        
        int garbage =0;
        String min = minField.getText();
        String max = maxField.getText();
        String inv = invField.getText();
        
        if (this.toggleGroup.getSelectedToggle().equals(this.InHouseRadioButton)) {
            
            Part part=  new InHouse(Integer.parseInt(idField.getText()), "", 0.0, 0,0,0,0);
            
            if (nameField.getText().isEmpty()) {
                garbage--;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Name field cannot be blank.");
                alert.showAndWait();        
                return;
                
            }else{
                part.setName(nameField.getText());
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
                part.setPrice(Double.parseDouble(priceField.getText())); 
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
                part.setStock(Integer.parseInt(invField.getText()));
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
                part.setMax(Integer.parseInt(maxField.getText()));
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
                part.setMin(Integer.parseInt(minField.getText()));
                garbage = 1;
            }
                      
            if (!compMachField.getText().isEmpty()) {
                ((InHouse)part).setMachineId(Integer.parseInt(compMachField.getText()));
            }
            Inventory.addPart(part);
            
        }
        
        if (this.toggleGroup.getSelectedToggle().equals(this.OutsourcedRadioButton)) {
            
            Part part = new Outsourced(Integer.parseInt(idField.getText()), "", 0.0,0,0,0,"");
            
            if (nameField.getText().isEmpty()) {
                garbage--;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Name field cannot be blank.");
                alert.showAndWait();
                return;
            }else{
                part.setName(nameField.getText());
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
                part.setPrice(Double.parseDouble(priceField.getText())); 
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
                alert.setContentText("Please insure that the minimum and maximum are the limits of the inventory.");
                alert.showAndWait();
                return;
            }else{
                part.setStock(Integer.parseInt(invField.getText()));
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
                part.setMin(Integer.parseInt(minField.getText()));
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
                part.setMax(Integer.parseInt(maxField.getText()));
                garbage = 1;
            }
            
            if (!compMachField.getText().isEmpty()) {
                ((Outsourced)part).setCompanyName(compMachField.getText());
            }            
            Inventory.addPart(part);
        }    
        if(garbage ==1){
            MainScreen(e);
        }
    }
    
    public void CancelButton(MouseEvent e) throws IOException {
        if (ErrorMessages.confirmAction("Cancel")) {
            MainScreen(e);
        }
    }
    
    public void radioButtonChanged() {
        
        if (this.toggleGroup.getSelectedToggle().equals(this.InHouseRadioButton)) {
            compMachLabel.setText("Machine ID");
            compMachField.setPromptText("Mach ID");
        }
            
        if (this.toggleGroup.getSelectedToggle().equals(this.OutsourcedRadioButton)) {
            compMachLabel.setText("Company Name");
            compMachField.setPromptText("Comp Nm");
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
        
        toggleGroup = new ToggleGroup();
        this.InHouseRadioButton.setToggleGroup(toggleGroup);
        this.OutsourcedRadioButton.setToggleGroup(toggleGroup);
        this.idField.setText(String.valueOf(Inventory.getAllParts().size()+1));
    }    
}
