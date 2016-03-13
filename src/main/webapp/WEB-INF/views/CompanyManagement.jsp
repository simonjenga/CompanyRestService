<%@ include file="/WEB-INF/views/includes.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Company Rest Service</title>
        <link type="text/css" rel="stylesheet" href="<c:url value='/static/css/bootstrap.min.css' />"></link>
        <link type="text/css" rel="stylesheet" href="<c:url value='/static/css/app.css' />"></link>

        <script type="text/javascript" src="<c:url value='/static/js/angular.min.js' />"></script>
        <script type="text/javascript" src="<c:url value='/static/js/app.js' />"></script>
        <script type="text/javascript" src="<c:url value='/static/js/service/company_service.js' />"></script>
        <script type="text/javascript" src="<c:url value='/static/js/controller/company_controller.js' />"></script>
    </head>
    <body ng-app="myApp" class="ng-cloak">
        <center>
            <div class="generic-container" ng-controller="CompanyController as ctrl">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <span class="lead">Company Rest Service</span>
                    </div>
                    <div class="formcontainer">
                        <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                            <input type="hidden" ng-model="ctrl.company.id" />
                            <div class="row">
                                <div class="form-group col-md-12">
                                    <label class="col-md-2 control-lable" for="username">Name:</label>
                                    <div class="col-md-7">
                                        <input type="text" ng-model="ctrl.company.name" name="username" class="username form-control input-sm" placeholder="Enter your name" required ng-minlength="5"/>
                                        <div class="has-error" ng-show="myForm.$dirty">
                                            <span ng-show="myForm.username.$error.required">This is a required field</span>
                                            <span ng-show="myForm.username.$error.minlength">Minimum length required is 5</span>
                                            <span ng-show="myForm.username.$invalid">This field is invalid</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                      
                            <div class="row">
                                <div class="form-group col-md-12">
                                    <label class="col-md-2 control-lable" for="address">Address:</label>
                                    <div class="col-md-7">
                                        <input type="text" ng-model="ctrl.company.address" name="address" class="address form-control input-sm" placeholder="Enter your address" required ng-minlength="5"/>
                                        <div class="has-error" ng-show="myForm.$dirty">
                                            <span ng-show="myForm.address.$error.required">This is a required field</span>
                                            <span ng-show="myForm.address.$error.minlength">Minimum length required is 5</span>
                                            <span ng-show="myForm.address.$invalid">This field is invalid</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                      
                            <div class="row">
                                <div class="form-group col-md-12">
                                    <label class="col-md-2 control-lable" for="city">City:</label>
                                    <div class="col-md-7">
                                        <input type="text" ng-model="ctrl.company.city" name="city" class="city form-control input-sm" placeholder="Enter your city" required ng-minlength="5"/>
                                        <div class="has-error" ng-show="myForm.$dirty">
                                            <span ng-show="myForm.city.$error.required">This is a required field</span>
                                            <span ng-show="myForm.city.$error.minlength">Minimum length required is 5</span>
                                            <span ng-show="myForm.city.$invalid">This field is invalid</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                      
                            <div class="row">
                                <div class="form-group col-md-12">
                                    <label class="col-md-2 control-lable" for="country">Country:</label>
                                    <div class="col-md-7">
                                        <input type="text" ng-model="ctrl.company.country" name="country" class="country form-control input-sm" placeholder="Enter your country" required ng-minlength="5"/>
                                        <div class="has-error" ng-show="myForm.$dirty">
                                            <span ng-show="myForm.country.$error.required">This is a required field</span>
                                            <span ng-show="myForm.country.$error.minlength">Minimum length required is 5</span>
                                            <span ng-show="myForm.country.$invalid">This field is invalid </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                      
                            <div class="row">
                                <div class="form-group col-md-12">
                                    <label class="col-md-2 control-lable" for="email">Email:</label>
                                    <div class="col-md-7">
                                        <input type="email" ng-model="ctrl.company.email" name="email" class="email form-control input-sm" placeholder="Enter your email [This field is validation free]"/>                                  
                                    </div>
                                </div>
                            </div>
                      
                            <div class="row">
                                <div class="form-group col-md-12">
                                    <label class="col-md-2 control-lable" for="phone">Phone:</label>
                                    <div class="col-md-7">
                                        <input type="text" ng-model="ctrl.company.phoneNumber" name="phone" class="phone form-control input-sm" placeholder="Enter your phone [This field is validation free]"/>
                                    </div>
                                </div>
                            </div>
                      
                            <div class="row">
                                <div class="form-group col-md-12">
                                    <label class="col-md-2 control-lable" for="owner">Owner:</label>
                                    <div class="col-md-7">
                                        <input type="text" ng-model="ctrl.company.owner" name="owner" class="owner form-control input-sm" placeholder="Enter the owner" required ng-minlength="5"/>
                                        <div class="has-error" ng-show="myForm.$dirty">
                                            <span ng-show="myForm.owner.$error.required">This is a required field</span>
                                            <span ng-show="myForm.owner.$error.minlength">Minimum length required is 5</span>
                                            <span ng-show="myForm.owner.$invalid">This field is invalid </span>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-actions floatRight">
                                    <input type="submit" value="{{!ctrl.company.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                                    <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading"><span class="lead">List of Companies</span></div>
                    <div class="tablecontainer">
                        <table id="listCompanies" class="table table-hover">
                            <thead>
                                <tr>
                                    <th>ID.</th>
                                    <th>Name</th>
                                    <th>Address</th>
                                    <th>City</th>
                                    <th>Country</th>
                                    <th>Email</th>
                                    <th>Phone</th>
                                    <th>Owner</th>
                                    <th width="20%"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr ng-repeat="u in ctrl.companies">
                                    <td><span ng-bind="u.id"></span></td>
                                    <td><span ng-bind="u.name"></span></td>
                                    <td><span ng-bind="u.address"></span></td>
                                    <td><span ng-bind="u.city"></span></td>
                                    <td><span ng-bind="u.country"></span></td>
                                    <td><span ng-bind="u.email"></span></td>
                                    <td><span ng-bind="u.phoneNumber"></span></td>
                                    <td>
                                        <select id="ownerData" ng-model="u.owner.name">
                                            <option value="">-- Select Owners --</option>
                                            <option data-ng-repeat="owner in u.owner" value="{{owner.id}}">{{owner.name}}</option>
                                        </select>
                                    </td>
                                    <td>
                                        <button type="button" ng-click="ctrl.edit(u.id, u.owner.name)" class="btn btn-success custom-width">Edit</button>
                                        <button type="button" ng-click="ctrl.addOwner(u.id)" class="btn btn-success custom-width">Owner</button>
                                        <!-- <tt>{{u.owner.name}}</tt> -->
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>     
        </center>
    </body>
</html>