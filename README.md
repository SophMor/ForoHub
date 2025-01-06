## Foro Hub
:construction: Proyecto en construcción :construction:


* Descripción del proyecto 

 Este proyecto consiste en una API REST desarrollada en Java con el framework Spring Boot. Su propósito principal es simular un foro en línea donde los usuarios registrados puedan crear, leer y eliminar tópicos. La autenticación de los usuarios se maneja mediante JSON Web Tokens (JWT), lo que garantiza que las operaciones de creación y eliminación sean seguras.

## :hammer:Funcionalidades del proyecto

- `Listar Tópicos (GET /tópicos)`: Obtener una lista de todos los tópicos existentes. Esto permite a los usuarios ver los temas disponibles en el foro.
![image-alt](https://github.com/SophMor/ForoHub/blob/main/listTopicos.png)

- `Crear Tópico (POST /tópicos)`: Permitir la creación de nuevos tópicos. Esta funcionalidad es clave para la interacción del usuario. Requiere autenticación mediante JWT.
![image-alt](https://github.com/SophMor/ForoHub/blob/main/CreateTopico.png)
- `Autenticación (POST /auth)`: Implementar un sistema de autenticación usando JSON Web Tokens (JWT) para asegurar que solo usuarios registrados puedan crear o eliminar tópicos. 
![image-alt](https://github.com/SophMor/ForoHub/blob/main/loginBearer.png)

- `Eliminar Tópico (DELETE /tópicos/{id})`: Permitir la eliminación de un tópico específico . Esta funcionalidad también requiere autenticación.
![image-alt](https://github.com/SophMor/ForoHub/blob/main/DeleteTopico.png)

## Requisitos previos:

- Java Development Kit (JDK)
- IDE 
- Creación schema en MYSQL [proyecto-foro]
- Herramienta de pruebas (ej: Insomnia)

## Pasos para ejecutar el proyecto:

1. Clonar el repositorio

2. Importar el proyecto al IDE
3. Configurar la base de datos : Ingresando Usuario. Contrasena y nombre del Schema en el application.properties
4. Ejecutar el aplicativo.
5. Probar los endpoints : Teniendo en cuenta tomar el JWToken del login y autenticarlo en los demás métodos
![image-alt](https://github.com/SophMor/ForoHub/blob/main/BearerToken.png)
