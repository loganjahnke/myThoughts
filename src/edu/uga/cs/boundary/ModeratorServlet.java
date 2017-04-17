package edu.uga.cs.boundary;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs.MyThoughtsException;
import edu.uga.cs.logic.*;
import edu.uga.cs.object.User;
import edu.uga.cs.session.Session;
import edu.uga.cs.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateExceptionHandler;

@WebServlet("/assign-moderator")
public class ModeratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String templateDir = "/WEB-INF/templates";
	private TemplateProcessor processor;

	private Configuration cfg;

	public ModeratorServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		cfg = new Configuration(Configuration.VERSION_2_3_25);
		cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
		processor = new TemplateProcessor(templateDir, cfg);
	}

	private void display(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		String templateName = "assign-moderator.ftl";

        AdministratorController ac = new AdministratorController();
        ArrayList<User> users = new ArrayList<User>();

        Session session = sessionCheck(request, response);

        // Get categories
		try {
            users = ac.getUsers();
		} catch (MyThoughtsException mte) {
			MTError.error(processor, response, cfg, mte);
			return;
		}

        root.put("user", session.getUser());
        root.put("visitor", session.getUser() == null);
        root.put("nonadmin", !session.getIsAdmin());
        root.put("users", users);

        processor.processTemplate(templateName, root, response);
	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sessionCheck(request, response);
		int userId = Integer.parseInt(request.getParameter("userid"));
		AdministratorController ac = new AdministratorController();
		
		boolean isModerator = false;
		
		try {
			isModerator = ac.toggleModerator(userId);
		} catch (MyThoughtsException mte) {
			MTError.error(processor, response, cfg, mte);
			return;
		}
		
		response.setContentType("text/plain");
       	response.setCharacterEncoding("UTF-8");
       	response.getWriter().write(isModerator ? "moderator" : "not");
	}

	private Session sessionCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = null;
        String ssid = null;
        Session session = null;

        // Get Session
        try {
            httpSession = request.getSession();
            session = SessionManager.prepareSession(httpSession, ssid, session);
        } catch (MyThoughtsException mte) {
            MTError.error(processor, response, cfg, mte);
        }

		if (!session.getIsAdmin())
			MTError.error(processor, response, cfg, "You do not have the credentials to view this page.");

        return session;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		display(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		create(request, response);
	}
}
