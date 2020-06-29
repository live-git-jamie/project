package com.dreams.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dreams.service.DreamService;

@SuppressWarnings("serial")
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String date = req.getParameter("date");
		
		DreamService dreamService = new DreamService();
		boolean deleted = dreamService.deleteDream(date);
		if(deleted) {
//			RequestDispatcher requestDispatcher = req.getRequestDispatcher("deleted-dream.html");
//			requestDispatcher.forward(req, resp);
			PrintWriter printWriter = resp.getWriter();
			
			// build HTML
			String html = "<html>";
			html += "<h2>Deleted entry on "+date+"<h2/>";
			html += "<html/>";
			
			printWriter.println(html);
		}
	}
}
