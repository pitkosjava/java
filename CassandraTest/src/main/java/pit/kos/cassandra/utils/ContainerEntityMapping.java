package pit.kos.cassandra.utils;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.reflect.FieldUtils;

import pit.kos.cassandra.main.CassandraConnector;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.LocalDate;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.TupleValue;
import com.datastax.driver.core.querybuilder.QueryBuilder;
public class ContainerEntityMapping {

	private String classname;
	private String databasename;
	private List<ContainerFieldMaping> listField;
	/** Id osobno bo mozna zaimplementowac sposob generacji PK ***/
	/** Do wywolywania metod refleksji **/

	/** przyspieszenie budowy zapytan dla kazdje klasy**/
	private SpeedQuery speedQuery;
	private Session session;
	
	/*** Tempy zmienna przyspieszajace**/
	private PreparedStatement statement;
	private BoundStatement bound;
	private ContainerFieldMaping field;
	
	public ContainerEntityMapping(String classname, String databesname) {
		super();
		this.classname = classname;
		this.databasename = databesname;
		this.listField = new ArrayList<ContainerFieldMaping>();
		this.speedQuery= new SpeedQuery(this.databasename);
		/** Builid query**/
	}

	/** Only test **/
	public void persistObject(Object obj) throws Exception {
		     statement = session.prepare(speedQuery.prepareStatmentInsert());
			 bound = statement.bind();
			 for(int i=0;i<listField.size();i++){
				 field=listField.get(i);
				 Object value=FieldUtils.readField(obj,field.getKey(),true);
				 convertTypToDatabase(value,bound,field.getType(), i);
			}
			session.execute(bound);
	}
	
	public void removeObject(Object obj) throws Exception{
		 field=listField.get(0); // id musi byc pierwsze zawsze 
		 Object value=FieldUtils.readField(obj,field.getKey(),true);
		 Statement statement = QueryBuilder.delete()
				 .from(CassandraConnector.KEYSPACE,databasename)
				 .where(eq(field.getValue(),value));
		 session.execute(statement);
	}
	
	public void refreshObject(Object obj) throws Exception{
		// pobierany stan z bazy i uaktualniamy
		 field=listField.get(0); 
		 Object value=FieldUtils.readField(obj,field.getKey(),true);
		 Statement statement = QueryBuilder.select()
		        .all()
		        .from(CassandraConnector.KEYSPACE,databasename)
		        .where(eq(field.getValue(),value));
		 ResultSet results = session.execute(statement);
		 Row one=results.all().get(0);
		 for(int i=0;i<listField.size();i++){
			  field=listField.get(i);
				convertTypDBToJava(obj, one);	
		}
	}
	public Object findObject(Class<?> classa, Object keyID) throws Exception{
		 field=listField.get(0); 
		 Statement statement = QueryBuilder.select()
			        .all()
			        .from(CassandraConnector.KEYSPACE,databasename)
			        .where(eq(field.getValue(),keyID));
		 Constructor<?> ctor = classa.getConstructor();
		 Object obj=ctor.newInstance();
		 ResultSet results = session.execute(statement);
		 Row one=results.all().get(0);
		 for(int i=0;i<listField.size();i++){
			  field=listField.get(i);
			  convertTypDBToJava(obj, one);	
		 }
		 return obj;
	}

