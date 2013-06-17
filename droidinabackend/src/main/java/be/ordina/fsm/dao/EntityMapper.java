package be.ordina.fsm.dao;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

public interface EntityMapper<T> {

	public Entity mapToEntity(T t, Entity... entities);
	public T mapEntityToObject(Entity entity);
	
}
