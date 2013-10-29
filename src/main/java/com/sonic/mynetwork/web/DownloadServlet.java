package com.sonic.mynetwork.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.sonic.mynetwork.model.NetworkDataInvoker;
import com.sonic.mynetwork.model.NetworkDataMgr;
import com.sonic.mynetwork.model.Person;

@SuppressWarnings("serial")
public class DownloadServlet extends HttpServlet 
{
	private Logger logger = Logger.getLogger(this.getClass());

	public void init(ServletConfig config) throws ServletException {
		//TODO
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		logger.debug("doGet()");
		HttpSession session = req.getSession(true);
		NetworkDataInvoker invoker = (NetworkDataInvoker)session.getAttribute("invoker");
		NetworkDataMgr networkMgr = invoker.getNetworkDataMgr();
		resp.setContentType("text/csv;charset=Cp1252");
		resp.setHeader("Content-disposition", "attachment; filename=network.csv");
		PrintWriter w = resp.getWriter();
		w.write(generateCSV(networkMgr.getConnections()));
		w.flush();
		w.close();
	}

	private String generateCSV(ArrayList<Person> connections){
		String csv = new String("");
		Iterator<Person> itr = connections.iterator();
		while(itr.hasNext()){
			Person person = itr.next();
			Iterator<String> itrConns = person.getMutualConnections().iterator();
			while(itrConns.hasNext()){
				csv += person.getFirst_name()+ " "+ person.getLast_name() +", "+
						itrConns.next() + "\n";
			}
		}
		return csv;
	}

}
