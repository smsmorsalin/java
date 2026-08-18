package com.example.modification_2411872_4_40;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SceneSwitchHelperUtility {
    public static void switchSceneWithData(javafx.event.ActionEvent event, String fxml, User data) {
        try {

            URL location = SceneSwitchHelperUtility.class.getResource(fxml);

            if (location == null) {
                AlertGeneratorUtility.showInformationAlert(
                        "FXML Error",
                        "FXML file not found:\n" + fxml
                );
                return;
            }

            FXMLLoader loader = new FXMLLoader(location);
            Parent root = loader.load();

            Object controller = loader.getController();

            if (controller instanceof suplierInformationViewController nextView) {
                nextView.receiveData();
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            AlertGeneratorUtility.showInformationAlert(
                    "Scene Loading Error",
                    "Unable to load the FXML file."
            );

        } catch (Exception e) {
            AlertGeneratorUtility.showInformationAlert(
                    "Unexpected Error",
                    e.getMessage()
            );
        }
    }

}
