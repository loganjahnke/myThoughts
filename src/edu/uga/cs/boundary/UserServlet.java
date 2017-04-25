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
import edu.uga.cs.logic.MyThoughtsController;
import edu.uga.cs.logic.TopicListController;
import edu.uga.cs.object.User;
import edu.uga.cs.persistence.DbAccessInterface;
import edu.uga.cs.persistence.PersonManager;
import edu.uga.cs.session.Session;
import edu.uga.cs.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateExceptionHandler;

/**
 * Class to deal with user options: viewing their personal topics and changing their password
 * @author Lucy Bradley
 *
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String templateDir = "/WEB-INF/templates";
	private TemplateProcessor processor;
	private Session session;

	private Configuration cfg;

	 public UserServlet() {
	    	super();
	 }	
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		cfg = new Configuration(Configuration.VERSION_2_3_25);
		cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
		processor = new TemplateProcessor(templateDir, cfg);
	}
	
	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		 HttpSession httpSession = null;
	     String ssid = null;
	     session = null;
		// Get Session
        try {
            httpSession = request.getSession();
            session = SessionManager.prepareSession(httpSession, ssid, session);
        } catch (MyThoughtsException mte) {
            MTError.error(processor, response, cfg, mte);
            return;
        }
        
        MyThoughtsController mtc = new MyThoughtsController();
		
        try{
        	User user = new User();
        	user = mtc.getUser(request.getParameter("username"));
			if(request.getParameter("newPassword") != null) changePassword(request, response);
			else if(request.getParameter("createdTopics") != null) showTopics(false, response, user);
			else if(request.getParameter("commentedTopics") != null) showTopics(true,response, user);
        } catch(MyThoughtsException mte){
        	MTError.error(processor, response, cfg, mte);
			return;
        }
	}
	
	public void showTopics(boolean byComment, HttpServletResponse response, User user) throws MyThoughtsException, ServletException, IOException{
		TemplateProcessor tp = new TemplateProcessor(templateDir, cfg);
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		String templateName= "view-topics.ftl";
		
		root.put("sender", "user");
		root.put("user", session.getUser());
        root.put("visitor", session.getUser() == null);
        root.put("viewing", session.getUser());
        root.put("mypage", true);
        root.put("nonadmin", !session.getIsAdmin());
	
		TopicListController tlc = new TopicListController();
		
		if(byComment){
			root.put("topics", tlc.getCommentedTopics(user));
			tp.processTemplate(templateName, root, response);
		}else{
			root.put("topics", tlc.getTopics(user));
			tp.processTemplate("view-topics.ftl", root, response);
		}
	}
	
	public void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//called when user enters new password in popup window
    
       	TemplateProcessor tp = new TemplateProcessor(templateDir, cfg);
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		String templateName= "user.ftl";
        
        PersonManager pm = new PersonManager(DbAccessInterface.connect());
		
        try{
			if(!pm.confirmChangePswd(session.getUser().getUsername(), request.getParameter("oldPassword"),
					request.getParameter("newPassword"))){
				root.put("wrongPassword", true);
			} else root.put("wrongPassword", false);
        } catch (MyThoughtsException mte) {
            MTError.error(processor, response, cfg, mte);
            return;
        }
		
		root.put("sender", "user");
		root.put("user", session.getUser());
		root.put("viewing", session.getUser());
        root.put("visitor", session.getUser() == null);
        root.put("mypage", true);
        root.put("nonadmin", !session.getIsAdmin());
			
		tp.processTemplate(templateName, root, response);
	}
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
