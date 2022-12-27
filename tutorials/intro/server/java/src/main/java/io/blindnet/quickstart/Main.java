package io.blindnet.quickstart;

import io.blindnet.jwt.TokenBuilder;
import io.blindnet.jwt.TokenPrivateKey;
import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;

public class Main {

  public static void main(String[] args) {

    int port = 3000;

    String appId = "<YOUR_APPLICATION_ID>";
    String key = "<YOUR_APPLICATION_KEY>";

    TokenBuilder tokenBuilder = new TokenBuilder(appId, TokenPrivateKey.fromString(key));

    Javalin.create(config -> {
      config.enableCorsForAllOrigins();
      config.jsonMapper(new JavalinJackson());
    })
        .get("/token/admin", ctx -> {
          String token = tokenBuilder.app();
          ctx.result(token);
        })
        .get("/token/user/{userId}", ctx -> {
          String token = tokenBuilder.user(ctx.pathParam("userId"));
          ctx.result(token);
        })
        .start(port);

    System.out.println("Listening on port " + port);
  }
}
