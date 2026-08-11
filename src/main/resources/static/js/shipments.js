const API_URL = "http://localhost:8080/shipments";

function saveShipment() {

    const shipment = {
        shipmentNumber: document.getElementById("shipmentNumber").value,
        sourceLocation: document.getElementById("sourceLocation").value,
        destinationLocation: document.getElementById("destinationLocation").value,
        currentLocation: document.getElementById("currentLocation").value,
        remarks: document.getElementById("remarks").value
    };

    fetch(API_URL + "/save", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(shipment)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("HTTP Error: " + response.status);
        }
        return response.json();
    })
    .then(data => {
        console.log("Saved:", data);
        alert("Shipment saved successfully");
    })
    .catch(error => {
        console.error("Save error:", error);
        alert("Failed to save shipment");
    });
}