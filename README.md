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

---

# 📋 DOCUMENTACIÓN TÉCNICA DETALLADA

## Análisis de la Estructura del Proyecto

### 1. INFORMACIÓN GENERAL

**Tipo**: API REST para gestión de dispositivos móviles
**Stack**: Java 21 + Spring Boot 4.0.0 + Maven
**Arquitectura**: Layered Architecture (3 capas)

```
api/
├── src/main/java/com/api/api/
│   ├── ApiApplication.java          # Punto de entrada
│   ├── config/
│   │   └── OpenApiConfig.java       # Configuración Swagger
│   ├── controller/
│   │   └── DeviceController.java    # Endpoints REST
│   ├── model/
│   │   └── Device.java              # Entidad de dominio
│   └── service/
│       └── DeviceService.java       # Lógica de negocio
└── resources/
    └── application.properties       # Config GCP/Database
```

---

### 2. ANÁLISIS CLASE POR CLASE

#### 🚀 **ApiApplication.java**
**Ubicación**: `com.api.api.ApiApplication`
**Tipo**: Clase principal

```java
@SpringBootApplication
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
```

**Responsabilidades**:
- Punto de entrada de la aplicación
- Inicializa el contexto de Spring
- Arranca el servidor embebido (Tomcat)
- La anotación `@SpringBootApplication` combina 3 anotaciones: `@Configuration`, `@EnableAutoConfiguration`, `@ComponentScan`

---

#### ⚙️ **OpenApiConfig.java**
**Ubicación**: `com.api.api.config.OpenApiConfig`
**Tipo**: Configuración

**Responsabilidades**:
- Configura la documentación automática con Swagger/OpenAPI 3.0
- Define metadatos de la API (título, versión, contacto, licencia)

**Configuración aplicada**:
- **Título**: "API de Catálogo de Productos"
- **Versión**: 1.0.0
- **Licencia**: Apache 2.0
- **URL Swagger**: `http://localhost:8080/swagger-ui.html`

---

#### 🎮 **DeviceController.java**
**Ubicación**: `com.api.api.controller.DeviceController`
**Tipo**: REST Controller (Capa de Presentación)

**Características**:
- Base path: `/devices`
- CORS habilitado: `@CrossOrigin(origins = "*")`
- Inyección de dependencia: `DeviceService` (constructor injection)

**Endpoints implementados**:

| Método | Endpoint | Descripción | Código HTTP |
|--------|----------|-------------|-------------|
| **GET** | `/devices` | Lista todos los dispositivos | 200 OK |
| **GET** | `/devices/{id}` | Obtiene un dispositivo por UUID | 200 OK / 404 Not Found |
| **PATCH** | `/devices/{id}/precio` | Actualiza el precio de un dispositivo | 200 OK / 404 Not Found |
| **DELETE** | `/devices/{id}` | Elimina un dispositivo | 204 No Content / 404 Not Found |

**Ejemplo de método**:
```java
@GetMapping("/{id}")
@Operation(summary = "Obtener dispositivo por ID")
@ApiResponse(responseCode = "200", description = "Dispositivo encontrado")
@ApiResponse(responseCode = "404", description = "Dispositivo no encontrado")
public ResponseEntity<Device> obtenerDispositivo(@PathVariable UUID id) {
    return deviceService.obtenerPorId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
}
```

**Patrones utilizados**:
- **Dependency Injection**: Spring inyecta `DeviceService` automáticamente
- **Optional Pattern**: Manejo seguro de valores nulos
- **ResponseEntity**: Control fino de respuestas HTTP

---

#### 📦 **Device.java**
**Ubicación**: `com.api.api.model.Device`
**Tipo**: Entidad de dominio (POJO)

