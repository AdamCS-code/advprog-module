package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.Mockbean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {

    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("770081a9-e62b-449f-ac9d-f72266bd9e93");
        product.setProductName("Sampo Bang Madun");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());
        Product foundProduct = products.next();
        assertEquals(product.getProductId(), foundProduct.getProductId());
        assertEquals(product.getProductName(), foundProduct.getProductName());
        assertEquals(product.getProductQuantity(), foundProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        Product product2 = new Product();

        product1.setProductId("770081a9-e62b-449f-ac9d-f72266bd9e93");
        product1.setProductName("Sampo Bang Madun");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        product2.setProductId("880080aa-e72b-409f-cc89-f72266bd9e93");
        product2.setProductName("Sampo Bang Mamat");
        product2.setProductQuantity(200);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }
}