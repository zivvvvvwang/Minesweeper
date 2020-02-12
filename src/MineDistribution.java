/**
 * This program is written by Wang Ziwen(Ziv) Use the technology of SWING GUI
 * and OO design
 * 
 * @author Ziv
 *
 */

public class MineDistribution {

	/** The mine. */
	public int[][] mine;

	/** The mine set. */
	private boolean mineSet;

	/**
	 * Instantiates a new mine distribution.
	 *
	 * @param mineNum
	 *            the value of mine number
	 * @param row
	 *            the value of row number
	 * @param col
	 *            the value of col number
	 */
	MineDistribution(int mineNum, int row, int col) {
		mine = new int[10][10];
		setMine(mineNum, row, col);
		setMineNum();
	}

	/**
	 * Sets the mine.
	 *
	 * @param mineNum
	 *            The value of mine number
	 * @param Outrow
	 *            the value of row number
	 * @param Outcol
	 *            the value of col number
	 */
	private void setMine(int mineNum, int Outrow, int Outcol) {
		int col = 0, row = 0, i = 0;
		// Math.srand(now);

		// use loop to make mine
		// (9 is represent mine!)
		while (i < mineNum) {
			col = (int) (Math.random() * 100) % 10;
			row = (int) (Math.random() * 100) % 10;
			if (mine[row][col] == 0 && (row != Outrow || col != Outcol || Outrow == 10)) {
				mine[row][col] = 9;
				i++;
			}
		}
	}

	/**
	 * This method use for look to set mine number
	 */
	private void setMineNum() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				mine[i][j] = mine[i][j] == 9 ? 9 : checkMineNum(i, j);
			}
		}
		mineSet = true;
	}

	/**
	 * This method check mine number.
	 *
	 * @param ii
	 *            the value of row number
	 * @param jj
	 *            the value of col number
	 * @return the count
	 */
	private int checkMineNum(int ii, int jj) {
		int top, bottom, left, right, count = 0;

		top = ii - 1 > 0 ? ii - 1 : 0;
		bottom = ii + 1 < 10 ? ii + 1 : 9;
		left = jj - 1 > 0 ? jj - 1 : 0;
		right = jj + 1 < 10 ? jj + 1 : 9;

		for (int i = top; i <= bottom; i++) {
			for (int j = left; j <= right; j++) {
				if (mine[i][j] == 9)
					count++;
			}
		}
		return count;
	}

	/**
	 * This method print the mine
	 */
	public void printMine() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(this.mine[i][j] + " ");
			}
			System.out.println();
		}
	}

	// public static void main(String[] args) {
	// MineDistribution mine = new MineDistribution(36, 10, 10);
	// mine.printMine();
	// }
}
