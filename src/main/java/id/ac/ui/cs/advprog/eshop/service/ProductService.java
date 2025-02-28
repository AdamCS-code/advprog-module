package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    Product create(Product product);
    List<Product> findAll();
    Product findById(String productId);
    Product edit(Product product);
    void delete(String productId);
}