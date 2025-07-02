# 📚 SmartLibraryPro

SmartLibraryPro is a full-stack Library Management System built with **Spring Boot** and **React.js**, designed to support book search, borrowing, reviews, admin book management, and secure JWT-based authentication.

---

## 🚀 Features

### 👨‍🎓 User Features
- 🔍 Search for books by title, author, or category
- 📖 View book details, availability, and ratings
- ✅ Secure login/signup with JWT authentication
- 📦 Checkout books (limit: 5 books per user)
- 📝 Submit and view reviews
- 📚 Personal bookshelf to track borrowed books

### 🔐 Admin Features
- ➕ Add new books with base64 image support
- 🗑️ Delete or manage existing books
- 📊 Monitor library inventory
- 🛠️ Secure admin panel with role-based access

---

## 🛠️ Tech Stack

### ⚙ Backend
- **Java 17**, **Spring Boot**
- **Spring Security** (JWT Authentication)
- **Spring Data JPA** with **MySQL**
- **RESTful API** architecture
- **CORS** enabled

### 💻 Frontend
- **React.js**, **TypeScript**
- **React Router** for navigation
- **Bootstrap 5** for UI styling
- **Context API** for global auth state

### 🔒 Security
- JWT Bearer Authentication
- Role-based access (User/Admin)
- Secured API routes

---

## 🗂️ Folder Structure

SmartLibraryPro/
├── backend/ # Spring Boot project
│ └── src/...
├── frontend/ # React project
│ └── src/...
│ ├── layouts/
│ ├── models/
│ ├── context/
│ ├── components/
│ └── App.tsx
├── README.md
└── .gitignore


---

## 🧪 Getting Started

### ⚙ Backend Setup (`Spring Boot`)

1. Navigate to backend:
   ```bash
   cd backend
2. Set up application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/smartlibrary
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

3. Run the Spring Boot server:
   ./mvnw spring-boot:run

💻 Frontend Setup (React)
1. Navigate to frontend:
cd frontend

2. Install dependencies:

npm install

3. Run the React app:

npm start

4. The app will run on:

http://localhost:3000
