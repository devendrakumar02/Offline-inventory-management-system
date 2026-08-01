package com.deva.inventory_management.controller;

import com.deva.inventory_management.service.BarcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/barcode")
@CrossOrigin("*")
public class BarcodeController {

    @Autowired
    private BarcodeService barcodeService;

    @GetMapping(value = "/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] generateBarcode(@PathVariable String barcode) throws Exception {

        return barcodeService.generateBarcode(barcode);

    }
}