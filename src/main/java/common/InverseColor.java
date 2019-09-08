package common;

import java.awt.*;

public class InverseColor extends Color {
    public InverseColor(int r, int g, int b) {
        super(255 - r, 255 - g, 255 - b);
    }
}
