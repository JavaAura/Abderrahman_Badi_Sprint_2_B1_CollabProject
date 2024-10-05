package model;

import java.util.List;

public class Squad {

	private long id;
	private String name;
	private List<Member> members;

	// Default constructor
	public Squad() {
	}

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
	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

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
