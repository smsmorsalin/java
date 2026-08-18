package com.example.modification_2411872_4_40;

import javafx.scene.control.Alert;

public class AlertGeneratorUtility {
    public static void showInformationAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
