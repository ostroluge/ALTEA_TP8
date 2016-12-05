package model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.dto.Player;

public class PlayerDAO {

	private static final String JPA_UNIT_NAME = "ALTEA_TP8";
	private EntityManager entityManager;
	
	protected EntityManager getEntityManager() {
		if (entityManager == null) {
			entityManager = Persistence.createEntityManagerFactory(
					JPA_UNIT_NAME).createEntityManager();
		}
		return entityManager;
	}
	
	public List<Player> selectAll() {
		List<Player> players = getEntityManager().createQuery(
				"select p from Player p").getResultList();
		return players;
	}

	public List<Player> getPlayers(Collection<Integer> ids) {
		List<Player> result = new ArrayList<Player>();
		for (Integer id : ids) {
			Player p = getEntityManager().find(Player.class, id);
			if (p != null) {
				result.add(p);
			}
		}
		return result;
	}
	
	public Player update(Player p) {
		getEntityManager().getTransaction().begin();
		p = getEntityManager().merge(p);
		getEntityManager().getTransaction().commit();
		return p;
	}
}
