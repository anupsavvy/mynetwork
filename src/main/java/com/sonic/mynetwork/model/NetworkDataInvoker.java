package com.sonic.mynetwork.model;

import java.io.IOException;
import java.util.HashMap;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

public class NetworkDataInvoker {

	private HashMap<String, NetworkData> commandMap = 
			new HashMap<String,NetworkData>();
	private NetworkDataMgr networkDataMgr = null;
	public NetworkDataInvoker(NetworkDataMgr networkDataMgr,OAuthService service,
			Token accessToken){
	    this.networkDataMgr = networkDataMgr;
		commandMap.put("getData", new GetData(networkDataMgr, service,
				accessToken));
		commandMap.put("convertData", new ConvertData(networkDataMgr, service,
				accessToken));
	}
	
	public void invoke(String command) throws IOException{
		commandMap.get(command).execute();
	}
	
	public NetworkDataMgr getNetworkDataMgr(){
		return this.networkDataMgr;
	}
}
