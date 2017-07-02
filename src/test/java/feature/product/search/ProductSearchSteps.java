package feature.product.search;

import static org.hamcrest.CoreMatchers.equalTo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import feature.AbstractProductTestLib;

public class ProductSearchSteps extends AbstractProductTestLib {

  private static final Logger LOG = LoggerFactory.getLogger(ProductSearchSteps.class);

  @Before
  public void before() {
    initWorld("/products");
  }

  @Before("@staging")
  public void beforeSystemStaging() {
    LOG.info("Given is a system staging");

    system("staging");
  }

  @Before("@storexy")
  public void beforeStoreXy() {
    LOG.info("Given is a store storexy");

    store("storexy");
  }

  @Before("@adminPeter")
  public void beforeAdminPeter() {
    LOG.info("Given a admin user with name adminPeter");

    authenticate("adminPeter", "p4ssw0rd");
  }

  @After
  public void afterScenario() {
    // actions
  }

  @Given("^is a system ([A-Za-z0-9]+)$")
  public void is_a_system(String system) throws Throwable {
    LOG.info("Given is a system {}", system);

    system(system);
  }

  @Given("^is a store with ID (\\d+)$")
  public void is_a_store_with_ID(int storeId) throws Throwable {
    LOG.info("Given is a store with ID {}", storeId);

    store(Integer.toString(storeId));
  }

  @Given("^is a store ([A-Za-z0-9\\.\\-\\_\\/]+)$")
  public void is_a_store(String storeDomain) throws Throwable {
    LOG.info("Given is a store {}", storeDomain);

    store(storeDomain);
  }

  @Given("^is a user with name ([A-Za-z0-9]+) and password ([A-Za-z0-9]+)$")
  public void is_a_user_with_name_and_password(String username, String password) throws Throwable {
    LOG.info("Given is a user with name {} and password {}", username, password);

    authenticate(username, password);
  }

  @Given("^is a admin user with name ([A-Za-z0-9]+) and password ([A-Za-z0-9]+)$")
  public void is_a_admin_user_with_name_and_password(String username, String password)
      throws Throwable {
    LOG.info("Given is a admin user with name {} and password {}", username, password);

    authenticate(username, password);
  }

  @Given("^is a guest user$")
  public void is_a_guest_user() throws Throwable {
    LOG.info("Given is a guest user ");

    authenticate("guest", "guest");
  }

  @Given("^are search options \"([^\"]*)\"$")
  public void are_search_options(String searchOptions) throws Throwable {
    LOG.info("Given are search options {}", searchOptions);

    searchOptions(searchOptions);
  }

  @When("^the user queries for \"([^\"]*)\"$")
  public void the_user_queries_for(String queryString) throws Throwable {
    LOG.info("When the user queries for {}", queryString);

    productSearchGet(queryString);
  }

  @Then("^the result list should contain a product with item code ([A-Za-z0-9]+)$")
  public void the_result_list_should_contain_a_product_with_item_code(String itemCode)
      throws Throwable {
    LOG.info("Then the result list should contain a product with item code {}", itemCode);

    resp().then().body("[0].itemCode", equalTo(itemCode));
  }

  @Then("^the result list should contain a product with ID (\\d+)$")
  public void the_result_list_should_contain_a_product_with_ID(int id) throws Throwable {
    resp().then().body("[0].id", equalTo(id));
  }

  @Then("^the result list should contain a product with name \"([^\"]*)\"$")
  public void the_result_list_should_contain_a_product_with_name(String name) throws Throwable {
    resp().then().body("[0].name", equalTo(name));
  }

}
