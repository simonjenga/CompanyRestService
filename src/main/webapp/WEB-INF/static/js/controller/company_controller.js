'use strict';

App.controller('CompanyController', ['$scope', 'CompanyService', function($scope, CompanyService) {
    var self = this;
    self.company={id:null,name:'',address:'',city:'',country:'',email:'',phoneNumber:'',owner:''};
    self.companies=[];
    self.editowner={id:null,name:''};
    self.newowner='';

    self.fetchAllCompanies = function() {
        CompanyService.fetchAllCompanies()
            .then(
                function(d) {
                    self.companies = d;
                },
                function(errResponse) {
                    console.error('Error while fetching companies');
                }
            );
    };

    self.createCompany = function(company) {
        CompanyService.createCompany(company)
            .then(
                self.fetchAllCompanies,
                function(errResponse) {
                    console.error('Error while creating company.');
                }
            );
    };

    self.updateCompany = function(id, company) {
    	CompanyService.updateCompany(id, company)
            .then(
                self.fetchAllCompanies,
                function(errResponse) {
                    console.error('Error while updating company.');
                }
            );
    };
    
    self.addOwner = function(id, newowner) {
    	//alert('Company ID: ' + id);
    	for(var i = 0; i < self.companies.length; i++) {
            if(self.companies[i].id === id) {
                self.company = angular.copy(self.companies[i]);
                self.company.owner = '';
				self.editowner.name = '';
				self.editowner.id = '';                                          
                break;
            }
        }
    	CompanyService.addOwner(id, newowner)
            .then(
                self.fetchAllCompanies,
                function(errResponse) {
                    console.error('Error while adding owner.');
                }
            );
    };

    self.fetchAllCompanies();

    self.submit = function() {
        if(self.company.id == null) { // adding a new company
            console.log('Saving New Company', self.company);
            // replace or remove special escape characters in the returned JSON
            var ownerInput = JSON.parse(angular.toJson([{ name: self.company.owner }], 1));
            // alert(ownerInput);
            self.company.owner = ownerInput;
            self.createCompany(self.company);
        } else {
        	if (self.editowner.id == '') { // adding a new company owner
        		// alert('Company ID: ' + self.company.id + ' ,New Owner Name: ' + self.company.owner + ' ,New Owner ID: ' + self.editowner.id);
        		var ownerName = angular.toJson({ name: self.company.owner }, 1);
        		self.newowner = ownerName;
				self.addOwner(self.company.id, self.newowner); 
        	} else { // editing a company owner
            	alert('Company ID: ' + self.company.id + ' ,Owner ID: ' + self.editowner.id + ' ,Name: ' + self.editowner.name);
            	var ownerNameAndID = JSON.parse(angular.toJson([{ id: self.editowner.id, name: self.company.owner }], 1));
            	alert(ownerNameAndID);
            	self.company.owner = ownerNameAndID;
            	self.updateCompany(self.company.id, self.company);
                console.log('Company updated with id ', self.company.id);
        	}
        }
        self.reset();
    };

    self.edit = function(companyId, ownerId) {
        console.log('id to be edited', companyId);
        if (ownerId == undefined || ownerId == '') {
            alert('Please select an Owner!');
            return;
        }
        for(var i = 0; i < self.companies.length; i++) {
            if(self.companies[i].id === companyId) {
                self.company = angular.copy(self.companies[i]);
                //alert(self.companies[i].owner[0].id + " " + self.companies[i].owner[0].name);
                //alert(self.companies[i].owner.length);
				if(self.companies[i].owner.length == 1) {
					alert("First Alert "+ ownerId);
					alert(self.companies[i].owner[0].id + ", " + self.companies[i].owner[0].name);
					self.company.owner = self.companies[i].owner[0].name;
					self.editowner.name = self.companies[i].owner[0].name;
					self.editowner.id = self.companies[i].owner[0].id;
				} else {
					for(var x = 0; x < self.companies[i].owner.length; x++) {
					    if(self.companies[i].owner[x].id == ownerId) {
						    alert(self.companies[i].owner[x].id + ", " + self.companies[i].owner[x].name);
							self.company.owner = self.companies[i].owner[x].name;
							self.editowner.name = self.companies[i].owner[x].name;
							self.editowner.id = self.companies[i].owner[x].id;
					    }
				    }
				}                        
                break;
            }
        }
    };
    
    self.reset = function() {
        self.company={id:null,name:'',address:'',city:'',country:'',email:'',phoneNumber:'',owner:''};
        $scope.myForm.$setPristine(); //reset Form
    };
}]);
