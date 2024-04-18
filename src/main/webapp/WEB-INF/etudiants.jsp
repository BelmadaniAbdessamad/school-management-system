<%@page import="com.sms.service.Auth"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.sms.beans.Etudiant"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Etudiants</title>
<link rel="stylesheet" href="styles/index.css">
<link rel="stylesheet" href="styles/main.css">
<script  src="js/main.js"  ></script>
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
			
			 <li style="margin-left: 3rem;<% if(!Auth.isAuthorized()) out.print(";display :none\""); %>"><a href="logout" style="color:red;">Logout</a></li>
		</ul>
	</header>
	<main class="container">

		<div class="search-container">
			<form action="find-student" >
				<input type="text" required name="search"
					placeholder="Tapez un nom complet ou cne ...">
				<button type="submit">Chercher</button>
			</form>
	<% Integer foudStudentId = (Integer) request.getAttribute("found-student-id"); %>
		
		</div>

		<section class="student-section">
		  <% if(request.getAttribute("messages") != null) out.print("<h4 class=\"msg-box\"><b><i>"+request.getAttribute("messages")+"</i></b></h4>"); %>
	
			<h2  class="table-title">Liste des Etudiants <% if(Auth.isAuthorized()) out.print(" - <a href=\"insertEtudiant.jsp\">Insérer un Etudiant</a>"); %></h2>


           <form class="filters" action="get-students">
                  <span>ORDER BY : </span>
                 <%
                     // Check if the order parameter exists in the request
                String[] appliedFilters = (String [])request.getAttribute("filters");
                  %>
                 
                 <div>
                 <input type="checkbox" name="order" value="cne"
                  <% if (appliedFilters != null && Arrays.asList(appliedFilters).contains("cne")) out.print("checked"); %>
                 >
                   <label>Cne</label>
                 </div>
                  
                   <div>
                   <input type="checkbox" name="order" value="nom"
                   <% if (appliedFilters != null && Arrays.asList(appliedFilters).contains("nom")) out.print("checked"); %>
               
                   >
                   <label>Nom</label>
                   </div>
                   
                   <div>
                   <input type="checkbox" name="order" value="prenom"
                   
                   <% if (appliedFilters != null && Arrays.asList(appliedFilters).contains("prenom")) out.print("checked"); %>
               >
                   <label>Prénom</label>
                   </div>
                   
                   <div>
                    <input type="checkbox" name="order" value="major"
                    <% if (appliedFilters != null && Arrays.asList(appliedFilters).contains("major")) out.print("checked"); %>
               
                    >
                   <label>Filière</label>
                   </div>
                  
                   <div>
                     <input type="checkbox" name="order" value="depart"
                     <% if (appliedFilters != null && Arrays.asList(appliedFilters).contains("depart")) out.print("checked"); %>
               
                     >
                   <label>Département</label>
                   </div>
                   
                  <div> <button type="submit">Appliquer</button></div>
                 <a href="get-students" style="text-decoration: none" ><button style="background-color: red <% if(appliedFilters == null || appliedFilters.length==0) out.print(";display :none\""); %>" type="button">Réinitialiser</button></a>
               
                 
            </form>
			<%
			List<Etudiant> etudiants = (List<Etudiant>) request.getAttribute("etudiants");
			%>

			<%
			if (etudiants == null || etudiants.size() == 0)
				out.println("<h2><b>No Student Found</b></h2> </br>");
			%>
			<div id="student-list">
				<table>
					<thead>
						<tr>
							<th>cne</th>
							<th>nom</th>
							<th>prénom</th>
							<th>filière</th>
							<th>departement</th>
							<th>tel</th>
							
							<% if(Auth.isAuthorized()) out.print("<th>actions</th>"); %>
						</tr>
					</thead>
					<tbody>


						<%
						if (etudiants != null) {
							for (Etudiant t : etudiants) {
						%>
						<tr id="<%=t.getCne()%>" class=<% if(foudStudentId != null && t.getCne()==foudStudentId) out.print("found"); %>>
							<td><%=t.getCne()%></td>
							<td><%=t.getNom()%></td>
							<td><%=t.getPrenom()%></td>
							<td><%=t.getFiliere().getNom()%></td>
							<td><%=t.getFiliere().getDepartement().getNom()%></td>
							<td><%=t.getTel()%></td>
							
							
							
							<td style="<% if(!Auth.isAuthorized()) out.print("display :none\""); %>">
							<form onsubmit="return confirmSubmit('cet étudiant')" action="delete-student" method="post"><input value="<%=t.getId()%>" name="toDeleteId" required type="hidden">
							<button type="submit">Delete</button></form>
							</td>


						</tr>
						<%
						}
						}
						%>
					</tbody>

				</table>

			</div>
		</section>
		

	</main>
	
		<%
			

			if (foudStudentId != null) {
				if (foudStudentId > -1) {
					%>
					<script>
					
					document.getElementById('<%=foudStudentId%>').scrollIntoView({ behavior: 'smooth', block: 'start' });
					</script>
					
					<%
				} else {
					%>
					<script>
					alert("No student was found")
					</script>
					
					<%
				}
			}
			%>
			
			
			
			
			
  

</body>
</html>