# Spring Boot 3.0 Security with JWT Implementation
В основе проекта Spring Boot 3.0 и веб-токен JSON (JWT). Проект включает в себя следующие функции:

## Features
* Регистрация пользователя и вход в систему с аутентификацией JWT.
* Добавление задач пользователю.
* Поиск пользователя по: id.
* Поиск задач пользователя.
* Шифрование пароля
* Комментарии к задачам

## Technologies
* Spring Boot 3.0
* Spring Security
* JSON Web Tokens (JWT)
* Maven
* Lombok
* Liquibase
* MapStruct
* Redis

## Getting Started
Чтобы начать работу с проектом, вам необходимо установить на локальном компьютере следующее:

* JDK 17+
* Maven 3+
* Docker 4.31.1+


Чтобы собрать и запустить проект, выполните следующие действия:

* Клонируем репозиторий: `git clone https://github.com/hinecora/TaskManagementSystem.git`
* Перейдите в каталог проекта: cd <путь к папке проекта>\TaskManagementSystem.
* Добавьте базу данных «taskmanagementsystem» в postgres.
* Терминал(из папки с проектом): mvn clean
* Терминал(из папки с проектом): mvn package
* Терминал(из папки с проектом): docker-compose up


* Приложение будет доступно по адресу http://localhost:8080.
* SwaggerUI: http://localhost:8080/swagger-ui/index.html#/