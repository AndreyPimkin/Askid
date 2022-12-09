package com.robbit.askid.POJO;

public class ForTreaty {
    private String statusTreaty;
    private String numberTreaty;
    private String nameTreaty;
    private String dateTreaty;
    private String dateTreatyTo;
    public String getDateTreatyTo() {return dateTreatyTo;}
    public void setDateTreatyTo(String dateTreatyTo) {
        this.dateTreatyTo = dateTreatyTo;
    }
    public ForTreaty(String numberTreaty, String nameTreaty, String dateTreaty, String dateTreatyTo, String statusTreaty) {
        this.statusTreaty = statusTreaty;
        this.dateTreatyTo = dateTreatyTo;
        this.numberTreaty = numberTreaty;
        this.nameTreaty = nameTreaty;
        this.dateTreaty = dateTreaty;}
    public String getNameTreaty() {
        return nameTreaty;
    }
    public void setNameTreaty(String nameTreaty) {
        this.nameTreaty = nameTreaty;
    }
    public String getNumberTreaty() {
        return numberTreaty;
    }
    public void setNumberTreaty(String numberTreaty) {
        this.numberTreaty = numberTreaty;
    }
    public String getStatusTreaty() {
        return statusTreaty;
    }
    public void setStatusTreaty(String statusTreaty) {
        this.statusTreaty = statusTreaty;
    }
    public String getDateTreaty() {
        return dateTreaty;
    }
    public void setDateTreaty(String dateTreaty) {
        this.dateTreaty = dateTreaty;
    }
    public ForTreaty() {}}
