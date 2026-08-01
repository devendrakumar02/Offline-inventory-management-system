package com.deva.inventory_management.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class BarcodeService {

    public byte[] generateBarcode(String barcode) throws Exception {

        BitMatrix matrix = new MultiFormatWriter().encode(
                barcode,
                BarcodeFormat.CODE_128,
                1000,
                300
        );

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        MatrixToImageWriter.writeToStream(matrix, "PNG", output);

        return output.toByteArray();
    }
}