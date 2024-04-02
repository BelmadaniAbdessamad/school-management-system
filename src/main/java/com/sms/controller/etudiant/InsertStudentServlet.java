package com.sms.controller.etudiant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.sms.beans.Etudiant;
import com.sms.beans.Filiere;
import com.sms.service.DefaultService;

/**
 * Servlet implementation class InsertStudentServlet
 */
@WebServlet("/add-student")
public class InsertStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String cne = request.getParameter("cne");
		String filiere = request.getParameter("filiere");
		String tel = request.getParameter("tel");
		
		String messages = null;
		int cneIntValue  = Integer.parseInt(cne);
		
		
		if(cneIntValue < 1) {
			messages = "Cne ne doit pas etre nul ou négatif";
			request.setAttribute("messages",messages);
			request.getRequestDispatcher("insertEtudiant.jsp").forward(request, response);
			return;
		}
		
		if(nom.isBlank() || prenom.isBlank() ||  cne.isBlank() ||  filiere.isBlank() ||  tel.isBlank() ) {
			messages = "Tous les champs sont obligatoires";
			request.setAttribute("messages",messages);
			request.getRequestDispatcher("insertEtudiant.jsp").forward(request, response);
			return;
		}
		
		// créer l'étudiant à insérer
		Etudiant et = new Etudiant();
		et.setCne(cneIntValue);
		et.setNom(nom);
		et.setPrenom(prenom);
		
		Filiere fl = new Filiere();
		fl.setId(Integer.parseInt(filiere));
		et.setFiliere(fl);
		et.setTel(tel);
		
		if(DefaultService.getServiceInstance().insertStudent(et)) {
			messages = "Etudiant est inséré avec succes";
		}else {
			messages = "Il y'a une erreur interne , essaie encors une fois";
		}
		request.setAttribute("messages",messages);
		request.getRequestDispatcher("insertEtudiant.jsp").forward(request, response);
		/*System.out.println(" nom : "+nom);
		System.out.println(" prenom : "+prenom);
		System.out.println(" cne : "+cne);
		System.out.println(" filiere : "+filiere);
		System.out.println(" tel : "+tel);
	    */
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
