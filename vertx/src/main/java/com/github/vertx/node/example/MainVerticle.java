package com.github.vertx.node.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MainVerticle extends AbstractVerticle {



    private final Logger logger = LoggerFactory.getLogger(MainVerticle.class);

    @Override
    public void start() throws InterruptedException {

        /** Count of services. */
        final AtomicInteger serviceCount = new AtomicInteger();

        /** List of verticles that we are starting. */
        final List<AbstractVerticle> verticles = Arrays.asList(new HelloWorldVerticle(), new WebVerticle());

        verticles.stream().forEach(verticle -> vertx.deployVerticle(verticle, deployResponse -> {

            if (deployResponse.failed()) {
                logger.error("Unable to deploy verticle " + verticle.getClass().getSimpleName(),
                        deployResponse.cause());
            } else {
                logger.info(verticle.getClass().getSimpleName() + " deployed");
                serviceCount.incrementAndGet();
            }
        }));


        /** Wake up in five seconds and check to see if we are deployed if not complain. */
        vertx.setTimer(TimeUnit.SECONDS.toMillis(5), event -> {

            if (serviceCount.get() != verticles.size()) {
                logger.error("Main Verticle was unable to start child verticles");
            } else {
                logger.info("Start up successful");
            }
        });

    }


    public static void main(final String... args) {
        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MainVerticle());
    }
}
