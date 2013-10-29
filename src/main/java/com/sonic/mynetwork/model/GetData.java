package com.sonic.mynetwork.model;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

public class GetData extends NetworkData{
	
	private OAuthService service;
	private Token accessToken;
	private OAuthRequest request;
	private static final String PROTECTED_RESOURCE_URL = "http://api.linkedin.com/v1/people/~/connections:("
	  		+ "id,first-name,last-name,headline,industry)";
	private Logger logger = Logger.getLogger(this.getClass());
	
	public GetData(NetworkDataMgr networkDataManager,OAuthService service,
			Token accessToken){
		this.networkDataManager = networkDataManager;
		setService(service);
		setAccessToken(accessToken);
	}
	
	public void execute() throws IOException{
		OAuthRequest request = new OAuthRequest(Verb.GET,PROTECTED_RESOURCE_URL);
	    service.signRequest(accessToken, request);
	    Response response = request.send();
		logger.info("Storing data.");
		networkDataManager.getData(this, response.getBody());	
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
	
}
