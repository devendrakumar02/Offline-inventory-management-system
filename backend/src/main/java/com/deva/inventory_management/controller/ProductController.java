package com.deva.inventory_management.controller;

import com.deva.inventory_management.entity.Product;
import com.deva.inventory_management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/barcode/{barcode}")
    public Product getByBarcode(@PathVariable String barcode) {
        return productService.getProductByBarcode(barcode);
    }

    @GetMapping("/name/{name}")
    public Product getByName(@PathVariable String name) {
        return productService.getProductByName(name);
    }
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Integer id,
                                 @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }
}