import java.awt.*;
import javax.swing.*;

public class Hearts {
    public int currentHealth, x, y, width, height;
    public Image imageNow, health1, health2, health3;
    
    public Hearts(int x, int y){
        this.x = x;
        this.y = y;
        width = 39;
        height = 80;
        imageNow = new ImageIcon("3HP.png").getImage();
    }

    public void setHP1(){
        imageNow = new ImageIcon("1HP.png").getImage();
    }
    public void setHP2(){
        imageNow = new ImageIcon("2HP.png").getImage();
    }
    public void setHP3(){
        imageNow = new ImageIcon("3HP.png").getImage();
    }

    public void draw(Graphics2D g){
        g.drawImage(imageNow, this.x, this.y, width, height, null);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
    
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
}
