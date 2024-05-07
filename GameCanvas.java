// This class extends JComponent and overrides the paintComponent method in order
//to create the custom drawing.

import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import java.util.*;
import javax.swing.Timer;

public class GameCanvas extends JComponent {
    private Player p1;
    private Platform OOBplatform, platform1, platform2, platform3, platform4, platform5, platform6, platform7, platform8, platform9, platform10 ;
    private AmmoBox ammoBox;
    private ArrayList<Platform> platformlist;
    private ArrayList<Bullet> bulletList1, bulletList2;

    public GameCanvas() {
        p1 = new Player(100, 100, 20, 20, 0, 0, 0);
        //platformtest = new Platform(200, 200, 500, 20, Color.yellow, false);
        //platformSoftTest = new Platform(200, 450, 500, 20, Color.red, true);
        platformlist = new ArrayList<Platform>();
        bulletList1 = new ArrayList<Bullet>();
        bulletList2 = new ArrayList<Bullet>();
        //platformlist.add(platformtest);
        //platformlist.add(platformSoftTest);

        //base platforms
        OOBplatform = new Platform(-900, -900, 1000, 20, Color.WHITE, false);
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
            for(int i = 0; i < 5; i++){
                Bullet bullet = new Bullet(-1000, -1000, -1001, -1001, 50, 50, Color.BLACK);
                bulletList1.add(bullet);
            }
        }
        else if(getPlayer1().getCharType() == 1){
            for(int i = 0; i < 11; i++){
                Bullet bullet = new Bullet(-1000, -1000, -1001, -1001, 50, 50, Color.BLACK);
                bulletList1.add(bullet);
            }
        }
        else if(getPlayer1().getCharType() == 2){
            for(int i = 0; i < 9; i++){
                Bullet bullet = new Bullet(-1000, -1000, -1001, -1001, 50, 50, Color.BLACK);
                bulletList1.add(bullet);
            }
        }

        //AmmoBox
        ammoBox = new AmmoBox(390, 275, 20, 20);
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

        for (Bullet bullet : bulletList1){
            bullet.draw(g2d);
        }

        ammoBox.draw(g2d);
        AffineTransform reset = g2d.getTransform();

    }

    public Player getPlayer1() {
        return p1;
    }

    public ArrayList<Platform> getPlatformList() {
        return platformlist;
    }

    public ArrayList<Bullet> getBulletList1(){
        return bulletList1;
    }

    public AmmoBox getAmmoBox(){
        return ammoBox;
    }
}