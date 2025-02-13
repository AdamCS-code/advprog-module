package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Product findById(String productId) {
        Product findProduct = null;

        if (productData.isEmpty()) {
            return findProduct;
        }

        for (Product product : productData) {
            if (product.getProductId().equals(productId)) {
                findProduct = product;
            }
        }
        return findProduct;
    }

    public Product edit(Product product) {
        for (int i = 0; i < productData.size(); i++) {
            if (productData.get(i).getProductId().equals(product.getProductId())) {
                productData.set(i, product);
                return product;
            }
        }
        return null;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }
}