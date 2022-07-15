package khairat.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import khairat.dao.PaymentDAO;
import khairat.dao.UserDAO;
import khairat.dao.BillDAO;
import khairat.dao.KariahDAO;
import khairat.model.Payment;

/**
 * Servlet implementation class PaymentController
 */
@WebServlet("/PaymentController")
public class PaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private PaymentDAO dao;
     private BillDAO bdao;
     RequestDispatcher view;
     private String forward;
     HttpSession session;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentController() {
        super();
        dao = new PaymentDAO();
        bdao = new BillDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		session = request.getSession(true);
		String role = (String)session.getAttribute("sessionRole");
		
		if(action.equalsIgnoreCase("listPayment")) {
			forward = "listPayment.jsp";
			int userid = Integer.parseInt(request.getParameter("userid"));
			request.setAttribute("payments", PaymentDAO.getUserPayment(userid));
			request.setAttribute("kariah", KariahDAO.getKariahById(userid));
		}
		if(action.equalsIgnoreCase("listAllPayment")) {
			forward = "listAllPayment.jsp";
			request.setAttribute("payments", PaymentDAO.getAllPayments());
		}
		if(action.equalsIgnoreCase("viewPayment")) {
			if(role.equalsIgnoreCase("admin")) 
				forward = "viewPaymentAdmin.jsp";
			else 
				forward = "viewPayment.jsp";
			
			int paymentid = Integer.parseInt(request.getParameter("pid"));
			String temppid = String.valueOf(paymentid);
			String trimpid = temppid.trim();
			
			int billid = Integer.parseInt(request.getParameter("bid"));
			String tempbid = String.valueOf(billid);
			String trimbid = tempbid.trim();
			
			int pid = Integer.parseInt(trimpid);
			int bid = Integer.parseInt(trimbid);
			
			request.setAttribute("payment", PaymentDAO.getPaymentById(pid));
			request.setAttribute("bill", BillDAO.getBillById(bid));
		}
		if(action.equalsIgnoreCase("deletePayment")) {
			forward = "listPayment.jsp";
			int pid = Integer.parseInt(request.getParameter("pid"));
			dao.deletePayment(pid);
			request.setAttribute("payments", PaymentDAO.getAllPayments());
		}
		if(action.equalsIgnoreCase("updatePayment")) {
			forward = "updatePayment.jsp";
			int pid = Integer.parseInt(request.getParameter("pid"));
			request.setAttribute("payment", PaymentDAO.getPaymentById(pid));
		}
		if(action.equalsIgnoreCase("addPayment")) {
			forward = "addPayment.jsp";
			request.setAttribute("bills", BillDAO.getAllBills());
		}
		
		view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			Payment payment = new Payment();
			payment.setBid(Integer.parseInt(request.getParameter("bid")));
			payment.setMethod(request.getParameter("method"));
			payment.setUserid(Integer.parseInt(request.getParameter("userid")));
			
			String pid = request.getParameter("pid");
			
			if(pid == null || pid.isEmpty()) {
				dao.addPayment(payment); 
			}
			else {
				payment.setPid(Integer.parseInt(request.getParameter("pid")));
				dao.updatePayment(payment);
			}
			
			request.setAttribute("payments", PaymentDAO.getUserPayment(payment.getUserid()));
			request.setAttribute("kariah", KariahDAO.getKariahById(payment.getUserid()));
			view = request.getRequestDispatcher("listPayment.jsp");
			view.forward(request, response);
	}

}