**Atributos**:

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    private UUID id;                    // Identificador único
    private String name;                // Nombre del dispositivo
    private String brand;               // Marca (Apple, Samsung, etc.)
    private BigDecimal price;           // Precio (precisión decimal)
    private String operating_system;    // Sistema operativo
    private LocalDateTime release_date; // Fecha de lanzamiento
    private String image_url;           // URL de imagen
}
```

**Anotaciones Lombok**:
- `@Data`: Genera getters, setters, equals(), hashCode(), toString()
- `@NoArgsConstructor`: Constructor vacío
- `@AllArgsConstructor`: Constructor con todos los parámetros

**Decisiones de diseño**:
- `UUID` para IDs únicos globales (no secuenciales)
- `BigDecimal` para precios (evita errores de redondeo de `double`)
- `LocalDateTime` para fechas sin zona horaria

---

#### 🔧 **DeviceService.java**
**Ubicación**: `com.api.api.service.DeviceService`
**Tipo**: Service Layer (Lógica de negocio)

**Almacenamiento**:
```java
private final List<Device> devices = new ArrayList<>();
```
- **In-memory storage** (no base de datos)
- Útil para prototipos y hackathons
- Los datos se pierden al reiniciar la aplicación

**Datos iniciales**:
El constructor carga 5 dispositivos de ejemplo:
1. iPhone 15 Pro - $999.99 (iOS 17)
2. Samsung Galaxy S24 - $899.99 (Android 14)
3. Google Pixel 8 - $699.99 (Android 14)
4. OnePlus 12 - $799.99 (Android 14)
5. Xiaomi 14 Pro - $749.99 (Android 14)

**Métodos públicos**:

```java
// Lista todos los dispositivos
public List<Device> obtenerTodos() {
    return new ArrayList<>(devices); // Retorna copia defensiva
}

// Busca por ID
public Optional<Device> obtenerPorId(UUID id) {
    return devices.stream()
        .filter(d -> d.getId().equals(id))
        .findFirst();
}

// Actualiza precio
public Optional<Device> editarPrecio(UUID id, BigDecimal nuevoPrecio) {
    return obtenerPorId(id).map(device -> {
        device.setPrice(nuevoPrecio);
        return device;
    });
}

// Elimina dispositivo
public boolean eliminar(UUID id) {
    return devices.removeIf(device -> device.getId().equals(id));
}
```

**Patrones utilizados**:
- **Service Pattern**: Encapsula lógica de negocio
- **Stream API**: Operaciones funcionales sobre colecciones
- **Optional**: Evita `NullPointerException`

---

### 3. ARQUITECTURA DE CAPAS

```
┌─────────────────────────────────────┐
│   CAPA DE PRESENTACIÓN              │
│   DeviceController                  │
│   - Maneja HTTP requests/responses  │
│   - Valida entrada                  │
│   - Serializa JSON                  │
└──────────────┬──────────────────────┘
               │ (Dependency Injection)
               ↓
┌─────────────────────────────────────┐
│   CAPA DE NEGOCIO                   │
│   DeviceService                     │
│   - Lógica CRUD                     │
│   - Gestión de datos                │
│   - Validaciones de negocio         │
└──────────────┬──────────────────────┘
               │
               ↓
┌─────────────────────────────────────┐
│   CAPA DE DATOS                     │
│   Device (Model)                    │
│   List<Device> (In-Memory)          │
│   - Almacenamiento temporal         │
└─────────────────────────────────────┘
```

---

### 4. FLUJO DE UNA PETICIÓN HTTP

Ejemplo: `GET /devices/123e4567-e89b-12d3-a456-426614174000`

```
1. Cliente HTTP
   ↓
2. DeviceController.obtenerDispositivo(UUID id)
   ↓
3. DeviceService.obtenerPorId(id)
   ↓
4. Stream sobre List<Device>
   ↓
5. Optional<Device>
   ↓
6. ResponseEntity<Device> (200 OK o 404 Not Found)
   ↓
7. Serialización a JSON
   ↓
8. Respuesta HTTP al cliente
```

---

### 5. DEPENDENCIAS PRINCIPALES (pom.xml)

```xml
<!-- Web MVC -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webmvc</artifactId>
</dependency>

<!-- Documentación OpenAPI/Swagger -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.7.0</version>
</dependency>

<!-- Lombok (reducir boilerplate) -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>

<!-- DevTools (hot reload) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
</dependency>

