angular.module('myApp').controller("APCCtrl", function($scope, $http){
	var y = localStorage.getItem("customerid");

	$http.get("/projectmanager/rest/project/getActiveProjectCustomer?userId="+y)
		.then(function(response){
		        $scope.activProjects = response.data;
			console.log($scope.activProjects);
			
	});
	
});	
