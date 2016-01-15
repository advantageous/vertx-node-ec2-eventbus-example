package com.github.vertx.node.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldVerticle extends AbstractVerticle {

    private final Logger logger = LoggerFactory.getLogger(HelloWorldVerticle.class);
    @Override
    public void start() {
        vertx.eventBus().consumer(Services.HELLO_WORLD.toString(), message -> {
            dispatchMessage(message);
        });
    }

    private void dispatchMessage(final Message<Object> message) {

        try {
            final HelloWorldOperations operation = HelloWorldOperations.valueOf(message.body().toString());
            switch (operation) {
                case SAY_HELLO_WORLD:
                    message.reply("HELLO WORLD");
                    break;
                default:
                    logger.error("Unable to handle operation {}", operation);
                    message.reply("Unsupported operation");
            }
        }catch (final Exception ex) {
            logger.error("Unable to handle operation due to exception" + message.body(), ex);
        }
    }

}

