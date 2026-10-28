package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

// Starter for Search Inventory Map (Workbook 3a, Section 4-1 Using a HashMap, Exercise 2).
// main is done: it asks for a product id and looks it up in the map.
// Your job is loadInventory(): put products in the map, the product id as the key.
public class SearchInventoryMap {

    public static HashMap<Integer, Product> inventory =
            new HashMap<Integer, Product>();

    public static void main(String[] args) {
        // this method loads product objects into inventory
        loadInventory();
        Scanner scanner = new Scanner(System.in);
        System.out.print("What item # are you interested in? ");
        int id = scanner.nextInt();
        Product matchedProduct = inventory.get(id);
        if (matchedProduct == null) {
            System.out.println("We don't carry that product");
        } else {
            System.out.printf("We carry %s and the price is $%.2f",
                    matchedProduct.getName(), matchedProduct.getPrice());
        }
    }

    private static HashMap<Integer, Product> loadInventory(){
        // Your code should be here
        String fileName = "inventory.csv";
        HashMap<Integer, Product> inventory = new HashMap<Integer, Product>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            // Skip first line
            bufferedReader.readLine();

            String items;

            while ((items = bufferedReader.readLine()) != null){
                String[] tokens = items.split("\\|");
                int id = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                double price = Double.parseDouble(tokens[2]);
                inventory.put(id, new Product(id, name, price));

                bufferedReader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inventory;
    }

}
