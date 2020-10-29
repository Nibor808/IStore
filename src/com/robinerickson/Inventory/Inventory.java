package com.robinerickson.Inventory;

import com.robinerickson.Store.IStore;
import com.robinerickson.Item;

import java.util.*;

public class Inventory implements IInventory {
    private final Map<String, InventoryItem> inventory = new HashMap<>();
    private final List<String> INVENTORY_PARAMS = new ArrayList<>(Arrays.asList("name", "price", "amount"));
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
    public void update(InventoryItem inventoryItem, String param, Object newVal) {
        if (!this.INVENTORY_PARAMS.contains(param)) {
            System.out.println(param + " is not a valid parameter to update.");
        }

        Item item = inventoryItem.getItem();

        switch (param) {
            case "name":
                item.setName((String)newVal);
                break;
            case "price":
                assert newVal instanceof Double;
                item.setPrice((Double)newVal);
                break;
            case "amount":
                assert newVal instanceof Integer;
                inventoryItem.setNumberInInventory((Integer)newVal);
                break;
            default:
                return;
        }

        try {
            inventory.replace(item.getName(), inventoryItem);
        } catch (Error error) {
            error.printStackTrace();
            return;
        }

        System.out.println("Inventory updated");
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
    public void delete(String item) {
        InventoryItem inventoryItem = inventory.remove(item);

        if (inventoryItem != null) {
            System.out.println(item + " removed from " + this.store.getName() + "'s inventory.");
        } else {
            System.out.println("Cannot remove " + item + ", It is not in inventory.");
        }
    }

    @Override
    public InventoryItem find(String name) {
        return inventory.get(name);
    }
}