<!-- Google Cloud Platform -->
<dependency>
    <groupId>com.google.cloud</groupId>
    <artifactId>spring-cloud-gcp-dependencies</artifactId>
    <version>5.1.0</version>
</dependency>
```

---

### 6. CONFIGURACIÓN (application.properties)

```properties
# Conexión a Cloud SQL (Google Cloud Platform)
spring.cloud.gcp.sql.instance-connection-name=<YOUR_SQUAD>:europe-southwest1:reskilling-hackathon
spring.cloud.gcp.sql.database-name=postgres

# Credenciales base de datos
spring.datasource.username=postgres
spring.datasource.password=2d>2Q7K;>gYo0YBg
```

**Nota**: La configuración de base de datos está presente pero **no se está usando** porque el proyecto usa almacenamiento in-memory.

---

### 7. RESUMEN DE RESPONSABILIDADES

| Clase | Responsabilidad | Patrón |
|-------|----------------|--------|
| **ApiApplication** | Arrancar aplicación | Main Class |
| **OpenApiConfig** | Configurar Swagger | Configuration Bean |
| **DeviceController** | Exponer API REST | REST Controller |
| **DeviceService** | Lógica de negocio | Service Layer |
| **Device** | Modelo de datos | Domain Entity (POJO) |

---

### 8. PUNTOS CLAVE DE LA ARQUITECTURA

✅ **Separación de responsabilidades**: Cada clase tiene un propósito claro
✅ **Inyección de dependencias**: Spring maneja las instancias
✅ **RESTful design**: Endpoints semánticos, métodos HTTP apropiados
✅ **Documentación automática**: Swagger UI disponible
✅ **Código limpio**: Lombok reduce boilerplate
✅ **Manejo de errores**: Uso de `Optional` y códigos HTTP apropiados

⚠️ **Limitaciones actuales**:
- Almacenamiento in-memory (datos volátiles)
- Sin persistencia en base de datos
- Sin validaciones de entrada (`@Valid`)
- Sin manejo centralizado de excepciones
- Sin autenticación/autorización

---

### 9. ENDPOINTS DE DISPOSITIVOS (DeviceController)

#### Listar todos los dispositivos
```bash
curl http://localhost:8080/devices
```

**Respuesta (200 OK)**:
```json
[
  {
    "id": "123e4567-e89b-12d3-a456-426614174000",
    "name": "iPhone 15 Pro",
    "brand": "Apple",
    "price": 999.99,
    "operating_system": "iOS 17",
    "release_date": "2023-09-22T00:00:00",
    "image_url": "https://example.com/iphone15pro.jpg"
  }
]
```

#### Obtener dispositivo por ID
```bash
curl http://localhost:8080/devices/{uuid}
```

**Respuestas**:
- `200 OK` - Dispositivo encontrado
- `404 Not Found` - Dispositivo no existe

#### Actualizar precio
```bash
curl -X PATCH http://localhost:8080/devices/{uuid}/precio \
  -H "Content-Type: application/json" \
  -d '{"precio": 899.99}'
```

**Respuestas**:
- `200 OK` - Precio actualizado
- `404 Not Found` - Dispositivo no existe

#### Eliminar dispositivo
```bash
curl -X DELETE http://localhost:8080/devices/{uuid}
```

**Respuestas**:
- `204 No Content` - Dispositivo eliminado
- `404 Not Found` - Dispositivo no existe

---

### 10. CONFIGURACIÓN DOCKER

El proyecto incluye un Dockerfile multi-stage optimizado:

```dockerfile
# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src /app/src
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
USER nonroot
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**Construir la imagen**:
```bash
docker build -t api-devices .
```

**Ejecutar contenedor**:
```bash
docker run -p 8080:8080 api-devices
```

---

### 11. DIAGRAMA DE RELACIONES ENTRE CLASES

```
ApiApplication (Entry Point)
    │
    ├─→ OpenApiConfig (Bean Configuration)
    │
    └─→ DeviceController (REST Endpoints)
        │
        ├─→ DeviceService (Dependency Injection)
        │   │
        │   └─→ Device (Model)
        │
        └─→ Device (Return Type)
```
