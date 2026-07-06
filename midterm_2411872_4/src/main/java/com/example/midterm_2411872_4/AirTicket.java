package com.example.midterm_2411872_4;

import java.time.LocalDate;

public class AirTicket {
    private String flightNo;
    private String PNR;
    private String originCode;
    private String destinationCode;
    private String seatType;
    private LocalDate travelDate;
    private String flightTime;
    private Float price;
    private boolean isRefundable;

    public AirTicket(String flightNo, String PNR, String originCode, String destinationCode, String seatType, LocalDate travelDate, String flightTime, Float price, boolean isRefundable) {
        this.flightNo = flightNo;
        this.PNR = PNR;
        this.originCode = originCode;
        this.destinationCode = destinationCode;
        this.seatType = seatType;
        this.travelDate = travelDate;
        this.flightTime = flightTime;
        this.price = price;
        this.isRefundable = isRefundable;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public String getPNR() {
        return PNR;
    }

    public String getOriginCode() {
        return originCode;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public String getSeatType() {
        return seatType;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public Float getPrice() {
        return price;
    }

    public boolean isRefundable() {
        return isRefundable;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    @Override
    public String toString() {
        return "AirTicket{" +
                "flightNo='" + flightNo + '\'' +
                ", PNR='" + PNR + '\'' +
                ", originCode='" + originCode + '\'' +
                ", destinationCode='" + destinationCode + '\'' +
                ", seatType='" + seatType + '\'' +
                ", travelDate=" + travelDate +
                ", flightTime='" + flightTime + '\'' +
                ", price=" + price +
                ", isRefundable=" + isRefundable +
                '}';
    }

    public void printRefundAmount(){
        if ( this.isRefundable ){
            System.out.println("non Refundable");
        }
        if ( this.isRefundable && this.seatType.equals("Economy") ){
            System.out.println("50% of the price as refund amount");
            return;
        }
        if ( this.isRefundable && (this.seatType.equals("Business")) ){
            System.out.println("70% of the price as refund amount");
            return;
        }
        if ( this.isRefundable && (this.seatType.equals("First")) ){
            System.out.println("0% of the price as refund amount");
            return;
        }
    }
}
