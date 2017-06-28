package feature;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class AbstractTestLib {
  protected World world = new World();

  protected World initWorld(String basePath) {
    world = new World(basePath);
    return world;
  }

  public World initWorld(String baseUri, String basePath) {
    world = new World(baseUri, basePath);
    return world;
  }

  protected World authenticate(String username, String password) {
    return world;
  }

  protected World system(String system) {
    world.setSystem(system);
    return world;
  }

  protected World store(String store) {
    world.setStore(store);
    return world;
  }

  protected RequestSpecification req() {
    return world.getReqSpec();
  }

  protected Response resp() {
    return world.getResp();
  }

  protected World req(RequestSpecification req) {
    world.setReqSpec(req);
    return world;
  }

  protected World resp(Response resp) {
    world.setResp(resp);
    return world;
  }

}
