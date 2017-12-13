package com.clients.dao.impl;

import com.clients.dao.PhoneDao;
import com.clients.model.Client;
import com.clients.model.Phone;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PhoneDaoImpl implements PhoneDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean phoneExists(String phoneNumber) {
        String sql = "SELECT phone.id,phone.client_id,phone.PHONE_NUMBER FROM Phones AS phone WHERE phone.PHONE_NUMBER=?";
        Query query = entityManager.createNativeQuery(sql, Phone.class);
        query.setParameter(1, phoneNumber);
        int count = query.getResultList().size();
        return count > 0 ? true : false;
    }

    @Override
    @Transactional
    public void addPhone(Integer clientId, Phone phone) {
        Client client = entityManager.find(Client.class, clientId);
        List<Phone> phones = client.getPhones();
        phone.setClient(client);
        phones.add(phone);
        client.setPhones(phones);
        entityManager.merge(client);
        return;
    }


    @Override
    public boolean phoneExistsById(Integer id) {
        String sql = "SELECT phone.id,phone.client_id,phone.PHONE_NUMBER FROM Phones AS phone WHERE phone.id=?";
        Query query = entityManager.createNativeQuery(sql, Phone.class);
        query.setParameter(1, id);
        int count = query.getResultList().size();
        return count > 0 ? true : false;
    }

    @Override
    @Transactional
    public void editPhone(Phone phone) {
        Phone foundPhone = entityManager.find(Phone.class, phone.getId());
        if (foundPhone != null) {
            foundPhone.setPhoneNumber(phone.getPhoneNumber());
            entityManager.persist(foundPhone);
        }
        return;
    }
}
