//This class contains the code that sets up the Main JFrame for the player. 

//since its just rough lang naman i am basing off the video sir provided! more specifically 
//(Supplement) Video List: Sending Coordinates Between 2 Players Over a Network in Java
// Part 1 - 9:06
// REFER TO 10 MINUTES ONWARDS FOR HOW TO DO GAME CANVAS I BELIEVE.
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class GameFrame extends JFrame implements MouseListener, MouseMotionListener {
    private int width, height, xCord, yCord, baseBulletLife, mouseX, mouseY;
    private float xVariable, yVariable, xVariable1, xVariable2, yVariable1, yVariable2;
    private Container contentPane;
    private GameCanvas GC;
    private Timer animationTimer;
    private boolean up, down, left, right, sKey, mouseHeld;
    private Platform testPlatform1;
    private Player player1, player2;
    private JLabel label;
    private int AmmoClaimCounter, shootDelay1, shootDelayCounter1, totalBullet1, totalBulletCounter1;
    private Socket socket;
    private int playerID;

    private int temp, incrementor, incrementor2;

    private ReadFromServer rfsRunnable;
    private WriteToServer wtsRunnable;

    public GameFrame(int w, int h) {
        connectToServer();
        width = w;
        height = h;
        GC = new GameCanvas();
        player1 = GC.getPlayer1();
        player2 = GC.getPlayer2();
        if(playerID == 2){
            player1 = GC.getPlayer2();
            player2 = GC.getPlayer1();
        }
        shootDelay1 = player1.getShootDelay();
        totalBullet1 = player1.getTotalAmmo();
        up = false;
        down = false;
        left = false;
        right = false;
        mouseHeld = false;
        temp = 0;
        incrementor = 0;
        incrementor2 = 0;
        AmmoClaimCounter = 180;
        mouseX = 0;
        mouseY = 0;
        totalBulletCounter1 = 0;

        shootDelayCounter1 = shootDelay1;
    }

    public void createPlayers() {
        System.out.println("CreatedPlayers!!!!!");
        if (playerID == 1) {
            player1 = GC.getPlayer1();
            player2 = GC.getPlayer2();
        } else if (playerID == 2) {
            player1 = GC.getPlayer2();
            player2 = GC.getPlayer1();
        }
    }

    public void connectToServer() {
        try {
            socket = new Socket("localhost", 12345); // LOCALHOST
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            playerID = in.readInt();
            System.out.println("You are player#" + playerID);
            if (playerID == 1) {
                System.out.println("Waiting for player #2 to connect");

            }
            rfsRunnable = new ReadFromServer(in);
            wtsRunnable = new WriteToServer(out);
            rfsRunnable.waitForStartMsg(); // waits for both players to log in b4 starting.
        } catch (IOException ie) {
            System.out.println("IOException from ConnectToServer in GameFrame");
        }
    }

    private void setUpAnimationTimer() {
        int interval = 1000 / 60; // i remember diego mentioning something like this when we went drinking.
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                //movement
                if (up == true && player1.isFalling() == false) { // NO WORKY
                    player1.setJumpStatus(true);
                    // GC.getPlayer1().setYPos(GC.getPlayer1().getYPos() - 100);
                    // GC.getPlayer1().setFalling(true);

                }

                if (player1.isJumping() == true) {
                    temp = temp - 5; // in this case temp is the distance travelled by the
                                     // player. the value here should be the speed.
                    player1.changeYSpeed(-17 + incrementor); // plus cuz it should slow
                                                             // down.
                    incrementor++;
                    // System.out.println(GC.getPlayer1().getYSpeed());
                    if (incrementor + -17 == 0) {
                        System.out.println("False");
                        player1.setJumpStatus(false);
                        player1.setFalling(true);
                        temp = 0;
                        incrementor = 0;
                    }

                }

                if (player1.isFalling() == true) {
                    // System.out.println(incrementor2);
                    player1.changeYSpeed(incrementor2);
                    if (incrementor2 < 17) {
                        incrementor2++;
                    }
                } else {
                    incrementor2 = 0;
                }

                if (right == true) {
                    player1.lookRight();
                    player1.changeXSpeed(10);
                    if (player1.isJumping() == false) {
                        player1.setFalling(true);
                    }
                }
                if (down == true) { // cancels any jump and makes bro start falling so that theres more vertical
                                    // mobility
                    player1.setFalling(true);
                    player1.setJumpStatus(false);
                    temp = 0;
                    incrementor = 0;
                }

                if (left == true) { // NO WORKY
                    player1.lookLeft();
                    player1.changeXSpeed(-10);
                    if (player1.isJumping() == false) {
                        player1.setFalling(true);
                    }
                }
                player1.move();
                GC.repaint();

                if (up == false || down == false) {
                    player1.changeYSpeed(0);
                }
                if (right == false || left == false) {
                    player1.changeXSpeed(0);
                }

                // bullet calculation
                if (mouseHeld == true) {
                    int xPlayerCenter = (player1.getXPos() + player1.getWidth() / 2);
                    int yPlayerCenter = (player1.getYPos() + player1.getHeight() / 2);
                    xCord = mouseX - xPlayerCenter; // Measures from center of player
                    yCord = yPlayerCenter - mouseY;

                    double angleRad = Math.atan2(yCord, xCord);

                    double angleDeg = Math.toDegrees(angleRad);
                    angleDeg = (angleDeg + 360) % 360;

                    double angleDeg1 = (angleDeg + 5) % 360;
                    double angleDeg2 = (angleDeg - 5) % 360;

                    double angleRad1 = Math.toRadians(angleDeg1);
                    double angleRad2 = Math.toRadians(angleDeg2);

                    xVariable = (float) Math.cos(angleRad);
                    yVariable = (float) Math.sin(angleRad) * (-1);

                    xVariable1 = (float) Math.cos(angleRad1);
                    yVariable1 = (float) Math.sin(angleRad1) * (-1);

                    xVariable2 = (float) Math.cos(angleRad2);
                    yVariable2 = (float) Math.sin(angleRad2) * (-1);
                    if (player1.getCharType() == 0) {
                        baseBulletLife = 23;
                        if (shootDelayCounter1 <= 0 && totalBulletCounter1 > 0) {
                            totalBulletCounter1 -= 1;
                            shootDelayCounter1 = shootDelay1;
                            for (int i = 0; i < GC.getBulletList1().size(); i++) {
                                Bullet bullet = GC.getBulletList1().get(i);
                                if (bullet.getX() <= width && bullet.getX() >= 0 && bullet.getY() <= height
                                        && bullet.getY() >= 0) {
                                    continue;
                                } else {
                                    bullet.resetBulletLife();
                                    bullet.setVariables(xVariable, yVariable);
                                    bullet.setXPos((int) (xPlayerCenter + xVariable * 10),
                                            (int) (xPlayerCenter + xVariable * 10 + xVariable * 10));
                                    bullet.setYPos((int) (yPlayerCenter + yVariable * 10),
                                            (int) (yPlayerCenter + yVariable * 10 + yVariable * 10));
                                    break;
                                }
                            }
                        }
                    } else if (player1.getCharType() == 1) {
                        baseBulletLife = 17;
                        if (shootDelayCounter1 <= 0 && totalBulletCounter1 > 0) {
                            totalBulletCounter1 -= 1;
                            shootDelayCounter1 = shootDelay1;
                            for (int i = 0; i < GC.getBulletList1().size(); i++) {
                                Bullet bullet = GC.getBulletList1().get(i);
                                Bullet bullet1 = GC.getBulletList1().get(i + 1);
                                Bullet bullet2 = GC.getBulletList1().get(i + 2);
                                if (bullet.getX() <= width && bullet.getX() >= 0 && bullet.getY() <= height
                                        && bullet.getY() >= 0) {
                                    continue;
                                } else {
                                    bullet.resetBulletLife();
                                    bullet.setVariables(xVariable, yVariable);
                                    bullet.setXPos((int) (xPlayerCenter + xVariable * 10),
                                            (int) (xPlayerCenter + xVariable * 10 + xVariable * 10));
                                    bullet.setYPos((int) (yPlayerCenter + yVariable * 10),
                                            (int) (yPlayerCenter + yVariable * 10 + yVariable * 10));

                                    bullet1.resetBulletLife();
                                    bullet1.setVariables(xVariable1, yVariable1);
                                    bullet1.setXPos((int) (xPlayerCenter + xVariable1 * 10),
                                            (int) (xPlayerCenter + xVariable1 * 10 + xVariable1 * 10));
                                    bullet1.setYPos((int) (yPlayerCenter + yVariable * 10),
                                            (int) (yPlayerCenter + yVariable1 * 10 + yVariable1 * 10));

                                    bullet2.resetBulletLife();
                                    bullet2.setVariables(xVariable2, yVariable2);
                                    bullet2.setXPos((int) (xPlayerCenter + xVariable2 * 10),
                                            (int) (xPlayerCenter + xVariable2 * 10 + xVariable2 * 10));
                                    bullet2.setYPos((int) (yPlayerCenter + yVariable * 10),
                                            (int) (yPlayerCenter + yVariable2 * 10 + yVariable2 * 10));
                                    break;
                                }
                            }
                        }
                    } else if (player1.getCharType() == 2) {
                        baseBulletLife = 12;
                        if (shootDelayCounter1 <= 0 && totalBulletCounter1 > 0) {
                            totalBulletCounter1 -= 1;
                            shootDelayCounter1 = shootDelay1;
                            for (int i = 0; i < GC.getBulletList1().size(); i++) {
                                Bullet bullet = GC.getBulletList1().get(i);
                                if (bullet.getX() <= width && bullet.getX() >= 0 && bullet.getY() <= height
                                        && bullet.getY() >= 0) {
                                    continue;
                                } else {
                                    bullet.resetBulletLife();
                                    bullet.setVariables(xVariable, yVariable);
                                    bullet.setXPos((int) (xPlayerCenter + xVariable * 10),
                                            (int) (xPlayerCenter + xVariable * 10 + xVariable * 10));
                                    bullet.setYPos((int) (yPlayerCenter + yVariable * 10),
                                            (int) (yPlayerCenter + yVariable * 10 + yVariable * 10));
                                    break;
                                }
                            }
                        }
                    }
                }

                // bullet movement
                for (Bullet bullet : GC.getBulletList1()) {
                    if (bullet.getX() <= width && bullet.getX() >= 0 && bullet.getY() <= height && bullet.getY() >= 0) {
                        bullet.shootMove();
                        bullet.increaseBulletLife();
                        if (bullet.getBulletLife() >= baseBulletLife) {
                            bullet.setXPos(-1000, -1001);
                            bullet.setYPos(-1000, -1001);
                        }
                        for (Platform platform : GC.getPlatformList()) {
                            if (platform.isSoft() == false) {
                                if (bullet.isCollidingBullet(platform)) {
                                    bullet.setXPos(-1000, -1001);
                                    bullet.setYPos(-1000, -1001);
                                }
                            } else if (platform.isSoft() == true) {

                            }
                        }
                    }
                }

                // AmmoBox stuff
                for (Platform platform : GC.getPlatformList()) {
                    if (GC.getAmmoBox().isColliding(platform) == true) {
                        GC.getAmmoBox().setYPos(platform.getYPos() - GC.getAmmoBox().getHeight());
                    } else {
                        GC.getAmmoBox().drop();
                    }
                    if (player1.isCollidingAmmo(GC.getAmmoBox())) {
                        GC.getAmmoBox().setYPos(-950);
                        totalBulletCounter1 = totalBullet1;
                    }
                }

                // COUNTERS
                AmmoClaimCounter -= 1;
                shootDelayCounter1 -= 1;

                for (Platform platform : GC.getPlatformList()) {
                    if (player1.isColliding(platform)) {
                        System.out.println("Colliding");
                        int direction = player1.collidingDirection(platform);
                        if (platform.isSoft() == false) {
                            if (direction == 1) { // TOP WALL
                                System.out.println("direction = 1");
                                player1.setYPos(platform.getYPos() - player1.getHeight());
                                player1.setFalling(false);
                            }
                            if (direction == 2) {
                                System.out.println("direction = 2");
                                player1.setYPos(platform.getYPos() + platform.getHeight());
                                player1.setFalling(true);

                            }
                            if (direction == 3) { // LEFT WALL
                                System.out.println("Direction == 3");
                                player1.setXPos(platform.getXPos() - player1.getWidth());
                                player1.setFalling(true);
                            }
                            if (direction == 4) { // RIGHT WALL
                                System.out.println("Direction = 4");
                                player1.setXPos(platform.getXPos() + platform.getWidth());
                                player1.setFalling(true);
                            }

                            // else{
                            // GC.getPlayer1().setFalling(true);
                            // }
                        }
                        if (platform.isSoft() == true) {
                            if (direction == 1) { // TOP WALL
                                System.out.println("direction = 1");
                                player1.setYPos(platform.getYPos() - player1.getHeight());
                                player1.setFalling(false);
                            }
                            if (direction == 3) { // LEFT WALL
                                System.out.println("Direction == 3");
                                player1.setXPos(platform.getXPos() - player1.getWidth());
                                player1.setFalling(true);
                            }
                            if (direction == 4) { // RIGHT WALL
                                System.out.println("Direction = 4");
                                player1.setXPos(platform.getXPos() + platform.getWidth());
                                player1.setFalling(true);
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
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

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

    private class ReadFromServer implements Runnable {
        private DataInputStream dataIn;

        public ReadFromServer(DataInputStream in) {
            dataIn = in;
            System.out.println("RFS Runnable created");

        }

        public void run() {
            try { // reads the values of the ENEMY so you know where to put them on screen
                while (true) {
                    if (player2 != null) { // if player2 exists, it sets enemy's position to there.
                        player2.setXPos(dataIn.readInt());
                        player2.setYPos(dataIn.readInt());
                        GC.getAmmoBox().setXPos(dataIn.readInt());
                        GC.getAmmoBox().setYPos(dataIn.readInt());
                    }
                }
            } catch (IOException ex) {
                System.out.println("IOException from ReadFromServer run() in GameFrame");
            }
        }

        public void waitForStartMsg() {
            try {
                String startMsg = dataIn.readUTF();
                System.out.println("Message From Server: " + startMsg);
                Thread readThread = new Thread(rfsRunnable);
                Thread writeThread = new Thread(wtsRunnable);
                readThread.start();
                writeThread.start();
            } catch (IOException ie) {
                System.out.println("IOException in waitforstartmsg() in readfromserver gameframe");
            }
        }
    }

    private class WriteToServer implements Runnable {
        private DataOutputStream dataOut;

        public WriteToServer(DataOutputStream out) {
            dataOut = out;
            System.out.println("WTS Runnable created");

        }

        public void run() {
            try {
                while (true) {
                    if (player1 != null) {
                        dataOut.writeInt(player1.getXPos());
                        dataOut.writeInt(player1.getYPos());
                        dataOut.writeInt(GC.getAmmoBox().getXPos());
                        dataOut.writeInt(GC.getAmmoBox().getYPos());
                        dataOut.flush();
                    }
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException IE) {
                        System.out.println("Interrupted Exception from WTS run()");
                    }
                }
            } catch (IOException ex) {
                System.out.println("IOException from WTS run() in GameFrame");
            }
        }
    }

    public void setUpGUI() {
        contentPane = this.getContentPane();
        label = new JLabel();
        label.setBounds(0, 0, width, height);
        label.setBackground(Color.white);
        label.addMouseListener(this);
        label.addMouseMotionListener(this);
        this.setTitle("Wild Whisker Shootout - Player #" + playerID);
        contentPane.setPreferredSize(new Dimension(width, height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack(); // idk what this does no lie
        GC = new GameCanvas();
        contentPane.add(label);
        contentPane.add(GC);
        createPlayers();
        GC.createBullets(playerID);
        this.setVisible(true);
        this.setLayout(null);
        setUpAnimationTimer();
        setUpKeyListener();
    }

}
