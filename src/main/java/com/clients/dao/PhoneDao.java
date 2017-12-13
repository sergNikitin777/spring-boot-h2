package com.clients.dao;

import com.clients.model.Phone;

public interface PhoneDao {
	
    boolean phoneExists(String phoneNumber);

	void addPhone(Integer clientId, Phone phone);

    boolean phoneExistsById(Integer id);

    void editPhone(Phone phone);
}
