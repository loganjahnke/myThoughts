// Gnu Emacs C++ mode:  -*- Java -*-
//
// Class:       MTError
//
// Type:        Servlet utility class
//
// K.J. Kochut - Logan Jahnke
//

package edu.uga.cs.boundary;



import java.io.BufferedWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import freemarker.template.Configuration;
import freemarker.template.Template;


public class MTError {
    static  String   errorTemplateName = "error.ftl";

    public static void error( Configuration cfg, BufferedWriter toClient, Exception e )
            throws ServletException
    {
        error( cfg, toClient, e.toString() );
    }

    public static void error( Configuration cfg, BufferedWriter toClient, String msg )
            throws ServletException
    {
        Template	    errorTemplate = null;
        Map<String, String> root = new HashMap<String, String>();
        String back = "index.html";

        // Load the error template from the WEB-INF/templates directory of the Web app
        try {
            errorTemplate = cfg.getTemplate( errorTemplateName );
        } catch( Exception e ) {
            throw new ServletException( "Can't load template: " + errorTemplateName + ": " + e.toString() );
        }

        if (msg.equals("java.lang.NullPointerException")) msg = "Mega-weird internal error. This shouldn't happen.";
        root.put( "reason", msg );
        if (msg.equals("Session expired or illegal; please log in")) back = "Logout";
        root.put( "window", back );

        try {
            errorTemplate.process( root, toClient );
            toClient.flush();
            toClient.close();
        }
        catch( Exception e ) {
            throw new ServletException( "Error while processing FreeMarker template", e);
        }

        return;
    }
}
