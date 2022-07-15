package khairat.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import khairat.dao.KariahDAO;
import khairat.dao.UserDAO;
import khairat.model.Kariah;
import khairat.model.User;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO dao;
	private KariahDAO kdao;
	RequestDispatcher view;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        dao = new UserDAO();
        kdao = new KariahDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		view = request.getRequestDispatcher("register.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = new User();
		Kariah kariah = new Kariah();
		//retrieve input and set
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setUser_type(request.getParameter("user_type"));
		kariah.setUsername(request.getParameter("username"));
		kariah.setPhoneno(Integer.parseInt(request.getParameter("phoneno")));
		kariah.setMaritalstat(request.getParameter("maritalstat"));
		kariah.setGender(request.getParameter("gender"));
		
		
		user = UserDAO.getUser(user);
		//check if user exists
				if(!user.isValid()) {
					try {
						//if user not exist, add/register the user
						dao.add(user);
						User us = dao.getUserByEmail(user.getEmail());
						int tempUserid = us.getUserid();
						kariah.setUserid(tempUserid);
						kdao.updateKariah(kariah);
						
					}catch (Exception e) {
						e.printStackTrace();
					}
					//redirect to login.jsp page
					//request.setAttribute("users", UserDAO.getAllUsers());
					view = request.getRequestDispatcher("login.jsp");
					view.forward(request, response);
				}
	}

}
