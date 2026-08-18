package com.example.modification_2411872_4_40;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class createNewSuplierViewController
{
    @javafx.fxml.FXML
    private TextField fxidUserNameTextField;
    @javafx.fxml.FXML
    private DatePicker fxidJoiningDateDatePicker;
    @javafx.fxml.FXML
    private TextField fxidPhoneNumberTextField;
    @javafx.fxml.FXML
    private TextField fxidUserIdTextField;
    @javafx.fxml.FXML
    private ComboBox<String> fxidGenderComboBox;
    @javafx.fxml.FXML
    private TextField fxidAddressTextFIeld;
    @javafx.fxml.FXML
    private TextField fxidLiceneceNumberTextField;
    @javafx.fxml.FXML
    private TextField fxidBusinessNameTextField;

    Suplier loggedInUser;
    @javafx.fxml.FXML
    private ComboBox<String> fxidZipcodeComboBox;


    @javafx.fxml.FXML
    public void initialize() {
        fxidGenderComboBox.getItems().addAll("Male", "Female", "Others");
        fxidZipcodeComboBox.getItems().addAll("1229: Bashundhara", "1280: Fulbari", "1260: Parbatipur", "Others" );
    }

    @javafx.fxml.FXML
    public void createNewUserAndAddToBinFileButtonOnAction(ActionEvent actionEvent) {
        if (fxidUserIdTextField.getText().trim().isEmpty() ||
                fxidUserNameTextField.getText().trim().isEmpty() ||
                fxidGenderComboBox.getValue() == null ||
                fxidLiceneceNumberTextField.getText().trim().isEmpty() ||
                fxidPhoneNumberTextField.getText().trim().isEmpty() ||
                fxidJoiningDateDatePicker.getValue() == null ||
                fxidBusinessNameTextField.getText().trim().isEmpty() ||
                fxidAddressTextFIeld.getText().trim().isEmpty() ||
                fxidZipcodeComboBox.getValue() == null
        ) {
            AlertGeneratorUtility.showInformationAlert("Error", "Please fill all the Required Field");
            return;
        }

        try{
            Integer.parseInt(fxidUserIdTextField.getText().trim());
            Integer.parseInt(fxidLiceneceNumberTextField.getText().trim());
        } catch (NumberFormatException e) {
            AlertGeneratorUtility.showInformationAlert("Error", "User ID, ");
            return;
        }
//        if(fxidJoiningDateDatePicker.getValue().isAfter(LocalDate.now())){
//            AlertGeneratorUtility.showInformationAlert("Error", "Joining Date must be past date");
//            return;
//        }

        //for test purpose only
        loggedInUser = new Suplier(100, "SMS", "Male", 123, LocalDate.now(), "017", "Friengineers ORG", "B R/A", "1229: Bashundhara");

//        int userId, String userName, String gender,
//        int licenceNumber, String phoneNumber, LocalDate joiningDate,
//                String suplierBusinessName, String address

        Suplier s = loggedInUser.createNewSuplier(
                Integer.parseInt(fxidUserIdTextField.getText().trim()),fxidUserNameTextField.getText(),
                fxidGenderComboBox.getValue(), Integer.parseInt(fxidLiceneceNumberTextField.getText().trim()),
                fxidPhoneNumberTextField.getText(), fxidJoiningDateDatePicker.getValue(),
                fxidBusinessNameTextField.getText(), fxidAddressTextFIeld.getText(), fxidZipcodeComboBox.getValue()
        );


    }

    @javafx.fxml.FXML
    public void seeAllSuplierInformationButtonOnAction(ActionEvent actionEvent) {
        SceneSwitchHelperUtility.switchSceneWithData(actionEvent, "/com/example/modification_2411872_4_40/suplierInformationView.fxml", loggedInUser);
    }
}