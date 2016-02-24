/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moj.komponet;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import java.io.Serializable;
import javax.swing.*;
import com.moj.komponet.SSNNeurony;

/**
 *
 * @author Yogi
 */
public class PanelWektoryzacja extends JPanel implements Serializable {

    private int skalX = 4;
    private int skalaY = 5;
    int wektor = 0;
    private static PanelWektoryzacja panelRysowania;
    private JLabel opis;

    public JLabel getOpis() {
        return opis;
    }

    public void setOpis(JLabel opis) {
        this.opis = opis;
    }

    public MouseListener listner = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent arg0) {
            System.out.println("mouseClicked");
            if (arg0.getButton() == MouseEvent.BUTTON1) {
                // TODO Auto-generated method stub
                int x = arg0.getX();
                int y = arg0.getY();
                Graphics g = panelRysowania.getGraphics();
                g.setColor(Color.BLUE);
                drawline();
                int awidth = panelRysowania.getWidth();
                int aheight = panelRysowania.getHeight();
                if (x <= awidth && y <= aheight) {
                    int stepx = awidth / skalX;
                    int stepy = aheight / skalaY;
                    x = x / stepx;
                    y = y / stepy;
                    g.fillRect(x * stepx, y * stepy, stepx, stepy);
                    if ((x < awidth && y < aheight) && (x >= 0) && (y >= 0)) {
                        int wsx = x;
                        int wsy = y;
                        System.out.println(wsx + " " + wsy);
                        tabUczace[wsy][wsx] = 1;
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent arg0) {
            System.out.println("mousePressed");
            if (arg0.getButton() == MouseEvent.BUTTON3) {
                for (int j = 0; j < skalaY; j++) {
                    for (int i = 0; i < skalX; i++) {
                        tabUczace[j][i] = 0;
                    }
                }
                panelRysowania.repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("mouseReleased");
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            System.out.println("mouseEntered");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            System.out.println("mouseExited");
        }
    };

    public MouseMotionAdapter motionlistner = new MouseMotionAdapter() {
        @Override
        public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub

            int x = arg0.getX();
            int y = arg0.getY();

            Graphics g = panelRysowania.getGraphics();
            g.setColor(Color.BLUE);
            drawline();
            int awidth = panelRysowania.getWidth();
            int aheight = panelRysowania.getHeight();
            if (x < awidth && y < aheight) {
                int stepx = awidth / skalX;
                int stepy = aheight / skalaY;

                x = x / stepx;
                y = y / stepy;

                g.fillRect(x * stepx, y * stepy, stepx, stepy);

                if ((x < awidth && y < aheight) && (x >= 0) && (y >= 0)) {
                    int wsx = x;
                    int wsy = y;
                    tabUczace[wsy][wsx] = 1;
                }
            }
        }

    };

    private int[][] tabUczace = new int[skalaY][skalX];

    private SSNNeurony neruron2;

    private double[][] wekttre0 = {{1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1}};
    private double[][] odpowiedz0 = {{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    private double[][] wekttre1 = {{0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1}};
    private double[][] odpowiedz1 = {{0, 1, 0, 0, 0, 0, 0, 0, 0, 0}};

    private double[][] wekttre2 = {{1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1}};
    private double[][] odpowiedz2 = {{0, 0, 1, 0, 0, 0, 0, 0, 0, 0}};

    private double[][] wekttre3 = {{1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1}};
    private double[][] odpowiedz3 = {{0, 0, 0, 1, 0, 0, 0, 0, 0, 0}};

    private double[][] wekttre4 = {{1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1}};
    private double[][] odpowiedz4 = {{0, 0, 0, 0, 1, 0, 0, 0, 0, 0}};
     //5 1,1,1,1,1,0,0,1,1,1,1,1,0,0,0,1,1,1,1,1

    private double[][] wekttre5 = {{1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1}};
    private double[][] odpowiedz5 = {{0, 0, 0, 0, 0, 1, 0, 0, 0, 0}};

    //6 1,1,1,1,1,0,0,0,1,1,1,1,1,0,0,1,1,1,1,1
    private double[][] wekttre6 = {{1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1}};
    private double[][] odpowiedz6 = {{0, 0, 0, 0, 0, 0, 1, 0, 0, 0}};

    //7  1 private double[][] wekttre5={{1,0,0,1,1,0,0,1,1,1,1,1,0,0,0,1,0,0,0,1}};,1,1,1,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0
    private double[][] wekttre7 = {{1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0}};
    private double[][] odpowiedz7 = {{0, 0, 0, 0, 0, 0, 0, 1, 0, 0}};

    //8  1,1,1,1,1,0,0,1,1,1,1,1,1,1,0,1,1,1,1,1
    private double[][] wekttre8 = {{1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1}};
    private double[][] odpowiedz8 = {{0, 0, 0, 0, 0, 0, 0, 0, 1, 0}};

    //9   1,1,1,1,1,0,0,1,1,1,1,1,1,1,0,1,1,1,1,1
    private double[][] wekttre9 = {{1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1}};
    private double[][] odpowiedz9 = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 1}};

    public PanelWektoryzacja() {
        super();
        panelRysowania = this;
        neruron2 = new SSNNeurony(skalX * skalaY, 14, 10);
        addMouseListener(listner);
        addMouseMotionListener(motionlistner);
        for (int j = 0; j < skalaY; j++) {
            for (int i = 0; i < skalX; i++) {
                tabUczace[j][i] = 0;
            }
        }

    }

    public int getIteracjebackpropagation() {
        return neruron2.getIteracjebackpropagation();
    }

    public void setIteracjebackpropagation(int iteracjebackpropagation) {
        neruron2.setIteracjebackpropagation(iteracjebackpropagation);
    }

    public void trenujWarstwy() {
        // neruron2.backPropagation(); 
        neruron2.ustawWektoryTrenujacae(0, wekttre0, "0");
        neruron2.ustawWektoryOdpowiedzi(0, odpowiedz0);

        neruron2.ustawWektoryTrenujacae(1, wekttre1, "1");
        neruron2.ustawWektoryOdpowiedzi(1, odpowiedz1);

        neruron2.ustawWektoryTrenujacae(2, wekttre2, "2");
        neruron2.ustawWektoryOdpowiedzi(2, odpowiedz2);

        neruron2.ustawWektoryTrenujacae(3, wekttre3, "3");
        neruron2.ustawWektoryOdpowiedzi(3, odpowiedz3);

        neruron2.ustawWektoryTrenujacae(4, wekttre4, "4");
        neruron2.ustawWektoryOdpowiedzi(4, odpowiedz4);

        neruron2.ustawWektoryTrenujacae(5, wekttre5, "5");
        neruron2.ustawWektoryOdpowiedzi(5, odpowiedz5);

        neruron2.ustawWektoryTrenujacae(6, wekttre6, "6");
        neruron2.ustawWektoryOdpowiedzi(6, odpowiedz6);

        neruron2.ustawWektoryTrenujacae(7, wekttre7, "7");
        neruron2.ustawWektoryOdpowiedzi(7, odpowiedz7);

        neruron2.ustawWektoryTrenujacae(8, wekttre8, "8");
        neruron2.ustawWektoryOdpowiedzi(8, odpowiedz8);

        neruron2.ustawWektoryTrenujacae(9, wekttre9, "9");
        neruron2.ustawWektoryOdpowiedzi(9, odpowiedz9);
        this.opis.setText("Trenowanie");
        neruron2.backPropagation();
        this.opis.setText("Koniec trenowania");
    }

    public String sprawdzZnak() {
        double[] tablicasprawdzenia = new double[skalaY * skalX];
        int temo = 0;
        for (int j = 0; j < skalaY; j++) {
            for (int i = 0; i < skalX; i++) {
                tablicasprawdzenia[temo] = tabUczace[j][i];
                temo++;
                System.out.print(tabUczace[j][i] + ",");

            }

        }

        String znak = neruron2.odpowiedzZnak(tablicasprawdzenia);

        return znak;

    }

    @Override
    public void paint(Graphics g) {

        int awidth = getWidth() - 1;
        int aheight = getHeight() - 1;
        int stepx = awidth / skalX;
        int stepy = aheight / skalaY;
        g.setColor(Color.RED);
        for (int i = 0; i <= skalaY; i++) {
            g.drawLine(stepx * i, 0, stepx * i, aheight - 2);
        }

        for (int i = 0; i <= skalX; i++) {
            g.drawLine(0, stepy * i, awidth - 2, stepy * i);
        }
              // drawline();

    }

    public void drawline() {

        int awidth = getWidth() - 1;
        int aheight = getHeight() - 1;

        int stepx = awidth / skalX;
        int stepy = aheight / skalaY;
        Graphics g = this.getGraphics();
        g.setColor(Color.RED);
        for (int i = 0; i <= skalaY; i++) {
            g.drawLine(stepx * i, 0, stepx * i, aheight - 2);
        }

        for (int i = 0; i <= skalX; i++) {
            g.drawLine(0, stepy * i - 1, awidth - 2, stepy * i - 1);
        }

    }

}
