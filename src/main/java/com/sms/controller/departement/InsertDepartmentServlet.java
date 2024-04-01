package com.sms.controller.departement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.sms.beans.Departement;
import com.sms.beans.Etudiant;
import com.sms.service.DefaultService;

/**
 * Servlet implementation class InsertDepartment
 */
@WebServlet("/add-depart")
public class InsertDepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertDepartmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nom = request.getParameter("nom");
		
		
		String messages = null;
		
		
	
		if(nom.isBlank()) {
			messages = "le nom est obligatoire";
			request.setAttribute("messages",messages);
			request.getRequestDispatcher("insertDepartement.jsp").forward(request, response);
			return;
		}
		
		// créer le départment à insérer
		Departement depart= new Departement();
		
		depart.setNom(nom);
		
		
		if(DefaultService.getServiceInstance().insertDepartement(depart)) {
			messages = "Département est inséré avec succes";
		}else {
			messages = "Il y'a une erreur interne , essaie encors une fois";
		}
		request.setAttribute("messages",messages);
		request.getRequestDispatcher("insertDepartement.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
