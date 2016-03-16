package pit.kos.cassandra.interfaces;

import com.datastax.driver.core.Session;

public interface MetodsTableDataBase {

	//
	void createTable(Session session);

	// DROP TABLE <tablename>
	void deleteTable(Session session);

	// ALTER TABLE emp DROP emp_email;
	// ALTER TABLE emp ADD emp_email1 text;
	void updateTable(Session session);

	void validateTable(Session session);

	// TRUNCATE <tablename>
	void truncateTable(Session session);

	/**
	 * To implements CREATE INDEX name ON emp1 (emp_name); drop index name;
	 * 
	 * 
	 * Batch processing
	 * 
	 * BEGIN BATCH <insert-stmt>/ <update-stmt>/ <delete-stmt> APPLY BATCH BEGIN
	 * 
	 * BATCH ... INSERT INTO emp (emp_id, emp_city, emp_name, emp_phone,
	 * emp_sal) values( 4,'Pune','rajeev',9848022331, 30000); ... UPDATE emp SET
	 * emp_sal = 50000 WHERE emp_id =3; ... DELETE emp_city FROM emp WHERE
	 * emp_id = 2; ... APPLY BATCH;
	 * */

}
