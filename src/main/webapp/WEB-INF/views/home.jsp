<!DOCTYPE html>
<html lang="en-US">
<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <title> Spring MVC 4 REST + AngularJS </title>
</head>
<body ng-app="myApp">
<div ng-controller="PersonController as personCtrl">
    <div navbar navbar-default navbar-fixed-top><h1> Contact Book</h1></div>
    <form name="personForm" method="POST" class="form-horizontal">
        <fieldset>
            <div class="col-lg-4">

                <legend>
                    <div ng-if="personCtrl.flag != 'edit'">
                        <h3> Add New Person </h3>
                    </div>
                    <div ng-if="personCtrl.flag == 'edit'">
                        <h3> Update Person for ID: {{ personCtrl.person.pid }} </h3>
                    </div>

                </legend>

                <div class="form-group">
                    <label class="col-lg-2 control-label">Name:</label>
                    <div class="col-lg-10">
                        <input class="form-control" type="text" name="name" ng-model="personCtrl.person.name" required/>
                        <span ng-show="personForm.name.$error.required"
                              class="help-block"><h6>Name is required.</h6></span>
                    </div>

                </div>

                <div class="form-group">
                    <label class="col-lg-2 control-label">Location:</label>
                    <div class="col-lg-10">
                        <input class="form-control" type="text" name="location" ng-model="personCtrl.person.location"
                               required/>
                        <span ng-show="personForm.location.$error.required"
                              class="help-blockg"><h6>Location is required.</h6></span>
                    </div>

                </div>

                <span ng-if="personCtrl.flag=='created'" class="text-success">Person successfully added.</span>
                <span ng-if="personCtrl.flag=='failed'" class="text-danger">Person already exists.</span>

                <div ng-if="personCtrl.flag != 'edit'">
                    <input type="submit" ng-click="personCtrl.addPerson()" value="Add Person" class="btn btn-success"/>
                    <input type="button" ng-click="personCtrl.reset()" value="Reset" class="btn btn-default"/>
                </div>

                <div ng-if="personCtrl.flag == 'edit'">
                    <input type="submit" ng-click="personCtrl.updatePersonDetail()" value="Update Person" class="btn btn-primary"/>
                    <input type="button" ng-click="personCtrl.cancelUpdate()" value="Cancel" class="btn btn-default"/>
                </div>
                <span ng-if="personCtrl.flag=='deleted'" class="text-success">Person successfully deleted.</span>
            </div>
        </fieldset>
    </form>
    </br>
    <div class="col-lg-4">
        <div class="panel panel-default">
            <table class="table table-striped table-hover">
                <thead>
                <tr class="active">
                    <th>ID</th>
                    <th>Name</th>
                    <th>Location</th>
                    <th></th>
                </tr>
                </thead>
                <tr ng-repeat="row in personCtrl.persons">
                    <td><span ng-bind="row.pid"></span></td>
                    <td><span ng-bind="row.name"></span></td>
                    <td><span ng-bind="row.location"></span></td>
                    <td>
                        <input type="button" ng-click="personCtrl.deletePerson(row.pid)" value="Delete"
                               class="btn btn-danger"/>
                        <input type="button" ng-click="personCtrl.editPerson(row.pid)" value="Edit"
                               class="btn btn-warning"/>
                        <span ng-if="personCtrl.flag=='updated' && row.pid==personCtrl.updatedId" class="text-success">
                        Person successfully updated.
                    </span>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/resources/js/angular.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/angular-resource.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/app.js"></script>
</body>
</html>