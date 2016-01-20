package com.github.vertx.node.example;


import io.vertx.codegen.annotations.ProxyClose;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.serviceproxy.ProxyHelper;

@ProxyGen
@VertxGen
public interface HelloWorldServiceInterface {



    /**
     * Create a HelloWorldServiceInterface implementation.
     * @param vertx vertx
     * @return HelloWorldServiceInterface
     */
    static HelloWorldServiceInterface create(final Vertx vertx) {
        return new HelloWorldServiceImpl();
    }


    static HelloWorldServiceInterface createProxy(final Vertx vertx,
                                                  final String address) {
        return ProxyHelper.createProxy(HelloWorldServiceInterface.class, vertx, address);

    }

    // Actual service operations here...
    void hello(final String message,
               final Handler<AsyncResult<String>> resultHandler);


    @ProxyClose
    void close();
}
