// This class extends JComponent and overrides the paintComponent method in order
//to create the custom drawing.

import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import java.util.*;
import javax.swing.Timer;

public class GameCanvas extends JComponent {

    // input private CLASS NAME here

    public GameCanvas() {

        // input NAME = new CLASS here



        // 
}
@Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        AffineTransform reset = g2d.getTransform();
        // NAME.draw(g2d);

}
}