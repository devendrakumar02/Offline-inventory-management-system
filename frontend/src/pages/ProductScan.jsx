import { useState } from "react";
import BarcodeScanner from "../components/BarcodeScanner";
import api from "../services/api";

function ProductScan() {

    const [product, setProduct] = useState(null);

    const handleScan = async (barcode) => {

        try {

            const response = await api.get("/products/barcode/" + barcode);

            setProduct(response.data);

        } catch (error) {

            alert("Product Not Found");

        }

    };

    return (
        <div style={{ padding: "20px" }}>

            <h2>Barcode Scanner</h2>

            <BarcodeScanner onScan={handleScan} />

            <hr />

            {product && (
                <div>

                    <h3>{product.productName}</h3>

                    <p><b>Barcode:</b> {product.barcode}</p>

                    <p><b>Category:</b> {product.category}</p>

                    <p><b>Supplier:</b> {product.supplier}</p>

                    <p><b>Quantity:</b> {product.quantity}</p>

                    <p><b>Selling Price:</b> {product.sellingPrice}</p>

                </div>
            )}

        </div>
    );
}

export default ProductScan;