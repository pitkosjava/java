package moja.biblioteka;

public class Populacja {

    private Trasa[] trasy;

    public Populacja(int wielkoscPopulacji, boolean inicjalizacjaTras) {
        trasy = new Trasa[wielkoscPopulacji];
        if (inicjalizacjaTras) {
            for (int i = 0; i < wielkoscPopulacji; i++) {
                Trasa nowa = new Trasa();
                nowa.inicjalizujTrase();
                zapiszTrase(i, nowa);
            }
        }
    }

    public Trasa pobierzNajlepszaTraseZPopulacji() {
        Trasa najlepsza = trasy[0];

        for (int i = 1; i < wielkoscPopulacji(); i++) {
            if (najlepsza.dopasowanieTrasy() <= trasy[i].dopasowanieTrasy()) {
                najlepsza = trasy[i];
            }
        }
        return najlepsza;
    }

    public int wielkoscPopulacji() {
        return trasy.length;
    }

    public void zapiszTrase(int index, Trasa trasa) {
        trasy[index] = trasa;
    }

    public Trasa pobierzTrase(int index) {
        return trasy[index];
    }

}
