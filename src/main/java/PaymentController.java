

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.*;

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
			request.setAttribute("user", UserDAO.getUserById(userid));
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
			
			int billid = Integer.parseInt(request.getParameter("bid"));
			String tempbid = String.valueOf(billid);
			String trimbid = tempbid.trim();
			
			int userid = Integer.parseInt(request.getParameter("userid"));
			String tempuserid = String.valueOf(userid);
			String trimuid = tempuserid.trim();
			
			int bId = Integer.parseInt(trimbid);
			int uId = Integer.parseInt(trimuid);
			
			Payment payment = new Payment();
			payment.setBid(bId);
			payment.setUserid(uId);
			
			request.setAttribute("user", UserDAO.getUserById(uId));
			request.setAttribute("payment", PaymentDAO.getPaymentById(payment));
			request.setAttribute("bill", BillDAO.getBillById(bId));
		}
		if(action.equalsIgnoreCase("deletePayment")) {
			forward = "listPayment.jsp";
			int pid = Integer.parseInt(request.getParameter("pid"));
			dao.deletePayment(pid);
			request.setAttribute("payments", PaymentDAO.getAllPayments());
		}
		if(action.equalsIgnoreCase("updatePayment")) {
			forward = "listAllPayment.jsp";
			
			int billid = Integer.parseInt(request.getParameter("bid"));
			String tempbid = String.valueOf(billid);
			String trimbid = tempbid.trim();
			
			int userid = Integer.parseInt(request.getParameter("userid"));
			String tempuserid = String.valueOf(userid);
			String trimuid = tempuserid.trim();
			
			String tempStatus = request.getParameter("payStatus");
			String trimpaymentstatus = tempStatus.trim();
			Payment payment = new Payment();
			
			int bId = Integer.parseInt(trimbid);
			int uId = Integer.parseInt(trimuid);
			
			payment.setBid(bId);
			payment.setUserid(uId);
			payment.setPayStatus(trimpaymentstatus);
			System.out.println("dopost paystatus:" + payment.getPayStatus());
			dao.updatePayment(payment);
			
			
			request.setAttribute("payment", PaymentDAO.getPaymentById(payment));
			request.setAttribute("user", UserDAO.getUserById(uId));
			request.setAttribute("bill", BillDAO.getBillById(bId));
			request.setAttribute("payments", PaymentDAO.getAllPayments());
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
			payment.setRefid(request.getParameter("refid"));
			payment.setUserid(Integer.parseInt(request.getParameter("userid")));
			payment.setPayStatus(request.getParameter("payStatus"));
			
			payment.setPayDate(java.time.LocalDate.now());
			
			String type = request.getParameter("type");
			
			if(type.equalsIgnoreCase("add")) {
				dao.addPayment(payment); 
				request.setAttribute("payments", PaymentDAO.getUserPayment(payment.getUserid()));
				forward = "listPayment.jsp";
			}
			/*else {
				payment.setUserid(Integer.parseInt(request.getParameter("userid")));
				dao.updatePayment(payment);
				request.setAttribute("kariah", KariahDAO.getKariahById(payment.getUserid()));
				forward = "listAllPayment.jsp";
			}*/
			
			view = request.getRequestDispatcher(forward);
			view.forward(request, response);
	}

}
