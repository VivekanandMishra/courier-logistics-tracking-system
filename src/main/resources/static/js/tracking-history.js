const API_URL = "/tracking-history";

function loadTrackingHistory() {

    fetch(API_URL)
        .then(response => {
            if (!response.ok) {
                throw new Error("HTTP Error: " + response.status);
            }

            return response.json();
        })
        .then(data => {

            console.log("Tracking History:", data);

            const table = document.getElementById("trackingTable");

            table.innerHTML = "";

            data.forEach(item => {

                const row = document.createElement("tr");

                row.innerHTML = `
                    <td>${item.id ?? ""}</td>
                    <td>${item.shipment?.id ?? ""}</td>
                    <td>${item.status ?? ""}</td>
                    <td>${item.location ?? ""}</td>
                    <td>${item.remarks ?? ""}</td>
                    <td>${item.updatedAt ?? ""}</td>
                `;

                table.appendChild(row);
            });
        })
        .catch(error => {
            console.error("Error loading tracking history:", error);
            alert("Error loading tracking history: " + error.message);
        });
}