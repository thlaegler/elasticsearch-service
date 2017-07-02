@staging
Feature: Search for products in elasticsearch index

  @storeXY @adminPeter
  Scenario: As a admin user I want to get a list of products as search result.
    Given is a store batteries.example
    Given is a admin user with name dummy and password p4ssw0rd
    When the user queries for "green"
    Then the result list should contain a product with item code 231

  Scenario: As a user I want to get a list of products as search result.
  	Given is system staging
    Given is a store batteries.example
    Given is a user with name dummy and password p4ssw0rd
    When the user queries for "green"
    Then the result list should contain a product with item code 231

  Scenario: As a guest user I want to get a category aggregated list of products as search result.
    Given is a store with ID 123
    Given is a guest user
    Given are search options "categoryAggregation"
    When the user queries for "green"
    Then the result list should contain a product with item code 231
