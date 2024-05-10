import java.awt.*;
import java.awt.geom.*;

import javax.swing.ImageIcon;


//when calling class bullet, always make initial values x1 and y1 = -1000 then x2 and y2 = -1001

public class Bullet {
    private Image projectileSprite;
    int x1, y1, x2, y2, xSpeed, ySpeed, charType, bulletLife, constantSpeed, boxX, boxY, width, height, boxHeight, boxWidth;
    float xVariable, yVariable;
    Color color;
    public Bullet(int x1, int y1, int x2, int y2, int xSpeed, int ySpeed, Color color, int charType){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.color = color;
        this.charType = charType;
        constantSpeed = 300;
        bulletLife = 0;
        boxX = -1000;
        boxY = -1000;
        boxHeight = 10;
        boxWidth = 10;
        if (this.charType == 0){
            projectileSprite = new ImageIcon("hairball_CATPROJ.png").getImage();
            width = 5;
            height = 5;
        }
        else if (this.charType == 1){
            projectileSprite = new ImageIcon("spike_HEDGEHOGPROJ.png").getImage();
            width = 6;
            height = 5;
        }
        else if (this.charType == 2){
            projectileSprite = new ImageIcon("acorn_SQUIRRELPROJ.png").getImage();
            width = 7;
            height = 5;
        }
    }

    public void draw(Graphics2D g2d){
        Line2D.Double temp = new Line2D.Double(x1, y1, x2, y2);
        g2d.drawImage(projectileSprite, boxX, boxY, width*2, height*2, null);
    }

    public void setVariables(float xVar, float yVar){
        this.xVariable = xVar;
        this.yVariable = yVar;
    }

    public int getX(){
        return x1;
    }
    public int getY(){
        return y1;
    }

    public void shootMove(){
        this.setXPos(this.x1 += xVariable*18, this.x2 += xVariable*18);
        this.setYPos(this.y1 += yVariable*18, this.y2 += yVariable*18);
    }

    public void setXPos(int tempVal, int tempVal2){
        this.x1 = tempVal;
        this.x2 = tempVal2;
    }
    public void setYPos(int tempVal, int tempVal2){
        this.y1 = tempVal;
        this.y2 = tempVal2;
    }

    public int getBulletLife(){
        return bulletLife;
    }

    public void increaseBulletLife(){
        bulletLife += 1;
    }

    public void resetBulletLife(){
        bulletLife = 0;
    }

    public int getXPos(){
        return boxX;
    }

    public int getYPos(){
        return boxY;
    }

    public int getWidth(){
        return boxWidth;
    }

    public int getHeight(){
        return boxHeight;
    }

    public boolean isCollidingBullet(Platform other){

        boxX = Math.min(x1, x2);
        boxY = Math.min(y1, y2);
        boxWidth = Math.abs(x2 - x1);
        boxHeight = Math.abs(y2 - y1);

        if (boxX + boxWidth < other.getXPos() ||
            boxX >= other.getXPos() + other.getWidth() ||
            boxY + boxHeight <= other.getYPos() ||
            boxY >= other.getYPos() + other.getHeight()){
            return false;
            }
        else{
            return true;
            }
        }
    }
