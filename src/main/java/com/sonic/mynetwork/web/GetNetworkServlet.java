package com.sonic.mynetwork.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.sonic.mynetwork.model.NetworkDataInvoker;
import com.sonic.mynetwork.model.NetworkDataMgr;

@SuppressWarnings("serial")
public class GetNetworkServlet extends HttpServlet 
{
	private Logger logger = Logger.getLogger(this.getClass());

	public void init(ServletConfig config) throws ServletException {
		//TODO
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		logger.debug("doPost()");
		String code = req.getParameter("code");
		Verifier verifier = new Verifier(code.trim());
		HttpSession session = req.getSession();
		OAuthService service = (OAuthService)session.getAttribute("service");
		Token token = (Token)session.getAttribute("token");
		Token accessToken = service.getAccessToken(token,
				verifier);
		startExtractionProcess(req,service,accessToken);
		resp.setContentType("text/xml");
		resp.setHeader("Cache-Control", "no-cache");
		resp.getWriter().write("<network><status>done!</status></network>");
		return;
	}

	private void startExtractionProcess(HttpServletRequest req,OAuthService service,
			Token accessToken) throws IOException{
		HttpSession session = req.getSession(true);
		NetworkDataMgr networkDataMgr = new NetworkDataMgr(session);
		NetworkDataInvoker invoker = new NetworkDataInvoker
				(networkDataMgr,service,accessToken);
		invoker.invoke("getData");
		invoker.invoke("convertData");
		session.setAttribute("invoker", invoker);
	};
}
