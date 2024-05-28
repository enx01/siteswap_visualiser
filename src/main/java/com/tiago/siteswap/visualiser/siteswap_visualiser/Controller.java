package com.tiago.siteswap.visualiser.siteswap_visualiser;

import com.tiago.siteswap.visualiser.siteswap_visualiser.diagram.DiagramController;
import com.tiago.siteswap.visualiser.siteswap_visualiser.model.Sequence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {

    @FXML
    private TextField sequenceTextField;


    @FXML
    protected void onGenerateButtonClick(ActionEvent event) throws IOException{
        String sequence = sequenceTextField.getText();
        boolean isValid = true;

        if (sequence.length() > 4) { isValid = false; }
        for (int i = 0; i < sequence.length(); i++) {
            if (!Character.isDigit(sequence.charAt(i))) { isValid = false; break; }
        }

        if (isValid) {
            // On teste d'abord si le pattern est valide.
            Sequence test = new Sequence(sequence);
            if (test.checkDuplicates()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid pattern");
                alert.setContentText("The given siteswap pattern is unsatisfactory");

                // Affiche la boîte de dialogue et attend que l'utilisateur la ferme
                alert.showAndWait();
                return;
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("visualiser-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),640,400);

            DiagramController control = fxmlLoader.getController();
            control.initialize(sequence);

            stage.setScene(scene);
            stage.show();
        }
    }

}