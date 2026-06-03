# CoursePlanner

CoursePlanner es una aplicación web que me he montado con Spring Boot y Thymeleaf para gestionar los cursos online. La idea es que los instructores puedan controlar sus cursos y alumnos, y que los estudiantes puedan ver el catálogo, apuntarse a lo que les gusten  y dejar sus valoraciones.

## Lo que he usado (Tecnologías)

![Java](https://img.shields.io/badge/Java_21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_4.0.6-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![SQL](https://img.shields.io/badge/SQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![H2](https://img.shields.io/badge/H2_Database-007ACC?style=for-the-badge&logo=databricks&logoColor=white)
![Git](https://img.shields.io/badge/GIT-F05032?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/GITHUB-181717?style=for-the-badge&logo=github&logoColor=white)
![Bootstrap](https://img.shields.io/badge/Bootstrap_5-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-92140C?style=for-the-badge)

Para la maquinaria del proyecto he elegido este combo que puedes ver en el pom.xml:

* **Java 21** y **Spring Boot 4.0.6** (a la última).
* **Spring MVC** (para las rutas y la lógica web).
* **Spring Data JPA** (para conectar y gestionar la base de datos).
* **Spring Security** + **Thymeleaf Extras Spring Security 6** (para la gestión de accesos y roles).
* **Spring Boot Validation** (para controlar los campos obligatorios, contraseñas y DNI).
* **H2 Database** con su consola web (para base de datos en memoria).
* **Thymeleaf**, **Bootstrap 5** y **Bootstrap Icons** (para maquetar el frontend).
* **Lombok** (para quitarnos código repetitivo).
* **Maven** (para gestionar todo el pom.xml).

---

## ¿Qué hace la aplicación?

El sistema cambia según quién seas. Aquí tienes el desglose de funcionalidades:

### Zona Pública (Sin loguearse)
* Página de inicio y pantalla de login.
* Registro tanto para estudiantes como para instructores (eliges tu bando en `/eleccionCuenta`).
* Redirección automática de `/` a `/principal`.

### Seguridad (La pasarela)
* Control de acceso estricto con Spring Security. Si intentas colarte en una página protegida sin cuenta, te rebota.
* El menú se adapta según tu rol y tiene su botón de Logout bien a mano.
* Al hacer login, te manda directo a tu panel correspondiente (Admin/Instructor o Estudiante).

### Rol: Instructor / Admin
* Cuadro de mandos: Un resumen visual con los cursos activos, total de alumnos, la nota media de sus cursos y cuántas opiniones tiene.
* Gestión de Cursos (CRUD completo): Crear, editar y borrar. Ojo: No te deja borrar un curso si ya tiene alumnos matriculados (para no romper nada).
* Control de alumnos: Ver quién está apuntado a cada curso, actualizarles el progreso (de 0 a 100) o cambiar el estado de su inscripción. Además, un listado general de estudiantes con opción de editarlos o borrarlos (con un modal para no meter la pata).
* Perfil: Modificar sus propios datos de instructor.

### Rol: Estudiante
* Su panel: Resumen de sus cursos actuales, cursos que quedan libres y valoraciones que ha dejado.
* Catálogo: Ver los detalles de los cursos disponibles y matricularse con un clic (con un filtro para que no se apunte dos veces al mismo).
* Mis Cursos: Ver su progreso y puntuar los cursos que ya esté haciendo.

### Validaciones clave
* El DNI tiene que ser de 9 caracteres exactos, sí o sí.
* Contraseñas seguras obligatorias en los formularios: mínimo 8 caracteres, una mayúscula, una minúscula, un número y un símbolo.
* Los formularios te avisan del error justo al lado del campo que has puesto mal y tienen el típico ojito para mostrar/ocultar la contraseña.

---

## Cómo ponerlo en marcha (En 4 pasos)

1. Clona este repositorio en tu equipo.
2. Métete en la carpeta raíz del proyecto de Spring Boot.
3. Arranca la aplicación con el Maven Wrapper ejecutando:
   * En Windows: `mvnw spring-boot:run`
   * En Linux/Mac: `./mvnw spring-boot:run`
4. Abre tu navegador favorito y entra en: http://localhost:9000

---

## Base de datos (H2)

Para que sea descargar y listo, uso una base de datos H2 en memoria que se crea y se destruye cada vez que lanzas el proyecto. 

Si quieres ver las tablas y trastear con las consultas, puedes meterte en la consola web: http://localhost:9000/h2-console

**Datos para el login de H2:**
* JDBC URL: `jdbc:h2:./db/basedatos`
* Usuario: `sa`
* Contraseña: *(déjala en blanco)*

---

## Mapa de Rutas (Por si te pierdes)

### Públicas
* `/` y `/principal` -> Inicio.
* `/acceso` -> Login.
* `/eleccionCuenta`, `/anadirestudiante`, `/crearInstructor` -> Registros.

### Instructores
* `/principalAdmin` -> Panel de control.
* `/admin/misCursos` -> Sus cursos.
* `/crearCurso` y `/admin/editarCurso/{id}` -> Formularios de curso.
* `/admin/listaEstudiantes` -> Lista de alumnos.
* `/inscripcion/admin/inscripciones/{id}` -> Notas y progreso de los alumnos en ese curso.

### Estudiantes
* `/principalUser` -> Panel de estudiante.
* `/catalogo` -> Tienda/Catálogo de cursos.
* `/misCursosInscritos` -> Sus matrículas.
* `/inscripcion/estudiante/valorar` -> Dejar reseñas.

---

## Estructura del proyecto

Para que encuentres las cosas rápido, el código está organizado de la forma típica de Spring Boot:

```text
CoursePlanner
├── CourserPlanner
│   ├── src
│   │   ├── main
│   │   │   ├── java/.../courserplanner
│   │   │   │   ├── controlador  <-- Los controladores web (Rutas)
│   │   │   │   ├── modelo       <-- Entidades de la BD (Curso, Alumno...)
│   │   │   │   ├── repositorio  <-- Consultas con Spring Data JPA
│   │   │   │   ├── seguridad    <-- Configuración de Spring Security
│   │   │   │   ├── servicio     <-- Lógica de negocio
│   │   │   │   └── Dataseed.java <-- Datos de prueba para no empezar de cero
│   │   │   └── resources
│   │   │       ├── static       <-- El CSS, JS e imágenes
│   │   │       └── templates    <-- Las vistas en HTML con Thymeleaf
│   │   └── pom.xml
├── Fotos testing                <-- Capturas de que todo rula
├── Testing_no_automatizado...   <-- Documento con las pruebas manuales
└── README.md
