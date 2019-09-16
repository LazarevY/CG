package task2;


import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    public Panel() {
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < 200; i++) {
            drawPixel(i, 20, g);
        }
        g.setColor(Color.red);
        fillBresenhamCircle(300, 500, 40, g);
        //BresenhamCircle(300, 500, 40, g);
        BresenhamLine(200,400, 200, 60, g);
        BresenhamLine(400,500, 60, 600, g);
        fillBresenhamOval(500, 500, 30, 100, g);
    }

    private void drawPixel(int x, int y, Graphics g){
        g.fillRect(x,y,1,1);
    }
    void BresenhamCircle(int x0, int y0, int radius, Graphics g)
    {
        int x = 0;
        int y = radius;
        int delta = 2 - 2* radius;
        int err = 0;
        while (y >= 0) {
            drawPixel(x0 + x, y0 + y, g);
            drawPixel(x0 + x, y0 - y, g);
            drawPixel(x0 - x, y0 + y, g);
            drawPixel(x0 - x, y0 - y, g);
            err = 2 * (delta + y) - 1;
            if(delta < 0 && err < 0){
                delta += 2 * ++x + 1;
            }
            else if(delta > 0 && err > 0){
                delta -= 2* --y + 1;
            }
            else {
                delta += 2 * (++x - y--);
            }
        }
    }

    void bresenhamOval(int x0, int y0, int hWidth, int hHeight, Graphics g){

        if(hWidth == hHeight){
            BresenhamCircle(x0, y0, hHeight, g);
            return;
        }

        int x = 0;
        int y = hHeight;
        int a_sqr = hWidth * hWidth;
        int b_sqr = hHeight * hHeight;
        int delta = 4 * b_sqr * ((x + 1) * (x + 1)) +
                a_sqr*((2* y - 1) * (2 * y - 1)) - 4 * a_sqr * b_sqr;

        while (a_sqr * (2 * y - 1) > 2 * b_sqr * (x + 1)) {
            drawPixel(x0 + x, y0 + y, g);
            drawPixel(x0 - x, y0 + y, g);
            drawPixel(x0 + x, y0 - y, g);
            drawPixel(x0 - x, y0 - y, g);

            if(delta < 0)
                delta += 4 * b_sqr * (2 * ++x + 3);
            else
                delta = delta - 8 * a_sqr * (y-- - 1) +
                        4 * b_sqr * (2 * ++x - 3);
        }
        delta = b_sqr * ((2 * x + 1) * (2 * x + 1)) +
                4 *a_sqr*((y + 1) * (y + 1)) - 4 * a_sqr * b_sqr;
        while (y >= 0){
            drawPixel(x0 + x, y0 + y, g);
            drawPixel(x0 - x, y0 + y, g);
            drawPixel(x0 + x, y0 - y, g);
            drawPixel(x0 - x, y0 - y, g);

            if(delta < 0)
                delta += 4 * a_sqr * (2 * --y + 3);
            else
                delta = delta - 8 * b_sqr * (x++ + 1) +
                        4 * a_sqr * (2 * --y + 3);
        }
    }

    void fillBresenhamOval(int x0, int y0, int hWidth, int hHeight, Graphics g){
        if(hWidth == hHeight){
            fillBresenhamCircle(x0, y0, hHeight, g);
            return;
        }
        int x = 0;
        int y = hHeight;
        int a_sqr = hWidth * hWidth;
        int b_sqr = hHeight * hHeight;
        int delta = 4 * b_sqr * ((x + 1) * (x + 1)) +
                a_sqr*((2* y - 1) * (2 * y - 1)) - 4 * a_sqr * b_sqr;

        while (a_sqr * (2 * y - 1) > 2 * b_sqr * (x + 1)) {
            horisontalPixelLine(x0 - x, x0 + x, y0 + y, g);
            horisontalPixelLine(x0 - x, x0 + x, y0 - y, g);

            if(delta < 0)
                delta += 4 * b_sqr * (2 * ++x + 3);
            else
                delta = delta - 8 * a_sqr * (y-- - 1) +
                        4 * b_sqr * (2 * ++x - 3);
        }
        delta = b_sqr * ((2 * x + 1) * (2 * x + 1)) +
                4 *a_sqr*((y + 1) * (y + 1)) - 4 * a_sqr * b_sqr;
        while (y >= 0){
            horisontalPixelLine(x0 - x, x0 + x, y0 + y, g);
            horisontalPixelLine(x0 - x, x0 + x, y0 - y, g);

            if(delta < 0)
                delta += 4 * a_sqr * (2 * --y + 3);
            else
                delta = delta - 8 * b_sqr * (x++ + 1) +
                        4 * a_sqr * (2 * --y + 3);
        }
    }

    void fillBresenhamCircle(int x0, int y0, int radius, Graphics g)
    {
        int x = 0;
        int y = radius;
        int delta = 1 - 2* radius;
        int err = 0;
        while (y >= 0) {
            horisontalPixelLine(x0 - x,x0 + x, y0 + y, g);
            horisontalPixelLine(x0 - x,x0 + x, y0 - y, g);
            err = 2 * (delta + y) - 1;
            if(delta < 0 && err < 0){
                delta += 2 * ++x + 1;
            }
            else if(delta > 0 && err > 0){
                delta -= 2* --y + 1;
            }
            else {
                delta += 2 * (++x - y--);
            }
        }
    }

    void BresenhamLine(int x0, int y0, int x1, int y1, Graphics g)
    {
        boolean steep = Math.abs(y1 - y0) > Math.abs(x1 - x0); // Проверяем рост отрезка по оси икс и по оси игрек
        // Отражаем линию по диагонали, если угол наклона слишком большой
        if (steep)
        {
            int t = x0;
            x0 = y0;
            y0 = t;

            t = x1;
            x1 = y1;
            y1 = t;

        }
        // Если линия растёт не слева направо, то меняем начало и конец отрезка местами
        if (x0 > x1)
        {
            int t = x0;
            x0 = x1;
            x1 = t;

            t = y0;
            y0 = y1;
            y1 = t;
        }
        int dx = x1 - x0;
        int dy = Math.abs(y1 - y0);
        int error = dx / 2; // Здесь используется оптимизация с умножением на dx, чтобы избавиться от лишних дробей
        int ystep = (y0 < y1) ? 1 : -1; // Выбираем направление роста координаты y
        int y = y0;
        for (int x = x0; x <= x1; x++)
        {
            drawPixel(steep ? y : x, steep ? x : y, g); // Не забываем вернуть координаты на место
            error -= dy;
            if (error < 0)
            {
                y += ystep;
                error += dx;
            }
        }
    }

    private void horisontalPixelLine(int x0, int x1, int y, Graphics g){
        g.fillRect(x0,y, x1 - x0, 1);
    }
}
