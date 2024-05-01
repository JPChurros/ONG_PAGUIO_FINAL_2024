// This class extends JComponent and overrides the paintComponent method in order
//to create the custom drawing.

import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import java.util.*;
import javax.swing.Timer;

public class GameCanvas extends JComponent {
    private Player p1;
    private Platform platformtest;

    public GameCanvas() {
        p1 = new Player(100, 100, 20, 20, 0, 0);
        platformtest = new Platform(200, 200, 500, 20, Color.yellow);
        // input NAME = new CLASS here

        //
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        p1.draw(g2d);
        platformtest.draw(g2d);
        AffineTransform reset = g2d.getTransform();

    }

    public Player getPlayer1() {
        return p1;
    }
    public Platform getPlatform(){
        return platformtest;
    }
}