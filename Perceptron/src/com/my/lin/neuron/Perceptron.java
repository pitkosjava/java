package com.my.lin.neuron;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Perceptron implements NeuronInterfaces {

    private int inputNumbers;                                               // liczba wejsc 
    private double[] weight;                                                // wektor wag neuronu
    private double[] inputSignals;                                          // wektor podany na wejscie wytrenowanego  neuronu
    private double outputSignal;                                            // wartosc odpowiedzi neuronu
    private double outputSignalSuma;                                        // wartosc odpowiedzi neuronu jak funkcja ciagla

    public Perceptron(int size) {
        this.inputNumbers = size;                                           // wektor zerowy nie jest podawany na wejscie 
        this.weight = new double[inputNumbers];
        this.inputSignals = new double[inputNumbers];
    }

    @Override
    public void trenujNeuron(double[][] signalLern) {
        /**
         * zerujemy wektor wag
         */
        zerujWagi();
        /**
         * Tworzymy zbior rozwiazan indeks kazdego wiersza trenujacego
         * umieszzany jest w biorze gdy nastopi poprawna klasyfikacja, w zbiorze
         * nie ma duplicatow, jesli w zbiorze jest tyle elementow co ilosc
         * wektorow trenujacych t otrzymalismy poprawne rozwiazanie
         */
        Set<Integer> set = new HashSet<>();
        /**
         * Zmienna odpowiedzialna za losowanie wektora trenujacego
         */
        int selectNeuron = 0;
        /**
         * Sprawdzamy czy w zbiorze rozwiazan jest tyle elementow co ilosc
         * neuronow trenujacych jesli tak to neuron zostal wutrenowany i
         * opuszczamy metode
         */
        while (!(set.size() == signalLern.length)) {
            /**
             * Losowanie wektora trenujacego
             */
            selectNeuron = randInt(0, signalLern.length - 1);
            /**
             * Sprawdzenie czy odpowiedz neurona jest prawidlowa
             */
            if (sprawdzenieOdpowiedzi(signalLern[selectNeuron])) {
                /**
                 * Odpowiedz prawidlowa umieszczamy nr wektora trenujacego w
                 * zbiorze rozwiazan
                 */
                set.add(selectNeuron);
            } else {
                /**
                 * Neuron popelnij blad podczas weryfikacji Czyscimy zbior
                 * rozwiazan, neuron musi zakwalifikowac wszystkie rozwiazania
                 * poprawnie
                 */
                set.clear();
                do {
                    /**
                     * Powtarzamy aktualizacje wag dopoki nie nastopi poprawna
                     * klasyfikacja
                     */
                    aktualizujWagi(signalLern[selectNeuron]);
                } while (!sprawdzenieOdpowiedzi(signalLern[selectNeuron]));
                /**
                 * Dodajemy nr wektora trenujacego do zbioru rozwiazan
                 */
                set.add(selectNeuron);
            }
        }
    }

    private boolean sprawdzenieOdpowiedzi(double[] lernSignal) {
        /**
         * Ustawiamy sygnaly wejsciowe z wektora lernSignal neuron po ustawieniu
         * sygnałów oblicza sygnal wyjsciowy outputSignal
         */
        ustawWejscia(lernSignal);
        /**
         * neuron udzielil odpowiedzi zgodnie z wartoscia zawarta w wekorze
         * trenujacym
         */
        if (lernSignal[lernSignal.length - 1] > 0 && outputSignal > 0) {
            return true;
        } else if (lernSignal[lernSignal.length - 1] < 0 && outputSignal < 0) {
            return true;
        } /**
         * Neuron nie udzielil poprawnej odpowiedzi
         */
        else {
            return false;
        }
    }

    private void ustawWejscia(double[] tab) {
        /**
         * Zerujemy sume sygnałów
         */
        outputSignalSuma = 0;
        /**
         * Aktualizujemy wektor sygnalow wejsciowych oraz obliczamy sume
         */
        for (int i = 0; i < inputNumbers; i++) {
            inputSignals[i] = tab[i];
            outputSignalSuma += inputSignals[i] * weight[i];
        }
        /**
         * Wprowadzamy funkcje dyskretyzacji wartosci ciaglej na dyskretna
         */
        if (outputSignalSuma > 0) {
            outputSignal = 1;
        } else if (outputSignalSuma < 0) {
            outputSignal = -1;
        } else {
            outputSignal = 0.0;
        }
    }

    private void aktualizujWagi(double[] lernWektor) {
        /**
         * Aktualizujemu wagi zgodnie z opisanym algorytmem
         */
        if (outputSignal == 0.0) {
            for (int i = 0; i < inputNumbers; i++) {
                weight[i] = weight[i] + lernWektor[lernWektor.length - 1] * lernWektor[i];
            }
        } else {
            for (int i = 0; i < inputNumbers; i++) {
                weight[i] = weight[i] + ((lernWektor[lernWektor.length - 1] - Math.signum(outputSignalSuma)) / 2) * lernWektor[i];
            }
        }
    }

    @Override
    public double odpowiedzNeuronuNaSygnal(double[] wektorIN) {
        ustawWejscia(wektorIN);
        return outputSignal;
    }

    private static int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    private void zerujWagi() {
        for (int i = 0; i < inputNumbers; i++) {
            weight[i] = 0;
        }
    }

    @Override
    public String toString() {
        return "Perceptron{" + "weight=" + Arrays.toString(weight);
    }

    public void drawNeuron() {
        if (inputNumbers == 3) {

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JFrame frame = new JFrame();
                    frame.setSize(400, 400);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    JPanel panel = new PanelNeuron(weight[0], weight[1], weight[2]);
                    panel.setSize(400, 400);
                    frame.getContentPane().add(panel);
                    frame.pack();
                    frame.setVisible(true);
                }
            });
        }
    }
}
