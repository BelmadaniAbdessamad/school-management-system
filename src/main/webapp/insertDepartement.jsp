<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insérer un département</title>
<link rel="stylesheet" href="styles/insertForm.css">
<link rel="stylesheet" href="styles/index.css">
<link rel="stylesheet" href="styles/main.css">
</head>
<body>

 <header>

        <h3 id="logo"><a href="index.jsp"> SMS - ENS</a></h3>
        <ul>
            <li><a href="get-students">Etudiants</a></li>
            <li class="active-tab"><a href="get-departs">Departments</a></li>
            <li><a href="get-majors">Filières</a></li>
            
             <li style="margin-left: 3rem;"><a href="logout" style="color:red;">Logout</a></li>
        </ul>
    </header>
    
    	<main class="container insert-form">
	     <% if(request.getAttribute("messages") != null) out.print("<h4><b><i>"+request.getAttribute("messages")+"</i></b></h4>"); %>
		<h2>Ajouter un Département</h2>
		<form action="add-depart" method="post" >
		
			<label for="nom">Nom :</label>
			 <input type="text" required name="nom" placeholder="Nom"> 
			
			<button type="submit">Ajouter</button>
		</form>
	</main>

</body>
</html>