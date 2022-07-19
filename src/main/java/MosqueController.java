

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import khairat.dao.BillDAO;
import khairat.dao.MosqueDAO;
import khairat.model.Mosque;

/**
 * Servlet implementation class MosqueController
 */
@WebServlet("/MosqueController")
public class MosqueController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MosqueDAO dao;
	RequestDispatcher view;
	private String forward;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MosqueController() {
        super();
        dao = new MosqueDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("addMosque")) {
			forward = "addMosque.jsp";
			request.setAttribute("mosques", MosqueDAO.getAllmosque());
		}
		if(action.equalsIgnoreCase("listMosque")) {
			forward = "listMosque.jsp";
			request.setAttribute("mosques", MosqueDAO.getAllmosque());
		}
		if(action.equalsIgnoreCase("viewMosque")) {
			forward = "viewMosque.jsp";
			int mosqueId = Integer.parseInt(request.getParameter("mosqueId"));
			request.setAttribute("mosque", MosqueDAO.getMosqueSuperv(mosqueId));
			request.setAttribute("mosques", MosqueDAO.getAllmosque());
		}
		if(action.equalsIgnoreCase("deleteMosque")) {
			forward = "listMosque.jsp";
			int mosqueId = Integer.parseInt(request.getParameter("mosqueId"));
			dao.deleteMosque(mosqueId);
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
		Mosque mos = new Mosque();
		mos.setMosqueName(request.getParameter("mosqueName"));
		mos.setMosqueAddress(request.getParameter("mosqueAddress"));
		mos.setSupervisorId(Integer.parseInt(request.getParameter("supervisorId")));
		
		String mosqueId = request.getParameter("mosqueId");
		
		if(mosqueId == null || mosqueId.isEmpty()) {
			dao.addMosque(mos);
		}
		else {
			mos.setMosqueId(Integer.parseInt(request.getParameter("mosqueId")));
			dao.updateMosque(mos);
		}
		
		request.setAttribute("mosques", MosqueDAO.getAllmosque());
		view = request.getRequestDispatcher("listMosque.jsp");
		view.forward(request, response);
	}

}
