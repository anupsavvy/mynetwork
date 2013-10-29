package com.sonic.mynetwork.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


import org.apache.log4j.Logger;
import org.apache.xerces.parsers.DOMParser;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ConvertData extends NetworkData{

	private OAuthService service;
	private Token accessToken;
	private Logger logger = Logger.getLogger(this.getClass());

	public ConvertData(NetworkDataMgr networkDataManager,OAuthService service,
			Token accessToken){
		this.networkDataManager = networkDataManager;
		setService(service);
		setAccessToken(accessToken);
	}

	public void execute(){
		DOMParser parser = new DOMParser();
		ArrayList<Person> connections = new ArrayList<Person>();
		try {
			parser.parse(new InputSource(new java.io.StringReader(networkDataManager.getConnData())));
			Document doc = parser.getDocument();	
			NodeList connectionNodes = doc.getChildNodes().item(0).getChildNodes();

			// make a list of all connections and extract all attributes from xml.

			for(int i = 0; i < connectionNodes.getLength(); i++){
				if(i % 2 != 0){
					NodeList attributes = connectionNodes.item(i).getChildNodes();
					Person person = new Person();
					if(attributes.item(1)!=null)
						person.setId(attributes.item(1).getTextContent());
					else
						person.setId(null);
					if(attributes.item(3)!=null)
						person.setFirst_name(attributes.item(3).getTextContent());
					else
						person.setFirst_name(null);
					if(attributes.item(5)!=null)
						person.setLast_name(attributes.item(5).getTextContent());
					else
						person.setLast_name(null);
					if(attributes.item(7)!=null)
						person.setHeadline(attributes.item(7).getTextContent());
					else
						person.setHeadline(null);
					if(attributes.item(9)!=null)
						person.setIndustry(attributes.item(9).getTextContent());
					else
						person.setIndustry(null);
					if(attributes.item(1)!=null && 
							!attributes.item(1).getTextContent().equalsIgnoreCase("private"))
						connections.add(person);
				}
			}

			// get mutual connections of user and his connections.
			System.out.println("Total connections : " + connections.size() + "\n\n\n\n\n\n\n");

			Iterator<Person> itr = connections.iterator();
			double count = 0.0;
			while(itr.hasNext()){
				Person person = itr.next();
				String new_url = "https://api.linkedin.com/v1/people/id=" + 
						person.getId() + ":(relation-to-viewer:(related-connections))";

				//String new_url = "https://api.linkedin.com/v1/people/id=VeWxqOutFC" + 
				//		":(relation-to-viewer:(related-connections))";
				OAuthRequest request = new OAuthRequest(Verb.GET,new_url);
				service.signRequest(accessToken, request);
				Response response = request.send();
				parser.parse(new InputSource(new java.io.StringReader(response.getBody())));
				doc = parser.getDocument();
				ArrayList<String> mutualConns = new ArrayList<String>();
				NodeList mutualNodes = doc.getChildNodes().item(0).getChildNodes().item(1).getChildNodes().
						item(1).getChildNodes();
				for(int i = 0; i < mutualNodes.getLength(); i++){
					if(i % 2 != 0){
						NodeList attributes = mutualNodes.item(i).getChildNodes();
						String first_name = null;
						String last_name = null;
						if(attributes.item(1)!=null &&
								!attributes.item(1).getTextContent().equalsIgnoreCase("private")){
							if(attributes.item(3)!=null)
								first_name = attributes.item(3).getTextContent();
							if(attributes.item(5)!=null)
								last_name = attributes.item(5).getTextContent();
							mutualConns.add(first_name + " " + last_name);
						}	
					}
				}
				person.setMutualConnections(mutualConns);
				System.out.println("Request no : " + ++count);
				updateProgress((double)(count/(double)connections.size())*100);
			}
			// System.out.println("The token is : " + getAccessToken().getToken());
		} catch (SAXException e) {
			// handle SAXException 
		} catch (IOException e) {
			// handle IOException 
		}
		networkDataManager.convertData(this,connections);
	}

	@Override
	public OAuthService getService(){
		return this.service;
	}

	@Override
	public void setService(OAuthService service){
		this.service = service;
	}

	@Override
	public Token getAccessToken(){
		return this.accessToken;
	}

	@Override
	public void setAccessToken(Token accessToken){
		this.accessToken = accessToken;
	}
	
	private void updateProgress(double progress){
		System.out.println("Progress : " + progress);
		this.networkDataManager.getSession().setAttribute("progress", progress);
	}
}
