package com.example.fxdemo;
public class BurgerstoreMains  {
    public static void main(String[] args) {
        BurgerStore store = new BurgerStore();
        System.out.println("Username: " + store.getUsername());
        System.out.println("Password: " + store.getPassword());
        String burger = "cheese burger";
        int quantity = 2;
        double cost = store.calculateCost(burger, quantity);
        double tax = store.calculateTax(cost);
        double total = store.calculateTotal(cost);
        System.out.println("Burger: " + burger);
        System.out.println("Cost: $" + cost);
        System.out.println("Tax: $" + tax);
        System.out.println("Total: $" + total);
    }
}
