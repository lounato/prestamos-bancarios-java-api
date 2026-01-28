# API REST de solicitudes de préstamos personales en JAVA

Aplicación desarrollada en Java para gestionar solicitudes de préstamos personales, pudiendo consultarlas, crearlas y modificar sus estados.

## Instrucciones para ejecutar el proyecto

### Requisitos
- Java JDK 21
- Maven
- IntelliJ IDEA (Community o Ultimate)

### Pasos para ejecutar el proyecto
```
git clone https://github.com/lounato/prestamos-bancarios-java-api.git
- Abrir el proyecto en el IDE
- Esperar a que Maven descargue las dependencias
- Ejecutar la aplicación desde la clase principal PrestamosBancariosApplication o botón de “Run”.
```
La API estará disponible en http://localhost:8080/solicitudPrestamo.

## Arquitectura y decisiones técnicas

La aplicación sigue una arquitectura REST en capas, separando responsabilidades para mejorar la mantenibilidad y escalabilidad.
- Controller: Capa de presentación. Expone los endpoints REST y gestiona las peticiones HTTP
- Service: Contiene la lógica de negocio
- Entity: Modelo de solicitudes de préstamo con sus atributos correspondientes

### Estructura del proyecto
```
data
└─ solicitudes.json
src/main/
├─ java/com/caixabanktech/prestamosbancarios
│  ├─ controller/ # Controladores
│  │  └─ SolicitudPrestamoController.java
│  ├─ dto/
│  │  └─ EstadoDTO.java
│  │  └─ SolicitudPrestamoDTO.java
│  ├─ entity/ # Entidades
│  │  └─ SolicitudPrestamo.java
│  ├─ enums/  # Enumerados
│  │  └─ DivisaEnum.java
│  │  └─ EstadoSolicitudEnum.java
│  ├─ service/  # Servicios
│  │  ├─ impl/
│  │  │	 └─ SolicitudPrestamoServiceImpl.java
│  │  └─ SolicitudPrestamoService.java
│  ├─ PrestamosBancariosApplication.java
├─ resources/
│  └─ application.properties
test/
├─ com/caixabanktech/prestamosbancarios
│  └─ SolicitudPrestamoServiceTest.java
└─ PrestamosBancariosApplicationTest.java
```

### Decisiones técnicas
- <ins>Spring Boot</ins>: Framework que permite configurar una aplicación para API REST de manera rápida y sencilla.
- <ins>Almacenamiento en formato JSON</ins>: Persistencia basada en estructura JSON, gestionada mediante la librería Jackson para serialización y deserialización de datos.
- <ins>Bean Validation</ins>: Validación de datos de entrada mediante anotaciones (`@NotNull`, `@NotBlank`, etc.).
- <ins>Testing con JUnit y Mockito</ins>: Implementación de tests unitarios para validar el comportamiento del servicio y las funcionalidades definidas.
- <ins>Ejecución directa desde el IDE</ins>: Simplifica el desarrollo y las pruebas locales sin necesidad de despliegue externo.

### Funcionalidades
- Consulta de todas las solicitudes existentes</br>
  `GET http://localhost:8080/solicitudPrestamo/all`
- Consulta de solicitudes por id</br>
  `GET http://localhost:8080/solicitudPrestamo/{id}`
- Creación de nueva solicitud</br>
  `POST http://localhost:8080/solicitudPrestamo/`</br>
  Request body:</br>
  ```
  {
    "nombreSolicitante": "Nombre Apellidouno Apellidodos",
    "importeSolicitado": 10000,
    "divisa": "EUR",
    "documentoIdentidad": "11111111A"
  }
  ```
- Actualizar estado de una solicitud</br>
  Flujo de estados permitido: Pendiente -> Aprobada o Rechazada / Aprobada -> Cancelada</br>
  `PUT http://localhost:8080/solicitudPrestamo/{id}/estado`</br>
  Request body:
  ```
  {
    "estadoSolicitud": "APROBADA"
  }
  ```

## Mejoras y extensiones futuras

### Funcionalidades
- Gestión de usuarios (clientes y gestores)
- Gestión de roles y permisos
- Persistencia de datos en base de datos externa
- Histórico de cambios

### Técnicas / Arquitectura
- Despliegue en servidor o contenedores
- Autenticación
- Mayor cobertura de tests con JUnit y Mockito
- Documentación de la API con Swagger / OpenAPI
