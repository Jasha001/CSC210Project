package com.example.fxdemo;
public class BurgerStore {
    // First Array for the username and password
    private String[][] usernames = {{"USERNAME", "password"}, {"Wendy", "1234"}};
    // Second array for type of burgers
    private String[] burgersType = {"cheese burger", "double cheese burger", "bacon burger", "turkey bacon burger"};
    //Third array for prices of the burgers
    double[] burgersPrice = {6.99, 9.99, 8.99, 8.99};
    double TAX_RATE = 0.08;
    public String getUsername() {
        return usernames[1][0];
    }
    public String getPassword() {
        return usernames[1][1];
    }
    public String[] getBurgerName() {
        return burgersType;
    }
    public double getBurgerPrice(String burgerName) {
        for (int i = 0; i < burgersType.length; i++) {
            if (burgersType[i].equalsIgnoreCase(burgerName)) {
                return burgersPrice[i];
            }
        }
        return -1; // Not found
    }
    // Method to calculate cost
    public double calculateCost(String burgerName, int quantity) {
        double price = getBurgerPrice(burgerName);
        if (price == -1) {
            return -1;
        }
        return price * quantity;
    }
    // Method to calculate tax
    public double calculateTax(double cost) {
        return cost * TAX_RATE;
    }
    // Method to calculate total cost
    public double calculateTotal(double cost) {
        double tax = calculateTax(cost);
        return cost + tax;
    }
}

