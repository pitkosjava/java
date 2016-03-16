package com.encje;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.util.UUID;

import com.datastax.driver.core.LocalDate;
import com.datastax.driver.core.TupleType;

import pit.kos.cassandra.adnotacje.ColumnName;
import pit.kos.cassandra.adnotacje.EntityName;
import pit.kos.cassandra.adnotacje.IDColumn;
import pit.kos.cassandra.dialect.Cassandra3Typ;

@EntityName(name = "monster")
public class Monster {
	
	
	@IDColumn(name = "id_monster", type = Cassandra3Typ.UUID)
	private UUID id_monster;

	@ColumnName(name = "email", type = Cassandra3Typ.VARCHAR)
	private String email;

	@ColumnName(name = "status_weryfikacji", type=Cassandra3Typ.BOOLEAN)
	private Boolean status_weryfikacji;

	@ColumnName(name = "data_rejestracji", type = Cassandra3Typ.TIMESTAMP)
	private Timestamp data_rejestracji;
	
	@ColumnName(name = "ascii", type = Cassandra3Typ.ASCII)
	private String ascii;
	
	@ColumnName(name = "bigint", type = Cassandra3Typ.BIGINT)
	private long bigint;
	
	@ColumnName(name = "byteBuffer", type = Cassandra3Typ.BLOB)
	private ByteBuffer byteBuffer;
	
	@ColumnName(name = "locladta", type = Cassandra3Typ.DATE)
	private LocalDate locladta;
	
	@ColumnName(name = "bigdecimal", type = Cassandra3Typ.DECIMAL)
	private BigDecimal bigdecimal;
	
	@ColumnName(name = "doubles", type = Cassandra3Typ.DOUBLE)
	private Double doubles;
	
	@ColumnName(name = "floats", type = Cassandra3Typ.FLOAT)
	private Float floats;
	
	@ColumnName(name = "ipadress", type = Cassandra3Typ.INET)
	private InetAddress ipadress;
	
	
	@ColumnName(name = "intint", type = Cassandra3Typ.INT)
	private Integer intint;


	@ColumnName(name = "shortsee", type = Cassandra3Typ.SMALLINT)
	private Short shortsee;
	
	@ColumnName(name = "texts", type = Cassandra3Typ.TEXT)
	private String texts;
	
	
	@ColumnName(name = "times", type = Cassandra3Typ.TIME)
	private Long times;
	
	@ColumnName(name = "uuidtime", type = Cassandra3Typ.TIMEUUID)
	private UUID timesuuid;
	
	@ColumnName(name = "tinnyinr", type = Cassandra3Typ.TINYINT)
	private Byte tinnyinr;
	
	
	/*@ColumnName(name = "typles", type = Cassandra3Typ.TUPLE)
	private TupleType typles;
	*/
	
	@ColumnName(name = "varrint", type = Cassandra3Typ.BIGINT)
	private BigInteger varrint;
	/**
	 * @return the id_monster
	 */
	public UUID getId_monster() {
		return id_monster;
	}

