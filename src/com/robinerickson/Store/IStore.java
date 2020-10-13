package com.robinerickson.Store;

import com.robinerickson.Inventory.IInventory;

public interface IStore {
    String getName();
    IInventory getInventory();
    void printInventory();
    void addItemToInventory(String name, double price, int amount) throws Exception;
    IInventory updateInventory(String name, String parameter, Object newVal);
    String getRegion();
    void setRegion(String region) throws Exception;
}
