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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String templateDir = "/WEB-INF/templates";

	private Configuration cfg;

	public LoginServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		cfg = new Configuration(Configuration.VERSION_2_3_25);
		cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthenticateController ac = new AuthenticateController();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		boolean error = false;
		try {
			error = ac.precheckLogin(username, password);
		} catch (Exception e) {
			error = true;
		}

		String r = "good";
		if (error)
			r = "bad";

    	response.setContentType("text/plain");
       	response.setCharacterEncoding("UTF-8");
       	response.getWriter().write(r);
	}
}
