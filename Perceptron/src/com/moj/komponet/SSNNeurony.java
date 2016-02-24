package com.moj.komponet;

import java.util.Random;

public class SSNNeurony {

    private double[] sygnalyWejsciowedoOutNeuron;

    private int iloscsygnalowwejsciowych, iloscsygnalowwejsciowych2, iloscsygnalowwejsciowych3;

    private NeuronSimple[] liczbaNeuronowUkrytych, liczbaneuronowWyjsciowych;

    private int iteracjebackpropagation = 100000;

    private double[] wektorTemp;
    private double[] tablicawspolczynikowwyjsciowych;
    private double[] wektorwagdlaKazdegopolaczenia;

    public SSNNeurony(int inNeuron, int NeuronHidde, int NeuronOut) {
        iloscsygnalowwejsciowych = inNeuron;
        iloscsygnalowwejsciowych2 = NeuronHidde;
        iloscsygnalowwejsciowych3 = NeuronOut;
        sygnalyWejsciowedoOutNeuron = new double[iloscsygnalowwejsciowych2];
        liczbaNeuronowUkrytych = new NeuronSimple[NeuronHidde];
        liczbaneuronowWyjsciowych = new NeuronSimple[NeuronOut];

        for (int i = 0; i < NeuronHidde; i++) {
            liczbaNeuronowUkrytych[i] = new NeuronSimple(iloscsygnalowwejsciowych);
        }
        for (int i = 0; i < NeuronOut; i++) {
            liczbaneuronowWyjsciowych[i] = new NeuronSimple(NeuronHidde);
        }
        wektorTemp = new double[iloscsygnalowwejsciowych];
        tablicawspolczynikowwyjsciowych = new double[iloscsygnalowwejsciowych3];
        wektorwagdlaKazdegopolaczenia = new double[iloscsygnalowwejsciowych3];
    }

    public void ustawWektoryTrenujacae(int neuron, double[][] tablicaTrenujaca, String znak) {

        if (neuron <= iloscsygnalowwejsciowych3) {
            liczbaneuronowWyjsciowych[neuron].znak = znak;
            int iloscwektor = tablicaTrenujaca.length;
            int wiellosc = tablicaTrenujaca[0].length;
            System.out.println(iloscwektor + " " + wiellosc);
            liczbaneuronowWyjsciowych[neuron].tablicawektorowtrenujacych = new double[iloscwektor][wiellosc];
			
            for (int i = 0; i < iloscwektor; i++) {
                for (int j = 0; j < wiellosc; j++) {
                    liczbaneuronowWyjsciowych[neuron].tablicawektorowtrenujacych[i][j] = tablicaTrenujaca[i][j];

                }
            }
        }

    }

    public void ustawWektoryOdpowiedzi(int ktotrNeuron, double[][] tablicaOdpowiedzi) {

        if (ktotrNeuron <= iloscsygnalowwejsciowych3) {
            liczbaneuronowWyjsciowych[ktotrNeuron].tablicaOdpowiedzi = new double[tablicaOdpowiedzi.length][tablicaOdpowiedzi[0].length];
            for (int i = 0; i < tablicaOdpowiedzi.length; i++) {
                for (int j = 0; j < tablicaOdpowiedzi[0].length; j++) {
                    liczbaneuronowWyjsciowych[ktotrNeuron].tablicaOdpowiedzi[i][j] = tablicaOdpowiedzi[i][j];
                    // System.out.println( liczbaneuronowWyjsciowych[ktotrNeuron].tablicaOdpowiedzi[i][j]);
                }
            }
        }
    }

    public void wyswietlwejsciaIsygnalyWyjsciowe() {
        for (int i = 0; i < 16; i++) {
            System.out.println(liczbaneuronowWyjsciowych[0].tablicawektorowtrenujacych[i][0] + " " + liczbaneuronowWyjsciowych[0].tablicawektorowtrenujacych[i][1] + " " + liczbaneuronowWyjsciowych[0].tablicaOdpowiedzi[i][0]);
        }
    }

