//This class contains the code that sets up the Main JFrame for the player. 

//since its just rough lang naman i am basing off the video sir provided! more specifically 
//(Supplement) Video List: Sending Coordinates Between 2 Players Over a Network in Java
// Part 1 - 9:06
// REFER TO 10 MINUTES ONWARDS FOR HOW TO DO GAME CANVAS I BELIEVE.
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameFrame extends JFrame implements MouseListener{
    private int width, height, xCord, yCord;
    private Container contentPane;
    private GameCanvas GC;
    private Timer animationTimer;
    private Player player1, player2;
    private boolean up, down, left, right, sKey;
    private Platform testPlatform1;
    private JLabel label;

    public GameFrame(int w, int h) {
        width = w;
        height = h;
        GC = new GameCanvas();
        player1 = GC.getPlayer1();
        up = false;
        down = false;
        left = false;
        right = false;
    }

    private void setUpAnimationTimer() {
        int interval = 1000 / 60; // i remember diego mentioning something like this when we went drinking.
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {



                if (up == true) { // NO WORKY
                    GC.getPlayer1().changeYSpeed(-10);
                }

                if (down == true) {
                    GC.getPlayer1().changeYSpeed(10);
                }

                if (right == true) {
                    GC.getPlayer1().changeXSpeed(10);
                }

                if (left == true) { // NO WORKY
                    GC.getPlayer1().changeXSpeed(-10);
                }
                if (sKey == true){//shooting mech
                    GC.getPlayer1().shoot();
                }
                GC.getPlayer1().move();
                // System.out.println(GC.getPlayer1().getXPos());
                // System.out.println(GC.getPlayer1().getYPos());
                GC.repaint();
                if (up == false || down == false) {
                    GC.getPlayer1().changeYSpeed(0);
                }
                if (right == false || left == false) {
                    GC.getPlayer1().changeXSpeed(0);
                }
                for (Platform platform : GC.getPlatformList()) {
                    /**
                     * System.out.println(platform.getXPos() + " " + platform.getYPos() + " " +
                     * platform.getWidth() + " "
                     * + platform.getHeight());
                     * System.out.println(player1.getXPos() + " " + player1.getYPos() + " " +
                     * player1.getWidth()
                     * + " " + player1.getHeight());
                     */
                    if (GC.getPlayer1().isColliding(platform)) {
                        System.out.println("Colliding");
                        int direction = GC.getPlayer1().collidingDirection(platform);
                        if(platform.isSoft() == false){
                        if (direction == 1) { // TOP WALL
                            System.out.println("direction = 1");
                            GC.getPlayer1().setYPos(platform.getYPos() - GC.getPlayer1().getHeight());
                        }
                        if (direction == 2) {
                            System.out.println("direction = 2");
                            GC.getPlayer1().setYPos(platform.getYPos() + platform.getHeight());
                        }
                        if (direction == 3) { // LEFT WALL
                            System.out.println("Direction == 3");
                            GC.getPlayer1().setXPos(platform.getXPos() - GC.getPlayer1().getWidth());
                        }
                        if (direction == 4) { // RIGHT WALL
                            System.out.println("Direction = 4");
                            GC.getPlayer1()
                                    .setXPos(platform.getXPos() + platform.getWidth());
                        }
                    }
                    if(platform.isSoft() == true){
                        if (direction == 1) { // TOP WALL
                            System.out.println("direction = 1");
                            GC.getPlayer1().setYPos(platform.getYPos() - GC.getPlayer1().getHeight());
                        }
                        if (direction == 3) { // LEFT WALL
                            System.out.println("Direction == 3");
                            GC.getPlayer1().setXPos(platform.getXPos() - GC.getPlayer1().getWidth());
                        }
                        if (direction == 4) { // RIGHT WALL
                            System.out.println("Direction = 4");
                            GC.getPlayer1()
                                    .setXPos(platform.getXPos() + platform.getWidth());
                        }
                    }
                }
            }
            }
        };
        animationTimer = new Timer(interval, al);
        animationTimer.start();
    }

    @Override
    public void mouseClicked(MouseEvent e){
        System.out.println("Mouse has been clicked!");
    }

    @Override
    public void mouseEntered(MouseEvent e){
        
    }

    @Override
    public void mouseExited(MouseEvent e){
        
    }

    @Override
    public void mousePressed(MouseEvent e){
        
    }

    @Override
    public void mouseReleased(MouseEvent e){
        xCord = e.getX() - GC.getPlayer1().getXPos();
        yCord = GC.getPlayer1().getYPos() - e.getY();
        float result = (float) yCord / xCord;
        System.out.println(String.format("%.4f", result));
        System.out.println(xCord);
        System.out.println(yCord);
    }

    private void setUpKeyListener() {
        KeyListener kl = new KeyListener() {
            public void keyTyped(KeyEvent ke) {
            }

            public void keyPressed(KeyEvent ke) {
                int keyCode = ke.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        // System.out.println("UP PRESSED");
                        up = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        // System.out.println("DOWN PRESSED");
                        down = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        // System.out.println("LEFT PRESSED");
                        left = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        // System.out.println("RIGHT PRESSED");
                        right = true;
                        break;
                    case KeyEvent.VK_S:
                        sKey = true;
                        break;
                }
            }

            public void keyReleased(KeyEvent ke) {
                int keyCode = ke.getKeyCode();
                System.out.println("Key has been released");
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
                    case KeyEvent.VK_S:
                        sKey = false;
                        break;
                }
            }
        };
        this.addKeyListener(kl);
        this.setFocusable(true);
    }

    public void setUpGUI() {
        contentPane = this.getContentPane();
        label = new JLabel();
        label.setBounds(0, 0, width, height);
        label.setBackground(Color.white);
        label.addMouseListener(this);
        this.setTitle("Wild Whisker Shootout");
        contentPane.setPreferredSize(new Dimension(width, height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack(); // idk what this does no lie
        GC = new GameCanvas();
        contentPane.add(label);
        contentPane.add(GC);
        this.setVisible(true);
        this.setLayout(null);
        setUpAnimationTimer();
        setUpKeyListener();
    }
}
