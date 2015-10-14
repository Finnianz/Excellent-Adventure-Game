
package Main;

import java.util.*;
import java.io.*;
import java.net.*;

import Game.*;
import Game.Character;
import UI.*;
import render.RenderCanvas;

/**
 * A master connection receives events from a slave connection via a socket.
 * These events are registered with the board. The master connection is also
 * responsible for transmitting information to the slave about the current board
 * state.
 */
public final class Master implements Runnable {

	private final Socket socket;

	private Gameplay game;

	public Master(Socket socket, Gameplay g) {
		this.socket = socket;
		game = g;
	}

	@Override
	public void run() {
		System.out.println("From Server with Love : ");

		try {
			GameFrame frame = new GameFrame();
			frame.inputPlayers();
			Main.characters.add(
					new Character(frame.getPlayerColour() + "Ghost", frame.getPlayerHat() + "Hat", frame.getName()));
			DataInputStream input = new DataInputStream(socket.getInputStream());
			DataOutputStream output = new DataOutputStream(socket.getOutputStream());

			// first read char selection from client
			boolean exit = false;
			while (!exit) {

				// read character selection from client.
				String color = input.readUTF();
				String hat = input.readUTF();
				Main.characters.add(new Character(color + "Ghost", hat + "Hat", "Player1"));

				// System.out.println("??" + color + hat);
				game = new Gameplay(Main.characters, true);
				game.setFrame(frame);

				// Now, broadcast the server character to client

				output.writeUTF(game.getFrame().getPlayerColour());
				output.writeUTF(game.getFrame().getPlayerHat());

				// now render game
				RenderCanvas renderCanv = new RenderCanvas();
				game.setCanvas(renderCanv);

				game.getFrame().getC().getRenderCanvas().setRoom(game.getRooms().get(0));
				game.getFrame().getC().repaint();

				if (input.available() != 0) {

					// read direction event from client.
					int dir = input.readInt();
					switch (dir) {
					case 1:
						Main.moveUp(1);
						break;
					case 2:
						Main.moveDown(1);
						break;
					case 3:
						Main.moveRight(1);
						break;
					case 4:
						Main.moveLeft(1);
						break;
					}
				}

				output.flush();

			}
			socket.close(); // release socket ... v.important!
		} catch (

		IOException e)

		{
			System.err.println("PLAYER DISCONNECTED");

		}
	}
}