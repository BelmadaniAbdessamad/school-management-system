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
 * Servlet implementation class MajorSearchServlet
 */
@WebServlet("/find-major")
public class MajorSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MajorSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchQuery = request.getParameter("search");
		Filiere foundFl =null;
		try {
			int searchQueryId = Integer.parseInt(searchQuery);
			foundFl = DefaultService.getServiceInstance().findMajorById(searchQueryId);
		} catch (Exception e) {
			// TODO: handle exception
			foundFl = DefaultService.getServiceInstance().findMajorByName(searchQuery);
		}
		
		
		if(foundFl != null) {
			request.setAttribute("found-major-id", foundFl.getId());
		}else {
			request.setAttribute("found-major-id", -1);
		}
		request.getRequestDispatcher("get-majors").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
