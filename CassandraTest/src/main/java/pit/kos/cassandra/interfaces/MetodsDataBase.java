package pit.kos.cassandra.interfaces;

/**
 * KEYSPACE in cassandra is database name prosty interfejs odpowiada za opcje po
 * podlaczeniu sie do bazy danych
 * **/
public interface MetodsDataBase {
	
	String createDataBase(String KeySpaces);
	String deleteDataBase(String KeySpaces);
	String updateDataBase(String KeySpaces);
	String noChangeDataBase(String KeySpaces);
}