    public void backPropagation() {
        System.out.println("Trenowanie");
        int iteracjetemp = 0;
        int selectNeuron = 0;
        int selectWektor = 0;
        System.out.println(" x " + iloscsygnalowwejsciowych3);
        while (iteracjetemp < iteracjebackpropagation) {
            iteracjetemp++;
            selectNeuron = randInt(0, iloscsygnalowwejsciowych3 - 1);
            selectWektor = 0;//randInt(0,  (liczbaneuronowWyjsciowych[selectNeuron].tablicawektorowtrenujacych.length)-1);
            for (int i = 0; i < iloscsygnalowwejsciowych; i++) {
                wektorTemp[i] = liczbaneuronowWyjsciowych[selectNeuron].tablicawektorowtrenujacych[selectWektor][i];
            }
            //	System.out.println("WEK="+ selectWektor+"   N "+selectNeuron);
            trenuj(selectWektor, wektorTemp, selectNeuron);
        }

        System.out.println("Koniec Trenowania iter " + iteracjetemp);
    }

    private boolean trenuj(int ktorywektorTrenujacy, double[] wektorwejsciowy, int ktoryneuron) {
        if (ktoryneuron < iloscsygnalowwejsciowych3) {

            /**
             * Zakodowanie znaku
             */
            trenujwPrzodwarstwy(wektorwejsciowy);
            liczwspolczyniki(ktorywektorTrenujacy, ktoryneuron);
            //	System.out.println(ktorywektorTrenujacy+" "+ ktoryneuron);
            return true;
        }
        return false;
    }

    private void trenujwPrzodwarstwy(double[] wektorwejsciowy) {
        /**
         * obliczamy co bedzie na warstwie wejsciowej
         */
        for (int i = 0; i < iloscsygnalowwejsciowych2; i++) {
            liczbaNeuronowUkrytych[i].setWejscia(wektorwejsciowy);
            liczbaNeuronowUkrytych[i].trenujwprzod();
            sygnalyWejsciowedoOutNeuron[i] = liczbaNeuronowUkrytych[i].activationsignal;
        }
        /**
         * Pobieramy sumy i otrzymamy wekrot wwejsciowy dla nastepnej warstwy
         */
        for (int i = 0; i < iloscsygnalowwejsciowych3; i++) {
            liczbaneuronowWyjsciowych[i].setWejscia(sygnalyWejsciowedoOutNeuron);
            liczbaneuronowWyjsciowych[i].trenujwprzod();
        }
    }

    private void liczwspolczyniki(int ktoryWektorodpowiedzi, int ktoryNeuron) { 										// wspolocznnkDlaWyjsciowejO=(oczekiwana-activateSignalO)*pochodnaactivateSignalO;
//															System.out.println("WEK "+liczbaneuronowWyjsciowych[ktoryNeuron].tablicawektorowtrenujacych[ktoryWektorodpowiedzi][0]+" "+
//																liczbaneuronowWyjsciowych[ktoryNeuron].tablicawektorowtrenujacych[ktoryWektorodpowiedzi][1]+" "+
//																liczbaneuronowWyjsciowych[ktoryNeuron].tablicaOdpowiedzi[ktoryWektorodpowiedzi][0]);

        for (int i = 0; i < iloscsygnalowwejsciowych3; i++) { //System.out.println("i "+i+" ktoryNeuron"+ktoryNeuron);
            liczbaneuronowWyjsciowych[i].wspolczynik = (liczbaneuronowWyjsciowych[ktoryNeuron].tablicaOdpowiedzi[ktoryWektorodpowiedzi][i] - liczbaneuronowWyjsciowych[i].activationsignal) * liczbaneuronowWyjsciowych[i].dactivationsignal;
            tablicawspolczynikowwyjsciowych[i] = liczbaneuronowWyjsciowych[i].wspolczynik;

        }
        //	System.out.println("Wagi uaktualnienie neurony ukryte");
        for (int j = 0; j < iloscsygnalowwejsciowych2; j++) {
            double tempwspolczynik = 0;
            for (int i = 0; i < iloscsygnalowwejsciowych3; i++) {
                wektorwagdlaKazdegopolaczenia[i] = liczbaneuronowWyjsciowych[i].wagisygnalow[j + 1]; 		//wagisygnalow
                tempwspolczynik += tablicawspolczynikowwyjsciowych[i] * wektorwagdlaKazdegopolaczenia[i];
            }
            liczbaNeuronowUkrytych[j].wspolczynik = tempwspolczynik * liczbaNeuronowUkrytych[j].dactivationsignal;
        }
        for (int i = 0; i < iloscsygnalowwejsciowych3; i++) {
            liczbaneuronowWyjsciowych[i].uaktualnijwagi();
        }
        for (int j = 0; j < iloscsygnalowwejsciowych2; j++) {
            liczbaNeuronowUkrytych[j].uaktualnijwagi();
        }
    }

