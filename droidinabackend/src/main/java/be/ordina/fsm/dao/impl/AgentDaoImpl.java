package be.ordina.fsm.dao.impl;

import static com.google.appengine.api.datastore.KeyFactory.keyToString;
import static com.google.appengine.api.datastore.KeyFactory.stringToKey;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;

import be.ordina.fsm.dao.AgentDao;
import be.ordina.fsm.dao.EntityMapper;
import be.ordina.fsm.domain.Agent;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.TransactionOptions;

@Repository
public class AgentDaoImpl implements AgentDao, EntityMapper<Agent> {

	private static final DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	
	private static final Logger logger = Logger.getLogger(AgentDaoImpl.class.getName());
	
	@Override
	public Entity mapToEntity(Agent agent, Entity... entities) {
		Entity entity = null;
		if(entities!=null && entities.length>0){
			entity = entities[0];
		}else{
			entity = new Entity(Agent.KIND);
		}
		
		entity.setProperty(Agent.FIRSTNAME, agent.getFirstName());
		entity.setProperty(Agent.LASTNAME, agent.getLastName());
		entity.setProperty(Agent.USERNAME, agent.getUserName());
		entity.setProperty(Agent.PASSWORD, agent.getPassword());
		
		return entity;
	}

	@Override
	public Agent mapEntityToObject(Entity entity) {
		Agent agent = new Agent();

		agent.setFirstName((String)entity.getProperty(Agent.FIRSTNAME));
		agent.setLastName((String)entity.getProperty(Agent.LASTNAME));
		agent.setUserName((String)entity.getProperty(Agent.USERNAME));
		agent.setPassword((String)entity.getProperty(Agent.PASSWORD));
		agent.setKey(keyToString(entity.getKey()));
		
		return agent;
	}

//	Transaction tx = ds.beginTransaction(TransactionOptions.Builder.withXG(true));
//	tx.commit();
	
	@Override
	public Agent createAgent(Agent agent) throws EntityNotFoundException{
		Entity entity = mapToEntity(agent); 
		Key key = ds.put(entity);
		
		Agent createdAgent = null;
		if(key.isComplete()){
			createdAgent = mapEntityToObject(ds.get(key));
		}else{
			logger.log(Level.SEVERE, "Error finding newly saved entity");
		}
		
		return createdAgent;
	}
	
	@Override
	public List<Agent> readAllAgents() {
		List<Agent> agentsList = new ArrayList<Agent>();
		
		Query allAgentsQuery = new Query(Agent.KIND);
		PreparedQuery preparedQuery = ds.prepare(allAgentsQuery);
		
		Iterable<Entity> result = preparedQuery.asIterable();
		
		for(Entity entity : result){
			agentsList.add(mapEntityToObject(entity));
		}
		
		return agentsList;
	}

	@Override
	public Agent readAgent(String stringKey) throws EntityNotFoundException {
		Key key = stringToKey(stringKey);
		
		return mapEntityToObject(ds.get(key));
	}

	@Override
	public void updateAgent(Agent agent) throws EntityNotFoundException{
		Key agentKey = stringToKey(agent.getKey());
		Entity foundAgentEntity = ds.get(agentKey);
		
		Entity agentToUpdate = mapToEntity(agent, foundAgentEntity);
		ds.put(agentToUpdate);
	}

	@Override
	public void deleteAgent(String stringKey) {
		ds.delete(stringToKey(stringKey));
	}

	@Override
	public Agent findAgentByFirstNameLastName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Agent findAgentByUserName(String username) {
		Query agentQuery = new Query(Agent.KIND);
		agentQuery.setFilter(new Query.FilterPredicate(Agent.USERNAME, FilterOperator.EQUAL, username));
		
		PreparedQuery preparedQuery = ds.prepare(agentQuery);
		Entity agentEntity = preparedQuery.asSingleEntity();
		
		return mapEntityToObject(agentEntity);
	}

}
