package com.clients.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "PHONES")
public class Phone {

	@Id
	@Column
	@GeneratedValue(strategy = IDENTITY)
	private int id;


	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "CLIENT_ID")
	private Client client;

	@Column
	private String phoneNumber;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
