package com.robinerickson;

import com.robinerickson.Checkout.Checkout;
import com.robinerickson.Checkout.ICheckout;
import com.robinerickson.Store.IStore;

public class Main {

    public static void main(String[] args) throws Exception {
	    IStore groceryStore = StoreFactory.getStore("grocery");

        groceryStore.setRegion("NWT");

        groceryStore.printInventory();

        ICheckout groceryCheckout = new Checkout(groceryStore);
        groceryCheckout.addItemToCart("apple", 4);
        groceryCheckout.addItemToCart("banana", 6);
        groceryCheckout.addItemToCart("chips", 1);
        groceryCheckout.addItemToCart("cereal", 2);
        groceryStore.addItemToInventory("chips", 3.99, 30);

        groceryCheckout.addItemToCart("chips", 1);

        groceryCheckout.checkOut();

	   IStore convenienceStore = StoreFactory.getStore("convenience");
	   convenienceStore.setRegion("ON");
	   convenienceStore.printInventory();

	   ICheckout convenienceStoreCheckout = new Checkout(convenienceStore);
	   convenienceStoreCheckout.addItemToCart("hamburger", 1);
	   convenienceStoreCheckout.addItemToCart("chips", 3);
	   convenienceStoreCheckout.addItemToCart("chocolate bar", 5);
	   convenienceStoreCheckout.addItemToCart("cookies", 12);

	   convenienceStore.addItemToInventory("hamburger", 8.00, 10);

	   convenienceStore.printInventory();

	   convenienceStoreCheckout.checkOut();
    }
}
