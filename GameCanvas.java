// This class extends JComponent and overrides the paintComponent method in order
//to create the custom drawing.

import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import java.util.*;
import javax.swing.Timer;

public class GameCanvas extends JComponent {
    private Player p1;
    private Bullet rifleBullet;
    private Platform platformtest, platformSoftTest;
    private Platform platform1, platform2, platform3, platform4, platform5, platform6, platform7, platform8, platform9, platform10;
    private ArrayList<Platform> platformlist;

    public GameCanvas() {
        p1 = new Player(100, 100, 20, 20, 0, 0, 0);
        //platformtest = new Platform(200, 200, 500, 20, Color.yellow, false);
        //platformSoftTest = new Platform(200, 450, 500, 20, Color.red, true);
        platformlist = new ArrayList<Platform>();
        //platformlist.add(platformtest);
        //platformlist.add(platformSoftTest);

        //base platforms
        platform1 = new Platform(0, 500, 250, 20, Color.yellow, false);
        platform2 = new Platform(550, 500, 250, 20, Color.yellow, false);
        //1
        platform3 = new Platform(100,350, 150, 20, Color.RED, true);
        platform4 = new Platform(325, 350, 150, 20, Color.RED, true);
        platform5 = new Platform(550,350, 150, 20, Color.RED, true);
        // input NAME = new CLASS here
        platformlist.add(platform1);
        platformlist.add(platform2);
        platformlist.add(platform3);
        platformlist.add(platform4);
        platformlist.add(platform5);

        //bullet
        if(getPlayer1().getCharType() == 0){
            rifleBullet = new Bullet(-1000, -1000, -1001, -1001, 50, 50, Color.BLACK);
        }
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
        //platformtest.draw(g2d);
        //platformSoftTest.draw(g2d);
        platform1.draw(g2d);
        platform2.draw(g2d);
        platform3.draw(g2d);
        platform4.draw(g2d);
        platform5.draw(g2d);
        if(getPlayer1().getCharType() == 0){
            rifleBullet.draw(g2d);
        }
        AffineTransform reset = g2d.getTransform();

    }

    public Player getPlayer1() {
        return p1;
    }

    public ArrayList<Platform> getPlatformList() {
        return platformlist;
    }

    public Bullet getBullet(){
        if(getPlayer1().getCharType() == 0){
            return rifleBullet;
        }
        return rifleBullet;
    }
}