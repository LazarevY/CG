package common;

import java.awt.*;

public class NightColor extends Color {
    public NightColor(int r, int g, int b) {
        super(r/23, g/23, b/23);
    }
}
