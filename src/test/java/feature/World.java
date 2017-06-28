package feature;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.Getter;
import lombok.Setter;

// @Builder
@Getter
@Setter
public class World {

  private String baseUri = "http://elasticsearch-service:8080";
  private String basePath = "/";

  private String system = "staging";
  private String store = "storexy";
  private String grantType = "api-code";
  private String user;
  private String password;
  private String searchOptions;

  private RequestSpecification reqSpec;
  private ResponseSpecification respSpec;
  private Response resp;

  public World() {
    reqSpec = given().basePath(this.basePath).baseUri(this.baseUri);
  }

  public World(String basePath) {
    this.basePath = basePath;
    reqSpec = given().basePath(this.basePath).baseUri(this.baseUri);
  }

  public World(String baseUri, String basePath) {
    this.baseUri = baseUri;
    this.basePath = basePath;
    reqSpec = given().basePath(this.basePath).baseUri(this.baseUri);
  }
}
