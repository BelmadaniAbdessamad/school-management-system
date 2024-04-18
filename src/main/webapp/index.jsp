<%@page import="com.sms.service.Auth"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>School Management System</title>
    <link rel="stylesheet" href="styles/index.css">
     <link rel="stylesheet" href="styles/main.css">
</head>

<body>
    <header>

        <h3 id="logo"><a href="index.jsp"> SMS - ENS</a></h3>
        <ul>
            <li><a href="get-students">Etudiants</a></li>
            <li><a href="get-departs">Departments</a></li>
            <li><a href="get-majors">Filières</a></li>
            
            <li style="margin-left: 3rem;<% if(!Auth.isAuthorized()) out.print(";display :none\""); %>"><a href="logout" style="color:red;">Logout</a></li>
        </ul>
        
       
    </header>
    <main class="container"><p> Welcome to the Homepage</p> </br>
      <% if(request.getAttribute("messages") != null) out.print("<h4 class=\"msg-box\"><b><i>"+request.getAttribute("messages")+"</i></b></h4>"); %>
     <form method="post" action="login" class="login" style="<% if(Auth.isAuthorized()) out.print(";display :none\""); %>">
    <h3>Login</h3>
    <div> <label for="username">username</label><input required id="username" name="username"></div>
    <div> <label for="password">password</label><input  type="password" required id="password" name="password"></div>
    <button>Login</button>
    </form>
    </main>
   
</body>

</html>