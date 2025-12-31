# e-Prescription

An **end-to-end electronic prescription system** that provides a modern digital platform for creating, managing, and sharing prescriptions between doctors, pharmacists, and patients. This project includes both backend and frontend components, along with authentication, database initialization, and deployment support.

## 🚀 Features

- **Doctor Module**
  - Create digital prescriptions
  - Manage patient records
  - Secure login and identity management
- **Pharmacist Module**
  - View and process prescriptions
  - Track fulfillment and prescription status
- **Patient Module**
  - Access personal prescribed data
  - View prescription history
- **Authentication & Security**
  - Integrated with **Keycloak** for identity and access control
- **Docker & Compose**
  - Easy deployment using containerization
- **Test Scripts**
  - Includes helper scripts for test and initial data population

## 📁 Repository Structure
.\
├── backend-server/        # Backend API and business logic\
├── frontend-client/       # Frontend application\
├── compose.yaml           # Docker Compose configuration\
├── init-db.sql            # Database initialization script\
├── keycloak-realm.json    # Keycloak realm configuration\
├── project.env            # Environment variables\
├── test_data.sh           # Test data generation script\


## 🧩 Tech Stack

| Layer | Technology |
|-------|------------|
| Backend | Java & SpringBoot|
| Frontend | Svelte, JavaScript & Astro |
| Auth | Keycloak |
| Database | SQL |
| Deployment | Docker & Docker Compose |

## 📦 Installation & Setup

1. **Clone the repository**

   ```bash
   git clone https://github.com/Giridhar-Shreyas/e-prescription.git
   cd e-prescription

2. **Clone the repository**

    Create a .env file (copy from project.env) and provide your configuration:



3. **Start services**

    ```bash
    docker compose up --build
    ```

    This will bring up the following components:
* **Backend API:** Spring Boot Resource Server
* **Frontend Client:** SvelteKit User Interface
* **Keycloak:** Identity and Access Management (IAM)
* **Database:** PostgreSQL / MySQL


## 📌 Usage

Once the services are launched locally, you can access:

* **Frontend UI:** `http://localhost:4321`
* **Keycloak Admin:** `http://localhost:8081` (Admin Console)
* **Backend API:** Access the protected endpoints via the exposed port (default `8080`)


---

## 🛡 Authentication

This project utilizes **Keycloak** for centralized Single Sign-On (SSO) and robust Role-Based Access Control (RBAC). The security flow is built around three primary roles:

1. **Doctors** - Authorized to issue and sign new prescriptions.
2. **Pharmacists** - Authorized to verify and dispense medications.
3. **Patients** - Authorized to view their personal prescription history.

### ⚙️ Keycloak Setup
To quickly replicate the environment, use the provided `keycloak-realm.json` file:
1. Log into the Keycloak Admin Console.
2. Navigate to **Import Realm**.
3. Upload `keycloak-realm.json` to automatically configure clients, roles, and scopes.
4. use `test_data.sh` to inject example users