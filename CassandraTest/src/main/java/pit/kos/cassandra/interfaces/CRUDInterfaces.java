package pit.kos.cassandra.interfaces;

public interface CRUDInterfaces {
	void persist(Object entity);
	void refresh(Object entity);
	void remove(Object entity);
	Object find(String entityName, Object primaryKey);
	Object find(Class<? extends Object> entityClass, Object primaryKey);
	
	//String query="SELECT 8 FROM emp";
	//ResultSet result=session.execute(query);
	//System.out.println(result.all());
	//DELETE emp_sal FROM emp WHERE emp_id=3; in column is null
	//DELETE FROM emp WHERE emp_id=3; row delete

}
