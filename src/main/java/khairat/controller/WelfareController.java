package khairat.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import khairat.dao.WelfareDAO;
import khairat.model.Welfare;

/**
 * Servlet implementation class WelfareController
 */
@WebServlet("/WelfareController")
public class WelfareController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private WelfareDAO dao;
    RequestDispatcher view;
    private String forward;
    HttpSession session;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelfareController() {
        super();
        dao = new WelfareDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Welfare welfare = new Welfare();
		welfare.setWelfareAmount(Double.parseDouble(request.getParameter("welfareAmount")));
		welfare.setWelfareDate(request.getParameter("welfareDate"));
		welfare.setNote(request.getParameter("note"));
		welfare.setUserid(Integer.parseInt(request.getParameter("userid")));
		welfare.setAdminid(Integer.parseInt(request.getParameter("adminid")));
		
		String welfareid = request.getParameter("welfareid");
		
		if(welfareid == null || welfareid.isEmpty()) {
			dao.addWelfare(welfare); 
		}
		else {
			welfare.setWelfareid(Integer.parseInt(request.getParameter("welfareid")));
			dao.updateWelfare(welfare);
		}
		
		request.setAttribute("welfares", WelfareDAO.getAllWelfares());
		view = request.getRequestDispatcher("listWelfare.jsp");
		view.forward(request, response);
	}

}
