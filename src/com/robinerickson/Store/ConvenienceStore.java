package com.robinerickson.Store;

import com.robinerickson.Inventory.IInventory;
import com.robinerickson.Inventory.Inventory;
import com.robinerickson.Inventory.InventoryItem;
import com.robinerickson.Item;
import com.robinerickson.Regions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvenienceStore implements IStore {
    private final IInventory inventory;
    private final List<String> INVENTORY_PARAMS = new ArrayList<>(Arrays.asList("name", "price", "amount"));
    private String region;

    public ConvenienceStore() {
        System.out.println("Welcome to the convenience store.");

        inventory = new Inventory(this);

        this.saveInventory();

        inventory.add(new InventoryItem(new Item("chips", 2.3), 10));
        inventory.add(new InventoryItem(new Item("cookies", 4.99), 25));
        inventory.add(new InventoryItem(new Item("chocolate bar", 2.5), 100));
    }

    @Override
    public String getName() {
        return "convenience";
    }

    @Override
    public IInventory getInventory() {
        return inventory;
    }

    @Override
    public void addItemToInventory(String name, double price, int amount) throws Exception {
        System.out.println("\nAdding " + name + " to " + this.getName() + " store.");
        if (!inventory.add(new InventoryItem(new Item(name.toLowerCase(), price), amount))) {
            throw new Exception("Item not added to inventory");
        }

        System.out.println(amount + " of " + name + " added to inventory.\n");
    }

    @Override
    public void printInventory() {
        System.out.println("Convenience Store Inventory");
        inventory.read();
    }

    private void saveInventory() {
        inventory.save();
    }

    public IInventory updateInventory(String name, String param, Object newVal) {
        if (!INVENTORY_PARAMS.contains(param)) {
            System.out.println(param + " is not a valid parameter to update.");
            return null;
        }

        InventoryItem inventoryItem = inventory.find(name);

        if (inventoryItem != null) {
            Item item = inventoryItem.getItem();

            switch (param) {
                case "name":
                    item.setName((String)newVal);
                case "price":
                    assert newVal instanceof Double;
                    item.setPrice((Double)newVal);
                case "amount":
                    inventoryItem.setNumberInInventory((Integer)newVal);
            }

            return inventory;
        }

        return null;
    }

    public IInventory addToInventory(InventoryItem inventoryItem) {
        return null;
    }

    public IInventory deleteFromInventory(InventoryItem inventoryItem) {
        return null;
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
