package com.github.vertx.node.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.ext.web.handler.sockjs.SockJSHandlerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebVerticle extends AbstractVerticle {


    private final Logger logger = LoggerFactory.getLogger(WebVerticle.class);

    @Override
    public void start() {

        final Router router = Router.router(vertx);


        router.route("/hello/*").handler(event -> handleHttpRequest(event.request()));


        final BridgeOptions options = new BridgeOptions().
                addOutboundPermitted(
                        new PermittedOptions().
                                setAddress("metrics")
                );

        router.route("/eventbus/*").handler(SockJSHandler.create(vertx).bridge(options));

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(8080);
    }

    private void handleHttpRequest(final HttpServerRequest httpRequest) {

        /* Invoke using the event bus. */
        vertx.eventBus().send(Services.HELLO_WORLD.toString(),
                HelloWorldOperations.SAY_HELLO_WORLD.toString(), response -> {

            if (response.succeeded()) {
                /* Send the result to the http connection. */
                httpRequest.response().end(response.result().body().toString());
            } else {
                logger.error("Can't send message to hello service", response.cause());
                //noinspection ThrowableResultOfMethodCallIgnored
                httpRequest.response().setStatusCode(500).end(response.cause().getMessage());
            }
        });
    }
}

