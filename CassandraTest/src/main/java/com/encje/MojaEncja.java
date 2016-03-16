package com.encje;

import pit.kos.cassandra.adnotacje.ColumnName;
import pit.kos.cassandra.adnotacje.EntityName;
import pit.kos.cassandra.adnotacje.IDColumn;
import pit.kos.cassandra.dialect.Cassandra3Typ;

@EntityName(name = "moja_encja")
public class MojaEncja {
	
	
	@IDColumn(name = "id_encja", type = Cassandra3Typ.INT)
	private Integer id_encja;
	
	
	@ColumnName(name = "email", type = Cassandra3Typ.VARCHAR)
	private String email;

	
	
	public MojaEncja() {
		super();
	}

	public Integer getId_encja() {
		return id_encja;
	}

	public void setId_encja(Integer id_encja) {
		this.id_encja = id_encja;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MojaEncja [id_encja=" + id_encja + ", email=" + email + "]";
	}

	
	
}
