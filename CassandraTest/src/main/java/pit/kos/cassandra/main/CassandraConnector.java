package pit.kos.cassandra.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import pit.kos.cassandra.dialect.Cassandra3Comand;
import pit.kos.cassandra.utils.DataBasesStatusOptions;
import pit.kos.cassandra.utils.EnumMetodsDataBase;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class CassandraConnector {
	
	private static final int PORT_DEFAULT = 9042;
	private static final String HOST_DEFAULT = "localhost";
	private static EnumMetodsDataBase enumMetodsDataBase = EnumMetodsDataBase.NO_CHANGE;
	public static  String PACKAGE_ENTITY;
	public static String KEYSPACE;
	private static String HOST;
	private static int PORT;
	
	
	
	
	private Cluster cluster;
	private Session session;
	
	public CassandraConnector() {
		try {
			loadProperties();
			this.cluster = Cluster.builder().addContactPoint(HOST).withPort(PORT).build();
		} catch (Exception e) {
			this.cluster = Cluster.builder().addContactPoint(HOST_DEFAULT).withPort(PORT_DEFAULT).build();
			System.err.println(e.getMessage());
		}
	}
	
	public CassandraConnector(final String host,final  int port) {
		try {
			loadProperties();
			this.cluster = Cluster.builder().addContactPoint(host).withPort(port).build();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public Session getSession() {
		return session;
	}
	
	public void openALL(Map<Class<?>,Mapper> mapper) {
		session = cluster.connect();
		firstComandToDataBase(mapper);
	}

	public void closeALL() {
		lastComandtoDataBase();
		closeSessionr();
		closeCluster();
	}
	
	private void closeCluster() {
		cluster.close();
	}
	private void closeSessionr() {
		session.close();
	}
	
	/** Pierwsze komendy po podlaczeniu sie do bazy danych **/
	private void firstComandToDataBase(Map<Class<?  extends Object>,Mapper>  mapper) {
		switch (enumMetodsDataBase) {
		case CREATE:
			create(mapper);
			break;
		case DELETE:
			delete(KEYSPACE);
			break;
		case CREATE_DELETE:
			create(mapper);
			break;
		case NO_CHANGE:
			noChange(KEYSPACE);
			break;
		default:
			break;
		}
	}

	private void lastComandtoDataBase() {
		switch (enumMetodsDataBase) {
		case CREATE_DELETE:
			delete(KEYSPACE);
			break;
		default:
			break;
		}
	}
	private void noChange(String keySpace){
		session.close();
		session = cluster.connect(KEYSPACE);
	}
 
	private void delete(String keySpace) {
		session.execute(new DataBasesStatusOptions().deleteDataBase(keySpace));
	}

	private void create(Map<Class<?  extends Object>,Mapper>  mapper) {
		session.execute(new DataBasesStatusOptions().createDataBase(KEYSPACE));
		session.close();
		session = cluster.connect(KEYSPACE);
		for (Map.Entry<Class<?>, Mapper> entry : mapper.entrySet()){
			 entry.getValue().createTable(session);
		}
	}
	
	private void loadProperties() throws IOException {
		InputStream is = ClassLoader.getSystemResourceAsStream("menadzer.properties");
		Properties props = new Properties();
		props.load(is);
		is.close();
		PACKAGE_ENTITY = props.getProperty("PACKAGE_ENTITY");
		PORT = Integer.parseInt(props.getProperty("PORT_DEFAULT"));
		HOST = props.getProperty("HOST_DEFAULT");
		KEYSPACE = props.getProperty("KEYSPACE");
		String ACTION_KEYSPACES = props.getProperty("ACTION_KEYSPACES");
		if(Cassandra3Comand.CREATE.equals(ACTION_KEYSPACES)){
			enumMetodsDataBase = EnumMetodsDataBase.CREATE;
		}
		else if(Cassandra3Comand.DELETE.equals(ACTION_KEYSPACES)){
			enumMetodsDataBase = EnumMetodsDataBase.DELETE;
		}
			
		else if(Cassandra3Comand.NO_CHANGE.equals(ACTION_KEYSPACES)){
			enumMetodsDataBase = EnumMetodsDataBase.NO_CHANGE;
		}
		else if(Cassandra3Comand.CREATE_DELETE.equals(ACTION_KEYSPACES)){
			enumMetodsDataBase = EnumMetodsDataBase.CREATE_DELETE;
		}
		else{
			enumMetodsDataBase = EnumMetodsDataBase.CREATE;
		}
	}
}
