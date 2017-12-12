package com.clients.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CLIENTS")
public class ClientDetails {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String firstName;

	@JsonIgnore
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<Phone> phones;

	public ClientDetails(){
	}

	public ClientDetails(String firstName){
		this.firstName = firstName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public List<Phone> getPhones() {
		return phones;
	}
}
