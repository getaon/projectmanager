angular.module('myApp').controller("EMCtrl", function($scope, $http){
	
	$http.get("/projectmanager/rest/employee/getAllEmployees")
		.then(function(response){
		        $scope.employees = response.data;
			console.log($scope.employees);
	});
					
	$http.get("/projectmanager/rest/project/getAllProjects")
	.then(function(response){
	        $scope.projects = response.data;
		console.log($scope.projects);
	});
	
	
	$scope.employeeToProject = function(){
		$("#belongForm").dialog();
		
		$scope.belong = function(){
			
			console.log($scope.employee)
			console.log($scope.project)
			
			
		
			$http.get("/projectmanager/rest/EmployeeProjectService/belongEmployeeToProject?employeeid="
					+$scope.employee+"&projectid="+$scope.project).then(function(response){
			        $scope.employeeproject = response.data;
				console.log($scope.employeeproject);
				
				$("#belongForm").dialog( "destroy" );
			});
	   };	
	}
	$scope.addRow = function(){
		
		$http.get("/projectmanager/rest/employee/createNewEmployee?firstname="
				+$scope.firstname+"&lastname="+$scope.lastname
				+"&email="+$scope.email+"&phone="+$scope.phone
				+"&username="+$scope.username+"&password="+$scope.password).then(function(response){
					$scope.employeeDB = response.data;
					console.log($scope.employeeDB);	
				
				if($scope.employeeDB.id == 0 ){
					alert("problem has found.." +
							"make shour you fill all form");
					
				}else{
					$http.get("/projectmanager/rest/employee/getAllEmployees")
					.then(function(response){
					        $scope.employees = response.data;
						console.log($scope.employees);
					});
					alert("new Employee added!");
					$("#create").dialog( "destroy" );
				}
				
				$scope.username="";	
				$scope.password="";	
				$scope.firstname="";	
				$scope.lastname="";		
				$scope.email="";	
				$scope.phone="";
	 	});
		
	};
	
	$scope.RemoveRow = function(index){
		if(confirm("confirm delete please...")==true){
			$http.get("/projectmanager/rest/employee/deleteEmployee?id="
					+$scope.employees[index].id).then(function(response){
						$scope.RemployeeDB = response.data;
					console.log($scope.RemployeeDB);
					
					if($scope.RemployeeDB.id==0){
						$http.get("/projectmanager/rest/employee/getAllEmployees")
						.then(function(response){
						        $scope.employees = response.data;
							console.log($scope.employees);
						});
					}else{
						alert("delete could not happend");
					}						
			});	
		}
	};
	
	$scope.editRow = function(index){					
		$("#create").dialog();
		$("#username").hide();
		$("#password").hide();
		$("#send").hide();
		$("#update").show();
		
		$scope.firstname = $scope.employees[index].firstname;
		$scope.lastname = $scope.employees[index].lastname;
		$scope.email = $scope.employees[index].email; 
		$scope.phone = $scope.employees[index].phone;
		
		$scope.edit = function(){
			if(confirm("confirm update please") == true){
	
				$http.get("/projectmanager/rest/employee/updateEmployee?id="+
						$scope.employees[index].id+"&firstname="
						+$scope.firstname+"&lastname="+$scope.lastname
						+"&email="+$scope.email+"&phone="+$scope.phone
						+"&user=10").then(function(response){
							$scope.employeeUpdate = response.data;
							console.log($scope.employeeUpdate);
								
					if($scope.employeeUpdate.id == 0){
						
						$http.get("/projectmanager/rest/employee/getAllEmployees")
						.then(function(response){
						        $scope.employees = response.data;
							console.log($scope.employees);
						});
						
					}else{
						alert("update went wrong!");
					}		
					
					$scope.firstname = "";
					$scope.lastname = "";
					$scope.email = ""; 
					$scope.phone = "";
				});	
				$("#create").dialog( "destroy" );
		   };
	  };
	}; 
	
	$scope.button = function(){
		$("#username").show();
		$("#password").show();
		$("#send").show();
		$("#update").hide();
		$("#create").dialog();
	};

});