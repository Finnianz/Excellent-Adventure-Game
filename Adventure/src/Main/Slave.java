package Main;

//Team 19
//Finnian Dempsey 300276760
import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.*;

import Game.*;
import Game.Character;
import UI.*;
import render.DrawableTile;
import render.RenderCanvas;

/**
 * A slave connection receives information about the current state of the board
 * and relays that into the local copy of the game. The slave connection also
 * notifies the master connection of key presses and mouse clicks by the player.
 */

public final class Slave implements Runnable, KeyListener, MouseListener {

	private Socket socket;
	private DataOutputStream output;
	private DataInputStream input;
	// Name??

	/**
	 * Construct a slave connection from a socket. A slave connection does no
	 * local computation, other than to render the changes given from the server
	 * 
	 * @param socket
	 * 
	 */

	public Slave(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		System.out.println("From Client with Love : ");

		try {

			Socket s = socket;
			Character player1;

			GameFrame frame = new GameFrame();
			frame.inputPlayers();

			output = new DataOutputStream(socket.getOutputStream());
			input = new DataInputStream(socket.getInputStream());
			// System.out.println("elllo?");

			// first write char selection to server
			output.writeUTF(frame.getPlayerColour());
			output.writeUTF(frame.getPlayerHat());

			// read character selection from server.
			String color = input.readUTF();
			String hat = input.readUTF();
			player1 = new Character(color + "Ghost", hat + "Hat", "Player1");

			Main.characters.add(player1);
			Main.characters
					.add(new Character(frame.getPlayerColour() + "Ghost", frame.getPlayerHat() + "Hat", "Player2"));
			Main.game = new Gameplay(Main.characters, true);
			Main.game.setFrame(frame);
			// and render game
			RenderCanvas renderCanv = new RenderCanvas();
			Main.game.setCanvas(renderCanv);
			Main.game.getFrame().getC().getRenderCanvas().setRoom(Main.game.getCharacters().get(0).getCurrentRoom());
			Main.game.getFrame().getC().repaint();

			boolean exit = false;
			while (!exit) {
				// read event of keypress
				int x = input.readInt();
				int y = input.readInt();

				Main.game.getCharacters().get(1).setCurrentLocation(Main.game.getRooms().get(0).getFloor()[x][y]);
				Main.game.getFrame().getC().repaint();
			}

			socket.close(); // release socket ... v.important!
		} catch (

		IOException e)

		{
			System.err.println("I/O Error: " + e.getMessage());
			e.printStackTrace(System.err);
		}

		catch (

		Exception e)

		{
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace(System.err);
		}

	}
	// The following intercept keyboard events from the user.

	public void keyPressed(KeyEvent e) {
		try {
			int code = e.getKeyCode();
			if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_KP_RIGHT) {
				output.writeUTF("button");
				output.writeInt(3);

			} else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_KP_LEFT) {
				output.writeUTF("button");
				output.writeInt(4);

			} else if (code == KeyEvent.VK_UP) {
				output.writeUTF("button");
				output.writeInt(1);

			} else if (code == KeyEvent.VK_DOWN) {
				output.writeUTF("button");
				output.writeInt(2);
			}
			output.flush();
		} catch (IOException ioe) {
			// something went wrong trying to communicate the key press to the
			// server. So, we just ignore it.
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			output.writeUTF("click");
			output.writeInt(e.getX());
			output.writeInt(e.getY());

		} catch (IOException ioe) {
			// something went wrong trying to communicate the click to the
			// server. So, we just ignore it.
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}