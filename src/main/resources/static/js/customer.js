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

        if (!response.ok) {
            throw new Error("HTTP Error: " + response.status);
        }

        return response.json();
    })
    .then(data => {
        console.log("POST Response:", data);

        alert("Customer saved successfully!");

        getCustomers();
    })
    .catch(error => {
        console.error("POST Error:", error);
        alert("Customer save failed!");
    });
}

function getCustomers() {

    console.log("Calling:", API_URL);

    fetch(API_URL)
        .then(response => {

            console.log("HTTP Status:", response.status);
            console.log("Response OK:", response.ok);

            if (!response.ok) {
                throw new Error("HTTP Error: " + response.status);
            }

            return response.text();
        })
        .then(text => {

            console.log("Raw Response:", text);

            const response = JSON.parse(text);

            console.log("Parsed Response:", response);

            let customers;

            // If backend returns:
            // { statusCode: 200, message: "...", data: [...] }
            if (response.data) {
                customers = response.data;
            }
            // If backend returns directly:
            // [...]
            else if (Array.isArray(response)) {
                customers = response;
            }
            else {
                throw new Error("Customer data not found in response");
            }

            console.log("Customers:", customers);

            const table = document.getElementById("customerTable");

            if (!table) {
                throw new Error("customerTable element not found in HTML");
            }

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

            alert("Error loading customers: " + error.message);

        });
}
