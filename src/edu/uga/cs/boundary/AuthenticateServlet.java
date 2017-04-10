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
import edu.uga.cs.object.DebateCategory;
import edu.uga.cs.session.Session;
import edu.uga.cs.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateExceptionHandler;

@WebServlet("/home")
public class AuthenticateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String templateDir = "/WEB-INF/templates";
	private TemplateProcessor processor;

	private Configuration cfg;

	public AuthenticateServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		cfg = new Configuration(Configuration.VERSION_2_3_25);
		cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
		processor = new TemplateProcessor(templateDir, cfg);
	}

	private void authenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		String templateName;

        HttpSession httpSession = null;
        String ssid = null;
        Session session = null;

        AuthenticateController ac = new AuthenticateController();
        MyThoughtsController mtc = new MyThoughtsController();
        ArrayList<DebateCategory> categories = new ArrayList<DebateCategory>();

        // Get Session
        try {
            httpSession = request.getSession();
            session = SessionManager.prepareSession(httpSession, ssid, session);
        } catch (MyThoughtsException mte) {
            MTError.error(processor, response, cfg, mte);
            return;
        }

        // Get the parameters
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Login the User and get categories
		try {
			if (session.getUser() == null) {
			    if (firstname == null) {
			    	ssid = ac.login(session, username, password);
			    } else {
			    	ssid = ac.register(session, firstname, lastname, username, email, password);
			    }
			    httpSession.setAttribute("ssid", ssid);
			}

		    // Only push 7 featured categories
            categories = mtc.getCategories();
            while (categories.size() > 7)
        		categories.remove(7);
		} catch (MyThoughtsException mte) {
			MTError.error(processor, response, cfg, mte);
			return;
		}

        if (session.getIsAdmin()) {
        	templateName = "admin-index.ftl";
        } else {
        	templateName = "regindex.ftl";
        }

        root.put("user", session.getUser());
        root.put("visitor", session.getUser() == null);
        root.put("nonadmin", !session.getIsAdmin());
        root.put("categories", categories);

        processor.processTemplate(templateName, root, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		authenticate(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		authenticate(request, response);
	}
}
