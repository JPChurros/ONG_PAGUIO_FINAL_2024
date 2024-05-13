
/**
	@author Charles Matthew L. Ong (234579)
    @author Gabriel Syd O. Paguio (234725)
	@version May 11, 2024
	
	I have not discussed the Java language code in my program 
	with anyone other than my instructor or the teaching assistants 
	assigned to this course.

	I have not used Java language code obtained from another student, 
	or any other unauthorized source, either modified or unmodified.

	If any Java language code or documentation used in my program 
	was obtained from another source, such as a textbook or website, 
	that has been clearly noted with a proper citation in the comments 
	of my program.
**/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class GameFrame extends JFrame implements MouseListener, MouseMotionListener {
    private int width, height, xCord, yCord, baseBulletLife, baseBulletLife2, mouseX, mouseY, mouseHeld, p2mouseHeld,
            p2mouseX, p2mouseY, cType, cTypeOther;
    private int xCord2, yCord2;
    private float xVariable, yVariable, xVariable1, xVariable2, yVariable1, yVariable2;
    private float p2xVariable, p2yVariable, p2xVariable1, p2xVariable2, p2yVariable1, p2yVariable2;
    private Container contentPane;
    private GameCanvas GC;
    private Timer animationTimer;
    private boolean up, down, left, right;
    private Player player1, player2;
    private Hearts player1Heart, player2Heart;
    private JLabel label;
    private int shootDelay1, shootDelayCounter1, totalBullet1, totalBulletCounter1, shootDelay2,
            shootDelayCounter2, totalBullet2, totalBulletCounter2, spawnTimer;
    private Socket socket;
    private int playerID;
    private ArrayList<Bullet> bulletArray1, bulletArray2;

    private int temp, incrementor, incrementor2, cType1, cType2;

    private ReadFromServer rfsRunnable;
    private WriteToServer wtsRunnable;

    public GameFrame(int w, int h, int charType) {
        width = w;
        height = h;
        GC = new GameCanvas(cType, cTypeOther);
        this.cType = charType;
        player1 = GC.getPlayer1();
        player2 = GC.getPlayer2();
        up = false;
        down = false;
        left = false;
        right = false;
        mouseHeld = 0;
        p2mouseHeld = 0;
        temp = 0;
        incrementor = 0;
        incrementor2 = 0;
        mouseX = 0;
        mouseY = 0;
        totalBulletCounter1 = 0;
        spawnTimer = 300;

        shootDelayCounter1 = shootDelay1;
    }

    public void createPlayers() {
        // System.out.println("CreatedPlayers!!!!!");
        if (playerID == 1) {
            player1 = GC.getPlayer1();
            player2 = GC.getPlayer2();
        } else if (playerID == 2) {
            player1 = GC.getPlayer2();
            player2 = GC.getPlayer1();
        }
    }

    public void connectToServer(String IPAddress, int Port) { // I THINK THIS IS WHERE YOU HAVE TO PUT THE CODE THAT
                                                              // SCANS IT BUT LIKE IDEK
        // HOW ITS GONNA PASS IT TO SERVER
        try {
            socket = new Socket(IPAddress, Port); // LOCALHOST
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
        int interval = 1000 / 60;
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                bulletArray1 = GC.getBulletList1();
                bulletArray2 = GC.getBulletList2();
                player1Heart = GC.getPlayer1Hearts();
                player2Heart = GC.getPlayer2Hearts();
                if (playerID == 2) {
                    player1 = GC.getPlayer2();
                    player2 = GC.getPlayer1();
                    player1Heart = GC.getPlayer2Hearts();
                    player2Heart = GC.getPlayer1Hearts();
                }
                shootDelay1 = player1.getShootDelay();
                totalBullet1 = player1.getTotalAmmo();
                shootDelay2 = player2.getShootDelay();
                totalBullet2 = player2.getTotalAmmo();
                player1.setCharType(cType);
                player2.setCharType(cTypeOther);
                GC.setFaces(playerID);
                player1.setCharacterFinal();
                player2.setCharacterFinal();

                for (Bullet bullet : bulletArray1) {
                    bullet.setChar(cType);
                }
                for (Bullet bullet : bulletArray2) {
                    bullet.setChar(cTypeOther);
                }

                if (player2.getLookRight() == 1) {
                    player2.lookRight();
                } else if (player2.getLookRight() == 0) {
                    player2.lookLeft();
                }

                // movement
                if (up == true && player1.isFalling() == false) {
                    player1.setJumpStatus(true);
                }

                if (player1.isJumping() == true) {
                    temp = temp - 5;
                    player1.changeYSpeed(-17 + incrementor);
                    incrementor++;
                    if (incrementor + -17 == 0) {
                        // System.out.println("False");
                        player1.setJumpStatus(false);
                        player1.setFalling(true);
                        temp = 0;
                        incrementor = 0;
                    }

                }

                if (player1.isFalling() == true) {
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

                if (left == true) {
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

                // TPs the player back to stage
                if (player1.getYPos() > height || player1.getXPos() + player1.getWidth() > width
                        || player1.getXPos() < 0) {
                    if (playerID == 1) {
                        player1.setYPos(50);
                        player1.setXPos(100);
                        player1.setFalling(true);
                    } else {
                        player1.setYPos(50);
                        player1.setXPos(900);
                        player1.setFalling(true);
                    }

                }
                // bullet calculation

                if (mouseHeld == 1) {
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
                            // System.out.println("am shooting");
                            totalBulletCounter1 -= 1;
                            shootDelayCounter1 = shootDelay1;
                            for (int i = 0; i < bulletArray1.size(); i++) {
                                Bullet bullet = bulletArray1.get(i);
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
                            for (int i = 0; i < bulletArray1.size(); i++) {
                                Bullet bullet = bulletArray1.get(i);
                                Bullet bullet1 = bulletArray1.get(i + 1);
                                Bullet bullet2 = bulletArray1.get(i + 2);
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
                            for (int i = 0; i < bulletArray1.size(); i++) {
                                Bullet bullet = bulletArray1.get(i);
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

                if (p2mouseHeld == 1) {
                    // System.out.println("p2holding button");
                    int xPlayerCenter2 = (player2.getXPos() + player2.getWidth() / 2);
                    int yPlayerCenter2 = (player2.getYPos() + player2.getHeight() / 2);
                    xCord2 = p2mouseX - xPlayerCenter2; // Measures from center of player
                    yCord2 = yPlayerCenter2 - p2mouseY;

                    double p2angleRad = Math.atan2(yCord2, xCord2);

                    double p2angleDeg = Math.toDegrees(p2angleRad);
                    p2angleDeg = (p2angleDeg + 360) % 360;

                    double p2angleDeg1 = (p2angleDeg + 5) % 360;
                    double p2angleDeg2 = (p2angleDeg - 5) % 360;

                    double p2angleRad1 = Math.toRadians(p2angleDeg1);
                    double p2angleRad2 = Math.toRadians(p2angleDeg2);

                    p2xVariable = (float) Math.cos(p2angleRad);
                    p2yVariable = (float) Math.sin(p2angleRad) * (-1);

                    p2xVariable1 = (float) Math.cos(p2angleRad1);
                    p2yVariable1 = (float) Math.sin(p2angleRad1) * (-1);

                    p2xVariable2 = (float) Math.cos(p2angleRad2);
                    p2yVariable2 = (float) Math.sin(p2angleRad2) * (-1);

                    if (player2.getCharType() == 0) {
                        baseBulletLife2 = 23;
                        // System.out.println("other guy is shooting fr");
                        if (shootDelayCounter2 <= 0 && totalBulletCounter2 > 0) {
                            totalBulletCounter2 -= 1;
                            shootDelayCounter2 = shootDelay2;
                            for (int i = 0; i < bulletArray2.size(); i++) {
                                Bullet bullet = bulletArray2.get(i);
                                if (bullet.getX() <= width && bullet.getX() >= 0 && bullet.getY() <= height
                                        && bullet.getY() >= 0) {
                                    continue;
                                } else {
                                    bullet.resetBulletLife();
                                    bullet.setVariables(p2xVariable, p2yVariable);
                                    bullet.setXPos((int) (xPlayerCenter2 + p2xVariable * 10),
                                            (int) (xPlayerCenter2 + p2xVariable * 10 + p2xVariable * 10));
                                    bullet.setYPos((int) (yPlayerCenter2 + p2yVariable * 10),
                                            (int) (yPlayerCenter2 + p2yVariable * 10 + p2yVariable * 10));
                                    break;
                                }
                            }
                        }
                    } else if (player2.getCharType() == 1) {
                        baseBulletLife2 = 17;
                        if (shootDelayCounter2 <= 0 && totalBulletCounter2 > 0) {
                            // System.out.println("other guy is shooting fr");
                            totalBulletCounter2 -= 1;
                            shootDelayCounter2 = shootDelay2;
                            for (int i = 0; i < bulletArray2.size(); i++) {
                                Bullet bullet = bulletArray2.get(i);
                                Bullet bullet1 = bulletArray2.get(i + 1);
                                Bullet bullet2 = bulletArray2.get(i + 2);
                                if (bullet.getX() <= width && bullet.getX() >= 0 && bullet.getY() <= height
                                        && bullet.getY() >= 0) {
                                    continue;
                                } else {
                                    bullet.resetBulletLife();
                                    bullet.setVariables(p2xVariable, p2yVariable);
                                    bullet.setXPos((int) (xPlayerCenter2 + p2xVariable * 10),
                                            (int) (xPlayerCenter2 + p2xVariable * 10 + p2xVariable * 10));
                                    bullet.setYPos((int) (yPlayerCenter2 + yVariable * 10),
                                            (int) (yPlayerCenter2 + p2xVariable * 10 + p2yVariable * 10));

                                    bullet1.resetBulletLife();
                                    bullet1.setVariables(p2xVariable1, p2yVariable1);
                                    bullet1.setXPos((int) (xPlayerCenter2 + xVariable1 * 10),
                                            (int) (xPlayerCenter2 + p2xVariable1 * 10 + p2xVariable1 * 10));
                                    bullet1.setYPos((int) (yPlayerCenter2 + yVariable * 10),
                                            (int) (yPlayerCenter2 + p2yVariable1 * 10 + p2yVariable1 * 10));

                                    bullet2.resetBulletLife();
                                    bullet2.setVariables(p2xVariable2, p2yVariable2);
                                    bullet2.setXPos((int) (xPlayerCenter2 + p2xVariable2 * 10),
                                            (int) (xPlayerCenter2 + p2xVariable2 * 10 + p2xVariable2 * 10));
                                    bullet2.setYPos((int) (yPlayerCenter2 + yVariable * 10),
                                            (int) (yPlayerCenter2 + p2yVariable2 * 10 + p2yVariable2 * 10));
                                    break;
                                }
                            }
                        }
                    } else if (player2.getCharType() == 2) {
                        baseBulletLife2 = 12;
                        // System.out.println("other guy is shooting fr og");
                        if (shootDelayCounter2 <= 0 && totalBulletCounter2 > 0) {
                            // System.out.println("other guy is shooting fr");
                            totalBulletCounter2 -= 1;
                            shootDelayCounter2 = shootDelay2;
                            for (int i = 0; i < bulletArray2.size(); i++) {
                                Bullet bullet = bulletArray2.get(i);
                                if (bullet.getX() <= width && bullet.getX() >= 0 && bullet.getY() <= height
                                        && bullet.getY() >= 0) {
                                    continue;
                                } else {
                                    bullet.resetBulletLife();
                                    bullet.setVariables(p2xVariable, p2yVariable);
                                    bullet.setXPos((int) (xPlayerCenter2 + p2xVariable * 10),
                                            (int) (xPlayerCenter2 + p2xVariable * 10 + p2xVariable * 10));
                                    bullet.setYPos((int) (yPlayerCenter2 + yVariable * 10),
                                            (int) (yPlayerCenter2 + p2yVariable * 10 + p2yVariable * 10));
                                    break;
                                }
                            }
                        }
                    }
                }

                // bullet movement
                for (Bullet bullet : bulletArray1) {
                    if (bullet.getX() <= width && bullet.getX() >= 0 && bullet.getY() <= height && bullet.getY() >= 0) {
                        bullet.shootMove();
                        bullet.increaseBulletLife();
                        if (bullet.getBulletLife() >= baseBulletLife) {
                            bullet.setXPos(-1000, -1001);
                            bullet.setYPos(-1000, -1001);
                        }
                        if (bullet.isCollidingPlayer(player2) == true) {
                            for (Bullet bulletin : bulletArray1) {
                                bulletin.setXPos(-1000, -1001);
                                bulletin.setYPos(-1000, -1001);
                            }
                            for (Bullet bulletin2 : bulletArray2) {
                                bulletin2.setXPos(-1000, -1001);
                                bulletin2.setYPos(-1000, -1001);
                            }
                            player2.minusHP();
                            if (player2.currentHP() == 2) {
                                player2Heart.setHP2();
                            } else if (player2.currentHP() == 1) {
                                player2Heart.setHP1();
                            } else if (player2.currentHP() == 0) {
                                player2Heart.setHP0();
                                GC.getEndScreen().win(player1.getCharType());
                                GC.getEndScreen().gameEnd();
                            }
                            GC.getPlayer1().setXPos(100);
                            GC.getPlayer1().setYPos(50);
                            GC.getPlayer2().setXPos(900);
                            GC.getPlayer2().setYPos(50);
                            GC.setCoverOrig();
                            totalBulletCounter1 = 0;
                            totalBulletCounter2 = 0;
                            spawnTimer = 180;

                        }
                        for (Platform platform : GC.getPlatformList()) {
                            if (platform.isSoft() == false) {
                                if (bullet.isCollidingBullet(platform)) {
                                    bullet.setXPos(-1000, -1001);
                                    bullet.setYPos(-1000, -1001);
                                }
                            }
                        }
                    }
                }
                for (Bullet bullet : bulletArray2) {
                    if (bullet.getX() <= width && bullet.getX() >= 0 && bullet.getY() <= height && bullet.getY() >= 0) {
                        bullet.shootMove();
                        bullet.increaseBulletLife();
                        if (bullet.getBulletLife() >= baseBulletLife2) {
                            bullet.setXPos(-1000, -1001);
                            bullet.setYPos(-1000, -1001);
                        }
                        if (bullet.isCollidingPlayer(player1) == true) {
                            // System.out.println("you hit him");
                            for (Bullet bulletin : bulletArray1) {
                                bulletin.setXPos(-1000, -1001);
                                bulletin.setYPos(-1000, -1001);
                            }
                            for (Bullet bulletin2 : bulletArray2) {
                                bulletin2.setXPos(-1000, -1001);
                                bulletin2.setYPos(-1000, -1001);
                            }
                            player1.minusHP();
                            if (player1.currentHP() == 2) {
                                player1Heart.setHP2();
                            } else if (player1.currentHP() == 1) {
                                player1Heart.setHP1();
                            } else if (player1.currentHP() == 0) {
                                player1Heart.setHP0();
                                GC.getEndScreen().lose(player1.getCharType());
                                GC.getEndScreen().gameEnd();
                            }
                            GC.getPlayer1().setXPos(100);
                            GC.getPlayer1().setYPos(50);
                            GC.getPlayer2().setXPos(900);
                            GC.getPlayer2().setYPos(50);
                            GC.setCoverOrig();
                            totalBulletCounter1 = 0;
                            totalBulletCounter2 = 0;
                            spawnTimer = 180;
                        }
                        for (Platform platform : GC.getPlatformList()) {
                            if (platform.isSoft() == false) {
                                if (bullet.isCollidingBullet(platform)) {
                                    bullet.setXPos(-1000, -1001);
                                    bullet.setYPos(-1000, -1001);
                                }
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
                        // System.out.println("player1 got ammo");
                        // System.out.println(totalBulletCounter1);
                    }
                    if (player2.isCollidingAmmo(GC.getAmmoBox())) {
                        GC.getAmmoBox().setYPos(-950);
                        totalBulletCounter2 = totalBullet2;
                        // System.out.println("player2 got ammo");
                        // System.out.println(totalBulletCounter2);
                    }

                }

                // COUNTERS
                shootDelayCounter1 -= 1;
                shootDelayCounter2 -= 1;
                spawnTimer -= 1;

                if (spawnTimer == 0) {
                    for (Platform platform : GC.getCoverList()) {
                        platform.setX(2000);
                    }
                }

                for (Platform platform : GC.getPlatformList()) {
                    if (player1.isColliding(platform)) {
                        // System.out.println("Colliding");
                        int direction = player1.collidingDirection(platform);
                        if (platform.isSoft() == false) {
                            if (direction == 1) { // TOP WALL
                                // System.out.println("direction = 1");
                                player1.setYPos(platform.getYPos() - player1.getHeight());
                                player1.setFalling(false);
                            }
                            if (direction == 2) {
                                // System.out.println("direction = 2");
                                player1.setYPos(platform.getYPos() + platform.getHeight());
                                player1.setFalling(true);

                            }
                            if (direction == 3) { // LEFT WALL
                                // System.out.println("Direction == 3");
                                player1.setXPos(platform.getXPos() - player1.getWidth());
                                player1.setFalling(true);
                            }
                            if (direction == 4) { // RIGHT WALL
                                // System.out.println("Direction = 4");
                                player1.setXPos(platform.getXPos() + platform.getWidth());
                                player1.setFalling(true);
                            }

                            // else{
                            // GC.getPlayer1().setFalling(true);
                            // }
                        }
                        if (platform.isSoft() == true) {
                            if (direction == 1) { // TOP WALL
                                // System.out.println("direction = 1");
                                player1.setYPos(platform.getYPos() - player1.getHeight());
                                player1.setFalling(false);
                            }
                            if (direction == 3) { // LEFT WALL
                                // System.out.println("Direction == 3");
                                player1.setXPos(platform.getXPos() - player1.getWidth());
                                player1.setFalling(true);
                            }
                            if (direction == 4) { // RIGHT WALL
                                // System.out.println("Direction = 4");
                                player1.setXPos(platform.getXPos() + platform.getWidth());
                                player1.setFalling(true);
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
        mouseHeld = 1;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseHeld = 0;
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
                    case KeyEvent.VK_W:
                        // System.out.println("UP PRESSED");
                        up = true;
                        break;
                    case KeyEvent.VK_S:
                        // System.out.println("DOWN PRESSED");
                        down = true;
                        break;
                    case KeyEvent.VK_A:
                        // System.out.println("LEFT PRESSED");
                        left = true;
                        break;
                    case KeyEvent.VK_D:
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
                    case KeyEvent.VK_W:
                        up = false;
                        break;
                    case KeyEvent.VK_S:
                        down = false;
                        break;
                    case KeyEvent.VK_A:
                        left = false;
                        break;
                    case KeyEvent.VK_D:
                        right = false;
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
                        player2.setLookRight(dataIn.readInt());
                        p2mouseHeld = dataIn.readInt();
                        p2mouseX = dataIn.readInt();
                        p2mouseY = dataIn.readInt();
                        cTypeOther = dataIn.readInt();
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
                        dataOut.writeInt(player1.getLookRight());
                        dataOut.writeInt(mouseHeld);
                        dataOut.writeInt(mouseX);
                        dataOut.writeInt(mouseY);
                        dataOut.writeInt(cType);
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
        GC = new GameCanvas(cType1, cType2);
        contentPane.add(label);
        contentPane.add(GC);
        createPlayers();
        GC.createBullets(playerID);
        GC.createHearts(width, height);
        this.setVisible(true);
        this.setLayout(null);
        setUpAnimationTimer();
        setUpKeyListener();
    }

}