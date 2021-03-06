/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.my.lin.neuron;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 *
 * @author serv
 */
public class PanelNeuron extends javax.swing.JPanel {

    double k1, k0;
    int scala = 200;

    public PanelNeuron(double w0, double w1, double w2) {
        k1 = w1 / w2;
        k1 = k1 * (-1);
        k0 = w0 / w2;
        k0 = k0 * (-1);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repaint();
            }
        });

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        int x = this.getWidth();
        int y = this.getHeight();
        g2d.drawLine(0, y / 2, x, y / 2);
        g2d.drawLine(x / 2, 0, x / 2, this.getHeight());
        double xp, yp;

        g2d.setColor(Color.RED);
        StringBuilder str = new StringBuilder();
        for (int j = 0; j < y; j++) {
            yp = -(j - y / 2);
            if (yp % scala == 0) {
                int a = (int) yp / scala;
                str.append(a);
                g2d.drawString(str.toString(), x / 2, j);
                str.setLength(0);
            }
        }
        for (int i = 0; i < x; i++) {
            xp = (i - x / 2);
            if (xp % scala == 0) {
                int a = (int) xp / scala;
                str.append(a);
                g2d.drawString(str.toString(), i, y / 2);
                str.setLength(0);
            }
        }
        for (int k = 0; k < x; k++) {
            xp = ((double) k - (double) x / 2) / scala;
            yp = (k1 * xp + k0) * scala;
            yp = ((y / 2) - yp);
            g2d.drawLine(k, (int) yp, k, (int) yp);
        }
    }

    public PanelNeuron() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
