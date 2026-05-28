const API_BASE_URL = "http://localhost:8081";
function isValidEmail(email) {
    return email.includes("@") && email.includes(".");
}

function togglePassword(id) {
    const input = document.getElementById(id);

    if (input.type === "password") {
        input.type = "text";
    } else {
        input.type = "password";
    }
}
window.onload = function () {
    localStorage.removeItem("token");
    showRegisterBox();
};

function showMessage(id, message, type) {
    document.getElementById(id).innerHTML =
        `<p class="${type}">${message}</p>`;
}

function showRegisterBox() {
    document.getElementById("authPage").style.display = "flex";
    document.getElementById("mainWebsite").style.display = "none";
    document.getElementById("registerBox").style.display = "block";
    document.getElementById("loginBox").style.display = "none";
}

function showLoginBox() {
    document.getElementById("authPage").style.display = "flex";
    document.getElementById("mainWebsite").style.display = "none";
    document.getElementById("registerBox").style.display = "none";
    document.getElementById("loginBox").style.display = "block";
}

function showMainWebsite() {
    document.getElementById("authPage").style.display = "none";
    document.getElementById("mainWebsite").style.display = "block";

    const role = localStorage.getItem("role");

    if (role === "ADMIN") {
        document.getElementById("admin").style.display = "block";
    } else {
        document.getElementById("admin").style.display = "none";
    }
}
function registerUser() {
    const name = document.getElementById("regName").value.trim();
    const email = document.getElementById("regEmail").value.trim();
    const password = document.getElementById("regPassword").value.trim();

    if (!name || !email || !password) {
        showMessage("registerMessage", "Please fill all fields.", "error");
        return;
    }

    if (!isValidEmail(email)) {
        showMessage("registerMessage", "Please enter valid email address.", "error");
        return;
    }

    fetch(`${API_BASE_URL}/api/auth/register`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ name, email, password })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Register failed");
        }
        return response.text();
    })
    .then(() => {
        showMessage("registerMessage", "Registered successfully. Please login.", "success");

        document.getElementById("loginEmail").value = email;

        setTimeout(() => {
            showLoginBox();
        }, 1000);
    })
    .catch(() => {
        showMessage("registerMessage", "Registration failed. Email may already exist.", "error");
    });
}

function loginUser() {
    const email = document.getElementById("loginEmail").value.trim();
    const password = document.getElementById("loginPassword").value.trim();

    if (!email || !password) {
        showMessage("loginMessage", "Please enter email and password.", "error");
        return;
    }

    if (!isValidEmail(email)) {
        showMessage("loginMessage", "Please enter valid email address.", "error");
        return;
    }

    fetch(`${API_BASE_URL}/api/auth/login`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ email, password })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Login failed");
        }
        return response.text();
    })
    .then(token => {
        if (!token || token.includes("Invalid") || token.includes("User Not Found")) {
            throw new Error("Invalid login");
        }

        localStorage.setItem("token", token);
        if (email === "abi12@gmail.com") {
    localStorage.setItem("role", "ADMIN");
} else {
    localStorage.setItem("role", "USER");
}
        showMessage("loginMessage", "Login successful.", "success");

        setTimeout(() => {
            showMainWebsite();
        }, 700);
    })
    .catch(() => {
        showMessage("loginMessage", "Wrong email or password.", "error");
    });
}
function searchCompany() {
    const name = document.getElementById("companyName").value.trim();
    const resultDiv = document.getElementById("result");

    if (name === "") {
        resultDiv.innerHTML = "<p class='error'>Please enter company name.</p>";
        return;
    }

    fetch(`${API_BASE_URL}/api/companies/search?name=${name}`)
        .then(response => response.json())
        .then(data => {
            if (data.length === 0) {
                resultDiv.innerHTML = "<p class='error'>No company found.</p>";
                return;
            }

            const company = data[0];
            let statusClass = "trusted";

if (company.status === "SUSPICIOUS") {
    statusClass = "suspicious";
}

if (company.status === "SCAM_REPORTED") {
    statusClass = "scam";
}

resultDiv.innerHTML = `
    <div class="result-card">
        <h3>${company.companyName}</h3>

        <div class="trust-meter">
            <div class="trust-fill ${statusClass}"
                 style="width:${company.trustScore}%">
            </div>
        </div>

        <p><b>Trust Score:</b> ${company.trustScore}/100</p>
        <p><b>Status:</b> ${company.status}</p>
        <p><b>Total Scam Reports:</b> ${company.totalScamReports ?? 0}</p>

        <hr>

        <p><b>Website:</b> ${company.website}</p>
        <p><b>Email:</b> ${company.email}</p>
        <p><b>Phone:</b> ${company.phone}</p>
    </div>
`;

        })
        .catch(() => {
            resultDiv.innerHTML =
                "<p class='error'>Backend not connected. Start Spring Boot.</p>";
        });
}


