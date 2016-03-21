package com.my.serialize;

public class Pracownik extends Osoba {
	private static final long serialVersionUID = 1L;
	private double wyplta;

	public Pracownik(String imie, String nazwisko) {
		super(imie, nazwisko);
		wyplta = 0.0;
	}

	public double getWyplta() {
		return wyplta;
	}

	public void setWyplta(double wyplta) {
		this.wyplta = wyplta;
	}

	@Override
	public String toString() {
		return "Pracownik [wyplta=" + wyplta + "]" +super.toString();
	}
	

}
