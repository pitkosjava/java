package moja.biblioteka;

public class AlgorytmGenetyczny {

    /* Parametry mutacji */
    // im wyzszy wspolczynik tym wiecej muacji
    private static double wspolczynikMutacji = 0.15;
    // wspolczynik okresla jak duzo elementow losowo pobieramy z populacji i wybieramy najlepszego by byl ojcm
    private static final int rozmiarKontrolny = 15;
    // zmiena dzieki ktorej zapamietujemy najlepsze rzwiazanie 
    private static final boolean zapiszNajlepszyZeStarejPopulacji = true;

    public static Populacja utworzNowaPopulcje(Populacja populacja) {
        // tu populacji nie tworzymy dlatego false
        Populacja nowaPopulacja = new Populacja(populacja.wielkoscPopulacji(), false);
        int offsetTras = 0;
        if (zapiszNajlepszyZeStarejPopulacji) {
            nowaPopulacja.zapiszTrase(0, populacja.pobierzNajlepszaTraseZPopulacji());
            offsetTras = 1;
        }
        // tworzenie dzieci
        for (int i = offsetTras; i < nowaPopulacja.wielkoscPopulacji(); i++) {
            Trasa rodzic1 = wybierzTraseZTestowejPopulacji(populacja);
            Trasa rodzic2 = wybierzTraseZTestowejPopulacji(populacja);
            Trasa dziecko = krzyzuj(rodzic1, rodzic2);
            // zapisujemy dziecko w populacji
            nowaPopulacja.zapiszTrase(i, dziecko);
        }

        // po krzyzowaniu dokonujemy mutacji na wszystkich osobnikach  populacji
        for (int j = offsetTras; j < nowaPopulacja.wielkoscPopulacji(); j++) {
            mutacja(nowaPopulacja.pobierzTrase(j));
        }

        //mutacja(dziecko);
        return nowaPopulacja;
    }

    // metod pobira najlepsza trase z losowego podzbioru populacji glownej
    public static Trasa wybierzTraseZTestowejPopulacji(Populacja pop) {
        // false bo nie losujemy 
        Populacja test = new Populacja(rozmiarKontrolny, false);
        for (int i = 0; i < rozmiarKontrolny; i++) {
            int randomId = (int) (Math.random() * pop.wielkoscPopulacji());
            test.zapiszTrase(i, pop.pobierzTrase(randomId));
        }
        Trasa najlepsza = test.pobierzNajlepszaTraseZPopulacji();
        return najlepsza;
    }

    public static Trasa krzyzuj(Trasa rodzic1, Trasa rodzic2) {
        Trasa dziecko = new Trasa();

        // losujemy indexy od ktorego skopiujemy sciezki i ich nie zmienimy od rodzica pierwszego
        int start = (int) (Math.random() * rodzic1.iloscMiast());
        int koniec = (int) (Math.random() * rodzic1.iloscMiast());

        // wazny jest operator krzyzowania w jaki spopsub zachodzi z rodzica pierwszego
        // pierwszy rodzic
        for (int i = 0; i < dziecko.iloscMiast(); i++) {
            if (start < koniec && i > start && i < koniec) {
                dziecko.setMiasto(rodzic1.getMiasto(i), i);
            } /* teraz aby urozmaici geny pobieramy z rodzica geny od 0 do koniec i od start do rozmiaru
             pobieramy reszte  ,jakby wycuety zbior rodzicow dolacze ci screena do tego
             te krzyzowanie powwoli na otrzymanie lepszej populacji */ else if (start > koniec) {
                if (!(i < start && i > koniec)) {
                    dziecko.setMiasto(rodzic1.getMiasto(i), i);
                }
            }
        }

        // kopiowanie danych z drugiego rodzica wstawiamy po koleji
        // Loop through parent2's city tour
        for (int i = 0; i < rodzic2.iloscMiast(); i++) {
            // If child doesn't have the city add it
            if (!dziecko.czyMiastoJestWTrasie(rodzic2.getMiasto(i))) {
                // Loop to find a spare position in the child's tour
                for (int j = 0; j < dziecko.iloscMiast(); j++) {
                    // Spare position found, add city
                    if (dziecko.getMiasto(j) == null) {
                        dziecko.setMiasto(rodzic2.getMiasto(i), j);
                        break;
                    }
                }
            }
        }

        return dziecko;
    }

    // zamienia  elementy miejscami 
    public static void mutacja(Trasa trasa) {

        for (int start = 0; start < trasa.iloscMiast(); start++) {
            if (Math.random() < wspolczynikMutacji) {
                // losowanie elementu do zamiany
                int zamiana = (int) (trasa.iloscMiast() * Math.random());

                Miasto miasto1 = trasa.getMiasto(start);
                Miasto miasto2 = trasa.getMiasto(zamiana);
                // zamiana miejscami 
                trasa.setMiasto(miasto1, zamiana);
                trasa.setMiasto(miasto2, start);
            }
        }
    }

    public static double getWspolczynikMutacji() {
        return wspolczynikMutacji;
    }

    public static void setWspolczynikMutacji(double wspolczynikMutacji) {
        AlgorytmGenetyczny.wspolczynikMutacji = wspolczynikMutacji;
    }

}
