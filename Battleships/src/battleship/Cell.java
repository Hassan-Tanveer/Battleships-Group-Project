package battleship;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This is the individual squares that make up the grid
 * Able to tell if the cell has been shot at and if so it will change to the
 * appropriate colour
 * 
 * @author Rajan Gopalji
 *
 *
 */
public class Cell extends Rectangle {
	public final int column, row;
	public Ship ship = null;
	public boolean wasShot = false;
	// private int health = 17;
	private Grid playerGrid;

	/**
	 * Constructs the cells size and colours
	 * 
	 * @param col
	 * @param row
	 * @param playerGrid the grid that would eventually be formed using rows and columns of cells
	 */
	public Cell(int col, int row, Grid playerGrid) {
		super(30, 30); // size of each square
		this.column = col;
		this.row = row;
		this.playerGrid = playerGrid;
		setFill(Color.AQUAMARINE); // colour of the squares
		setStroke(Color.CORNFLOWERBLUE); // colour of the lines that make up the
											// square
	}

	/**
	 * if the cell was shot at the colour should change to either of the two colours red or dark
	 * blue
	 * Distinguishable by checking if a ship object exists within the cell
	 * 
	 * @return boolean if the cell has been shot at
	 */
	public boolean shoot() {
		wasShot = true;
		setFill(Color.CADETBLUE);

		if (ship != null) {
			ship.hit();
			setFill(Color.RED);

			if (!ship.isAlive()) {
				playerGrid.ships--;
			}
			 return true;
		}

		return false;
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	
}