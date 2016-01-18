package com.github.vertx.node.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This gets started by the MainVerticle.
 * It configures the event bus bridge and install the REST routes.
 */
public class WebVerticle extends AbstractVerticle {

    private final Logger logger = LoggerFactory.getLogger(WebVerticle.class);

    @Override
    public void start() {

        /* Create vertx web router. */
        final Router router = Router.router(vertx);


        /* Install our original "REST" handler at the /hello/ uri. */
        router.route("/hello/*").handler(event -> handleHttpRequestToHelloWorld(event.request()));


        /* Allow Hello World service to be exposed to Node.js. */
        final BridgeOptions options = new BridgeOptions()
                .addInboundPermitted(
                        new PermittedOptions().setAddress(Services.HELLO_WORLD.toString()));

        /* Configure bridge at this HTTP/WebSocket URI. */
        router.route("/eventbus/*").handler(SockJSHandler.create(vertx).bridge(options));

        /* Install router into vertx. */
        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(8080);
    }

    /** This REST endpoint if for hello.
     *  It invokes the hello world service via the event bus.
     * @param httpRequest HTTP request from vertx.
     */
    private void handleHttpRequestToHelloWorld(final HttpServerRequest httpRequest) {

        /* Invoke using the event bus. */
        vertx.eventBus().send(Services.HELLO_WORLD.toString(),
                HelloWorldOperations.SAY_HELLO_WORLD.toString(), response -> {

           /* If the response was successful, this means we were able to execute the operation on
              the HelloWorld service.
              Pass the results to the http request's response.
           */
            if (response.succeeded()) {
                /* Send the result to the http connection. */
                logger.debug("Successfully invoked HelloWorld service {}", response.result().body());
                httpRequest.response().end(response.result().body().toString());
            } else {
                logger.error("Can't send message to hello world service", response.cause());
                //noinspection ThrowableResultOfMethodCallIgnored
                httpRequest.response().setStatusCode(500).end(response.cause().getMessage());
            }
        });
    }
}
