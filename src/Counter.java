

import java.awt.*;
import javax.swing.*;


/**
 * The Class Counter.
 */
class Counter extends JPanel {
	
	
	private ImageIcon[] numSet = { getImage("c0.gif"), getImage("c1.gif"), getImage("c2.gif"), getImage("c3.gif"),
			getImage("c4.gif"), getImage("c5.gif"), getImage("c6.gif"), getImage("c7.gif"), getImage("c8.gif"),
			getImage("c9.gif") };
	

	private JButton[] counter = { new JButton(numSet[0]), new JButton(numSet[0]), new JButton(numSet[0]) };
	
	
	private int counterNum;
	

	private Insets space;

	/**
	 * Instantiates a new counter.
	 */
	public Counter() {
		this(0);
	}

	/**
	 * Instantiates a new counter.
	 *
	 * @param num
	 *            the number of counter
	 */
	public Counter(int num) {
		super();
		setSize(13, 23);

		space = new Insets(0, 0, 0, 0);
		this.counterNum = num;
		for (int i = 0; i < 3; i++) {
			counter[i].setSize(13, 23);
			counter[i].setMargin(space);
			add(counter[i]);
		}
		this.setVisible(true);
		resetImage();
	}

	/**
	 * Gets the counter num.
	 *
	 * @return the counter number
	 */
	public int getCounterNum() {
		return counterNum;
	}

	/**
	 * Sets the counter num.
	 *
	 * @param num
	 *            the new counter number
	 */
	private void setCounterNum(int num) {
		this.counterNum = num;
	}

	/**
	 * Reset image of counter.
	 */
	private void resetImage() {
		int ones, tens, hundreds;
		ones = counterNum % 10;
		tens = counterNum % 100 / 10;
		hundreds = (counterNum) % 1000 / 100;
		this.counter[0].setIcon(numSet[hundreds]);
		this.counter[1].setIcon(numSet[tens]);
		this.counter[2].setIcon(numSet[ones]);
	}

	/**
	 * Reset counter.
	 *
	 * @param num
	 *            the number of counter
	 */
	public void resetCounter(int num) {
		setCounterNum(num);
		resetImage();
		this.repaint();
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		JFrame jf = new JFrame("Test");
		jf.setSize(100, 100);
		Counter jc = new Counter();
		jf.setContentPane(jc);

		jf.show();
		jc.resetCounter(12);
	}

	/**
	 * Gets the image.
	 *
	 * @param name
	 *            the name of image 
	 * @return the image of current image 
	 */
	private ImageIcon getImage(String name) {
		return new ImageIcon("image//" + name);

	}

}
