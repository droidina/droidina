package be.ordina.fsm.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import be.ordina.fsm.dao.AgentDao;
import be.ordina.fsm.domain.Agent;
import be.ordina.fsm.domain.Agent;
import be.ordina.fsm.service.AgentService;

import com.google.appengine.api.datastore.EntityNotFoundException;

@Service
public class AgentServiceImpl implements AgentService{

	private AgentDao agentDao;
	
	@Inject
	public void setAgentDao(AgentDao agentDao) {
		this.agentDao = agentDao;
	}
	
	@Override
	public List<Agent> readAllAgents() {
		return agentDao.readAllAgents();
	}

	@Override
	public Agent readAgent(String stringKey) {
		Agent foundAgent=null;
		try {
			foundAgent = agentDao.readAgent(stringKey);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return foundAgent;
	}
	
	@Override
	public Agent createAgent(Agent agent){
		Agent createdAgent = null;
		try {
			 createdAgent = agentDao.createAgent(agent);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return createdAgent;
	}

	@Override
	public Agent findAgentByUserName(String username) {
		return agentDao.findAgentByUserName(username);
	}

	@Override
	public void updateAgent(Agent agent) {
		try {
			agentDao.updateAgent(agent);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
	}	
}
