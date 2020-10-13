package com.robinerickson.Inventory;

import com.robinerickson.Store.IStore;
import com.robinerickson.Item;

import java.util.HashMap;
import java.util.Map;

public class Inventory implements IInventory {
    private final Map<String, InventoryItem> inventory = new HashMap<>();
    private final IStore store;

    public Inventory(IStore store) {
        this.store = store;
    }

    @Override
    public void save() {
        System.out.println("Saving " + store.getName() + " inventory.");
    }

    @Override
    public void read() {

        for (Map.Entry<String, InventoryItem> mapEntry : inventory.entrySet()) {
            Item item = mapEntry.getValue().getItem();
            InventoryItem inventoryItem = mapEntry.getValue();
            
            System.out.println(
                    item.getName() + "\n"
                            + "price: " + item.getPrice() + "\n"
                            + "available: " + inventoryItem.getNumberInInventory() + "\n"
            );
        }
    }

    @Override
    public IInventory update(InventoryItem inventoryItem) {
        Item item = inventoryItem.getItem();

        try {
            inventory.replace(item.getName(), inventoryItem);
        } catch (Error error) {
            error.printStackTrace();
            return null;
        }

        return this;
    }

    @Override
    public boolean add(InventoryItem inventoryItem) {
        Item item = inventoryItem.getItem();

        if (this.find(item.getName()) == null) {
            try {
                inventory.put(item.getName(), inventoryItem);
            } catch (Error error) {
                error.printStackTrace();
                return false;
            }

            return true;
        }

        return false;
    }

    @Override
    public IInventory delete(InventoryItem inventoryItem) {
        Item item = inventoryItem.getItem();

        try {
            inventory.remove(item.getName());
        } catch (Error error) {
            error.printStackTrace();
            return null;
        }

        return this;
    }

    @Override
    public IInventory get() {
        return this;
    }

    @Override
    public InventoryItem find(String name) {
        return inventory.get(name);
    }
}
