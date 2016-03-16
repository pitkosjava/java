package pit.kos.cassandra.utils;

import pit.kos.cassandra.dialect.Cassandra3Comand;
import pit.kos.cassandra.interfaces.MetodsDataBase;


public  class  DataBasesStatusOptions implements MetodsDataBase{
	
	public String createDataBase(String KeySpaces) {
		StringBuilder sb=new StringBuilder().
				append(Cassandra3Comand.CREATE_KEYSPACE).
				append(Cassandra3Comand.SPACE).
				append(KeySpaces).
				append(Cassandra3Comand.SPACE).
				append(Cassandra3Comand.WITH_REPLICATION);
		return sb.toString();
	}
	public String deleteDataBase(String KeySpaces) {
		StringBuilder sb=new StringBuilder().
				append(Cassandra3Comand.DROP).
				append(Cassandra3Comand.SPACE).
				append(KeySpaces);
		return sb.toString();
	}
	public String updateDataBase(String KeySpaces) {
		return "";
	}
	public String noChangeDataBase(String KeySpaces) {
		
		return "";
	}



}
