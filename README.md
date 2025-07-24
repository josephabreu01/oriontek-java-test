# Proyecto de Gestión de Clientes OrionTek (CQRS)

---

## 🚀 Visión General

Este proyecto implementa un sistema de gestión de clientes para **OrionTek**, diseñado bajo el patrón arquitectónico **CQRS (Command Query Responsibility Segregation)**. Utiliza **Spring Boot 3.x** para la aplicación y **PostgreSQL** para la persistencia de datos, gestionado por Docker Compose.

El objetivo principal es separar las operaciones de escritura (comandos) de las operaciones de lectura (consultas), permitiendo optimizar cada lado de forma independiente para sus respectivos propósitos:
* **Lado de Comandos (Command Side):** Enfocado en la consistencia transaccional y la validación de la lógica de negocio.
* **Lado de Consultas (Query Side):** Optimizado para la recuperación eficiente de datos, devolviendo modelos de lectura (Records) diseñados para interfaces de usuario o reportes.

---

## ✨ Características Principales

* **CQRS Pattern:** Separación clara entre modelos de escritura y lectura.
* **Spring Boot:** Framework robusto para el desarrollo rápido de microservicios.
* **Spring Data JPA & Hibernate:** Para la persistencia de datos relacionales en el lado de comandos.
* **PostgreSQL:** Base de datos relacional robusta y escalable.
* **Lombok:** Reduce el código boilerplate en entidades y Records.
* **Java Records:** Utilizados para todos los DTOs y eventos, proporcionando inmutabilidad y concisión.
* **Jakarta Validation (Bean Validation):** Para la validación de comandos de entrada.
* **Manejo Global de Excepciones:** Con `@ControllerAdvice` para respuestas de API consistentes y código limpio.
* **Swagger UI (SpringDoc OpenAPI):** Documentación interactiva de la API REST para fácil exploración y pruebas.
* **Docker & Docker Compose:** Utilizado **únicamente** para gestionar la instancia de la base de datos PostgreSQL.

## 🛠️ Tecnologías Utilizadas

* **Java 17+**
* **Spring Boot 3.3.1+**
* **Maven**
* **PostgreSQL**
* **Spring Data JPA**
* **Hibernate**
* **Lombok**
* **SpringDoc OpenAPI (Swagger UI)**
* **Docker & Docker Compose** (solo para la base de datos)

---

## 🚀 Cómo Empezar

Sigue estos pasos para levantar y ejecutar el proyecto en tu entorno local.

### Prerrequisitos

Asegúrate de tener instalado lo siguiente:

* **Java Development Kit (JDK) 17 o superior**
* **Maven 3.6+**
* **Docker Desktop** (para ejecutar la base de datos PostgreSQL)

### 1. Clonar el Repositorio

```aiignore
git clone <URL_DEL_REPOSITORIO>
cd <Carpeta donde bajaste el repositorio>
```
### 2. Configuración y Arranque de la Base de Datos con Docker Compose
Docker Compose para levantar solo la instancia de PostgreSQL.
```aiignore
docker-compose up -d db
```
Espera unos segundos a que la base de datos se inicie completamente. Puedes verificar su estado con:
```aiignore
docker-compose ps
docker-compose logs -f db # Para ver los logs de la base de datos
```

### 3. Construir y Ejecutar la Aplicación Spring Boot
Puedes construir y ejecutar la aplicación Spring Boot directamente desde tu IDE (IntelliJ IDEA, Eclipse, VS Code) o desde la línea de comandos con Maven:

Para construir el proyecto (compilar y empaquetar)
```
mvn clean install
```
Para ejecutar la aplicación
```
mvn spring-boot:run
```

### 4. Usar Swagger 
Para iteractuar con Swagger ve a la ruta :
```aiignore
http://localhost:8080/swagger-ui/index.html#/
```