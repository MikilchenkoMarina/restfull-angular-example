'use strict';

var myApp = angular.module('myApp', ['ngRoute']);
myApp.config(function ($routeProvider, $httpProvider, $locationProvider) {
    $routeProvider.when("/user", {
        templateUrl: "webapp/resources/html/user.html",
        controller: "AppController"
    })
});