**API MOVIES**

Aplicación backend para gestionar un catálogo de películas.

DEPENDENCIAS: 
1. El proyecto está desarrollado con JDK 11.0.11 y Gradle 7.4.
2. Spring Boot 2.6.0.


CONFIGURACIÓN:
1. crear una base de datos en MongoDB llamada `movies` 
2. Crear los respectivos documentos que de momento son `categorias` `peliculas`.
3. El archivo application.yml está configurado para interactuar con la BD.

EJECUCIÓN:
1. La aplicación estará disponible en: `http://localhost:8020`
2. En el proyecto encontrarás las colecciones postman.
3. variables de entorno: DB_NAME_MONGO=movies;MONGODB=localhost:27017