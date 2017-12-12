/**
 *
 */
package com.clients.service.impl;

import com.clients.dao.ClientDao;
import com.clients.dao.PhoneDao;
import com.clients.model.ClientDetails;
import com.clients.model.Phone;
import com.clients.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CllientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private PhoneDao phoneDao;

    @Override
    public List<ClientDetails> getClientsDetails() {
        return clientDao.getClientsDetails();

    }

    @Override
    public ClientDetails getClientDetails(int clientId) {
        return clientDao.getClientDetails(clientId);

    }

    @Override
    public synchronized javafx.util.Pair<Boolean,String> addClient(ClientDetails client){
       if (clientDao.clientExists(client.getFirstName())){
           return new javafx.util.Pair<Boolean, String>(false, "client alredy exists");
       }
        try {
            clientDao.addClient(client);
        } catch (Exception e) {
            return new javafx.util.Pair<Boolean, String>(false, e.getLocalizedMessage());
        }
        return new javafx.util.Pair<Boolean, String>(true, "success");
    }

    @Override
    public synchronized javafx.util.Pair<Boolean, String> addPhone(Phone phone) {
        if (phoneDao.phoneExists(phone.getPhoneNumber())) {
            return new javafx.util.Pair<Boolean, String>(false, "phone alredy exists");
        } else {
            try {
                phoneDao.addPhone(phone);
            } catch (Exception e) {
                return new javafx.util.Pair<Boolean, String>(false, e.getLocalizedMessage());
            }
            return new javafx.util.Pair<Boolean, String>(true, "success");
        }
    }
}
