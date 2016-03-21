<%--
  Created by IntelliJ IDEA.
  User: andrewjohnsson
  Date: 29.02.16
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
  <script>
    $(document).ready(function() {
      $('#country').change(function(event) {
        var country = $("select#country").val();
        $.getJSON('ajaxAction', {
          countryName : country
        }, function(jsonResponse) {
          $('#ajaxResponse').text(jsonResponse.dummyMsg);
          var select = $('#states');
          select.find('option').remove();
          $.each(jsonResponse.stateMap, function(key, value) {
            $('<option>').val(key).text(value).appendTo(select);
          });
        });
      });
    });
  </script>
</head>
<body>
<s:actionerror/>

<div class="col-xs-12 col-sm-6 col-md-3 text-center">
  <div class="panel panel-default">
    <h1 class="text-center">Create User</h1>
    <form method="post" action="create.action">
      <div class="form-group">
        <input type="text" name="user.name" class="form-control" placeholder="Name">
        <input type="text" name="user.login" class="form-control" placeholder=Login>
        <input type="text" name="user.email" class="form-control" placeholder=Email>
        <input type="text" name="user.password" class="form-control" placeholder=Password>
        <input type="text" name="user.age" class="form-control" placeholder=Age(Optional)>
        <button type="submit" class="btn btn-default">Create</button>
      </div>
    </form>
  </div>
</div>

<div class="col-xs-12 col-sm-6 col-md-3 text-center">
  <div class="panel panel-default">
    <h1 class="text-center">Read User</h1>
    <form method="post" action="read.action">
      <div class="form-group">
        <input type="text" name="user.name" class="form-control" placeholder="Name">
        <button type="submit" class="btn btn-default">Read</button>
      </div>
    </form>
  </div>
</div>

<h2>Users List</h2>
<table>
  <tr>
    <th>Name</th>
    <th>Login</th>
    <th>Password</th>
    <th>Email</th>
    <th>Age</th>
    <th>Delete</th>
  </tr>
  <s:iterator value="users" var="users">
    <tr>
      <td><s:property value="name"/></td>
      <td><s:property value="login"/></td>
      <td><s:property value="password"/></td>
      <td><s:property value="email"/></td>
      <td><s:property value="age"/></td>
      <td><a href="delete?id=<s:property value="userId"/>">delete</a></td>
    </tr>
  </s:iterator>
</table>
</body>
</html>
