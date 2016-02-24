package com.myapp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Komonet umozliwiajacy ryswanie punktow na plaszczyznie i zaznaczanie punktow
 */
public class MojPanel extends JPanel {

    private ArrayList<Point> punkty = new ArrayList<>();
    private boolean resetValue = false;
    //private final int rozmiarPunkt=5;
    private ArrayList<Integer> sciezka;
    private boolean rysujSciezke = false;
    private Color kolorDrogi;

    public MojPanel() {
        super();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                punkty.add(e.getPoint());
                repaint();
            }

        });
    }

    @Override
    public synchronized void paintComponent(Graphics g) {
        // super.paintAll(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2d = (Graphics2D) g;
        this.setBackground(Color.WHITE);
        super.paintComponent(g);

        if (!resetValue) {

            g2d.setPaint(Color.RED);
            g2d.setStroke(new BasicStroke(5));
            int i = 0;
            for (Point p : punkty) {
                g2d.drawString("" + i, (int) p.getX(), (int) p.getY() - 4);
                i++;
                g2d.drawLine((int) p.getX(), (int) p.getY(), (int) p.getX(), (int) p.getY());
            }
        } else { // wymazanie
            this.setBackground(Color.WHITE);
            super.paintComponent(g);
            setResetValue(false);
        }
        if (rysujSciezke) {
            rysujSciezke = false;
            g2d.setStroke(new BasicStroke(1));
            g2d.setPaint(kolorDrogi);
            Point pointa = null;
            Point pointb = null;
            for (int i = 0; i < sciezka.size() - 1; i++) {
                pointa = punkty.get(sciezka.get(i));

                pointb = punkty.get(sciezka.get(i + 1));

                g2d.drawLine((int) pointa.getX(), (int) pointa.getY(), (int) pointb.getX(), (int) pointb.getY());
            }

        }
    }

    public ArrayList<Point> getPunkty() {
        return punkty;
    }

    public void setPunkty(ArrayList<Point> punkty) {
        this.punkty = punkty;
        repaint();
    }

    public void resetuje() {
        punkty = new ArrayList<>();
        setResetValue(true);
        repaint();

    }

    public boolean isResetValue() {
        return resetValue;
    }

    public void setResetValue(boolean resetValue) {
        this.resetValue = resetValue;
    }

    public void rysujDroge(ArrayList<Integer> sciezka, Color color) {
        this.sciezka = sciezka;
        rysujSciezke = true;
        kolorDrogi = color;
        repaint();
    }

}
