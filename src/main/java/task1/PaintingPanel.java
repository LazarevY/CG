package task1;

import common.InverseColor;
import common.NightColor;
import common.RandomRange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PaintingPanel extends JPanel {
    final int WIDTH = 1281, HEIGHT = 698;
    public PaintingPanel(){
        super();
        setBackground(new Color(69, 150, 255));

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F) {
                    repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        RandomRange r = new RandomRange();

        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(2));

        g2.scale((double) super.getWidth()/WIDTH, (double)super.getHeight()/HEIGHT);

        paintCloud(300, 100, 60, g);
        paintCloud(600, 200, 25, g);

        g.setColor(new Color(16, 132, 33));
        g.fillRect(0, HEIGHT /3*2,WIDTH, HEIGHT/3 + 1);

        paintMountain(1050, 100, 150, HEIGHT * 2/3 - 100, 0.65, g2);
        paintMountain(1190, 250, 90, HEIGHT * 2/3 - 250, 0.65, g2);
        //FOREST

        for (int x = 80; x < 1200; x += 200){
            paintTree(x, 300, 32, 50, HEIGHT*2/3, 8, g2);
        }
        for (int x = 180; x < 1200; x += 200){
            paintTree(x, 390, 15, 20, HEIGHT*2/3, 5, g2);
        }

//        paintTree(100,300, 40, 50, HEIGHT*2/3, 9, g2);
//        paintTree(400,300, 30, 50, HEIGHT*2/3, 9, g2);
        paintSun(800, 70, 60, 36, g2);
        for (int i = 0; i < 150; i++) {

            paintChamomile(r.getOfRange(10, 1270), r.getOfRange(600, HEIGHT -10),
                    r.getOfRange(16,21), g2);
        }
        paintChamomile(200, 600, 20 , g2);

        g.setColor(new Color(0,0,0));


        paintEagle(400, 100, 120, 40, 60, 4, g2);
        paintEagle(1000, 200, 120, 40, 60, 4, g2);
        paintEagle(200, 200, 80, 20, 50, 3, g2);
        paintEagle(500, 200, 120, 20, 40, 2, g2);
        paintEagle(800, 200, 80, 20, 50, 2, g2);


        //g2.fillOval(1000, 570, 35, 25);

    }

    void paintTree(int x, int y, int halfWidth, int heigth, int rootY, int rootHWidth, Graphics2D g){
        Color save = g.getColor();
        RandomRange r = new RandomRange();

        g.setColor(new Color(150, 75,0));
        g.fillRect(x - rootHWidth,y , rootHWidth * 2, rootY - y);
        g.setColor(new Color(0, 255, 0));
        fillCircle(x, y, halfWidth*3,g);
        fillCircle(x + halfWidth, y + heigth, halfWidth*3,g);
        fillCircle(x - halfWidth, y + heigth, halfWidth*3,g);
        fillCircle(x, y + heigth, halfWidth*3,g);
        g.setColor(save);
    }

    void fillCircle(int x, int y, int radius, Graphics g){
        g.fillOval(x - radius/2, y - radius/2, radius, radius);
    }


    void paintSun(int x, int y, int radius, int rayAngle, Graphics2D g){
        Color save = g.getColor();
        g.setColor(new Color(242, 228, 62));
        fillCircle(x,y,radius,g);

        for(int angle = 0; angle < 360; angle += rayAngle){
            double v_x = Math.cos(Math.toRadians(angle));
            double v_y = Math.sin(Math.toRadians(angle));
            int b_x = (int) (0.8 *v_x * radius);
            int b_y = (int) (0.8 *v_y * radius);
            g.drawLine(x + b_x, y + b_y, x + b_x + (int)(1/3.0 * radius * v_x),
                    y + b_y + (int)(1/3.0 * radius * v_y));
        }
        g.setColor(save);


    }

    void paintMountain(int x, int y, int halfWidth, int height, double relate, Graphics g){
        Color save = g.getColor();
        g.setColor(new Color(150, 150, 150));
        int[] ys = {y, y + height, y + height};
        g.fillPolygon(new int[]{x, x - halfWidth, x + halfWidth},
                ys, 3);
        g.setColor(new Color(75, 75, 75));
        g.fillPolygon(new int[]{x, (int) (x - halfWidth + halfWidth * relate * 2), x + halfWidth },
                ys, 3);
        g.setColor(save);
    }

    void paintCloud(int x, int y, int radiusLim, Graphics g){
        Color save = g.getColor();

        g.setColor(new Color(240,240,240));
        fillCircle(x,y, radiusLim, g);
        fillCircle((int) (x - radiusLim * 0.6),y, (int) (radiusLim * 0.8), g);
        fillCircle((int) (x + radiusLim * 0.6),y, (int) (radiusLim * 0.8), g);

        g.setColor(save);
    }
    void paintCloud(int x, int y, int width, int height, Graphics g){

    }

    void paintChamomile(int x, int y, int coreRadius, Graphics2D g){
        Color save = g.getColor();
        g.setColor(new Color(255,255,255));
        for (int a = 0; a < 360; a+= 60) {
            double v_x = Math.cos(Math.toRadians(a));
            double v_y = Math.sin(Math.toRadians(a));
            fillCircle((int)(x + v_x*coreRadius/2), (int)(y + v_y * coreRadius/2), (int) (coreRadius*0.7), g);
        }
        g.setColor(new Color(217, 190, 44));
        fillCircle(x, y, coreRadius, g);

        g.setColor(save);
    }

    void paintEagle(int x, int y, int width, int height, int angleLength, int stroke, Graphics2D g){
        g.setStroke(new BasicStroke(stroke));
        g.drawArc(x, y - height/2, width, height, 180, -angleLength);
        g.drawArc(x - width, y - height/2, width, height, 0, angleLength);
    }

}
