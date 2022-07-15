package khairat.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import khairat.dao.StaffDAO;
import khairat.model.Staff;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static StaffDAO dao;	
    RequestDispatcher view;
    private String forward;
	HttpSession session;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        dao = new StaffDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
			
			if(action.equalsIgnoreCase("admindashboard")) {
				forward = "admindashboard.jsp";
				int userid = Integer.parseInt(request.getParameter("userid"));
				request.setAttribute("staff", StaffDAO.getStaffById(userid));
			}
			if(action.equalsIgnoreCase("updateAdmin")) {
				forward = "updateAdmin.jsp";
				int userid = Integer.parseInt(request.getParameter("userid"));
				request.setAttribute("staff", StaffDAO.getStaffById(userid));
			}
			
			view = request.getRequestDispatcher(forward);
			view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = request.getSession(true);
		Staff st = new Staff();
		st.setName(request.getParameter("name"));
		st.setPosition(request.getParameter("position"));
		int id = (Integer)session.getAttribute("sessionId");
		st.setStaffid(id);
		session.setAttribute("sessionName", st.getName());
		dao.updateStaff(st);
		
		request.setAttribute("staff", StaffDAO.getStaffById(st.getStaffid()));
		view = request.getRequestDispatcher("admindashboard.jsp");
		view.forward(request, response);
	}

}