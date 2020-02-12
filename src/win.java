
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * This program is written by Wang Ziwen(Ziv)
 * Use the technology of SWING GUI and OO design
 * @author Ziv
 *
 */

public class win extends JFrame implements MouseListener {
	
	/** The win pane. */
	private JPanel winPane;
	
	/** The msg 3. */
	private JLabel msg, msg2, msg3;
	
	/** The hard. */
	private JButton easy, middle, hard;
	
	/** The level. */
	private int level;
	
	/** The is ok. */
	private boolean isOk;

	/**
	 * Instantiates a new win frame.
	 *
	 * @param name
	 *            the value of title name
	 */
	public win(String name) {
		super(name);
		setSize(150, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		level = 1;
		isOk = false;

		winPane = new JPanel();
		msg = new JLabel("      Congratulation!      ");
		msg2 = new JLabel("       You win.           ");
		msg3 = new JLabel("     Click to restart!    ");
		easy = new JButton("Easy");
		easy.addMouseListener(this);
		middle = new JButton("Middle");
		middle.addMouseListener(this);
		hard = new JButton("hard");
		hard.addMouseListener(this);
		winPane.add(msg);
		winPane.add(msg2);
		winPane.add(msg3);
		winPane.add(easy);
		winPane.add(middle);
		winPane.add(hard);

		setContentPane(winPane);
		setLocation(250, 200);
		this.setVisible(true);
	}

	/**
	 * Gets the level of the game .
	 *
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Gets the mine number.
	 *
	 * @return the mine num
	 */
	public int getMineNum() {
		return (level * 12);
	}

	/**
	 * Gets weather win .
	 *
	 * @return the win ok
	 */
	public boolean getWinOk() {
		return isOk;
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == easy) {
			level = 1;
		}
		if (e.getSource() == middle) {
			level = 2;
		}
		if (e.getSource() == hard) {
			level = 3;
		}
		isOk = true;
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
	 * The main method.
	 *
	 * @param arg
	 *            the arguments
	 */
	public static void main(String[] arg) {
		win winframe = new win("win");

	}

}
