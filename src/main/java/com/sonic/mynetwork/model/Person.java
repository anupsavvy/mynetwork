package com.sonic.mynetwork.model;

import java.util.ArrayList;

public class Person {
	private String id;
	private String first_name;
	private String last_name;
	private String headline;
	private String industry;
	private ArrayList<String> mutualConnections;
	
	public ArrayList<String> getMutualConnections() {
		return mutualConnections;
	}
	public void setMutualConnections(ArrayList<String> mutualConnections) {
		this.mutualConnections = mutualConnections;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
}