    public boolean odpowiedzSprawdzenieTrenowania(double[] wektorwejsciowy, String znak) {
        trenujwPrzodwarstwy(wektorwejsciowy);
        int index = 0;
        double max = liczbaneuronowWyjsciowych[0].activationsignal;
        for (int i = 1; i < iloscsygnalowwejsciowych3; i++) {
            if (liczbaneuronowWyjsciowych[i].activationsignal > max) {
                index = i;
                max = liczbaneuronowWyjsciowych[i].activationsignal;
            }
        }
        if (liczbaneuronowWyjsciowych[index].znak == znak) {
            return true;
        } else {
            return false;
        }
    }

    public double odpowiedz(double[] wektorwejsciowy) {
        trenujwPrzodwarstwy(wektorwejsciowy);
        double max = liczbaneuronowWyjsciowych[0].activationsignal;
        int index = 0;
        for (int i = 0; i < iloscsygnalowwejsciowych3; i++) {
            System.out.println("Warstwa " + i + " Znak" + liczbaneuronowWyjsciowych[i].znak + " F a" + liczbaneuronowWyjsciowych[i].activationsignal);
            if (liczbaneuronowWyjsciowych[i].activationsignal > max) {

                max = liczbaneuronowWyjsciowych[i].activationsignal;
                index = i;
            }
        }
        System.out.println("ZNAK MAX " + liczbaneuronowWyjsciowych[index].znak);
        return max;
    }

    public String odpowiedzZnak(double[] wektorwejsciowy) {
        trenujwPrzodwarstwy(wektorwejsciowy);
        double max = liczbaneuronowWyjsciowych[0].activationsignal;
        int index = 0;
        for (int i = 0; i < iloscsygnalowwejsciowych3; i++) {
            System.out.println("Warstwa " + i + " Znak" + liczbaneuronowWyjsciowych[i].znak + " F a" + liczbaneuronowWyjsciowych[i].activationsignal);
            if (liczbaneuronowWyjsciowych[i].activationsignal > max) {

                max = liczbaneuronowWyjsciowych[i].activationsignal;
                index = i;
            }
        }

        return liczbaneuronowWyjsciowych[index].znak;
    }

    public void wyswietlLinieklasyfikacyjne() {
        for (int i = 0; i < iloscsygnalowwejsciowych2; i++) {
            double alfa = -1 * (liczbaNeuronowUkrytych[i].wagisygnalow[1] / liczbaNeuronowUkrytych[i].wagisygnalow[2]);
            double beta = -1 * (liczbaNeuronowUkrytych[i].wagisygnalow[0] / liczbaNeuronowUkrytych[i].wagisygnalow[2]);
            String opis = new String();
            opis = alfa + " x " + beta;
            System.out.println("Linia neuronu i" + opis);
        }
    }

