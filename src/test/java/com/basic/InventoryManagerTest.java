package com.basic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryManagerTest {

    private InventoryManager inventoryManager;

    @BeforeEach
    void setUp() {
        inventoryManager = new InventoryManager();
    }

    @Test
    void addProduct_withValidProduct_addsProduct() {
        Product product = new Product("1", "Product A", 10);

        inventoryManager.addProduct(product);

        assertEquals(1, inventoryManager.listProducts().size());
        assertEquals(product, inventoryManager.getProduct("1").orElse(null));
    }

    @Test
    void addProduct_withNullProduct_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> inventoryManager.addProduct(null));
        assertEquals("Product cannot be null", exception.getMessage());
    }

    @Test
    void addProduct_withDuplicateId_throwsIllegalArgumentException() {
        Product product = new Product("1", "Product A", 10);
        inventoryManager.addProduct(product);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> inventoryManager.addProduct(new Product("1", "Product B", 5)));
        assertEquals("Product with ID 1 already exists", exception.getMessage());
    }

    @Test
    void getProduct_withExistingId_returnsProduct() {
        Product product = new Product("1", "Product A", 10);
        inventoryManager.addProduct(product);

        Optional<Product> result = inventoryManager.getProduct("1");

        assertTrue(result.isPresent());
        assertEquals(product, result.get());
    }

    @Test
    void getProduct_withNonExistingId_returnsEmptyOptional() {
        Optional<Product> result = inventoryManager.getProduct("missing");

        assertFalse(result.isPresent());
    }

    @Test
    void getProduct_withNullId_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> inventoryManager.getProduct(null));
        assertEquals("Product ID cannot be null or blank", exception.getMessage());
    }

    @Test
    void removeProduct_withExistingId_returnsTrueAndRemovesProduct() {
        Product product = new Product("1", "Product A", 10);
        inventoryManager.addProduct(product);

        boolean removed = inventoryManager.removeProduct("1");

        assertTrue(removed);
        assertFalse(inventoryManager.getProduct("1").isPresent());
        assertTrue(inventoryManager.listProducts().isEmpty());
    }

    @Test
    void removeProduct_withNonExistingId_returnsFalse() {
        boolean removed = inventoryManager.removeProduct("missing");

        assertFalse(removed);
    }

    @Test
    void removeProduct_withBlankId_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> inventoryManager.removeProduct("  "));
        assertEquals("Product ID cannot be null or blank", exception.getMessage());
    }

    @Test
    void listProducts_returnsUnmodifiableList() {
        Product product = new Product("1", "Product A", 10);
        inventoryManager.addProduct(product);

        List<Product> products = inventoryManager.listProducts();

        assertThrows(UnsupportedOperationException.class, () -> products.add(new Product("2", "Product B", 5)));
    }

    @Test
    void updateProductQuantity_withValidId_updatesQuantity() {
        Product product = new Product("1", "Product A", 10);
        inventoryManager.addProduct(product);

        inventoryManager.updateProductQuantity("1", 15);

        assertEquals(15, inventoryManager.getProduct("1").orElseThrow().getQuantity());
    }

    @Test
    void updateProductQuantity_withNegativeQuantity_throwsIllegalArgumentException() {
        Product product = new Product("1", "Product A", 10);
        inventoryManager.addProduct(product);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> inventoryManager.updateProductQuantity("1", -5));
        assertEquals("Product quantity cannot be negative", exception.getMessage());
    }

    @Test
    void updateProductQuantity_withNonExistingId_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> inventoryManager.updateProductQuantity("missing", 5));
        assertEquals("Product with ID missing does not exist", exception.getMessage());
    }

    @Test
    void updateProductQuantity_withBlankId_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> inventoryManager.updateProductQuantity("  ", 5));
        assertEquals("Product ID cannot be null or blank", exception.getMessage());
    }
}