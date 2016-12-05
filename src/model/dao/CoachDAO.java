package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.dto.Coach;

public class CoachDAO {

	private static final String JPA_UNIT_NAME = "ALTEA_TP8";
	private EntityManager entityManager;
	
	protected EntityManager getEntityManager() {
		if (entityManager == null) {
			entityManager = Persistence.createEntityManagerFactory(
					JPA_UNIT_NAME).createEntityManager();
		}
		return entityManager;
	}
	
	public List<Coach> selectAll() {
		List<Coach> coaches = getEntityManager().createQuery(
				"select c from Coach c").getResultList();
		return coaches;
	}
}
