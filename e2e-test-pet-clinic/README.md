# 🌐 Pruebas End-to-End (E2E)

Este módulo se encarga de realizar pruebas End-to-End para verificar el correcto funcionamiento de toda la aplicación desde el principio hasta el fin en el Proyecto Final Testing.

## 📋 Descripción

Las pruebas End-to-End son esenciales para asegurar que todas las partes del sistema funcionen correctamente juntas en un entorno real. En este módulo, se utilizan diversas herramientas y frameworks para llevar a cabo estas pruebas de manera eficiente y efectiva.

La aplicación objetivo de las pruebas End-to-End es el proyecto [Spring Framework Pet Clinic](https://spring-framework-petclinic-qctjpkmzuq-od.a.run.app). Esta aplicación proporciona una interfaz completa para gestionar una clínica veterinaria, simulando una aplicación real con varias capas y funcionalidades.

### Definición y Implementación

Las pruebas End-to-End están definidas utilizando `Gherkin`, un lenguaje simple y legible por humanos que describe comportamientos esperados en forma de escenarios. Los archivos de características (`.feature`) contienen estos escenarios de prueba escritos en Gherkin.

La implementación de las especificaciones de Gherkin se realiza con `Cucumber`, que permite la ejecución de estos escenarios como pruebas automatizadas. Para la automatización de la interfaz de usuario y la interacción con el navegador, se utiliza `Selenium`. Selenium ayuda a simular acciones de usuario en la aplicación web, como clics, entradas de texto y navegaciones.

Para mantener el código de prueba organizado y reutilizable, se emplea el patrón de diseño Page Object. Este patrón encapsula la lógica de la interfaz de usuario dentro de clases específicas, mejorando la mantenibilidad y legibilidad del código de prueba.
## 🔨 Tecnologías y Herramientas Utilizadas

- **Java 21**: Lenguaje de programación principal.
- **Maven**: Herramienta de construcción y gestión de dependencias.
- **Selenium**: Herramienta para la automatización de navegadores web.
- **Serenity BDD**: Framework para la gestión de pruebas de aceptación y automatización.
- **Cucumber**: Framework para pruebas basadas en BDD (Behavior-Driven Development).
- **Gherkin**: Lenguaje utilizado por Cucumber para definir criterios de aceptación.

## ⚙️ Instalación y Configuración

1. Asegúrate de tener instalados Java 21 y Maven en tu sistema.
2. Navega al directorio del proyecto de pruebas End-to-End:
   ```sh
   cd e2e-test-pet-clinic
   ```
3. Ejecuta el siguiente comando para instalar las dependencias del proyecto:
   ```sh
    mvn clean install
   ```
## 🚀 Ejecución de Pruebas
Para ejecutar las pruebas End-to-End, utiliza el siguiente comando en el directorio del proyecto de pruebas End-to-End: 
   ```sh
     mvn clean verify
   ```
Este comando ejecutará todas las pruebas End-to-End definidas en el proyecto y generará un informe de resultados.

## 📊 Informes de Resultados
Serenity genera informes detallados de los resultados de las pruebas. Después de ejecutar las pruebas, los informes estarán disponibles en la carpeta `target/site/serenity`.

## 🛠️ Estructura del Proyecto
La estructura del proyecto de pruebas End-to-End es la siguiente:
   ```bash
     e2e-test-pet-clinic/
      ├── src/
      │   ├── main/
      │   │   └── java/    # Código fuente principal
      │   │   └── resources/
      │   └── test/
      │       ├── java/    # Código de las pruebas End-to-End
      │       └── resources/
      │           └── features/    # Archivos de características Gherkin
      ├── pom.xml          # Archivo de configuración de Maven
      └── README.md        # Este archivo
   ```