#language en
#encoding utf-8

@managerPets
Feature: Management Pets

  Background:
    Given El cliente configura la URI base de la API
    And El cliente configura el header Content-Type con "application/json"

  @addOwners
  Scenario Outline: Resgistrar mascota exitosamente
    Given el cliente tiene los datos de una nueva mascota
    """
     {
          "id": <id>,
          "category": {
              "id": "<categoryId>",
              "name": "<categoryName>"
          },
          "name": "<name>",
          "photoUrls": [
              "<photoUrls>"
              ],
          "tags": [
              {
                  "id": <tagsId>,
                  "name": "<tagsName>"
              }
          ],
          "status": "<status>"
      }
    """
    When el usuario envia la peticion POST a "/pet" con los datos de la mascota
    Then el servidor debe retornar un status 200
    Examples:
      | id   | categoryId | categoryName | name   | photoUrls     | tagsId | tagsName   | status    |
      | 1001 | 1          | Dogs         | Winnie | url-photo-dog | 1      | Dachshund  | available |
      | 1002 | 2          | Cats         | Tom    | url-photo-cat | 1      | abyssinian | available |

  @getPetsById
  Scenario Outline: Consultar mascota por id
    Given el cliente tiene el id <idPet> de una mascota
    When el cliente envia la peticion GET a <path>
    Then el servidor debe retornar un status <statusCode>
    Examples:
      | idPet | path   | statusCode |
      | 1001  | "pet/" | 200        |
      | 1002  | "pet/" | 200        |


  @updatePets
  Scenario Outline: Actualizar un tipo de mascota
    Given el cliente tiene los detalles de un tipo de mascota actualizado
       """
         {
              "id": <id>,
              "category": {
                  "id": "<categoryId>",
                  "name": "<categoryName>"
              },
              "name": "<name>",
              "photoUrls": [
                  "<photoUrls>"
                  ],
              "tags": [
                  {
                      "id": <tagsId>,
                      "name": "<tagsName>"
                  }
              ],
              "status": "<status>"
          }
        """
    When el cliente realiza una peticion PUT a "/pet" con los datos actualizados
    Then el servidor debe retornar un status 200
    Examples:
      | id   | categoryId | categoryName | name          | photoUrls       | tagsId | tagsName   | status    |
      | 1001 | 1          | Dogs         | Winnie update | url-photo-dog-2 | 1      | Dachshund  | available |
      | 1002 | 2          | Cats         | Tom update    | url-photo-cat-2 | 1      | abyssinian | available |

  @deletePets
  Scenario: Eliminar un tipo de mascota
    When el cliente realiza una peticion DELETE a "/pet/{id}" con id tipo de mascota eliminado 1002
    Then el servidor debe retornar un status 200



