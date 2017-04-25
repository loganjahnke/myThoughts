package edu.uga.cs.boundary;

import java.io.IOException;

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
import edu.uga.cs.object.*;
import edu.uga.cs.session.Session;
import edu.uga.cs.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

@WebServlet("/feature")
public class FeatureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String templateDir = "/WEB-INF/templates";
	private TemplateProcessor processor;

	private Configuration cfg;

	public FeatureServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		cfg = new Configuration(Configuration.VERSION_2_3_25);
		cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
		processor = new TemplateProcessor(templateDir, cfg);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdministratorController ac = new AdministratorController();
		UserController uc = new UserController();
		MyThoughtsController mtc = new MyThoughtsController();
		TopicListController tlc = new TopicListController();

		int id = Integer.parseInt(request.getParameter("id"));
		String whattodo = request.getParameter("do");

		HttpSession httpSession = null;
        String ssid = null;
        Session session = null;

        String res = "";

		// Get Session
        try {
            httpSession = request.getSession();
            session = SessionManager.prepareSession(httpSession, ssid, session);
        } catch (MyThoughtsException mte) {
            MTError.error(processor, response, cfg, mte);
            return;
        }

		try {
			if (whattodo.equals("delete"))
				ac.deleteTopic(id);
			else if (whattodo.equals("feature"))
				ac.featureTopic(id);
			else if (whattodo.equals("unfeature"))
				ac.unfeatureTopic(id);
			else if (whattodo.equals("upvote") && !session.getIsAdmin())
				session.setUser(uc.upvoteTopic((User) session.getUser(), id));
			else if (whattodo.equals("downvote") && !session.getIsAdmin())
				session.setUser(uc.downvoteTopic((User) session.getUser(), id));
			else if (whattodo.equals("addComment")) {
				String subject = request.getParameter("subj");
				String argument = request.getParameter("arg");
				DebateTopic topic = tlc.getTopic(id);
				
				Comment c = new Comment();
				c.setSubject(subject);
				c.setArgument(argument);
				c.setUser((User) session.getUser());
				c.setDebateTopic(topic);
				
				int result = mtc.saveComment(c);
				
				if (result != -1) {
					System.out.println("Saved");
				}
				else {
					System.out.println("Failed!");
				}
			}
		} catch (Exception e) {
			System.out.println("not featured");
		}

    	response.setContentType("text/plain");
       	response.setCharacterEncoding("UTF-8");
       	response.getWriter().write(res);
	}
}
