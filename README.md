#  Code Snippet Repository

A robust full-stack web application designed to help developers store, organize, and retrieve useful code snippets. Built with **Spring Boot** and **Thymeleaf**, and performance-optimized using **Redis Caching**.

##  Features

* **Snippet Management:** Create, read, and delete code snippets with syntax highlighting support.
* **High Performance:** Implements **Redis** caching to reduce database load and speed up snippet retrieval.
* **Search & Organize:** (Planned) Categorize snippets by programming language (Java, Python, SQL, etc.).
* **Community:** View details and comments on specific snippets.
* **Responsive UI:** Server-side rendering using Thymeleaf templates.

##  Tech Stack

* **Backend:** Java 17, Spring Boot (Web, Data JPA)
* **Frontend:** Thymeleaf, HTML5, CSS
* **Database:** MySQL
* **Caching:** Redis
* **Build Tool:** Maven

##  Prerequisites

Before running the application, ensure you have the following installed:

1.  **Java Development Kit (JDK) 17+**
2.  **Maven**
3.  **MySQL Server**
4.  **Redis Server** (Required for caching)
    * *Windows Users:* [Download the MSI installer here](https://github.com/microsoftarchive/redis/releases)

## ⚙️ Configuration

### 1. Database Setup
Create a MySQL database named `code_snippet_db` (or update the properties below to match your DB name).

### 2. Application Properties
Navigate to `src/main/resources/application.properties` and configure your connections:
# Code Snippet Repository 🚀

A full-stack application to store and manage code snippets. This project is fully containerized using **Docker**, making it easy to run anywhere without installing Java, MySQL, or Redis manually.

## 🛠 Tech Stack
* **Backend:** Java Spring Boot
* **Database:** MySQL
* **Caching:** Redis (for high performance)
* **Frontend:** HTML, CSS, Thymeleaf
* **DevOps:** Docker & Docker Compose

## ⚡ How to Run
You only need **Docker Desktop** installed.

1.  **Clone the project:**
    ```bash
    git clone [https://github.com/Zameerbashaw/Code-snippet-repository.git](https://github.com/Zameerbashaw/Code-snippet-repository.git)
    cd Code-snippet-repository
    ```

2.  **Run the app:**
    ```bash
    docker-compose up --build
    ```

3.  **Open in Browser:**
    Go to: http://localhost:8080

## 🧪 How to Test Caching
1.  Open the app and refresh the page.
2.  Check the terminal logs. You will see `Fetching from Database...`.
3.  Refresh again immediately.
4.  You will see **no database logs** because the data is loading instantly from **Redis**.

---
*Created by Zameer Bashaw*
```properties
# MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/code_snippet_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD
spring.jpa.hibernate.ddl-auto=update

# Redis Configuration (Caching)
spring.cache.type=redis
spring.data.redis.host=localhost
spring.data.redis.port=6379