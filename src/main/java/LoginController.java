

import java.io.IOException;

import javax.servlet.annotation.WebServlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import khairat.dao.*;
import khairat.model.Kariah;
import khairat.model.Staff;
import khairat.model.User;
import khairat.dao.KariahDAO;

/**
 * Servlet implementation class LoginController
 */

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher view = request.getRequestDispatcher("login.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {

			User user = new User();
			//retrieve and set email and password
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));

			user = UserDAO.login(user);
			//set user session if user is valid
			if(user.isValid()){
				if(user.isActiveStatus()) {
					session = request.getSession(true);
					session.setAttribute("sessionId", user.getUserid());
					System.out.println(user.getUserid());
					session.setAttribute("sessionName", user.getName());
					session.setAttribute("sessionEmail", user.getEmail());
					session.setAttribute("sessionRole", user.getUser_type()); 

					if(user.getUser_type().equalsIgnoreCase("admin")) {
						request.setAttribute("user", UserDAO.getUserByEmail(user.getEmail()));
						request.setAttribute("staff", StaffDAO.getStaffById(user.getUserid())); 
						RequestDispatcher view = request.getRequestDispatcher("admindashboard.jsp");
						view.forward(request, response);	
					}
					else {
						Kariah kariah = KariahDAO.getKariahById(user.getUserid());
						request.setAttribute("user", UserDAO.getUserByEmail(user.getEmail()));	
						request.setAttribute("kariah", KariahDAO.getKariahById(user.getUserid()));
						request.setAttribute("mosque", MosqueDAO.getmosqueById(user.getUserid()));
						RequestDispatcher view = request.getRequestDispatcher("homepage.jsp");
						view.forward(request, response);	
					}	
				}
				else {
					response.sendRedirect("unverifiedUser.jsp");
				}
			}
			//redirect to invalidLoggin.jsp if user is not valid
			else{
				response.sendRedirect("invalidLogin.jsp");
			}		
		}
		catch (Throwable ex) {
			System.out.println(ex);
		}
	}

}
