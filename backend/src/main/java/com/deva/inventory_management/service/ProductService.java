package com.deva.inventory_management.service;

import com.deva.inventory_management.entity.Product;
import com.deva.inventory_management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Add Product
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Get All Products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Search by Barcode
    public Product getProductByBarcode(String barcode) {
        return productRepository.findByBarcode(barcode).orElse(null);
    }

    // Search by Product Name
    public Product getProductByName(String productName) {
        return productRepository.findByProductName(productName).orElse(null);
    }

    // Update Product
    public Product updateProduct(Integer id, Product updatedProduct) {

        Product product = productRepository.findById(id).orElse(null);

        if (product != null) {

            product.setBarcode(updatedProduct.getBarcode());
            product.setProductName(updatedProduct.getProductName());
            product.setCategory(updatedProduct.getCategory());
            product.setSupplier(updatedProduct.getSupplier());
            product.setPurchasePrice(updatedProduct.getPurchasePrice());
            product.setSellingPrice(updatedProduct.getSellingPrice());
            product.setQuantity(updatedProduct.getQuantity());
            product.setMinimumStockLevel(updatedProduct.getMinimumStockLevel());

            return productRepository.save(product);
        }

        return null;
    }

    // Delete Product
    public String deleteProduct(Integer id) {

        if (productRepository.existsById(id)) {

            productRepository.deleteById(id);
            return "Product Deleted Successfully";
        }

        return "Product Not Found";
    }
}