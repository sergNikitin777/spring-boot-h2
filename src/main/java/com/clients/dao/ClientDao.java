package com.clients.dao;

import com.clients.model.ClientDetails;

import java.util.List;

public interface ClientDao {
	
	List<ClientDetails> getClientsDetails();

	ClientDetails getClientDetails();

	boolean clientExists(String firstName);

	void addClient(ClientDetails client);
}
