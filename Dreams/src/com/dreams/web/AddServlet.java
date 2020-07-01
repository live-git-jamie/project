package com.dreams.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dreams.model.Dream;
import com.dreams.service.DreamService;

@SuppressWarnings("serial")
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String dreamDate = req.getParameter("dreamDate");
		String dreamTitle = req.getParameter("dreamTitle");
		String dreamDescr = req.getParameter("dreamDescr");
		float dreamDuration = Float.parseFloat(req.getParameter("dreamDuration"));
		// For now user will always be 1
		int dreamRecordsId = 1;
		
		// Send dream to service layer
		Dream dream = new Dream(-1,dreamRecordsId,dreamDate,
				dreamTitle,dreamDescr,dreamDuration);
		DreamService dreamService = new DreamService();
		boolean saved = dreamService.saveDream(dream);
		if(saved) {
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("added-dream.html");
			requestDispatcher.forward(req, resp);
		} else {
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("added-dream-failed.html");
			requestDispatcher.forward(req, resp);
		}
		
	}
}
