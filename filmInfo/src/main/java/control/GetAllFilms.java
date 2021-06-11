package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Film;
import model.FilmDAO;

@WebServlet("/GetAllFilms")
public class GetAllFilms extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FilmDAO dao = new FilmDAO();
		ArrayList<Film> allFilms = dao.getAllFilms();

		//send output to different JSP depending on format variable (JSON/XML/Plaintext)
		request.setAttribute("films", allFilms);
		
		String requestedFormat = request.getParameter("format");
		String outputPage;
		
		if (requestedFormat==null || "json".equals(requestedFormat)) {
			response.setContentType("text/javascript");
			outputPage = "/WEB-INF/results/films-json.jsp";
		} else if ("xml".equals(requestedFormat)) {
			response.setContentType("text/xml");
		  	outputPage = "/WEB-INF/results/films-xml.jsp";
		} else {
			response.setContentType("text/plain");
			outputPage = "/WEB-INF/results/films-string.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(outputPage);
	  	dispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
