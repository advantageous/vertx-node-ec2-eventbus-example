package com.github.vertx.node.example;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;


public class HelloWorldServiceImpl implements HelloWorldServiceInterface {

    @Override
    public void hello(final String message, final Handler<AsyncResult<String>> resultHandler) {

        resultHandler.handle(Future.succeededFuture("Hello World! " + message));

    }

    @Override
    public void close() {

    }
}
