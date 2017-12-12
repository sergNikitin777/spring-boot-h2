package com.clients.model;

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
	@JoinColumn(name = "CLIENT_ID")
	private ClientDetails client;

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

	public ClientDetails getClient() {
		return client;
	}

	public void setClient(ClientDetails client) {
		this.client = client;
	}
}
