// ==========================================
// SHIPMENT STATUS
// ==========================================

const shipmentStatuses = [

    "CREATED",
    "PICKED_UP",
    "IN_TRANSIT",
    "OUT_FOR_DELIVERY",
    "DELIVERED",
    "CANCELLED"

];


// ==========================================
// SHOW SELECTED STATUS
// ==========================================

function showStatus() {

    const selectedStatus =
        document.getElementById("shipmentStatus").value;


    const table =
        document.getElementById("statusTable");


    const message =
        document.getElementById("message");


    // Clear old table
    table.innerHTML = "";


    // No status selected
    if (selectedStatus === "") {

        message.innerText =
            "Please select a shipment status.";

        return;
    }


    // Find selected status
    const index =
        shipmentStatuses.indexOf(selectedStatus);


    // Create row
    const row =
        document.createElement("tr");


    row.innerHTML = `

        <td>
            ${index + 1}
        </td>

        <td>
            ${selectedStatus}
        </td>

    `;


    table.appendChild(row);


    // Message
    message.innerText =
        "Selected Status: " + selectedStatus;
}