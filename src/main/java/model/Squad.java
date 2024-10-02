package model;

public class Squad {

	private long id;
	private String name;
	
	
	// constructor with id to fetch sqauds from the DataBase
	public Squad(long id, String name) {
		this.id = id;
		this.name = name;
	}
	// constructor without id   
	public Squad(String name) {
		this.name = name;
	}
	
	
	// Getters & Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
