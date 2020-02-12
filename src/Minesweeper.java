import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * This program is written by Wang Ziwen(Ziv)
 * Use the technology of SWING GUI and OO design
 * @author Ziv
 *
 */

public class Minesweeper extends JFrame implements ActionListener, MouseListener {
	
	/** The flag num. */
	int width = 500, height = 500, mineNum, flagNum;
	
	/** The game start. */
	boolean gameStart;
	
	/** The Minenumber. */
	ImageIcon[] Minenumber = { getImage("0.gif"), getImage("1.gif"), getImage("2.gif"), getImage("3.gif"),
			getImage("4.gif"), getImage("5.gif"), getImage("6.gif"), getImage("7.gif"), getImage("8.gif") };
	
	/** The flag. */
	ImageIcon[] flag = { getImage("0.gif"), getImage("flag.gif"), getImage("question.gif") };
	
	/** The block. */
	ImageIcon[] block = { getImage("blank.gif"), getImage("blank1.gif") };
	
	/** The bomb. */
	ImageIcon[] bomb = { getImage("0.gif"), getImage("mine.gif"), getImage("wrongmine.gif"), getImage("bomb.gif") };
	
	/** The face icon. */
	ImageIcon[] faceIcon = { getImage("smile.gif"), getImage("Ooo.gif") };
	
	/** The distribution. */
	MineDistribution distribution;
	
	/** The mine button. */
	MineButton[][] mineButton;
	
	/** The time counter thread. */
	TimeCounterThread timeCounterThread;
	
	/** The contro pane. */
	JPanel minePlay, mineStart, controPane;
	
	/** The time counter. */
	Counter mineCounter, timeCounter;
	
	/** The m bar. */
	JMenuBar mBar;
	
	/** The help. */
	JMenu game, help;
	
	/** The exit. */
	JMenuItem easy, middle, hard, about, exit;
	
	/** The Start btn. */
	JButton smile, StartBtn;
	
	/** The menu. */
	JLabel menu,rules,rules1,rules2,rules3;
	
	/** The start. */
	StartMenu start;
	
	/** The m about. */
	About mAbout;
	
	/** The gb. */
	GridBagConstraints gb = new GridBagConstraints();

	
	// Constructor of the game
	public Minesweeper() {
		super("Minesweeper");
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Insets space = new Insets(0, 0, 0, 0);
		
		// game value
		mineNum = 12;
		flagNum = 0;
		rules = new JLabel("New rules:     ");
		rules1 = new JLabel("Easy:    12 mines (10 * 10)");
		rules2 = new JLabel("Middle: 24 mines (10 * 10)");
		rules3 = new JLabel("Hard:    36 mines (10 * 10)");
		rules.setBounds(20, 20, 300, 30);
		rules1.setBounds(20, 60, 300, 30);
		rules2.setBounds(20, 100, 300, 30);
		rules3.setBounds(20, 140, 300, 30);
		gameStart = false;
		ImageIcon myIcon = getImage("0.gif");
		start = new StartMenu(width, height);
		GridBagLayout GridBag = new GridBagLayout();
		gb = new GridBagConstraints();
		mineStart = new JPanel();
		minePlay = new JPanel();

		// set layout for panel
		minePlay.setLayout(GridBag);
		gb.fill = GridBagConstraints.BOTH;
		gb.anchor = GridBagConstraints.CENTER;

		// Begin menu set
		StartBtn = new JButton("START");
		StartBtn.addActionListener(this);
		StartBtn.setBounds(200, 360, 100, 40);
		mBar = new JMenuBar();
		game = new JMenu("Game");
		easy = new JMenuItem("Easy");
		easy.addActionListener(this);
		middle = new JMenuItem("Medium");
		middle.addActionListener(this);
		hard = new JMenuItem("Hard");
		hard.addActionListener(this);
		exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		menu = new JLabel(start.getImg());
		menu.setSize(width, height);
		
		menu.add(rules);
		menu.add(rules1);
		menu.add(rules2);
		menu.add(rules3);
		menu.add(StartBtn);
		game.add(easy);
		game.add(middle);
		game.add(hard);
		game.add(exit);
		mBar.add(game);

		help = new JMenu("Help");
		about = new JMenuItem("About");

		about.addActionListener(this);
		help.add(about);
		mBar.add(help);
		mineStart.add(menu);
		this.setJMenuBar(mBar);

		// Control pane
		smile = new JButton(faceIcon[0]);
		smile.setSize(faceIcon[0].getIconWidth(), faceIcon[0].getIconHeight());
		smile.setMargin(space);

		smile.addMouseListener(this);
		smile.setPressedIcon(faceIcon[1]);
		controPane = new JPanel();

		mineCounter = new Counter(mineNum);
		timeCounter = new Counter();
		controPane.add(mineCounter);
		controPane.add(smile);
		controPane.add(timeCounter);
		getGridBag(gb, 0, 0, 10, 10, 100, 100);
		GridBag.setConstraints(controPane, gb);
		minePlay.add(controPane);

		// buttons
		mineButton = new MineButton[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				mineButton[i][j] = new MineButton(i, j, myIcon);
				mineButton[i][j].setSize(myIcon.getIconWidth(), myIcon.getIconHeight());
				mineButton[i][j].addMouseListener(this);
				mineButton[i][j].setMargin(space);
				getGridBag(gb, j, i + 10, 1, 1, 100, 100);
				GridBag.setConstraints(mineButton[i][j], gb);
				minePlay.add(mineButton[i][j]);
			}
		}
		mAbout = new About("About");

