
package Main;

import java.util.*;
import java.io.*;
import java.net.*;

import Game.*;
import UI.*;

/**
 * A master connection receives events from a slave connection via a socket.
 * These events are registered with the board. The master connection is also
 * responsible for transmitting information to the slave about the current board
 * state.
 */
public final class Master implements Runnable {

	private final Socket socket;
	private final GameFrame frame;

	public Master(Socket socket, GameFrame f) {
		this.socket = socket;
		frame = f;
	}

	@Override
	public void run() {
		try {

			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			GameFrame frame = new GameFrame();
			oos.writeObject(frame);

			oos.close();
			socket.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}