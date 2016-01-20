var express = require('express');
var router = express.Router();

// path to generated proxy module
var HelloWorldInterface = require('../../vertx/build/resources/main/hello-module-js/hello_world_service_interface-proxy');

/* GET users listing. */
// this is totally unsafe, but works as example code
router.get('/:name', function(req, res, next) {
  // we can use the service right away because the eventBus has already been initialized
  var service = new HelloWorldInterface(req.eventBus, "hello.world");
  // call the exposed method on the proxy and respond to the client with the message from vert.x
  service.hello(req.params.name, function(err, result) {
    res.send(result);
  });
});

module.exports = router;
