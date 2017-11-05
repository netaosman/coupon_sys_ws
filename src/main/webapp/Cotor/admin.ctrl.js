

var module = angular.module("adminApp");
module.controller("AdminCtrl", AdminCotor);

var debug2 = 0;

function AdminCotor($http) {
    this.newCompany = {};
    this.companies = [];
    var self = this;

    //Create Company method.
    this.createCompany = function () {
        $http.put('http://localhost:8080/coupon_sys_ws/webapi/admin/createCompany')
            .then(
            function (resp) {
                alert('Company added!')
                console.log(resp.data);
                self.newCompany = resp.data;
            },
            function (err) {
                console.log(err);
                alert('error in adding company!');
            })
    }
    this.getCompanies = function () {
        $http.get('http://localhost:8080/coupon_sys_ws/webapi/admin/companies')
            .then(
            function (resp) {
                alert('success')
                console.log(resp.data);
                self.companies = resp.data;
            },
            function (err) {
                alert('error')
            })
    }

}
