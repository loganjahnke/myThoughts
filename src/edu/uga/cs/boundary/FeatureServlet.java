package edu.uga.cs.boundary;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uga.cs.logic.*;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

@WebServlet("/feature")
public class FeatureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String templateDir = "/WEB-INF/templates";

	private Configuration cfg;

	public FeatureServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		cfg = new Configuration(Configuration.VERSION_2_3_25);
		cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdministratorController ac = new AdministratorController();

		int id = Integer.parseInt(request.getParameter("id"));
		String whattodo = request.getParameter("do");

		try {
			if (whattodo.equals("delete"))
				ac.deleteTopic(id);
			else if (whattodo.equals("feature"))
				ac.featureTopic(id);
		} catch (Exception e) {
			System.out.println("not featured");
		}

    	response.setContentType("text/plain");
       	response.setCharacterEncoding("UTF-8");
       	response.getWriter().write("");
	}
}
