import { useState } from "react";
import BarcodeScanner from "../components/BarcodeScanner";
import api from "../services/api";

function ProductScan() {

    const [product, setProduct] = useState(null);

    async function handleScan(barcode) {

        console.log("Barcode:", barcode);

        try {

            const response = await api.get("/products/barcode/" + barcode);

            setProduct(response.data);

        } catch (err) {

            alert("Product Not Found");

            setProduct(null);

        }

    }

    return (

        <div style={{ padding: "20px" }}>

            <h1>Offline Smart Inventory Management</h1>

            <BarcodeScanner onScan={handleScan} />

            <hr />

            {product && (

                <div>

                    <h2>Product Details</h2>

                    <p><b>Barcode:</b> {product.barcode}</p>

                    <p><b>Name:</b> {product.productName}</p>

                    <p><b>Category:</b> {product.category}</p>

                    <p><b>Supplier:</b> {product.supplier}</p>

                    <p><b>Quantity:</b> {product.quantity}</p>

                    <p><b>Selling Price:</b> ₹{product.sellingPrice}</p>

                </div>

            )}

        </div>

    );

}

export default ProductScan;