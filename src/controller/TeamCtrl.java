package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.dao.CoachDAO;
import model.dao.PlayerDAO;
import model.dao.TeamDAO;
import model.dto.Coach;
import model.dto.Player;
import model.dto.Team;

@ManagedBean (name="teamCtrl")
@SessionScoped
public class TeamCtrl {

	private TeamDAO teamDAO = new TeamDAO();
	private CoachDAO coachDAO = new CoachDAO();
	private PlayerDAO playerDAO = new PlayerDAO();
	private Collection<Team> teams;
	private Collection<Coach> coaches;
	private Collection<Player> players;
	private Team mNewTeam = new Team();
	private Long idTeam;
	private Team mEditedTeam;
	private int coachIdSelected;
	private Collection<Integer> playerIdsSelected = new ArrayList<Integer>();
	
	public Collection<Team> getTeams() {
		if (teams == null) {
			teams = teamDAO.selectAll();
		}
		return teams;
	} 

	public Collection<Coach> getCoaches() {
		if (coaches == null) {
			coaches = coachDAO.selectAll();
		}
		return coaches;
	}
	
	public Collection<Player> getPlayers() {
		if (players == null) {
			players = playerDAO.selectAll();
		}
		return players;
	}
	
	public String createTeam() {
		teamDAO.insert(mNewTeam);
		mNewTeam = new Team();
		teams.clear();
		teams.addAll(teamDAO.selectAll());
		return "list";
	}

	public String deleteTeam() {
		Team t = teamDAO.getTeam(idTeam);
		teamDAO.delete(t);
		teams.clear();
		teams.addAll(teamDAO.selectAll());
		return "list";
	}

	public String editTeam() {
		mEditedTeam = teamDAO.getTeam(idTeam);
		return "edit";
	}

	public String updateTeam() {
		Coach coachSelected = coachDAO.getCoach(getCoachIdSelected());
		if (coachSelected != null) {
			mEditedTeam.setCoach(coachSelected);
		}
		List<Player> players = playerDAO.getPlayers(getPlayerIdsSelected());
		for (Player player : players) {
			player.setTeam(mEditedTeam);
			playerDAO.update(player);
		}
		if (players != null) {
			mEditedTeam.setPlayers(players);
		}
		teamDAO.update(mEditedTeam);
		teams.clear();
		teams.addAll(teamDAO.selectAll());
		return "list";
	}

	public Team getmNewTeam() {
		return mNewTeam;
	}

	public void setmNewTeam(Team mNewTeam) {
		this.mNewTeam = mNewTeam;
	}

	public Long getIdTeam() {
		return idTeam;
	}

	public void setIdTeam(Long idTeam) {
		this.idTeam = idTeam;
	}

	public Team getmEditedTeam() {
		return mEditedTeam;
	}

	public void setmEditedTeam(Team mEditedTeam) {
		this.mEditedTeam = mEditedTeam;
	}

	public void setTeams(Collection<Team> teams) {
		this.teams = teams;
	}

	public int getCoachIdSelected() {
		return coachIdSelected;
	}

	public void setCoachIdSelected(int coachIdSelected) {
		this.coachIdSelected = coachIdSelected;
	}

	public Collection<Integer> getPlayerIdsSelected() {
		return playerIdsSelected;
	}

	public void setPlayerIdsSelected(Collection<Integer> playerIdsSelected) {
		this.playerIdsSelected = playerIdsSelected;
	}
}
