package com.robinerickson.Inventory;

public interface IInventory {
    void save();
    void read();
    void update(InventoryItem inventoryItem, String param, Object newVal);
    boolean add(InventoryItem inventoryItem);
    void delete(String item);
    InventoryItem find(String name);
}
