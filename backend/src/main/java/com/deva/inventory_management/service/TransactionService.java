package com.deva.inventory_management.service;

import com.deva.inventory_management.entity.Product;
import com.deva.inventory_management.entity.Transaction;
import com.deva.inventory_management.repository.ProductRepository;
import com.deva.inventory_management.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ProductRepository productRepository;

    // Stock In
    public Transaction stockIn(Integer productId, Integer quantity) {

        Product product = productRepository.findById(productId).orElse(null);

        if (product == null) {
            return null;
        }

        product.setQuantity(product.getQuantity() + quantity);
        productRepository.save(product);

        Transaction transaction = new Transaction();
        transaction.setProduct(product);
        transaction.setTransactionType("STOCK_IN");
        transaction.setQuantity(quantity);
        transaction.setTransactionDate(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

    // Stock Out
    public Transaction stockOut(Integer productId, Integer quantity) {

        Product product = productRepository.findById(productId).orElse(null);

        if (product == null) {
            return null;
        }

        if (product.getQuantity() < quantity) {
            return null;
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        Transaction transaction = new Transaction();
        transaction.setProduct(product);
        transaction.setTransactionType("STOCK_OUT");
        transaction.setQuantity(quantity);
        transaction.setTransactionDate(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

    // Transaction History
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // Product Transaction History
    public List<Transaction> getTransactionsByProduct(Integer productId) {
        return transactionRepository.findByProductId(productId);
    }
}