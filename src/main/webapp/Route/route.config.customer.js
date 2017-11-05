
(function() {
	var module = angular.module("customerApp");
	
	// http://stackoverflow.com/questions/41211875/angularjs-1-6-0-latest-now-routes-not-working
	module.config([ '$locationProvider', function($locationProvider) {
		$locationProvider.hashPrefix('');
	} ]);

	// router config
	module.config(function($stateProvider, $urlRouterProvider) {
		$stateProvider.state("purchaseCoup", {
			url : "/purchaseCoup",
			templateUrl : "../html/customerHtml/purchaseCoupon.html",
			controller : "purchaseCoupCtrl"
		})
		$stateProvider.state("pureCoup", {
			url : "/pureCoup",
			templateUrl : "../html/customerHtml/getAllPurchase.html",
			controller : "getPurchaseCoupons"
		})
		$stateProvider.state("couponType", {
			url : "/couponType",
			templateUrl : "../html/customerHtml/getCoupType.html",
			controller : "getCoupTypeCtrl"
		})
		// The Market configuration.
		$stateProvider.state("couponPrice", {
			url : "/couponPrice",
			templateUrl : "../html/customerHtml/getCouponByPrice.html",
			controller : "updateCompCtrl"
		})
		$stateProvider.state("getCoup", {
			url : "/getCoup",
			templateUrl : "../html/companyHtml/getCoupon.html",
			controller : "getCoupCtrl"
		})
		$stateProvider.state("coupons", {
			url : "/coupons",
			templateUrl : "../html/companyHtml/getAllCoupons.html",
			controller : "couponsCtrl",
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

		$urlRouterProvider.when("", "/createCoup"); // first browsing postfix is
		// empty --> route it to /main
		// $urlRouterProvider.otherwise('/404'); // when no switch case matches
		// --> route to /404
	});

})();