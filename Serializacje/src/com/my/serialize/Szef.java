package com.my.serialize;

/** Teraz za³ó¿my, ¿e zapisujemy dane pracowników na dysk. Oczywiœcie nie mo¿emy zapisaæ
	i przywróciæ adresów obiektów asystentów w pamiêci, poniewa¿ po ponownym za³adowaniu
	obiekt asystenta najprawdopodobniej znajdzie siê w zupe³nie innym miejscu pamiêci.
	Zamiast tego ka¿dy obiekt zostaje zapisany z numerem seryjnym i st¹d w³aœnie pochodzi
	okreœlenie serializacja. Oto jej algorytm:
	*/

/** Numery seryjne inda od 1 do n dla kazdego elementui tam gdzie sa takie same referencje wstawia sie numery serializacji
 * 
 * 
 * */
public class Szef extends Pracownik {
	/** Wartosc klasy*/
	
	/**Od momentu, gdy w danej klasie umieœcisz powy¿sz¹ sta³¹, system serializacji bêdzie móg³
		odczytywaæ ró¿ne wersje obiektów tej samej klasy. Odsik klasy nie bedzie tworzony **/
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
