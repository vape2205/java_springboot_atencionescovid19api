# Dockerfile
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copiar el pom.xml y descargar las dependencias
COPY pom.xml .
RUN mkdir -p src/main/java src/main/resources

# Descargar las dependencias
RUN apt-get update && apt-get install -y maven
RUN mvn dependency:go-offline -B

# Copiar el código fuente
COPY src ./src

# Compilar la aplicación con el plugin Spring Boot
RUN mvn clean package -DskipTests

# Verificar que el JAR se ha creado correctamente
RUN ls -la target/

# Compilar la aplicación
RUN #mvn package -DskipTests

# Crear la imagen final
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copiar el JAR de la etapa de compilación
#COPY --from=build /app/target/*.jar app.jar

# Copiar el JAR con su nombre específico
COPY --from=build /app/target/*.jar app.jar

# Puerto expuesto
EXPOSE 8000

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]