

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import khairat.dao.ReportDAO;
import khairat.dao.StaffDAO;
import khairat.dao.UserDAO;

/**
 * Servlet implementation class ReportController
 */
@WebServlet("/ReportController")
public class ReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ReportDAO dao;
    RequestDispatcher view;
    private String forward;
	HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportController() {
        super();
        dao = new ReportDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		forward = "report.jsp";
		request.setAttribute("alive", ReportDAO.getAliveUser());
		request.setAttribute("dead", ReportDAO.getDeceasedUser());
		request.setAttribute("reportApprove", ReportDAO.getReportApprovedPayment());
		request.setAttribute("reportPending", ReportDAO.getReportPendingPayment());
		request.setAttribute("total", ReportDAO.totalApprovedPayment());
		//request.setAttribute("gender", dao.getTotalGender());
		view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
}


