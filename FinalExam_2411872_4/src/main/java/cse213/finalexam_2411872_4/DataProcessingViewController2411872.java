package cse213.finalexam_2411872_4;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class DataProcessingViewController2411872
{
    @javafx.fxml.FXML
    private Label DummyLabelNOTtoUse;
    @javafx.fxml.FXML
    private ComboBox<String> fxidBloodGroupComboBox;
    @javafx.fxml.FXML
    private TableColumn<IUBSmartCard, Boolean> tableColIsEmployee;
    @javafx.fxml.FXML
    private TableColumn<IUBSmartCard, String> tableColBloodGroup;
    @javafx.fxml.FXML
    private TableColumn<IUBSmartCard, LocalDate> tableColDateOfBirth;
    @javafx.fxml.FXML
    private TableColumn<IUBSmartCard, String> tableColUserType;
    @javafx.fxml.FXML
    private ComboBox<String> fxidUserTypeComboBox;
    @javafx.fxml.FXML
    private TableColumn<IUBSmartCard, Integer> tableColUserID;
    @javafx.fxml.FXML
    private TableColumn<IUBSmartCard, String> tableColUserName;
    @javafx.fxml.FXML
    private TableColumn<IUBSmartCard, LocalDate> tableColDateOfExpiry;
    @javafx.fxml.FXML
    private TableView<IUBSmartCard> tableView;

    @javafx.fxml.FXML
    public void initialize() {
        fxidUserTypeComboBox.getItems().addAll("Student", "Faculty", "NonFaculty", "Trustee");
        fxidBloodGroupComboBox.getItems().addAll("A+", "A-", "O+", "O-", "AB+", "AB-");

        tableColUserID.setCellValueFactory(new PropertyValueFactory<IUBSmartCard, Integer>("userId"));
        tableColUserName.setCellValueFactory(new PropertyValueFactory<IUBSmartCard, String>("name"));
        tableColBloodGroup.setCellValueFactory(new PropertyValueFactory<IUBSmartCard, String>("bloodGroup"));
        tableColDateOfBirth.setCellValueFactory(new PropertyValueFactory<IUBSmartCard, LocalDate>("dateOfBirth"));
        tableColDateOfExpiry.setCellValueFactory(new PropertyValueFactory<IUBSmartCard, LocalDate>("dateOfExpiry"));
        tableColIsEmployee.setCellValueFactory(new PropertyValueFactory<IUBSmartCard, Boolean>("isEmployee"));
    }


    @javafx.fxml.FXML
    public void loadTableWithMatchedIUBSmartCardButtonOnAction(ActionEvent actionEvent) {

        if (fxidUserTypeComboBox.getValue() == null || fxidBloodGroupComboBox.getValue() == null){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Select userType and blood group.");
            return;
        }

        IUBSmartCard LoggedInUser = new IUBSmartCard(1, LocalDate.now(), "A+", "SMS", "Student", LocalDate.now(), false);
        ArrayList<Object> readDataList = LoggedInUser.readFromBinFile();

        for(Object obj: readDataList){
            tableView.getItems().clear();
            if(obj instanceof IUBSmartCard iubSmartCard){
                if(iubSmartCard.getUserType().equals(fxidUserTypeComboBox.getValue()) &&
                        iubSmartCard.getBloodGroup().equals(fxidBloodGroupComboBox.getValue())){
                    tableView.getItems().add(iubSmartCard);
                }
            }
        }

    }
}