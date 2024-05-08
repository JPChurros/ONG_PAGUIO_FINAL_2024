
//This class contains the code that manages the game server's functionality. It also
//contains the main method that instantiates and starts the server.
import java.io.*;
import java.net.*;

public class GameServer {
    private ServerSocket ss;
    private int numPlayers;
    private int maxPlayers;

    public GameServer() {
        System.out.println("==== GAME SERVER ====");
        numPlayers = 0;
        maxPlayers = 2;

        try {
            ss = new ServerSocket(12345); // SOCKET HERE
        } catch (BindException bindException) {
            System.out.println("Socket already in use, please pick a new one.");
        } catch (IOException ie) {
            System.out.println("IOException from GameServer constructor");
            ie.printStackTrace();
        }
    }

    public void acceptConnections() {
        try {
            System.out.println("Waiting for Connections");

            while (numPlayers < maxPlayers) {
                Socket s = ss.accept(); // begin accepting connections.
                DataInputStream in = new DataInputStream(s.getInputStream());
                DataOutputStream out = new DataOutputStream(s.getOutputStream());
                numPlayers++;
                out.writeInt(numPlayers); // writes out the numPlayers.
                System.out.println("Player #" + numPlayers + " has connected");
            }
            System.out.println("No longer accepting Connections");
        } catch (IOException ie) {
            System.out.println("IOException from GameServer acceptConnections");
        }
    }

    public static void main(String[] args) {
        GameServer gs = new GameServer();
        gs.acceptConnections();
    }
}
