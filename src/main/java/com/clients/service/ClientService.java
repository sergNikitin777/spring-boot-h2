/**
 * 
 */
package com.clients.service;

import com.clients.model.ClientDetails;
import com.clients.model.Phone;

import java.util.List;

public interface ClientService {

	List<ClientDetails> getClientsDetails();

    ClientDetails getClientDetails();

    javafx.util.Pair<Boolean,String> addClient(ClientDetails client);

    javafx.util.Pair<Boolean, String> addPhone(Phone phone);

}
