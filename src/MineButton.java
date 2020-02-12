
import javax.swing.*;
/**
 * This program is written by Wang Ziwen(Ziv)
 * Use the technology of SWING GUI and OO design
 * @author Ziv
 *
 */


public class MineButton extends JButton {
	
	/** The col. */
	private int col;
	
	/** The row. */
	private int row;
	
	/** The flag. */
	private int flag = 0;
	
	/** The click flag. */
	private boolean clickFlag = false;

	/**
	 * Instantiates a new mine button.
	 *
	 * @param row
	 *            the value of row number 
	 * @param col
	 *            the value of col number 
	 * @param icon
	 *            the value of image  
	 */
	MineButton(int row, int col, ImageIcon icon) {
		super(icon);
		this.row = row;
		this.col = col;
	}

	/**
	 * Gets the click flag.
	 *
	 * @return the click flag
	 */
	public boolean getClickFlag() {
		return (clickFlag);
	}

	/**
	 * Sets the click flag.
	 *
	 * @param toSet
	 *            the new click flag
	 */
	public void setClickFlag(boolean toSet) {
		clickFlag = toSet;
	}

	/**
	 * Gets the col.
	 *
	 * @return the col
	 */
	public int getCol() {
		return (col);
	}

	/**
	 * Gets the row.
	 *
	 * @return the row
	 */
	public int getRow() {
		return (row);
	}

	/**
	 * Sets the flag.
	 *
	 * @param flag
	 *            the new flag
	 */
	public void setFlag(int flag) {
		this.flag = flag;
	}

	/**
	 * Gets the flag.
	 *
	 * @return the flag
	 */
	public int getFlag() {
		return (flag);
	}
}