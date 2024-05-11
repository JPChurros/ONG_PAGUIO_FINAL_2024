import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PreGame extends JFrame {
    private JButton button1, button2, button3;
    private JLabel label1, label2, label3;

    public PreGame(){
        setTitle("Choose Your Character");
        setSize(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon1 = new ImageIcon("catpistol_RIGHT.png");
        ImageIcon icon2 = new ImageIcon("hedgehog_RIGHT.png");
        ImageIcon icon3 = new ImageIcon("squirrel_RIGHT.png");

        JLabel iconLabel1 = new JLabel(icon1);
        JLabel iconLabel2 = new JLabel(icon2);
        JLabel iconLabel3 = new JLabel(icon3);

        button1 = new JButton("Cat");
        button2 = new JButton("Hedgehog");
        button3 = new JButton("Squirrel");

        Dimension buttonSize = new Dimension(100, 25);

        button1.setPreferredSize(buttonSize);
        button2.setPreferredSize(buttonSize);
        button3.setPreferredSize(buttonSize);

        label1 = new JLabel("<html><br>Uses a rifle <br>Shoots long range<br>Medium Shooting Speed<br>Magazine Size = 3 <br>SPECIAL: None</html>");
        label2 = new JLabel("<html><br>Uses a shotgun <br>Shoots close range<br>Slow Shooting Speed<br>Magazine Size = 2<br>SPECIAL: shoots 3 at once</html>");
        label3 = new JLabel("<html><br>Uses a submachine gun <br>Shoots mid range<br>Fast Shooting Speed<br>Magazine Size = 7<br>SPECIAL: None</html>");

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel row4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel row5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel row6 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        row1.add(button1);
        row2.add(button2);
        row3.add(button3);
        row4.add(label1);
        row5.add(label2);
        row6.add(label3);

        JPanel gridPanel1 = new JPanel(new FlowLayout());
        
        gridPanel1.add(button1);
        gridPanel1.add(label1);
        gridPanel1.add(iconLabel1);

        JPanel gridPanel2 = new JPanel(new FlowLayout());
        
        gridPanel2.add(button2);
        gridPanel2.add(label2);
        gridPanel2.add(iconLabel2);

        JPanel gridPanel3 = new JPanel(new FlowLayout());
        
        gridPanel3.add(button3);
        gridPanel3.add(label3);
        gridPanel3.add(iconLabel3);

        JPanel mainPanel = new JPanel(new GridLayout(1, 3));

        mainPanel.add(gridPanel1);
        mainPanel.add(gridPanel2);
        mainPanel.add(gridPanel3);

        add(mainPanel);

        button1.addActionListener(new ButtonClickListener());
        button2.addActionListener(new ButtonClickListener());
        button3.addActionListener(new ButtonClickListener());

        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            JButton source = (JButton)e.getSource();

            if(source == button1){

            }
            else if(source == button2){

            }
            else if(source == button3){

            }
        }
    }
    public static void main(String[] args) {
        new PreGame();
    }
}
