## Running this

First install any Node modules

#### Run npm install from the node directory 
```sh
$ npm install
```

Then run your node server

#### Run Express 
```sh
$ node app.js
Express server listening on port 3000
```
Next run your vertx server

#### Run MainVerticle 
```sh
$ gradle clean shadowJar
$ find . -name "*.jar"
$ java -jar ./build/libs/vertx-1.0-SNAPSHOT-fat.jar
```

#### Hit the REST end point which calls the HelloWorldService proxy
```sh
$ curl http://localhost:3000/users/YourName
Hello World! YourName
```