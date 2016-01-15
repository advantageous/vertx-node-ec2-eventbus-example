package com.github.vertx.node.example

import io.vertx.core.AbstractVerticle
import io.vertx.core.eventbus.Message
import org.slf4j.LoggerFactory

enum class HelloWorldOperations {

    SAY_HELLO_WORLD
}


class HelloWorldVerticle : AbstractVerticle() {

    private val logger = LoggerFactory.getLogger(HelloWorldVerticle::class.java)
    override fun start() {
        vertx.eventBus().consumer<Any>(Services.HELLO_WORLD.toString()) { message -> dispatchMessage(message) }
    }

    private fun dispatchMessage(message: Message<Any>) {

        try {
            val operation = HelloWorldOperations.valueOf(message.body().toString())
            when (operation) {
                HelloWorldOperations.SAY_HELLO_WORLD -> message.reply("HELLO WORLD FROM KOTLIN")
                else -> {
                    logger.error("Unable to handle operation {}", operation)
                    message.reply("Unsupported operation")
                }
            }
        } catch (ex: Exception) {
            logger.error("Unable to handle operation due to exception" + message.body(), ex)
        }

    }

}