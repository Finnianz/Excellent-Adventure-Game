package render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RenderCanvas extends JPanel {
	private Isometric.Compass direction = Isometric.Compass.NORTH;
	public Isometric renderThingy = new Isometric();
	private Floor[][] board = new Floor[4][4];
	private final String IMG = "resource/FloorBlock";

	public RenderCanvas(){
		for(int i=0;i<board.length;i++){
			for(int j= 0;j<board.length;j++){
				board[i][j] = new Floor(IMG);
			}
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(600, 600);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		switch (direction) {
		case NORTH:
			renderThingy.northDraw(board, g);
			break;
		case EAST:
			renderThingy.eastDraw(board, g);
			break;
		case SOUTH:
			renderThingy.southDraw(board, g);
			break;
		case WEST:
			renderThingy.westDraw(board, g);
			break;
		}

	}

}
