ShopSphere Backend 🚀
A complete Spring Boot E-Commerce Backend Application with JWT Authentication, Role-Based Authorization, Product Management, Cart System, Order Management, Admin Dashboard APIs, Revenue Analytics, Pagination, Search, Sorting, and Swagger Documentation.

📌 Project Overview
ShopSphere Backend is a REST API based E-Commerce backend developed using:
Spring Boot
Spring Security
JWT Authentication
Hibernate / JPA
MySQL
Maven
Swagger OpenAPI
The project supports:
User Authentication & Authorization
Product & Category Management
Cart Operations
Order Processing
Admin Management
Revenue Statistics
Dashboard APIs

🛠️ Tech Stack
Technology
Used
Java
JDK 24
Spring Boot
Backend Framework
Spring Security
Authentication & Authorization
JWT
Token Authentication
Hibernate / JPA
ORM
MySQL
Database
Maven
Dependency Management
Swagger OpenAPI
API Documentation
Lombok
Boilerplate Reduction
Eclipse IDE
Development


📂 Project Structure
src/main/java/com/nari/shopsphere_backend
│
├── config
├── controller
├── dto
├── entity
├── exception
├── jwt
├── repository
├── service
├── serviceimpl
└── util

🔐 Authentication & Security
Implemented using:
Spring Security
JWT Token Authentication
Role-Based Authorization
Roles:
ADMIN
USER
Features:
Secure Login
JWT Token Generation
JWT Validation Filter
Protected APIs
Admin-Only APIs

📦 Features Implemented
✅ Authentication APIs
API
Method
Register User
POST
Login User
POST

Endpoints
POST /api/auth/register
POST /api/auth/login

✅ User Profile APIs
API
Method
Get Profile
GET
Update Profile
PUT

Endpoints
GET /api/users/profile
PUT /api/users/profile

✅ Category APIs
API
Method
Add Category
POST
Get All Categories
GET
Get Category By ID
GET
Update Category
PUT
Delete Category
DELETE

Endpoints
POST /api/categories
GET /api/categories
GET /api/categories/{id}
PUT /api/categories/{id}
DELETE /api/categories/{id}

✅ Product APIs
API
Method
Add Product
POST
Get All Products
GET
Get Product By ID
GET
Update Product
PUT
Delete Product
DELETE
Search Products
GET
Pagination
GET
Sort ASC
GET
Sort DESC
GET
Price Filter
GET
Filter By Category
GET

Endpoints
POST /api/products
GET /api/products
GET /api/products/{id}
PUT /api/products/{id}
DELETE /api/products/{id}

GET /api/products/search
GET /api/products/pagination
GET /api/products/sort/asc
GET /api/products/sort/desc
GET /api/products/price
GET /api/products/category/{categoryId}

✅ Cart APIs
API
Method
Add To Cart
POST
Get Cart
GET
Update Quantity
PUT
Remove Cart Item
DELETE
Clear Cart
DELETE

Endpoints
POST /api/cart/add
GET /api/cart/{cartId}
PUT /api/cart/update/{cartItemId}
DELETE /api/cart/remove/{cartItemId}
DELETE /api/cart/clear/{cartId}

✅ Order APIs
API
Method
Place Order
POST
Get All Orders
GET
Get Order By ID
GET

Endpoints
POST /api/orders/place/{cartId}
GET /api/orders
GET /api/orders/{id}

✅ Admin Order APIs
API
Method
Get All Orders
GET
Get Order By ID
GET
Update Order Status
PUT
Revenue Analytics
GET
Orders Count
GET

Endpoints
GET /api/admin/orders
GET /api/admin/orders/{id}
PUT /api/admin/orders/{orderId}/status

GET /api/admin/orders/revenue
GET /api/admin/orders/count
Supported Order Status:
PENDING
SHIPPED
DELIVERED
CANCELLED

✅ Admin User APIs
API
Method
Get All Users
GET
Get User By ID
GET
Delete User
DELETE
Dashboard
GET
Admin Stats
GET

Endpoints
GET /api/admin/users
GET /api/admin/users/{id}
DELETE /api/admin/users/{id}

GET /api/admin/dashboard
GET /api/admin/stats

✅ Upload API
API
Method
Upload Image/File
POST

Endpoint
POST /api/upload

✅ Additional Features
Implemented:
JWT Security Filter
Global Exception Handling
DTO Pattern
Pagination
Sorting
Searching
Revenue Calculation
Swagger API Testing
Cart Total Calculation
Order Total Calculation
Soft Delete for Products
Category Validation Before Delete
Role-Based Endpoint Access

🗄️ Database
Database Used:
MySQL
Example Database Name:
shopsphere_db

⚙️ Swagger Documentation
Swagger UI:
http://localhost:8080/swagger-ui/index.html
Used for:
API Testing
JWT Authorization Testing
Request/Response Validation

▶️ Run Project
Clone Repository
git clone https://github.com/Narendra-Git1/shopsphere-E-Commerce.git
Open Project
Import as:
Maven Existing Project

Configure Database
Update:
application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/shopsphere_db
spring.datasource.username=root
spring.datasource.password=root

Run Application
mvn spring-boot:run
OR run:
ShopSphereBackendApplication.java

🔑 Sample Admin Login
{
 "email": "admin@gmail.com",
 "password": "Admin@123"
}

📈 Project Status
✅ Backend Development Completed
 ✅ API Testing Completed
 ✅ Swagger Testing Completed
 ✅ JWT Security Completed
 ✅ Admin Features Completed
 ✅ Database Integration Completed

👨‍💻 Developer
Narendra M
Aspiring Java Full Stack Developer
Skills:
Java
Spring Boot
Hibernate
MySQL
React
REST APIs
JWT Security

📌 Future Enhancements
React Frontend
Payment Gateway Integration
Docker Deployment
Cloud Deployment
Email Notifications
Wishlist Feature
Review & Rating System
Microservices Architecture

⭐ Conclusion
ShopSphere Backend is a complete E-Commerce REST API backend project demonstrating real-world backend development concepts including authentication, authorization, cart management, order processing, analytics, and admin management using Spring Boot and MySQL.

