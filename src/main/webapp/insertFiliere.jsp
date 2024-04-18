<%@page import="com.sms.service.DefaultService"%>
<%@page import="com.sms.beans.Departement"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insérer un Filière</title>
<link rel="stylesheet" href="styles/insertForm.css">
<link rel="stylesheet" href="styles/index.css">
<link rel="stylesheet" href="styles/main.css">
</head>
<body>

<header>

		<h3 id="logo">
			<a href="index.jsp"> SMS - ENS</a>
		</h3>
		<ul>
			<li><a href="get-students">Etudiants</a></li>
			<li><a href="get-departs">Departments</a></li>
			<li  class="active-tab"><a href="get-majors">Filières</a></li>
		</ul>
	</header>
    
	<main class="container insert-form">
	     <% if(request.getAttribute("messages") != null) out.print("<h4><b><i>"+request.getAttribute("messages")+"</i></b></h4>"); %>
		<h2>Ajouter un Filière</h2>
		<form action="add-major" method="post" >
			
			<label for="nom">Nom :</label>
			 <input
				type="text" required name="nom" placeholder="Nom"> 
				
				<label for="filiere">Département :</label>
				  <select id="departement" name="departement">
				  <% List<Departement> departs =  DefaultService.getServiceInstance().getAllDepartements(null) ;%>
            	 <% 
            	 
            	 for (Departement depart :  departs) {
         		   

         		    // Print department information
         		   out.print("<option value='"+ depart.getId() +"' + >"+ depart.getNom()+"</option>");

         		   
         		}
            	 
            	 
            	 %>
        </select>
			
			<button type="submit">Ajouter</button>
		</form>
	</main>

</body>
</html>