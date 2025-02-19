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
    void testFindById() {
        String productId = product.getProductId();
        when(productRepository.findById(productId)).thenReturn(product);

        Product foundProduct = productService.findById(productId);

        assertNotNull(foundProduct);
        assertEquals(productId, foundProduct.getProductId());
    }


    @Test
    void testEditProduct() {
        when(productRepository.create(product)).thenReturn(product);
        Product createdProduct = productService.create(product);

        Product editedProduct = new Product();
        editedProduct.setProductId(createdProduct.getProductId());
        editedProduct.setProductName("Updated Name");
        editedProduct.setProductQuantity(200);

        when(productRepository.edit(editedProduct)).thenReturn(editedProduct);

        Product result = productService.edit(editedProduct);

        assertEquals("Updated Name", result.getProductName());
        assertEquals(200, result.getProductQuantity());
        assertEquals(createdProduct.getProductId(), result.getProductId());
    }

    @Test
    void testDeleteProduct() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Iterator<Product> iterator = productList.iterator();

        when(productRepository.findAll()).thenReturn(iterator);
        List<Product> beforeDelete = productService.findAll();
        assertEquals(1, beforeDelete.size());

        productService.delete(product.getProductId());

        when(productRepository.findAll()).thenReturn(new ArrayList<Product>().iterator());
        List<Product> afterDelete = productService.findAll();
        assertEquals(0, afterDelete.size());
    }
}