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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import Model.Outsourced;
import Model.Part;
import javafx.scene.input.MouseEvent;

public class ModifyPartController implements Initializable {
    
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
    
    private Part selectedPart;

    public void SaveButton(MouseEvent e) throws IOException {
        
         if (this.toggleGroup.getSelectedToggle().equals(this.InHouseRadioButton)) {
            Part newPart = new Model.InHouse(
                    Integer.parseInt(idField.getText()), 
                    nameField.getText(), 
                    Double.parseDouble(priceField.getText()), 
                    Integer.parseInt(invField.getText()), 
                    Integer.parseInt(minField.getText()), 
                    Integer.parseInt(maxField.getText()), 
                    Integer.parseInt(compMachField.getText())
            );
            Model.Inventory.updatePart(Integer.parseInt(idField.getText()), newPart);
        }
         
        if (this.toggleGroup.getSelectedToggle().equals(this.OutsourcedRadioButton)) {
            Part newPart = new Outsourced(
                    Integer.parseInt(idField.getText()), 
                    nameField.getText(), 
                    Double.parseDouble(priceField.getText()), 
                    Integer.parseInt(invField.getText()), 
                    Integer.parseInt(minField.getText()), 
                    Integer.parseInt(maxField.getText()), 
                    compMachField.getText()
            );
            Model.Inventory.updatePart(Integer.parseInt(idField.getText()), newPart);
        }
        
        MainScreen(e);
    }
    
    public void CancelButton(MouseEvent e) throws IOException {
        if (Main.ErrorMessages.confirmAction("Cancel")) {
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
        Parent mainScreenParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene mainScreenScene = new Scene(mainScreenParent);
        
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        
        window.setScene(mainScreenScene);
        window.show();
    }
    
    public void partToModify(Part part){
        
        selectedPart = part;
        idField.setText(String.valueOf(selectedPart.getId()));
        nameField.setText(selectedPart.getName());
        invField.setText(String.valueOf(selectedPart.getStock()));
        priceField.setText(String.valueOf(selectedPart.getPrice()));
        maxField.setText(String.valueOf(selectedPart.getMax()));
        minField.setText(String.valueOf(selectedPart.getMin()));
        
        if (selectedPart instanceof Model.InHouse){        
            InHouseRadioButton.setSelected(true);
            compMachLabel.setText("Machine ID");
            compMachField.setText(String.valueOf(((Model.InHouse)selectedPart).getMachineId()));
        }
        
        else if (selectedPart instanceof Outsourced){
            OutsourcedRadioButton.setSelected(true);
            compMachLabel.setText("Company Name");
            compMachField.setText(((Outsourced)selectedPart).getCompanyName());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        toggleGroup = new ToggleGroup();
        this.InHouseRadioButton.setToggleGroup(toggleGroup);
        this.OutsourcedRadioButton.setToggleGroup(toggleGroup);
    }    
}
