// 1 - get the angular applicaiton module.
var module = angular.module("adminApp");

// 2 - register controller into the app module
// give it a name, supply a ctor function
module.service("createCompApi", createCompCotr);

function createCompCotr($http) {
    this.newCompany = {};
    var self = this;

    this.createComp = function () {
        $http
            .put(
            'http://localhost:8080/coupon_sys_ws/webapi/admin/createCompany',
            this.newCompany).then(function (resp) {
                alert('Company added!')
            }, function (err) {
                alert('error in adding Company!')
            })
    }
}
module.service("companiesApi", companiesApiCotr);

function companiesApiCotr($http) {
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

}

module.service("deleteCompApi", deleteCompApiCotr);

function deleteCompApiCotr($http) {
    this.compObj = {};
    var self = this;
    this.compId = 0;

    this.deleteComp = function () {
        var promise = $http
            .delete('http://localhost:8080/coupon_sys_ws/webapi/admin/' + this.compId, this.compObj)
        promise.then(function (res) {
            alert('Company Deleted');
        }, function (err) {
            console.log(err)
            alert('Erorr in deleting Company')
        });
    }
}
