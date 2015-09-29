package UI;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class GameFrame extends JFrame implements WindowListener{

	private GameCanvas c;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenu helpMenu;
	private JMenuItem startNew;
	private JMenuItem exit;
	private JMenuItem instructions;
	
	
	public GameFrame() {
		super("Welcome to your Worst Nightmare!");
		setLayout(new BorderLayout());
		GameCanvas canvas = new GameCanvas();
		c = canvas;
		getContentPane().add(c, BorderLayout.CENTER);
		setUpMenu();
		setSize(1500, 1500);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		pack();
		setResizable(true);
		setVisible(true);

		
	}
	private void setUpMenu() {
		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		helpMenu = new JMenu ("Help");
		menuBar.add(menu);
		menuBar.add(helpMenu);
		startNew = new JMenuItem("Start new game");
		startNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//GUI.main(null);
			}			
		});
		menu.add(startNew);
		exit = new JMenuItem("Exit game");
		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int r = JOptionPane.showConfirmDialog(c, new JLabel(
						"Exit Cluedo?"), "Confirm Exit",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (r == JOptionPane.YES_OPTION) {
							System.exit(0);
						}
			}			
		});
		menu.add(exit);
		instructions = new JMenuItem("Instructions");
		instructions.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			showInstructions();
				
			}			
		});
		helpMenu.add(instructions);
		this.setJMenuBar(menuBar);
		
	}
	/**
	 * @return the game
	 */
//	public Game getGame() {
//		return game;
//	}
	public GameCanvas getC() {
		return c;
	}
	public void setC(GameCanvas c) {
		this.c = c;
	}
	
	
	public void windowClosing(WindowEvent e) {
		int r = JOptionPane.showConfirmDialog(this, new JLabel(
				"Exit?"), "Confirm Exit",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (r == JOptionPane.YES_OPTION) {
					System.exit(0);
				}		
	}
	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
	
	public static void main(String[] args) {
		new GameFrame();
	}
	public void showInstructions() {
		JOptionPane.showMessageDialog(this, "this is a game \nit is not yet clear what to do.", "Instructions", JOptionPane.PLAIN_MESSAGE);
		JOptionPane.showMessageDialog(this, "more unknown instructions", "Instructions", JOptionPane.PLAIN_MESSAGE);
	}
}
