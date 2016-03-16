package pit.kos.cassandra.utils;

public enum JavaCastTyp {
   STRING,LONG,BYTEBUFFER,BOOLEAN,LOCALADATE,BIGDECIMAL,DOUBLE,FLOAT,INT,
   LIST,MAP,SET,SHORT,INETADDRESS,UUID,BYTE,TUPLETYPE,BIGINTEGER,TIMESTAMP;
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
		timestamp java.util.Date1 TIMESTAMP
		timeuuid java.util.UUID
		tinyint byte2
		tuple com.datastax.driver.core.TupleType
		uuid java.util.UUID
		varchar java.lang.String
		varint java.math.BigInteger
*/
}
