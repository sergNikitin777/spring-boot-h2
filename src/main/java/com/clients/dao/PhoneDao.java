package com.clients.dao;

import com.clients.model.Phone;

public interface PhoneDao {
	
    boolean phoneExists(String phoneNumber);

	void addPhone(Phone phone);

    boolean phoneExistsById(Integer id);

    void editPhone(Phone phone);
}
