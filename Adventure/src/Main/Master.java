
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

	public Master(Socket socket) {
		this.socket = socket;

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

			// read character selection from client.
			String color = input.readUTF();
			String hat = input.readUTF();
			Main.characters.add(new Character(color + "Ghost", hat + "Hat", "Player1"));

			// System.out.println("??" + color + hat);
			Main.game = new Gameplay(Main.characters, true);
			Main.game.setFrame(frame);

			// Now, broadcast the server character to client

			output.writeUTF(Main.game.getFrame().getPlayerColour());
			output.writeUTF(Main.game.getFrame().getPlayerHat());

			// first read char selection from client
			boolean exit = false;
			while (!exit) {

				if (input.available() != 0) {

					// read direction event from client and return x,y for
					// movement.
					int dir = input.readInt();
					switch (dir) {
					case 1:
						Main.moveUp(1);
						
						output.writeInt(Main.game.getCharacters().get(1).getCurrentLocation().getX());
						output.writeInt(Main.game.getCharacters().get(1).getCurrentLocation().getY());
						break;
					case 2:
						Main.moveDown(1);
						output.writeInt(Main.game.getCharacters().get(1).getCurrentLocation().getX());
						output.writeInt(Main.game.getCharacters().get(1).getCurrentLocation().getY());
						break;
					case 3:
						Main.moveRight(1);
						output.writeInt(Main.game.getCharacters().get(1).getCurrentLocation().getX());
						output.writeInt(Main.game.getCharacters().get(1).getCurrentLocation().getY());
						break;
					case 4:
						Main.moveLeft(1);
						output.writeInt(Main.game.getCharacters().get(1).getCurrentLocation().getX());
						output.writeInt(Main.game.getCharacters().get(1).getCurrentLocation().getY());
						break;
					}
				}

				output.flush();
				// now render game
				RenderCanvas renderCanv = new RenderCanvas();
				Main.game.setCanvas(renderCanv);

				Main.game.getFrame().getC().getRenderCanvas()
						.setRoom(Main.game.getCharacters().get(0).getCurrentRoom());
				Main.game.getFrame().getC().repaint();

			}
			socket.close(); // release socket ... v.important!
		} catch (

		IOException e)

		{
			System.err.println("PLAYER DISCONNECTED");

		}
	}
}