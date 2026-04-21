# SolarSynBackend

Backend service for the SolarSync dashboard platform.

Built with:

* Java 21
* Spring Boot 3.5.3
* Spring Security
* Spring Data MongoDB
* Maven Wrapper
* MongoDB 8.x

---

# Features

* JWT Authentication
* User management
* Station management
* Dashboard APIs
* Sensor APIs (temperature, humidity, voltage)
* MongoDB persistence
* REST API architecture

---

# Requirements

This project is designed for Linux / Ubuntu environments.

Required software:

* Git
* Make
* Java 21
* MongoDB 8.x
* Bash

---

# Quick Start (Recommended)

Use the included Makefile to install everything automatically.

```bash
make setup
make run
```

This will:

* Install Java 21
* Install MongoDB
* Install required tools
* Download Maven dependencies
* Start the backend server

---

# Run Commands

## Start backend

```bash
make run
```

## Development mode

```bash
make dev
```

## Run tests

```bash
make test
```

## Build JAR file

```bash
make package
```

Generated file:

```text
target/*.jar
```

## Clean project

```bash
make clean
```

## Re-download dependencies

```bash
make reset
```

---

# MongoDB Commands

## Start MongoDB

```bash
make mongo-start
```

## Stop MongoDB

```bash
make mongo-stop
```

## Check MongoDB status

```bash
make mongo-status
```

---

# Application Configuration

Current configuration:

```properties
spring.application.name=SolarSynBackend
spring.data.mongodb.uri=mongodb://localhost:27017/solarsync_db
spring.data.mongodb.auto-index-creation=true
server.address=0.0.0.0
server.port=8080
```

---

# API Base URL

```text
http://localhost:8080
```

Examples:

```text
http://localhost:8080/api/auth
http://localhost:8080/api/dashboard
http://localhost:8080/api/sensors
```

---

# Project Structure

```text
SolarSynBackend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
├── pom.xml
├── mvnw
├── mvnw.cmd
├── Makefile
└── README.md
```

---

# If Port 8080 Is Busy

Find process:

```bash
sudo lsof -i :8080
```

Kill process:

```bash
sudo kill -9 PID
```

---

# Troubleshooting

## Java not found

Install:

```bash
sudo apt install openjdk-21-jdk
```

## MongoDB not running

```bash
make mongo-start
```

## Maven issues

```bash
make reset
```

---

# Developer Notes

This project uses **Maven Wrapper**, so no global Maven installation is required.

All dependencies are managed automatically from:

```text
pom.xml
```

---

# Recommended Workflow

```bash
git pull
make setup
make run
```

---

# Maintainer

Rania Bouabid

Computer Engineering Student
AI / Full Stack / Embedded Systems

---
