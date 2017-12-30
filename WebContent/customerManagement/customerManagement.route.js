	angular.module('myApp').config(function($routeProvider) {
		
		$routeProvider
		
		.when("/CM", {
	        templateUrl : "customerManagement/customersmangement.html",
	        controller: "CMCtrl"
	    })
	    
	    .when("/CMAC", {
	        templateUrl : "customerManagement/activeCustomer.html",
	        controller: "CMCtrl"
	    })
		
	});