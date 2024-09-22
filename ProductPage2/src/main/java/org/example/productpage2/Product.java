package org.example.productpage2;

import java.util.Date;

public class Product {
        private int id;
        private String productName;
        private String productType;
        private String description;
        private String Location;
        private int quantity;
        private Date date;
        private Boolean insured;
        private int price;

        public Product(int id, String productName, String productType, String description, String Location, int quantity, Date date, Boolean insured, int price){
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
        public Product(String productName,String productType,String description,String Location,int quantity,Date date,Boolean insured,int price){
            this.productName = productName;
            this.productType = productType;
            this.description = description;
            this.Location = Location;
            this.quantity = quantity;
            this.date  = date;
            this.insured = insured;
            this.price = price;
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
        public int getProductQuantity(){return quantity;}
        public void setProductQuantity(int Quantity){this.quantity = Quantity;}
        public Date getPurchaseDate(){return date;}
        public void setProductPurchaseDate(Date date){this.date=date;}
        public Boolean getInsured(){return insured;}
        public void setProductInsured(Boolean Insured){this.insured = Insured;}
        public int getPrice(){return price;}
        public void setProductPrice(int price){this.price = price;}
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", description='" + description + '\'' +
                ", location='" + Location + '\'' +
                ", quantity=" + quantity +
                ", purchaseDate=" + date +
                ", insured=" + insured + // Assuming insured is a RadioButton
                ", price=" + price +
                '}';
    }
    }


