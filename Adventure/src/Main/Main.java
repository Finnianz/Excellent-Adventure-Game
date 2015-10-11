package Main;

import Game.Gameplay;
import UI.GameFrame;
import render.RenderCanvas;

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
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static final int DEFAULT_CLK_PERIOD = 20;
	private static final int DEFAULT_BROADCAST_CLK_PERIOD = 5;
	private static Socket client;
	private static OutputStreamWriter osw;

	public static void main(String[] args) {
		boolean server = false;
		int nclients = 0;
		int port = 36768; // default

		try {
			GameFrame frame = new GameFrame();

		} catch (Exception e) {
			e.printStackTrace();
		}

		startGame();
	}

	public static void startGame() {
		List<Game.Character> characters = new ArrayList<Game.Character>();

		Gameplay game = new Gameplay(characters);

		RenderCanvas renderCanv = new RenderCanvas();
		game.setCanvas(renderCanv);

		GameFrame gameFrame = new GameFrame();
		game.setFrame(gameFrame);

		game.getFrame().getC().getRenderCanvas().setRoom(game.getRooms().get(0));

	}

	public static void joinGame(int port) {
		Socket s;
		try {
			s = new Socket("localhost", port);
			new Slave(s).run();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * Socket s; DataInputStream din; DataOutputStream dout;
		 * 
		 * try { client = new Socket("localhost", port); osw = new
		 * OutputStreamWriter(client.getOutputStream());
		 * 
		 * } catch (UnknownHostException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */
	}

	public static void hostGame(int port) {

		try {

			// Now, we await connections.
			ServerSocket ss = new ServerSocket(port);
			while (1 == 1) {
				// Wait for a socket
				Socket s = ss.accept();

				Master connection = new Master(s);
				connection.start();
				System.out.println("whhoooohooooo");
				return; // done
			}

		} catch (IOException e)

		{
			System.err.println("I/O error: " + e.getMessage());
		}

		/*
		 * new Thread(new Runnable() {
		 * 
		 * @Override public void run() {
		 * 
		 * try {
		 * 
		 * ServerSocket server = new ServerSocket(port); client =
		 * server.accept();
		 * 
		 * BufferedReader br = new BufferedReader(new
		 * InputStreamReader(client.getInputStream())); String line = "";//
		 * testing JOptionPane.showMessageDialog(null, "Connection Success! " +
		 * line + '!');
		 * 
		 * while (true) { line = br.readLine();// just for testing if (line !=
		 * null) { JOptionPane.showInputDialog(null, "Message Received", "" +
		 * line); } }
		 * 
		 * } catch (IOException e) { e.printStackTrace(); } } }).start();
		 */
	}
}
