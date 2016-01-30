package com.github.vertx.node.example;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HelloWorldServiceImpl implements HelloWorldServiceInterface {
    private final Logger logger = LoggerFactory.getLogger(HelloWorldServiceImpl.class);


    @Override
    public void hello(final String message, final Handler<AsyncResult<String>> resultHandler) {


        logger.info("HelloWorldServiceImpl hello was called {}", message);
        resultHandler.handle(Future.succeededFuture("Hello World! " + message));

    }

    @Override
    public void close() {

    }
}
