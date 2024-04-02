<%@page import="java.util.Arrays"%>
<%@page import="com.sms.beans.Departement"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Departments</title>
    <link rel="stylesheet" href="styles/index.css">
    <link rel="stylesheet" href="styles/main.css">
    <script  src="js/main.js"  ></script>
</head>
<body>
    <header>

        <h3 id="logo"><a href="index.html"> SMS - ENS</a></h3>
        <ul>
            <li><a href="get-students">Etudiants</a></li>
            <li class="active-tab"><a href="get-departs">Departments</a></li>
            <li><a href="get-majors">Filières</a></li>
        </ul>
    </header>
   	<main class="container">

		<div class="search-container">
			<form action="find-depart" >
				<input type="text" required name="search"
					placeholder="Tapez un nom de departement ou id ...">
				<button type="submit">Chercher</button>
			</form>
	<% Integer foudDepartId = (Integer) request.getAttribute("found-depart-id"); %>
		
		</div>

		<section class="student-section">
		  <% if(request.getAttribute("messages") != null) out.print("<h4 class=\"msg-box\"><b><i>"+request.getAttribute("messages")+"</i></b></h4>"); %>
	
			<h2 class="table-title">Liste des Départements  - <a href="insertDepartement.jsp">Insérer un Départments</a></h2>
 
           <form class="filters" action="get-departs" style="width: 49rem">
                  <span>ORDER BY : </span>
                 <%
                     // Check if the order parameter exists in the request
                String[] appliedFilters = (String [])request.getAttribute("filters");
                  %>
                 
                 
                   <div>
                     <input type="checkbox" name="order" value="depart"
                     <% if (appliedFilters != null && Arrays.asList(appliedFilters).contains("depart")) out.print("checked"); %>
               
                     >
                   <label>Département</label>
                   </div>
                   
                   <div>
                   <input type="checkbox" name="order" value="student-count-asc"
                   
                   <% if (appliedFilters != null && Arrays.asList(appliedFilters).contains("student-count-asc")) out.print("checked"); %>
               >
                   <label>N d'etudiants ASC</label>
                   </div>
                   
                    
                   <div>
                   <input type="checkbox" name="order" value="student-count-dec"
                   
                   <% if (appliedFilters != null && Arrays.asList(appliedFilters).contains("student-count-dec")) out.print("checked"); %>
               >
                   <label>N d'etudiants DEC</label>
                   </div>
                   
                      <div>
                   <input type="checkbox" name="order" value="major-count-asc"
                   
                   <% if (appliedFilters != null && Arrays.asList(appliedFilters).contains("major-count-asc")) out.print("checked"); %>
               >
                   <label>N Filières ASC</label>
                   </div>
                   
                    
                   <div>
                   <input type="checkbox" name="order" value="major-count-dec"
                   
                   <% if (appliedFilters != null && Arrays.asList(appliedFilters).contains("major-count-dec")) out.print("checked"); %>
               >
                   <label>N Filières DEC</label>
                   </div>
                  
                   
                   
                  <div> <button type="submit">Appliquer</button></div>
                     <a href="get-departs" style="text-decoration: none" ><button style="background-color: red <% if(appliedFilters == null || appliedFilters.length==0) out.print(";display :none\""); %>" type="button">Réinitialiser</button></a>
                 
            </form>  
		 
			<%
			List<Departement> departements = (List<Departement>) request.getAttribute("departements");
			%>

			<%
			if (departements == null || departements.size() == 0)
				out.println("<h2><b>No departements Found</b></h2> </br>");
			%>
			<div id="student-list">
				<table>
					<thead>
						<tr>
							
							<th>Département</th>
							<th>Nombre des Filières</th>
							<th>Nombres des étudiants</th>
							<th>actions</th>
							

						</tr>
					</thead>
					<tbody>


						<%
						if (departements != null) {
							for (Departement depart : departements) {
						%>
						<tr id="<%=depart.getId()%>" class=<% if(foudDepartId != null && depart.getId()==foudDepartId) out.print("found"); %>>
							<td><%=depart.getNom()%></td>
							<td><%=depart.getFiliereCount()+" Filières"%></td>
							<td><%=depart.getEtudiantCount()+" Etudiants"%></td>
							<td>
							<form onsubmit="return confirmSubmit('cet département')" action="delete-depart" method="post"><input value="<%=depart.getId()%>" name="toDeleteId" required type="hidden">
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
			

			if (foudDepartId != null) {
				if (foudDepartId > -1) {
					%>
					<script>
					
					document.getElementById('<%=foudDepartId%>').scrollIntoView({ behavior: 'smooth', block: 'start' });
					</script>
					
					<%
				} else {
					%>
					<script>
					alert("No Department was found")
					</script>
					
					<%
				}
			}
			%>
</body>
</html>