package Main;

import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.*;

import Game.*;
import UI.*;

/**
 * A slave connection
 */

public final class Slave implements Runnable, KeyListener {

	private Socket socket;
	private Gameplay game;
	private DataOutputStream output;
	private DataInputStream input;
	// Name??

	public Slave(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			Socket s = socket;
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			GameFrame frame = (GameFrame) ois.readObject();
			if (frame != null) {
				System.out.println("Frame Arrived");
			}

			is.close();
			s.close();

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace(System.err);
		}
	}
	// The following intercept keyboard events from the user.

	public void keyPressed(KeyEvent e) {
		try {
			int code = e.getKeyCode();
			if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_KP_RIGHT) {
				output.writeInt(3);

			} else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_KP_LEFT) {
				output.writeInt(4);

			} else if (code == KeyEvent.VK_UP) {
				output.writeInt(1);

			} else if (code == KeyEvent.VK_DOWN) {
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
}