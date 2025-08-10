# Activity Tracker — Backend (Spring Boot + MongoDB)

This is the **backend API** for the Activity Tracker.  
Built with **Spring Boot 3**, **MongoDB**, and **Maven**.

---

## ⚙️ Requirements
- Java 17+ (Temurin recommended)
- Maven 3.9+ (wrapper included)
- MongoDB (local or Docker)
- (Optional) Docker & Docker Compose

---

## 🏗️ Project Setup (Spring Initializr)
Generated with:
- Project: **Maven**
- Language: **Java**
- Spring Boot: **3.x**
- Dependencies: **Spring Web**, **Spring Data MongoDB**, **Lombok**, **DevTools**
- (Optional) **Actuator**

Package root:
```
com.example.activity_tracker
```

---

## 🔐 Environment Variables (.env)
We load `.env` using **spring-dotenv**.

**Add to `pom.xml`:**
```xml
<dependency>
  <groupId>me.paulschwarz</groupId>
  <artifactId>spring-dotenv</artifactId>
  <version>4.0.0</version>
</dependency>
```

**Create `backend/.env`:**
```
MONGODB_URI=mongodb://localhost:27017/activity_tracker
# Example Atlas:
# MONGODB_URI=mongodb+srv://user:pass@cluster0.mongodb.net/activity_tracker
```

**`src/main/resources/application.properties`:**
```properties
spring.application.name=activity-tracker
server.port=8080
spring.data.mongodb.uri=${MONGODB_URI}
```


## ▶️ Run the API
From the backend folder:
```bash
# Linux/Mac
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

API base URL:
```
http://localhost:8080/api/activities
```

---

## 📚 Endpoints

### List all
`GET /api/activities`

### List by date
`GET /api/activities?date=YYYY-MM-DD`

### Get by id
`GET /api/activities/{id}`

### Create
`POST /api/activities`
```json
{
  "name": "Reading",
  "description": "Read 50 pages",
  "category": "Study",
  "date": "2025-08-10",
  "durationMinutes": 60
}
```

### Update
`PUT /api/activities/{id}`

### Delete
`DELETE /api/activities/{id}`

---

## 🧪 Testing

Run all tests:
```bash
./mvnw test
# or on Windows
mvnw.cmd test
```

Included tests:
- **Service tests** using Mockito.
- **Controller tests** using **@SpringBootTest + @AutoConfigureMockMvc** with a mocked `ActivityService` (no DB required).

> If you prefer a slice test, you can revert to `@WebMvcTest`, but you’ll need to provide mocks and disable filters.

---

## 📦 Build Jar
```bash
./mvnw -DskipTests package
# Jar will be in target/*.jar
```

Run Jar:
```bash
java -jar target/activity-tracker-*.jar
```

Pass Mongo URI via env:
```bash
MONGODB_URI="mongodb://localhost:27017/activity_tracker" java -jar target/activity-tracker-*.jar
```

---


## 🧭 Package Structure
```
src/
├── main/java/com/example/activity_tracker/
│   ├── controller/ActivityController.java
│   ├── model/Activity.java
│   ├── repository/ActivityRepository.java
│   ├── service/ActivityService.java
│   └── ActivityTrackerApplication.java
├── test/java/com/example/activity_tracker/
│   ├── controller/ActivityControllerTest.java
│   └── service/ActivityServiceTest.java
└── main/resources/application.properties
```

---

## ✅ Quick Start
1. Create `backend/.env` → `MONGODB_URI=...`
2. Start Mongo locally or via Docker Compose.
3. `./mvnw spring-boot:run`
4. Hit `http://localhost:8080/api/activities`

Happy building! 🚀
