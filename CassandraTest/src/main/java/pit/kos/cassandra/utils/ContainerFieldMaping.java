package pit.kos.cassandra.utils;

import java.io.Serializable;

public class ContainerFieldMaping implements Serializable{
	
	private static final long serialVersionUID = 7746756140600369031L;
	private String key; 			// nazwa pola w klasie
	private String value; 			// nazwa pola w bazie danych
	private String typ;  			//typ pola w bazie danychy
	private JavaCastTyp type;		// typ uzywany w programie java

	public ContainerFieldMaping(String key, String value, String typ) {
		super();
		this.key = key;
		this.value = value;
		this.typ = typ;
		this.type=DynamicCast.getClassTyp(typ);
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the type
	 */
	public JavaCastTyp getType() {
		return type;
	}
	
}
