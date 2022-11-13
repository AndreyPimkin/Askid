package com.robbit.askid.POJO;

public class Product {
    private String nameProduct;
    private String dateProduct;
    private String price;
    public Product(String nameProduct, String price, String dateProduct) {
        this.nameProduct = nameProduct;
        this.price = price;
        this.dateProduct = dateProduct;}
    public Product() {}
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
