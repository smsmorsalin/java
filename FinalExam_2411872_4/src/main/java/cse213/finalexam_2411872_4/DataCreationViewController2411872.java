package cse213.finalexam_2411872_4;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class DataCreationViewController2411872
{
    @javafx.fxml.FXML
    private Label dummyLabelNOTtoUse;
    @javafx.fxml.FXML
    private TextField fxidUserNameTextField;
    @javafx.fxml.FXML
    private ComboBox<String> fxidBloodGroupComboBox;
    @javafx.fxml.FXML
    private DatePicker fxidDateOfBirthDatePicker;
    @javafx.fxml.FXML
    private DatePicker fxidDateOfExpiryDatePicker;
    @javafx.fxml.FXML
    private TextField fxidUserIdTextField;
    @javafx.fxml.FXML
    private CheckBox fxidIsEmployeeCheckPicker;
    @javafx.fxml.FXML
    private ComboBox<String> fxidUserTypeComboBox;

    @javafx.fxml.FXML
    public void initialize() {
        fxidUserTypeComboBox.getItems().addAll("Student", "Faculty", "NonFaculty", "Trustee");
        fxidBloodGroupComboBox.getItems().addAll("A+", "A-", "O+", "O-");
    }

    Alert a = new Alert(Alert.AlertType.INFORMATION);

    @javafx.fxml.FXML
    public void goTODataProcessingViewButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader myFxmlLoader = new FXMLLoader(DataCreationViewController2411872.class.getResource("DataProcessingView2411872.fxml"));
            Scene nextScene = new Scene(myFxmlLoader.load());
            Stage nextStage =  new Stage();
            nextStage.setTitle("Data Processing View");
            nextStage.setScene(nextScene);
            nextStage.show();
        }
        catch(Exception e){
            //
        }
    }

    @javafx.fxml.FXML
    public void validateAndNewIUBSmartCardButtonOnAction(ActionEvent actionEvent) {
        if(
                (fxidUserTypeComboBox.getValue().equals("Student") || fxidUserTypeComboBox.getValue().equals("Trustee"))
                        && fxidIsEmployeeCheckPicker.isSelected()

        ){
            a.setContentText("user Type Student/Trustee can't be employee");
            a.showAndWait();
            return;
        }
        if(fxidDateOfExpiryDatePicker.getValue() == null){
            a.setContentText("Expiry Date can't empty");
            a.showAndWait();
            return;
        }
        if (fxidDateOfExpiryDatePicker.getValue().isBefore(LocalDate.now())){
            a.setContentText("expiryDate Can't before Date");
            a.showAndWait();
            return;
        }

        IUBSmartCard LoggedInUser = new IUBSmartCard(1, LocalDate.now(), "A+", "SMS", "Student", LocalDate.now(), false);
        LoggedInUser.createNewSmartCard(
                Integer.parseInt(fxidUserIdTextField.getText()),
                fxidDateOfBirthDatePicker.getValue(),
                fxidBloodGroupComboBox.getValue(),
                fxidUserNameTextField.getText(),
                fxidUserTypeComboBox.getValue(),
                fxidDateOfExpiryDatePicker.getValue(),
                fxidIsEmployeeCheckPicker.isSelected()
        );

    }
}