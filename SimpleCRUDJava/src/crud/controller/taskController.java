package crud.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crud.dao.tasksDao;
import crud.model.task;

public class taskController extends HttpServlet{
	private static final long serialVersionUID =1L;
	private static String INSERT_OR_EDIT = "/task.jsp";
	private static String LIST_TASK = "/tasklist.jsp";
	
	private tasksDao dao;
	
	public taskController() {
		super();
		dao = new tasksDao();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			dao.deletetask(id);
			forward = LIST_TASK;
			request.setAttribute("tasks", dao.getAllTasks());
		}
		else if(action.equalsIgnoreCase("edit")) {
			forward = INSERT_OR_EDIT;
			int id = Integer.parseInt(request.getParameter("id"));
			task t = dao.getTaskById(id); 
			request.setAttribute("task", t);
		}
		else if (action.equalsIgnoreCase("tasklist")) {
			forward = LIST_TASK;
			request.setAttribute("tasks", dao.getAllTasks());
		}
		else {
			forward = INSERT_OR_EDIT;
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		task t = new task();
		t.setName(request.getParameter("name"));
		t.setDesc(request.getParameter("description"));
		try {
			Date dateCreated = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dateCreated"));
			Date dateUpdated = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dateUpdated"));
			t.setDateCreated(dateCreated);
			t.setDateUpdated(dateUpdated);
			
		} catch(ParseException e) {
			e.printStackTrace();
		}
		
		String id = request.getParameter("id");
		if(id==null || id.isEmpty()) {
			dao.addtask(t);
		}
		else {
			t.setId(Integer.parseInt(id));
			dao.updatetask(t);
		}
		
		RequestDispatcher view = request.getRequestDispatcher(LIST_TASK);
		request.setAttribute("tasks", dao.getAllTasks());
		view.forward(request,response);
	}
}
