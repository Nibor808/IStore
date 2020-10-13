package com.robinerickson.Inventory;

import com.robinerickson.Item;

public class InventoryItem {
    private int numberInInventory = 0;
    private final Item item;

    public InventoryItem(Item item, int numberInInventory) {
        this.numberInInventory = numberInInventory;
        this.item = item;
    }

    public int getNumberInInventory() {
        return numberInInventory;
    }

    public void setNumberInInventory(int numberInInventory) {
        this.numberInInventory = numberInInventory;
    }

    public Item getItem() {
        return item;
    }
}
