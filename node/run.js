var http = require('http');
var EventBus = require('vertx3-eventbus-client');
var eventBus = new EventBus("http://localhost:8080/eventbus/");

/** Don't call until the event bus is open. */
function onopenEventBus() {

      //Call using event bus.
      eventBus.send("HELLO_WORLD",
              "SAY_HELLO_WORLD", function(response, json) {
              console.log(json.body);
      });
}

eventBus.onopen = onopenEventBus;





//Call using REST
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
