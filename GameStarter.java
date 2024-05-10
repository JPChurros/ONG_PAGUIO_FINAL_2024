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

// This class contains the main method that will start the game from the
// player's
// side.
public class GameStarter {
    public static void main(String[] args) {
        GameFrame gf = new GameFrame(800, 600); // arbitrary
        gf.connectToServer();
        gf.setUpGUI();

    }
}
