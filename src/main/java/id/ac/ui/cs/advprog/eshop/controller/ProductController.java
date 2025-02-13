package id.ac.ui.cs.advprog.eshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String getHome(Model model) {
        return "index";
    }

    @GetMapping("/create")
    public String createProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute Product product, Model model) {
        productService.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String listProduct(Model model) {
        model.addAttribute("products", productService.findAll());
        return "productList";
    }

    @GetMapping("/edit/{id}")
    public String updateProduct(@PathVariable("id") String id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "EditProduct";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable("id") String id, @ModelAttribute Product product, Model model) {
        product.setProductId(id);
        productService.edit(product);
        System.out.print(product.getProductName() + " ");
        System.out.print(product.getProductId() + " ");
        System.out.print(product.getProductQuantity() + " ");
        return "redirect:/product/list";
    }
}