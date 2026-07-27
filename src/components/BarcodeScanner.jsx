import { useEffect, useRef } from "react";
import { BrowserMultiFormatReader } from "@zxing/browser";
import { BarcodeFormat, DecodeHintType } from "@zxing/library";

function BarcodeScanner({ onScan }) {

    const videoRef = useRef(null);

    useEffect(() => {

        const hints = new Map();

        hints.set(DecodeHintType.POSSIBLE_FORMATS, [
            BarcodeFormat.EAN_13,
            BarcodeFormat.EAN_8,
            BarcodeFormat.UPC_A,
            BarcodeFormat.UPC_E,
            BarcodeFormat.CODE_128,
            BarcodeFormat.CODE_39,
            BarcodeFormat.QR_CODE
        ]);

        const codeReader = new BrowserMultiFormatReader(hints);

        let controls = null;

        async function startScanner() {

            try {

                const devices =
                    await BrowserMultiFormatReader.listVideoInputDevices();

                devices.forEach((device, index) => {
                    console.log(
                        `Camera ${index}:`,
                        device.label,
                        device.deviceId
                    );
                });

                if (devices.length === 0) {
                    alert("No camera found");
                    return;
                }

                let cameraId = devices[0].deviceId;

                const phoneCamera = devices.find(device =>
                    device.label.toLowerCase().includes("iriun") ||
                    device.label.toLowerCase().includes("droid")
                );

                if (phoneCamera) {
                    cameraId = phoneCamera.deviceId;
                }

                console.log("Using Camera:", cameraId);

                controls = await codeReader.decodeFromVideoDevice(
                    cameraId,
                    videoRef.current,
                    (result, error) => {

                        if (result) {

                            const barcode = result.getText();

                            console.log("Scanned:", barcode);

                            if (onScan) {
                                onScan(barcode);
                            }

                            return;
                        }

                        if (error) {

                            const errorName =
                                error.name || error.constructor?.name;

                            if (errorName !== "NotFoundException") {
                                console.error(error);
                            }

                        }

                    }
                );

            } catch (err) {

                console.error("Scanner Error:", err);

            }

        }

        startScanner();

        return () => {

            if (controls) {
                controls.stop();
            }

        };

    }, [onScan]);

    return (

        <div style={{ textAlign: "center" }}>

            <h3>Scan Barcode</h3>

            <video
                ref={videoRef}
                style={{
                    width: "100%",
                    maxWidth: "700px",
                    border: "2px solid blue",
                    borderRadius: "10px"
                }}
            />

        </div>

    );

}

export default BarcodeScanner;