/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

/** @module hello-module-js/hello_world_service_interface */
var utils = require('vertx-js/util/utils');
var Vertx = require('vertx-js/vertx');

var io = Packages.io;
var JsonObject = io.vertx.core.json.JsonObject;
var JHelloWorldServiceInterface = com.github.vertx.node.example.HelloWorldServiceInterface;

/**
 @class
*/
var HelloWorldServiceInterface = function(j_val) {

  var j_helloWorldServiceInterface = j_val;
  var that = this;

  /**

   @public
   @param message {string} 
   @param resultHandler {function} 
   */
  this.hello = function(message, resultHandler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] === 'string' && typeof __args[1] === 'function') {
      j_helloWorldServiceInterface["hello(java.lang.String,io.vertx.core.Handler)"](message, function(ar) {
      if (ar.succeeded()) {
        resultHandler(ar.result(), null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public

   */
  this.close = function() {
    var __args = arguments;
    if (__args.length === 0) {
      j_helloWorldServiceInterface["close()"]();
    } else throw new TypeError('function invoked with invalid arguments');
  };

  // A reference to the underlying Java delegate
  // NOTE! This is an internal API and must not be used in user code.
  // If you rely on this property your code is likely to break if we change it / remove it without warning.
  this._jdel = j_helloWorldServiceInterface;
};

/**
 Create a HelloWorldServiceInterface implementation.

 @memberof module:hello-module-js/hello_world_service_interface
 @param vertx {Vertx} vertx 
 @return {HelloWorldServiceInterface} HelloWorldServiceInterface
 */
HelloWorldServiceInterface.create = function(vertx) {
  var __args = arguments;
  if (__args.length === 1 && typeof __args[0] === 'object' && __args[0]._jdel) {
    return utils.convReturnVertxGen(JHelloWorldServiceInterface["create(io.vertx.core.Vertx)"](vertx._jdel), HelloWorldServiceInterface);
  } else throw new TypeError('function invoked with invalid arguments');
};

/**

 @memberof module:hello-module-js/hello_world_service_interface
 @param vertx {Vertx} 
 @param address {string} 
 @return {HelloWorldServiceInterface}
 */
HelloWorldServiceInterface.createProxy = function(vertx, address) {
  var __args = arguments;
  if (__args.length === 2 && typeof __args[0] === 'object' && __args[0]._jdel && typeof __args[1] === 'string') {
    return utils.convReturnVertxGen(JHelloWorldServiceInterface["createProxy(io.vertx.core.Vertx,java.lang.String)"](vertx._jdel, address), HelloWorldServiceInterface);
  } else throw new TypeError('function invoked with invalid arguments');
};

// We export the Constructor function
module.exports = HelloWorldServiceInterface;