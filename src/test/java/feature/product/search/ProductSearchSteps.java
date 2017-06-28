package feature.product.search;

import static org.hamcrest.CoreMatchers.equalTo;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import feature.AbstractProductTestLib;

public class ProductSearchSteps extends AbstractProductTestLib {

  @Before
  public void before() {
    initWorld("/products");
  }

  @Before("@staging")
  public void beforeStaging() {
    system("staging");
  }

  @Before("@storexy")
  public void beforeCustomerXy() {
    store("storexy");
  }

  @Before("@adminPeter")
  public void beforeAdminPeter() {
    authenticate("adminPeter", "p4ssw0rd");
  }

  @After
  public void afterScenario() {
    // actions
  }

  @Given("^is system ([A-Za-z0-9]+)$")
  public void is_system(String system) throws Throwable {
    system(system);
  }

  @Given("^is a store with ID (\\d+)$")
  public void is_a_store_with_ID(int storeId) throws Throwable {
    store(Integer.toString(storeId));
  }

  @Given("^is a store ([A-Za-z0-9\\.\\-\\_\\/]+)$")
  public void is_a_store(String storeDomain) throws Throwable {
    store(storeDomain);
  }

  @Given("^is a user with name ([A-Za-z0-9]+) and password ([A-Za-z0-9]+)$")
  public void is_a_user_with_name_and_password(String username, String password) throws Throwable {
    authenticate(username, password);
  }

  @Given("^is a admin user with name ([A-Za-z0-9]+) and password ([A-Za-z0-9]+)$")
  public void is_a_admin_user_with_name_and_password(String username, String password)
      throws Throwable {
    authenticate(username, password);
  }

  @Given("^is a guest user$")
  public void is_a_guest_user() throws Throwable {
    authenticate("guest", "guest");
  }

  @Given("^are search options \"([^\"]*)\"$")
  public void are_search_options(String searchOptions) throws Throwable {
    searchOptions(searchOptions);
  }

  @When("^the user queries for \"([^\"]*)\"$")
  public void the_user_queries_for(String queryString) throws Throwable {
    productSearchGet(queryString);
  }

  @Then("^the result list should contain a product with item code ([A-Za-z0-9]+)$")
  public void the_result_list_should_contain_a_product_with_item_code(String itemCode)
      throws Throwable {
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
