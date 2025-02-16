# Corplat Project

Este proyecto es una aplicación Spring Boot para la gestión de precios.

## 📦 Requisitos previos

- JDK 17
- Maven 3.x
- IntelliJ IDEA u otro IDE compatible

## 🚀 Cómo ejecutar la aplicación

```bash
# Compilar el proyecto
mvn clean install

# Iniciar la aplicación
mvn spring-boot:run
```

## ▶️ Ejecutar todas las pruebas
```bash
mvn verify
```

## 🛠️ Ejecutar solo las pruebas unitarias
```bash
mvn test
```

## 🔍 Ejecutar solo las pruebas de integración
```bash
mvn verify -Dgroups=integration
```

## 🔧 Ejecutar solo las pruebas unitarias específicas
```bash
mvn test -Dtest=NombreDeLaClaseTest
```

## ⚙️ Configuración adicional
Swagger UI disponible en: http://localhost:8080/swagger-ui.html

H2 Console disponible en: http://localhost:8080/h2-console

## 👨‍💻 Autor
Ismael García Martín - Desarrollador de la prueba.