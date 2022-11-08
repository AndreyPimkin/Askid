package com.robbit.askid.POJO;

public class Client {
    private String ID;
    private String passwordOne;
    private String passwordTwo;

    public Client(String ID, String passwordOne, String passwordTwo) {
        this.ID = ID;
        this.passwordOne = passwordOne;
        this.passwordTwo = passwordTwo;
    }

    public Client() {

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPasswordOne() {
        return passwordOne;
    }

    public void setPasswordOne(String passwordOne) {
        this.passwordOne = passwordOne;
    }

    public String getPasswordTwo() {
        return passwordTwo;
    }

    public void setPasswordTwo(String passwordTwo) {
        this.passwordTwo = passwordTwo;
    }
}
