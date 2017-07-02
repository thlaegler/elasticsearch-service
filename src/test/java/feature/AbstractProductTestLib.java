package feature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.laegler.microservice.elasticsearch.model.Product;

import io.restassured.response.Response;

public abstract class AbstractProductTestLib extends AbstractTestLib {

  protected static final Logger LOG = LoggerFactory.getLogger(AbstractProductTestLib.class);

  protected Product product;
  protected String productId = "1";

  protected Product getProductFromIndex(Object queryString) {
    LOG.info("getProductFromIndex({})", queryString);

    return productSearchGet(queryString).body().as(Product.class);
  }

  protected Response productSearchGet(Object queryString) {
    LOG.info("productSearchGet({})", queryString);

    resp(req().when().get("/search/" + queryString));
    return resp();
  }

  protected Product getProductFromDatabase(String productId) {
    LOG.info("getProductFromDatabase({})", productId.toString());

    return productGet(productId).body().as(Product.class);
  }

  protected Response productGet(Object productId) {
    LOG.info("productGet({})", productId.toString());

    resp(req().when().get("/" + productId));
    return resp();
  }

  protected void productPut(Product product) {
    LOG.info("productPut({})", product.toString());

    req().when().body(product).put();
  }

  protected void productIndexPut() throws InterruptedException {
    LOG.info("productPut()");

    req().when().put("/reindex");
    Thread.sleep(3000);
  }

  protected void searchOptions(String searchOptions) {
    LOG.info("productPut({})", searchOptions);

    world.setSearchOptions(searchOptions);
  }

  protected void modifyProductInDatabase(String productId, String name) {
    LOG.info("modifyProductInDatabase({}, {})", productId, name);

    product = getProductFromDatabase(productId);
    product.setName(name + " shirt");
    product.setDescription(name);
    productPut(product);
  }

}
