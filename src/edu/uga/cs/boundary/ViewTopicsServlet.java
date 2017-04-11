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
import edu.uga.cs.object.DebateTopic;
import edu.uga.cs.object.User;
import edu.uga.cs.session.Session;
import edu.uga.cs.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateExceptionHandler;

@WebServlet("/topics")
public class ViewTopicsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String templateDir = "/WEB-INF/templates";
	private TemplateProcessor processor;

	private Configuration cfg;

	public ViewTopicsServlet() {
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
		String templateName;

        HttpSession httpSession = null;
        String ssid = null;
        Session session = null;

        MyThoughtsController mtc = new MyThoughtsController();
        TopicListController tlc = new TopicListController();
        ArrayList<DebateTopic> topics = new ArrayList<DebateTopic>();

        // Get Session
        try {
            httpSession = request.getSession();
            session = SessionManager.prepareSession(httpSession, ssid, session);
        } catch (MyThoughtsException mte) {
            MTError.error(processor, response, cfg, mte);
            return;
        }

        String categoryName = request.getParameter("category");
        String userName = request.getParameter("user");

        // Get topics
		try {
			if (categoryName != null && !categoryName.equals("Recent")) {
				DebateCategory dc = mtc.getCategory(categoryName);
				root.put("category", dc);
	            topics = tlc.getTopics(dc);
	            root.put("sender", "category");
        	} else if (categoryName != null && categoryName.equals("Recent")) {
        		DebateCategory dc = mtc.getCategory(categoryName);
				root.put("category", dc);
	            topics = tlc.getRecentTopics();
	            root.put("sender", "category");
        	} else if (userName != null) {
				User user = mtc.getUser(userName);
	            topics = tlc.getTopics(user);
	            root.put("sender", "user");
        	} else {
        		DebateCategory dc = mtc.getCategory("Recent");
				root.put("category", dc);
        		topics = tlc.getRecentTopics();
	            root.put("sender", "category");
        	}
		} catch (MyThoughtsException mte) {
			MTError.error(processor, response, cfg, mte);
			return;
		}

        templateName = "view-topics.ftl";

        root.put("user", session.getUser());
        root.put("visitor", session.getUser() == null);
        root.put("nonadmin", !session.getIsAdmin());
        root.put("topics", topics);

        processor.processTemplate(templateName, root, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		display(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		display(request, response);
	}
}
