  var app = angular.module('Cobot', []);

  app.controller('WeatherController', function($scope, $http){

    $scope.celcius="true";
    $scope.formData = {};

    this.getCelc = function(){
      return $scope.celcius;
    };

    this.setCelc = function(newValue){
      this.celcius = newValue;
    };

    $http.get('http://localhost:9000/weatherC')
       .then(function(res){
          $scope.weatherdata = res.data;
    });
});

  app.controller('CalendarController', function($scope, $http){

    $scope.celcius="true";
    $scope.formData = {};

    this.getCelc = function(){
      return $scope.celcius;
    };

    this.setCelc = function(newValue){
      this.celcius = newValue;
    };

    $http.get('http://localhost:9000/calendar')
       .then(function(res){
          $scope.calenderdata = res.data;
    });
  });
