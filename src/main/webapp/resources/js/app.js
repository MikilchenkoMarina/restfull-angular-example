/*    -------------------------App -------------------------------------------*/
var app = angular.module('myApp', ['ngResource']);


/*    -------------------------Factory -------------------------------------------*/
app.factory('Person', ['$resource', function ($resource) {
    return $resource('/person/:personId', {personId: '@pid'},
        {
            updatePerson: {method: 'PUT'},
        }
    );
}]);


/*    -------------------------Controller -----------------------------------------*/

app.controller('PersonController', ['$scope', 'Person', function ($scope, Person) {
    debugger
    var ob = this;
    ob.persons = [];
    ob.person = new Person();


    ob.fetchAllPersons = function () {
        ob.persons = Person.query();
    };


    ob.fetchAllPersons();

    ob.addPerson = function () {
        console.log('Inside save');
        debugger
        if ($scope.personForm.$valid) {
            debugger
            ob.person.$save(function (person) {
                    console.log(person);
                    ob.flag = 'created';
                    ob.reset();
                    ob.fetchAllPersons();
                },
                function (err) {
                    debugger
                    console.log(err.status);
                    ob.flag = 'failed';
                }
            );
        }
    };

    ob.editPerson = function (id) {
        console.log('Inside edit');
        ob.person = Person.get({personId: id}, function () {
            ob.flag = 'edit';
        });
    };

    ob.updatePersonDetail = function () {
        debugger
        console.log('Inside update');
        if ($scope.personForm.$valid) {
            debugger
            ob.person.$updatePerson(function (person) {
                console.log(person);
                debugger
                ob.updatedId = person.pid;
                debugger
                ob.reset();
                ob.flag = 'updated';
                ob.fetchAllPersons();
            });
        }
    };

    ob.deletePerson = function (id) {
        console.log('Inside delete');
        debugger
        ob.person = Person.delete({personId: id}, function () {
            ob.reset();
            ob.flag = 'deleted';
            ob.fetchAllPersons();
        });
    };

    ob.reset = function () {
        ob.person = new Person();
        $scope.personForm.$setPristine();
    };

    ob.cancelUpdate = function (id) {
        ob.person = new Person();
        ob.flag = '';
        ob.fetchAllPersons();
    };
}]);