package Main;

import java.util.*;
import java.io.*;
import java.net.*;

import Game.*;
import UI.*;

/**
 * A slave connection
 */

public final class Slave {

	private Socket socket;

	public Slave(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		System.out.println("It works!!");
	}
}