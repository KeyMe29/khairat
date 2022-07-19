

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import khairat.dao.KariahDAO;
import khairat.dao.MosqueDAO;
import khairat.dao.StaffDAO;
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
	private MosqueDAO mdao;
	private StaffDAO sdao;
	RequestDispatcher view;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        dao = new UserDAO();
        kdao = new KariahDAO();
        mdao = new MosqueDAO();
        sdao = new StaffDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("mosques", MosqueDAO.getAllmosque());
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
		user.setName(request.getParameter("username"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setUser_type(request.getParameter("user_type"));
		kariah.setIcNo(request.getParameter("icNo"));
		kariah.setDob(request.getParameter("dob"));
		kariah.setAddress(request.getParameter("address"));
		kariah.setPhoneNo(request.getParameter("phoneno"));
		kariah.setMaritalstat(request.getParameter("maritalstat"));
		kariah.setGender(request.getParameter("gender"));
		kariah.setMosqueId(Integer.parseInt(request.getParameter("mosqueId")));
		
			
		
		user = UserDAO.getUser(user);
		//check if user exists
		if(!user.isValid()) {
				try {
					//if user not exist, add/register the user
					dao.add(user);
					User us = UserDAO.getUserByEmail(user.getEmail());
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


