package com.example.microplanredsphereandroid.models;

public class SaleItem {
    double itemPrice;
    String productName;
    int productQuantity;

    public SaleItem() {
    }

    public SaleItem(double itemPrice, String productName, int productQuantity) {
        this.itemPrice = itemPrice;
        this.productName = productName;
        this.productQuantity = productQuantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
