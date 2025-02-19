package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    private ProductRepository productRepository;
    private Product product;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
        product = new Product();
        product.setProductId("123e4567-e89b-12d3-a456-556642440000");
        product.setProductName("Product 1");
        product.setProductQuantity(100);
        productRepository.create(product);
    }

    @Test
    void testCreateAndFind() {
        Product newProduct = new Product();
        newProduct.setProductId("123e4567-e89b-12d3-a456-556642440001");
        newProduct.setProductName("Product 2");
        newProduct.setProductQuantity(100);
        productRepository.create(newProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        productIterator.next();

        Product savedProduct = productIterator.next();
        assertEquals(newProduct.getProductId(), savedProduct.getProductId());
        assertEquals(newProduct.getProductName(), savedProduct.getProductName());
        assertEquals(newProduct.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        productRepository = new ProductRepository();
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product2 = new Product();
        product2.setProductId("123e4567-e89b-12d3-a456-556642440001");
        product2.setProductName("Product 2");
        product2.setProductQuantity(200);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product firstProduct = productIterator.next();
        assertEquals(product.getProductId(), firstProduct.getProductId());

        assertTrue(productIterator.hasNext());
        Product secondProduct = productIterator.next();
        assertEquals(product2.getProductId(), secondProduct.getProductId());

        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindById() {
        Product foundProduct = productRepository.findById(product.getProductId());
        assertNotNull(foundProduct);
        assertEquals(product.getProductId(), foundProduct.getProductId());
    }

    @Test
    void testEditProduct() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId(product.getProductId());
        updatedProduct.setProductName("Updated Name");
        updatedProduct.setProductQuantity(200);

        Product result = productRepository.edit(updatedProduct);
        assertNotNull(result);
        assertEquals("Updated Name", result.getProductName());
        assertEquals(200, result.getProductQuantity());

        Product foundProduct = productRepository.findById(product.getProductId());
        assertEquals("Updated Name", foundProduct.getProductName());
        assertEquals(200, foundProduct.getProductQuantity());
    }

    @Test
    void testEditNonExistentProduct() {
        Product nonExistentProduct = new Product();
        nonExistentProduct.setProductId("non-existent-id");
        nonExistentProduct.setProductName("Non-existent Product");
        nonExistentProduct.setProductQuantity(50);

        Product result = productRepository.edit(nonExistentProduct);
        assertNull(result);

        // Verify the repository wasn't modified
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        assertEquals(1, countIteratorItems(productIterator));

        // Verify original product remains unchanged
        Product originalProduct = productRepository.findById(product.getProductId());
        assertEquals(product.getProductName(), originalProduct.getProductName());
        assertEquals(product.getProductQuantity(), originalProduct.getProductQuantity());
    }

    private int countIteratorItems(Iterator<?> iterator) {
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        return count;
    }

    @Test
    void testDeleteProduct() {
        productRepository.delete(product.getProductId());
        assertNull(productRepository.findById(product.getProductId()));
    }

    @Test
    void testRemoveIfCondition() {
        productRepository.delete(product.getProductId());
        Iterator<Product> iterator = productRepository.findAll();
        assertFalse(iterator.hasNext());
    }
}