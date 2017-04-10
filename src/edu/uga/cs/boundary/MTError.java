// Gnu Emacs C++ mode:  -*- Java -*-
//
// Class:       MTError
//
// Type:        Servlet utility class
//
// K.J. Kochut - Logan Jahnke
//

package edu.uga.cs.boundary;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import edu.uga.cs.object.User;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;


public class MTError {
    static  String   errorTemplateName = "error.ftl";

    public static void error(TemplateProcessor processor, HttpServletResponse response, Configuration cfg, Exception e) throws ServletException, IOException {
        error(processor, response, cfg, e.toString());
    }

    public static void error(TemplateProcessor processor, HttpServletResponse response, Configuration cfg, String msg) throws ServletException, IOException {
        DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
        String back = "index.html";

        if (msg.equals("java.lang.NullPointerException")) msg = "Mega-weird internal error. This shouldn't happen.";
        root.put( "reason", msg );
        if (msg.equals("Session expired or illegal; please log in")) back = "Logout";
        root.put( "window", back );

        User fake = new User();
        fake.setFirstname("We messed up.");
        fake.setKarma(90210);
        root.put("user", fake);
        root.put("nonadmin", false);
        root.put("visitor", false);

        processor.processTemplate(errorTemplateName, root, response);

        return;
    }
}