    private static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public int getIteracjebackpropagation() {
        return iteracjebackpropagation;
    }

    public void setIteracjebackpropagation(int iteracjebackpropagation) {
        this.iteracjebackpropagation = iteracjebackpropagation;
    }

    private class NeuronSimple {

        /**
         * Podstawowa wersja
         */
        private double[] wejsciaSygnalow;
        private double[] wagisygnalow;
        private double activationsignal;
        private double suma;
        private int size;
        private double dactivationsignal;
        private double wspolczynik;
        protected double wspolczynikuczenia = 0.2;
        private String znak = "Pusto";
        /**
         * Do metody momentum*
         */
        private double alfaMomentum = 0.8;
        private double[] wagisygnalowmomentum;
        private boolean momentumOn = true;

        private double[][] tablicawektorowtrenujacych = null;
        private double[][] tablicaOdpowiedzi = null;

        private NeuronSimple(int size) {
            this.size = size + 1;
            wejsciaSygnalow = new double[this.size];
            wagisygnalow = new double[this.size];
            wejsciaSygnalow[0] = 1;
            activationsignal = 0;
            if (momentumOn == true) {
                wagisygnalowmomentum = new double[this.size];
            }
            init();
        }

        private void init() {
            losujwagi();
        }

        private void trenujwprzod() {
            sumuj();
            liczfunkcjaaktywacji();
            liczpochodnaaktywacji();
        }

        private void setWejscia(double[] tab) {
            wejsciaSygnalow[0] = 1;
            for (int i = 1; i < size; i++) {
                wejsciaSygnalow[i] = tab[i - 1];
            }
        }

        private void losujwagi() {
            Random generator = new Random();
            for (int i = 0; i < size; i++) {
                wagisygnalow[i] = generator.nextGaussian();
                if (momentumOn) {
                    wagisygnalowmomentum[i] = wagisygnalow[i];
                }
            }
        }

        private void sumuj() {
            this.suma = 0;
            for (int i = 0; i < size; i++) {
                this.suma += wejsciaSygnalow[i] * wagisygnalow[i];
            }
        }

        private void liczfunkcjaaktywacji() {
            // activationsignal= (1-Math.pow(Math.E,-suma))/(1+Math.pow(Math.E,-suma)); 
            activationsignal = (1 / (1 + Math.pow(Math.E, -suma)));
        }

        private void liczpochodnaaktywacji() {
            // dactivationsignal=0.5*(1-activationsignal*activationsignal); 
            dactivationsignal = activationsignal * (1 - activationsignal);
        }

        private void uaktualnijwagi() {
            for (int i = 0; i < size; i++) {
                wagisygnalow[i] = wagisygnalow[i] + wspolczynikuczenia * wspolczynik * wejsciaSygnalow[i];
                if (momentumOn) {
                    wagisygnalowmomentum[i] = wagisygnalow[i] - wagisygnalowmomentum[i];
                    wagisygnalow[i] = wagisygnalow[i] + wagisygnalowmomentum[i] * alfaMomentum;
                    wagisygnalowmomentum[i] = wagisygnalow[i];
                }
            }
        }
    }
}

/**
 * Wykorzytsanie neuronow tak ze ilosc neuronow ukrytych jest taka sama jak
 * wyjsciowych przykladowe uzycie ssN=new SSNNeurony(skalaY*skalX,13,2); 1
 * wejscia ,n-ukryte,n wyjsciowe
 * ssN.trenuj(znakzakoduj.getText(),1,tablicanauki); tenowanie wektorem 1 jaki
 * znak oczkiwana i wektor odpowiedzialny mozna trenowac ta samam litere kilka
 * razy ssN.odpowiedzQuery(tablicasprawdzenia) odpowiedz sieci w postaci
 * rozpoznanej litery
 *
 */
