package pit.kos.cassandra.utils;

import java.util.ArrayList;
import java.util.List;

import pit.kos.cassandra.dialect.Cassandra3Comand;

/** Klasa w ktorej przechowyane jest juz string wygenerowany poprednio do operacji CRUD**/
public class SpeedQuery {
	private String INSERT_INTO;
	/** nazwy parametrow do bazy danych**/
	private List<String> PARAM_INSERT;

	private String prepareStatment;
	private boolean setInserStatment;
	
	public SpeedQuery(String databasename){
		PARAM_INSERT= new ArrayList<String>();
		StringBuilder sb = new StringBuilder()
		.append(Cassandra3Comand.INSERT_INTO)
		.append(Cassandra3Comand.SPACE).append(databasename).append(Cassandra3Comand.SPACE);
		setINSERT_INTO(sb.toString());
		sb.setLength(0);
		
	}
	
	public String prepareStatmentInsert(){
		if(!setInserStatment){
			StringBuilder 	sb= new StringBuilder().append(INSERT_INTO).append(Cassandra3Comand.OPEN_TABLE);
			StringBuilder 	sbparam= new StringBuilder().append(Cassandra3Comand.VALUES).append(Cassandra3Comand.SPACE).append(Cassandra3Comand.OPEN_TABLE);
			for(String str:PARAM_INSERT){
				sb.append(str).append(Cassandra3Comand.COMMA); //(imie,nazwisko)
				sbparam.append(Cassandra3Comand.PARAM_STATMENT); //(?,?,)
			}
			sbparam.setLength(sbparam.length()-1);
			sbparam.append(Cassandra3Comand.CLOSE_TABLE_END);
			sb.setLength(sb.length()-1);
			sb.append(Cassandra3Comand.CLOSE_TABLE).append(Cassandra3Comand.SPACE).append(sbparam);
			prepareStatment=sb.toString();
			cleanInsertVariable();
			return prepareStatment;
		}
		else {
			return prepareStatment;
		}
	}
	
	


	public void add(ContainerFieldMaping field){
	     PARAM_INSERT.add(field.getValue());
	}
	private String getLUpperCase(String field_name) {
		return Character.toString(field_name.charAt(0)).toUpperCase();
	}
	/** Wyczyszczenie pamieci**/
	private void cleanInsertVariable(){
		INSERT_INTO=null;
		PARAM_INSERT=null;
		setInserStatment=true;
	
	}

	public String getINSERT_INTO() {
		return INSERT_INTO;
	}


	public void setINSERT_INTO(String iNSERT_INTO) {
		INSERT_INTO = iNSERT_INTO;
	}

	

}
