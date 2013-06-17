package be.ordina.fsm.service;

import java.util.List;

import be.ordina.fsm.domain.Agent;

public interface AgentService {
	public List<Agent> readAllAgents();
	public Agent readAgent(String stringKey);
	public Agent createAgent(Agent agent);
	public Agent findAgentByUserName(String username);
	public void updateAgent(Agent agent);
}
