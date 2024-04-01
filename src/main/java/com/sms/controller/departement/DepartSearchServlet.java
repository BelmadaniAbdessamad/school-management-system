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
 * Servlet implementation class DepartSearchServlet
 */
@WebServlet("/find-depart")
public class DepartSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String searchQuery = request.getParameter("search");
		Departement foundDepart =null;
		try {
			int searchQueryId = Integer.parseInt(searchQuery);
			foundDepart = DefaultService.getServiceInstance().findDepartById(searchQueryId);
		} catch (Exception e) {
			// TODO: handle exception
			foundDepart = DefaultService.getServiceInstance().findDepartByName(searchQuery);
		}
		
		
		if(foundDepart != null) {
			request.setAttribute("found-depart-id", foundDepart.getId());
		}else {
			request.setAttribute("found-depart-id", -1);
		}
		request.getRequestDispatcher("get-departs").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