function submitScamReport() {

    document.getElementById("reportMessage").innerHTML = "";

    const companyName =
    document.getElementById("reportCompanyName").value.trim();

    const reportedBy =
    document.getElementById("reportedBy").value.trim();

    const fakeHrName =
    document.getElementById("fakeHrName").value.trim();

    const fakeHrEmail =
    document.getElementById("fakeHrEmail").value.trim();

    const fakeHrPhone =
    document.getElementById("fakeHrPhone").value.trim();

    const chatPlatform =
    document.getElementById("chatPlatform").value;

    const paymentAmount =
    document.getElementById("paymentAmount").value.trim();

    const paymentRequested =
    document.getElementById("paymentRequested").value === "true";

    const description =
    document.getElementById("description").value.trim();

    // PHONE VALIDATION
    if (fakeHrPhone.length !== 10) {

        showMessage(
            "reportMessage",
            "Phone number must be exactly 10 digits.",
            "error"
        );

        return;
    }

    // PAYMENT VALIDATION
    if (paymentAmount === "" || isNaN(paymentAmount)) {

        showMessage(
            "reportMessage",
            "Payment amount must contain numbers only.",
            "error"
        );

        return;
    }

    fetch(`${API_BASE_URL}/api/scam-report`, {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({

            companyName,
            reportedBy,
            fakeHrName,
            fakeHrEmail,
            fakeHrPhone,
            chatPlatform,
            paymentAmount,
            paymentRequested,
            description
        })
    })

    .then(response => response.json())

    .then(() => {

        showMessage(
            "reportMessage",
            "Scam report submitted successfully.",
            "success"
        );

        // CLEAR FORM
        document.getElementById("reportCompanyName").value = "";

        document.getElementById("reportedBy").value = "";

        document.getElementById("fakeHrName").value = "";

        document.getElementById("fakeHrEmail").value = "";

        document.getElementById("fakeHrPhone").value = "";

        document.getElementById("chatPlatform").value = "";

        document.getElementById("paymentAmount").value = "";

        document.getElementById("paymentRequested").value = "false";

        document.getElementById("description").value = "";
    })

    .catch(() => {

        showMessage(
            "reportMessage",
            "Error submitting report.",
            "error"
        );

    });
}


function submitReview() {
    const companyName = document.getElementById("reviewCompanyName").value.trim();
    const reviewerName = document.getElementById("reviewerName").value.trim();
    const rating = document.getElementById("rating").value;
    const reviewText = document.getElementById("reviewText").value.trim();

    fetch(`${API_BASE_URL}/api/reviews/add`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ companyName, reviewerName, rating, reviewText })
    })
    .then(response => response.json())
    .then(() => {
        showMessage("reviewMessage", "Review submitted successfully.", "success");
        document.getElementById("reviewCompanyName").value = "";
        document.getElementById("reviewerName").value = "";
        document.getElementById("rating").value = "";
        document.getElementById("reviewText").value = "";
    })
    .catch(() => {
        showMessage("reviewMessage", "Error submitting review.", "error");
    });
}

function loadUsers() {
    fetch(`${API_BASE_URL}/api/admin/users`)
        .then(response => response.json())
        .then(data => {
            let output = "<h3>Users</h3><table><tr><th>Name</th><th>Email</th><th>Role</th></tr>";

            data.forEach(user => {
                output += `<tr><td>${user.name}</td><td>${user.email}</td><td>${user.role}</td></tr>`;
            });

            output += "</table>";
            document.getElementById("adminResult").innerHTML = output;
        });
}

function loadCompanies() {
    fetch(`${API_BASE_URL}/api/companies/all`)
        .then(response => response.json())
        .then(data => {
            let output = "<h3>Companies</h3><table><tr><th>ID</th><th>Company</th><th>Status</th><th>Trust Score</th><th>Action</th></tr>";

            data.forEach(company => {
                output += `
                    <tr>
                        <td>${company.id}</td>
                        <td>${company.companyName}</td>
                        <td>${company.status}</td>
                        <td>${company.trustScore}</td>
                        <td><button onclick="deleteCompany(${company.id})">Delete</button></td>
                    </tr>
                `;
            });

            output += "</table>";
            document.getElementById("adminResult").innerHTML = output;
        });
}

