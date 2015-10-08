
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
public final class Master extends Thread {

	private final Socket socket;

	public Master(Socket socket) {
		this.socket = socket;

	}

}