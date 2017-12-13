package com.clients.dao.impl;

import com.clients.dao.ClientDao;
import com.clients.model.Client;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientDaoImpl implements ClientDao {

	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<Client> getClients(Integer id) {
		if (id==null) {
			String query = "SELECT NEW Client(i.id, i.firstName) FROM Client i ";
			TypedQuery<Client> typedQuery = entityManager.createQuery(query , Client.class);
			List<Client> results = typedQuery.getResultList();
			return results;
		}else{
			List<Client> list = new ArrayList<Client>();
			Client client = entityManager.find(Client.class,id);
			list.add(client);
			return list;
		}
	}

	@Override
	public Client getClient(int clientId) {
		return entityManager.find(Client.class, clientId);
	}

	@Override
	public boolean clientExists(String firstName) {
		String sql = "select id,first_name FROM clients s WHERE first_name=?";
		Query query = entityManager.createNativeQuery(sql, Client.class);
		query.setParameter(1, firstName);
		int count = query.getResultList().size();
		return count > 0 ? true : false;
	}

	@Override
	@Transactional
	public void addClient(Client client) {
		entityManager.persist(client);
		return;
	}

}
