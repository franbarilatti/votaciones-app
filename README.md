# 🗳️ Sistema de Gestión de Votaciones

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-green)
![H2 Database](https://img.shields.io/badge/H2-Database-blueviolet)
![Build](https://img.shields.io/badge/Build-Maven-brightgreen)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

API REST desarrollada en **Java y Spring Boot** para gestionar un sistema de votaciones local. Permite registrar partidos políticos, candidatos y votos emitidos por electores, manteniendo una arquitectura limpia, probada y documentada.

---

## 🔧 Tecnologías utilizadas

- Java 17
- Spring Boot 3.5.6
- Spring Data JPA
- H2 Database (in-memory)
- Lombok
- Swagger / OpenAPI
- JUnit 5 y Mockito
- Git
- IntelliJ IDEA / VS Code

---

## 🚀 Endpoints disponibles

### Partidos Políticos
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST   | /api/partidos | Crear un partido político |
| GET    | /api/partidos | Listar todos los partidos |
| GET    | /api/partidos/{id} | Obtener partido por ID |
| DELETE | /api/partidos/{id} | Eliminar partido |

### Candidatos
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST   | /api/candidatos | Crear candidato |
| GET    | /api/candidatos | Listar todos los candidatos |
| GET    | /api/candidatos/{id} | Obtener candidato por ID |
| DELETE | /api/candidatos/{id} | Eliminar candidato |

### Votos
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST   | /api/votos | Registrar un voto |
| GET    | /api/votos/por-candidato/{id} | Total de votos por candidato |
| GET    | /api/votos/por-partido/{id} | Total de votos por partido |

> Documentación Swagger disponible en: `http://localhost:8080/swagger-ui.html`

---

## 🖼️ Capturas de Swagger

**Ejemplo de GET /api/candidatos**  
[
{
"id": 1,
"nombreCompleto": "Luke Skywalker",
"partido": {
"id": 1,
"nombre": "Lado Oscuro",
"sigla": "LO"
}
},
{
"id": 2,
"nombreCompleto": "Frodo Bolsón",
"partido": {
"id": 2,
"nombre": "Comarca Unida",
"sigla": "CU"
}
}
]

bash
Copiar código

**Ejemplo de POST /api/votos**
Request Body:
{
"candidatoId": 1
}

Response:
{
"id": 10,
"candidato": {
"id": 1,
"nombreCompleto": "Luke Skywalker"
},
"fechaEmision": "2025-10-28T09:15:32"
}

yaml
Copiar código

---

## 🖥️ Ejecución del proyecto

1. Clonar el repositorio:
```bash
git clone <URL_DEL_REPOSITORIO>
cd votaciones-app
Ejecutar la aplicación:

bash
Copiar código
./mvnw spring-boot:run
Acceder a la API:

Endpoints REST: http://localhost:8080/api/

H2 Console: http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:votacionesdb)

📊 Datos de prueba
Se incluye un data.sql que precarga:

Partidos:

Lado Oscuro (LO)

Comarca Unida (CU)

Frente de Hogwarts (FH)

Unión por los Minions (UPM)

Partido de los Cazafantasmas (PCF)

Candidatos:

Luke Skywalker → Lado Oscuro

Frodo Bolsón → Comarca Unida

Albus Dumbledore → Frente de Hogwarts

Gru → Unión por los Minions

Bill Murray → Partido de los Cazafantasmas

Votos: Distribuidos entre los candidatos para pruebas de consultas estadísticas.

🧪 Testing
Se implementaron pruebas unitarias con JUnit 5 y Mockito para servicios y controladores.

Ejecutar tests:

bash
Copiar código
./mvnw test
🧹 Buenas prácticas
Arquitectura limpia y separación de responsabilidades

DTOs para comunicación entre capas y evitar exposición de entidades JPA

Manejo de excepciones personalizado

Análisis de código con SonarLint / SonarQube
<img width="1818" height="1011" alt="image" src="https://github.com/user-attachments/assets/6da58388-a6eb-47cb-94be-d61ae9255767" />


Nombres y convenciones según estándares de Java y Spring Boot

📦 Postman
Colección de Postman en /docs/votaciones.postman_collection.json con:

Ejemplos de request y response

Datos de prueba

Endpoints listos para testing

👨‍💻 Autor
Franco Barilatti
