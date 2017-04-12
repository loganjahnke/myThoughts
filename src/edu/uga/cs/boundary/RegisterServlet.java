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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String templateDir = "/WEB-INF/templates";

	private Configuration cfg;

	public RegisterServlet() {
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

		String email = request.getParameter("email");
		String username = request.getParameter("username");

		boolean emailDuplicate = false;
		boolean usernameDuplicate = false;
		try {
			emailDuplicate = ac.precheckEmail(email);
			usernameDuplicate = ac.precheckUsername(username);
		} catch (Exception e) {
			emailDuplicate = true;
			usernameDuplicate = true;
		}

		String r = "";
		if (emailDuplicate)
			r += "email";
		if (usernameDuplicate)
			r += "username";
		if (!emailDuplicate && !usernameDuplicate)
			r = "good";

    	response.setContentType("text/plain");
       	response.setCharacterEncoding("UTF-8");
       	response.getWriter().write(r);
	}
}
