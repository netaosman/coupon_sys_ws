
(function() {
	var module = angular.module("adminApp");
	
	// http://stackoverflow.com/questions/41211875/angularjs-1-6-0-latest-now-routes-not-working
	module.config([ '$locationProvider', function($locationProvider) {
		$locationProvider.hashPrefix('');
	} ]);

	// router config
	module.config(function($stateProvider, $urlRouterProvider) {
		$stateProvider.state("createCompany", {
			url : "/createCompany",
			templateUrl : "../html/adminHtml/createCompany.html",
			controller : "createCompCtrl"
			// service: "createCompApi"
		})
		$stateProvider.state("deleteCompany", {
			url : "/deleteCompany",
			templateUrl : "../html/adminHtml/deleteCompany.html",
			controller : "deleteCompCtrl"
		})
		// The Market configuration.
		$stateProvider.state("updateCompany", {
			url : "/updateCompany",
			templateUrl : "../html/adminHtml/updateComp.html",
			controller : "updateCompCtrl"
		})
		$stateProvider.state("getCompany", {
			url : "/getCompany",
			templateUrl : "../html/adminHtml/getCompany.html",
			controller : "getCompCtrl"
		})
		$stateProvider.state("Companies", {
			url : "/companies",
			templateUrl : "../html/adminHtml/getAllCompanies.html",
			controller : "companiesCtrl",
			// service: "companiesApi"
		})
		$stateProvider.state("createCust", {
			url : "/createCust",
			templateUrl : "../html/adminHtml/createCustomer.html",
			controller : "createCustCtrl as medium"
		})
		$stateProvider.state("deleteCust", {
			url : "/deleteCust",
			templateUrl : "../html/adminHtml/deleteCust.html",
			controller : "deleteCustCtrl"
		})
		$stateProvider.state("updateCust", {
			url : "/updateCust",
			templateUrl : "../html/adminHtml/updateCust.html",
			controller : "updateCustCtrl"
		})
		
		$stateProvider.state("getCust", {
			url : "/getCust",
			templateUrl : "../html/adminHtml/getCustomer.html",
			controller : "getCustCtrl"
		})
		$stateProvider.state("customers", {
			url : "/customers",
			templateUrl : "../html/adminHtml/getAllCustomers.html",
			controller : "customersCtrl"
		})

		$urlRouterProvider.when("", "/createCompany"); // first browsing postfix is
		// empty --> route it to /main
		// $urlRouterProvider.otherwise('/404'); // when no switch case matches
		// --> route to /404
	});

})();