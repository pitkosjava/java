package pit.kos.cassandra.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import com.datastax.driver.core.Session;

import pit.kos.cassandra.adnotacje.EntityName;
import pit.kos.cassandra.interfaces.CRUDInterfaces;
import pit.kos.cassandra.utils.ContainerEntityMapping;

public class Menadzer implements CRUDInterfaces {

	/** Klasa odpowiedzialna za polaczenie **/
	private CassandraConnector cassandraConnector;
	/** Mapa powiazan obiektow  classa -mapper**/
	private Map<Class<?  extends Object>,Mapper> mapper;

	public Menadzer(){
		this.cassandraConnector= new CassandraConnector();
		this.initManadzer();
	}
	
	public Session getSession(){
		return cassandraConnector.getSession();
	}
	
	/*** Metoda skanuje folder w poszkiwaniu encji i tworzy mapper**/
	private void initManadzer() {
		mapper = new HashMap<>();
		Reflections reflections = new Reflections(CassandraConnector.PACKAGE_ENTITY);
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(EntityName.class);
		for (Class<?> cls : annotated) {
			mapper.put(cls, new Mapper(cls));
		}
	}
	
	
	public void open() {
		cassandraConnector.openALL(mapper);
	}

	public void close() {
		cassandraConnector.closeALL();
	}
	/** utrwalanie stanu encji w bazie**/
	public void persist(Object entity) {
		try {
			if (entity.getClass().isAnnotationPresent(EntityName.class)) {
						ContainerEntityMapping em = mapper.get(entity.getClass()).getContainerMappingClass();
						em.setSession(getSession());
						em.persistObject(entity);
			}
			return;
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public void remove(Object entity){
		try {
			if (entity.getClass().isAnnotationPresent(EntityName.class)) {
						ContainerEntityMapping em = mapper.get(entity.getClass()).getContainerMappingClass();
						em.setSession(getSession());
						em.removeObject(entity);
			}
			return;
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	// pobranie z bazy danych obiektu i aktualizacja
	public void refresh(Object entity) { 
		try {
			if (entity.getClass().isAnnotationPresent(EntityName.class)) {
						ContainerEntityMapping em = mapper.get(entity.getClass()).getContainerMappingClass();
						em.setSession(getSession());
						em.refreshObject(entity);
			}
			return;
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public Object find(Class<? extends Object> entityClass, Object primaryKey) {
		try {
			ContainerEntityMapping em= mapper.get(entityClass).
					getContainerMappingClass();
			em.setSession(getSession());
			return em.findObject(entityClass,primaryKey);
		} catch (Exception e) {
			System.err.println(e);
		}
		return null;
	}
	
	
	
	public ContainerEntityMapping getCoontainer(Object entity){
		try {
			if (entity.getClass().isAnnotationPresent(EntityName.class)) {
						ContainerEntityMapping em = mapper.get(entity.getClass()).getContainerMappingClass();
					   return em;
			}
			
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
		return null;
	}

	@Override
	public Object find(String entityName, Object primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}



}
