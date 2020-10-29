package com.robinerickson.Checkout;

import com.robinerickson.CalculateTax;
import com.robinerickson.Store.IStore;
import com.robinerickson.Inventory.IInventory;
import com.robinerickson.Inventory.InventoryItem;
import com.robinerickson.Item;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Checkout implements ICheckout {
    private final Map<Item, Integer> cart = new HashMap<>();
    private static double total = 0.00;
    private final IInventory inventory;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private final IStore store;

    public Checkout(IStore store) {
        this.inventory = store.getInventory();
        this.store = store;
    }

    @Override
    public void addItemToCart(String name, int amount) {
        InventoryItem inventoryItem = inventory.find(name);

        if (inventoryItem != null) {
            int amountInInventory = inventoryItem.getNumberInInventory();
            Item item = inventoryItem.getItem();
            int available = inventoryItem.getNumberInInventory();

            if (available < amount) {
                System.out.println("Sorry. Only " + available + " of " + name + " are available");
            } else {
                System.out.println("Adding " + amount + " " + item.getName() + " to cart.");

                cart.put(new Item(item.getName(), item.getPrice()), amount);

                total += item.getPrice() * amount;

                inventoryItem.setNumberInInventory(amountInInventory - amount);
            }
        } else System.out.println(name + " - not available.");
    }



    @Override
    public void checkOut() {
        System.out.println("\nChecking out at the " + store.getName() + " store.");

        if (cart.isEmpty()) System.out.println("There are no items in your cart\n");
        else {
            System.out.println("Checking out " + cart.size() + " items\n");

            for (Map.Entry<Item, Integer> entry : cart.entrySet()) {
                Item item = entry.getKey();
                String subTotal = df.format(item.getPrice() * entry.getValue());

                System.out.println(
                        "Item: " + item.getName() +
                                "\nPrice: $" + item.getPrice() +
                                "\nAmount: " + entry.getValue() +
                                "\ncost: $" + subTotal + "\n"
                );
            }

            double tax = CalculateTax.calculate(total, store.getRegion());

            System.out.println(
                    "\tsub total: $" + df.format(total) +
                            "\n\ttax: $" + df.format(tax) +
                            "\n\tTotal: $" + df.format(total + tax) + "\n"
            );
        }
    }
}
