package com.deva.inventory_management.controller;

import com.deva.inventory_management.entity.StockTransaction;
import com.deva.inventory_management.service.StockTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin("*")
public class StockTransactionController {

    @Autowired
    private StockTransactionService stockTransactionService;

    @PostMapping("/stock-in")
    public StockTransaction stockIn(@RequestBody Map<String, Object> request) {

        String barcode = (String) request.get("barcode");
        int quantity = (Integer) request.get("quantity");

        return stockTransactionService.stockIn(barcode, quantity);
    }

    @PostMapping("/stock-out")
    public StockTransaction stockOut(@RequestBody Map<String, Object> request) {

        String barcode = (String) request.get("barcode");
        int quantity = (Integer) request.get("quantity");

        return stockTransactionService.stockOut(barcode, quantity);
    }

    @GetMapping
    public List<StockTransaction> getAllTransactions() {

        return stockTransactionService.getAllTransactions();

    }
}