	/**
	 * @param id_monster the id_monster to set
	 */
	public void setId_monster(UUID id_monster) {
		this.id_monster = id_monster;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the status_weryfikacji
	 */
	public Boolean getStatus_weryfikacji() {
		return status_weryfikacji;
	}

	/**
	 * @param status_weryfikacji the status_weryfikacji to set
	 */
	public void setStatus_weryfikacji(Boolean status_weryfikacji) {
		this.status_weryfikacji = status_weryfikacji;
	}

	/**
	 * @return the data_rejestracji
	 */
	public Timestamp getData_rejestracji() {
		return data_rejestracji;
	}

	/**
	 * @param data_rejestracji the data_rejestracji to set
	 */
	public void setData_rejestracji(Timestamp data_rejestracji) {
		this.data_rejestracji = data_rejestracji;
	}

	/**
	 * @return the ascii
	 */
	public String getAscii() {
		return ascii;
	}

	/**
	 * @param ascii the ascii to set
	 */
	public void setAscii(String ascii) {
		this.ascii = ascii;
	}

	/**
	 * @return the bigint
	 */
	public long getBigint() {
		return bigint;
	}

	/**
	 * @param bigint the bigint to set
	 */
	public void setBigint(long bigint) {
		this.bigint = bigint;
	}

	/**
	 * @return the byteBuffer
	 */
	public ByteBuffer getByteBuffer() {
		return byteBuffer;
	}

	/**
	 * @param byteBuffer the byteBuffer to set
	 */
	public void setByteBuffer(ByteBuffer byteBuffer) {
		this.byteBuffer = byteBuffer;
	}

	

	/**
	 * @return the locladta
	 */
	public LocalDate getLocladta() {
		return locladta;
	}

	/**
	 * @param locladta the locladta to set
	 */
	public void setLocladta(LocalDate locladta) {
		this.locladta = locladta;
	}

	/**
	 * @return the bigdecimal
	 */
	public BigDecimal getBigdecimal() {
		return bigdecimal;
	}

	/**
	 * @param bigdecimal the bigdecimal to set
	 */
	public void setBigdecimal(BigDecimal bigdecimal) {
		this.bigdecimal = bigdecimal;
	}

	/**
	 * @return the doubles
	 */
	public Double getDoubles() {
		return doubles;
	}

	/**
	 * @param doubles the doubles to set
	 */
	public void setDoubles(Double doubles) {
		this.doubles = doubles;
	}

	/**
	 * @return the floats
	 */
	public Float getFloats() {
		return floats;
	}

	/**
	 * @param floats the floats to set
	 */
	public void setFloats(Float floats) {
		this.floats = floats;
	}

	/**
	 * @return the ipadress
	 */
	public InetAddress getIpadress() {
		return ipadress;
	}

	/**
	 * @param ipadress the ipadress to set
	 */
	public void setIpadress(InetAddress ipadress) {
		this.ipadress = ipadress;
	}

	/**
	 * @return the intint
	 */
	public Integer getIntint() {
		return intint;
	}

	/**
	 * @param intint the intint to set
	 */
	public void setIntint(Integer intint) {
		this.intint = intint;
	}

	/**
	 * @return the shortsee
	 */
	public Short getShortsee() {
		return shortsee;
	}

	/**
	 * @param shortsee the shortsee to set
	 */
	public void setShortsee(Short shortsee) {
		this.shortsee = shortsee;
	}

	/**
	 * @return the texts
	 */
	public String getTexts() {
		return texts;
	}

	/**
	 * @param texts the texts to set
	 */
	public void setTexts(String texts) {
		this.texts = texts;
	}

	/**
	 * @return the times
	 */
	public Long getTimes() {
		return times;
	}

	/**
	 * @param times the times to set
	 */
	public void setTimes(Long times) {
		this.times = times;
	}

	/**
	 * @return the timesuuid
	 */
	public UUID getTimesuuid() {
		return timesuuid;
	}

	/**
	 * @param timesuuid the timesuuid to set
	 */
	public void setTimesuuid(UUID timesuuid) {
		this.timesuuid = timesuuid;
	}

	/**
	 * @return the tinnyinr
	 */
	public Byte getTinnyinr() {
		return tinnyinr;
	}

	/**
	 * @param tinnyinr the tinnyinr to set
	 */
	public void setTinnyinr(Byte tinnyinr) {
		this.tinnyinr = tinnyinr;
	}


	/**
	 * @return the varrint
	 */
	public BigInteger getVarrint() {
		return varrint;
	}

	/**
	 * @param varrint the varrint to set
	 */
	public void setVarrint(BigInteger varrint) {
		this.varrint = varrint;
	}
	
	
	
	
	
/** ascii java.lang.String
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
		varint java.math.BigInteger*/
	
	
	

}
