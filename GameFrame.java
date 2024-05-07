//This class contains the code that sets up the Main JFrame for the player. 

//since its just rough lang naman i am basing off the video sir provided! more specifically 
//(Supplement) Video List: Sending Coordinates Between 2 Players Over a Network in Java
// Part 1 - 9:06
// REFER TO 10 MINUTES ONWARDS FOR HOW TO DO GAME CANVAS I BELIEVE.
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameFrame extends JFrame implements MouseListener, MouseMotionListener {
    private int width, height, xCord, yCord, baseBulletLife, mouseX, mouseY;
    private float xVariable, yVariable;
    private Container contentPane;
    private GameCanvas GC;
    private Timer animationTimer;
    private Player player1, player2;
    private boolean up, down, left, right, sKey, mouseHeld;
    private Platform testPlatform1;
    private JLabel label;
    private Bullet rifleBullet, shotgunBullet1, shotgunBullet2, shotgunBullet3, smgBullet1, smgBullet2, smgBullet3;
    private int Ammo1, shootDelay1, shootDelayCounter1;

    private int temp, incrementor, incrementor2;

    public GameFrame(int w, int h) {
        width = w;
        height = h;
        GC = new GameCanvas();
        player1 = GC.getPlayer1();
        up = false;
        down = false;
        left = false;
        right = false;
        mouseHeld = false;
        temp = 0;
        incrementor = 0;
        incrementor2 = 0;
        Ammo1 = 0;
        mouseX = 0;
        mouseY = 0;
        if (GC.getPlayer1().getCharType() == 0){
            shootDelay1 = 30;
        }
        else if (GC.getPlayer1().getCharType() == 0){
            shootDelay1 = 60;
        }
        else if (GC.getPlayer1().getCharType() == 0){
            shootDelay1 = 12;
        }
        shootDelayCounter1 = shootDelay1;
    }

    private void setUpAnimationTimer() {
        int interval = 1000 / 60; // i remember diego mentioning something like this when we went drinking.
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (up == true && GC.getPlayer1().isFalling() == false) { // NO WORKY
                    GC.getPlayer1().setJumpStatus(true);
                    // GC.getPlayer1().setYPos(GC.getPlayer1().getYPos() - 100);
                    // GC.getPlayer1().setFalling(true);

                }

                if (GC.getPlayer1().isJumping() == true) {
                    temp = temp - 5; // in this case temp is the distance travelled by the
                                     // player. the value here should be the speed.
                    GC.getPlayer1().changeYSpeed(-17 + incrementor); // plus cuz it should slow
                                                                     // down.
                    incrementor++;
                    System.out.println(GC.getPlayer1().getYSpeed());
                    if (incrementor + -17 == 0) {
                        System.out.println("False");
                        GC.getPlayer1().setJumpStatus(false);
                        GC.getPlayer1().setFalling(true);
                        temp = 0;
                        incrementor = 0;
                    }

                }

                if (GC.getPlayer1().isFalling() == true) {
                    System.out.println(incrementor2);
                    GC.getPlayer1().changeYSpeed(incrementor2);
                    if (incrementor2 < 17) {
                        incrementor2++;
                    }
                } else {
                    incrementor2 = 0;
                }

                if (right == true) {
                    GC.getPlayer1().changeXSpeed(10);
                    if (GC.getPlayer1().isJumping() == false) {
                        GC.getPlayer1().setFalling(true);
                    }
                }
                if (down == true) { // cancels any jump and makes bro start falling so that theres more vertical
                                    // mobility
                    GC.getPlayer1().setFalling(true);
                    GC.getPlayer1().setJumpStatus(false);
                    temp = 0;
                    incrementor = 0;
                }

                if (left == true) { // NO WORKY
                    GC.getPlayer1().changeXSpeed(-10);
                    if (GC.getPlayer1().isJumping() == false) {
                        GC.getPlayer1().setFalling(true);
                    }
                }
                GC.getPlayer1().move();
                GC.repaint();

                if (up == false || down == false) {
                    GC.getPlayer1().changeYSpeed(0);
                }
                if (right == false || left == false) {
                    GC.getPlayer1().changeXSpeed(0);
                }

                //bullet calculation
                if(mouseHeld == true){
                    int xPlayerCenter = (GC.getPlayer1().getXPos() + GC.getPlayer1().getWidth() / 2);
                    int yPlayerCenter = (GC.getPlayer1().getYPos() + GC.getPlayer1().getHeight() / 2);
                    xCord = mouseX - xPlayerCenter; // Measures from center of player
                    yCord = yPlayerCenter - mouseY;

                    double angleRad = Math.atan2(yCord, xCord);

                    double angleDeg = Math.toDegrees(angleRad);
                    angleDeg = (angleDeg + 360) % 360;

                    xVariable = (float) Math.cos(angleRad);
                    yVariable = (float) Math.sin(angleRad) * (-1);
                    if (GC.getPlayer1().getCharType() == 0) {
                        baseBulletLife = 15;
                        for(int i = 0; i < GC.getBulletList1().size(); i++){
                            Bullet bullet = GC.getBulletList1().get(i);
                            if (bullet.getX() <= width && bullet.getX() >= 0 && bullet.getY() <= height
                            && bullet.getY() >= 0){
                                continue;
                            }
                            else{
                                bullet.resetBulletLife();
                                bullet.setVariables(xVariable, yVariable);
                                bullet.setXPos((int) (xPlayerCenter + xVariable * 10), (int) (xPlayerCenter + xVariable * 10 + xVariable * 10));
                                bullet.setYPos((int) (yPlayerCenter + yVariable * 10), (int) (yPlayerCenter + yVariable * 10 + yVariable * 10));
                                break;
                            }
                        }
                    }
                }

                // bullet movement
                for(Bullet bullet : GC.getBulletList1()){
                    if(bullet.getX() <= width && bullet.getX() >= 0 && bullet.getY() <= height && bullet.getY() >= 0){
                        bullet.shootMove();
                        bullet.increaseBulletLife();
                        if(bullet.getBulletLife() >= baseBulletLife){
                            bullet.setXPos(-1000, -1001);
                            bullet.setYPos(-1000, -1001);
                        }
                        for(Platform platform : GC.getPlatformList()){
                            if(platform.isSoft() == false){
                                if(bullet.isCollidingBullet(platform)){
                                    bullet.setXPos(-1000, -1001);
                                    bullet.setYPos(-1000, -1001);
                                }
                            }
                            else if(platform.isSoft() == true){

                            }
                        }
                    }
                }
                for(Platform platform : GC.getPlatformList()){
                    if(GC.getAmmoBox().isColliding(platform) == true){
                        GC.getAmmoBox().setYPos(platform.getYPos() - GC.getAmmoBox().getHeight());
                    }
                    else{
                        GC.getAmmoBox().drop();
                    }
                    if(GC.getPlayer1().isCollidingAmmo(GC.getAmmoBox())){
                        GC.getAmmoBox().setYPos(-950);
                        if(GC.getPlayer1().getCharType() == 0){
                            Ammo1 = 3;
                        }
                        else if(GC.getPlayer1().getCharType() == 1){
                            Ammo1 = 2;
                        }
                        else if(GC.getPlayer1().getCharType() == 2){
                            Ammo1 = 5;
                        }
                    }
                }
                shootDelay1 -= 1;

                for (Platform platform : GC.getPlatformList()) {
                    if (GC.getPlayer1().isColliding(platform)) {
                        System.out.println("Colliding");
                        int direction = GC.getPlayer1().collidingDirection(platform);
                        if (platform.isSoft() == false) {
                            if (direction == 1) { // TOP WALL
                                System.out.println("direction = 1");
                                GC.getPlayer1().setYPos(platform.getYPos() - GC.getPlayer1().getHeight());
                                GC.getPlayer1().setFalling(false);
                            }
                            if (direction == 2) {
                                System.out.println("direction = 2");
                                GC.getPlayer1().setYPos(platform.getYPos() + platform.getHeight());
                                GC.getPlayer1().setFalling(true);

                            }
                            if (direction == 3) { // LEFT WALL
                                System.out.println("Direction == 3");
                                GC.getPlayer1().setXPos(platform.getXPos() - GC.getPlayer1().getWidth());
                                GC.getPlayer1().setFalling(true);
                            }
                            if (direction == 4) { // RIGHT WALL
                                System.out.println("Direction = 4");
                                GC.getPlayer1().setXPos(platform.getXPos() + platform.getWidth());
                                GC.getPlayer1().setFalling(true);
                            }

                            // else{
                            // GC.getPlayer1().setFalling(true);
                            // }
                        }
                        if (platform.isSoft() == true) {
                            if (direction == 1) { // TOP WALL
                                System.out.println("direction = 1");
                                GC.getPlayer1().setYPos(platform.getYPos() - GC.getPlayer1().getHeight());
                                GC.getPlayer1().setFalling(false);
                            }
                            if (direction == 3) { // LEFT WALL
                                System.out.println("Direction == 3");
                                GC.getPlayer1().setXPos(platform.getXPos() - GC.getPlayer1().getWidth());
                                GC.getPlayer1().setFalling(true);
                            }
                            if (direction == 4) { // RIGHT WALL
                                System.out.println("Direction = 4");
                                GC.getPlayer1().setXPos(platform.getXPos() + platform.getWidth());
                                GC.getPlayer1().setFalling(true);
                            }

                            // else{
                            // GC.getPlayer1().setFalling(true);
                            // }
                        }
                    }
                }
            }
        };
        animationTimer = new Timer(interval, al);
        animationTimer.start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        mouseHeld = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseHeld = false;
    }

    @Override
    public void mouseDragged(MouseEvent e){
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e){

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
        label.addMouseMotionListener(this);
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
