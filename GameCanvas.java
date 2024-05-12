
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
import java.util.*;

//This class extends JComponent and is incharge of drawing the players aswell as the platforms. It is also incharge of handling the bullets created by the players.
public class GameCanvas extends JComponent {
    private Player p1, p2, player1, player2;
    private Platform OOBplatform, platform1, platform2, platform3, platform4, platform5, platform6, platform7,
             platform9, platform10, playerCover1, playerCover2, playerCover3, playerCover4, playerCover5;
    private AmmoBox ammoBox;
    private ArrayList<Platform> platformlist, coverList;
    private ArrayList<Bullet> bulletList1, bulletList2;
    private ArrayList<Player> playerList;
    private Hearts player1Hearts, player2Hearts;
    private FaceUI playerFaces;
    private EndScreen endScreen;
    private Background background;

    // Constructor initializes the players, the platforms, the graphics, and the
    // bullets.
    public GameCanvas(int CharType1, int CharType2) { // The Canvas is 1000 by 600
        p1 = new Player(100, 100, 20, 20, 0, 0, CharType1);
        p2 = new Player(900, 100, 20, 20, 0, 0, CharType2);
        // platformtest = new Platform(200, 200, 500, 20, Color.yellow, false);
        // platformSoftTest = new Platform(200, 450, 500, 20, Color.red, true);
        platformlist = new ArrayList<Platform>();
        coverList = new ArrayList<Platform>();
        bulletList1 = new ArrayList<Bullet>();
        bulletList2 = new ArrayList<Bullet>();
        playerList = new ArrayList<Player>();

        // platformlist.add(platformtest);
        // platformlist.add(platformSoftTest);

        // player graphics
        player1Hearts = new Hearts(-300, -300);
        player2Hearts = new Hearts(-300, -300);
        playerFaces = new FaceUI(-300, -300, -300, -300);
        endScreen = new EndScreen();
        background = new Background();

        // base platforms
        OOBplatform = new Platform(-900, -900, 1000, 20, Color.WHITE, false, 2);
        platform1 = new Platform(0, 500, 250, 20, Color.yellow, false, 0);
        platform2 = new Platform(750, 500, 250, 20, Color.yellow, false, 0);
        // 1
        platform3 = new Platform(50, 350, 250, 20, Color.RED, true, 0);
        platform4 = new Platform(375, 400, 250, 20, Color.RED, true, 0);
        platform5 = new Platform(700, 350, 250, 20, Color.RED, true, 0);
        // input NAME = new CLASS here
        platform6 = new Platform(75, 200, 150, 20, Color.RED, true, 1);
        platform7 = new Platform(300, 200, 150, 20, Color.RED, true, 1);
        // platform8 = new Platform(475, 200, 150, 20, Color.RED, true);
        platform9 = new Platform(550, 200, 150, 20, Color.RED, true, 1);
        platform10 = new Platform(775, 200, 150, 20, Color.RED, true, 1);
        playerCover1 = new Platform(50, 50, 10, 180, new Color(0, 0, 0, 0), false, 2);
        playerCover2 = new Platform(150, 50, 10, 180, new Color(0, 0, 0, 0), false, 2);
        playerCover3 = new Platform(0, 50, 1000, 50, new Color(0, 0, 0, 0), false, 2);
        playerCover4 = new Platform(850, 50, 10, 180, new Color(0, 0, 0, 0), false, 2);
        playerCover5 = new Platform(950, 50, 10, 180, new Color(0, 0, 0, 0), false, 2);

        platformlist.add(platform1);
        platformlist.add(platform2);
        platformlist.add(platform3);
        platformlist.add(platform4);
        platformlist.add(platform5);
        platformlist.add(platform6);
        platformlist.add(platform7);
        // platformlist.add(platform8);
        platformlist.add(platform9);
        platformlist.add(platform10);
        platformlist.add(playerCover1);
        platformlist.add(playerCover2);
        platformlist.add(playerCover3);
        platformlist.add(playerCover4);
        platformlist.add(playerCover5);

        coverList.add(playerCover1);
        coverList.add(playerCover2);
        coverList.add(playerCover3);
        coverList.add(playerCover4);
        coverList.add(playerCover5);

        playerList.add(p1);
        playerList.add(p2);

        // AmmoBox
        ammoBox = new AmmoBox(485, 275, 30, 40);
    }

