package edu.uga.cs.boundary;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
<<<<<<< Updated upstream
import java.util.concurrent.ThreadLocalRandom;
=======
import java.util.List;
>>>>>>> Stashed changes

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;

import edu.uga.cs.MyThoughtsException;
import edu.uga.cs.logic.*;
import edu.uga.cs.object.DebateCategory;
import edu.uga.cs.object.User;
import edu.uga.cs.object.UserID;
import edu.uga.cs.session.Session;
import edu.uga.cs.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateExceptionHandler;
import edu.uga.cs.persistence.*;

@WebServlet("/InsertPostServlet")
public class InsertPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String category = null;
	private String templateDir = "/WEB-INF/templates";
	private TemplateProcessor processor;
	private ResultSet rs, rs1, rs2;
	private Configuration cfg;
	private Connection con = DbAccessInterface.connect();
	UserID user, cat, topic;
	
	public InsertPostServlet() {
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
        ArrayList<DebateCategory> categories = new ArrayList<DebateCategory>();

        // Get Session
        try {
            httpSession = request.getSession();
            session = SessionManager.prepareSession(httpSession, ssid, session);
        } catch (MyThoughtsException mte) {
            MTError.error(processor, response, cfg, mte);
            return;
        }

        // Get categories
		try {
            categories = mtc.getCategories();
            while (categories.size() > 7)
        		categories.remove(ThreadLocalRandom.current().nextInt(0, categories.size()));
		} catch (MyThoughtsException mte) {
			MTError.error(processor, response, cfg, mte);
			return;
		}

        templateName = "categories.ftl";
        
        String dTitle = request.getParameter("debateTitle");
        String dDescription = request.getParameter("debateDescription");
<<<<<<< Updated upstream

=======
        
        String userIdQuery = "select id from person where username = '"+ session.getUser().getUsername()+"';";
        String catIdQuery = "select id from debate_category where name = '"+ category+"';";
        
        rs = DbAccessInterface.retrieve(con, userIdQuery);
        rs1 = DbAccessInterface.retrieve(con, catIdQuery);
      
        String userID = "";
        String catID = null;
        
		try {
			while(rs.next()) {
				
				user = new UserID(rs.getString("id"));
				userID = user.getId();
			}//while
			
			while(rs1.next()) {
						
				cat = new UserID(rs1.getString("id"));
				catID = cat.getId();
			}//while
			
			} 
		catch (SQLException e) {
			e.printStackTrace();
		}//try
        
		String addTopic ="INSERT INTO debate_topic(title, description, created, user_id) Values('"+dTitle+"','" +dDescription+"','"+ session.getUser().getCreatedDate()+"','"+userID+"');";
		
		int t = DbAccessInterface.insert(con, addTopic);
		String topicID = null;
		String topicIDQuery ="Select id from debate_topic where title ='"+dTitle+"';";
		 rs2 = DbAccessInterface.retrieve(con, topicIDQuery);
			
			try {
				while(rs2.next()) {
					
					topic = new UserID(rs2.getString("id"));
					topicID = topic.getId();
				}//while
				} 
			catch (SQLException e) {
				e.printStackTrace();
			}//try
		
		String addCatID ="INSERT INTO topic_category(category_id,topic_id) Values('" +catID+ "','"+ topicID+"');";
		
		t = DbAccessInterface.insert(con, addCatID);
		
>>>>>>> Stashed changes
        root.put("debateTitle", dTitle);
        root.put("debateDescription", dDescription);
        root.put("user", session.getUser());
        root.put("visitor", session.getUser() == null);
        root.put("nonadmin", !session.getIsAdmin());
        root.put("categories", categories);
       // System.out.println("Debate: " + dTitle +" " +dDescription);
       // System.out.println(session.getUser().getUsername());
        processor.processTemplate(templateName, root, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		display(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Submit = request.getParameter("submit");
		if (Submit!=null){
			category = request.getParameter("category");
			//System.out.println("Topics: " +category);
		}
		display(request, response);
		
	}
}

