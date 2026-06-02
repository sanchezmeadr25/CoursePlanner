# CoursePlanner

CoursePlanner es una aplicacion web desarrollada con Spring Boot y Thymeleaf para gestionar cursos online, instructores, estudiantes e inscripciones.

La aplicacion permite que los instructores administren sus cursos y las inscripciones de sus estudiantes, mientras que los estudiantes pueden consultar el catalogo, inscribirse en cursos, revisar sus cursos inscritos y valorar los cursos realizados.

## Tecnologias utilizadas

- Java 21
- Spring Boot 4.0.6
- Spring MVC
- Spring Data JPA
- Spring Security
- Thymeleaf
- Thymeleaf Extras Spring Security
- H2 Database
- Bootstrap 5
- Bootstrap Icons
- Lombok
- Maven

## Funcionalidades principales

### Funcionalidades publicas

- Pagina principal publica.
- Pantalla de acceso.
- Eleccion de tipo de cuenta.
- Registro de estudiante.
- Registro de instructor.
- Redireccion desde `/` a `/principal`.

### Seguridad y usuarios

- Login con Spring Security.
- Logout desde el menu de usuario.
- Redireccion segun rol despues del login.
- Proteccion de rutas por roles.
- Acceso diferenciado para administrador/instructor y estudiante.
- Bloqueo de acceso a paginas protegidas sin iniciar sesion.

### Rol administrador / instructor

- Panel principal con resumen de:
  - Cursos activos.
  - Total de estudiantes.
  - Calificacion media.
  - Numero de valoraciones.
- Gestion de cursos propios.
- Creacion de cursos.
- Edicion de cursos.
- Borrado de cursos.
- Restriccion para no borrar cursos con estudiantes inscritos.
- Consulta del detalle de un curso.
- Consulta de estudiantes inscritos en un curso.
- Gestion de inscripciones:
  - Cambiar estado.
  - Actualizar progreso.
- Gestion de estudiantes:
  - Listado de estudiantes.
  - Alta de estudiante.
  - Edicion de estudiante.
  - Borrado con modal de confirmacion.
- Edicion del perfil de instructor.

### Rol estudiante

- Panel principal de estudiante con resumen de:
  - Cursos inscritos.
  - Cursos disponibles.
  - Valoraciones dadas.
- Consulta del catalogo de cursos.
- Consulta del detalle de un curso.
- Inscripcion en cursos.
- Prevencion de inscripciones duplicadas.
- Consulta de cursos inscritos.
- Valoracion de cursos.

### Validaciones destacadas

- El DNI debe tener exactamente 9 caracteres.
- La contrasena debe ser segura en los formularios:
  - Minimo 8 caracteres.
  - Una mayuscula.
  - Una minuscula.
  - Un numero.
  - Un simbolo.
- Los formularios muestran mensajes de error junto al campo correspondiente.
- Los campos obligatorios se validan desde el formulario.
- Los campos numericos usan limites cuando corresponde, por ejemplo progreso entre 0 y 100.
- Los formularios de contrasena incluyen boton para mostrar y ocultar la contrasena.



## Como ejecutar el proyecto

1. Clonar el repositorio.


2. Entrar en la carpeta del proyecto Spring Boot.

3. Ejecutar la aplicacion con Maven Wrapper.

4. Abrir la aplicacion en el navegador.

http://localhost:9000


## Base de datos

La aplicacion usa H2.

Configuracion principal:

```properties
server.port=9000
spring.datasource.url=jdbc:h2:./db/basedatos;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create-drop
```

Consola H2:

```text
http://localhost:9000/h2-console
```

Datos de acceso a H2:

| Campo | Valor |
|---|---|
| JDBC URL | `jdbc:h2:./db/basedatos` |
| Usuario | `sa` |
| Contrasena | vacia |

## Rutas principales

### Publicas

| Ruta | Descripcion |
|---|---|
| `/` | Redirige a la pagina principal |
| `/principal` | Pagina principal publica |
| `/acceso` | Login |
| `/eleccionCuenta` | Seleccion de tipo de cuenta |
| `/anadirestudiante` | Registro de estudiante |
| `/crearInstructor` | Registro de instructor |

### Administrador / instructor

| Ruta | Descripcion |
|---|---|
| `/principalAdmin` | Panel principal del instructor |
| `/admin/misCursos` | Listado de cursos del instructor |
| `/crearCurso` | Formulario para crear curso |
| `/admin/editarCurso/{id}` | Editar curso |
| `/admin/borrarCurso/{id}` | Borrar curso |
| `/admin/listaEstudiantes` | Listado de estudiantes |
| `/admin/editarEstudiante/{dni}` | Editar estudiante |
| `/admin/estudiante/borrar/{dni}` | Borrar estudiante |
| `/inscripcion/admin/inscripciones/{id}` | Gestionar inscripciones de un curso |
| `/editarInstructor/{dni}` | Editar instructor |

### Estudiante

| Ruta | Descripcion |
|---|---|
| `/principalUser` | Panel principal del estudiante |
| `/catalogo` | Catalogo de cursos |
| `/misCursosInscritos` | Cursos inscritos del estudiante |
| `/cursoDetalle/{id}` | Detalle de curso |
| `/inscripcion/nueva?cursoId={id}` | Crear inscripcion |
| `/inscripcion/estudiante/valorar` | Valorar curso |

## Estructura del proyecto

```text
CoursePlanner
├── CourserPlanner
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com.salesianostriana.dam.courserplanner
│   │   │   │       ├── controlador
│   │   │   │       ├── modelo
│   │   │   │       ├── repositorio
│   │   │   │       ├── seguridad
│   │   │   │       ├── servicio
│   │   │   │       └── Dataseed.java
│   │   │   └── resources
│   │   │       ├── static
│   │   │       │   ├── css
│   │   │       │   ├── img
│   │   │       │   └── js
│   │   │       └── templates
│   │   └── test
│   └── pom.xml
├── Fotos testing
├── Testing_no_automatizado_CoursePlanner_COMPLETO.docx
└── README.md
```

## Testing no automatizado

El proyecto incluye documentacion de pruebas manuales en:

```text
Testing_no_automatizado_CoursePlanner_COMPLETO.docx
```

Ese documento contiene:

- 54 casos de prueba.
- Tabla de errores detectados.
- Tabla de capturas recomendadas.
- Casos de login, logout, roles, estudiantes, instructores, cursos, inscripciones, valoraciones y errores.

Las capturas se organizan en:

```text
Fotos testing/
```

Dentro de esa carpeta hay una subcarpeta por cada captura:

```text
CAP-001
CAP-002
CAP-003
...
CAP-054
```

Cada carpeta corresponde a la evidencia indicada en el documento de testing.