	private void convertTypDBToJava(Object obj, Row one)
			throws IllegalAccessException {
		switch (field.getType()) { //typ jaki jest w bazie
		case STRING:
			FieldUtils.writeField(obj, field.getKey(), one.getString(field.getValue()), true);
			break;
		case LONG:
			FieldUtils.writeField(obj, field.getKey(), one.getLong(field.getValue()), true);
			break;
		case BYTEBUFFER:
			FieldUtils.writeField(obj, field.getKey(), one.getBytes(field.getValue()), true);
			break;
		case BOOLEAN:
			FieldUtils.writeField(obj, field.getKey(), one.getBool(field.getValue()), true);
			break;
		case LOCALADATE:
			FieldUtils.writeField(obj, field.getKey(), one.getDate(field.getValue()), true);
			break;
		case BIGDECIMAL:
			FieldUtils.writeField(obj, field.getKey(), one.getDecimal(field.getValue()), true);
			break;
		case DOUBLE:
			FieldUtils.writeField(obj, field.getKey(), one.getDouble(field.getValue()), true);
			break;
		case FLOAT:
			FieldUtils.writeField(obj, field.getKey(), one.getFloat(field.getValue()), true);					break;
		case INT:
			FieldUtils.writeField(obj, field.getKey(), one.getInt(field.getValue()), true);
			break;
		case LIST:
			// to do
			break;
		case MAP:
			// to do
			break;
		case SET:
			// to do
			break;
		case SHORT:
			FieldUtils.writeField(obj, field.getKey(), one.getShort(field.getValue()), true);
			break;
		case INETADDRESS:
			FieldUtils.writeField(obj, field.getKey(), one.getInet(field.getValue()), true);
			break;
		case UUID:
			
			FieldUtils.writeField(obj, field.getKey(), one.getUUID(field.getValue()), true);
			break;
		case BYTE:
			FieldUtils.writeField(obj, field.getKey(), one.getByte(field.getValue()), true);
			break;
		case TUPLETYPE:
			FieldUtils.writeField(obj, field.getKey(), one.getTupleValue(field.getValue()), true);
			break;
		case BIGINTEGER:
			FieldUtils.writeField(obj, field.getKey(), one.getVarint(field.getValue()), true);
			break;
		case TIMESTAMP:
			FieldUtils.writeField(obj, field.getKey(), one.getTimestamp(field.getValue()), true);
			break;
		default:
			break;
		}
	}
	
	
	
	
	

	private void convertTypToDatabase(Object value, BoundStatement bound, JavaCastTyp classCast, int i) {
		switch (classCast) {
		case STRING:
			bound.setString(i, (String) value);
			break;
		case LONG:
			bound.setLong(i, (Long) value);
			break;
		case BYTEBUFFER:
			bound.setBytes(i, (ByteBuffer)value);
			break;
		case BOOLEAN:
			bound.setBool(i, (Boolean)value);
			break;
		case LOCALADATE:
			bound.setDate(i, (LocalDate)value);
			break;
		case BIGDECIMAL:
			bound.setDecimal(i, (BigDecimal)value);
			break;
		case DOUBLE:
			bound.setDouble(i, (Double)value);
			break;
		case FLOAT:
			bound.setFloat(i, (Float)value);
			break;
		case INT:
			bound.setInt(i, (Integer)value);
			break;
		case LIST:
			// to do
			break;
		case MAP:
			// to do
			break;
		case SET:
			// to do
			break;
		case SHORT:
			bound.setShort(i, (Short)value);
			break;
		case INETADDRESS:
			bound.setInet(i, (InetAddress)value);
			break;
		case UUID:
			bound.setUUID(i, (UUID)value);
			break;
		case BYTE:
			bound.setByte(i, (Byte)value);
			break;
		case TUPLETYPE:
			bound.setTupleValue(i, (TupleValue)value);
			break;
		case BIGINTEGER:
			bound.setVarint(i, (BigInteger)value);
			break;
		case TIMESTAMP:
			bound.setTimestamp(i, (Date)value);
			break;
		default:
			break;
		}	
	}


	public void setSession(Session session) {
		this.session = session;
	}

	/** Builid speed query insert przechowuje ilosc parametrow i ich nazwe szybciej buduje inserty i inne komendy  ***/
	public void addFieldMapping(ContainerFieldMaping field) {
		speedQuery.add(field);
		listField.add(field);
	}

	public List<ContainerFieldMaping> getListField() {
		return listField;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getDatabasename() {
		return databasename;
	}

	public void setDatabasename(String databasename) {
		this.databasename = databasename;
	}

}
