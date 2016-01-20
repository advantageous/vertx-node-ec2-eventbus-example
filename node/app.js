var http = require('http');
var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');

// initialize the event bus when we initialize the server
var EventBus = require('vertx3-eventbus-client');
// this will typically come from a config, but this will suffice for now
var eventBus = new EventBus("http://localhost:8080/eventbus/");

var routes = require('./routes/index');
var users = require('./routes/users');

var app = express();

// add application middleware to pass eventbus to controllers
app.use(function(req, res, next) {
  req.eventBus = eventBus;
  next();
});

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

// uncomment after placing your favicon in /public
//app.use(favicon(path.join(__dirname, 'public', 'favicon.ico')));
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', routes);
app.use('/users', users);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  var err = new Error('Not Found');
  err.status = 404;
  next(err);
});

// error handlers

// development error handler
// will print stacktrace
if (app.get('env') === 'development') {
  app.use(function(err, req, res, next) {
    res.status(err.status || 500);
    res.render('error', {
      message: err.message,
      error: err
    });
  });
}

// production error handler
// no stacktraces leaked to user
app.use(function(err, req, res, next) {
  res.status(err.status || 500);
  res.render('error', {
    message: err.message,
    error: {}
  });
});

// create the Express web server once the event bus is ready so we know we can use it straight away.
eventBus.onopen = function() {
  // this will now expose an Express web server on port 3000
  http.createServer(app).listen(3000, function() {
    console.log('Express server listening on port 3000');
  });
};
