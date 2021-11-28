package Main;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

public class ErrorMessages {
    
    public static boolean confirmAction(String button) {
      
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm " + button);
        alert.setHeaderText(null);
        alert.setContentText("Do you really want to " + button.toLowerCase() + "?");

        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(yes, no);
        Optional<ButtonType> result = alert.showAndWait();
        
        return (result.get() == yes);
    }
    
    public static void partRequirement() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Products must have associated parts");
        alert.setHeaderText(null);
        alert.setContentText("Products must have at least 1 associated part.\nPlease add a part and try again.");
        alert.showAndWait();
    }
    
    public static void noSelection(String partOrProduct) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No " + partOrProduct + " Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a " + partOrProduct.toLowerCase() + " to modify first");
            alert.showAndWait();
    }
}
