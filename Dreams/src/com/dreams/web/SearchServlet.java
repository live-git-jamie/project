package com.dreams.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dreams.model.Dream;
import com.dreams.service.DreamService;

@SuppressWarnings("serial")
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String stringToSearch = req.getParameter("search");
		
		DreamService dreamService = new DreamService();
		List<Dream> dreams = dreamService.searchDatabase(stringToSearch);
		// Set up html
		PrintWriter printWriter = resp.getWriter();
		printWriter.println("<!DOCTYPE html>");
		printWriter.println("<html>");
		printWriter.println("	<head>");
		printWriter.println("		<title>Search results</title>");
		printWriter.println("	</head>");
		printWriter.println("	<body>");
		// Print dreams
		dreams.forEach((Dream dream) -> {
			System.out.println("Printing ->"+dream.getDreamId());
			printWriter.println("<h3>"+dream.getDreamDate()+"<h3/>");
			printWriter.println("<h3>"+dream.getDreamDuration()+"<h3/>");
			printWriter.println("<h3>"+dream.getDreamTitle()+"<h3/>");
			printWriter.println("<h3>"+dream.getDreamDescr()+"<h3/>");
			printWriter.println("<br/><br/><br/><br/>");
		});
		printWriter.println("	</body>");
		printWriter.println("</html>");
	}
}
