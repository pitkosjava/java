
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class Mandelbrot extends javax.swing.JPanel {

    private int MAX_ITER = 200;
    private double ZOOM = 100;
    private final double ZOOM_INCREMENT = 10;
    private BufferedImage I;

    private final int wspY = 300; // wspolrzedne srodka aktualnego obrazu 
    private final int wspX = 300;
    
    private double x_przesuniecie=0;
    private double y_przesuniecie=0;
    

    private final int width = 600;
    private final int height = 600;

    private final int kolorodcien = 140; // ustawienie koloru prezesuniecia

    // do algorytmu
    private double zx, zy, cX, cY, tmp;

    private boolean ramka;

    public Mandelbrot() {
        setSize(width, height);
        initComponents();
    }

    @Override
    public synchronized void paint(Graphics g) {
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                zx = zy = 0;
                
                cX = ((x - wspX) / ZOOM); // srodek 
                cY = ((y - wspY)/ ZOOM); // srodek

                int iter = MAX_ITER;
                // xy       |
                //  ++      |   -+
                //______________________
                //          |
                //   +-      |    --
                // z2= (zr2- zi2)+ i(2*zr*zi)
                // plus p to z na x= )zr2-zi2+cx_
                // plus p na y = 2 * zr*zi +cy
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    tmp = zx * zx - zy * zy + cX; // czesc rzeczywsita plus p
                    zy = 2.0 * zx * zy + cY; // czesc urojona plus p
                    zx = tmp;
                    iter--;
                }

                I.setRGB(x, y, (iter << kolorodcien));
            }
        }
        g.drawImage(I, 0, 0, this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    public synchronized int getMAX_ITER() {
        return MAX_ITER;
    }

    public synchronized void setMAX_ITER(int MAX_ITER) {
        this.MAX_ITER = MAX_ITER;
    }

    public double getZOOM() {
        return ZOOM;
    }

    public void incrementZoom() {
        setZOOM(getZOOM() + ZOOM_INCREMENT);
        repaint();
        
    }

    public void setZOOM(double ZOOM) {
        this.ZOOM = ZOOM;
    }

}
