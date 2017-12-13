/**
 *
 */
package com.clients.service.impl;

import com.clients.dao.ClientDao;
import com.clients.dao.PhoneDao;
import com.clients.model.Client;
import com.clients.model.Phone;
import com.clients.service.ClientService;
import javafx.util.Pair;
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
    public List<Client> getClients(Integer id) {
        return clientDao.getClients(id);

    }

    @Override
    public Client getClient(int clientId) {
        return clientDao.getClient(clientId);

    }

    @Override
    public synchronized javafx.util.Pair<Boolean,String> addClient(Client client){
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

    @Override
    public Pair<Boolean, String> editPhone(Phone phone) {
        if (phoneDao.phoneExistsById(phone.getId())){
            phoneDao.editPhone(phone);
            return new javafx.util.Pair<Boolean, String>(true, "success");
        }else{
            return new javafx.util.Pair<Boolean, String>(false, "phone not found");
        }
    }
}