/**
 *
 * public double odpowiedz2(double [] wektorwejsciowy){ for(int
 * i=0;i<iloscsygnalowwejsciowych2;i++){
 * liczaNeuronowUkrytyczk[i].setWejscia(wektorwejsciowy);
 * liczaNeuronowUkrytyczk[i].trenujwprzod(); }
 *
 * for(int i=0;i< iloscsygnalowwejsciowych2;i++){
 * sygnalyWejsciowedoOutNeuron[i]= liczaNeuronowUkrytyczk[i].activationsignal;}
 * double min = 0, max; for(int i=0;i< iloscsygnalowwejsciowych3;i++){
 * liczbaneuronowWyjsciowych[i].setWejscia(sygnalyWejsciowedoOutNeuron);
 * liczbaneuronowWyjsciowych[i].trenujwprzod();
 * max=liczbaneuronowWyjsciowych[i].activationsignal;
 * if(i==0) { min=max; }
 * else if(i>0 && max>min){ min=max; } } return min; }
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * public void wyswietlsiec(){	for(int j=0;j< iloscsygnalowwejsciowych2;j++){
 * for(int i=0;i<=iloscsygnalowwejsciowych;i++){ System.out.println("waga
 * "+liczaNeuronowUkrytyczk[j].wagisygnalow[i]);} System.out.println("Neurony
 * ukryte "+j);} for(int j=0;j< iloscsygnalowwejsciowych3;j++){ for(int i=0;i<=iloscsygnalowwejsciowych2;i++){
 * System.out.println("waga "+ liczbaneuronowWyjsciowych[j].wagisygnalow[i]); }
 * System.out.println("Neurony wyjsciowe "+j);} }
 *
 *
 *
 *
 *
 *
 *
 *
 * private void sprawdzpoprzedniki(){
 * if(countznaki>0){ int savecountznak=countznaki; countznaki=0;
 * //tablicawektorowtrenujacych; tempdoPrzeksztalcenia for(int
 * i=0;i<=savecountznak;i++){ for(int k=0;k< iloscsygnalowwejsciowych;k++)
 * tempdoPrzeksztalcenia[k]=tablicawektorowtrenujacych[i][k];
 * trenujSimple(liczbaneuronowWyjsciowych[i].znak,1,tempdoPrzeksztalcenia);
 * countznaki++; } countznaki=savecountznak; } } private void
 * trenujSimple(String znak,double oczekiwana,double [] wektorwejsciowy){
 * liczaNeuronowUkrytyczk[countznaki].setWejscia(wektorwejsciowy);
 * while(!odpowiedz(wektorwejsciowy,znak)){ //	System.out.println("Trenuje znak
 * "+	liczaNeuronowUkrytyczk[countznaki].znak+"i="+countznaki);
 * trenujwPrzodwarstwy(); liczwspolczyniki(oczekiwana); } }
 *
 * public String odpowiedzQuery(double [] wektorwejsciowy){ for(int
 * i=0;i<iloscsygnalowwejsciowych2;i++){
 * liczaNeuronowUkrytyczk[i].setWejscia(wektorwejsciowy);
 * liczaNeuronowUkrytyczk[i].sumuj();
 * liczaNeuronowUkrytyczk[i].liczfunkcjaaktywacji();} for(int i=0;i<
 * iloscsygnalowwejsciowych2;i++){ sygnalyWejsciowedoOutNeuron[i]=
 * liczaNeuronowUkrytyczk[i].activationsignal; } double [] maxvalue= new
 * double[iloscsygnalowwejsciowych3]; for(int i=0;i<
 * iloscsygnalowwejsciowych3;i++){
 * liczbaneuronowWyjsciowych[i].setWejscia(sygnalyWejsciowedoOutNeuron);
 * liczbaneuronowWyjsciowych[i].sumuj();
 * liczbaneuronowWyjsciowych[i].liczfunkcjaaktywacji();
 * maxvalue[i]=liczbaneuronowWyjsciowych[i].activationsignal; } double
 * max=maxvalue[0]; int index=0; for(int i=0;i<iloscsygnalowwejsciowych3;i++){
 * System.out.println("Funkcje aktywacji"+liczbaneuronowWyjsciowych[i].znak+" Aktywacja "+ liczbaneuronowWyjsciowych[i].activationsignal );
 * if(maxvalue[i]> max){ max=maxvalue[i]; index=i;} } System.out.println("Znak
 * Podobny do"+liczbaneuronowWyjsciowych[index].znak+" Aktywacja "+
 * liczbaneuronowWyjsciowych[index].activationsignal ); return
 * liczbaneuronowWyjsciowych[index].znak; }
 */
