
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

				if (!input.readUTF().equals(null)) {

					// read character selection from client.
					String name = input.readUTF();
					String color = input.readUTF();
					String hat = input.readUTF();
					Main.characters.add(new Character(color + "Ghost", hat + "Hat", name));

				}

				game = new Gameplay(Main.characters);
			

				// Now, broadcast the server character to client

				output.writeUTF(game.getFrame().getName());
				output.writeUTF(game.getFrame().getPlayerColour());
				output.writeUTF(game.getFrame().getPlayerHat());

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