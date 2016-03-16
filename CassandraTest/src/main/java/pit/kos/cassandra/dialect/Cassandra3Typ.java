package pit.kos.cassandra.dialect;


/** datastax 3.0.0 drivers  convert  typ cassandra -> java typ 
 * 
 * @author serv
 *
 */
public class Cassandra3Typ {
	// String types
	public static final String VARCHAR="varchar";
	public static final String ASCII="ascii";
	public static final String TEXT="text";
	// long type 
	public static final String BIGINT="bigint";
	public static final String COUNTER="counter";
	public static final String TIME="time";
	// java.nio.ByteBuffer
	public static final String BLOB="blob";
	// boolean
	public static final String BOOLEAN="boolean";
	// com.datastax.driver.core.LocalDat
	public static final String DATE="date";
	// java.math.BigDecimal
	public static final String DECIMAL="decimal";
	//double
	public static final String DOUBLE="double";
	//float
	public static final String FLOAT="float";
	// int
	public static final String INT="int";
	// java.util.List<T>
	public static final String LIST="list";
	// java.util.Map<K, V>
	public static final String MAP="map";
	// java.util.Set<T>
	public static final String SET="set";
	// short
	public static final String SMALLINT="smallint";
	// java.net.InetAddress
	public static final String INET="inet";
	// java.util.Date1
	public static final String TIMESTAMP="timestamp";
	// java.util.UUID
	public static final String TIMEUUID="timeuuid";
	// tinyint byte
	public static final String TINYINT="tinyint";
	//tuple com.datastax.driver.core.TupleType
	public static final String TUPLE="tuple";
	//uuid java.util.UUID
	public static final String UUID="uuid";
	//varint java.math.BigInteger
	public static final String VARINT="varint";
}
