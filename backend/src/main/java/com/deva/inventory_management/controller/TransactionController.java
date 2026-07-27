package com.deva.inventory_management.controller;

import com.deva.inventory_management.entity.Transaction;
import com.deva.inventory_management.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Stock In
    @PostMapping("/stock-in")
    public Transaction stockIn(@RequestParam Integer productId,
                               @RequestParam Integer quantity) {

        return transactionService.stockIn(productId, quantity);
    }

    // Stock Out
    @PostMapping("/stock-out")
    public Transaction stockOut(@RequestParam Integer productId,
                                @RequestParam Integer quantity) {

        return transactionService.stockOut(productId, quantity);
    }

    // Get All Transactions
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    // Get Transactions by Product
    @GetMapping("/product/{productId}")
    public List<Transaction> getTransactionsByProduct(@PathVariable Integer productId) {

        return transactionService.getTransactionsByProduct(productId);
    }
}