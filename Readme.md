# 💰 Gold Loan Application – Spring Boot REST API

This is a **Spring Boot-based Gold Loan Application System**.  
The application provides **REST APIs** to manage customers and their gold loan applications.

---

## 🎥 Project Demo Video

The demo video is included in this repository under the `/video` folder.  
You can view it here:  
[▶️ Watch Demo Video](https://drive.google.com/file/d/1AEKPjjC0gnuylNCPg8zZaF0349AJpukR/view?usp=sharing)

---

## 🧩 Prerequisites
- Java 17 or later
- Maven 3.8 or later
- MySQL or H2 Database

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/SourabhPal/gold-loan-api.git
cd gold-loan-api
```

---

### 2️⃣ Configure the Database
Update the `application.properties` file located in  
`src/main/resources/` with your database credentials.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gold_loan_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

---

### 3️⃣ Create the Database Schema
Before running the project, make sure you have created the database in MySQL.

```sql
CREATE DATABASE gold_loan_db;
```

If you prefer to use H2 (in-memory) database instead of MySQL,  
update your `application.properties` like this:

```properties
spring.datasource.url=jdbc:h2:mem:gold_loan_db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
```

Then open H2 console at:  
👉 [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

---

### 4️⃣ Build and Run the Application
Navigate to the project directory and run:

```bash
mvn spring-boot:run
```

The application will start at:  
👉 [http://localhost:8080](http://localhost:8080)

---

## 📘 API Documentation
Swagger UI is available at:  
👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 🔧 Example Requests

### ➕ Create a Customer
**POST** `/api/customers`
```json
{
  "name": "Sourabh Pal",
  "email": "sourabh@gmail.com",
  "phone": "9876500000",
  "dob": "1999-05-10"
}
```

---

### 💸 Submit a Loan Application
**POST** `/api/loans`
```json
{
  "customerId": 1,
  "loanAmount": 50000,
  "goldWeight": 50,
  "loanPurpose": "Business expansion"
}
```

---

### 🔍 Get All Customers
**GET** `/api/customers`

### 🔍 Get All Loan Applications
**GET** `/api/loans`

---

## 🏗️ Tech Stack
- **Spring Boot** (REST API)
- **Spring Data JPA / Hibernate**
- **MySQL / H2 Database**
- **Swagger** for API documentation

---

## 👨‍💻 Author
**Sourabh Pal**  
Spring Boot Developer 🚀  
📧 [sourabhpal@example.com](mailto:sourabhpal@example.com)

---

⭐ *If you like this project, don't forget to star the repository!*
