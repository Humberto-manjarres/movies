# Usa una imagen base que contenga JDK 11
FROM openjdk:11

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /usr/src/app

# Copia el archivo JAR de tu aplicación al contenedor
COPY ./build/libs/movies-1.0-SNAPSHOT.jar .

# Comando para ejecutar la aplicación cuando el contenedor se inicie
CMD ["java", "-jar", "movies-1.0-SNAPSHOT.jar"]
