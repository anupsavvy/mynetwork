package com.sonic.mynetwork.model;

import java.io.IOException;

import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

public abstract class NetworkData {
	protected NetworkDataMgr networkDataManager;

	public abstract void execute() throws IOException;
	
	public OAuthService getService(){
		throw new UnsupportedOperationException();
	}
	
	public void setService(OAuthService service){
		throw new UnsupportedOperationException();
	}
	
	public Token getAccessToken(){
		throw new UnsupportedOperationException();
	}
	
	public void setAccessToken(Token accessToken){
		throw new UnsupportedOperationException();
	}
}
