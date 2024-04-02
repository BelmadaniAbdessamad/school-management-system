<%@page import="java.util.Arrays"%>
<%@page import="com.sms.beans.Filiere"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Filières</title>
<link rel="stylesheet" href="styles/index.css">
<link rel="stylesheet" href="styles/main.css">
<script  src="js/main.js"  ></script>
</head>
<body>
	<header>

		<h3 id="logo">
			<a href="index.html"> SMS - ENS</a>
		</h3>
		<ul>
			<li><a href="get-students">Etudiants</a></li>
			<li><a href="get-departs">Departments</a></li>
			<li class="active-tab"><a href="get-majors">Filières</a></li>
		</ul>
	</header>
	<main class="container">

		<div class="search-container">
			<form action="find-major">
				<input type="text" required name="search"
					placeholder="Tapez un nom de filière ou id ...">
				<button type="submit">Chercher</button>
			</form>
			<%
			Integer foundMajorId = (Integer) request.getAttribute("found-major-id");
			%>

		</div>

		<section class="student-section">
			<%
			if (request.getAttribute("messages") != null)
				out.print("<h4 class=\"msg-box\"><b><i>" + request.getAttribute("messages") + "</i></b></h4>");
			%>

			<h2 class="table-title">
				Liste des Filières - <a href="insertFiliere.jsp">Insérer un
					Filière</a>
			</h2>
			
			 <form class="filters" action="get-majors" style="width: 39rem">
                  <span>ORDER BY : </span>
                 <%
                     // Check if the order parameter exists in the request
                String[] appliedFilters = (String [])request.getAttribute("filters");
                  %>
                 
                 
                  <div>
                    <input type="checkbox" name="order" value="major"
                    <% if (appliedFilters != null && Arrays.asList(appliedFilters).contains("major")) out.print("checked"); %>
               
                    >
                   <label>Filière</label>
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
                     <input type="checkbox" name="order" value="depart"
                     <% if (appliedFilters != null && Arrays.asList(appliedFilters).contains("depart")) out.print("checked"); %>
               
                     >
                   <label>Département</label>
                   </div>
                   
                  <div> <button type="submit">Appliquer</button></div>
                     <a href="get-majors" style="text-decoration: none" ><button style="background-color: red <% if(appliedFilters == null || appliedFilters.length==0) out.print(";display :none\""); %>" type="button">Réinitialiser</button></a>
                 
            </form>

			<%
			List<Filiere> filieres = (List<Filiere>) request.getAttribute("filieres");
			%>

			<%
			if (filieres == null || filieres.size() == 0)
				out.println("<h2><b>No Major Found</b></h2> </br>");
			%>
			<div id="student-list">
				<table>
					<thead>
						<tr>

							<th>Filière</th>
							<th>Département</th>
							<th>Nombre des Etudiants</th>
							<th>actions</th>

						</tr>
					</thead>
					<tbody>


						<%
						if (filieres != null) {
							for (Filiere fl : filieres) {
						%>
						<tr id="<%=fl.getId()%>"
							class=<%if (foundMajorId != null && fl.getId() == foundMajorId)
	out.print("found");%>>
							<td><%=fl.getNom()%></td>
							<td><%=fl.getDepartement().getNom() %></td>
							<td><%=fl.getEtudiantCount()+" Etudiants"%></td>

							<td>
								<form onsubmit="return confirmSubmit('cet filière')" action="delete-major" method="post">
									<input value="<%=fl.getId()%>" name="toDeleteId" required
										type="hidden">
									<button type="submit">Delete</button>
								</form>
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
	if (foundMajorId != null) {
		if (foundMajorId > -1) {
	%>
	<script>
					
					document.getElementById('<%=foundMajorId%>
		').scrollIntoView({
			behavior : 'smooth',
			block : 'start'
		});
	</script>

	<%
	} else {
	%>
	<script>
		alert("No Major was found")
	</script>

	<%
	}
	}
	%>
</body>
</html>