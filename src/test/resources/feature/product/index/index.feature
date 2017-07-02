#@staging
Feature: Re-index products into elasticsearch index

  Scenario: As a admin user I want to reindex after changing product properties
    Given is a store batteries.example
    Given is a admin user with name dummy and password p4ssw0rd
    Given is a change on a product in database
    When the user triggers reindexing
    Then the changed product should appear in index
