
# Purpose

The purpose of this example is to show how to connect [Node](https://nodejs.org/en/) and [Vert.x](http://vertx.io/) 
with the [Vert.x EventBus](http://vertx.io/docs/vertx-core/java/#event_bus) using the 
[Vert.x event bus bridge](http://vertx.io/docs/vertx-tcp-eventbus-bridge/java/). We will use Java on 
[Kotlin](https://kotlinlang.org/) 
in this example. We also plan on using Vert.x built in clustering and discovery in EC2, which means we will need
to configure [Hazelcast](https://hazelcast.com/) to work in EC2. We plan on using [gradle](http://gradle.org/) 
for the build. 

Another feature of Vert.x 3 that we want to use is [Service Proxies](http://vertx.io/docs/vertx-service-proxy/) 
and [Service Factories](http://vertx.io/docs/vertx-service-factory/). This should allow us to create client stubs in 
JS which will allows us to async call services written in Vert.x/Java/Kotlin from JavaScript/Node 
(or for that matter JRuby, Ruby, Jython, Python, etc.) via the event bus and event bus bridge (we think).

Based on research, this should be possible. Some of the research were slides in talks we saw on youtube.com.
We have worked with Vert.x and the EventBus before. 


## Why Kotlin?
We picked Kotlin because it is strongly typed, compiles fast and has great Java integration. 
It provides many of the things we love about Scala but in a more Java compatible way. At a minimum we will use Kotlin
data classes. Kotlin is also similar to Swift ([Swift is Kotlin](http://blog.translusion.com/posts/swift-is-kotlin/)).
Kotlin syntax is also a lot closer to JavaScript than Java, but still strongly typed and able to take full advantage of
the JDK and Java libs.

## Why Vert.x?
Vert.x is reactive and fast. It has a lot of integration with monitoring, service discovery, and reactive streaming.
It also allows more flexibility in how we manage our IO/Threads, i.e., pure reactive or multi-reactive.
The idea is to write microservices in Node and Vert.x

## Why Node?
A lot of companies use Node and we often find ourselves integrating with Node application. 

## Why Gradle?
It is less verbose than Maven and more programmable. It is easier to hack. 





