'use strict';

App.controller('CompanyController', ['$scope', 'CompanyService', function($scope, CompanyService) {
    var self = this;
    self.company={id:null,name:'',address:'',city:'',country:'',email:'',phoneNumber:'',owner:''};
    self.companies=[];

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

    self.updateCompany = function(company, id) {
        CompanyService.updateCompany(company, id)
            .then(
                self.fetchAllCompanies,
                function(errResponse) {
                    console.error('Error while updating company.');
                }
            );
    };

    self.fetchAllCompanies();

    self.submit = function() {
        if(self.company.id == null) {
            console.log('Saving New Company', self.company);
            // replace or remove special escape characters in the returned JSON
            var ownerInput = JSON.parse(angular.toJson([{ name: self.company.owner }], 1));
            // alert(ownerInput);
            self.company.owner = ownerInput;
            self.createCompany(self.company);
        } else {
            self.updateCompany(self.company, self.company.id);
            console.log('Company updated with id ', self.company.id);
        }
        self.reset();
    };

    self.edit = function(id) {
        console.log('id to be edited', id);
        // alert(id);
        for(var i = 0; i < self.companies.length; i++) {
            if(self.companies[i].id === id) {
                self.company = angular.copy(self.companies[i]);
                break;
            }
        }
    };

    self.reset = function() {
        self.company={id:null,name:'',address:'',city:'',country:'',email:'',phoneNumber:'',owner:''};
        $scope.myForm.$setPristine(); //reset Form
    };
}]);
