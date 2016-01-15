
# Purpose

The purpose of this example is to show how to connect [Node](https://nodejs.org/en/) and [Vert.x](http://vertx.io/) 
with the [Vert.x EventBus](http://vertx.io/docs/vertx-core/java/#event_bus) using the 
[Vert.x event bus bridge](http://vertx.io/docs/vertx-tcp-eventbus-bridge/java/). We will use Java on 
[Kotlin](https://kotlinlang.org/) 
in this example. We also plan on using Vert.x built-in clustering and discovery in ***EC2***, which means we will need
to configure [Hazelcast](https://hazelcast.com/) to work in EC2. We plan on using [gradle](http://gradle.org/) 
for the build. 

Note: Don't delete the branches. There are steps in there that correspond to Wiki pages.

Another feature of Vert.x 3 that we want to use is [Service Proxies](http://vertx.io/docs/vertx-service-proxy/) 
and [Service Factories](http://vertx.io/docs/vertx-service-factory/). This should allow us to create client stubs in 
JS which will allows us to make async call services written in ***Vert.x/Java/Kotlin*** from ***JavaScript/Node*** 
(or for that matter ***JRuby***, ***Ruby***, ***Jython***, ***Python***, etc.) via the event bus and event bus bridge.

Based on research, this should be possible. Some of the research were slides in talks we saw on youtube.com 
and Vert.x docs.
We have worked with ***Vert.x*** and the ***EventBus*** before. 

> The previous section has shown how you can create a service proxy in Java. 
> However, you can consume your service directly from your browser or from a node.js application using 
> a SockJS-based proxy. --Vert.x docs

We may have to break this up in small steps.

#### Calling a proxy from JS in a browser from Vert.x docs
```
<script src="http://cdn.sockjs.org/sockjs-0.3.4.min.js"></script>
<script src="vertx-eventbus.js"></script>
<script>
  var eb = new EventBus('http://localhost:8080/eventbus');
  eb.onopen = function() {
    var SomeDatabaseService =
      require('vertx-database-js/some_database_service-proxy.js');
    var svc = new SomeDatabaseService(eb, "database-service-address");
  };
</script>
```


Arguments to services must be basic types and [data objects](https://github.com/vert-x3/vertx-codegen).
Data objects are fairly easy to create see above link. 

#### From Vert.x docs allowed types in services
```
Parameters
JSON

PRIMITIVE

List<JSON>

List<PRIMITIVE>

Set<JSON>

Set<PRIMITIVE>

Map<String, JSON>

Map<String, PRIMITIVE>

Any Enum type

Any class annotated with @DataObject

Return type
JSON

PRIMITIVE

List<JSON>

List<PRIMITIVE>

Set<JSON>

Set<PRIMITIVE>

Any Enum type

Any class annotated with @DataObject

Another proxy
```

## Why Kotlin?
We picked Kotlin because it is strongly typed, compiles fast and has great Java integration. 
It provides many of the things we love about Scala but in a more Java compatible way. At a minimum we will use Kotlin
data classes. Kotlin is also similar to Swift ([Swift is Kotlin](http://blog.translusion.com/posts/swift-is-kotlin/)).
Kotlin syntax is also a lot closer to JavaScript than Java, but still strongly typed and able to take full advantage of
the JDK and Java libs. Kotlin has constructs found in Swift and Scala, and is very approachable. 

## Why Vert.x?
Vert.x is reactive and fast. It has a lot of integration with monitoring, service discovery, and reactive streaming.
It also allows more flexibility in how we manage our IO/Threads, i.e., pure reactive or multi-reactive.
The idea is to write microservices in Node and Vert.x

## Why Node?
A lot of companies use Node and we often find ourselves integrating with Node application. 

## Why Gradle?
It is less verbose than Maven and more programmable. It is easier to hack. 


    


