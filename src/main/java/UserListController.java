

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import khairat.dao.KariahDAO;
import khairat.dao.PaymentDAO;
import khairat.dao.UserDAO;
import khairat.model.Kariah;
import khairat.model.Payment;
import khairat.model.User;

/**
 * Servlet implementation class UserListController
 */
@WebServlet("/UserListController")
public class UserListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO dao;
	private KariahDAO kdao;
    RequestDispatcher view;
    private String forward;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("listAllUser")) {
			forward = "userList.jsp"; 
			request.setAttribute("kariahs", KariahDAO.getAllUserKariah());
		}
		
		if(action.equalsIgnoreCase("viewUser")) {
			forward = "userViewAdmin.jsp";
		
			int uid = Integer.parseInt(request.getParameter("userid"));
			String tempuserid = String.valueOf(uid);
			String trimuid = tempuserid.trim();
			
			int kuid = Integer.parseInt(request.getParameter("userid"));
			String tempkid = String.valueOf(kuid);
			String trimkid = tempkid.trim();
		
			boolean activestatus = Boolean.parseBoolean(request.getParameter("activeStatus"));
			String tempactive = String.valueOf(activestatus);
			String trimStatus = tempactive.trim();
		
			int uId = Integer.parseInt(trimuid);
			Boolean aS = Boolean.parseBoolean(trimStatus);
			int kUid = Integer.parseInt(trimkid);
		
			User user = new User();
			Kariah kariah = new Kariah();
			
			user.setUserid(uId);
			user.setActiveStatus(aS);
			kariah.setUserid(kUid);
			
			
			request.setAttribute("user", UserDAO.getUserById(uId));
			request.setAttribute("kariah", KariahDAO.getKariahById(kUid));
		}
		
		if(action.equalsIgnoreCase("updateUser")) {
			forward = "userViewAdmin.jsp";
		
			int uid = Integer.parseInt(request.getParameter("userid"));
			String tempuserid = String.valueOf(uid);
			String trimuid = tempuserid.trim();
		
			int kuid = Integer.parseInt(request.getParameter("userid"));
			String tempkid = String.valueOf(kuid);
			String trimkid = tempkid.trim();
			
			Boolean activestatus = Boolean.parseBoolean(request.getParameter("activeStatus"));
			String tempactive = String.valueOf(activestatus);
			String trimStatus = tempactive.trim();
			
			String tempDeath = request.getParameter("userDeathDate");
			String trimdDate = tempDeath.trim();
		
			int uId = Integer.parseInt(trimuid);
			int kUid = Integer.parseInt(trimkid);
			Boolean aS = Boolean.parseBoolean(trimStatus);
		
			User user = new User();
			Kariah kariah = new Kariah();
			
			kariah.setUserDeathDate(trimdDate);
			user.setUserid(uId);
			user.setActiveStatus(aS);
			kariah.setUserid(kUid);
			
			UserDAO.updateActiveStatus(user);
			
			request.setAttribute("user", UserDAO.getUserById(uId));
			request.setAttribute("kariah", KariahDAO.getKariahById(kUid));
			request.setAttribute("kariahs", KariahDAO.getAllUserKariah());
		}
		if(action.equalsIgnoreCase("displayactivekariah")){
			forward = "displayactivekariah.jsp";
			
		}
		view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			User user = new User();
			Kariah kariah = new Kariah();
			user.setActiveStatus(Boolean.parseBoolean(request.getParameter("activeStatus")));
			user.setUserid(Integer.parseInt(request.getParameter("userid")));
			kariah.setUserid(Integer.parseInt(request.getParameter("userid")));
			String deathdate = request.getParameter("userDeathDate");
			kariah.setUserDeathDate(request.getParameter("userDeathDate"));
			System.out.println("kariah deathdate:" + deathdate);
			
			System.out.println(user.isActiveStatus());
			System.out.println(user.getUserid());
			
			UserDAO.updateActiveStatus(user);
			KariahDAO.updateDeathDate(kariah);
			
			request.setAttribute("user", UserDAO.getUserById(user.getUserid()));
			request.setAttribute("kariah", KariahDAO.getKariahById(user.getUserid()));
			view = request.getRequestDispatcher("userViewAdmin.jsp");
			view.forward(request, response);
	}

}