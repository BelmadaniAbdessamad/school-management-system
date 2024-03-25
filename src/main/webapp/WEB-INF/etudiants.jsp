<%@page import="com.sms.beans.Etudiant"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Etudiants</title>
<link rel="stylesheet" href="styles/index.css">
<link rel="stylesheet" href="styles/etudiants.css">
</head>
<body>
	<header>

		<h3 id="logo">
			<a href="index.html"> SMS - ENS</a>
		</h3>
		<ul>
			<li class="active-tab"><a href="get-students">Etudiants</a></li>
			<li><a href="departments.html">Departments</a></li>
			<li><a href="filiere.html">Filières</a></li>
		</ul>
	</header>
	<main class="container">

		<div class="search-container">
			<form action="find-student" >
				<input type="text" required name="search"
					placeholder="Tapez un nom ...">
				<button type="submit">Chercher</button>
			</form>
	<% Integer foudStudentId = (Integer) request.getAttribute("found-student-id"); %>
		
		</div>

		<section class="student-section">
			<h2 class="table-title">Liste des Etudiants</h2>

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
							<td><%=t.getFiliere()%></td>
							<td><%=t.getDepartement()%></td>
							<td><%=t.getTel()%></td>



						</tr>
						<%
						}
						}
						%>
					</tbody>

				</table>

			</div>
		</section>
		<!-- <div class="insert-form">
            <h2>Ajouter un Etudiant</h2>
            <form action="">
                <input type="text" required name="cne" placeholder="CNE">
                <input type="text" required name="nom" placeholder="Nom">
                <input type="text" required name="prenom" placeholder="Prénom">
                <input type="text" required name="filiere" placeholder="Filière">
                <input type="text" required name="tel" placeholder="Téléphone">
                <button type="submit">Ajouter</button>
            </form>
        </div> -->

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