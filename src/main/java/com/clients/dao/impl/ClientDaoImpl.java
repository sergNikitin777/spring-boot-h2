package com.clients.dao.impl;

import com.clients.dao.ClientDao;
import com.clients.model.ClientDetails;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
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
	public List<ClientDetails> getClientsDetails(Integer id) {
		if (id==null) {
//			Criteria criteria = entityManager.unwrap(Session.class).createCriteria(ClientDetails.class);
//			criteria.setProjection(Projections.projectionList()
//					.add(Projections.property("id"),"id")
//					.add(Projections.property("firstName"),"firstName"));
//			return criteria.list();

			String query = "SELECT NEW ClientDetails(i.id, i.firstName) FROM ClientDetails i ";
			TypedQuery<ClientDetails> typedQuery = entityManager.createQuery(query , ClientDetails.class);
			List<ClientDetails> results = typedQuery.getResultList();
			return results;
		}else{
			List<ClientDetails> list = new ArrayList<ClientDetails>();
			ClientDetails clientDetails = entityManager.find(ClientDetails.class,id);
			list.add(clientDetails);
			return list;
		}
	}

	@Override
	public ClientDetails getClientDetails(int clientId) {
		return entityManager.find(ClientDetails.class, clientId);
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
