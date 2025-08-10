# TaskTracker

A robust RESTful API for managing task lists and tasks built with Spring Boot 3.5.4 and Java 24.

## 🚀 Features

- **Task List Management**: Create, read, update, and delete task lists
- **Task Management**: Full CRUD operations for tasks within task lists
- **Task Organization**: Hierarchical structure with tasks belonging to task lists
- **Task Properties**: 
  - Title and description
  - Due dates
  - Priority levels (HIGH, MEDIUM, LOW)
  - Status tracking (TODO, IN_PROGRESS, COMPLETED)
- **RESTful API**: Clean, intuitive REST endpoints
- **API Documentation**: Interactive Swagger UI for easy API exploration
- **Database Support**: PostgreSQL with H2 for testing
- **Modern Stack**: Spring Boot 3.5.4, Java 24, JPA/Hibernate

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.5.4
- **Language**: Java 24
- **Database**: PostgreSQL (production), H2 (testing)
- **ORM**: Spring Data JPA with Hibernate
- **API Documentation**: SpringDoc OpenAPI 3 with Swagger UI
- **Build Tool**: Maven

## 📋 Prerequisites

- Java 24 or higher
- Maven 3.6+
- PostgreSQL (for production)
- Git

## 🚀 Quick Start

### 1. Clone the Repository

```bash
git clone <your-repository-url>
cd TaskTracker
```

### 2. Database Setup

#### Option A: Using PostgreSQL (Recommended for Production)

1. Install PostgreSQL and create a database:
```sql
CREATE DATABASE task_tracker_project;
```

2. Configure environment variables by copying the example file:
```bash
cp example.env .env
```

3. Update the `.env` file with your database credentials:
```env
DB_HOST=localhost
DB_PORT=5432
DB_NAME=task_tracker_project
DB_USERNAME=your_username
DB_PASSWORD=your_password
APP_PORT=8080
```

#### Option B: Using H2 (Development/Testing)

The application is configured to use H2 database for testing. No additional setup required.

### 3. Run the Application

```bash
# Using Maven
mvn spring-boot:run

# Or build and run
mvn clean package
java -jar target/TaskTracker-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`

### 4. Access API Documentation

Once the application is running, you can access the interactive API documentation:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

## 📚 API Endpoints

### Task Lists

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/task-lists` | Get all task lists |
| POST | `/api/task-lists` | Create a new task list |
| GET | `/api/task-lists/{id}` | Get a specific task list |
| PUT | `/api/task-lists/{id}` | Update a task list |
| DELETE | `/api/task-lists/{id}` | Delete a task list |

### Tasks

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/task-lists/{task_list_id}/tasks` | Get all tasks in a task list |
| POST | `/api/task-lists/{task_list_id}/tasks` | Create a new task in a task list |
| GET | `/api/task-lists/{task_list_id}/tasks/{task_id}` | Get a specific task |
| PUT | `/api/task-lists/{task_list_id}/tasks/{task_id}` | Update a task |
| DELETE | `/api/task-lists/{task_list_id}/tasks/{task_id}` | Delete a task |

## 📝 Usage Examples

### Creating a Task List

```bash
curl -X POST http://localhost:8080/api/task-lists \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Work Projects",
    "description": "Tasks related to work projects"
  }'
```

### Creating a Task

```bash
curl -X POST http://localhost:8080/api/task-lists/{task_list_id}/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Complete API Documentation",
    "description": "Write comprehensive API documentation",
    "dueDate": "2024-12-31T23:59:59",
    "status": "TODO",
    "priority": "HIGH"
  }'
```

### Getting All Tasks in a List

```bash
curl -X GET http://localhost:8080/api/task-lists/{task_list_id}/tasks
```

## 🏗️ Project Structure

```
src/
├── main/
│   ├── java/com/devtiro/
│   │   ├── config/           # Configuration classes
│   │   ├── controllers/      # REST controllers
│   │   ├── domain/          # Domain entities and DTOs
│   │   │   ├── dto/         # Data Transfer Objects
│   │   │   └── entities/    # JPA entities
│   │   ├── exception/       # Exception handling
│   │   ├── mapper/          # Object mappers
│   │   ├── repositories/    # Data access layer
│   │   └── services/        # Business logic layer
│   └── resources/
│       └── application.yml  # Application configuration
└── test/                    # Test files
```

## 🧪 Testing

Run the test suite:

```bash
mvn test
```

The application includes:
- Unit tests for services
- Integration tests
- H2 in-memory database for testing

## 🔧 Configuration

Key configuration options in `application.yml`:

- **Database**: PostgreSQL with configurable connection parameters
- **JPA**: Hibernate with automatic schema updates
- **Server**: Configurable port (default: 8080)
- **Swagger**: API documentation enabled by default

## 🚀 Deployment

### Docker (Recommended)

1. Build the Docker image:
```bash
docker build -t tasktracker .
```

2. Run the container:
```bash
docker run -p 8080:8080 \
  -e DB_HOST=your_db_host \
  -e DB_PORT=5432 \
  -e DB_NAME=task_tracker_project \
  -e DB_USERNAME=your_username \
  -e DB_PASSWORD=your_password \
  tasktracker
```

### Traditional Deployment

1. Build the JAR:
```bash
mvn clean package
```

2. Run the application:
```bash
java -jar target/TaskTracker-0.0.1-SNAPSHOT.jar
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🆘 Support

If you encounter any issues or have questions:

1. Check the [API Documentation](http://localhost:8080/swagger-ui.html) when running locally
2. Review the application logs for error details
3. Open an issue on GitHub with detailed information about the problem

## 🔄 Version History

- **v0.0.1-SNAPSHOT**: Initial release with basic CRUD operations for tasks and task lists

---

**Happy Task Tracking! 🎯**
