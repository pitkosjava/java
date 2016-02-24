package moja.biblioteka;

import java.util.ArrayList;
import java.util.Collections;

public class Trasa {

    private ArrayList<Miasto> trasa = new ArrayList<Miasto>();
    private double dlugoscTrasy;

    public Trasa() {
        for (int i = 0; i < TrasaMenadzer.iloscMiast(); i++) {
            trasa.add(null);
        }
        dlugoscTrasy = 0;
    }

    public Trasa(ArrayList<Miasto> trasa) {
        this.trasa = trasa;
    }

    public void setMiasto(Miasto miasto, int index) {
        trasa.set(index, miasto);
    }

    public Miasto getMiasto(int index) {
        return (Miasto) trasa.get(index);
    }

    public void inicjalizujTrase() {
        for (int i = 0; i < TrasaMenadzer.iloscMiast(); i++) {
            setMiasto(TrasaMenadzer.getMiasto(i), i);
        }
        // tasowanie(mieszanie by zdbyc losowa trase) trasy na losowa wartosc
        Collections.shuffle(trasa);
    }

    public boolean czyMiastoJestWTrasie(Miasto miasto) {
        return trasa.contains(miasto);
    }

    public int iloscMiast() {
        return trasa.size();
    }

    public double getDlugoscTrasy() {
        if (dlugoscTrasy == 0) {
            for (int i = 0; i < trasa.size(); i++) {

                Miasto temp = (Miasto) trasa.get(i);

                // czy mozmy pobrac jesli z ostatniego miasta idziemy do pierwszego z indexem 0
                if (i + 1 < trasa.size()) {

                    Miasto temp2 = (Miasto) trasa.get(i + 1);
                    dlugoscTrasy += temp2.odlegloscZmiasta(temp);

                } else {
                    Miasto temp2 = (Miasto) trasa.get(0);
                    dlugoscTrasy += temp2.odlegloscZmiasta(temp);
                }
            }
        }
        return dlugoscTrasy;
    }

    @Override
    public String toString() {
        String info = "";
        for (int i = 0; i < trasa.size(); i++) {
            // automatyczna konwersja na string przelonieta metoda w klasie Miasto -toString
            info += "|" + trasa.get(i).toString();
        }
        return info;

    }

    // im dlusza trasa tym gorsza do porownania tras bedzie sluzyc odwrotnosc
    public double dopasowanieTrasy() {
        double tempos = getDlugoscTrasy();

        return 1 / tempos;
    }

}
