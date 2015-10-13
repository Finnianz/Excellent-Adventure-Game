package Main;

import Game.Gameplay;
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

	public static void main(String[] args) throws IOException {
		// ======================================================
		// ======== First, parse command-line arguments =========
		// ======================================================
		boolean server = false;
		String url = null;
		int port = 7777; // default
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
		Socket s = new Socket("localhost", 7777);

		InputStream obj = s.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(obj));

		String str;

		while ((str = br.readLine()) != null) {
			System.out.println("From Server with Love : " + str);
		}

		br.close();
		s.close();

	}

	private static void runServer(int port) throws Exception {
		ServerSocket ss = new ServerSocket(7777);
		Socket s = ss.accept();
		System.out.println("Connection Established");
		OutputStream obj = s.getOutputStream();
		PrintStream ps = new PrintStream(obj);
		String str = "Hello Cleint";
		ps.println(str);
		ps.println("bye");
		ps.close();
		ss.close();
		s.close();
	}

	public static void startGame() {
		List<Game.Character> characters = new ArrayList<Game.Character>();
		Gameplay game = new Gameplay(characters);

		RenderCanvas renderCanv = new RenderCanvas();
		game.setCanvas(renderCanv);

		GameFrame frame = new GameFrame();
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

				Master connection = new Master(s);
				connection.start();
				return; // done
			}

		} catch (IOException e)

		{
			System.err.println("I/O error: " + e.getMessage());
		}
	}
}
