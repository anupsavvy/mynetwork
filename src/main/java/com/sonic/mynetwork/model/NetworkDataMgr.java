package com.sonic.mynetwork.model;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public class NetworkDataMgr {
	
	private String data = null;
	private ArrayList<Person> connections = null;
	private HttpSession session = null;
	
	public NetworkDataMgr(HttpSession session){
		this.session = session;
	}
	
	public void getData(NetworkData networkData, String data){
		setConnData(data);
	}
	
	public void convertData(NetworkData networkData,ArrayList<Person> connections){
		setConnections(connections);
	}
	
	private void setConnData(String data){
		this.data = data;
	}
	
	public String getConnData(){
		return this.data;
	}
	
	public ArrayList<Person> getConnections() {
		return connections;
	}

	public void setConnections(ArrayList<Person> connections) {
		this.connections = connections;
	}
	
	public HttpSession getSession(){
		return this.session;
	}
}
