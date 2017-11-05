
// 1 - get the angular applicaiton module
var module = angular.module("adminApp");
// 2 - register controller into the app module
//      give it a name, supply a ctor function
//Create Company.
module.controller("createCompCtrl", createCompCtor);
function createCompCtor($http) {
    this.newCompany = {};
    this.compObj = {};
    var self = this;
    this.createCompany = function () {
        $http.put('http://localhost:8080/coupon_sys_ws/webapi/admin/createCompany',
            this.newCompany)
            .then(
            function (res) {
                alert('You can create company only with id and name that do not exist in the DB. Please make sure that in the Get All Companies page!')
                self.compObj = res.data;
            },
            function (err) {
                console.log(err);
                alert('The company is already exists. Please change the name and the password!');
            })
    }

}

//Delete Company by ID.
module.controller("deleteCompCtrl", deleteCompCtor);

function deleteCompCtor($http) {
    this.compDelete = {};
    this.compId;
    this.compObj = {};
    var self = this;

    this.deleteComp = function () {
        var promise = $http
            .delete('http://localhost:8080/coupon_sys_ws/webapi/admin/deleteCompany/' + this.compId, this.compObj)
        promise.then(function (res) {
            alert('Company Deleted');
            self.compObj = res.data;
        }, function (err) {
            console.log(err)
            alert('The id is incorrect. Please try again.')
        });
    }
}

//Update Company. Updating email and password.
module.controller("updateCompCtrl", updateCompCtor);

function updateCompCtor($http) {
    this.compToUpdate = {};
    this.compObj = {};
    var self = this;

    this.updateComp = function () {
        var promise = $http
            .put('http://localhost:8080/coupon_sys_ws/webapi/admin/updateCompany/', this.compToUpdate)
        promise.then(function (res) {
        	alert('You can update only the password and the email!');
            self.compObj = res.data;
        }, function (err) {
            console.log(err)
             alert('The id doasnt exit in the DB. Please try again!');
        });
    }
}

//Get Company by id.
module.controller("getCompCtrl", getCompCtor);

function getCompCtor($http) {
    this.compObj = {};
    this.compId;
    var self = this;

    this.getCompById = function () {
        var promise = $http
            .get('http://localhost:8080/coupon_sys_ws/webapi/admin/company/' + this.compId, this.compObj)
        promise.then(function (res) {
        	alert("plaese make sure this id exit in the DB!");
            console.log(res.data);
            self.compObj = res.data;

        }, function (err) {
            console.log(err)
            alert('Error in getting company. The ID doesnt exist.');
        });
    }
}

//Get All Companies.
module.controller("companiesCtrl", companiesCtor);

function companiesCtor($http) {
    this.companies = [];
    var self = this;

    this.getCompanies = function () {
        var promise = $http
            .get('http://localhost:8080/coupon_sys_ws/webapi/admin/companies')
        promise.then(function (res) {
            console.log(res.data);
            self.companies = [];
            for (var i = 0; i < res.data.length; i++) {
                self.companies.push(res.data[i]);
            }
            console.log('-------');
        }, function (err) {
            console.log(err)
        });
    }
    self.getCompanies();
}

//Create Customer.
module.controller("createCustCtrl", createCustCtor);
function createCustCtor($http) {
    this.newCust = {};
    this.custObj = {};
    var self = this;

    this.createCust = function () {
        $http.put('http://localhost:8080/coupon_sys_ws/webapi/admin/createCustomer',
            this.newCust)
            .then(
            function (res) {
                alert('You can create customer, only with id and name that do not exist in the DB. Please make sure that in the Get All Companies page!');
                self.custObj = res.data;
            },
            function (err) {
                console.log(err);
                alert('This customer is already exists. Please change the name and the password!');
            })
    }

}

//Delete Customer by ID.
module.controller("deleteCustCtrl", deleteCustCtor);

function deleteCustCtor($http) {
    this.custObj = {};
    this.custId;
    var self = this;

    this.deleteCust = function () {
        var promise = $http
            .delete('http://localhost:8080/coupon_sys_ws/webapi/admin/deleteCustomer/' + this.custId, this.custObj)
        promise.then(function (res) {
            alert('Customer Deleted');
            self.custObj = res.data;
        }, function (err) {
            console.log(err)
            alert('The id is incorrect. Please try again.')
        });
    }
}

//Update Customer. Updating email and password.
module.controller("updateCustCtrl", updateCustCtor);

function updateCustCtor($http) {
    this.custToUpdate = {};
    this.custObj = {};
    var self = this;

    this.updateCust = function () {
        var promise = $http
            .put('http://localhost:8080/coupon_sys_ws/webapi/admin/updateCustomer', this.custToUpdate)
        promise.then(function (res) {
            alert('You can update only the password and the email!');
            self.custObj = res.data;
        }, function (err) {
            console.log(err)
            alert('The id doasnt exit in the DB. Please try again!')
        });
    }
}

//Get Customer by id.
module.controller("getCustCtrl", getCustCtor);

function getCustCtor($http) {
    this.custObj = {};
    this.custId;
    var self = this;

    this.getCustById = function () {
        var promise = $http
            .get('http://localhost:8080/coupon_sys_ws/webapi/admin/customer/' + this.custId, this.custObj)
        promise.then(function (res) {
        	alert("plaese make sure this id exit in the DB!");
            console.log(res.data);
            self.custObj = res.data;

        }, function (err) {
            console.log(err)
            alert('Error in getting customer. The ID doesnt exist.');
        });
    }
}

//Get All customers.
module.controller("customersCtrl", customersCtor);

function customersCtor($http) {
    this.customers = [];
    var self = this;

    this.getcustomers = function () {
        var promise = $http
            .get('http://localhost:8080/coupon_sys_ws/webapi/admin/customers')
        promise.then(function (res) {
            console.log(res.data);
            self.customers = [];
            for (var i = 0; i < res.data.length; i++) {
                self.customers.push(res.data[i]);
            }
            console.log('-------');
        }, function (err) {
            console.log(err)
        });
    }
    self.getcustomers();
}