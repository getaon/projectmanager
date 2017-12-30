
	angular.module('myApp').controller("CMCtrl", function($scope, $http){
	
					$http.get("/projectmanager/rest/customer/getAllCustomers")
						.then(function(response){
						        $scope.customers = response.data;
							console.log($scope.customers);
					});
					
					$http.get("/projectmanager/rest/customer/getActiveCustomers")
					.then(function(response){
					        $scope.ActiveCustomers = response.data;
						console.log($scope.customers);
					});
					
					$scope.addRow = function(){
						if($scope.companyname != undefined
								  && $scope.companynumber!= undefined 
								  && $scope.contactname !=  undefined
								  && $scope.email != undefined 
								  && $scope.phone != undefined
								  && $scope.username != undefined
								  && $scope.password != undefined){
							
						$http.get("/projectmanager/rest/customer/createNewCustomer?companyname="
								+$scope.companyname+"&companynumber="+$scope.companynumber
								+"&contactname="+$scope.contactname+"&email="+$scope.email
								+"&phone="+$scope.phone+"&username="+$scope.username
								+"&password="+$scope.password)
								.then(function(response){
						        $scope.createdCustomer = response.data;
							console.log($scope.createdCustomer);
							if($scope.createdCustomer.id == 0){
								alert("problem has found..");
							}else{

								$http.get("/projectmanager/rest/customer/getAllCustomers")
								.then(function(response){
								        $scope.customers = response.data;
									console.log($scope.customers);
								});
								$("#createCustomerForm").dialog( "destroy" );
								alert("new Customer added!");
							}
							
							$scope.username="";	
							$scope.password="";	
							$scope.companyname="";	
							$scope.companynumber="";		
							$scope.contactname="";		
							$scope.email="";	
							$scope.phone="";
						});
						
					}else{
						alert("fill all form pleas");
					}
				};
					
					$scope.removeRow = function(index){
						
						if(confirm("confirm delete please") == true){
							$http.get("/projectmanager/rest/customer/deleteCustomer?id="
									+$scope.customers[index].id).then(function(response){
										$scope.deletedCustomer = response.data;
									console.log($scope.deletedCustomer);
									
									if($scope.deletedCustomer.id==0){
										$http.get("/projectmanager/rest/customer/getAllCustomers")
										.then(function(response){
										        $scope.customers = response.data;
											console.log($scope.customers);
										});
									}else{
										alert("delete could not happend");
									}						
							});			
						}
					};
					
					$scope.editRow = function(index){
						$("#createCustomerForm").dialog();
						$("#user").hide();
						$("#send").hide();
						$("#update").show();

						$scope.companyname = $scope.customers[index].companyname;
						$scope.companynumber = $scope.customers[index].companynumber;
						$scope.contactname = $scope.customers[index].contactname;
						$scope.email = $scope.customers[index].email; 
						$scope.phone = $scope.customers[index].phone;
												
						$scope.edit = function(){
							if(confirm("confirm update please") == true){

								$http.get("/projectmanager/rest/customer/updateCustomer?id="+
										$scope.customers[index].id+"&companyname="
										+$scope.companyname+"&companynumber="+$scope.companynumber
										+"&contactname="+$scope.contactname+"&email="+$scope.email
										+"&phone="+$scope.phone+"&user="+$scope.customers[index].user)
										.then(function(response){
											$scope.customerUpdate = response.data;
											console.log($scope.customerUpdate);
												
									if($scope.customerUpdate.id == 0){
										$http.get("/projectmanager/rest/customer/getAllCustomers")
										.then(function(response){
										        $scope.customers = response.data;
											console.log($scope.customers);
										});
										
									}else{
										alert("update went wrong!");
									}	
									
									$scope.companyname = "";
									$scope.companynumber = "";
									$scope.contactname = "";
									$scope.email = ""; 
									$scope.phone = "";
								});
								$("#createCustomerForm").dialog( "destroy" );
						    };
					    };		
			        };	
			        
					$scope.button = function(){
						$("#createCustomerForm").dialog();
						$("#username").show();
						$("#password").show();
						$("#send").show();
						$("#update").hide();
						
					};
			});