		// set windows
		
		setContentPane(mineStart);
		setResizable(false);
		setVisible(true);

	}

/**
 * Checks if is win.
 *
 * @return true, if is win
 */
	private boolean isWin() {
		int mineCounter = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (distribution.mine[i][j] == 9 && mineButton[i][j].getFlag() != 1) {
					return false;
				}
				if (distribution.mine[i][j] != 9 && mineButton[i][j].getFlag() == 1) {
					return false;
				}
				if (distribution.mine[i][j] != 9 && mineButton[i][j].getClickFlag() == false) {
					return false;
				}
			}

		}
		return true;
	}

	/**
	 * Bomb.
	 *
	 * @param row
	 *            the value of row number 
	 * @param col
	 *            the value of col number
	 */
	private void bomb(int row, int col) {
		System.out.println("bomb!");
		// stop thread
		timeCounterThread.stop();

		// use for loop to check weather flag work
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
//				mineButton[i][j].setIcon(bomb[0]);
				int show;
				show = distribution.mine[i][j] != 9 ? 0 : 1;
				mineButton[i][j].setClickFlag(true);
				// if the block is a mine and the current row and col 
				// number not equal the real number, show the bomb image 
				if (show == 1 && (i != row || j != col)) {
					mineButton[i][j].setIcon(bomb[show]);
					mineButton[i][j].setClickFlag(true);
				} else if (show == 1 && (i == row && j == col)) {// if the block is a mine and the current row and col 
					// number equal the real number, show the wrong bomb image 
					mineButton[i][j].setIcon(bomb[3]);
					mineButton[i][j].setClickFlag(true);
				} else if (show == 0 && mineButton[i][j].getFlag() != 1) {
					mineButton[i][j].setEnabled(false);
				} else if (show == 0 && mineButton[i][j].getFlag() == 1) {
					mineButton[i][j].setIcon(bomb[2]);
					mineButton[i][j].setClickFlag(true);
				}
			}
		}

	}

	/**
	 * This method determine whether the game win.
	 */
	private void gameWin() {
		// stop thread.
		timeCounterThread.stop();
		RestartRunner r = new RestartRunner();
		
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		new Minesweeper();
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == StartBtn) {
			width = 250;
			height = 330;
			this.setSize(width, height);
			setContentPane(minePlay);
			setResizable(false);
			setVisible(true);
		}
		if(e.getSource() == easy) {
			setNewGame(12);
			return;
		}
		if(e.getSource() == middle) {
			setNewGame(24);
			return;
		}
		if(e.getSource() == hard) {
			setNewGame(36 );
			return;
		}
		if(e.getSource() == exit) {
			System.exit(0);
		}
		if(e.getSource() == about) {
			mAbout.setVisible(true);
		}


	}

	/**
	 * Gets the grid bag.
	 *
	 * @param abc
	 *            the value of gridBagConstraint
	 * @param x
	 *            the value of grid x
	 * @param y
	 *            the value of grid y
	 * @param height
	 *            the value of height
	 * @param width
	 *            the value of width
	 * @param weightX
	 *            the value of weight X
	 * @param weightY
	 *            the value of weight Y
	 * @return the grid bag
	 */
	private void getGridBag(GridBagConstraints abc, int x, int y, int height, int width, int weightX, int weightY) {
		gb.gridx = x;
		gb.gridy = y;
		gb.gridheight = height;
		gb.gridwidth = width;
		gb.weightx = weightX;
		gb.weighty = weightY;
	}

	/**
	 * Gets the image.
	 *
	 * @param name
	 *            the value of image name 
	 * @return the image
	 */
	private ImageIcon getImage(String name) {
		return new ImageIcon("image//" + name);

	}

	/**
	 * Check mine.
	 *
	 * @param row
	 *            the value of row number 
	 * @param col
	 *            the value of col number 
	 */
	public void checkMine(int row, int col) {
		int i, j;
		i = row < 0 ? 0 : row;
		i = i > 9 ? 9 : i;
		j = col < 0 ? 0 : col;
		j = j > 9 ? 9 : j;
		// if the block is mine, bomb!
		if (distribution.mine[i][j] == 9) {
			bomb(i, j);
		} else if (distribution.mine[i][j] == 0 && mineButton[i][j].getClickFlag() == false) {
			// if the block is not a mine, and do not click flag,show the number of the block.
			mineButton[i][j].setClickFlag(true);
			showLabel(i, j);
			// use the recursion check every item in array
			for (int t = i - 1; t <= i + 1; t++) {
				for (int k = j - 1; k <= j + 1; k++) {
					checkMine(t, k);
				}
			}
		} else {
			showLabel(i, j);
			mineButton[i][j].setClickFlag(true);
		}
		if (isWin()) {
			gameWin();
		}
	}

	/**
	 * This method clear all label of block
	 * clear all status of block.
	 *
	 * @param row
	 *            the value of row number 
	 * @param col
	 *            the value of col number 
	 */
	public void clearAll(int row, int col) {
		int top, bottom, left, right, count = 0;
		top = row - 1 > 0 ? row - 1 : 0;
		bottom = row + 1 < 10 ? row + 1 : 9;
		left = col - 1 > 0 ? col - 1 : 0;
		right = col + 1 < 10 ? col + 1 : 9;
		for (int i = top; i <= bottom; i++) {
			for (int j = left; j <= right; j++) {
				if (mineButton[i][j].getFlag() != 1)
					checkMine(i, j);
			}
		}

	}

	/**
	 * This method reset everything include block status 
	 * and label.
	 */
	public void resetAll() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				mineButton[i][j].setFlag(0);
				mineButton[i][j].setClickFlag(false);
				mineButton[i][j].setIcon(flag[0]);
				mineButton[i][j].setEnabled(true);
				mineButton[i][j].setVisible(true);
			}
		}
	}
	
	/**
	 * This method get flag number 
	 *
	 * @param row
	 *            the value of row number 
	 * @param col
	 *            the value of col number 
	 */
	// to flag the mine you want to flag out
		public void flagMine(int row, int col) {
			
			int i,j;
			i=row<0?0:row;
			i=i>9?9:i;
			j=col<0?0:col;
			j=j>9?9:j;
			// when play click a flag, the number of flag ++.
			if(mineButton[i][j].getFlag() == 0) {
				flagNum++;
			} else if(mineButton[i][j].getFlag() == 1){
				// when player eliminate a flag, the number of flag --.
				flagNum--;
			}
			// the counter of mine need to reset. 
			mineCounter.resetCounter(mineNum - flagNum>=0?mineNum - flagNum:0);
			
			mineButton[i][j].setFlag((mineButton[i][j].getFlag() + 1) %3) ;
			showFlag(i,j);
			if (isWin()) {
				gameWin();
			}	
		}


		/**
		 * This method shows the number in the block 
		 * which means the number of the nearby mines.
		 *
		 * @param row
		 *            the value of row number 
		 * @param col
		 *            the value of col number 
		 */
		// show the numbers of the nearby mines
		public void showLabel(int row, int col) {
			//System.out.println("ShowLabel row:" + row + ",col:" + col);
	        	int toShow;
	        	toShow = distribution.mine[row][col];
			if (toShow != 0) {
				mineButton[row][col].setIcon(Minenumber[toShow]);
				mineButton[row][col].setClickFlag(true);
				//mineButton[row][col].setEnabled(false);
			} 
			else {
				//mineButton[row][col].setIcon(mineNumIcon[0]);
				//mineButton[row][col].setClickFlag(true);
				mineButton[row][col].setEnabled(false);
			}
		}
		
		/**
		 * This method shows the flag on the block
		 *
		 * @param row
		 *            the value of row number 
		 * @param col
		 *            the value of col numebr 
		 */
		public void showFlag(int row, int col) {			
			mineButton[row][col].setIcon(flag[mineButton[row][col].getFlag()]);
		}
		
		/**
		 * This method start a new game 
		 *
		 * @param num
		 *            the value of mine number  
		 * @param row
		 *            the value of row number 
		 * @param col
		 *            the value of col number 
		 */
		// method to start the new game
		private void startNewGame(int num, int row, int col) {
			distribution = new MineDistribution(num, row, col);
			//mine.printMine();
			gameStart = true;
			// restart a new thread.
			timeCounterThread = new TimeCounterThread(timeCounter);
			timeCounterThread.start(); 
		}
		
		/**
		 * This method sets a new game 
		 * sets all value again.
		 *
		 * @param num
		 *            the value of mine number 
		 */
		public void setNewGame(int num){
			resetAll();
			mineNum = num;
			flagNum = 0;
			gameStart = false;
			mineCounter.resetCounter(mineNum);
			timeCounter.resetCounter(0);
			timeCounterThread.stop();
		}

		



	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == smile) {
			setNewGame(mineNum);
			return;
		}
		int  row,col;
		row=((MineButton)e.getSource()).getRow();
		col=((MineButton)e.getSource()).getCol();
		if (!gameStart) {
			startNewGame(mineNum, row, col);
		}
		
		if (e.getModifiers() == (InputEvent.BUTTON1_MASK+InputEvent.BUTTON3_MASK)) {
			System.out.println("HA");
			clearAll(row, col);
		}
		if (!mineButton[row][col].getClickFlag()) {

			if (e.getModifiers() == InputEvent.BUTTON1_MASK) { 
				//System.out.println("LeftButton");
				if (mineButton[row][col].getFlag() == 1 ) { 
					return;
				}
				else {
					checkMine(row, col);
				}
			}
			else if (e.getModifiers() == InputEvent.BUTTON3_MASK){
				//System.out.println("RightButton");
				flagMine(row, col);
			} else {
				//System.out.println("MiddleButton");
			}
		}


	}

	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * The Class RestartRunner.
	 * @author Ziv 
	 */
	public class RestartRunner implements Runnable {
		
		/** The winnn. */
		private win winnn;
		
		/** The mine. */
		private Minesweeper mine;
		
		/** The is mine set. */
		private boolean isMineSet;
		
		/** The timer. */
		private TimeCounterThread timer;

		/**
		 * This method set the mine 
		 *
		 * @param mine
		 *            the value of mine
		 */
		public void setMine(Minesweeper mine) {
			this.mine = mine;

		}

		/**
		 * This method set the timer 
		 *
		 * @param timer
		 *            the value of timer
		 */
		public void setTimer(TimeCounterThread timer) {
			this.timer = timer;
		}

		/**
		 * This method run the game when player win the game 
		 */
		public void run() {
			isMineSet = false;
			winnn = new win("You win!");
			while (!this.winnn.getWinOk() || isMineSet) {

			}
			mine.mineNum = winnn.getMineNum();
			winnn.setVisible(true);
		}
	}

	/**
	 * The Class is TimeCounterThread.
	 */
	class TimeCounterThread extends Thread {
		
		/** The time counter. */
		private Counter timeCounter;

		/**
		 * Instantiates a new time counter thread.
		 *
		 * @param time
		 *            the time
		 */
		public TimeCounterThread(Counter time) {
			// TODO Auto-generated constructor stub
			timeCounter = time;
		}

		public void run() {
			while (true) {
				try {
					sleep(1000);
					timeCounter.resetCounter(timeCounter.getCounterNum() + 1);
				} catch (InterruptedException e) {
					// TODO: handle exception
				}

			}
		}
	}
}
