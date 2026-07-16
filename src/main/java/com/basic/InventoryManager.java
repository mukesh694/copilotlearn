package com.basic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class InventoryManager {

    private final Map<String, Product> inventory = new HashMap<>();

    /**
     * Adds a new product to the inventory.
     *
     * @param product the product to add, must not be null
     * @throws IllegalArgumentException if the product is null or if a product with the same ID already exists
     */
    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        String id = product.getId();
        if (inventory.containsKey(id)) {
            throw new IllegalArgumentException("Product with ID " + id + " already exists");
        }
        inventory.put(id, product);
    }

    /**
     * Returns the product with the given ID.
     *
     * @param id the product ID, must not be null or blank
     * @return an Optional containing the product if found
     * @throws IllegalArgumentException if the ID is null or blank
     */
    public Optional<Product> getProduct(String id) {
        validateId(id);
        return Optional.ofNullable(inventory.get(id));
    }

    /**
     * Removes a product from the inventory by its ID.
     *
     * @param id the ID of the product to remove, must not be null or blank
     * @return true if the product was removed, false if no product with that ID exists
     * @throws IllegalArgumentException if the ID is null or blank
     */
    public boolean removeProduct(String id) {
        validateId(id);
        return inventory.remove(id) != null;
    }

    /**
     * Lists all products currently in the inventory.
     *
     * @return an unmodifiable list of products in the inventory
     */
    public List<Product> listProducts() {
        return List.copyOf(inventory.values());
    }

    /**
     * Updates the quantity of an existing product.
     *
     * @param id       the product ID, must not be null or blank
     * @param quantity the new quantity, must be non-negative
     * @throws IllegalArgumentException if the ID is null or blank, if the quantity is negative, or if the product does not exist
     */
    public void updateProductQuantity(String id, int quantity) {
        validateId(id);
        if (quantity < 0) {
            throw new IllegalArgumentException("Product quantity cannot be negative");
        }

        Product existingProduct = inventory.get(id);
        if (existingProduct == null) {
            throw new IllegalArgumentException("Product with ID " + id + " does not exist");
        }

        existingProduct.setQuantity(quantity);
        inventory.put(id, existingProduct);
    }

    private static void validateId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Product ID cannot be null or blank");
        }
    }

    //method to test the InventoryManager class
    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();

        // Adding products
        Product product1 = new Product("1", "Product A", 10);
        Product product2 = new Product("2", "Product B", 20);
        inventoryManager.addProduct(product1);
        inventoryManager.addProduct(product2);

        // Listing products
        System.out.println("=== Listing Products ===");
        for (Product product : inventoryManager.listProducts()) {
            System.out.println(product);
        }

        // Updating product quantity
        inventoryManager.updateProductQuantity("1", 15);
        System.out.println("\n=== After Updating Quantity of Product A ===");
        System.out.println(inventoryManager.getProduct("1").orElse(null));

        // Removing a product
        inventoryManager.removeProduct("2");
        System.out.println("\n=== After Removing Product B ===");
        for (Product product : inventoryManager.listProducts()) {
            System.out.println(product);
        }
    }
}
    



