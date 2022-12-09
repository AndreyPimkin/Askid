package com.robbit.askid.POJO;

public class ForAdmin {
    private String login;
    private String passwordAdmin;
    private String nameAcc;
    private String surnameAcc;
    private String patronymicAcc;
    private String idClientColumn;
    private String fullNameColumn;
    private String activityColumn;
    private String loginClient;
    private String idAccColumn;
    private String fullName;
    private String numberColumn;
    private String loginColumn;
    private String passwordColumn;
    private String idTreatyColumn;
    private String dateFrom;
    private String dateTo;
    private String idClient;
    private String idProduct;
    private String status;
    public ForAdmin(String idClientColumn, String fullNameColumn, String activityColumn, String loginClient) {
        this.idClientColumn = idClientColumn;
        this.fullNameColumn = fullNameColumn;
        this.activityColumn = activityColumn;
        this.loginClient = loginClient;}
    public ForAdmin(String idTreatyColumn, String dateFrom, String dateTo, String idClient, String idProduct, String status) {
        this.idTreatyColumn = idTreatyColumn;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.status = status;}
    public String getIdTreatyColumn() {return idTreatyColumn;}
    public void setIdTreatyColumn(String idTreatyColumn) {this.idTreatyColumn = idTreatyColumn;}
    public String getDateFrom() {return dateFrom;}
    public void setDateFrom(String dateFrom) {this.dateFrom = dateFrom;}
    public String getDateTo() {return dateTo;}
    public void setDateTo(String dateTo) {this.dateTo = dateTo;}
    public String getIdClient() {return idClient;}
    public void setIdClient(String idClient) {this.idClient = idClient;}
    public String getIdProduct() {return idProduct;}
    public void setIdProduct(String idProduct) {this.idProduct = idProduct;}
    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
    public String getIdClientColumn() {
        return idClientColumn;
    }
    public void setIdClientColumn(String idClientColumn) {
        this.idClientColumn = idClientColumn;
    }
    public String getFullNameColumn() {
        return fullNameColumn;
    }
    public void setFullNameColumn(String fullNameColumn) {
        this.fullNameColumn = fullNameColumn;
    }
    public String getActivityColumn() {
        return activityColumn;
    }
    public void setActivityColumn(String activityColumn) {
        this.activityColumn = activityColumn;
    }
    public String getLoginClient() {
        return loginClient;
    }
    public void setLoginClient(String loginClient) {
        this.loginClient = loginClient;
    }
    public String getNameAcc() {
        return nameAcc;
    }
    public void setNameAcc(String nameAcc) {
        this.nameAcc = nameAcc;
    }
    public String getSurnameAcc() {
        return surnameAcc;
    }
    public void setSurnameAcc(String surnameAcc) {
        this.surnameAcc = surnameAcc;
    }
    public String getPatronymicAcc() {
        return patronymicAcc;
    }
    public void setPatronymicAcc(String patronymicAcc) {
        this.patronymicAcc = patronymicAcc;
    }
    public String getIdAccColumn() {return idAccColumn;}
    public void setIdAccColumn(String idAccColumn) {this.idAccColumn = idAccColumn;}
    public String getFullName() {return fullName;}
    public void setFullName(String fullName) {this.fullName = fullName;}
    public String getNumberColumn() {return numberColumn;}
    public void setNumberColumn(String numberColumn) {this.numberColumn = numberColumn;}
    public String getLoginColumn() {return loginColumn;}
    public void setLoginColumn(String loginColumn) {this.loginColumn = loginColumn;}
    public String getPasswordColumn() {return passwordColumn;}
    public void setPasswordColumn(String passwordColumn) {this.passwordColumn = passwordColumn;}
    public String getLogin() {return login;}
    public void setLogin(String login) {this.login = login;}
    public String getPasswordAdmin() {return passwordAdmin;}
    public void setPasswordAdmin(String passwordAdmin) {this.passwordAdmin = passwordAdmin;}
    public ForAdmin() {}

    public ForAdmin(String idAccColumn, String fullName, String numberColumn, String loginColumn, String passwordColumn) {
        this.idAccColumn = idAccColumn;
        this.fullName = fullName;
        this.numberColumn = numberColumn;
        this.loginColumn = loginColumn;
        this.passwordColumn = passwordColumn;}}
