package com.github.vertx.node.example

import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.spi.cluster.impl.zookeeper.ZookeeperClusterManager
import org.slf4j.LoggerFactory
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.collections.forEach

enum class Services {
    HELLO_WORLD
}

public class MainVerticle : AbstractVerticle() {

    private val logger = LoggerFactory.getLogger(MainVerticle::class.java)

    override fun start() {

        /** Count of services.  */
        val serviceCount = AtomicInteger()

        /** List of verticles that we are starting.  */
        val verticles = Arrays.asList(HelloWorldVerticle(), WebVerticle())


        verticles.forEach { verticle ->
            vertx.deployVerticle(verticle) { deployResponse ->

                if (deployResponse.failed()) {
                    logger.error("Unable to deploy verticle ${verticle.javaClass.simpleName}",
                            deployResponse.cause())
                } else {
                    logger.info("${verticle.javaClass.simpleName} deployed")
                    serviceCount.incrementAndGet()
                }
            }
        }

        /** Wake up in five seconds and check to see if we are deployed if not complain.  */
        vertx.setTimer(TimeUnit.SECONDS.toMillis(5)) { event ->

            if (serviceCount.get() != verticles.size) {
                logger.error("Main Verticle was unable to start child verticles")
            } else {
                logger.info("Start up successful")
            }
        }

    }

}

fun main(args: Array<String>) {


    val logger = LoggerFactory.getLogger(MainVerticle::class.java)
    val zkConfig = Properties();
    zkConfig.setProperty("hosts.zookeeper", "127.0.0.1");
    zkConfig.setProperty("path.root", "io.vertx");
    zkConfig.setProperty("retry.initialSleepTime", "1000");
    zkConfig.setProperty("retry.intervalTimes", "3");

    val clusterManager =  ZookeeperClusterManager(zkConfig);
    val options =  VertxOptions().setClusterManager(clusterManager);

    Vertx.clusteredVertx(options) { response ->
        if (response.succeeded()) {
            val vertx = response.result();
            vertx.deployVerticle(MainVerticle())
            logger.info("Main is deployed")
        } else {
            logger.error("Issue deploying main", response.cause())
        }
    }


}