/**
 * do{ trenujwPrzodwarstwy();
 * delta=oczekiwana-Math.abs(liczbaneuronowWyjsciowych[countznaki].activationsignal);
 * delta=Math.abs(delta);
 * System.out.println(liczbaneuronowWyjsciowych[countznaki].activationsignal);
 * if(oczekiwana>liczbaneuronowWyjsciowych[countznaki].activationsignal)
 *
 * liczwspolczyniki(oczekiwana);
 * delta=oczekiwana-Math.abs(liczbaneuronowWyjsciowych[countznaki].activationsignal);
 * //	System.out.println("Blad "+
 * (oczekiwana-Math.abs(liczbaneuronowWyjsciowych[countznaki].activationsignal)));
 * //	System.out.println("aktywacja
 * "+liczbaneuronowWyjsciowych[countznaki].activationsignal);
 * //odpowiedz(wektorwejsciowy,liczbaneuronowWyjsciowych[countznaki].znak);
 * }while(!(liczbaneuronowWyjsciowych[countznaki].activationsignal>=oczekiwana));//while(delta>blad);
 * //while(!odpowiedz(wektorwejsciowy,liczbaneuronowWyjsciowych[countznaki].znak));
 * } System.out.println(liczbaneuronowWyjsciowych[countznaki].activationsignal);
 * return true;
 */
/***Klas przykladowe uzycie konstrukto neurony wejsciowe ,ile ukrtytch i wysjacia 
 * kazdy wektor koncowy moze miesc swoja tablice nauki  trenowania przydatne przy ttrnowaniu do ocr
 * ustawaimy tablic wejsciowe i wyjsciowe dla kazdego neuronu wyjscowego i to wszystko 
 * trenuje bck propagation i pozniej  mamy gotowa siec
 * 
		SSNNeurony neruron2= new SSNNeurony(2,1,1);
		double[][] tablicaFF=new double[100][2];			// wektory trenujace 2 zmiene to 2 argumenty
		double[][] tablicODp= new double[100][1];		// odpowiedzi bo jden neuron koncowy
		double x,y,z;
		int count=0;
		for(int i=0;i<10;i++){
			y=((double)i-(double)10)/(double)10;
			for(int j=0;j<10;j++){
				x=((double)j-(double)10)/(double)10;
				tablicaFF[count][0]=x;
				tablicaFF[count][1]=y;
				z=(x*-y);
				tablicODp[count][0]=z;
				count++;
			}
		}
		neruron2.ustawWektoryTrenujacae(1,tablicaFF,"x*x-y*y");
		neruron2.ustawWektoryOdpowiedzi(1,tablicODp);
		neruron2.backPropagation();
		
		
		System.out.println("Po trenowaniu bledy ");
		double [] temp2= new double[2];
		for(int i=0;i<10;i++){
			y=(double)((double)i-(double)10)/(double)10;
			for(int j=0;j<10;j++){
				x=(double)(j-(double)10)/(double)10;
				z=(x*-y);
				
				temp2[0]=x;
				temp2[1]=y;
				
				System.out.println(Math.abs(z-neruron2.odpowiedz(temp2)));
			}
		}
		*/
