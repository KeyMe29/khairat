package khairat.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import khairat.dao.DependentDAO;
import khairat.dao.KariahDAO;
import khairat.dao.MosqueDAO;
import khairat.dao.StaffDAO;
import khairat.dao.UserDAO;
import khairat.model.Kariah;
import khairat.model.User;

/**
 * Servlet implementation class KariahController
 */
@WebServlet("/KariahController")
public class KariahController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static KariahDAO kdao;
	private static DependentDAO ddao;
	HttpSession session;
	RequestDispatcher view;
	private String forward;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KariahController() {
        super();
        kdao = new KariahDAO();
        ddao = new DependentDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("homepage")) {
			forward = "homepage.jsp";
			int userid = Integer.parseInt(request.getParameter("userid"));
			String email = request.getParameter("email");
			
			request.setAttribute("user", UserDAO.getUserByEmail(email));
			request.setAttribute("kariah", KariahDAO.getKariahById(userid));
		}
		if(action.equalsIgnoreCase("updateKariah")) {
			forward = "updateKariah.jsp";
			int userid = Integer.parseInt(request.getParameter("userid"));
			String email = request.getParameter("email");
			
			request.setAttribute("user", UserDAO.getUserByEmail(email));
			request.setAttribute("kariah", KariahDAO.getKariahById(userid));
			request.setAttribute("mosques", MosqueDAO.getAllmosque());
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
		Kariah kariah = new Kariah();
		kariah.setAddress(request.getParameter("address"));
		kariah.setPhoneNo(request.getParameter("phoneNo"));
		kariah.setMaritalstat(request.getParameter("maritalstat"));
		kariah.setUserDeathDate(request.getParameter("userDeathDate"));
		kariah.setUserid(Integer.parseInt(request.getParameter("userid")));
		kariah.setMosqueId(Integer.parseInt(request.getParameter("mosqueId")));
		User us = UserDAO.getUserById(kariah.getUserid());
		session.setAttribute("sessionName",us.getName());
		kdao.updateKariah(kariah);
		kdao.updateDeathDate(kariah);
		
		request.setAttribute("user", UserDAO.getUserById(kariah.getUserid()));
		request.setAttribute("kariah", KariahDAO.getKariahById(kariah.getUserid()));
		request.setAttribute("mosque", MosqueDAO.getmosqueById(kariah.getUserid()));
		view = request.getRequestDispatcher("homepage.jsp");
		view.forward(request, response);
		
	}

}
