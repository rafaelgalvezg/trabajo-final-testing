# language: en
@gestionarPropietarios
Feature: Gestionar propietarios

  @listarPropietarios
  Scenario: Listar todos propietarios
    Given el cliente abre el navegador
    When el cliente navega al menú encontrar propietarios
    And el cliente selecciona la opción encontrar propietarios
    Then la página debe mostrar una lista de propietarios válida

  @registrarPropietario
  Scenario Outline: Registrar un propietario
    Given el cliente tiene los siguientes datos del propietario:
      | firstName   | lastName   | address   | city   | telephone   |
      | <firstName> | <lastName> | <address> | <city> | <telephone> |
    And el cliente abre el navegador
    When el cliente navega al menú encontrar propietarios
    And el cliente selecciona la opción agregar nuevo
    And el cliente ingresa los datos del propietario
    And el cliente guarda el propietario
    Then la página debe mostrar la información del propietario registrado
    Examples:
      | firstName | lastName | address      | city | telephone |
      | John      | Doe      | 1234 Main St | NY   | 123456789 |
      | Jane      | Smith    | 5678 Elm St  | LA   | 987654321 |
      | Alice     | Johnson  | 9101 Oak St  | SF   | 555555555 |