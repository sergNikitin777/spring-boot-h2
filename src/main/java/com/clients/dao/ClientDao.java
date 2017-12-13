package com.clients.dao;

import com.clients.model.Client;

import java.util.List;

public interface ClientDao {
	
	List<Client> getClients(Integer id);

    Client getClient(int clientId);

    boolean clientExists(String firstName);

	void addClient(Client client);
}
