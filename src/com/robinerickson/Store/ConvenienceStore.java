package com.robinerickson.Store;

import com.robinerickson.Inventory.IInventory;
import com.robinerickson.Inventory.Inventory;
import com.robinerickson.Inventory.InventoryItem;
import com.robinerickson.Item;
import com.robinerickson.Regions;

public class ConvenienceStore implements IStore {
    private final IInventory inventory;
    private String region;

    public ConvenienceStore() {
        System.out.println("Welcome to the convenience store.");

        this.inventory = new Inventory(this);

        this.saveInventory();

        this.inventory.add(new InventoryItem(new Item("chips", 2.3), 10));
        this.inventory.add(new InventoryItem(new Item("cookies", 4.99), 25));
        this.inventory.add(new InventoryItem(new Item("chocolate bar", 2.5), 100));
    }

    @Override
    public String getName() {
        return "convenience";
    }

    @Override
    public IInventory getInventory() {
        return this.inventory;
    }

    @Override
    public void addItemToInventory(String name, double price, int amount) throws Exception {
        System.out.println("\nAdding " + name + " to " + this.getName() + " store.");
        if (!this.inventory.add(new InventoryItem(new Item(name.toLowerCase(), price), amount))) {
            throw new Exception("Item not added to inventory");
        }

        System.out.println(amount + " of " + name + " added to inventory.\n");
    }

    @Override
    public void printInventory() {
        System.out.println("Convenience Store Inventory");
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
        return region;
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
