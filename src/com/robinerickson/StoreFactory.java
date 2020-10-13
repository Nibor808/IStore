package com.robinerickson;

import com.robinerickson.Store.ConvenienceStore;
import com.robinerickson.Store.GroceryStore;
import com.robinerickson.Store.IStore;

public class StoreFactory {
    public static IStore getStore(String type) throws Exception {
        if (type.equalsIgnoreCase("convenience")) return new ConvenienceStore();
        else if (type.equalsIgnoreCase("grocery")) return new GroceryStore();
        else throw new Exception(type + " store cannot be created");
    }
}
