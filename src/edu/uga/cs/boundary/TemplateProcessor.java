package edu.uga.cs.boundary;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TemplateProcessor {

	private String templateDir;
	private String templateName;
	private Configuration configuration;

	public TemplateProcessor(String templateDir, Configuration cfg) {
		this.templateDir = templateDir;
		this.configuration = cfg;
	}

	public void processTemplate(String templateName, SimpleHash root, HttpServletResponse response) throws ServletException, IOException {
		this.templateName = templateName;
		Template template = null;
		try {
			template = configuration.getTemplate(templateName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Writer out = response.getWriter();
			response.setContentType("text/html");
			template.process(root, out);
		} catch(TemplateException e) {
			MTError.error(this, response, configuration, e);
			e.printStackTrace();
		} catch(IOException e) {
			MTError.error(this, response, configuration, e);
			e.printStackTrace();
		}
	}


	public String getTemplateDir() {
		return templateDir;
	}

	public void setTemplateDir(String templateDir) {
		this.templateDir = templateDir;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration config) {
		this.configuration = config;
	}
}
