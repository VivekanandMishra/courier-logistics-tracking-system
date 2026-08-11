
const URL = "http://localhost:8080/delivery-agents";

// ===============================
// SAVE DELIVERY AGENT
// ===============================
function saveAgent() {

    const agent = {
        agentCode: document.getElementById("agentCode").value.trim(),
        firstName: document.getElementById("firstName").value.trim(),
        lastName: document.getElementById("lastName").value.trim(),
        email: document.getElementById("email").value.trim(),
        phoneNo: document.getElementById("phoneNo").value.trim(),
        vehicleType: document.getElementById("vehicleType").value.trim(),
        vehicleNumber: document.getElementById("vehicleNumber").value.trim(),
        drivingLicenseNo: document.getElementById("drivingLicenseNo").value.trim(),
        currentLocation: document.getElementById("currentLocation").value.trim(),
        available: document.getElementById("available").checked,
        joiningDate: document.getElementById("joiningDate").value,
        salary: Number(document.getElementById("salary").value)
    };

    console.log("Sending Agent:", agent);

    // Basic validation
    if (!agent.agentCode ||
        !agent.firstName ||
        !agent.lastName ||
        !agent.email ||
        !agent.phoneNo ||
        !agent.vehicleType ||
        !agent.vehicleNumber ||
        !agent.drivingLicenseNo ||
        !agent.currentLocation ||
        !agent.joiningDate ||
        !agent.salary) {

        alert("Please fill all fields.");
        return;
    }

    fetch(URL + "/save", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(agent)
    })
    .then(async response => {

        console.log("POST Status:", response.status);

        const text = await response.text();

        if (!response.ok) {
            console.error("Backend Error:", text);
            throw new Error("HTTP Error: " + response.status);
        }

        return text ? JSON.parse(text) : {};
    })
    .then(data => {

        console.log("POST Response:", data);

        alert("Agent Saved Successfully!");

        // Clear form
        document.getElementById("agentForm").reset();

        // Load updated data
        getAgents();
    })
    .catch(error => {

        console.error("POST Error:", error);

        alert("Agent Save Failed: " + error.message);
    });
}


// ===============================
// LOAD ALL DELIVERY AGENTS
// ===============================
function getAgents() {

    console.log("Loading Agents:", URL);

    fetch(URL)
        .then(async response => {

            console.log("GET Status:", response.status);

            const text = await response.text();

            if (!response.ok) {
                console.error("GET Backend Error:", text);
                throw new Error("HTTP Error: " + response.status);
            }

            return text ? JSON.parse(text) : [];
        })
        .then(agents => {

            console.log("GET Response:", agents);

            const table = document.getElementById("agentTable");

            table.innerHTML = "";

            if (!agents || agents.length === 0) {

                table.innerHTML = `
                    <tr>
                        <td colspan="12">
                            No Delivery Agents Found
                        </td>
                    </tr>
                `;

                return;
            }

            agents.forEach(agent => {

                table.innerHTML += `
                    <tr>
                        <td>${agent.id ?? ""}</td>
                        <td>${agent.agentCode ?? ""}</td>
                        <td>${agent.firstName ?? ""}</td>
                        <td>${agent.lastName ?? ""}</td>
                        <td>${agent.email ?? ""}</td>
                        <td>${agent.phoneNo ?? ""}</td>
                        <td>${agent.vehicleType ?? ""}</td>
                        <td>${agent.vehicleNumber ?? ""}</td>
                        <td>${agent.drivingLicenseNo ?? ""}</td>
                        <td>${agent.currentLocation ?? ""}</td>
                        <td>${agent.available ?? ""}</td>
                        <td>${agent.joiningDate ?? ""}</td>
                        <td>${agent.salary ?? ""}</td>
                    </tr>
                `;
            });
        })
        .catch(error => {

            console.error("GET Error:", error);

            alert("Error loading delivery agents: " + error.message);
        });
}