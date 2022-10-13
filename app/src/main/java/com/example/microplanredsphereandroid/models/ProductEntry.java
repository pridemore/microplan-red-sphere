package com.example.microplanredsphereandroid.models;

public class ProductEntry implements Comparable<ProductEntry> {
    private Product product;
    private int quantity;

    public ProductEntry(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductEntry(Product product) {
        this.product = product;
        this.quantity = 0;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int compareTo(ProductEntry o) {
        return this.product.name.compareTo(o.product.name);
    }
}
