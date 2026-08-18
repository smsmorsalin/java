package cse213.finalexam_2411872_4;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class IUBSmartCard implements Serializable{
    private final int userId;
    private String name;
    private String userType;
    private final LocalDate dateOfBirth;
    private final String bloodGroup;
    private LocalDate dateOfExpiry;
    private boolean isEmployee;

    public IUBSmartCard(int userId, LocalDate dateOfBirth, String bloodGroup, String name, String userType, LocalDate dateOfExpiry, boolean isEmployee) {
        this.userId = userId;
        this.dateOfBirth = dateOfBirth;
        this.bloodGroup = bloodGroup;
        this.name = name;
        this.userType = userType;
        this.dateOfExpiry = dateOfExpiry;
        this.isEmployee = isEmployee;
    }


    public String getAttendanceRule(){
        if(this.userType.equals("Student")){
            return userId + "Can enter IUB anytime, need to tap searately for classroom/Gym/Lib.";
        }
        if(this.userType.equals("Faculty")){
            return userId + "Can enter IUB anytime, adhere to their class/office schedule.";
        }
        if(this.userType.equals("NonFaculty")){
            return userId + "need to enter IUB by 9 AM, late attendance applicable.";
        }
        if(this.userType.equals("Trustee")){
            return userId + "Can enter IUB anytime, without any restriction";
        }
        return "User Type has no define Rules";
    }

    public void createNewSmartCard(int userId, LocalDate dateOfBirth, String bloodGroup, String name, String userType, LocalDate dateOfExpiry, boolean isEmployee){
        IUBSmartCard newSmartCard = new IUBSmartCard(userId, dateOfBirth, bloodGroup, name, userType,dateOfExpiry, isEmployee);

        ObjectOutputStream oos = null;
        FileOutputStream fos = null;

        try{
            File newFile = new File("IUBSmartCard.bin");

            if (newFile.exists()){
                fos = new FileOutputStream(newFile, true);
                oos = new AppendableObjectOutputStream(fos);
            }
            else{
                fos = new FileOutputStream(newFile);
                oos = new ObjectOutputStream(fos);
            }
            oos.writeObject(newSmartCard);


        } catch (Exception e) {
            //
        }finally {
            if (oos != null){
                try{
                    oos.close();
                } catch (IOException e) {
                    //
                }
            }
        }
    }

    public ArrayList<Object> readFromBinFile(){
        ArrayList<Object> readList = new ArrayList<>();

        ObjectInputStream ois = null;
        try{
            File readFile = new File("IUBSmartCard.bin");
            ois = new ObjectInputStream(new FileInputStream(readFile));
            while (true){
                Object tempObj = ois.readObject();
                readList.add(tempObj);
            }

        }catch (Exception e){
            //
        }finally {
            if (ois != null){
                try{
                    ois.close();
                } catch (IOException e) {
                    //
                }
            }
        }
        return readList;
    }



    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public LocalDate getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(LocalDate dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public boolean isEmployee() {
        return isEmployee;
    }

    public void setEmployee(boolean employee) {
        isEmployee = employee;
    }

    @Override
    public String toString() {
        return "IUBSmartCard{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", userType='" + userType + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", dateOfExpiry=" + dateOfExpiry +
                ", isEmployee=" + isEmployee +
                '}';
    }
}
