'use strict';

var myApp = angular.module('myApp', ['ngRoute']);
myApp.config(function ($routeProvider, $httpProvider, $locationProvider) {
    $routeProvider.when("/contact", {
        templateUrl: "/resources/html/user.html"
    })
});