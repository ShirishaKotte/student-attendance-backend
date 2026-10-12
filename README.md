# Student Attendance Management System - Backend

Spring Boot REST API backend service with MySQL database integration, supporting role-based authentication (Faculty vs Student), full CRUD operations, and automated percentage calculation.

---

## 🛠️ Tech Stack
* **Java 17 / 21**
* **Spring Boot 3.2.3**
* **Spring Data JPA & Hibernate**
* **MySQL Database**
* **Maven**
* **Docker** (Containerized for cloud deployment)

---

## 🚀 Features & Endpoints
* **Role-Based Authentication:**
  * `POST /api/auth/register` - Register as Faculty or Student
  * `POST /api/auth/login` - Authenticate with ID/Roll Number and Password
* **Student Management (CRUD):**
  * `GET /api/students` - Retrieve all enrolled students
  * `GET /api/students/{id}` - Retrieve single student details
  * `POST /api/students` - Add new student
  * `PUT /api/students/{id}` - Update student record
  * `DELETE /api/students/{id}` - Remove student and associated logs
* **Attendance Management (CRUD & Analytics):**
  * `POST /api/attendance/batch` - 1-to-40 batch attendance submission for whole class
  * `GET /api/attendance/date/{date}` - Retrieve attendance records by date
  * `GET /api/attendance/student/{studentId}` - Retrieve single student's attendance history
  * `GET /api/attendance/summary` - Class-wide summary with percentage calculation
  * `GET /api/attendance/summary/{studentId}` - Individual student percentage report

---

## 💻 Local Setup & Execution
1. Clone the repository:
   ```bash
   git clone https://github.com/ShirishaKotte/student-attendance-backend.git
   cd student-attendance-backend
   ```
2. Configure MySQL credentials in `src/main/resources/application.properties`.
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. Server starts on: `http://localhost:8080`

---

## ☁️ Deployment (Render)
This repository includes a multi-stage `Dockerfile`.
1. Go to [Render.com](https://render.com) and create a new **Web Service**.
2. Connect this repository (`ShirishaKotte/student-attendance-backend`).
3. Set Environment to **Docker**.
4. Configure Environment Variables for MySQL:
   * `SPRING_DATASOURCE_URL`
   * `SPRING_DATASOURCE_USERNAME`
   * `SPRING_DATASOURCE_PASSWORD`
