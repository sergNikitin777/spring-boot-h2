package com.clients.dao.impl;

import com.clients.dao.PhoneDao;
import com.clients.model.Phone;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class PhoneDaoImpl implements PhoneDao {

	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public boolean phoneExists(String phoneNumber) {
		String sql = "select phone.id,phone.client_id,phone.PHONE_NUMBER FROM Phones as phone WHERE phone.PHONE_NUMBER=?";
		Query query = entityManager.createNativeQuery(sql, Phone.class);
		query.setParameter(1, phoneNumber);
		int count = query.getResultList().size();
		return count > 0 ? true : false;
	}

	@Override
	@Transactional
	public void addPhone(Phone phone) {
		entityManager.persist(phone);
		return;
	}
}
