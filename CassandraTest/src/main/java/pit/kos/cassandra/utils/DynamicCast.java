package pit.kos.cassandra.utils;

import java.util.HashMap;
import java.util.Map;

import static pit.kos.cassandra.dialect.Cassandra3Typ.*;

/**
 * Faster builid query and comand for list,set,map other solution
 * ***/
public class DynamicCast {
	private static final int ALL_DATA_TYP = 23;
	private static  Map<String, JavaCastTyp> mapaCast= new HashMap<>(ALL_DATA_TYP);

	static{
		mapaCast.put(VARCHAR,JavaCastTyp.STRING); 	//varchar java.lang.String
		mapaCast.put(ASCII,JavaCastTyp.STRING); 	//ascii java.lang.String
		mapaCast.put(TEXT,JavaCastTyp.STRING);		//text java.lang.String
		mapaCast.put(BIGINT,JavaCastTyp.LONG);      //bigint long
		mapaCast.put(COUNTER,JavaCastTyp.LONG);    //counter long   
		mapaCast.put(TIME,JavaCastTyp.LONG);         //time long
		mapaCast.put(BLOB,JavaCastTyp.BYTEBUFFER);    //blob java.nio.ByteBuffer
		mapaCast.put(BOOLEAN,JavaCastTyp.BOOLEAN);    //boolean boolean
		mapaCast.put(DATE,JavaCastTyp.LOCALADATE);   //date com.datastax.driver.core.LocalDate
		mapaCast.put(DECIMAL,JavaCastTyp.BIGDECIMAL); //decimal java.math.BigDecimal
		mapaCast.put(DOUBLE,JavaCastTyp.DOUBLE);      //double double
		mapaCast.put(FLOAT,JavaCastTyp.FLOAT);      //float float
		mapaCast.put(INT,JavaCastTyp.INT);         //int int
		mapaCast.put(SMALLINT,JavaCastTyp.SHORT);     //smallint short2
		mapaCast.put(INET, JavaCastTyp.INETADDRESS); // inet java.net.InetAddress
		mapaCast.put(TIMESTAMP,JavaCastTyp.TIMESTAMP); //timestamp java.util.Date1 to getTimestamp() and setTimestamp()
		mapaCast.put(TIMEUUID,JavaCastTyp.UUID);      //timeuuid java.util.UUID
		mapaCast.put(TINYINT,JavaCastTyp.BYTE);         //tinyint byte
		mapaCast.put(TUPLE,JavaCastTyp.TUPLETYPE);      //tuple com.datastax.driver.core.TupleType
		mapaCast.put(UUID,JavaCastTyp.UUID);             //uuid java.util.UUID
		mapaCast.put(VARINT,JavaCastTyp.BIGINTEGER);   //varint java.math.BigInteger
		mapaCast.put(SET,JavaCastTyp.SET); 
		mapaCast.put(LIST,JavaCastTyp.LIST); 
		mapaCast.put(MAP,JavaCastTyp.MAP); 
	}
	 /***
	    *   ascii java.lang.String
			bigint long
			blob java.nio.ByteBuffer
			boolean boolean
			counter long
			date com.datastax.driver.core.LocalDate1
			decimal java.math.BigDecimal
			double double
			float float
			inet java.net.InetAddress
			int int
			list java.util.List<T>
			map java.util.Map<K, V>
			set java.util.Set<T>
			smallint short2
			text java.lang.String
			time long
			timestamp java.util.Date1
			timeuuid java.util.UUID
			tinyint byte2
			tuple com.datastax.driver.core.TupleType
			uuid java.util.UUID
			varchar java.lang.String
			varint java.math.BigInteger
	*/
	public static JavaCastTyp getClassTyp(String key) {
		return mapaCast.get(key);
	}

}
