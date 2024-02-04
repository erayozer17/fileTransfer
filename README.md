## A playground to try out non-blocking I/O in Java. Still needs improvements.

# File Transfer Application

The File Transfer Application is a client-server based application that allows users to upload and download files from a server. This application is built using Java and follows a client-server architecture.

## Features

- File upload: Users can upload files from the client to the server.
- File download: Users can download files from the server to the client.
- Concurrent file transfers: The server can handle multiple client connections and transfer files concurrently.
- User-friendly interface: The application provides an intuitive interface for users to interact with.
- Error handling: Proper error handling is implemented to handle exceptions and provide meaningful error messages.
- Logging: The application logs important events and actions for debugging and monitoring purposes.

## Prerequisites

- Java Development Kit (JDK) version 8 or higher
- Gradle build tool
- Git version control system

## Getting Started

1. Clone the repository:

   ```shell
   git clone https://github.com/your-username/file-transfer-application.git
   ```

2. Change into the project directory:

   ```shell
   cd file-transfer-application
   ```

3. Build the project using Gradle:

   ```shell
   gradle build
   ```

4. Start the server:

   ```shell
   java -jar server/build/libs/server.jar
   ```

5. Start the client:

   ```shell
   java -jar client/build/libs/client.jar
   ```

## Configuration

- The server and client components have separate configuration files located in their respective `src/main/resources` directories.
- Modify the `config.properties` file to customize the application's behavior, such as server port, file storage location, etc.

## Contributing

Contributions to this project are welcome. To contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your changes and commit them.
4. Push your changes to your forked repository.
5. Submit a pull request to the main repository.

## Contact

For any questions or inquiries, please contact [your-email@example.com](mailto:your-email@example.com).
