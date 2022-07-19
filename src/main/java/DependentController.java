

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import khairat.dao.BillDAO;
import khairat.dao.DependentDAO;
import khairat.dao.KariahDAO;
import khairat.dao.PaymentDAO;
import khairat.dao.UserDAO;
import khairat.model.Dependent;
import khairat.model.Payment;

/**
 * Servlet implementation class DependentController
 */
@WebServlet("/DependentController")
public class DependentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DependentDAO dao;
	HttpSession session;
	RequestDispatcher view;
	private String forward;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DependentController() {
        super();
        dao = new DependentDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		session = request.getSession(true);
		
		if(action.equalsIgnoreCase("listDependent")) {
			forward = "listDependent.jsp";
			int userid = Integer.parseInt(request.getParameter("userid"));
			request.setAttribute("dependents", DependentDAO.getKariahDependent(userid));
			request.setAttribute("user", UserDAO.getUserById(userid));
		}
		if(action.equalsIgnoreCase("viewDependent")) {
			forward = "viewDependent.jsp";
			int userid = Integer.parseInt(request.getParameter("userid"));
			String tempuserid = String.valueOf(userid);
			String trimuserid = tempuserid.trim();
			
			int depid = Integer.parseInt(request.getParameter("depid"));
			String tempdepid = String.valueOf(depid);
			String trimdepid = tempdepid.trim();
			
			int userId = Integer.parseInt(trimuserid);
			int depId = Integer.parseInt(trimdepid);
			
			Dependent dependent = new Dependent();
			dependent.setUserid(userId);
			dependent.setDepid(depId);
			
			request.setAttribute("user", UserDAO.getUserById(userId));
			request.setAttribute("dependent", DependentDAO.getDependentById(depId));
		}
		if(action.equalsIgnoreCase("deleteDependent")) {
			forward = "listDependent.jsp";
			int depid = Integer.parseInt(request.getParameter("depid"));
			session = request.getSession(true);
			int userid = (Integer)session.getAttribute("sessionId");	
			dao.deleteDependent(depid);
			request.setAttribute("dependents", DependentDAO.getKariahDependent(userid));
			request.setAttribute("user", UserDAO.getUserById(userid));
		}
		if(action.equalsIgnoreCase("updateDependent")) {
			forward = "updateDependent.jsp";
			int depid = Integer.parseInt(request.getParameter("depid"));
			String tempdepid = String.valueOf(depid);
			String trimdepid = tempdepid.trim();
			
			int depId = Integer.parseInt(trimdepid);
			
			Dependent dependent = new Dependent();
			dependent.setDepid(depId);
			dao.updateDependent(dependent);
			
			request.setAttribute("dependent", DependentDAO.getDependentById(depId));
		}
		
		view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dependent dependent = new Dependent();
		dependent.setDepName(request.getParameter("depName"));
		dependent.setDepIcNo(request.getParameter("depIcNo"));
		dependent.setDepGender(request.getParameter("depGender"));
		dependent.setDepPhoneNo(request.getParameter("depPhoneNo"));
		dependent.setRelation(request.getParameter("relation"));
		
		session = request.getSession(true);
		int userid = (Integer)session.getAttribute("sessionId");	
		dependent.setUserid(userid);
		
		String depid = request.getParameter("depid");
		
		if(depid == null || depid.isEmpty()) {
			dao.addDependent(dependent); 
		}
		else {
			dependent.setDepid(Integer.parseInt(request.getParameter("depid")));
			dao.updateDependent(dependent);
		}
		
		request.setAttribute("dependents", DependentDAO.getKariahDependent(dependent.getUserid()));
		view = request.getRequestDispatcher("listDependent.jsp");
		view.forward(request, response);
	}

}
