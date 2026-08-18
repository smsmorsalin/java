package com.example.modification_2411872_4_40;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class suplierInformationViewController
{
    @javafx.fxml.FXML
    private TableColumn<Suplier, LocalDate> tabelColJoiningDate;
    @javafx.fxml.FXML
    private TableColumn<Suplier, Integer> tabelColUserId;
    @javafx.fxml.FXML
    private TableColumn<Suplier, String> tabelColAddress;
    @javafx.fxml.FXML
    private TableColumn<Suplier, String> tabelColGender;
    @javafx.fxml.FXML
    private TableColumn<Suplier, Integer> tabelColLicenceNumber;
    @javafx.fxml.FXML
    private TableColumn<Suplier, String> tabelColUserName;
    @javafx.fxml.FXML
    private TableColumn<Suplier, String> tabelColBusinessName;
    @javafx.fxml.FXML
    private TableColumn<Suplier, String> tabelColPhoneNumber;
    @javafx.fxml.FXML
    private TableView<Suplier> tableView;

    private Suplier storeRecivedData;
    @javafx.fxml.FXML
    private Label fxidShowUserNameLabel;
    @javafx.fxml.FXML
    private PieChart piChart;
    ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
    ObservableList<PieChart.Data> zipData = FXCollections.observableArrayList();
    @javafx.fxml.FXML
    private ComboBox<String> filterComboBox;
    @javafx.fxml.FXML
    private DatePicker fxidFromDatePicker;
    @javafx.fxml.FXML
    private DatePicker fxidToDatePicker;
    @javafx.fxml.FXML
    private TableColumn<Suplier, String> tabelColAddress1;
    @javafx.fxml.FXML
    private PieChart zipPiChart;

    public void receiveData(){
        loadTableView();
        loadPiChart();
        loadZipPiChart();
    }
    ArrayList<Object> userList;

    private void loadTableView(){
        tableView.getItems().clear();
        userList = BinaryFileUtility.readObjects("User.bin");
        if(userList == null || userList.size() == 0){
            return;
        }
        for(Object obj: userList){
            if(obj instanceof Suplier s){
                tableView.getItems().add(s);
            }
        }
    }

    public void loadPiChart(){
        int maleCount = 0;
        int femaleCount = 0;
        int otherCount = 0;

        data.clear();
        for (Suplier s : tableView.getItems()) {
            if(s.getGender().equals("Male")){
                maleCount ++;
            }
            else if(s.getGender().equals("Female")){
                femaleCount ++;
            }
            else {
                otherCount ++;
            }
        }

        data.add(new PieChart.Data("Male", maleCount));
        data.add(new PieChart.Data("Female", femaleCount));
        data.add(new PieChart.Data("Other", otherCount));

        piChart.setData(data);
    }

    public void loadZipPiChart(){
        int bashundharaCount = 0;
        int fulbariCount = 0;
        int parbatipurCount = 0;
        int othersCount = 0;

        zipData.clear();
        for (Suplier s : tableView.getItems()) {
            if(s.getZipCode().equals("1229: Bashundhara")){
                bashundharaCount ++;
            }
            else if(s.getZipCode().equals("1280: Fulbari")){
                fulbariCount ++;
            }
            else if(s.getZipCode().equals("1260: Parbatipur")){
                parbatipurCount ++;
            }
            else {
                othersCount ++;
            }
        }

        zipData.add(new PieChart.Data("1229: Bashundhara", bashundharaCount));
        zipData.add(new PieChart.Data("1280: Fulbari", fulbariCount));
        zipData.add(new PieChart.Data("1260: Parbatipur", parbatipurCount));
        zipData.add(new PieChart.Data("Others", othersCount));

        zipPiChart.setData(zipData);
    }

    @javafx.fxml.FXML
    public void initialize() {
        filterComboBox.getItems().addAll("Male", "Female", "Others");

        tabelColUserId.setCellValueFactory(new PropertyValueFactory<Suplier, Integer>("userId"));
        tabelColUserName.setCellValueFactory(new PropertyValueFactory<Suplier, String>("userName"));
        tabelColGender.setCellValueFactory(new PropertyValueFactory<Suplier, String>("gender"));
        tabelColLicenceNumber.setCellValueFactory(new PropertyValueFactory<Suplier, Integer>("licenceNumber"));
        tabelColPhoneNumber.setCellValueFactory(new PropertyValueFactory<Suplier, String>("phoneNumber"));
        tabelColJoiningDate.setCellValueFactory(new PropertyValueFactory<Suplier, LocalDate>("joinDate"));
        tabelColAddress.setCellValueFactory(new PropertyValueFactory<Suplier, String>("address"));
        tabelColBusinessName.setCellValueFactory(new PropertyValueFactory<Suplier, String>("suplierBusinessName"));
        tabelColBusinessName.setCellValueFactory(new PropertyValueFactory<Suplier, String>("zipCode"));


    }

    @javafx.fxml.FXML
    public void FilterButtonOnAction(ActionEvent actionEvent) {
//        if (filterComboBox.getValue() == null){
//            AlertGeneratorUtility.showInformationAlert("Error", "Select a value from gender");
//            return;
//        }

        // from to now
        if(fxidFromDatePicker.getValue() != null && fxidToDatePicker.getValue() == null && filterComboBox.getValue() == null){
            tableView.getItems().clear();
            for(Object obj : userList){
                if(obj instanceof Suplier s){
                    if(s.getJoinDate().isAfter(fxidFromDatePicker.getValue())){
                        tableView.getItems().add(s);
                    }
                }
            }

            if(tableView.getItems().isEmpty()){
                AlertGeneratorUtility.showInformationAlert("message", "No data found");
                return;
            }
            loadPiChart();
            loadZipPiChart();

            return;
        }

        //to --> before all
        if(fxidFromDatePicker.getValue() == null && fxidToDatePicker.getValue() != null && filterComboBox.getValue() == null){
            tableView.getItems().clear();
            for(Object obj : userList){
                if(obj instanceof Suplier s){
                    if(s.getJoinDate().isBefore(fxidToDatePicker.getValue())){
                        tableView.getItems().add(s);
                    }
                }
            }
            if(tableView.getItems().isEmpty()){
                AlertGeneratorUtility.showInformationAlert("message", "No data found");
                return;
            }

            loadPiChart();
            loadZipPiChart();
            return;
        }

        //only gender
        if(fxidFromDatePicker.getValue() == null && fxidToDatePicker.getValue() == null && filterComboBox.getValue() != null){
            tableView.getItems().clear();
            for(Object obj : userList){
                if(obj instanceof Suplier s){
                    if(s.getGender().equals(filterComboBox.getValue())){
                        tableView.getItems().add(s);
                    }
                }
            }
            if(tableView.getItems().isEmpty()){
                AlertGeneratorUtility.showInformationAlert("message", "No data found");
                return;
            }
            loadPiChart();
            loadZipPiChart();
            return;
        }

        // From -> to
        if(fxidFromDatePicker.getValue() != null && fxidToDatePicker.getValue() != null && filterComboBox.getValue() == null){
            if(fxidFromDatePicker.getValue().isAfter(fxidToDatePicker.getValue())){
                AlertGeneratorUtility.showInformationAlert("Error", "From date must be past than return date");
                return;
            }
            tableView.getItems().clear();
            for(Object obj : userList){
                if(obj instanceof Suplier s){
                    if(s.getJoinDate().isBefore(fxidToDatePicker.getValue()) && s.getJoinDate().isAfter(fxidFromDatePicker.getValue())){
                        tableView.getItems().add(s);
                    }
                }
            }
            if(tableView.getItems().isEmpty()){
                AlertGeneratorUtility.showInformationAlert("message", "No data found");
                return;
            }
            loadPiChart();
            loadZipPiChart();
            return;
        }

        // from+gender
        if(fxidFromDatePicker.getValue() != null && fxidToDatePicker.getValue() == null && filterComboBox.getValue() != null){
            tableView.getItems().clear();
            for(Object obj : userList){
                if(obj instanceof Suplier s){
                    if(s.getJoinDate().isAfter(fxidFromDatePicker.getValue()) && s.getGender().equals(filterComboBox.getValue())){
                        tableView.getItems().add(s);
                    }
                }
            }
            if(tableView.getItems().isEmpty()){
                AlertGeneratorUtility.showInformationAlert("message", "No data found");
                return;
            }
            loadPiChart();
            loadZipPiChart();
            return;
        }

        //to+gender
        if(fxidFromDatePicker.getValue() == null && fxidToDatePicker.getValue() != null && filterComboBox.getValue() != null){
            tableView.getItems().clear();
            for(Object obj : userList){
                if(obj instanceof Suplier s){
                    if(s.getJoinDate().isBefore(fxidToDatePicker.getValue()) && s.getGender().equals(filterComboBox.getValue())){
                        tableView.getItems().add(s);
                    }
                }
            }
            if(tableView.getItems().isEmpty()){
                AlertGeneratorUtility.showInformationAlert("message", "No data found");
                return;
            }
            loadPiChart();
            loadZipPiChart();
            return;
        }

        //all
        if(fxidFromDatePicker.getValue() != null && fxidToDatePicker.getValue() != null && filterComboBox.getValue() != null) {
            if (fxidFromDatePicker.getValue().isAfter(fxidToDatePicker.getValue())) {
                AlertGeneratorUtility.showInformationAlert("Error", "From date must be past than return date");
                return;
            }
            tableView.getItems().clear();
            for (Object obj : userList) {
                if (obj instanceof Suplier s) {
                    if (s.getJoinDate().isBefore(fxidToDatePicker.getValue()) && s.getJoinDate().isAfter(fxidFromDatePicker.getValue()) && s.getGender().equals(filterComboBox.getValue())) {
                        tableView.getItems().add(s);
                    }
                }
            }
            if (tableView.getItems().isEmpty()) {
                AlertGeneratorUtility.showInformationAlert("message", "No data found");
                return;
            }
            loadPiChart();
            loadZipPiChart();
            return;
        }
    }

    @javafx.fxml.FXML
    public void refreshTableWithAllValueButtonOnAction(ActionEvent actionEvent) {
        tableView.getItems().clear();
        loadTableView();
        loadPiChart();
        loadZipPiChart();
    }
}