	angular.module('myApp').controller("PMCtrl", function($scope, $http){
		
		
		$http.get("http://localhost/projectmanager/rest/project/getAllProjects")
		.then(function(response) {
		        $scope.managerprojects = response.data;
			console.log("getAllProjects"+$scope.managerprojects);
	});
	
	$http.get("http://localhost/projectmanager/rest/project/getActiveProject")
	.then(function(response) {
	        $scope.ActiveProjects = response.data;
		console.log("getActiveProject"+$scope.ActiveProjects);
	});
	
	$http.get("http://localhost/projectmanager/rest/employee/getAllEmployees")
	.then(function(response){
	        $scope.employees = response.data;
		console.log("getAllEmployees"+$scope.employees);
	});
	
	$http.get("http://localhost/projectmanager/rest/customer/getAllCustomers")
	.then(function(response){
	        $scope.customers = response.data;
		console.log("getAllCustomers"+$scope.customers);
	});
	
	$http.get("/projectmanager/rest/EmployeeProjectService/getAllEmployeeProject")
	.then(function(response){
	        $scope.EmployeeProjectTable = response.data;
		console.log($scope.EmployeeProjectTable);
	});
	
	
	$scope.openBelongingForm = function(){
		$("#createEmployeeProject").dialog();
	
		$scope.belong = function(){
		      if( $scope.employee != undefined 
				  && $scope.project !=  undefined){
			
			$http.get("/projectmanager/rest/EmployeeProjectService/belongEmployeeToProject?employeeid="
					+$scope.employee+"&projectid="+$scope.project)
			.then(function(response){
			        $scope.employeeproject = response.data;
				console.log($scope.employeeproject);
				
				if($scope.employeeproject.id == 0){
					$http.get("/projectmanager/rest/EmployeeProjectService/getAllEmployeeProject")
					.then(function(response){
					        $scope.EmployeeProjectTable = response.data;
						console.log($scope.EmployeeProjectTable);
					});
					
					$http.get("/projectmanager/rest/EmployeeProjectService/getAllEmployeeProject")
					.then(function(response){
					        $scope.EmployeeProjectTable = response.data;
						console.log($scope.EmployeeProjectTable);
					});
						
						$scope.employee = "";
						$scope.project = "";
						$("#createEmployeeProject").dialog("destroy");	
				}else{
					alert("cant belong each other");
				}
			});
			
		}else{
			alert("fill the form before sending");
		}	
	  }
	};
	
	$scope.deleteRow= function (index){
		
		if(confirm("conferm delete please..")==true){
			$http.get("/projectmanager/rest/EmployeeProjectService/deleteEmployeeProject?"
					+"emproid="+$scope.EmployeeProjectTable[index].id).then(function(response){
						$scope.deletedEmployeeProject = response.data;
					
					if($scope.deletedEmployeeProject.id == 0){
						$http.get("/projectmanager/rest/EmployeeProjectService/"
								+"getAllEmployeeProject")
						.then(function(response){
						        $scope.EmployeeProjectTable = response.data;
							console.log("רשימת כל הפרוייקטים"+$scope.EmployeeProjectTable);
						});
					}else{
						alert("delete could not happend");
					}						
			});				
		};
	}; 
	
	
	$scope.addRow = function(){
		
		if($scope.projectname != undefined
				  && $scope.companyname!= undefined 
				  && $("#startdate-datepicker").val() !=  undefined
				  && $("#enddate-datepicker").val() != undefined 
				  && $scope.customerprojectmanager != undefined
				  && $scope.projectmanageremail != undefined
				  && $scope.projectmanagerphone != undefined){
		
			$http.get("/projectmanager/rest/project/createProject?projectname="
				+$scope.projectname+"&customer="+$scope.companyname
				+"&startdate="+$("#startdate-datepicker").val()
				+"&enddate="+$("#enddate-datepicker").val()
				+"&customerprojectmanager="+$scope.customerprojectmanager
				+"&projectmanageremail="+$scope.projectmanageremail
				+"&projectmanagerphone="+$scope.projectmanagerphone)
				.then(function(response){
					$scope.createdProject = response.data;

			if($scope.createdProject != null){
				
				$http.get("/projectmanager/rest/project/getAllProjects")
				.then(function(response){
				        $scope.managerprojects = response.data;

				});
				
				$http.get("http://localhost/projectmanager/rest/project/getActiveProject")
				.then(function(response) {
				        $scope.ActiveProjects = response.data;

				});
				
				alert("new Project added!");
			}else{
				alert("problem has found..");
			}
			
			$scope.projectname="";	
			$scope.companyname="";	
			$scope.startdate="";	
			$scope.enddate="";		
			$scope.customerprojectmanager="";		
			$scope.projectmanageremail="";	
			$scope.projectmanagerphone="";
		});
		$("#createProjectform").dialog("destroy");
	};
};	
	
	
	$scope.RemoveRow = function(index){
		if(confirm("conferm delete please..")==true){
			$http.get("/projectmanager/rest/project/deleteProject?id="
					+$scope.managerprojects[index].id).then(function(response){
						$scope.deletedProject = response.data;
					console.log("נמחק מה-DB"+$scope.deletedProject);
					
					if($scope.deletedProject.id == 0){
						$http.get("/projectmanager/rest/project/getAllProjects")
						.then(function(response){
						        $scope.managerprojects = response.data;
							console.log("רשימת כל הפרוייקטים"+$scope.managerprojects);
						});
					}else{
						alert("delete could not happend");
					}						
			});				
		}	
	};
	
	$scope.editRow = function(index){
		$("#createProjectform").dialog();
		$("#update").show();
		$("#send").hide();

		$scope.projectname = $scope.managerprojects[index].projectname;
		$scope.companyname = $scope.managerprojects[index].customer;
		$scope.startdate = $scope.managerprojects[index].startdate;
		$scope.enddate = $scope.managerprojects[index].enddate; 
		$scope.customerprojectmanager = $scope.managerprojects[index].customerprojectmanager;
		$scope.projectmanageremail = $scope.managerprojects[index].projectmanageremail;
		$scope.projectmanagerphone = $scope.managerprojects[index].projectmanagerphone;
								
		$scope.edit = function(){
			if(confirm("confirm update please") == true){
			
				$http.get("/projectmanager/rest/project/updateProject?id="
						+$scope.managerprojects[index].id+"&projectname="
						+$scope.projectname+"&customer="+$scope.companyname
						+"&startdate="+$("#startdate-datepicker").val()
						+"&enddate="+$("#enddate-datepicker").val()
						+"&customerprojectmanager="+$scope.customerprojectmanager
						+"&projectmanageremail="+$scope.projectmanageremail
						+"&projectmanagerphone="+$scope.projectmanagerphone)
						.then(function(response){
							$scope.projectUpdate = response.data;
							console.log("חזר עידכון פרויקט מה-DB"+$scope.projectUpdate);
								
					if($scope.projectUpdate.id == 0){
						$http.get("/projectmanager/rest/project/getAllProjects")
						.then(function(response){
						        $scope.managerprojects = response.data;
							console.log($scope.managerprojects);
						});
						$("#createProjectform").dialog("destroy");
						
					}else{
						alert("update went wrong!");
					}		
					
					$scope.projectname="";	
					$scope.companyname="";	
					$scope.startdate="";	
					$scope.enddate="";		
					$scope.customerprojectmanager="";		
					$scope.projectmanageremail="";	
					$scope.projectmanagerphone="";
				});		
				
		   };	
		};		
	};		
	
	$scope.button = function(){
		$("#createProjectform").dialog();
		$("#update").hide();
		$("#send").show();
	};

	
});