Feature: Indexing products into search index

	@staging @storexy @adminPeter
  Scenario: As user I want to have a search index
		When the user queries for "green"
    Then the result list should contain a product with item code 231

  Scenario: As admin I want to reindex after changing product properties
    Given is a store www.dummy-customer.example
    Given is a admin user with name dummy and password p4ssw0rd
    Given is a change on a product in database
    When the user triggers reindexing
    Then the changed product should appear in index
