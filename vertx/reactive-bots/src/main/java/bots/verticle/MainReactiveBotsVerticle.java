package bots.verticle;

import bots.infra.module.ReactiveBotsModule;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;

/**
 * @author claudioed on 25/06/17. Project vertx
 */
public class MainReactiveBotsVerticle extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    JsonObject config = new JsonObject().put("guice_binder", ReactiveBotsModule.class.getName());
    final DeploymentOptions deploymentOptions = new DeploymentOptions().setConfig(config);
    String deploymentName = "java-guice:" + FacebookWebhookVerticle.class.getName();
    vertx.deployVerticle(deploymentName,deploymentOptions);
  }

}
