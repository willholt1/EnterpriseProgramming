package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Film;
import model.FilmDAO;


@WebServlet("/DeleteFilm")
public class DeleteFilm extends HttpServlet { 

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get the id of the film to be deleted from the url and run deleteFilm from the DAO
		int delId = Integer.parseInt(request.getParameter("id"));
		FilmDAO dao = new FilmDAO();
		int delCheck = dao.deleteFilm(delId);
		
		//convert 1/0 returned to a string to be displayed
		String delCheckStr;
		if(delCheck == 1) {
			delCheckStr = "Record Deletion Successful";
		}else {
			delCheckStr = "Record Deletion Unsuccessful";
		}
		System.out.println(delCheckStr);
		
		//send output to the JSP page
		request.setAttribute("response", delCheckStr);
		response.setContentType("text/plain");
		String outputPage = "/WEB-INF/results/films-cud.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(outputPage);
	  	dispatcher.include(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
