	angular.module('myApp').config(function($routeProvider) {
		    $routeProvider
		     .when("/ActivePC", {
			        templateUrl : "customer/activeProjectCustomer.html",
			        controller: "APCCtrl"
		        	
		     })
		});