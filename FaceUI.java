import java.awt.*;
import javax.swing.*;


public class FaceUI {

    public int x, y, x2, y2, width, height;
    public Image p1Face, p2Face;

    public FaceUI(int x, int y, int x2, int y2){
        this.x = x + 49;
        this.y = y + 7;
        this.x2 = x2 - 76;
        this.y2 = y2 + 7;
        width = 66;
        height = 66;
        p1Face = new ImageIcon("catP1Blue.png").getImage();
        p2Face = new ImageIcon("catP2Red.png").getImage();
    }

    public void setChars(int ID, int p1Char, int p2Char){
        if(ID == 1){
            if(p1Char == 0){
                p1Face = new ImageIcon("catP1Blue.png").getImage();
            }
            if(p1Char == 1){
                p1Face = new ImageIcon("hedgehogP1Blue.png").getImage();
            }
            if(p1Char == 2){
                p1Face = new ImageIcon("squirrelP1Blue.png").getImage();
            }
            if(p2Char == 0){
                p2Face = new ImageIcon("catP2Red.png").getImage();
            }
            if(p2Char == 1){
                p2Face = new ImageIcon("hedgehogP2Red.png").getImage();
            }
            if(p2Char == 2){
                p2Face = new ImageIcon("squirrelP2Red.png").getImage();
            }
        }

        else if (ID == 2){
            if(p1Char == 0){
                p1Face = new ImageIcon("catP1Red.png").getImage();
            }
            if(p1Char == 1){
                p1Face = new ImageIcon("hedgehogP1Red.png").getImage();
            }
            if(p1Char == 2){
                p1Face = new ImageIcon("squirrelP1Red.png").getImage();
            }
            if(p2Char == 0){
                p2Face = new ImageIcon("catP2Blue.png").getImage();
            }
            if(p2Char == 1){
                p2Face = new ImageIcon("hedgehogP2Blue.png").getImage();
            }
            if(p2Char == 2){
                p2Face = new ImageIcon("squirrelP2Blue.png").getImage();
            }
        }
    }

    public void draw(Graphics2D g){
        g.drawImage(p1Face, this.x, this.y, width, height, null);
        g.drawImage(p2Face, this.x2, this.y2, width, height, null);
    }
}
