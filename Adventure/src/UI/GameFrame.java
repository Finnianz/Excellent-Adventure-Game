package UI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Game.Gameplay;
import Main.Main;
import render.RenderCanvas;

public class GameFrame extends JFrame implements WindowListener {

	private GameCanvas c;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenu helpMenu;
	private JMenuItem startNewSingle;
	private JMenuItem startNewMulti;
	private JMenuItem exit;
	private JMenuItem instructions;
	boolean newGame = true;
	int countOfPlayers =0;
	private JRadioButton[] array;
	private String playerColour= "Yellow";
	private String playerHat= "cowboy";
	public GameFrame() {
		super("Welcome to your Worst Nightmare!");
		setLayout(new BorderLayout());
		GameCanvas canvas = new GameCanvas();
		c = canvas;
		c.setMinimumSize(new Dimension(600, 600));
		getContentPane().add(c, BorderLayout.CENTER);
		setUpMenu();
		setSize(1500, 1500);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		pack();
		setResizable(true);
		setVisible(true);
			showInstructions();
			c.addKeyListener(new KeyListener(){

				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
	}

	private void inputPlayers() {

		JRadioButton Yellow = new JRadioButton("Yellow");
		Yellow.setActionCommand("Yellow"); 
		Yellow.setSelected(true);
		Yellow.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				playerColour = e.getActionCommand().toString();
			}			 
		});
		JRadioButton Blue = new JRadioButton("Blue");
		Blue.setActionCommand("Blue");
		Blue.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				playerColour = e.getActionCommand().toString();
			}			 
		});
		JRadioButton Green = new JRadioButton("Green");
		Green.setActionCommand("Green");
		Green.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				playerColour = e.getActionCommand().toString();
			}			 
		});
		JRadioButton Red = new JRadioButton("Red");
		Red.setActionCommand("Red");
		Red.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {	
				playerColour = e.getActionCommand().toString();
			}			 
		});

		array = new JRadioButton[6];
		array[0] = Yellow;
		array[1] = Blue;
		array[2] = Green;
		array[3] = Red;

		ButtonGroup buttons = new ButtonGroup();
		buttons.add(Yellow);
		buttons.add(Blue);
		buttons.add(Green);
		buttons.add(Red);

		JPanel panel = new JPanel();
		panel.add(Yellow);
		panel.add(Blue);
		panel.add(Green);
		panel.add(Red);
		
		//asks for colour and saves as field
		int token = JOptionPane.showOptionDialog(this, panel, "Choose your Player Colour:", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);
		System.out.print(token);
		if(token==0){
			System.out.print(playerColour);
		}
		//set up radio buttons for selecting hat
		JRadioButton beanie = new JRadioButton("Beanie");
		Yellow.setActionCommand("Beanie"); 
		Yellow.setSelected(true);
		Yellow.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				playerHat = e.getActionCommand().toString();
			}			 
		});
		JRadioButton cowboy = new JRadioButton("Cowboy");
		Blue.setActionCommand("Cowboy");
		Blue.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				playerHat = e.getActionCommand().toString();
			}			 
		});
		JRadioButton sunhat = new JRadioButton("Sunhat");
		Green.setActionCommand("Sunhat");
		Green.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				playerHat = e.getActionCommand().toString();
			}			 
		});
		JRadioButton tophat = new JRadioButton("Tophat");
		Red.setActionCommand("Tophat");
		Red.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {	
				playerHat = e.getActionCommand().toString();
			}			 
		});

		array = new JRadioButton[4];
		array[0] = beanie;
		array[1] = cowboy;
		array[2] = sunhat;
		array[3] = tophat;

		ButtonGroup buttons2 = new ButtonGroup();
		buttons.add(beanie);
		buttons.add(cowboy);
		buttons.add(sunhat);
		buttons.add(tophat);

		JPanel panel2 = new JPanel();
		panel2.add(beanie);
		panel2.add(cowboy);
		panel2.add(sunhat);
		panel2.add(tophat);
		
		//asks for hat and saves as field playerHat
		int token2 = JOptionPane.showOptionDialog(this, panel2, "Choose your Hat:", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);
		System.out.print(token);
		if(token2==0){
			System.out.print(playerHat);
		}
	}

	private void setUpMenu() {
		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		helpMenu = new JMenu("Help");
		menuBar.add(menu);
		menuBar.add(helpMenu);
		startNewSingle = new JMenuItem("Start Single Player Game");
		startNewSingle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Gameplay(new ArrayList<Game.Character>());
				inputPlayers();
			}
		});
		menu.add(startNewSingle);

		// adds multiplayer item to menu, and offers option pane to choose host
		// or client
		startNewMulti = new JMenuItem("Multi Player");
		startNewMulti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] choices = new String[] { "Host Game", "Join Game" };
				String choice = (String) JOptionPane.showInputDialog(null, "Please Choose whether Host or Client", "??",
						JOptionPane.PLAIN_MESSAGE, null, choices, choices[0]);
				if (choice.equals("Host Game")) {
					Main.hostGame(Integer.parseInt(
							JOptionPane.showInputDialog(null, "Enter Port", null, JOptionPane.PLAIN_MESSAGE)));

				} else if (choice.equals("Join Game")) {

					Main.joinGame(Integer.parseInt(
							JOptionPane.showInputDialog(null, "Enter Port", null, JOptionPane.PLAIN_MESSAGE)));
				}
				inputPlayers();
			}
		});
		menu.add(startNewMulti);
		exit = new JMenuItem("Exit game");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int r = JOptionPane.showConfirmDialog(c, new JLabel("Exit?"), "Confirm Exit",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (r == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		menu.add(exit);
		instructions = new JMenuItem("Instructions");
		instructions.addActionListener(new ActionListener() {
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
	// public Game getGame() {
	// return game;
	// }
	public GameCanvas getC() {
		return c;
	}

	public void setC(GameCanvas c) {
		this.c = c;
	}

	public void windowClosing(WindowEvent e) {
		int r = JOptionPane.showConfirmDialog(this, new JLabel("Exit?"), "Confirm Exit", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (r == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}

	public void windowOpened(WindowEvent e) {
	}

	public static void main(String[] args) {
		new GameFrame();
	}

	public void showInstructions() {
		JOptionPane.showMessageDialog(this, "this is a game \nit is not yet clear what to do.", "Instructions",
				JOptionPane.PLAIN_MESSAGE);
		JOptionPane.showMessageDialog(this, "more unknown instructions", "Instructions", JOptionPane.PLAIN_MESSAGE);
	}
}
