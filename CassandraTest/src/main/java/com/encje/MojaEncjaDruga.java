package com.encje;

import pit.kos.cassandra.adnotacje.ColumnName;
import pit.kos.cassandra.adnotacje.EntityName;
import pit.kos.cassandra.adnotacje.IDColumn;
import pit.kos.cassandra.dialect.Cassandra3Typ;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
@Table(keyspace = "testowa4", name = "moja_encjadwa")
@EntityName(name = "moja_encjadwa")
public class MojaEncjaDruga {
	@PartitionKey
	@IDColumn(name = "id_encjadwa", type = Cassandra3Typ.INT)
	private Integer id_encjadwa;
	
	@Column (name = "email")
	@ColumnName(name = "email", type = Cassandra3Typ.VARCHAR)
	private String email;

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId_encjadwa() {
		return id_encjadwa;
	}

	public void setId_encjadwa(Integer id_encjadwa) {
		this.id_encjadwa = id_encjadwa;
	}

	
}
