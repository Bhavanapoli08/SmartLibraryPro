# 📚 Library Management System

A full-stack web application built with **React** and **Spring Boot** for managing books, users, and borrowing records in a library.

---

## 🔧 Tech Stack

- **Frontend**: React, JavaScript, HTML, CSS
- **Backend**: Java, Spring Boot, Spring REST, Spring Data JPA, Hibernate
- **Database**: MySQL (or other databases)
- **Tools**: Maven, VS Code, IntelliJ IDEA, Git

---

## ✨ Features

- Add, update, delete, and view books
- Manage library users
- Search for books
- Checkout and return books
- Authentication using JWT (JSON Web Tokens)

---

## 🚀 Getting Started

### 🖥️ Frontend (React)

1. Navigate to the `frontend/` directory and install dependencies.
2. Run the development server to start the frontend application.

The frontend will be available at `http://localhost:3000`.

### ☕ Backend (Spring Boot)

1. Open the `backend/` folder in your preferred IDE (e.g., IntelliJ IDEA or VS Code).
2. Configure the database connection in `src/main/resources/application.properties`.
3. Run the backend application, which will be available at `http://localhost:8080`.

---

## 🔐 Environment Variables

Create a `.env` file in the `frontend/` directory to specify the API URL:

**frontend/.env**
```env
REACT_APP_API_URL=http://localhost:8080/api
