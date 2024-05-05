package net.bushfire.crud.model;

public class Drones {
	protected int id;
	protected String dname;
	protected String dcoordinates;
	protected String dmodel;
	
	public Drones() {
	}
	
	public Drones(String dname, String dcoordinates, String dmodel) {
		super();
		this.dname = dname;
		this.dcoordinates = dcoordinates;
		this.dmodel = dmodel;
	}

	public Drones(int id, String dname, String dcoordinates, String dmodel) {
		super();
		this.id = id;
		this.dname = dname;
		this.dcoordinates = dcoordinates;
		this.dmodel = dmodel;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDcoordinates() {
		return dcoordinates;
	}
	public void setDcoordinates(String dcoordinates) {
		this.dcoordinates = dcoordinates;
	}
	public String getDmodel() {
		return dmodel;
	}
	public void setDmodel(String dmodel) {
		this.dmodel = dmodel;
	}
}
