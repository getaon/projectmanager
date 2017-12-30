	angular.module('myApp').controller("MCtrl", function($scope, $http){
		
			$http.get("/projectmanager/rest/customer/getActiveCustomers")
				.then(function(response){
				        $scope.ActiveCustomers = response.data;
					console.log($scope.ActiveCustomers);
			});
			
			$http.get("/projectmanager/rest/project/getActiveProject")
			.then(function(response){
			        $scope.activProjects = response.data;
				console.log($scope.activProjects);
			});
			
			$http.get("/projectmanager/rest/project/getEndingProject")
			.then(function(response){
			        $scope.endingProjects = response.data;
				console.log($scope.endingProjects);
			});
	
		});