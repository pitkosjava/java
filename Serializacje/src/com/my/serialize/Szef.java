package com.my.serialize;

/** Teraz za��my, �e zapisujemy dane pracownik�w na dysk. Oczywi�cie nie mo�emy zapisa�
	i przywr�ci� adres�w obiekt�w asystent�w w pami�ci, poniewa� po ponownym za�adowaniu
	obiekt asystenta najprawdopodobniej znajdzie si� w zupe�nie innym miejscu pami�ci.
	Zamiast tego ka�dy obiekt zostaje zapisany z numerem seryjnym i st�d w�a�nie pochodzi
	okre�lenie serializacja. Oto jej algorytm:
	*/

/** Numery seryjne inda od 1 do n dla kazdego elementui tam gdzie sa takie same referencje wstawia sie numery serializacji
 * 
 * 
 * */
public class Szef extends Pracownik {
	/** Wartosc klasy*/
	
	/**Od momentu, gdy w danej klasie umie�cisz powy�sz� sta��, system serializacji b�dzie m�g�
		odczytywa� r�ne wersje obiekt�w tej samej klasy. Odsik klasy nie bedzie tworzony **/
	private static final long serialVersionUID = 3644588173528004921L;
	
	/** wszystkim referencja sa nadawane numery seryjne  jesli ta referencja zostala jako pierwsza
	 * obiekt zostaje zapisany, jesli obiekt zostal juz zapisany ma ta sama referencje zostaje wpisane ze tu jest obiekt co pod numerem seryjnym x**/
	private Pracownik asystent;

	public Szef(String imie, String nazwisko) {
		super(imie, nazwisko);
	}

	public Pracownik getAsystent() {
		return asystent;
	}

	public void setAsystent(Pracownik asystent) {
		this.asystent = asystent;
	}
}
