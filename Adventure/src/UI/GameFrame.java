package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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

/**
 * 
 * @author Megan Davidson ID:300313759
 *
 */
public class GameFrame extends JFrame implements WindowListener, Serializable {

	private GameCanvas canvasOfGame;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenu helpMenu;
	private JMenuItem startNewSingle;
	private JMenuItem startNewMulti;
	private JMenuItem exit;
	private JMenuItem instructions;
	boolean newGame = true;
	int countOfPlayers = 0;
	private JRadioButton[] array;
	private String playerColour = "Yellow";
	private String playerHat = "Beanie";
	private Gameplay game;

	/**
	 * Sets up the frame for the game and the keyListener for movement
	 */
	public GameFrame() {
		super("Welcome to your Worst Nightmare!");
		setLayout(new BorderLayout());
		GameCanvas canvas = new GameCanvas();
		canvasOfGame = canvas;
		canvasOfGame.setMinimumSize(new Dimension(600, 600));
		getContentPane().add(canvasOfGame, BorderLayout.CENTER);
		setUpMenu();
		setSize(1500, 1500);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		pack();
		setResizable(true);
		setVisible(true);
		showInstructions();

		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(new MyDispatcher());
	}

	/**
	 * helper class to register the keystrokes
	 *
	 */
	private class MyDispatcher implements KeyEventDispatcher {
		@Override
		public boolean dispatchKeyEvent(KeyEvent e) {
			if (e.getID() == KeyEvent.KEY_PRESSED) {
				if (e.getKeyCode() == (40)) {
					Main.moveDown(0);
					canvasOfGame.drawBag();

				}
				if (e.getKeyCode() == (37)) {
					Main.moveLeft(0);
					canvasOfGame.drawBag();
				}
				if (e.getKeyCode() == (38)) {
					Main.moveUp(0);
					canvasOfGame.drawBag();
				}
				if (e.getKeyCode() == (39)) {
					Main.moveRight(0);
					canvasOfGame.drawBag();
				}

			} else if (e.getID() == KeyEvent.KEY_RELEASED) {

			} else if (e.getID() == KeyEvent.KEY_TYPED) {

			}
			return false;
		}
	}

	/**
	 * sets up dialogue boxes to get characters to select colour and hat types.
	 * (are saved as private fields
	 */
	public void inputPlayers() {

		// sets up button for colour
		JRadioButton Yellow = new JRadioButton("Yellow");
		Yellow.setActionCommand("Yellow");
		Yellow.setSelected(true);
		Yellow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playerColour = e.getActionCommand().toString();
			}
		});
		JRadioButton Blue = new JRadioButton("Blue");
		Blue.setActionCommand("Blue");
		Blue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playerColour = e.getActionCommand().toString();
			}
		});
		JRadioButton Green = new JRadioButton("Green");
		Green.setActionCommand("Green");
		Green.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playerColour = e.getActionCommand().toString();
			}
		});
		JRadioButton Red = new JRadioButton("Red");
		Red.setActionCommand("Red");
		Red.addActionListener(new ActionListener() {
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

		// asks for colour and saves as field
		int token = JOptionPane.showOptionDialog(this, panel, "Choose your Player Colour:",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (token == 0) {
		}
		// set up radio buttons for selecting hat
		JRadioButton beanie = new JRadioButton("Beanie");
		beanie.setActionCommand("Beanie");
		beanie.setSelected(true);
		beanie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playerHat = e.getActionCommand().toString();
			}
		});
		JRadioButton cowboy = new JRadioButton("Cowboy");
		cowboy.setActionCommand("Cowboy");
		cowboy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playerHat = e.getActionCommand().toString();
			}
		});
		JRadioButton sunhat = new JRadioButton("Sunhat");
		sunhat.setActionCommand("Sunhat");
		sunhat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playerHat = e.getActionCommand().toString();
			}
		});
		JRadioButton tophat = new JRadioButton("Tophat");
		tophat.setActionCommand("Tophat");
		tophat.addActionListener(new ActionListener() {
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
		buttons2.add(beanie);
		buttons2.add(cowboy);
		buttons2.add(sunhat);
		buttons2.add(tophat);

		JPanel panel2 = new JPanel();
		panel2.add(beanie);
		panel2.add(cowboy);
		panel2.add(sunhat);
		panel2.add(tophat);

		// asks for hat and saves as field playerHat
		int token2 = JOptionPane.showOptionDialog(this, panel2, "Choose your Hat:", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (token2 == 0) {
		}
	}

	// sets up the menu bar giving players options to exit/start new game/get
	// help
	private void setUpMenu() {
		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		helpMenu = new JMenu("Help");
		menuBar.add(menu);
		menuBar.add(helpMenu);
		startNewSingle = new JMenuItem("Start Single Player Game");
		startNewSingle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputPlayers();
				Main.startGame();
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
					JOptionPane.showMessageDialog(null, "Server started on " + Main.serverIp, null,
							JOptionPane.PLAIN_MESSAGE);
					Main.hostGame(Integer.parseInt(
							JOptionPane.showInputDialog(null, "Enter Port", null, JOptionPane.PLAIN_MESSAGE)));

				} else if (choice.equals("Join Game")) {

					Main.joinGame(JOptionPane.showInputDialog(null, "Enter Server", null, JOptionPane.PLAIN_MESSAGE),
							Integer.parseInt(
									JOptionPane.showInputDialog(null, "Enter Port", null, JOptionPane.PLAIN_MESSAGE)));
					JOptionPane.showMessageDialog(null, "Server connected on " + Main.serverIp, "Server deets: ",
							JOptionPane.INFORMATION_MESSAGE);
				}
				inputPlayers();
			}
		});
		menu.add(startNewMulti);
		exit = new JMenuItem("Exit game");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int r = JOptionPane.showConfirmDialog(canvasOfGame, new JLabel("Exit?"), "Confirm Exit",
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
	 * gets the game canvas
	 * 
	 * @return GameCanvas
	 */
	public GameCanvas getC() {
		return canvasOfGame;
	}

	/**
	 * sets the canvas of the frame
	 * 
	 * @param c
	 */
	public void setC(GameCanvas c) {
		this.canvasOfGame = c;
	}

	/**
	 * the dialogue window that open on exiting the game
	 */
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

	/**
	 * Dialogue box that opens when player requests instructions and at the
	 * beginning of the game
	 */
	public void showInstructions() {
		JOptionPane.showMessageDialog(this, ("\n\n"
				+ "It was halloween and foolishy you and\nyour friends go to a haunted house. While inside \n"
				+ "your friends thought it would be fun to ditch you\n unfortunately you then got scared to death \n by the spirits. Now you must escape the tower\n "
				+ "so you can get revenge!\n"
				+ "          GOOD LUCK GETTING OUT!!!!!!\n\n"), "Instructions", JOptionPane.PLAIN_MESSAGE, new ImageIcon(getClass().getResource("Tower2.jpg")));
		JOptionPane.showMessageDialog(this, "You must make your way down the tower. \n"
				+ "Hunt around the rooms find a key and make \n"
				+ "your way through the trap door and if you forgot\n"
				+ " someting you can make your way back up the ladder\n"
				+ " if you dare!", "Instructions", JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * sets the GamePlay of the frame
	 * 
	 * @param g
	 */
	public void setGame(Gameplay g) {
		game = g;
	}

	/**
	 * @return playerColour
	 */
	public String getPlayerColour() {
		return playerColour;
	}

	/**
	 * @return playerHat
	 */
	public String getPlayerHat() {
		return playerHat;
	}

	public static void main(String[] args) {
		new GameFrame();
	}

}
