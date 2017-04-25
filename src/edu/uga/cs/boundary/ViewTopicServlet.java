package edu.uga.cs.boundary;

import java.io.IOException;
import java.util.ArrayList;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs.MyThoughtsException;
import edu.uga.cs.logic.*;
import edu.uga.cs.object.*;
import edu.uga.cs.session.*;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateExceptionHandler;

/**
 * Servlet implementation class ViewTopicServlet
 */
@WebServlet("/topic")
public class ViewTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String templateDir = "/WEB-INF/templates";
	private TemplateProcessor processor;
	
	private Configuration cfg;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewTopicServlet() {
        super();
        // TODO Auto-generated constructor stub
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
    	ArrayList<Comment> comments = new ArrayList<Comment>();
    	
    	//Get Session
    	try {
    		httpSession = request.getSession();
    		session = SessionManager.prepareSession(httpSession, ssid, session);
    	} catch(MyThoughtsException mte) {
    		MTError.error(processor, response, cfg, mte);
    		return;
    	}
    	
    	String topicId = request.getParameter("id");
    	NumberFormat nf = NumberFormat.getIntegerInstance();
    	int topId;
    	try {
    		topId = nf.parse(topicId).intValue();
    		try {
    			templateName = "topic.ftl";
    			DebateTopic topic = tlc.getTopic(topId);
    			comments = mtc.getCommentsForTopic(topic);
    			ArrayList<Comment> agreeComments = mtc.sortAgreeComment(comments);
    			ArrayList<Comment> disagreeComments = mtc.sortDisagreeComment(comments);
    			
    			root.put("topic", topic);
    			root.put("debateTopicId", topicId);
    			root.put("agreeComments", agreeComments);
    			root.put("disagreeComments", disagreeComments);
    			root.put("user", session.getUser());
    			root.put("visitor", session.getUser() == null);
    			root.put("category", topic.getCategories().get(0));
    			root.put("nonadmin", !session.getIsAdmin());
    			
    			processor.processTemplate(templateName, root, response);
    		} catch(MyThoughtsException mte) {
    			MTError.error(processor, response, cfg, mte);
    			return;
    		}
    	} catch(ParseException pe) {
    		pe.printStackTrace();
    		return;
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		display(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
