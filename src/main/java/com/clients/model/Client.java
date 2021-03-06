package com.clients.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CLIENTS")
public class Client {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String firstName;

	@JsonManagedReference
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<Phone> phones;

	public Client(){
	}

	public Client(String firstName){
		this.firstName = firstName;
	}

	public Client(int id, String firstName){
		super();
		this.id = id;
		this.firstName = firstName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
}
