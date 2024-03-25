package com.sms.controller.etudiant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.sms.beans.Etudiant;
import com.sms.service.DefaultService;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/find-student")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fullname = request.getParameter("search");
		Etudiant foundStudent = DefaultService.getServiceInstance().findStudentByFullName(fullname);
		if(foundStudent != null) {
			request.setAttribute("found-student-id", foundStudent.getCne());
		}else {
			request.setAttribute("found-student-id", -1);
		}
		request.getRequestDispatcher("get-students").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
