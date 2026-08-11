// ======================================================
// USERS API
// ======================================================

const API_URL = "http://localhost:8080/users";

console.log("users.js loaded");


// ======================================================
// SAVE USER
// ======================================================

document
    .getElementById("userForm")
    .addEventListener("submit", function(event) {

        event.preventDefault();

        saveUser();

    });


// ======================================================
// SAVE USER
// ======================================================

function saveUser() {

    const user = {

        username:
            document.getElementById("username").value,

        email:
            document.getElementById("email").value,

        role:
            document.getElementById("role").value

    };


    console.log("Sending user:", user);


    fetch(API_URL + "/save", {

        method: "POST",

        headers: {

            "Content-Type": "application/json"

        },

        body: JSON.stringify(user)

    })


    .then(response => {

        console.log(
            "SAVE STATUS:",
            response.status
        );


        if (!response.ok) {

            throw new Error(
                "HTTP Error: " + response.status
            );

        }


        return response.json();

    })


    .then(data => {

        console.log(
            "SAVE RESPONSE:",
            data
        );


        document.getElementById("message").innerText =
            "User saved successfully!";


        document
            .getElementById("userForm")
            .reset();


        loadUsers();

    })


    .catch(error => {

        console.error(
            "SAVE ERROR:",
            error
        );


        document.getElementById("message").innerText =
            "Save Error: " + error.message;

    });

}


// ======================================================
// LOAD USERS
// ======================================================

function loadUsers() {

    console.log("Loading users...");


    fetch(API_URL)

        .then(response => {

            console.log(
                "LOAD STATUS:",
                response.status
            );


            if (!response.ok) {

                throw new Error(
                    "HTTP Error: " + response.status
                );

            }


            return response.json();

        })


        .then(responseData => {

            console.log(
                "FULL RESPONSE:",
                responseData
            );


            // ==========================================
            // HANDLE BOTH RESPONSE TYPES
            // ==========================================

            let users;


            if (Array.isArray(responseData)) {

                users = responseData;

            }

            else if (
                responseData.data &&
                Array.isArray(responseData.data)
            ) {

                users = responseData.data;

            }

            else {

                throw new Error(
                    "User data is not an array"
                );

            }


            console.log(
                "USER DATA:",
                users
            );


            const table =
                document.getElementById(
                    "userTable"
                );


            table.innerHTML = "";


            if (users.length === 0) {

                table.innerHTML = `
                    <tr>
                        <td colspan="4">
                            No users found
                        </td>
                    </tr>
                `;

                return;

            }


            // ==========================================
            // DISPLAY USERS
            // ==========================================

            users.forEach(user => {

                const row =
                    document.createElement("tr");


                row.innerHTML = `

                    <td>
                        ${user.id ?? ""}
                    </td>

                    <td>
                        ${user.username ?? ""}
                    </td>

                    <td>
                        ${user.email ?? ""}
                    </td>

                    <td>
                        ${user.role ?? ""}
                    </td>

                `;


                table.appendChild(row);

            });

        })


        .catch(error => {

            console.error(
                "LOAD ERROR:",
                error
            );


            document.getElementById("message").innerText =
                "Load Error: " + error.message;

        });

}