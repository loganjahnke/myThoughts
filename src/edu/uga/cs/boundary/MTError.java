// Gnu Emacs C++ mode:  -*- Java -*-
//
// Class:       MTError
//
// Type:        Servlet utility class
//
// K.J. Kochut - Logan Jahnke
//

package edu.uga.cs.boundary;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;


public class MTError {
    static  String   errorTemplateName = "error.ftl";

    public static void error(TemplateProcessor processor, HttpServletResponse response, Configuration cfg, Exception e) throws ServletException {
        error(processor, response, cfg, e.toString());
    }

    public static void error(TemplateProcessor processor, HttpServletResponse response, Configuration cfg, String msg) throws ServletException {
        DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
        String back = "index.html";

        if (msg.equals("java.lang.NullPointerException")) msg = "Mega-weird internal error. This shouldn't happen.";
        root.put( "reason", msg );
        if (msg.equals("Session expired or illegal; please log in")) back = "Logout";
        root.put( "window", back );

        processor.processTemplate(errorTemplateName, root, response);

        return;
    }
}
