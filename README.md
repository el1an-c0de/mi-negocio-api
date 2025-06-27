# 📦 Mi Negocio API

API REST para gestionar clientes y sus direcciones en un sistema contable y de facturación electrónica.
Este ejercicio practico es una prueba tecnica de Mi Negocio.com.ec [Instagram Post](https://www.instagram.com/p/DIeBUx_MrhZ/)

---

## 🚀 Tecnologías utilizadas

- **Java 11**
- **Spring Boot 2.7.18**
- **Spring Data JPA**
- **PostgreSQL**
- **JUnit + Mockito**
- **Maven**

---

## ⚙️ Requisitos previos

- Java 11 o superior instalado.
- PostgreSQL instalado y corriendo.
- Maven instalado (`mvn` en la línea de comandos).

---

## 🛠️ Configuración de la base de datos

1. Crear la base de datos:

   ```sql
   CREATE DATABASE mi_negocio_db;
2. Crear un usuario con permisos o usar tu usuario PostgreSQL existente.
3. Configurar el archivo ```src/main/resources/application.properties:```
    ```
   # URL de conexión
    spring.datasource.url=jdbc:postgresql://localhost:5432/minegocio
    spring.datasource.username=postgres
    spring.datasource.password=tu_password
    
    # Hibernate DDL Auto
    spring.jpa.hibernate.ddl-auto=update
    
    # Mostrar consultas SQL
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
   ```
## 🚀 Ejecución de la aplicación
Desde la terminal:
   ```sql
   ./mvnw spring-boot:run
   ```
O si tienes Maven global:
    ```
    mvn spring-boot:run
    ```
La API estará disponible en:
    ```
    http://localhost:8080
    ```
## 📚 Endpoints REST principales
| Método | Endpoint | Descripción |
|----------|---------|----------|
| POST   | ```/api/clientes```   | Crear un cliente con dirección matriz   |
| PUT    | ```/api/clientes/{id}``` | 	Editar datos del cliente   |
| DELETE    | ```/api/clientes/{id}``` | Eliminar cliente   |
| GET    | ```/api/clientes/buscar?filtro=...``` | Buscar clientes por nombre o identificación   |
| GET    | ```/api/clientes/{id}``` | Obtener cliente por ID   |
| POST    | ```/api/direcciones/{clienteId}``` | Registrar dirección adicional   |
| GET    | ```/api/direcciones/{clienteId}``` | Listar todas las direcciones del cliente   |

## 🧪 Pruebas unitarias
Para ejecutar las pruebas:
```commandline
mvn test
```
Los tests usan Mockito y no afectan tu base de datos.

## 💡 Documentación del API
documentación automática con Swagger. Abre en tu navegador:
```
http://localhost:8080/swagger-ui.html
```

## 🙌 Autor
Creado por Elian Ramirez ⚡.