
package Main;

import java.util.*;
import java.io.*;
import java.net.*;

import Game.*;
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
	private final GameFrame frame;
	private Gameplay game;

	public Master(Socket socket, GameFrame f, Gameplay g) {
		this.socket = socket;
		frame = f;
		game = g;
	}

	@Override
	public void run() {
		try {

			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			GameFrame frame = new GameFrame();
			oos.writeObject(frame);

			oos.writeObject(game);

			oos.close();
			socket.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}