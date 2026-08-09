const API_URL = "http://localhost:8080/delivery-agents";


// Save Delivery Agent
function saveAgent() {

    const agent = {
        firstName: document.getElementById("firstName").value,
        lastName: document.getElementById("lastName").value,
        email: document.getElementById("email").value,
        phoneNo: document.getElementById("phoneNo").value,
        vehicleType: document.getElementById("vehicleType").value,
        vehicleNumber: document.getElementById("vehicleNumber").value
    };

    console.log("Sending Agent:", agent);

    fetch(API_URL + "/save", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(agent)
    })
    .then(response => {

        console.log("POST Status:", response.status);

        return response.text();
    })
    .then(data => {

        console.log("POST Response:", data);

        alert("Delivery Agent saved successfully!");

        getAgents();
    })
    .catch(error => {

        console.error("POST Error:", error);

        alert("Delivery Agent save failed!");
    });
}


// Load Delivery Agents
function getAgents() {

    fetch(API_URL)
        .then(response => {

            console.log("GET Status:", response.status);

            return response.json();
        })
        .then(data => {

            console.log("Agents:", data);

            const table = document.getElementById("agentTable");

            table.innerHTML = "";

            data.forEach(agent => {

                const row = `
                    <tr>
                        <td>${agent.id}</td>
                        <td>${agent.firstName} ${agent.lastName}</td>
                        <td>${agent.email}</td>
                        <td>${agent.phoneNo}</td>
                        <td>${agent.vehicleType}</td>
                    </tr>
                `;

                table.innerHTML += row;
            });

        })
        .catch(error => {

            console.error("GET Error:", error);

            alert("Error loading delivery agents");
        });
}