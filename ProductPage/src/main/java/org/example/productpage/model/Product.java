package org.example.productpage.model;

import java.util.Date;

public class Product {
    private int id;
    private String productName;
    private String productType;
    private String description;
    private String Location;
    private int quantity;
    private Date date;
    private String insured;
    private int price;

    public Product(int id,String productName,String productType,String description,String Location,int quantity,Date date,String insured,int price){
        this.id = id;
        this.productName = productName;
        this.productType = productType;
        this.description = description;
        this.Location = Location;
        this.quantity = quantity;
        this.date  = date;
        this.insured = insured;
        this.price = price;
    }
    public Product(String productName,String productType,String description,String Location,int quantity,Date date,String insured,int price){
        this.productName = productName;
        this.productType = productType;
        this.description = description;
        this.Location = Location;
        this.quantity = quantity;
        this.date  = date;
        this.insured = insured;
        this.price = price;
    }

    public Product(String defaultProductName, String defaultProductType, String defaultProductDescription, String defaultProductLocation, Integer defaultProductQuantity, Date defaultProductDate, String defaultProductInsured, Integer defaultProductPrice) {
    }

    public int getId(){return id;}
    public void setId(int id){this.id = id;}
    public String getProductName(){return productName;}
    public void setProductName(String productName){this.productName = productName;}
    public String getProductType(){return productType;}
    public void setProductType(String productType){this.productType = productType;}
    public String getProductDescription(){return description;};
    public void setProductDescription(String description){this.description = description;}
    public String getProductLocation(){return Location;}
    public void setProductLocation(String location){this.Location = location;}
    public Integer getQuantity(){return quantity;}
    public void setQuantity(int Quantity){this.quantity = Quantity;}
    public String getInsured(){return insured;}
    public void setInsured(String Insured){this.insured = Insured;}
    public int getPrice(){return price;}
    public void setPrice(int price){this.price = price;}
}
