package com.clients.dao.impl;

import com.clients.dao.ClientDao;
import com.clients.model.ClientDetails;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ClientDaoImpl implements ClientDao {

	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<ClientDetails> getClientsDetails() {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(ClientDetails.class);
		return criteria.list();
	}

	@Override
	public ClientDetails getClientDetails() {
		return null;
	}

	@Override
	public boolean clientExists(String firstName) {
		String sql = "select id,first_name FROM clients s WHERE first_name=?";
		Query query = entityManager.createNativeQuery(sql, ClientDetails.class);
		query.setParameter(1, firstName);
		int count = query.getResultList().size();
		return count > 0 ? true : false;
	}

	@Override
	@Transactional
	public void addClient(ClientDetails client) {
		entityManager.persist(client);
		return;
	}

}
