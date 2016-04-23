'use strict';

App.controller('CompanyController', ['$scope', 'CompanyService', function($scope, CompanyService) {
    var self = this;
    self.company = { id:null, name:'', address:'', city:'', country:'', email:'', phoneNumber:'', owner:'' };
    self.companies = [];
    self.editowner = { id:null, name:'' };
    self.newowner = '';

    // this function fetches all companies from the database back-end
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

    // this function creates a new company
    self.createCompany = function(company) {
        CompanyService.createCompany(company)
            .then(
                self.fetchAllCompanies,
                function(errResponse) {
                    console.error('Error while creating company.');
                }
            );
    };

    // this function updates a company
    self.updateCompany = function(id, company) {
    	CompanyService.updateCompany(id, company)
            .then(
                self.fetchAllCompanies,
                function(errResponse) {
                    console.error('Error while updating company.');
                }
            );
    };
    
    // this function adds an owner to a company
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

    // makes a call to the fetchAllCompanies() function
    self.fetchAllCompanies();

    // this function submits the form with the company details
    self.submit = function() {
        if(self.company.id == null) { // adding a new company
            console.log('Saving New Company', self.company);
            // replace or remove special escape characters in the returned JSON
            var ownerInput = JSON.parse(angular.toJson([{ name: self.company.owner }], 1));
            self.company.owner = ownerInput;
            self.createCompany(self.company);
        } else {
            if (self.editowner.id == '') { // adding a new company owner
                var ownerName = angular.toJson({ name: self.company.owner }, 1);
                self.newowner = ownerName;
                self.addOwner(self.company.id, self.newowner);
            } else { // editing a company owner
                var ownerNameAndID = JSON.parse(angular.toJson([{ id: self.editowner.id, name: self.company.owner }], 1));
                self.company.owner = ownerNameAndID;
                self.updateCompany(self.company.id, self.company);
                console.log('Company updated with id ', self.company.id);
            }
        }
        self.reset();
    };

    // this function edits a company
    self.edit = function(companyId, ownerId) {
        console.log('id to be edited', companyId);
        if (ownerId == undefined || ownerId == '') {
            //alert('Please select an Owner!');
            return;
        }
        for(var i = 0; i < self.companies.length; i++) {
            if(self.companies[i].id === companyId) {
                self.company = angular.copy(self.companies[i]);
                if(self.companies[i].owner.length == 1) {
                    self.company.owner = self.companies[i].owner[0].name;
                    self.editowner.name = self.companies[i].owner[0].name;
                    self.editowner.id = self.companies[i].owner[0].id;
                } else {
                    for(var x = 0; x < self.companies[i].owner.length; x++) {
                        if(self.companies[i].owner[x].id == ownerId) {
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

    // this function resets the form fields and clears the data
    self.reset = function() {
        self.company = { id:null, name:'', address:'', city:'', country:'', email:'', phoneNumber:'', owner:'' };
        $scope.myForm.$setPristine(); //reset Form
    };
}]);
