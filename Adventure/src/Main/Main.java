package Main;

import Game.Character;
import Game.Gameplay;
import Game.Location;
import UI.GameFrame;
import render.RenderCanvas;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {
	private static Socket client;
	private static OutputStreamWriter osw;

	public static InetAddress serverIp;

	private static Gameplay game;
	private static GameFrame frame;
	static List<Game.Character> characters;

	public static void main(String[] args) throws IOException {

		// ======================================================
		// ======== First, parse command-line arguments =========
		// ======================================================
		boolean server = false;
		String url = null;
		int port = 7778; // default
		for (int i = 0; i != args.length; ++i) {
			if (args[i].startsWith("-")) {
				String arg = args[i];
				if (arg.equals("-server")) {
					server = true;
				} else if (arg.equals("-connect")) {
					url = args[++i];
					port = Integer.parseInt(args[++i]);
				} else if (arg.equals("-single")) {
					startGame();
				}
			}

			if (url != null && server) {
				System.out.println("Cannot be a server and connect to another server!");
				System.exit(1);
			}

			if (server) {
				// Run in Server mode
				try {
					runServer(port);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (url != null) {
				// Run in client mode
				try {
					runClient(url, port);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private static void runClient(String url, int port) throws Exception {
		Socket s = new Socket(url, port);
		System.out.println("From Client with Love : ");

		new Slave(s).run();

		/*
		 * // extra for testing InputStream obj = s.getInputStream();
		 * BufferedReader br = new BufferedReader(new InputStreamReader(obj));
		 * 
		 * String str;
		 * 
		 * while ((str = br.readLine()) != null) { System.out.println(
		 * "From Server with Love : " + str); }
		 * 
		 * br.close(); s.close();
		 */

	}

	private static void runServer(int port) throws Exception {

		try {
			Master connection;
			// Now, we await connections.
			ServerSocket ss = new ServerSocket(port);
			System.out.println("From Server with Love : ");

			while (1 == 1) {
				// Wait for a socket
				Socket s = ss.accept();
				System.out.println("ACCEPTED CONNECTION FROM: " + s.getInetAddress());

				connection = new Master(s, game);
				connection.run();

				return; // done
			}
		} catch (IOException e) {
			System.err.println("I/O error: " + e.getMessage());
		}

		/*
		 * ServerSocket ss = new ServerSocket(7777); Socket s = ss.accept();
		 * System.out.println("Connection Established"); OutputStream obj =
		 * s.getOutputStream(); PrintStream ps = new PrintStream(obj); String
		 * str = "Hello Cleint"; ps.println(str); ps.println("bye"); ps.close();
		 * ss.close(); s.close();
		 */
	}

	public static void startGame() {
		characters = new ArrayList<Character>();
		// add player
		frame = new GameFrame();
		frame.inputPlayers();

		characters.add(new Character(frame.getPlayerColour() + "Ghost", frame.getPlayerHat() + "Hat", frame.getName()));
		game = new Gameplay(characters);

		RenderCanvas renderCanv = new RenderCanvas();
		game.setCanvas(renderCanv);

		game.setFrame(frame);

		game.getFrame().getC().getRenderCanvas().setRoom(game.getRooms().get(0));
		game.getFrame().getC().repaint();
	}

	public static void joinGame(String server, int port) {
		Socket s;
		try {
			s = new Socket(server, port);
			new Slave(s).run();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void hostGame(int port) {

		try {
			// Now, we await connections.
			ServerSocket ss = new ServerSocket(port);
			while (1 == 1) {
				// Wait for a socket
				Socket s = ss.accept();

				Master connection = new Master(s, game);
				return; // done
			}

		} catch (IOException e)

		{
			System.err.println("I/O error: " + e.getMessage());
		}
	}

	public static void moveLeft() {
		RenderCanvas.Compass direction = game.getFrame().getC().getRenderCanvas().getDirection();
		switch (direction) {
		case NORTH:
			game.moveWest(game.getCharacters().get(0));
			break;
		case SOUTH:
			game.moveEast(game.getCharacters().get(0));
			break;
		case EAST:
			game.moveNorth(game.getCharacters().get(0));
			break;
		case WEST:
			game.moveSouth(game.getCharacters().get(0));
			break;
		}
		game.getFrame().getC().repaint();

	}

	public static void moveRight() {
		RenderCanvas.Compass direction = game.getFrame().getC().getRenderCanvas().getDirection();
		switch (direction) {
		case NORTH:
			game.moveEast(game.getCharacters().get(0));
			break;
		case SOUTH:
			game.moveWest(game.getCharacters().get(0));
			break;
		case EAST:
			game.moveSouth(game.getCharacters().get(0));
			break;
		case WEST:
			game.moveNorth(game.getCharacters().get(0));
			break;
		}
		game.getFrame().getC().repaint();

	}

	public static void moveUp() {
		RenderCanvas.Compass direction = game.getFrame().getC().getRenderCanvas().getDirection();
		switch (direction) {
		case NORTH:
			game.moveNorth(game.getCharacters().get(0));
			break;
		case SOUTH:
			game.moveSouth(game.getCharacters().get(0));
			break;
		case EAST:
			game.moveEast(game.getCharacters().get(0));
			break;
		case WEST:
			game.moveWest(game.getCharacters().get(0));
			break;
		}
		game.getFrame().getC().repaint();

	}

	public static void moveDown() {
		RenderCanvas.Compass direction = game.getFrame().getC().getRenderCanvas().getDirection();
		switch (direction) {
		case NORTH:
			game.moveSouth(game.getCharacters().get(0));
			break;
		case SOUTH:
			game.moveNorth(game.getCharacters().get(0));
			break;
		case EAST:
			game.moveWest(game.getCharacters().get(0));
			break;
		case WEST:
			game.moveEast(game.getCharacters().get(0));
			break;
		}
		game.getFrame().getC().repaint();

	}
}
