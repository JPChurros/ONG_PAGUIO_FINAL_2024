//This class contains the code that sets up the Main JFrame for the player. 

//since its just rough lang naman i am basing off the video sir provided! more specifically 
//(Supplement) Video List: Sending Coordinates Between 2 Players Over a Network in Java
// Part 1 - 9:06
// REFER TO 10 MINUTES ONWARDS FOR HOW TO DO GAME CANVAS I BELIEVE.
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameFrame extends JFrame {
    private int width, height;
    private Container contentPane;
    private GameCanvas GC;
    private Timer animationTimer;
    private Player player1, player2;
    private boolean up, down, left, right;

    public GameFrame(int w, int h) {
        width = w;
        height = h;
        GC = new GameCanvas();
        player1 = GC.getPlayer1();
        up = false;
        down = false;
        left = false;
        right = true;
    }

    private void setUpAnimationTimer() {
        int interval = 1000 / 60; // i remember diego mentioning something like this when we went drinking.
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("Timer Started");
                // GC.getPlayer1().move();
                // System.out.println(player1.getXPos());
                if (up == true) {
                    GC.getPlayer1().changeYSpeed(-10);
                    GC.getPlayer1().move();
                }
                if (down == true) {
                    GC.getPlayer1().changeYSpeed(10);
                    GC.getPlayer1().move();
                }
                if (left == true) {
                    GC.getPlayer1().changeXSpeed(-10);
                    GC.getPlayer1().move();
                }
                if (right == true) {
                    GC.getPlayer1().changeXSpeed(10);
                    GC.getPlayer1().move();
                }
                GC.repaint();
            }
        };
        animationTimer = new Timer(interval, al);
        animationTimer.start();
    }

    private void setUpKeyListener() { // I HAVENT FINISHED THIS YET. BRO CANNOT MOVE.
        KeyListener kl = new KeyListener() {
            public void keyTyped(KeyEvent ke) {
            }

            public void keyPressed(KeyEvent ke) {
                int keyCode = ke.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        up = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        down = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        left = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        right = true;
                        break;
                }
            }

            public void keyReleased(KeyEvent ke) {
                int keyCode = ke.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        up = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        down = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        left = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        right = false;
                        break;
                }
            }
        };
        contentPane.addKeyListener(kl);
        contentPane.setFocusable(true);
    }

    public void setUpGUI() {
        contentPane = this.getContentPane();
        this.setTitle("Wild Whisker Shootout");
        contentPane.setPreferredSize(new Dimension(width, height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack(); // idk what this does no lie
        GC = new GameCanvas();
        contentPane.add(GC);
        this.setVisible(true);
        setUpAnimationTimer();
        setUpKeyListener();
    }
}
