# Foro-API-Rest

Foro API-Rest es una API REST desarrollada en Java utilizando Spring Boot, diseñada para gestionar un foro donde los usuarios pueden publicar tópicos (preguntas o discusiones), comentar y realizar otras acciones relacionadas.

## Tecnologías utilizadas

- **Java 17**
- **Spring Boot**
- **Maven**
- **PostgreSQL**
- **Hibernate** (JPA)
- **Insomnia** (para pruebas de la API)

## Características principales

- Registro de tópicos con validaciones para evitar duplicados.
- Relación de los tópicos con usuarios mediante un ID.
- Listado paginado de tópicos con orden ascendente por fecha de creación.
- Manejo de errores HTTP personalizados para diferentes escenarios (e.g., tópicos no encontrados).

## Requisitos previos

Asegúrate de tener instalados los siguientes componentes:

- **Java 17** o superior
- **Maven**
- **PostgreSQL**

## Configuración inicial

1. Clona este repositorio:
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd ForoAlura
   ```

2. Configura la base de datos en el archivo `application.properties` (ubicado en `src/main/resources`):
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/nombre_de_tu_base_de_datos
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

3. Instala las dependencias del proyecto:
   ```bash
   mvn clean install
   ```

4. Ejecuta la aplicación:
   ```bash
   mvn spring-boot:run
   ```

## Endpoints principales

### **1. Crear un tópico**
- **POST** `/topicos`
- **Request Body (JSON):**
  ```json
  {
      "titulo": "Pregunta sobre Spring Boot",
      "mensaje": "¿Cómo configuro un DataSource en Spring Boot?",
      "idAutor": 1,
      "curso": "Java"
  }
  ```
- **Respuesta:**
  ```json
  {
      "id": 1,
      "titulo": "Pregunta sobre Spring Boot",
      "mensaje": "¿Cómo configuro un DataSource en Spring Boot?",
      "fechaCreacion": "2025-01-21T12:00:00",
      "status": true,
      "autorId": 1,
      "perfilAutor": "Estudiante",
      "correoAutor": "autor@example.com",
      "curso": "Java"
  }
  ```

### **2. Listar tópicos**
- **GET** `/topicos`
- **Parámetros opcionales:**
  - `page` (número de página, por defecto 0)
  - `size` (tamaño de la página, por defecto 10)
- **Respuesta:** Paginada y ordenada por fecha de creación (ascendente).

### **3. Obtener un tópico por ID**
- **GET** `/topicos/{id}`
- **Respuesta:**
  ```json
  {
      "id": 1,
      "titulo": "Pregunta sobre Spring Boot",
      "mensaje": "¿Cómo configuro un DataSource en Spring Boot?",
      "fechaCreacion": "2025-01-21T12:00:00",
      "status": true,
      "autorId": 1,
      "perfilAutor": "Estudiante",
      "correoAutor": "autor@example.com",
      "curso": "Java"
  }
  ```

## Tests

Puedes utilizar **Insomnia** o **Postman** para probar los endpoints. Un archivo de configuración para **Insomnia** está disponible en este repositorio.
