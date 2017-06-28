package feature;

import com.laegler.microservice.elasticsearch.model.Product;

import io.restassured.response.Response;

public abstract class AbstractProductTestLib extends AbstractTestLib {

  protected Product product;
  protected String productId = "1";

  protected Product getProductFromIndex(Object queryString) {
    return productSearchGet(queryString).body().as(Product.class);
  }

  protected Response productSearchGet(Object queryString) {
    resp(req().when().get("/search/" + queryString));
    return resp();
  }

  protected Product getProductFromDatabase(String productId) {
    return productGet(productId).body().as(Product.class);
  }

  protected Response productGet(Object productId) {
    resp(req().when().get("/" + productId));
    return resp();
  }

  protected void productPut(Product product) {
    req().when().body(product).put();
  }

  protected void productIndexPut() throws InterruptedException {
    req().when().put("/reindex");
    Thread.sleep(3000);
  }

  protected void searchOptions(String searchOptions) {
    world.setSearchOptions(searchOptions);
  }

  protected void modifyProductInDatabase(String productId, String name) {
    product = getProductFromDatabase(productId);
    product.setName(name + " shirt");
    product.setDescription(name);
    productPut(product);
  }

}
