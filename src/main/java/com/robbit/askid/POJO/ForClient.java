package com.robbit.askid.POJO;

public class ForClient {
    private String login;
    private String passwordOne;
    private String passwordTwo;

    private String name;
    private String surname;
    private String patronymic;
    private String date;

    private String typeDoc;
    private String numberDoc;
    private String dateDoc;

    private String typeCon;

    private String numberCon;
    private String linkCon;
    private String idDoc;
    private String idClient;

    public String getLinkCon() {
        return linkCon;
    }

    public void setLinkCon(String linkCon) {
        this.linkCon = linkCon;
    }

    public String getTypeCon() {
        return typeCon;
    }

    public void setTypeCon(String typeCon) {
        this.typeCon = typeCon;
    }

    public String getNumberCon() {
        return numberCon;
    }

    public void setNumberCon(String numberCon) {
        this.numberCon = numberCon;
    }

    public String getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(String typeDoc) {
        this.typeDoc = typeDoc;
    }

    public String getNumberDoc() {
        return numberDoc;
    }

    public void setNumberDoc(String numberDoc) {
        this.numberDoc = numberDoc;
    }

    public String getDateDoc() {
        return dateDoc;
    }

    public void setDateDoc(String dateDoc) {
        this.dateDoc = dateDoc;
    }

    public String getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(String idDoc) {
        this.idDoc = idDoc;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public ForClient(String login, String passwordOne, String passwordTwo, String name, String surname, String patronymic, String date, String typeDoc, String numberDoc, String dateDoc, String idDoc, String idClient) {
        this.login = login;
        this.passwordOne = passwordOne;
        this.passwordTwo = passwordTwo;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.date = date;
        this.typeDoc = typeDoc;
        this.numberDoc = numberDoc;
        this.dateDoc = dateDoc;
        this.idDoc = idDoc;
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ForClient(String login, String passwordOne, String passwordTwo, String name, String surname, String patronymic, String date) {
        this.login = login;
        this.passwordOne = passwordOne;
        this.passwordTwo = passwordTwo;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.date = date;
    }

    public ForClient(String login, String passwordOne, String passwordTwo) {
        this.login = login;
        this.passwordOne = passwordOne;
        this.passwordTwo = passwordTwo;
    }

    public ForClient() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
