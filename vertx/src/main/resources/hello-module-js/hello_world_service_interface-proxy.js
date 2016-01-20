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
!function (factory) {
  if (typeof require === 'function' && typeof module !== 'undefined') {
    factory();
  } else if (typeof define === 'function' && define.amd) {
    // AMD loader
    define('hello-module-js/hello_world_service_interface-proxy', [], factory);
  } else {
    // plain old include
    HelloWorldServiceInterface = factory();
  }
}(function () {

  /**
 @class
  */
  var HelloWorldServiceInterface = function(eb, address) {

    var j_eb = eb;
    var j_address = address;
    var closed = false;
    var that = this;
    var convCharCollection = function(coll) {
      var ret = [];
      for (var i = 0;i < coll.length;i++) {
        ret.push(String.fromCharCode(coll[i]));
      }
      return ret;
    };

    /**

     @public
     @param message {string} 
     @param resultHandler {function} 
     */
    this.hello = function(message, resultHandler) {
      var __args = arguments;
      if (__args.length === 2 && typeof __args[0] === 'string' && typeof __args[1] === 'function') {
        if (closed) {
          throw new Error('Proxy is closed');
        }
        j_eb.send(j_address, {"message":__args[0]}, {"action":"hello"}, function(err, result) { __args[1](err, result &&result.body); });
        return;
      } else throw new TypeError('function invoked with invalid arguments');
    };

    /**

     @public

     */
    this.close = function() {
      var __args = arguments;
      if (__args.length === 0) {
        if (closed) {
          throw new Error('Proxy is closed');
        }
        j_eb.send(j_address, {}, {"action":"close"});
        closed = true;
        return;
      } else throw new TypeError('function invoked with invalid arguments');
    };

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
      if (closed) {
        throw new Error('Proxy is closed');
      }
      j_eb.send(j_address, {"vertx":__args[0]}, {"action":"create"});
      return;
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
      if (closed) {
        throw new Error('Proxy is closed');
      }
      j_eb.send(j_address, {"vertx":__args[0], "address":__args[1]}, {"action":"createProxy"});
      return;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  if (typeof exports !== 'undefined') {
    if (typeof module !== 'undefined' && module.exports) {
      exports = module.exports = HelloWorldServiceInterface;
    } else {
      exports.HelloWorldServiceInterface = HelloWorldServiceInterface;
    }
  } else {
    return HelloWorldServiceInterface;
  }
});