package com.example.midterm_2411872_4;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class AirTicketController
{
    @javafx.fxml.FXML
    private CheckBox filter_checkBocIsRefundable;
    @javafx.fxml.FXML
    private TextField field_PNR;
    @javafx.fxml.FXML
    private TableColumn<AirTicket, String> tableCol_PNR;
    @javafx.fxml.FXML
    private TableView<AirTicket> tableView;
    @javafx.fxml.FXML
    private TextField field_flightTime;
    @javafx.fxml.FXML
    private TextField field_price;
    @javafx.fxml.FXML
    private ComboBox<String> field_comboxDestinationCode;
    @javafx.fxml.FXML
    private TableColumn<AirTicket, LocalDate> tableCol_travelDate;
    @javafx.fxml.FXML
    private TableColumn<AirTicket, String> tableCol_originCode;
    @javafx.fxml.FXML
    private TextField field_flightNo;
    @javafx.fxml.FXML
    private DatePicker field_datePicerTravelDate;
    @javafx.fxml.FXML
    private TableColumn<AirTicket, Boolean> tableCol_isRefundable;
    @javafx.fxml.FXML
    private Label label_textSeatCount;
    @javafx.fxml.FXML
    private TableColumn<AirTicket, String> tableCol_flightNo;
    @javafx.fxml.FXML
    private CheckBox field_datePickerIsRefundable;
    @javafx.fxml.FXML
    private ComboBox<String> field_comboxSeatType;
    @javafx.fxml.FXML
    private ComboBox<String> field_comboxOriginCode;
    @javafx.fxml.FXML
    private ComboBox<String> filter_originCode;
    @javafx.fxml.FXML
    private TableColumn<AirTicket, Float> tableCol_price;

    ArrayList<AirTicket> ticketList;
    @javafx.fxml.FXML
    private DatePicker filter_travelDate;

    @javafx.fxml.FXML
    public void initialize() {
        ticketList = new ArrayList<AirTicket>();

        field_comboxDestinationCode.getItems().addAll("DAC", "DXB", "MAD", "LHR", "BKK", "HKG");
        field_comboxOriginCode.getItems().addAll("DAC", "DXB", "MAD", "LHR", "BKK", "HKG");
        filter_originCode.getItems().addAll("DAC", "DXB", "MAD", "LHR", "BKK", "HKG");
        field_comboxSeatType.getItems().addAll("Economy", "Business", "First");

        tableCol_flightNo.setCellValueFactory(new PropertyValueFactory<AirTicket, String>("flightNo"));
        tableCol_isRefundable.setCellValueFactory(new PropertyValueFactory<AirTicket, Boolean>("isRefundable"));
        tableCol_originCode.setCellValueFactory(new PropertyValueFactory<AirTicket, String>("originCode"));
        tableCol_PNR.setCellValueFactory(new PropertyValueFactory<AirTicket, String>("PNR"));
        tableCol_price.setCellValueFactory(new PropertyValueFactory<AirTicket, Float>("price"));
        tableCol_travelDate.setCellValueFactory(new PropertyValueFactory<AirTicket, LocalDate>("travelDate"));


    }

    @javafx.fxml.FXML
    public void ShowSeatTypeConuntsButton(ActionEvent actionEvent) {
        if (tableView.getItems().isEmpty()){
            label_textSeatCount.setText("empty Table");
            return;
        }
        int Economy = 0;
        int Business = 0;
        int First = 0;
        for ( AirTicket A: tableView.getItems()){
            if (A.getSeatType().equals("Economy")){
                Economy ++;
            } else if (A.getSeatType().equals("Business")) {
                Business ++;
            }
            else {
                First ++;
            }
        }

        label_textSeatCount.setText("Business: " + Business + "  Economy: " + Economy + "  First: " + First);

    }

    @javafx.fxml.FXML
    public void ValidateAndAddNewObjectButton(ActionEvent actionEvent) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        if( (field_comboxOriginCode.getValue().equals(field_comboxDestinationCode.getValue())) ){
            a.setContentText("Origin Code and Destination code can not be same");
            a.showAndWait();
            return;
        }
        if ( (Float.parseFloat(field_price.getText()) < 20000) && field_comboxSeatType.getValue().equals("Business") ){
            field_price.clear();
            a.setContentText("for business seat price can not less than 20000");
            a.showAndWait();
            return;
        }

        if ( field_datePicerTravelDate.getValue().isBefore(LocalDate.now()) ){
            a.setContentText("travel date can not be a past date");
            a.showAndWait();
            return;
        }


        ticketList.add(new AirTicket(
//                String flightNo, String PNR, String originCode, String destinationCode, String seatType,
//                LocalDate travelDate, String flightTime, Float price, boolean isRefundable
                field_flightNo.getText(),
                field_PNR.getText(),
                field_comboxOriginCode.getValue(),
                field_comboxDestinationCode.getValue(),
                field_comboxSeatType.getValue(),
                field_datePicerTravelDate.getValue(),
                field_flightTime.getText(),
                Float.parseFloat(field_price.getText()),
                field_datePickerIsRefundable.isSelected()
        ));

    }

    @javafx.fxml.FXML
    public void filterAndLoadTableButton(ActionEvent actionEvent) {
        tableView.getItems().clear();
        if ( (filter_checkBocIsRefundable.isSelected()) ) {
            for (AirTicket A : ticketList) {
                if ( (filter_originCode.getValue().equals(A.getOriginCode())) && (filter_travelDate.getValue().equals(A.getTravelDate())) && (filter_checkBocIsRefundable.isSelected() == A.isRefundable()) ) {
                    tableView.getItems().add(A);
                }

            }
        }
        else {
            for (AirTicket A : ticketList) {
                if ( (filter_originCode.getValue().equals(A.getOriginCode())) && (filter_travelDate.getValue().equals(A.getTravelDate())) ) {
                    tableView.getItems().add(A);
                }
            }
        }
    }
}