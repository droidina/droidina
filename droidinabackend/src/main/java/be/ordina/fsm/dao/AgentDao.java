package be.ordina.fsm.dao;

import java.util.List;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;

import be.ordina.fsm.domain.Agent;

public interface AgentDao {
	public Agent createAgent(Agent agent) throws EntityNotFoundException;
	public List<Agent> readAllAgents();
	public Agent readAgent(String stringKey) throws EntityNotFoundException;
	public void updateAgent(Agent newAgent) throws EntityNotFoundException;
	public void deleteAgent(String stringKey);
	public Agent findAgentByFirstNameLastName(String firstName, String lastName);
	public Agent findAgentByUserName(String username);
}
