package com.sonic.mynetwork.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

public class AuthorizationURLServlet extends HttpServlet 
{
	private Logger logger = Logger.getLogger(this.getClass());
	OAuthService service = null;

	public void init(ServletConfig config) throws ServletException {
		ServletContext context = config.getServletContext();
		String apiKey = context.getInitParameter("apiKey");
		String apiSecret = context.getInitParameter("apiSecret");
		service = new ServiceBuilder()
		.provider(LinkedInApi.class)
		.apiKey(apiKey)
		.apiSecret(apiSecret)
		.build();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		logger.debug("doGet()");
		Token token = service.getRequestToken();
		String url = service.getAuthorizationUrl(token);
		HttpSession session = req.getSession();
		session.setAttribute("service", service);
		session.setAttribute("token", token);
		resp.setContentType("text/xml");
		resp.setHeader("Cache-Control", "no-cache");
		resp.getWriter().write("<url>"+ url +"</url>");
		return;
	}
}

