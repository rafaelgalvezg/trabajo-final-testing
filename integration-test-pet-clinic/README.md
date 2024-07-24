# 🧩 Pruebas de Integración

Este módulo se encarga de realizar pruebas de integración para comprobar la interacción entre diferentes componentes del sistema en el Proyecto Final Testing.

## 📋 Descripción

Las pruebas de integración son esenciales para asegurar que diferentes componentes del sistema funcionen correctamente juntos. En este módulo, se utilizan diversas herramientas y frameworks para llevar a cabo estas pruebas de manera eficiente y efectiva.

La aplicación objetivo de las pruebas de integración es la API de Petstore disponible en [Swagger Petstore](https://petstore3.swagger.io/). Esta API proporciona una interfaz para gestionar una tienda de mascotas.

Las pruebas de integración están definidas utilizando `Gherkin`, un lenguaje simple de texto plano que describe comportamientos esperados. Los archivos de características `(.feature)` contienen los escenarios de prueba escritos en Gherkin y la implementacion de esas especificaciones se realizo con `Cucumber`.

## 🔨 Tecnologías y Herramientas Utilizadas

- **Java 21**: Lenguaje de programación principal.
- **Maven**: Herramienta de construcción y gestión de dependencias.
- **Serenity BDD**: Framework para la gestión de pruebas de aceptación y automatización.
- **Cucumber**: Framework para pruebas basadas en BDD (Behavior-Driven Development).
- **Gherkin**: Lenguaje utilizado por Cucumber para definir criterios de aceptación.
- **RestAssured**: Herramienta para pruebas de servicios RESTful.


## ⚙️ Instalación y Configuración

1. Asegúrate de tener instalados Java 21 y Maven en tu sistema.
2. Navega al directorio del proyecto de pruebas de integración:
   ```sh
   cd integration-test-pet-clinic
   ```
3. Construye el proyecto y descarga las dependencias:
    ```sh
    mvn clean install
    ```
## 🚀 Ejecución de Pruebas
Para ejecutar las pruebas de integración, utiliza el siguiente comando en el directorio del proyecto de pruebas de integración: 
   ```sh
    mvn clean verify
   ```
Este comando ejecutará todas las pruebas de integración definidas en el proyecto y generará un informe de resultados.


##  📊 Informes de Resultados
Serenity genera informes detallados de los resultados de las pruebas. Después de ejecutar las pruebas, los informes estarán disponibles en la carpeta `target/site/serenity`.

## 🛠️ Estructura del Proyecto
La estructura del proyecto de pruebas de integración es la siguiente:

   ```bash
    integration-test-pet-clinic/
     ├── src/
     │   ├── main/
     │   │   └── java/    # Código fuente principal
     │   │   └── resources/
     │   └── test/
     │       ├── java/    # Código de las pruebas de integración
     │       └── resources/
     │           └── features/    # Archivos de características Gherkin
     ├── pom.xml          # Archivo de configuración de Maven
     └── README.md        # Este archivo
   ```