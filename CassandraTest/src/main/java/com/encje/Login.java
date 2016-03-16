package com.encje;

import java.io.Serializable;
import java.sql.Timestamp;

import pit.kos.cassandra.adnotacje.ColumnName;
import pit.kos.cassandra.adnotacje.EntityName;
import pit.kos.cassandra.adnotacje.IDColumn;
import pit.kos.cassandra.dialect.Cassandra3Typ;

@EntityName(name = "login")
public class Login implements Serializable {

	private static final long serialVersionUID = 1L;

	@IDColumn(name = "id_login", type = Cassandra3Typ.VARCHAR)
	private String id_login;

	@ColumnName(name = "email", type = Cassandra3Typ.VARCHAR)
	private String email;

	@ColumnName(name = "haslo", type = Cassandra3Typ.VARCHAR)
	private String haslo;

	@ColumnName(name = "status_weryfikacji", type=Cassandra3Typ.BOOLEAN)
	private Boolean status_weryfikacji;

	@ColumnName(name = "kod_weryfikacji", type = Cassandra3Typ.VARCHAR)
	private String kod_weryfikacji;

	@ColumnName(name = "data_rejestracji", type = Cassandra3Typ.TIMESTAMP)
	private Timestamp data_rejestracji;

	@ColumnName(name = "rola", type = Cassandra3Typ.VARCHAR)
	private String rola;

	public Login() {
		super();
		status_weryfikacji=false;
	}

	public String getId_login() {
		return id_login;
	}
	public void setId_login(String id_login) {
		this.id_login = id_login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHaslo() {
		return haslo;
	}
	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	public Boolean getStatus_weryfikacji() {
		return status_weryfikacji;
	}

	public void setStatus_weryfikacji(Boolean status_weryfikacji) {
		this.status_weryfikacji = status_weryfikacji;
	}

	public String getKod_weryfikacji() {
		return kod_weryfikacji;
	}

	public void setKod_weryfikacji(String kod_weryfikacji) {
		this.kod_weryfikacji = kod_weryfikacji;
	}

	public Timestamp getData_rejestracji() {
		return data_rejestracji;
	}

	public void setData_rejestracji(Timestamp data_rejestracji) {
		this.data_rejestracji = data_rejestracji;
	}

	public String getRola() {
		return rola;
	}

	public void setRola(String rola) {
		this.rola = rola;
	}

	
	
	

}
