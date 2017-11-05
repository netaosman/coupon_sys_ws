
// 1 - get the angular applicaiton module
var module = angular.module("customerApp");
// 2 - register controller into the app module
//      give it a name, supply a ctor function

//Purchase Coupon.
module.controller("purchaseCoupCtrl", purchaseCoupCtor);
function purchaseCoupCtor($http) {
    this.newCoup = {};
    // this.newCoup.startDate = "2017-07-26T00:00:00+03:00";
    // this.newCoup.endDate = "2019-01-11T00:00:00+02:00";
    this.newCoup.image = "no iamge";
    this.newCoup.amount = 1;
    this.newCoup.price = 450;
    // this.newCoup.type = "FOOD";
    this.coupObj = {};
    var self = this;
    this.purchaseCoupon = function () {
        $http.put('http://localhost:8080/coupon_sys_ws/webapi/customer/purchaseCoupon',
            this.newCoup)
            .then(
            function (res) {
                alert('Purchased Successfully!')
                self.coupObj = res.data;
            },
            function (err) {
                console.log(err);
                alert('The coupon is out of stock!');
            })
    }

}

//Get All Purchase Coupons.
module.controller("purchaseCouponsCtrl", purchaseCouponsCtor);

function purchaseCouponsCtor($http) {
    this.coupons = [];
    var self = this;

    this.getPurchaseCoupons = function () {
        var promise = $http
            .get('http://localhost:8080/coupon_sys_ws/webapi/customer/coupons')
        promise.then(function (res) {
            console.log(res.data);
            self.coupons = [];
            for (var i = 0; i < res.data.length; i++) {
                self.coupons.push(res.data[i]);
            }
        }, function (err) {
            console.log(err)
        });
    }
    self.getPurchaseCoupons();
}

//Get Coupon by Type.
module.controller("getCoupTypeCtrl", getCoupTypeCtor);

function getCoupTypeCtor($http) {
    this.coupObj = {};
    this.coupType;
    var self = this;

    this.getCoupByType = function () {
        var promise = $http
            .get('http://localhost:8080/coupon_sys_ws/webapi/customer/coupon/' + this.coupType, this.coupObj)
        promise.then(function (res) {
            alert("plaese make sure this type exit!");
            console.log(res.data);
            self.coupObj = res.data;

        }, function (err) {
            console.log(err)
            alert('Error in getting coupon. This type doesnt exist.');
        });
    }
}

//Get Company by price.
module.controller("getCoupByPriceCtrl", getCoupByPricrCtor);

function getCoupByPricrCtor($http) {
    this.coupObj = {};
    this.coupPrice;
    var self = this;

    this.getCoupByPrice = function () {
        var promise = $http
            .get('http://localhost:8080/coupon_sys_ws/webapi/customer/coupons/' + this.coupPrice, this.coupObj)
        promise.then(function (res) {
            alert(this.price);
            console.log(res.data);
            self.coupObj = res.data;

        }, function (err) {
            console.log(err)
            alert('Error in getting coupon.');
        });
    }
}