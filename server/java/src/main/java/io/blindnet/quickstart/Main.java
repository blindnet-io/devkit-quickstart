package io.blindnet.quickstart;

import com.fasterxml.jackson.databind.node.TextNode;
import io.blindnet.jwt.TokenBuilder;
import io.blindnet.jwt.TokenPrivateKey;
import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;

public class Main {

  public static void main(String[] args) {

    int port = 3000;

    String appId = "78f5fc15-5645-4f4f-8e1d-0792b7d89acd";
    String key = "EgPThokIzi0oGkOGPOuC3zA63/b39ZAefcbxpegoHog=";

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
