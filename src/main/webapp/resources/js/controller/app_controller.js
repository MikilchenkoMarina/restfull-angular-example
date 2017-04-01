/**
 * Created by mikimar on 01.04.2017.
 */
'use strict';

App.controller('AppController', ['$scope', 'ContactService', function($scope,ContactService) {
    var self = this;
    self.contact={id:null,username:'',address:'',email:''};
    self.contacts=[];

    self.fetchAllContacts = function(){
        ContactService.fetchAllContacts()
            .then(
                function(d) {
                    self.contacts = d;
                },
                function(errResponse){
                    console.error('Error while fetching Contacts');
                }
            );
    };

    self.createContact = function(contact){
        ContactService.createContact(contact)
            .then(
                self.fetchAllContacts,
                function(errResponse){
                    console.error('Error while creating Contacts.');
                }
            );
    };


    self.deleteContact = function(id){
        ContactService.deleteContact(id)
            .then(
                self.fetchAllContacts,
                function(errResponse){
                    console.error('Error while deleting Contact.');
                }
            );
    };

    self.fetchAllContacts();

    self.submit = function() {
/*        if(self.contact.id===null){*/
            console.log('Saving New User', self.contact);
            self.createContact(self.contact);
    /*    }*/
        self.reset();
    };

    self.remove = function(id){
        console.log('id to be deleted', id);
        if(self.contact.id === id) {//clean the form if the user to be deleted is shown there.
            self.reset();
        }
        self.deleteContact(id);
    }

    self.reset = function(){
        self.contact={id:null,username:'',address:'',email:''};
        $scope.myForm.$setPristine(); //reset Form
    }

}]);




