package org.example.simplestock.Model;
import org.example.simplestock.Model.Product;

import java.time.DateTimeException;

public class Product {
    private int id;
    private String productName;
    private String productType;
    private String Description;
    private String Location;
    private int Quantity;
    Private timestamp DateTimeException;
    private String insured;
    private int Price;

    public Product(int id,String productName,String productType,String Description,String location,int quantity,private timeStamp,String insured,int price){
        this.id = id;
        this.productName = productName;
        this.productType = productType;
        this.Description = Description;
        this.Location = Location;
        this.Quantity = Quantity;
        this.timestamp = timeStamp;
        this.insured = insured;
        this.Price = Price;
    }
    public int getId(){
        return id;
    }
    public String getProductName(){
        return productName;
    }

}