function loadScamReports() {
    fetch(`${API_BASE_URL}/api/admin/scam-reports`)
        .then(response => response.json())
        .then(data => {
            let output = "<h3>Scam Reports</h3><table><tr><th>ID</th><th>Company</th><th>Reported By</th><th>Fake HR</th><th>Description</th><th>Action</th></tr>";

            data.forEach(report => {
                output += `
                    <tr>
                        <td>${report.id}</td>
                        <td>${report.companyName}</td>
                        <td>${report.reportedBy}</td>
                        <td>${report.fakeHrName}</td>
                        <td>${report.description}</td>
                        <td><button onclick="deleteScamReport(${report.id})">Delete</button></td>
                    </tr>
                `;
            });

            output += "</table>";
            document.getElementById("adminResult").innerHTML = output;
        });
}

function deleteCompany(id) {
    fetch(`${API_BASE_URL}/api/admin/company/${id}`, { method: "DELETE" })
        .then(response => response.text())
        .then(() => {
            alert("Company deleted successfully");
            loadCompanies();
        })
        .catch(() => alert("Delete failed"));
}

function deleteScamReport(id) {
    fetch(`${API_BASE_URL}/api/admin/scam-report/${id}`, { method: "DELETE" })
        .then(response => response.text())
        .then(() => {
            alert("Scam report deleted successfully");
            loadScamReports();
        })
        .catch(() => alert("Delete failed"));
}
function viewReviews() {
    const companyName = document.getElementById("viewReviewCompanyName").value.trim();
    const reviewsDiv = document.getElementById("reviewsResult");

    if (companyName === "") {
        reviewsDiv.innerHTML = "<p class='error'>Please enter company name.</p>";
        return;
    }

    fetch(`${API_BASE_URL}/api/reviews/company/${companyName}`)
        .then(response => response.json())
        .then(data => {
            if (data.length === 0) {
                reviewsDiv.innerHTML = "<p class='error'>No reviews found for this company.</p>";
                return;
            }

            let output = "<h3>Reviews</h3>";

            data.forEach(review => {
                output += `
                    <div class="result-card">
                        <p><b>Company:</b> ${review.companyName}</p>
                        <p><b>Reviewer:</b> ${review.reviewerName}</p>
                        <p><b>Rating:</b> ⭐ ${review.rating}/5</p>
                        <p><b>Review:</b> ${review.reviewText}</p>
                    </div>
                `;
            });

            reviewsDiv.innerHTML = output;
        })
        .catch(() => {
            reviewsDiv.innerHTML = "<p class='error'>Unable to load reviews.</p>";
        });
}
function viewScamReports() {

    const companyName =
    document.getElementById("viewScamCompanyName").value.trim();

    const scamDiv =
    document.getElementById("scamReportsResult");

    if (companyName === "") {

        scamDiv.innerHTML =
        "<p class='error'>Please enter company name.</p>";

        return;
    }

    fetch(`${API_BASE_URL}/api/scam-report/company/${companyName}`)

    .then(response => response.json())

    .then(data => {

        if (data.length === 0) {

            scamDiv.innerHTML =
            "<p class='error'>No scam reports found.</p>";

            return;
        }

        let output = "<h3>Scam Reports</h3>";

        data.forEach(report => {

            output += `

                <div class="result-card">

                    <p><b>Company:</b>
                    ${report.companyName}</p>

                    <p><b>Reported By:</b>
                    ${report.reportedBy}</p>

                    <p><b>Fake HR:</b>
                    ${report.fakeHrName}</p>
                    
                    <p><b>Fake HR Email:</b>
                    ${report.fakeHrEmail}</p>

                    <p><b>Fake HR Phone:</b>
                    ${report.fakeHrPhone}</p>

                    <p><b>Chat Platform:</b>
                    ${report.chatPlatform}</p>
                    
                    <p><b>Chat Platform:</b>
                    ${report.paymentAmount}</p>

                    <p><b>Payment Requested:</b>
                    ${report.paymentRequested}</p>

                    <p><b>Description:</b>
                    ${report.description}</p>

                </div>

            `;
        });

        scamDiv.innerHTML = output;
    })

    .catch(() => {

        scamDiv.innerHTML =
        "<p class='error'>Unable to load scam reports.</p>";

    });
}