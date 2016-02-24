'use strict';

App.factory('CompanyService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllCompanies: function() {
					return $http.get('http://localhost:8080/CompanyRestService/restservice/companies')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching companies');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createCompany: function(company){
					return $http.post('http://localhost:8080/CompanyRestService/restservice/company', company)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating company');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateCompany: function(company, id){
					return $http.put('http://localhost:8080/CompanyRestService/restservice/company/'+id, company)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating company');
										return $q.reject(errResponse);
									}
							);
			}
		
	};

}]);
