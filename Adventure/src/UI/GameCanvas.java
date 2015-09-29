package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Game.Game;
import render.RenderCanvas;
import render.RenderFrame;




public class GameCanvas extends JPanel {
	private Game game;
	private JPanel outerPanel;
	private RenderFrame GamePanel;
	private JLabel[][] grid;
	private int xClick = -1;
	private int yClick = -1;
	private final int width = 26;
	private final int height = 27;

	/**
	 * Creates a new Canvas and sets up the board
	 * @param gui 
	 */
	public GameCanvas() {
		setLayout(new BorderLayout());
		outerPanel = new JPanel();
		outerPanel.setSize(1500, 1500);
		outerPanel.setLayout(new GridLayout(width, height));
		makeOuterPanel(width, height);
		drawBoard();
	}
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(600, 600);
	}
	
	public void drawBoard() {
	}
	
	public void makeOuterPanel(int width, int height){
		grid = new JLabel[width][height];
	}
	@Override
	public void paint(Graphics g) {
		repaint();
	}
	
	/**
	 * @return the xClick
	 */
	public int getxClick() {
		return xClick;
	}


	/**
	 * @return the yClick
	 */
	public int getyClick() {
		return yClick;
	}
	

}
