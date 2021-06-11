package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Film;
import model.FilmDAO;


@WebServlet("/InsertFilm")
public class InsertFilm extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get variables from url
		String insTitle = request.getParameter("title");
		int insYear = Integer.parseInt(request.getParameter("year"));
		String insDirector = request.getParameter("director");
		String insStars = request.getParameter("stars");
		String insReview = request.getParameter("review");
		
		//id passed to constructor is 0 as id isn't used by the SQL
		Film f = new Film(0, insTitle, insYear, insDirector, insStars, insReview);
		FilmDAO dao = new FilmDAO();
		int insCheck = dao.insertFilm(f);
		
		//convert 1/0 returned to a string to be displayed
		String insCheckStr;
		if(insCheck == 1) {
			insCheckStr = "Record Insertion Successful";
		}else {
			insCheckStr = "Record Insertion Unsuccessful";
		}
		System.out.println(insCheckStr);
		
		//send output to the JSP page
		request.setAttribute("response", insCheckStr);
		response.setContentType("text/plain");
		String outputPage = "/WEB-INF/results/films-cud.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(outputPage);
	  	dispatcher.include(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
