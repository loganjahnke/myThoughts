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

@WebServlet("/edit-category")
public class EditCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String templateDir = "/WEB-INF/templates";
	private TemplateProcessor processor;

	private Configuration cfg;

	public EditCategoryServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		cfg = new Configuration(Configuration.VERSION_2_3_25);
		cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
		processor = new TemplateProcessor(templateDir, cfg);
	}

	private void viewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		String templateName;

        HttpSession httpSession = null;
        String ssid = null;
        Session session = null;
        
        MyThoughtsController mtc = new MyThoughtsController();

        // Get Session
        try {
            httpSession = request.getSession();
            session = SessionManager.prepareSession(httpSession, ssid, session);
        } catch (MyThoughtsException mte) {
            MTError.error(processor, response, cfg, mte);
            return;
        }

        if (session.getIsAdmin()) {
        	templateName = "create-category.ftl";
        } else {
        	MTError.error(processor, response, cfg, "You do not have the credentials to view this page.");
        	return;
        }

        DebateCategory dc = new DebateCategory();
        
        try {
        	dc = mtc.getCategory(request.getParameter("name"));
        } catch (MyThoughtsException mte) {
        	MTError.error(processor, response, cfg, mte);
        }

        root.put("user", session.getUser());
        root.put("visitor", session.getUser() == null);
        root.put("nonadmin", !session.getIsAdmin());
        root.put("update", true);
        root.put("category", dc);

        processor.processTemplate(templateName, root, response);
	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		String templateName;

        HttpSession httpSession = null;
        String ssid = null;
        Session session = null;

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

        DebateCategory dc = new DebateCategory();
        dc.setId(Integer.parseInt(request.getParameter("id")));
        dc.setName(request.getParameter("name"));
        dc.setDescription(request.getParameter("description"));
        dc.setIcon(request.getParameter("icon"));
        dc.setColor(request.getParameter("color"));

        // Controller work
 		try {
 			if (request.getParameter("updateORdelete").equals("update")) {
 				dc.setId(mtc.saveCategory(dc));
 			}
 			else if (request.getParameter("updateORdelete").equals("delete")) {
 				mtc.deleteCategory(dc);
 			}
            categories = mtc.getCategories();
 		} catch (MyThoughtsException mte) {
 			MTError.error(processor, response, cfg, mte);
 			return;
 		}

        if (session.getIsAdmin()) {
        	templateName = "categories.ftl";
        } else {
        	MTError.error(processor, response, cfg, "You do not have the credentials to view this page.");
        	return;
        }

        root.put("user", session.getUser());
        root.put("visitor", session.getUser() == null);
        root.put("nonadmin", !session.getIsAdmin());
        root.put("categories", categories);

        processor.processTemplate(templateName, root, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		viewForm(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		create(request, response);
	}
}
