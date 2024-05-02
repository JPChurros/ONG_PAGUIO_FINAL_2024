import java.awt.*;
import java.awt.geom.*;

public class Bullet {
    int x1, y1, x2, y2, xSpeed, ySpeed, charType, xVariable, yVariable, bulletLife, constantSpeed;
    Color color;
    public Bullet(int x1, int y1, int x2, int y2, int xSpeed, int ySpeed, Color color){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.color = color;
        constantSpeed = 300;
    }

    public void draw(Graphics2D g){
        Line2D.Double temp = new Line2D.Double(x1, y1, x2, y2);
    }

    public void shootMove(int xVariable, int yVariable){
        this.xVariable = xVariable;
        this.yVariable = yVariable;
    }
}
