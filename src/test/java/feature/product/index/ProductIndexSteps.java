package feature.product.index;

import static org.hamcrest.CoreMatchers.equalTo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import feature.AbstractProductTestLib;

public class ProductIndexSteps extends AbstractProductTestLib {

  protected static final Logger LOG = LoggerFactory.getLogger(ProductIndexSteps.class);

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
  // ("@staging")
  public void afterScenario() {
    modifyProductInDatabase(productId, "blue");
  }

  @Given("^is a change on a product in database$")
  public void is_a_change_on_a_product_in_database() throws Throwable {
    LOG.info("Given is a change on a product in database");

    modifyProductInDatabase(productId, "yellow");
  }

  @When("^the user triggers reindexing$")
  public void the_user_triggers_reindexing() throws Throwable {
    LOG.info("When the user triggers reindexing");

    productIndexPut();
  }

  @Then("^the changed product should appear in index$")
  public void the_changed_product_should_appear_in_index() throws Throwable {
    LOG.info("Then the changed product should appear in index");

    productSearchGet("yellow").then().body("[0].id", equalTo(productId));
  }

}
