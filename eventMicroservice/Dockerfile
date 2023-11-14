
# Usa una imagen base de Java
FROM maven:3.8.4-openjdk-17

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo JAR de la aplicación en el contenedor
COPY target/eventMicroservice-0.0.1-SNAPSHOT.jar .

# Expone el puerto 8761 utilizado por Eureka Server
EXPOSE 8081

# Comando para ejecutar la aplicación cuando el contenedor se inicie
CMD ["java", "-jar", "eventMicroservice-0.0.1-SNAPSHOT.jar"]