const API_URL = "http://localhost:8080/warehouses";

console.log("WAREHOUSE JS LOADED");


// =================================================
// SAVE
// =================================================

document.getElementById("warehouseForm")
    .addEventListener("submit", function(event) {

        event.preventDefault();

        console.log("SAVE BUTTON CLICKED");

        saveWarehouse();

    });


function saveWarehouse() {

    const warehouse = {

        warehouseName:
            document.getElementById("warehouseName").value,

        warehouseCode:
            document.getElementById("warehouseCode").value,

        location:
            document.getElementById("location").value,

        city:
            document.getElementById("city").value,

        state:
            document.getElementById("state").value,

        capacity:
            Number(document.getElementById("capacity").value),

        managerName:
            document.getElementById("managerName").value,

        contactNumber:
            document.getElementById("contactNumber").value,

        active:
            document.getElementById("active").checked
    };


    console.log("DATA BEING SENT:");
    console.log(warehouse);


    fetch("http://localhost:8080/warehouses/save", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(warehouse)

    })

    .then(function(response) {

        console.log("SAVE STATUS:", response.status);

        if (!response.ok) {

            throw new Error(
                "Save failed: HTTP " + response.status
            );
        }

        return response.json();

    })

    .then(function(data) {

        console.log("SAVE RESPONSE:", data);

        document.getElementById("message").innerText =
            "Warehouse saved successfully";

        document.getElementById("warehouseForm").reset();

        document.getElementById("active").checked = true;

        loadWarehouses();

    })

    .catch(function(error) {

        console.error("SAVE ERROR:", error);

        document.getElementById("message").innerText =
            error.message;

    });

}


// =================================================
// LOAD
// =================================================

function loadWarehouses() {

    console.log("LOAD BUTTON CLICKED");

    fetch("http://localhost:8080/warehouses")

    .then(function(response) {

        console.log("LOAD STATUS:", response.status);

        if (!response.ok) {

            throw new Error(
                "Load failed: HTTP " + response.status
            );
        }

        return response.json();

    })

    .then(function(data) {

        console.log("LOAD RESPONSE:");
        console.log(data);


        let warehouses = [];


        // Backend returns direct List
        if (Array.isArray(data)) {

            warehouses = data;

        }

        // Backend returns ResponseStructure
        else if (data.data && Array.isArray(data.data)) {

            warehouses = data.data;

        }

        else {

            console.error(
                "Unexpected response:",
                data
            );

            throw new Error(
                "API response does not contain warehouse list"
            );
        }


        const table =
            document.getElementById("warehouseTable");


        table.innerHTML = "";


        warehouses.forEach(function(warehouse) {

            const row =
                document.createElement("tr");


            row.innerHTML = `

                <td>${warehouse.id || ""}</td>

                <td>${warehouse.warehouseCode || ""}</td>

                <td>${warehouse.warehouseName || ""}</td>

                <td>${warehouse.location || ""}</td>

                <td>${warehouse.city || ""}</td>

                <td>${warehouse.state || ""}</td>

                <td>${warehouse.capacity || ""}</td>

                <td>${warehouse.managerName || ""}</td>

                <td>${warehouse.contactNumber || ""}</td>

                <td>
                    ${warehouse.active ? "Yes" : "No"}
                </td>

            `;


            table.appendChild(row);

        });


        document.getElementById("message").innerText =
            warehouses.length + " warehouse(s) loaded";

    })

    .catch(function(error) {

        console.error("LOAD ERROR:", error);

        document.getElementById("message").innerText =
            error.message;

    });

}