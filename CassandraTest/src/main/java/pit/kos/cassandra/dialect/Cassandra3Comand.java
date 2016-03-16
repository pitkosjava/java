package pit.kos.cassandra.dialect;

public class Cassandra3Comand {
	/***Start  properties file option database**/
	public static final String CREATE = "CREATE";
	public static final String DELETE = "DELETE";
	public static final String NO_CHANGE = "NO_CHANGE";
	public static final String CREATE_DELETE = "CREATE_DELETE";
	/**End properties**/
	
	public static final String CREATE_KEYSPACE = "CREATE KEYSPACE";
	public static final String CREATE_IF_NO_EXIST_KEYSPACE = "CREATE KEYSPACE IF NOT EXISTS";
	public static final String DROP = "DROP KEYSPACE ";
	public static final String CREATE_TABLE = "CREATE TABLE";
	public static final String DROP_TABLE = "DROP TABLE";
	public static final String OPEN_TABLE = "(";
	public static final String CLOSE_TABLE = ")";
	public static final String CLOSE_TABLE_END = ");";
	public static final String PRIMARY_KEY = "PRIMARY KEY";
	public static final String WITH_REPLICATION = "WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 }";
	public static final String GET = "get";
	public static final String INSERT_INTO = "INSERT INTO";
	public static final String VALUES = "VALUES";
	public static final String PARAM_STATMENT = "?,";
	

	public static final char SPACE = ' ';
	public static final char COMMA = ',';
	
	
	
	
	
	
	
}
