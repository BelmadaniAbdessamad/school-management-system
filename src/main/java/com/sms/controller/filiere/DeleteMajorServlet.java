package com.sms.controller.filiere;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.sms.service.DefaultService;

/**
 * Servlet implementation class DeleteMajorServlet
 */
@WebServlet("/delete-major")
public class DeleteMajorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMajorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int  toDeleteId = Integer.parseInt(request.getParameter("toDeleteId"));
			String messages = null;
			if(DefaultService.getServiceInstance().deleteMajor(toDeleteId)) {
				messages = "Filière est supprimé avec succes";
			}else {
				
				messages = "Il y'a une erreur interne , essaie encors une fois";
			}
			
			request.setAttribute("messages",messages);
			request.getRequestDispatcher("get-majors").forward(request, response);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
