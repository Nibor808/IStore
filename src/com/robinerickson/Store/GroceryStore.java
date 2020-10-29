package com.robinerickson.Store;

import com.robinerickson.Inventory.IInventory;
import com.robinerickson.Inventory.Inventory;
import com.robinerickson.Inventory.InventoryItem;
import com.robinerickson.Item;
import com.robinerickson.Regions;

public class GroceryStore implements IStore {
    private final IInventory inventory;
    private String region;

    public GroceryStore() {
        System.out.println("Welcome to the grocery store.");

        this.inventory = new Inventory(this);

        this.saveInventory();

        this.inventory.add(new InventoryItem(new Item("apple", 1.2), 50));
        this.inventory.add(new InventoryItem(new Item("banana", 0.75), 25));
        this.inventory.add(new InventoryItem(new Item("cereal", 4.5), 100));
        this.inventory.add(new InventoryItem(new Item("bread", 3.0), 10));
    }

    @Override
    public String getName() {
        return "grocery";
    }

    @Override
    public IInventory getInventory() {
        return this.inventory;
    }

    @Override
    public void addItemToInventory(String name, double price, int amount) throws Exception {
        System.out.println("\nAdding " + name + " to " + this.getName() + " store.");
        if (!this.inventory.add(new InventoryItem(new Item(name.toLowerCase(), price), amount))) {
            throw new Exception("Item not added to inventory\n");
        }

        System.out.println(amount + " of " + name + " added to inventory.\n");
    }

    @Override
    public void printInventory() {
        System.out.println("Grocery Store Inventory");
        this.inventory.read();
    }

    private void saveInventory() {
        this.inventory.save();
    }

    public void updateInventory(String name, String param, Object newVal) {
        InventoryItem inventoryItem = this.inventory.find(name);

        if (inventoryItem != null) {
            this.inventory.update(inventoryItem, param, newVal);
        }
    }

    public void deleteFromInventory(String item) {
        this.inventory.delete(item);
    }

    @Override
    public String getRegion() {
        return this.region;
    }

    @Override
    public void setRegion(String region) throws Exception {
        try {
            Regions.valueOf(region);
        } catch (IllegalArgumentException e) {
            throw new Exception(region + " is not a valid region.");
        }

        this.region = region;
    }

}
