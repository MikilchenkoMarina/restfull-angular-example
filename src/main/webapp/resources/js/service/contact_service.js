/**
 * Created by mikimar on 01.04.2017.
 */
'use strict';

angular.module('myApp').factory('ContactService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/contact/';

    var factory = {
        fetchAllContacts: fetchAllContacts,
        createContact:createContact,
        deleteContact:deleteContact
    };

    return factory;

    function fetchAllContacts() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Contacts');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function createContact(contact) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, contact)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while creating Contact');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }


    function deleteContact(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while deleting Contact');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);
