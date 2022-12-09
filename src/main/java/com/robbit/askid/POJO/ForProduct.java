package com.robbit.askid.POJO;

public class ForProduct {
    private String nameProduct;
    private String dateProduct;
    private String price;


    private String idProductColumn;
    private String priceProductColumn;
    private String nameProductColumn;
    private String dateProductColumn;




    public ForProduct(String nameProduct, String price, String dateProduct) {
        this.nameProduct = nameProduct;
        this.price = price;
        this.dateProduct = dateProduct;}
    public ForProduct() {}

    public ForProduct(String idProductColumn, String priceProductColumn, String nameProductColumn, String dateProductColumn) {
        this.idProductColumn = idProductColumn;
        this.priceProductColumn = priceProductColumn;
        this.nameProductColumn = nameProductColumn;
        this.dateProductColumn = dateProductColumn;
    }

    public String getIdProductColumn() {
        return idProductColumn;
    }

    public void setIdProductColumn(String idProductColumn) {
        this.idProductColumn = idProductColumn;
    }

    public String getPriceProductColumn() {
        return priceProductColumn;
    }

    public void setPriceProductColumn(String priceProductColumn) {
        this.priceProductColumn = priceProductColumn;
    }

    public String getNameProductColumn() {
        return nameProductColumn;
    }

    public void setNameProductColumn(String nameProductColumn) {
        this.nameProductColumn = nameProductColumn;
    }

    public String getDateProductColumn() {
        return dateProductColumn;
    }

    public void setDateProductColumn(String dateProductColumn) {
        this.dateProductColumn = dateProductColumn;
    }

    public String getNameProduct() {
        return nameProduct;
    }
    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }
    public String getDateProduct() {
        return dateProduct;
    }
    public void setDateProduct(String dateProduct) {
        this.dateProduct = dateProduct;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }}
