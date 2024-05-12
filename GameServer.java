
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

import java.io.*;
import java.net.*;

//This Class is in charge of handling the server (and reading/writing from the players.). It contains the Main method that starts and initiates the server.

public class GameServer {
    private ServerSocket ss;
    private int numPlayers;
    private int maxPlayers;

    private Socket p1Socket;
    private Socket p2Socket;
    private ReadFromClient p1ReadRunnable, p2ReadRunnable;
    private WriteToClient p1WriteRunnable, p2WriteRunnable;

    private int p1x, p1y, p2x, p2y, p1LR, p2LR, p1mouseHeld, p2mouseHeld, p1mouseX, p2mouseX, p1mouseY,
            p2mouseY, p1cType, p2cType;

    // Constructor initializes the starting values of changing values (such as the
    // players x and y position, and where they hold their mouse)
    public GameServer() {
        System.out.println("==== GAME SERVER ====");
        numPlayers = 0;
        maxPlayers = 2;
        p1x = 100;
        p2x = 680;
        p1y = 100;
        p2y = 100;
        p1LR = 1;
        p2LR = 0;

        p1mouseHeld = 0;
        p2mouseHeld = 0;
        p1mouseX = 0;
        p2mouseX = 0;
        p1mouseY = 0;
        p2mouseY = 0;

        try {
            ss = new ServerSocket(55555); // SOCKET HERE
        } catch (BindException bindException) {
            System.out.println("Socket already in use, please pick a new one.");
        } catch (IOException ie) {
            System.out.println("IOException from GameServer constructor");
            ie.printStackTrace();
        }
    }

    // Here the server is waiting for connections. Once both players have connected,
    // it stops accepting connections.
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

                ReadFromClient rfc = new ReadFromClient(numPlayers, in); // in is input stream
                WriteToClient wtc = new WriteToClient(numPlayers, out);

                if (numPlayers == 1) {
                    p1Socket = s;
                    p1ReadRunnable = rfc;
                    p1WriteRunnable = wtc;
                } else {
                    p2Socket = s;
                    p2ReadRunnable = rfc;
                    p2WriteRunnable = wtc;

                    p1WriteRunnable.sendStartMsg(); // sends start message so that once both connect we cna play!
                    p2WriteRunnable.sendStartMsg();
                    Thread readThreadPlayer1 = new Thread(p1ReadRunnable);
                    Thread readThreadPlayer2 = new Thread(p2ReadRunnable);
                    readThreadPlayer1.start();
                    readThreadPlayer2.start();

                    Thread writeThreadPlayer1 = new Thread(p1WriteRunnable);
                    Thread writeThreadPlayer2 = new Thread(p2WriteRunnable);
                    writeThreadPlayer1.start();
                    writeThreadPlayer2.start();
                }

            }
            System.out.println("No longer accepting Connections");
        } catch (IOException ie) {
            System.out.println("IOException from GameServer acceptConnections");
        }
    }

    // This class is in charge of reading values from the client. It receives input
    // from the GameFrames WriteToServer class.
    private class ReadFromClient implements Runnable {
        private int playerID;
        private DataInputStream dataIn;

        // Constructor Initializes values.
        public ReadFromClient(int PID, DataInputStream in) {
            playerID = PID;
            dataIn = in;
            System.out.println("RFC" + playerID + "Runnable Created");
        }

        // This is where it perpetually reads the players x and y positions aswell as
        // other important values.
        public void run() {
            try {
                while (true) {
                    if (playerID == 1) {
                        p1x = dataIn.readInt();
                        p1y = dataIn.readInt();
                        p1LR = dataIn.readInt();
                        p1mouseHeld = dataIn.readInt();
                        p1mouseX = dataIn.readInt();
                        p1mouseY = dataIn.readInt();
                        p1cType = dataIn.readInt();
                    } else {
                        p2x = dataIn.readInt();
                        p2y = dataIn.readInt();
                        p2LR = dataIn.readInt();
                        p2mouseHeld = dataIn.readInt();
                        p2mouseX = dataIn.readInt();
                        p2mouseY = dataIn.readInt();
                        p2cType = dataIn.readInt();
                    }
                }
            } catch (IOException ex) {
                System.out.println("IOException from RFC run() in GameServer");
            }
        }
    }

    // This class is in charge of writing out the enemy's values to the client. It
    // does this by sending player2's values to player1 and vice versa.
    private class WriteToClient implements Runnable {
        private int playerID;
        private DataOutputStream dataOut;

        // Constructor initializes Values
        public WriteToClient(int PID, DataOutputStream out) {
            playerID = PID;
            dataOut = out;
            System.out.println("WTC" + playerID + "Runnable Created");
        }

        // This is where it writes to the client the enemy's positions and other
        // pertinent information.
        public void run() {
            try {
                while (true) {
                    if (playerID == 1) {
                        dataOut.writeInt(p2x);
                        dataOut.writeInt(p2y);
                        dataOut.writeInt(p2LR);
                        dataOut.writeInt(p2mouseHeld);
                        dataOut.writeInt(p2mouseX);
                        dataOut.writeInt(p2mouseY);
                        dataOut.writeInt(p2cType);
                        dataOut.flush();
                    } else {
                        dataOut.writeInt(p1x);
                        dataOut.writeInt(p1y);
                        dataOut.writeInt(p1LR);
                        dataOut.writeInt(p1mouseHeld);
                        dataOut.writeInt(p1mouseX);
                        dataOut.writeInt(p1mouseY);
                        dataOut.writeInt(p1cType);
                        dataOut.flush();
                    }
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException IE) {
                        System.out.println("InterruptedException from WTC run() in gameServer");
                    }
                }
            } catch (IOException ex) {
                System.out.println("IOException from WTC run() in GameServer");
            }
        }

        // This method essentially lets us wait for both players to join the game before
        // starting up the gameframe.
        public void sendStartMsg() {
            try {
                dataOut.writeUTF("We now have two players. Go!");
            } catch (IOException ie) {
                System.out.println("IOException in sendStartMsg() in GameServer");
            }
        }
    }

    // Main method instantiates the gameserver and lets it accept connections.
    public static void main(String[] args) {
        GameServer gs = new GameServer();
        gs.acceptConnections();
    }
}
