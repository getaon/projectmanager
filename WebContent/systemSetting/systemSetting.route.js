	angular.module('myApp').config(function($routeProvider) {
	    $routeProvider
	
		.when("/systemset", {
	        templateUrl : "systemSetting/systemsettings.html",
	        controller : "system"
	    })
	});