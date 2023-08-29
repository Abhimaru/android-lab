package com.example.android_lab.P3;

public class ItemDetails_p3 {
    private final String itemName;
    public double itemPrice;
    public ItemDetails_p3(String itemName, double itemPrice) {
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