    // This method is incharge of drawing the hearts that signify the health of the
    // player.
    public void createHearts(int width, int height) {
        player1Hearts = new Hearts(25, height - 105);
        player2Hearts = new Hearts(width - 64, height - 105);
        playerFaces = new FaceUI(player1Hearts.getX(), player1Hearts.getY(), player2Hearts.getX(),
                player2Hearts.getY());
    }

    // This method is in charge of changing the face of the player next to the heart
    // once the game starts depending on the char type.
    public void setFaces(int playerID) {
        playerFaces.setChars(playerID, p1.getCharType(), p2.getCharType());
    }

    // This method is incharge of creating the bullets and initializing them
    // depending on the type of player.
    public void createBullets(int playerID) {
        // bullet creation
        if (playerID == 1) {
            player1 = p1;
            player2 = p2;
        } else if (playerID == 2) {
            player1 = p2;
            player2 = p1;
        }
        if (player1.getCharType() == 0) {
            for (int i = 0; i < 5; i++) {
                Bullet bullet = new Bullet(-1000, -1000, -1001, -1001, 50, 50, Color.BLACK, 0);
                bulletList1.add(bullet);
            }
        } else if (player1.getCharType() == 1) {
            for (int i = 0; i < 11; i++) {
                Bullet bullet = new Bullet(-1000, -1000, -1001, -1001, 50, 50, Color.BLACK, 1);
                bulletList1.add(bullet);
            }
        } else if (player1.getCharType() == 2) {
            for (int i = 0; i < 9; i++) {
                Bullet bullet = new Bullet(-1000, -1000, -1001, -1001, 50, 50, Color.BLACK, 2);
                bulletList1.add(bullet);
            }
        }

        if (player2.getCharType() == 0) {
            for (int i = 0; i < 5; i++) {
                Bullet bullet = new Bullet(-1000, -1000, -1001, -1001, 50, 50, Color.BLACK, 0);
                bulletList2.add(bullet);
            }
        } else if (player2.getCharType() == 1) {
            for (int i = 0; i < 11; i++) {
                Bullet bullet = new Bullet(-1000, -1000, -1001, -1001, 50, 50, Color.BLACK, 1);
                bulletList2.add(bullet);
            }
        } else if (player2.getCharType() == 2) {
            for (int i = 0; i < 9; i++) {
                Bullet bullet = new Bullet(-1000, -1000, -1001, -1001, 50, 50, Color.BLACK, 2);
                bulletList2.add(bullet);
            }
        }
    }

    // This method is incharge of drawing all the objects.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        background.draw(g2d);
        p1.draw(g2d);
        p2.draw(g2d);

        OOBplatform.draw(g2d);
        // platformtest.draw(g2d);
        // platformSoftTest.draw(g2d);

        for (Bullet bullet : bulletList1) {
            bullet.draw(g2d);
        }
        for (Bullet bullet : bulletList2) {
            bullet.draw(g2d);
        }
        for (Platform platform : platformlist) {
            platform.draw(g2d);
        }

        ammoBox.draw(g2d);

        // UI GRAPHICS

        player1Hearts.draw(g2d);
        player2Hearts.draw(g2d);
        playerFaces.draw(g2d);
        endScreen.draw(g2d);

    }

    // Accessor method that returns player1
    public Player getPlayer1() {
        return p1;
    }

    // Accessor method that returns player2
    public Player getPlayer2() {
        return p2;
    }

    // Accessor method that returns an arraylist of both players.
    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    // Accessor method that returns an arraylist of all platforms
    public ArrayList<Platform> getPlatformList() {
        return platformlist;
    }

    // Accessor method that returns all the bullets of player1
    public ArrayList<Bullet> getBulletList1() {
        return bulletList1;
    }

    // Accessor method that returns all the bullets of player2
    public ArrayList<Bullet> getBulletList2() {
        return bulletList2;
    }

    // Accessor method that returns the number of hearts of player1
    public Hearts getPlayer1Hearts() {
        return player1Hearts;
    }

    // Accessor method that returns the number of hearts of player2
    public Hearts getPlayer2Hearts() {
        return player2Hearts;
    }

    // ACcessor method that returns the ammoBox.
    public AmmoBox getAmmoBox() {
        return ammoBox;
    }

    // Accessor method that returns endScreen.
    public EndScreen getEndScreen(){
        return endScreen;
    }

    //Accessor method for coverList
    public ArrayList<Platform> getCoverList(){
        return coverList;
    }

    public void setCoverOrig(){
        playerCover1.setX(50);
        playerCover2.setX(150);
        playerCover3.setX(0);
        playerCover4.setX(850);
        playerCover5.setX(950);
    }
}