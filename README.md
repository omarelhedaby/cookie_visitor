# Cookie Tracker Application

## Configuration

To connect the application to the database, you need to provide your database credentials in an `.env` file.

### Steps:

1. Create a `.env` file in the root of your project if it does not already exist.
2. Add the following environment variables:

```bash
DB_USERNAME=
DB_PASSWORD=
```

## Running the Application

To run the application, including the database, volumes, and networks, follow these steps:

1. **Start the services using Docker Compose:**

    Run the following command:

    ```bash
    docker-compose up
    ```

    This will start the application along with the database and required services.

    If you make changes in the code and want to run again and force re-build.
    Run the following command:

    ```bash
    docker-compose up --build
    ```

2. **Access the Application:**

    Once the application is running, you can access it on:

    ```bash
    http://localhost:8081/
    ```

    To get the most visited cookie for a specific date.

    ```bash
    http://localhost:8081/api/cookie/most-visited/{date}
    ```

    The date should be in the format YYYY-MM-DD

3. **Access the DB:**

    Once the db is running, you can access it on:

    ```bash
    http://localhost:5432/
    ```

4. **API Documentation:**
    The API documentation is available at:

    ```bash
    http://localhost:8081/swagger-ui.html
    ```

## Testing the Application

    Run the following command to run the tests:

    ```bash
    mvn test -Dspring.profiles.active=test
    ```