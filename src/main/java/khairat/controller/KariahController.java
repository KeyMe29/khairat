package khairat.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import khairat.dao.KariahDAO;
import khairat.dao.StaffDAO;
import khairat.dao.UserDAO;
import khairat.model.Kariah;

/**
 * Servlet implementation class KariahController
 */
@WebServlet("/KariahController")
public class KariahController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static KariahDAO kdao;
	HttpSession session;
	RequestDispatcher view;
	private String forward;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KariahController() {
        super();
        kdao = new KariahDAO();
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
		kariah.setUsername(request.getParameter("username"));
		kariah.setPhoneno(Integer.parseInt(request.getParameter("phoneno")));
		kariah.setMaritalstat(request.getParameter("maritalstat"));
		kariah.setGender(request.getParameter("gender"));
		kariah.setUserid(Integer.parseInt(request.getParameter("userid")));
		session.setAttribute("sessionName", kariah.getUsername());
		kdao.updateKariah(kariah);
		
		request.setAttribute("kariah", KariahDAO.getKariahById(kariah.getUserid()));
		view = request.getRequestDispatcher("homepage.jsp");
		view.forward(request, response);
		
	}

}
