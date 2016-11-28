package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.dto.Team;

public class TeamDAO {

	private static final String JPA_UNIT_NAME = "ALTEA_TP8";
	private EntityManager entityManager;
	
	protected EntityManager getEntityManager() {
		if (entityManager == null) {
			entityManager = Persistence.createEntityManagerFactory(
					JPA_UNIT_NAME).createEntityManager();
		}
		return entityManager;
	}
	
	public List<Team> selectAll() {
		List<Team> teams = getEntityManager().createQuery(
				"select t from Team t").getResultList();
		return teams;
	}

	public Team getTeam (Long tId) 	{
		return getEntityManager().find(Team.class, tId);
	}
	
	public Team insert(Team t) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(t);
		getEntityManager().getTransaction().commit();
		return t;
	}

	public void delete(Team t) {
		getEntityManager().getTransaction().begin();
		t = getEntityManager().merge(t);//<-Important
		getEntityManager().remove(t);
		getEntityManager().getTransaction().commit();
	}

	public Team update(Team t) {
		getEntityManager().getTransaction().begin();
		t = getEntityManager().merge(t);
		getEntityManager().getTransaction().commit();
		return t;
	}
}
