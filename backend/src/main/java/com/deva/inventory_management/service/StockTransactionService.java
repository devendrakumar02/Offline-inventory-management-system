package com.deva.inventory_management.service;

import com.deva.inventory_management.entity.Product;
import com.deva.inventory_management.entity.StockTransaction;
import com.deva.inventory_management.repository.ProductRepository;
import com.deva.inventory_management.repository.StockTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockTransactionService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockTransactionRepository transactionRepository;

    // Stock In
    public StockTransaction stockIn(String barcode, int quantity) {

        Product product = productRepository.findByBarcode(barcode)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setQuantity(product.getQuantity() + quantity);
        productRepository.save(product);

        StockTransaction transaction = new StockTransaction();
        transaction.setBarcode(product.getBarcode());
        transaction.setProductName(product.getProductName());
        transaction.setQuantity(quantity);
        transaction.setType("STOCK_IN");

        return transactionRepository.save(transaction);
    }

    // Stock Out
    public StockTransaction stockOut(String barcode, int quantity) {

        Product product = productRepository.findByBarcode(barcode)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        StockTransaction transaction = new StockTransaction();
        transaction.setBarcode(product.getBarcode());
        transaction.setProductName(product.getProductName());
        transaction.setQuantity(quantity);
        transaction.setType("STOCK_OUT");

        return transactionRepository.save(transaction);
    }

    // Get all transactions
    public List<StockTransaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}