package com.robbit.askid.POJO;

public class ForAcc {
    private String idClient;
    private String fullName;
    private String numberTreaty;
    private String dateTreaty;
    private String status;
    private String nameProduct;
    public ForAcc(String idClient, String fullName, String numberTreaty, String dateTreaty, String nameProduct) {
        this.idClient = idClient;
        this.fullName = fullName;
        this.numberTreaty = numberTreaty;
        this.dateTreaty = dateTreaty;
        this.nameProduct = nameProduct;}
    public ForAcc() {}
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getIdClient() {
        return idClient;
    }
    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getNumberTreaty() {
        return numberTreaty;
    }
    public void setNumberTreaty(String numberTreaty) {
        this.numberTreaty = numberTreaty;
    }
    public String getDateTreaty() {
        return dateTreaty;
    }
    public void setDateTreaty(String dateTreaty) {
        this.dateTreaty = dateTreaty;
    }
    public String getNameProduct() {
        return nameProduct;
    }
    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }}
