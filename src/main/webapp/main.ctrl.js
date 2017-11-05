// 1 - get the angular applicaiton module [= phone book]
var module = angular.module("myApp")

var debug2 = 0;

// 2 - register controller into the app module
//      give it a name, supply a ctor function
module.controller("MainCtrl", MainCtrlCtor)

function MainCtrlCtor($http)
 {
    var self = this;
    this.newCompany = {};

//Create Company methode.
    this.createCompany = function ()
     {
        $http.put('http://localhost:8080/coupon_sys_ws/webapi/admin/createCompany',
            this.newCompany)
            .then(
            function (resp) {
                alert('Company added!')
            },
            function (err) {
                console.log(err);
                alert('error in adding company!');
            })
    }

}

