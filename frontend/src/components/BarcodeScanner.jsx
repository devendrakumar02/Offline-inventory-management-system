import { useZxing } from "react-zxing";

function BarcodeScanner({ onScan }) {

    const { ref } = useZxing({
        onDecodeResult(result) {
            onScan(result.getText());
        }
    });

    return (
        <video
            ref={ref}
            style={{
                width: "400px",
                border: "2px solid #0d6efd",
                borderRadius: "10px"
            }}
        />
    );
}

export default BarcodeScanner;