package id.ac.ui.cs.advprog.eshop.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import id.ac.ui.cs.advprog.eshop.model.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Test Product");
        product.setProductQuantity(100);
    }

    @Test
    void testEditProduct() {
        // Create the product to edit
        when(productRepository.create(product)).thenReturn(product);
        Product createdProduct = productService.create(product);

        // Prepare edited product data
        Product editedProduct = new Product();
        editedProduct.setProductId(createdProduct.getProductId());
        editedProduct.setProductName("Updated Name");
        editedProduct.setProductQuantity(200);

        // Mock repository edit behavior
        when(productRepository.edit(editedProduct)).thenReturn(editedProduct);

        // Perform edit
        Product result = productService.edit(editedProduct);

        // Assert the changes
        assertEquals("Updated Name", result.getProductName());
        assertEquals(200, result.getProductQuantity());
        assertEquals(createdProduct.getProductId(), result.getProductId());
    }

    @Test
    void testDeleteProduct() {
        // Setup initial data
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Iterator<Product> iterator = productList.iterator();

        // Mock repository behavior before delete
        when(productRepository.findAll()).thenReturn(iterator);
        List<Product> beforeDelete = productService.findAll();
        assertEquals(1, beforeDelete.size());

        // Perform delete
        productService.delete(product.getProductId());

        // Mock empty repository after delete
        when(productRepository.findAll()).thenReturn(new ArrayList<Product>().iterator());
        List<Product> afterDelete = productService.findAll();
        assertEquals(0, afterDelete.size());
    }
}