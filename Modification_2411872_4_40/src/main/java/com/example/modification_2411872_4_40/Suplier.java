package com.example.modification_2411872_4_40;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Suplier extends User implements Serializable {
    private final int licenceNumber;
    private String phoneNumber;
    private final LocalDate joinDate;
    private String suplierBusinessName;
    private String address;
    private String zipCode;

    public Suplier(int userId, String userName, String gender, int licenceNumber, LocalDate joinDate, String phoneNumber, String suplierBusinessName, String address, String zipCode) {
        super(userId, userName, gender);
        this.licenceNumber = licenceNumber;
        this.joinDate = joinDate;
        this.phoneNumber = phoneNumber;
        this.suplierBusinessName = suplierBusinessName;
        this.address = address;
        this.zipCode = zipCode;
    }

    public int getLicenceNumber() {
        return licenceNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public String getSuplierBusinessName() {
        return suplierBusinessName;
    }

    public void setSuplierBusinessName(String suplierBusinessName) {
        this.suplierBusinessName = suplierBusinessName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Suplier{" +
                "licenceNumber=" + licenceNumber +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", joinDate=" + joinDate +
                ", suplierBusinessName='" + suplierBusinessName + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public final Suplier createNewSuplier(int userId, String userName, String gender,
                                          int licenceNumber, String phoneNumber, LocalDate joiningDate,
                                          String suplierBusinessName, String address, String zipCode){

        ArrayList<Object> userList = BinaryFileUtility.readObjects("User.bin");
        for(Object obj: userList){
            if(obj instanceof User user){
                if(user.getUserId()==userId){
                    AlertGeneratorUtility.showInformationAlert("Error", "User ID Already Exist");
                    return null;
                }
            }
        }

        Suplier newSuplier = new Suplier(userId, userName, gender, licenceNumber,
                joiningDate, phoneNumber, suplierBusinessName, address, zipCode);

        boolean b = BinaryFileUtility.writeObjects("User.bin", newSuplier);
        if(b){
            AlertGeneratorUtility.showInformationAlert("Succcess", "User "+userId+" Created successfully");
            return newSuplier;
        }
        return null;

    }

}
