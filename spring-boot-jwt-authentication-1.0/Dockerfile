# Usa una imagen base de Maven para construir la aplicación
FROM maven:3.8.4-openjdk-17 AS builder

# Copia el código fuente y el archivo de configuración
COPY ./ /app
WORKDIR /app

# Construye la aplicación con Maven
RUN mvn clean install

# Cambia a una imagen más ligera de OpenJDK para la ejecución
FROM openjdk:17


# Establece el directorio de trabajo
WORKDIR /app

# Copia el artefacto JAR generado durante la fase de construcción
COPY --from=builder /app/target/demo-jwt-0.0.1-SNAPSHOT.jar app.jar

# Copia el archivo de propiedades
COPY ./src/main/resources/application.properties application.properties

# Expone el puerto en el que se ejecuta la aplicación
EXPOSE 8092

# Comando para ejecutar la aplicación al iniciar el contenedor
CMD ["java", "-jar", "app.jar"]
