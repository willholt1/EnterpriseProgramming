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

@WebServlet("/UpdateFilm")
public class UpdateFilm extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get variables from url
		int updateId = Integer.parseInt(request.getParameter("id"));
		String updateTitle = request.getParameter("title");
		int updateYear = Integer.parseInt(request.getParameter("year"));
		String updateDirector = request.getParameter("director");
		String updateStars = request.getParameter("stars");
		String updateReview = request.getParameter("review");
		
		Film f = new Film(updateId, updateTitle, updateYear, updateDirector, updateStars, updateReview);
		FilmDAO dao = new FilmDAO();
		int updateCheck = dao.updateFilm(f);
		
		//convert 1/0 returned to a string to be displayed
		String updateCheckStr;
		if(updateCheck == 1) {
			updateCheckStr = "Record Update Successful";
		}else {
			updateCheckStr = "Record Update Unsuccessful";
		}
		System.out.println(updateCheckStr);
		
		//send output to the JSP page
		request.setAttribute("response", updateCheckStr);
		response.setContentType("text/plain");
		String outputPage = "/WEB-INF/results/films-cud.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(outputPage);
	  	dispatcher.include(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
