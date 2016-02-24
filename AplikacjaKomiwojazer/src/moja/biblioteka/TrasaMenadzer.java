package moja.biblioteka;

import java.util.ArrayList;

/**
 * Klasa odpowiedzialna za przechowanie wszystkich miast zaznaczonych w
 * programie zapamietuje wspolrzedne kazdego miasta(Klikniecia na komponent)
 */
public class TrasaMenadzer {

    // lista miast
    private static ArrayList<Miasto> trasa = new ArrayList<>();

    // dodanie miasta 
    public static void dodajMiasto(Miasto miasto) {
        trasa.add(miasto);
    }

    public static Miasto getMiasto(int index) {
        return (Miasto) trasa.get(index);
    }

    // calkowita ilosc miast
    public static int iloscMiast() {
        return trasa.size();
    }

    public static void resetujMiasta() {
        trasa = new ArrayList<>();
    }

}
