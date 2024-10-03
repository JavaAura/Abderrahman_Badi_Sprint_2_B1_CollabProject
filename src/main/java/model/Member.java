package model;

import model.enums.Role;

public class Member {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private Role role;
	private Long squadId;
	 


	public Member() {

	}

	public Member(Long id, String firstName, String lastName, String email, Role role, Long squadId) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.squadId = squadId;
	}
	
 
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Long getSquadId() {
		return squadId;
	}

	public void setSquadId(Long squadId) {
		this.squadId = squadId;
	}

}
