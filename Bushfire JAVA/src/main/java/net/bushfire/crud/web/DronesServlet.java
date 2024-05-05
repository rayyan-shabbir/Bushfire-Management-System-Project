package net.bushfire.crud.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bushfire.crud.dao.DronesDAO;
import net.bushfire.crud.model.Drones;

/**
 * Servlet implementation class DronesServlet
 */
@WebServlet("/")
public class DronesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DronesDAO dronesDAO;
    
    public DronesServlet() {
    	this.dronesDAO = new DronesDAO();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	this.doGet(request, response);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

		System.out.println(action);
		
		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertDrones(request, response);
				break;
			case "/delete":
				deleteDrones(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateDrones(request, response);
				break;
			default:
				listDrones(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	
	}
	
	private void listDrones(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Drones> listDrones = dronesDAO.selectAllDrones();
		request.setAttribute("listDrones", listDrones);
		RequestDispatcher dispatcher = request.getRequestDispatcher("drones-list.jsp");
		dispatcher.forward(request, response);
	}

	
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("drones-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Drones existingDrones = dronesDAO.selectDrones(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("drones-form.jsp");
		request.setAttribute("user", existingDrones);
		dispatcher.forward(request, response);

	}

	private void insertDrones(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String dname = request.getParameter("dname");
		String dcoordinates = request.getParameter("dcoordinates");
		String dmodel = request.getParameter("dmodel");
		Drones newDrones = new Drones(dname, dcoordinates, dmodel);
		dronesDAO.insertDrones(newDrones);
		response.sendRedirect("list");
	}

	private void updateDrones(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String dname = request.getParameter("dname");
		String dcoordinates = request.getParameter("dcoordinates");
		String dmodel = request.getParameter("dmodel");

		Drones drone = new Drones(id, dname, dcoordinates, dmodel);
		dronesDAO.updateDrones(drone);
		response.sendRedirect("list");
	}

	private void deleteDrones(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		dronesDAO.deleteDrones(id);
		response.sendRedirect("list");

	}

}
