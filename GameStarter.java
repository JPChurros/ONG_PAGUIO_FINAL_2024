import java.util.Scanner;

/**
 * @author Charles Matthew L. Ong (234579)
 * @author Gabriel Syd O. Paguio (234725)
 * @version May 11, 2024
 * 
 *          I have not discussed the Java language code in my program
 *          with anyone other than my instructor or the teaching assistants
 *          assigned to this course.
 * 
 *          I have not used Java language code obtained from another student,
 *          or any other unauthorized source, either modified or unmodified.
 * 
 *          If any Java language code or documentation used in my program
 *          was obtained from another source, such as a textbook or website,
 *          that has been clearly noted with a proper citation in the comments
 *          of my program.
 **/

// This class contains the main method that instantiates the gameframe and
// connects it to server. It is the class that is called upon to allow both
// players to start playing.
public class GameStarter {
    // Main method instantiates gameframe, connects it to server and sets up the
    // gui.
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("IP Address: ");
        String ipAddress = console.nextLine();
        System.out.print("Port Number: ");
        int portNumber = Integer.parseInt((console.nextLine()));
        new PreGame(new ButtonClickListener() {
            public void onButtonClick(int buttonIndex) {
                startGameFrame(buttonIndex, ipAddress, portNumber);
            }
        });

    }

    // Method to start GameFrame
    private static void startGameFrame(int playerChoice, String IP, int Port) {
        GameFrame gf = new GameFrame(1000, 600, playerChoice); // arbitrary
        gf.connectToServer(IP, Port);
        gf.setUpGUI();
    }
}
