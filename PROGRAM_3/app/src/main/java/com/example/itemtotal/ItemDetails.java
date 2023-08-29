package com.example.itemtotal;

public class ItemDetails {
    private final String itemName;
    public double itemPrice;
    public ItemDetails(String itemName, double itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }
    public String getItemName() {
        return itemName;
    }
    public double getItemPrice() {
        return itemPrice;
    }

}
