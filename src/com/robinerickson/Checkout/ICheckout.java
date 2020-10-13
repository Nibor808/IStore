package com.robinerickson.Checkout;

public interface ICheckout {
    void addItemToCart(String name, int amount);
    void checkOut();
}
