package com.encje;

import java.io.Serializable;
import java.sql.Timestamp;

import pit.kos.cassandra.adnotacje.ColumnName;
import pit.kos.cassandra.adnotacje.EntityName;
import pit.kos.cassandra.adnotacje.IDColumn;

@EntityName(name = "profil")
public class Profil  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@IDColumn(name = "id_osoba", type = "varchar")
	private String id_osoba;
	
	@ColumnName(name = "imie", type = "varchar")
	private String imie;
	
	@ColumnName(name = "nazwisko", type = "varchar")
	private String nazwisko;
	
	@ColumnName(name = "dataUrodzenia", type = "timestamp")
	private Timestamp dataUrodzenia;
	

	@ColumnName(name = "opis_siebie", type = "varchar")
	private String opis_siebie;
	
	@ColumnName(name = "motto", type = "varchar")
	private String motto;
	
	@ColumnName(name = "mezczyzna", type = "boolean")
	private Boolean mezczyzna;
	

	public Profil() {
		super();
	}

	/**
	 * @return the id_osoba
	 */
	public String getId_osoba() {
		return id_osoba;
	}

	/**
	 * @param id_osoba the id_osoba to set
	 */
	public void setId_osoba(String id_osoba) {
		this.id_osoba = id_osoba;
	}

	/**
	 * @return the imie
	 */
	public String getImie() {
		return imie;
	}

	/**
	 * @param imie the imie to set
	 */
	public void setImie(String imie) {
		this.imie = imie;
	}

	/**
	 * @return the nazwisko
	 */
	public String getNazwisko() {
		return nazwisko;
	}

	/**
	 * @param nazwisko the nazwisko to set
	 */
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	/**
	 * @return the dataUrodzenia
	 */
	public Timestamp getDataUrodzenia() {
		return dataUrodzenia;
	}

	/**
	 * @param dataUrodzenia the dataUrodzenia to set
	 */
	public void setDataUrodzenia(Timestamp dataUrodzenia) {
		this.dataUrodzenia = dataUrodzenia;
	}

	

	/**
	 * @return the opis_siebie
	 */
	public String getOpis_siebie() {
		return opis_siebie;
	}

	/**
	 * @param opis_siebie the opis_siebie to set
	 */
	public void setOpis_siebie(String opis_siebie) {
		this.opis_siebie = opis_siebie;
	}

	/**
	 * @return the motto
	 */
	public String getMotto() {
		return motto;
	}

	/**
	 * @param motto the motto to set
	 */
	public void setMotto(String motto) {
		this.motto = motto;
	}

	/**
	 * @return the mezczyzna
	 */
	public Boolean getMezczyzna() {
		return mezczyzna;
	}

	/**
	 * @param mezczyzna the mezczyzna to set
	 */
	public void setMezczyzna(Boolean mezczyzna) {
		this.mezczyzna = mezczyzna;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}
