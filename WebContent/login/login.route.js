	angular.module('myApp').config(function($routeProvider) {
		$routeProvider
	
		.when("/", {
	    	templateUrl : "login/login.html",
	        controller : "LoginCtrl"
	    })
	});