package com.sms.controller.filiere;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.sms.beans.Departement;
import com.sms.beans.Filiere;
import com.sms.service.DefaultService;

/**
 * Servlet implementation class InsertMajorServlet
 */
@WebServlet("/add-major")
public class InsertMajorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertMajorServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String departement = request.getParameter("departement");

		String messages = null;
	    int departIdIntValue  = Integer.parseInt(departement);
		
		
		if(departIdIntValue < 1) {
			messages = "départment id ne doit pas etre nul ou négatif";
			request.setAttribute("messages",messages);
			request.getRequestDispatcher("insertFiliere.jsp").forward(request, response);
			return;
		}

		if (nom.isBlank()) {
			messages = "le nom est obligatoire";
			request.setAttribute("messages", messages);
			request.getRequestDispatcher("insertFiliere.jsp").forward(request, response);
			return;
		}

		// créer le filière à insérer
		Filiere fl = new Filiere();

		fl.setNom(nom);
		
		Departement depart = new Departement();
		depart.setId(departIdIntValue);
		
		fl.setDepartement(depart);
		

		if (DefaultService.getServiceInstance().insertMajor(fl)) {
			messages = "Filière est inséré avec succes";
		} else {
			messages = "Il y'a une erreur interne , essaie encors une fois";
		}
		request.setAttribute("messages", messages);
		request.getRequestDispatcher("insertFiliere.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
