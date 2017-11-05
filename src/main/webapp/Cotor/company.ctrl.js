
// 1 - get the angular applicaiton module
var module = angular.module("companyApp");
// 2 - register controller into the app module
//      give it a name, supply a ctor function
//Create Company.
module.controller("createCoupCtrl", createCoupCtor);
function createCoupCtor($http) {
    this.newCoup = {};
    // this.newCoup.startDate = "2017-07-26T00:00:00+03:00";
    // this.newCoup.endDate = "2019-01-11T00:00:00+02:00";
    this.newCoup.image = "no iamge";
    this.coupObj = {};
    var self = this;
    this.createCoupon = function () {
        $http.put('http://localhost:8080/coupon_sys_ws/webapi/company/createCoupon',
            this.newCoup)
            .then(
            function (res) {
                alert('You can create coupon only with id and title that do not exist in the DB. Please make sure that in the Get All Coupons page!')
                self.coupObj = res.data;
            },
            function (err) {
                console.log(err);
                alert('The coupon is already exists. Please change the id and the title!');
            })
    }

}

//Delete Company by ID.
module.controller("deleteCoupCtrl", deleteCoupCtor);
function deleteCoupCtor($http) {
    this.coupDelete = {};
    this.coupId;
    this.coupObj = {};
    var self = this;

    this.deleteCoup = function () {
        var promise = $http
            .delete('http://localhost:8080/coupon_sys_ws/webapi/company/deleteCoupon/' + this.compId, this.coupObj)
        promise.then(function (res) {
            alert('Coupon Deleted');
            self.coupObj = res.data;
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
    this.coupObj = {};
    var self = this;

    this.updateComp = function () {
        var promise = $http
            .put('http://localhost:8080/coupon_sys_ws/webapi/admin/updateCompany/', this.compToUpdate)
        promise.then(function (res) {
        	alert('You can update only the password and the email!');
            self.coupObj = res.data;
        }, function (err) {
            console.log(err)
             alert('The id doasnt exit in the DB. Please try again!');
        });
    }
}

//Get Company by id.
module.controller("getCoupCtrl", getCoupCtor);

function getCoupCtor($http) {
    this.coupObj = {};
    this.coupId;
    var self = this;

    this.getCoupById = function () {
        var promise = $http
            .get('http://localhost:8080/coupon_sys_ws/webapi/company/coupon/' + this.coupId, this.coupObj)
        promise.then(function (res) {
        	alert("plaese make sure this id exit in the DB!");
            console.log(res.data);
            self.coupObj = res.data;

        }, function (err) {
            console.log(err)
            alert('Error in getting coupon. The ID doesnt exist.');
        });
    }
}

//Get All Companies.
module.controller("couponsCtrl", couponsCtor);

function couponsCtor($http) {
    this.coupons = [];
    var self = this;

    this.getCoupons = function () {
        var promise = $http
            .get('http://localhost:8080/coupon_sys_ws/webapi/company/coupons')
        promise.then(function (res) {
            console.log(res.data);
            self.coupons = [];
            for (var i = 0; i < res.data.length; i++) {
                self.coupons.push(res.data[i]);
            }
            console.log('-------');
        }, function (err) {
            console.log(err)
        });
    }
    self.getCoupons();
}

//Get Coupon by type.
module.controller("getCoupTypeCtrl", getCoupTypeCtor);

function getCoupTypeCtor($http) {
    this.coupObj = {};
    this.coupType;
    var self = this;

    this.getType = function () {
        var promise = $http
            .get('http://localhost:8080/coupon_sys_ws/webapi/company/couponType/' + this.coupType, this.coupObj)
        promise.then(function (res) {
        	alert(this.coupType);
            console.log(res.data);
            self.coupObj = res.data;

        }, function (err) {
            console.log(err)
            alert('This type isnt in the DB!');
        });
    }
}