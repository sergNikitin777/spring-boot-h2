/**
 * 
 */
package com.clients.service;

import com.clients.model.Client;
import com.clients.model.Phone;
import javafx.util.Pair;

import java.util.List;

public interface ClientService {

	List<Client> getClients(Integer id);

    Client getClient(int clientId);

    javafx.util.Pair<Boolean,String> addClient(Client client);

    javafx.util.Pair<Boolean, String> addPhone(Phone phone);

    Pair<Boolean,String> editPhone(Phone phone);
}
