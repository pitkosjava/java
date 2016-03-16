package pit.kos.cassandra.main;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import pit.kos.cassandra.adnotacje.ColumnName;
import pit.kos.cassandra.adnotacje.EntityName;
import pit.kos.cassandra.adnotacje.IDColumn;
import pit.kos.cassandra.dialect.Cassandra3Comand;
import pit.kos.cassandra.interfaces.MetodsTableDataBase;
import pit.kos.cassandra.utils.ContainerEntityMapping;
import pit.kos.cassandra.utils.ContainerFieldMaping;

import com.datastax.driver.core.Session;

public class Mapper implements MetodsTableDataBase {
	/** Infomracja o klasie ktora ma mapowac obiekt**/
	private Class<? extends Object> object;
	private ContainerEntityMapping containerMappingClass;

	public Mapper(Class<?  extends Object> object) {
		this.object = object;
		final Annotation[] annotations = this.object.getAnnotations();
		final Field[] fields = this.object.getDeclaredFields();
		EntityName entityName;
		for (Annotation annotation : annotations) {
			if (annotation instanceof EntityName) {
				entityName = (EntityName) annotation;
				containerMappingClass = new ContainerEntityMapping(object.getName(), entityName.name());
			}
		}

		ColumnName columnName;
		IDColumn column;
		/** set speed query**/
		for (Field f : fields) {
			Annotation[] annotationsfield = f.getAnnotations();
			for (Annotation annotation : annotationsfield) {
				if (annotation instanceof IDColumn) {
					column = (IDColumn) annotation;
					containerMappingClass.addFieldMapping(new ContainerFieldMaping(f.getName(), column.name(), column.type()));
				} else if (annotation instanceof ColumnName) {
					columnName = (ColumnName) annotation;
					containerMappingClass.addFieldMapping(new ContainerFieldMaping(f.getName(), columnName.name(), columnName.type()));
				}
			}
		}
	
		
	}
	
	
	
	
	/**  komendy do bazy co ma zrobic z ze znaleziona encja obsluga w classie
	 *  cassandraConnector ona odpowiada za te parametry znajdujace sie w pliku wlasciwosci
	 * 	 tabela moga byc tworzone modyfikowane lub brak akcji wszystko zalezy od parametrow
	 *  polaczenia kazdy mapper obsluguje tylko swoja klase
	 *  **/
	public void createTable(Session session) {
		StringBuilder buffer = new StringBuilder();
		  buffer.append(Cassandra3Comand.CREATE_TABLE).append(Cassandra3Comand.SPACE)
				.append(containerMappingClass.getDatabasename()).append(Cassandra3Comand.SPACE)
				.append(Cassandra3Comand.OPEN_TABLE).append(Cassandra3Comand.SPACE);
		
		for (ContainerFieldMaping fields : containerMappingClass.getListField()) {
			buffer.append(fields.getValue()).append(Cassandra3Comand.SPACE)
				  .append(fields.getTyp()).append(Cassandra3Comand.COMMA);
		}
		  buffer.append(Cassandra3Comand.PRIMARY_KEY).append(Cassandra3Comand.SPACE).append(Cassandra3Comand.OPEN_TABLE)
				.append(containerMappingClass.getListField().get(0).getValue())
				.append(Cassandra3Comand.CLOSE_TABLE).append(Cassandra3Comand.CLOSE_TABLE);
		session.execute(buffer.toString());
	}

	public void deleteTable(Session session) {
		StringBuilder buffer = new StringBuilder();
		buffer.append(Cassandra3Comand.DROP_TABLE).append(Cassandra3Comand.SPACE)
		.append(containerMappingClass.getDatabasename());
		session.execute(buffer.toString());
	}

	public void updateTable(Session session) {
		// to do
	}

	public void validateTable(Session session) {
		// to do
	}

	public void truncateTable(Session session) {
		// to do
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((object == null) ? 0 : object.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		Class<? extends Object> other = obj.getClass();
		if (object.getName().equals(other.getName())) {
			return true;
		}
		return false;
	}
	
	
	public String insertStringBuilid(Object e){
		
		return "";
	}

	public ContainerEntityMapping getContainerMappingClass() {
		return containerMappingClass;
	}

	

}
