package Main;

import Game.Gameplay;
import UI.GameFrame;
import render.RenderCanvas;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	private static final int DEFAULT_CLK_PERIOD = 20;
	private static final int DEFAULT_BROADCAST_CLK_PERIOD = 5;
	
	
	

	public static void main(String[] args) {
		 boolean server = false;
		 int nclients = 0;	
		 int port = 36768; // default
		 GameFrame frame = new GameFrame();
		
		//startGame();
	}

	
	
	public static void startGame() {
		List<Game.Character> characters = new ArrayList<Game.Character>();
		
		
		
		
		
		
		
		
		Gameplay game = new Gameplay(characters);
		
		
		
		RenderCanvas renderCanv = new RenderCanvas();
		game.setCanvas(renderCanv);
		
		GameFrame gameFrame = new GameFrame();
		game.setFrame(gameFrame);
		
		
		
		
		game.getFrame().getC().getRenderCanvas().setRoom(game.getRooms().get(0).getFloor());

	}
}
