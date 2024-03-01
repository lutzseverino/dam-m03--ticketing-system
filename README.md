<div align="center">
  <h1>Ticketing System</h1>
  <p>Java-based full-stack ticket management solution using JavaFX and Spring Boot.</p>    
  <p>
    <img alt="Spring badge" src="https://img.shields.io/badge/spring-%236DB33F.svg?style=flat&logo=spring&logoColor=white"/>
    <img alt="Java badge" src="https://img.shields.io/badge/java-%23ED8B00.svg?style=flat&logo=openjdk&logoColor=white"/>
  </p>
</div>

> Project assignment for the 2023-2024 period of the DAM (Desarrollo de Aplicaciones Multiplataforma - Multiplatform Application Development) M03 course at La Salle Gràcia.

## Project setup
We'll guide you through the process of setting up the modules of the project.

### Prerequisites
- [Java 17](https://www.oracle.com/es/java/technologies/downloads/#java17)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/products/docker-desktop/)
- [Docker Compose](https://www.docker.com/products/docker-desktop/)

Ensure that all the binaries are available before proceeding.

### Cloning the repository

Start by cloning the repository and navigating to the root directory of the project.

```bash
git clone https://github.com/lutzseverino/dam-m03-ticketing-system.git
cd dam-m03-ticketing-system
```

### Server
To set up the server, first, navigate to the `server` directory.

```bash
cd server
```

Copy the `.env.dist` file to `.env` and **fill in the environment variables**.

```bash
cp .env.dist .env
```

Then, build the project using Maven.

```bash
mvn clean install
```

Finally, start the server using Docker Compose.

```bash
docker-compose up
```
### Client
To set up the client, first, navigate to the `client` directory.

```bash
cd .. # If you are in the server directory
cd client
```

And run the app using Maven.

```bash
mvn clean javafx:run
```