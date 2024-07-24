# 🧪 Pruebas Unitarias

Este módulo se encarga de realizar pruebas unitarias para validar la funcionalidad de componentes individuales del código en el Proyecto Final Testing.

## 📋 Descripción

Las pruebas unitarias son esenciales para asegurar que cada componente individual del sistema funciona correctamente de manera aislada. En este módulo, se utilizan varias herramientas y frameworks para llevar a cabo estas pruebas de manera eficiente y efectiva.

La aplicación objetivo de las pruebas es un proyecto básico de Pet Clinic con código Java simple que consta de tres capas: `model`, `repository` y `service`, simulando conexiones a bases de datos y a servicios externos.

## 🔨 Tecnologías y Herramientas Utilizadas

- **Java 21**: Lenguaje de programación principal.
- **Maven**: Herramienta de construcción y gestión de dependencias.
- **JUnit**: Framework para realizar pruebas unitarias.
- **Hamcrest**: Biblioteca para escribir aserciones en pruebas.
- **JaCoCo**: Herramienta para medir la cobertura de código en pruebas unitarias.

## ⚙️ Instalación y Configuración

1. Asegúrate de tener instalados Java 21 y Maven en tu sistema.
2. Navega al directorio del proyecto de pruebas unitarias:
   ```sh
   cd unit-test-pet-clinic
   ```
3. Construye el proyecto y descarga las dependencias:
    ```sh
    mvn clean install
   ```
## 🚀 Ejecución de Pruebas
Para ejecutar las pruebas unitarias, utiliza el siguiente comando en el directorio del proyecto de pruebas unitarias:
   ```sh
    mvn clean test
   ```

Este comando ejecutará todas las pruebas unitarias definidas en el proyecto y generará un informe de resultados.

## 📊 Informe de Cobertura
JaCoCo se utiliza para generar informes de cobertura de código. Después de ejecutar las pruebas, puedes generar el informe de cobertura utilizando el siguiente comando:
   ```sh
        mvn jacoco:report
   ```
El informe generado se encontrará en la ruta target/site/jacoco/index.html. Abre este archivo en tu navegador para ver el informe detallado de cobertura.

## 🛠️ Estructura del Proyecto

La estructura del proyecto de pruebas unitarias es la siguiente:

   ```bash
    unit-test-pet-clinic/
    ├── src/
    │   ├── main/
    │   │   └── java/    # Código fuente principal
    │   └── test/
    │       └── java/    # Código de las pruebas unitarias
    ├── pom.xml          # Archivo de configuración de Maven
    └── README.md        # Este archivo
   ```
   