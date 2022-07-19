

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import khairat.dao.StaffDAO;
import khairat.dao.UserDAO;
import khairat.model.Staff;
import khairat.model.User;

/**
 * Servlet implementation class AdminRegisterController
 */
@WebServlet("/AdminRegisterController")
public class AdminRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO dao;
	private StaffDAO sdao;
	RequestDispatcher view;
	HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminRegisterController() {
        super();
        dao = new UserDAO();
        sdao = new StaffDAO();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		view = request.getRequestDispatcher("registerAdmin.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = request.getSession(true);
		User user = new User();
		Staff staff = new Staff();
		
		user.setName(request.getParameter("name"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setUser_type(request.getParameter("user_type"));
		
		staff.setPosition(request.getParameter("position"));
		user = UserDAO.getUser(user);
		
		//check if user exists
		if(!user.isValid()) {
			try {
				//if user not exist, add/register the user
				dao.add(user);
				User us = UserDAO.getUserByEmail(user.getEmail());
				int tempUserid = us.getUserid();
				staff.setStaffid(tempUserid);
				sdao.addStaff(staff);

			}catch (Exception e) {
				e.printStackTrace();
			}
			//redirect to login.jsp page
			 int userid = (Integer)session.getAttribute("sessionId");
			request.setAttribute("user", UserDAO.getUserById(userid));
			request.setAttribute("staff", StaffDAO.getStaffById(staff.getStaffid()));
			view = request.getRequestDispatcher("admindashboard.jsp");
			view.forward(request, response);
		}
	}


}
