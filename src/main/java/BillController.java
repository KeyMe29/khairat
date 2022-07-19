

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import khairat.dao.BillDAO;
import khairat.model.Bill;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/BillController")
public class BillController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private BillDAO dao;
     RequestDispatcher view;
     private String forward;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillController() {
        super();
        dao = new BillDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("listBill")) {
			forward = "listBill.jsp";
			request.setAttribute("bills", BillDAO.getAllBills());
		}
		if(action.equalsIgnoreCase("viewBill")) {
			forward = "viewBill.jsp";
			int bid = Integer.parseInt(request.getParameter("bid"));
			request.setAttribute("bill", BillDAO.getBillById(bid));
		}
		if(action.equalsIgnoreCase("deleteBill")) {
			forward = "listBill.jsp";
			int bid = Integer.parseInt(request.getParameter("bid"));
			dao.deleteBill(bid);
			request.setAttribute("bills", BillDAO.getAllBills());
		}
		if(action.equalsIgnoreCase("updateBill")) {
			forward = "updateBill.jsp";
			int bid = Integer.parseInt(request.getParameter("bid"));
			request.setAttribute("bill", BillDAO.getBillById(bid));
		}
		
		view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Bill bill = new Bill();
		bill.setBillname(request.getParameter("billname"));
		bill.setDate(request.getParameter("date"));
		bill.setAmount(Double.parseDouble(request.getParameter("amount")));
		bill.setUserid(Integer.parseInt(request.getParameter("userid")));
		
		String bid = request.getParameter("bid");
		
		if(bid == null || bid.isEmpty()) {
			dao.addBill(bill); 
		}
		else {
			bill.setBid(Integer.parseInt(request.getParameter("bid")));
			dao.updateBill(bill);
		}
		
		request.setAttribute("bills", BillDAO.getAllBills());
		view = request.getRequestDispatcher("listBill.jsp");
		view.forward(request, response);

	}

}
