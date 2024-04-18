<%@page import="com.sms.beans.Filiere"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.sms.service.DefaultService"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Inserer un étudiant</title>
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
			<li class="active-tab"><a href="get-students">Etudiants</a></li>
			<li><a href="get-departs">Departments</a></li>
			<li><a href="get-majors">Filières</a></li>
			
			 <li style="margin-left: 3rem;"><a href="logout" style="color:red;">Logout</a></li>
		</ul>
	</header>
    
	<main class="container insert-form">
	     <% if(request.getAttribute("messages") != null) out.print("<h4><b><i>"+request.getAttribute("messages")+"</i></b></h4>"); %>
		<h2>Ajouter un Etudiant</h2>
		<form action="add-student" method="post" >
			<label for="cne">Cne :</label>
			<input type="number" required name="cne" placeholder="CNE">
			<label for="nom">Nom :</label>
			 <input
				type="text" required name="nom" placeholder="Nom"> 
				<label for="prenom">Prénom :</label><input
				type="text" required name="prenom" placeholder="Prénom"> 
				<label for="filiere">Filière :</label>
				  <select id="filiere" name="filiere">
				  <% Map<String,List<Filiere>> departmentWithMajors =  DefaultService.getServiceInstance().getAllDepartementsWithMajors() ;%>
            	 <% 
            	 
            	 for (Map.Entry<String, List<Filiere>> entry :departmentWithMajors.entrySet()) {
         		    String department = entry.getKey();
         		    List<Filiere> majors = entry.getValue();

         		    // Print department information
         		   out.print("<optgroup label='" + department +"'>");

         		    // Print majors for the department
         		    for (Filiere major : majors) {
         		    	  out.print(" <option value='"+ major.getId() +"' + >"+ major.getNom()+"</option>");
         		    	 
         		    }
         		    out.print(" </optgroup>");
         		}
            	 
            	 
            	 %>
        </select>
			<label for="tel">Tel :</label>
			<input type="tel" required name="tel" placeholder="Téléphone">
			<button type="submit">Ajouter</button>
		</form>
	</main>
</body>
</html>