package Game;

import UI.GameFrame;
import render.RenderCanvas;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	private static final int DEFAULT_CLK_PERIOD = 20;
	private static final int DEFAULT_BROADCAST_CLK_PERIOD = 5;
	
	
	
	public static void main(String[] args) {
		int gameClock = DEFAULT_CLK_PERIOD;
		int broadcastClock = DEFAULT_BROADCAST_CLK_PERIOD;
		
		
		
		
		
		
		
		
		startGame();

	}

	public static void startGame() {

		Gameplay game = new Gameplay();
		game.setCanvas(new RenderCanvas());
		game.setFrame(new GameFrame());
		game.getFrame().getC().getRenderCanvas().setRoom(game.getRooms().get(0).getFloor());

	}

}
