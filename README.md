# API de Gestión de Incidencias

API REST desarrollada con Spring Boot 4.0 para gestionar incidencias en un dashboard de soporte técnico.

## Características

- CRUD completo de incidencias
- Filtrado por estado
- Documentación interactiva con Swagger/OpenAPI
- Almacenamiento en memoria (ideal para prototipado rápido)
- CORS habilitado para integración con frontends

## Tecnologías

- **Java 25**
- **Spring Boot 4.0.0**
- **Spring Web MVC**
- **Lombok** - Reducción de código boilerplate
- **SpringDoc OpenAPI 2.7.0** - Documentación automática
- **Maven** - Gestión de dependencias

## Estructura del Proyecto

```
src/main/java/com/api/api/
├── config/
│   └── OpenApiConfig.java          # Configuración de Swagger/OpenAPI
├── controller/
│   └── IncidenciaController.java   # Endpoints REST
├── model/
│   ├── Incidencia.java             # Modelo de datos
│   ├── Estado.java                 # Enum de estados
│   └── Prioridad.java              # Enum de prioridades
└── service/
    └── IncidenciaService.java      # Lógica de negocio
```

## Modelo de Datos

### Incidencia

```json
{
  "id": 1,
  "titulo": "Error en login",
  "descripcion": "Los usuarios no pueden iniciar sesión",
  "estado": "ABIERTA",
  "prioridad": "ALTA",
  "reportadoPor": "Juan Pérez",
  "asignadoA": "María García",
  "fechaCreacion": "2024-12-04T10:30:00",
  "fechaActualizacion": "2024-12-04T10:30:00"
}
```

### Estados Disponibles
- `ABIERTA` - Incidencia recién creada
- `EN_PROGRESO` - Se está trabajando en ella
- `RESUELTA` - Solución implementada
- `CERRADA` - Incidencia completamente finalizada

### Prioridades Disponibles
- `BAJA`
- `MEDIA`
- `ALTA`
- `CRITICA`

## Instalación y Ejecución

### Requisitos Previos
- Java 25 o superior
- Maven 3.6+

### Iniciar la aplicación

```bash
# Usando Maven Wrapper (recomendado)
./mvnw spring-boot:run

# O usando Maven instalado localmente
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`

### Construir el proyecto

```bash
./mvnw clean package
```

## Documentación de la API

Una vez iniciada la aplicación, accede a:

- **Swagger UI (Interfaz interactiva)**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

## Endpoints

### Obtener todas las incidencias
```http
GET /api/incidencias
```

**Respuesta exitosa (200):**
```json
[
  {
    "id": 1,
    "titulo": "Error en login",
    "descripcion": "Los usuarios no pueden iniciar sesión",
    "estado": "ABIERTA",
    "prioridad": "ALTA",
    "reportadoPor": "Juan Pérez",
    "asignadoA": null,
    "fechaCreacion": "2024-12-04T10:30:00",
    "fechaActualizacion": "2024-12-04T10:30:00"
  }
]
```

### Filtrar por estado
```http
GET /api/incidencias?estado=ABIERTA
```

### Obtener una incidencia específica
```http
GET /api/incidencias/{id}
```

**Respuestas:**
- `200 OK` - Incidencia encontrada
- `404 Not Found` - Incidencia no existe

### Crear nueva incidencia
```http
POST /api/incidencias
Content-Type: application/json

{
  "titulo": "Error en login",
  "descripcion": "Los usuarios no pueden iniciar sesión",
  "prioridad": "ALTA",
  "reportadoPor": "Juan Pérez"
}
```

**Respuesta exitosa (201):**
```json
{
  "id": 1,
  "titulo": "Error en login",
  "descripcion": "Los usuarios no pueden iniciar sesión",
  "estado": "ABIERTA",
  "prioridad": "ALTA",
  "reportadoPor": "Juan Pérez",
  "asignadoA": null,
  "fechaCreacion": "2024-12-04T10:30:00",
  "fechaActualizacion": "2024-12-04T10:30:00"
}
```

### Actualizar incidencia
```http
PUT /api/incidencias/{id}
Content-Type: application/json

{
  "titulo": "Error en login - Resuelto",
  "descripcion": "Los usuarios no pueden iniciar sesión",
  "estado": "RESUELTA",
  "prioridad": "ALTA",
  "reportadoPor": "Juan Pérez",
  "asignadoA": "María García"
}
```

**Respuestas:**
- `200 OK` - Incidencia actualizada
- `404 Not Found` - Incidencia no existe

### Eliminar incidencia
```http
DELETE /api/incidencias/{id}
```

**Respuestas:**
- `204 No Content` - Incidencia eliminada
- `404 Not Found` - Incidencia no existe

## Ejemplos de Uso con cURL

### Crear una incidencia
```bash
curl -X POST http://localhost:8080/api/incidencias \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Error en checkout",
    "descripcion": "El proceso de pago no se completa",
    "prioridad": "CRITICA",
    "reportadoPor": "Ana López"
  }'
```

### Obtener todas las incidencias
```bash
curl http://localhost:8080/api/incidencias
```

### Filtrar incidencias abiertas
```bash
curl http://localhost:8080/api/incidencias?estado=ABIERTA
```

### Actualizar una incidencia
```bash
curl -X PUT http://localhost:8080/api/incidencias/1 \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Error en checkout",
    "descripcion": "El proceso de pago no se completa",
    "estado": "EN_PROGRESO",
    "prioridad": "CRITICA",
    "reportadoPor": "Ana López",
    "asignadoA": "Carlos Ruiz"
  }'
```

### Eliminar una incidencia
```bash
curl -X DELETE http://localhost:8080/api/incidencias/1
```

## Configuración

### application.properties

```properties
spring.application.name=api

# Para cambiar el puerto (opcional)
# server.port=8081
```

## Próximas Mejoras

- [ ] Integración con base de datos (JPA + H2/PostgreSQL/MySQL)
- [ ] Sistema de autenticación y autorización
- [ ] Paginación de resultados
- [ ] Búsqueda por múltiples criterios
- [ ] Validaciones con Bean Validation
- [ ] Manejo centralizado de excepciones
- [ ] Tests unitarios e integración
- [ ] Histórico de cambios en incidencias
- [ ] Asignación automática de incidencias
- [ ] Notificaciones por email

## Contribución

Este es un proyecto de hackathon. Si deseas contribuir:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -m 'Agrega nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

## Licencia

Este proyecto está bajo la Licencia Apache 2.0.

## Contacto

Para preguntas o sugerencias, contacta al equipo de desarrollo.
