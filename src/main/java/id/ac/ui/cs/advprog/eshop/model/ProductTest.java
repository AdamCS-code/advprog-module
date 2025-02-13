package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    Product product;

    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductId("770081a9-e62b-449f-ac9d-f72266bd9e93");
        this.product.setProductName("Adam Caldipawell Sembiring");
        this.product.setProductQuantity(100);
    }

    @Test
    void testGetProductId() {
        assertEquals(product.getProductId(),"770081a9-e62b-449f-ac9d-f72266bd9e93");
    }

    @Test
    void testGetProductName() {
        assertEquals(product.getProductId(), "Adam Caldipawell Sembiring");
    }

    @Test
    void testGetProductQuantity() {
        assertEquals(product.getProductQuantity(), 100);
    }

}