var http = require('http');
var EventBus = require('vertx3-eventbus-client');

var eventBus = new EventBus("http://localhost:8080/eventbus");

eventBus.send("HELLO_WORLD",
        "SAY_HELLO_WORLD", function(response) {

    if (response.succeeded()) {
        /* Send the result to the http connection. */
        console.log(response.result.body);
    } else {
        console.log("Can't send message to hello service " + response.cause);
    }
});

var options = {
  host: 'localhost',
  path: '/hello',
  port: 8080
};

callback = function(response) {
  var str = '';

  response.on('data', function (chunk) {
    str += chunk;
  });

  response.on('end', function () {
    console.log("FROM SERVER " + str);
  });
}

http.request(options, callback).end();
