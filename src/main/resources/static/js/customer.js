const API_URL = "http://localhost:8080/customers";

function saveCustomer() {

    const customer = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        phoneNo: document.getElementById("phone").value,
        address: document.getElementById("address").value
    };

    console.log("Sending:", customer);

    fetch(API_URL + "/save", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(customer)
    })
    .then(response => {
        console.log("POST Status:", response.status);
        return response.json();
    })
    .then(data => {
        console.log("POST Response:", data);

        alert("Customer saved successfully!");

        // Automatically reload customers
        getCustomers();
    })
    .catch(error => {
        console.error("POST Error:", error);
        alert("Customer save failed!");
    });
}


function getCustomers() {

    fetch(API_URL)
    .then(response => {

        console.log("GET Status:", response.status);

        if (!response.ok) {
            throw new Error("HTTP Error: " + response.status);
        }

        return response.json();
    })
    .then(response => {

        console.log("GET Response:", response);

        // If backend returns ResponseStructure
        const customers = response.data;

        console.log("Customers:", customers);

        const table = document.getElementById("customerTable");

        table.innerHTML = "";

        customers.forEach(customer => {

            table.innerHTML += `
                <tr>
                    <td>${customer.id}</td>
                    <td>${customer.name}</td>
                    <td>${customer.email}</td>
                    <td>${customer.phoneNo}</td>
                    <td>${customer.address}</td>
                </tr>
            `;

        });

    })
    .catch(error => {

        console.error("GET Error:", error);

        alert("Error loading customers");

    });
}