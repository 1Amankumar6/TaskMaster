# **TaskMaster – Task Management API (Spring Boot)**

A clean and extensible **Spring Boot REST API** for managing tasks with support for:

* CRUD operations
* Dynamic filtering (priority, user, status)
* Partial updates (PATCH)
* Custom exceptions with clean JSON error responses
* Validations using Hibernate Validator
* User–Task relationship (One-to-Many)

---

## 🚀 **Features**

### ✔ Task Management

* Create a task
* Update a task (full update)
* Partially update (description / priority only)
* Delete a task
* Get task by ID
* Get all tasks

### ✔ Advanced Filtering

Single endpoint with multiple query params:

```
/tasks?priority=HIGH&userId=1&status=PENDING
```

Supports:

* Priority filter
* User filter
* Status filter
* Any combination of the above

### ✔ Custom Exceptions

* `TaskNotFoundException`
* `UserNotFoundException`
* Global exception handler
* Clean JSON responses

### ✔ Validations

* Title must not be blank
* Description length constraint
* Priority must be valid ENUM
* Status must be valid ENUM

### ✔ Clean Entity Relations

* One User → Many Tasks
* Tasks mapped with `@ManyToOne`
* User mapped with `@OneToMany`

---

## 📁 **Project Structure**

```
src/
 └── main/
     ├── java/com/curd/basicProject/
     │   ├── controller/
     │   │   ├── TaskController.java
     │   │   └── UserController.java
     │   │
     │   ├── dto/
     │   │   └── TaskUpdateDTO.java
     │   │
     │   ├── entities/
     │   │   ├── User.java
     │   │   ├── Task.java
     │   │   ├── Priority.java
     │   │   └── Status.java
     │   │
     │   ├── exception/
     │   │   ├── TaskNotFoundException.java
     │   │   ├── UserNotFoundException.java
     │   │   └── GlobalExceptionHandler.java
     │   │
     │   ├── repository/
     │   │   ├── TaskRepository.java
     │   │   └── UserRepository.java
     │   │
     │   └── service/
     │       ├── TaskService.java
     │       └── UserService.java
     │       └── imp/
     │          ├── TaskServiceImpl.java
     │          └── UserServiceImpl.java
     │
     └── resources/
         └── application.properties
```

---

## 🔗 **API Endpoints**

###  **1. Create Task**

**POST** `/tasks`
Validates title, description, priority, status, user.

---

###  **2. Get All Tasks / Filter**

**GET** `/tasks`
Examples:

```
/tasks?priority=HIGH
/tasks?userId=1
/tasks?status=PENDING
/tasks?priority=LOW&userId=3&status=DONE
```

---

###  **3. Get Task by ID**

**GET** `/tasks/{id}`

---

### **4. Delete Task**

**DELETE** `/tasks/{id}`

---

###  **5. Full Update Task**

**PUT** `/tasks/{id}`

---

###  **6. Partial Update (PATCH)**

**PATCH** `/tasks/{id}`
Example body:

```json
{ "priority": "MEDIUM" }
```

or

```json
{ "description": "Improved task details" }
```

---

###  **7. Get Tasks by User**

**GET** `/users/{id}/tasks`
Throws `UserNotFoundException` if invalid.

---

##  **Error Handling (JSON Response Example)**

Invalid task ID:

```json
{
  "error": "Task Not Found",
  "message": "Task with id 20 does not exist",
  "status": 404,
  "timestamp": "2025-12-05T10:15:30"
}
```

Invalid Enum:

```json
{
  "error": "Bad Request",
  "message": "Invalid priority value: SUPER_HIGH",
  "status": 400
}
```

---

##  **Tech Stack**

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate Validator
* MySQL
* Lombok

---

## ️ **Run the Project**

```
mvn spring-boot:run
```

---

## **Future Improvements**

* JWT authentication
* Role-based access
* Pagination & Sorting
* Soft deletes