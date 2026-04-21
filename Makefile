.PHONY: help setup setup-tools setup-mongo install run dev test package clean reset mongo-start mongo-stop mongo-status doctor

MONGO_VERSION=8.0.12
MONGO_GPG=/usr/share/keyrings/mongodb-server-8.0.gpg
MONGO_LIST=/etc/apt/sources.list.d/mongodb-org-8.0.list

help:
	@echo "Available commands:"
	@echo "  make setup        Install Java 21, Git, make, curl, gnupg, MongoDB 8.0.12, then resolve Maven deps"
	@echo "  make install      Resolve Maven dependencies with Maven Wrapper"
	@echo "  make run          Run Spring Boot app"
	@echo "  make dev          Alias for run"
	@echo "  make test         Run tests"
	@echo "  make package      Build jar"
	@echo "  make clean        Clean target/"
	@echo "  make reset        Remove local Maven repo cache and resolve again"
	@echo "  make mongo-start  Start MongoDB service"
	@echo "  make mongo-stop   Stop MongoDB service"
	@echo "  make mongo-status Show MongoDB service status"
	@echo "  make doctor       Check installed tool versions"

setup: setup-tools setup-mongo install

setup-tools:
	sudo apt-get update
	sudo apt-get install -y openjdk-21-jdk git make curl gnupg ca-certificates

setup-mongo:
	@if dpkg -s mongodb-org >/dev/null 2>&1; then \
		echo "MongoDB already installed."; \
	else \
		set -e; \
		UBUNTU_CODENAME=$$(. /etc/os-release && echo $$VERSION_CODENAME); \
		if [ "$$UBUNTU_CODENAME" != "noble" ] && [ "$$UBUNTU_CODENAME" != "jammy" ] && [ "$$UBUNTU_CODENAME" != "focal" ]; then \
			echo "Unsupported Ubuntu version for this Makefile: $$UBUNTU_CODENAME"; \
			echo "Supported: focal (20.04), jammy (22.04), noble (24.04)"; \
			exit 1; \
		fi; \
		curl -fsSL https://pgp.mongodb.com/server-8.0.asc | sudo gpg -o $(MONGO_GPG) --dearmor; \
		echo "deb [ arch=amd64,arm64 signed-by=$(MONGO_GPG) ] https://repo.mongodb.org/apt/ubuntu $$UBUNTU_CODENAME/mongodb-org/8.0 multiverse" | sudo tee $(MONGO_LIST) >/dev/null; \
		sudo apt-get update; \
		sudo apt-get install -y \
			mongodb-org=$(MONGO_VERSION) \
			mongodb-org-database=$(MONGO_VERSION) \
			mongodb-org-server=$(MONGO_VERSION) \
			mongodb-mongosh \
			mongodb-org-shell=$(MONGO_VERSION) \
			mongodb-org-mongos=$(MONGO_VERSION) \
			mongodb-org-tools=$(MONGO_VERSION) \
			mongodb-org-database-tools-extra=$(MONGO_VERSION); \
		echo "mongodb-org hold" | sudo dpkg --set-selections; \
		echo "mongodb-org-database hold" | sudo dpkg --set-selections; \
		echo "mongodb-org-server hold" | sudo dpkg --set-selections; \
		echo "mongodb-mongosh hold" | sudo dpkg --set-selections; \
		echo "mongodb-org-mongos hold" | sudo dpkg --set-selections; \
		echo "mongodb-org-tools hold" | sudo dpkg --set-selections; \
	fi
	sudo systemctl daemon-reload
	sudo systemctl enable mongod
	sudo systemctl start mongod

install:
	chmod +x mvnw
	./mvnw dependency:resolve

run:
	chmod +x mvnw
	./mvnw spring-boot:run

dev: run

test:
	chmod +x mvnw
	./mvnw test

package:
	chmod +x mvnw
	./mvnw clean package -DskipTests

clean:
	chmod +x mvnw
	./mvnw clean

reset:
	rm -rf ~/.m2/repository
	chmod +x mvnw
	./mvnw dependency:resolve

mongo-start:
	sudo systemctl start mongod

mongo-stop:
	sudo systemctl stop mongod

mongo-status:
	sudo systemctl status mongod --no-pager

doctor:
	@echo "Java version:"
	@java -version || true
	@echo
	@echo "Git version:"
	@git --version || true
	@echo
	@echo "Make version:"
	@make --version | head -n 1 || true
	@echo
	@echo "MongoDB version:"
	@mongod --version | head -n 1 || true
	@echo
	@echo "Mongosh version:"
	@mongosh --version || true