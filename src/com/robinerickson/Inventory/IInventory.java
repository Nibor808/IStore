package com.robinerickson.Inventory;

public interface IInventory {
    IInventory get();
    void save();
    void read();
    IInventory update(InventoryItem inventoryItem);
    boolean add(InventoryItem inventoryItem);
    IInventory delete(InventoryItem inventoryItem);
    InventoryItem find(String name);
}
