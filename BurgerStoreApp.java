package com.example.fxdemo;
// BurgerStoreApp.java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
public class BurgerStoreApp extends Application {
    BurgerStore store = new BurgerStore(); // Create BurgerStore object
    @Override
    public void start(Stage primaryStage) {
// Login Screen Components
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Button resetButton = new Button("RESET");
        Label loginMessage = new Label();
// Layout for Login Screen
        GridPane loginPane = new GridPane();
        loginPane.setPadding(new Insets(10));
        loginPane.setHgap(10);
        loginPane.setVgap(10);
        loginPane.add(new Label("Username:"), 0, 0);
        loginPane.add(usernameField, 1, 0);
        loginPane.add(new Label("Password:"), 0, 1);
        loginPane.add(passwordField, 1, 1);
        loginPane.add(resetButton, 0,2);
        loginPane.add(loginButton, 1, 2);
        loginPane.add(loginMessage, 1, 3);
        Scene loginScene = new Scene(loginPane, 400, 200);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Burger Store Login");
        primaryStage.show();
// Login Button Action
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (username.equalsIgnoreCase(store.getUsername()) && password.equals(store.getPassword())) {
                showMenu(primaryStage); // Proceed to menu screen
            } else {
                loginMessage.setText("Incorrect username or password. Try again.");
            }
        });
    }
    // Method to Show Menu Screen
    private void showMenu(Stage stage) {
        GridPane menuPane = new GridPane();
        menuPane.setPadding(new Insets(10));
        menuPane.setHgap(10);
        menuPane.setVgap(10);
// Display menu items
        String[] items = store.getBurgerName();
        double[] prices = {6.99, 9.99, 8.99, 8.99}; // you can fetch from store if you want
        menuPane.add(new Label("Item"), 0, 0);
        menuPane.add(new Label("Price"), 1, 0);
        for (int i = 0; i < items.length; i++) {
            menuPane.add(new Label(items[i]), 0, i + 1);
            menuPane.add(new Label("$" + prices[i]), 1, i + 1);
        }
// Inputs for ordering
        Label orderLabel = new Label("Enter item name:");
        TextField itemField = new TextField();
        Label quantityLabel = new Label("Enter quantity:");
        TextField quantityField = new TextField();
        Button calculateButton = new Button("Calculate Total");
        Button cancelButton = new Button("Cancel");
        Label outputLabel = new Label();
        menuPane.add(orderLabel, 0, items.length + 2);
        menuPane.add(itemField, 1, items.length + 2);
        menuPane.add(quantityLabel, 0, items.length + 3);
        menuPane.add(quantityField, 1, items.length + 3);
        menuPane.add(calculateButton, 1, items.length + 4);
        menuPane.add(cancelButton,0,items.length + 4);
        menuPane.add(outputLabel, 1, items.length + 5);
        Scene menuScene = new Scene(menuPane, 500, 400);
        stage.setScene(menuScene);
// Calculate Button Action
        calculateButton.setOnAction(e -> {
            String itemName = itemField.getText();
            String quantityText = quantityField.getText();
            if (itemName.isEmpty() || quantityText.isEmpty()) {
                outputLabel.setText("Please enter both item name and quantity.");
                return;
            }
            try {
                int quantity = Integer.parseInt(quantityText);
                double cost = store.calculateCost(itemName, quantity);
                if (cost == -1) {
                    outputLabel.setText("Invalid item name. Please choose from the menu.");
                    return;
                }
                double tax = store.calculateTax(cost);
                double total = store.calculateTotal(cost);
                outputLabel.setText(String.format(
                        "Pre-cost: $%.2f\nTax: $%.2f\nTotal Cost: $%.2f",
                        cost, tax, total));
            } catch (NumberFormatException ex) {
                outputLabel.setText("Quantity must be a valid number.");
            }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}
