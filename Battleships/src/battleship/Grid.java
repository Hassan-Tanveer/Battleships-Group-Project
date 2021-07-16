package battleship;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Takes the cells and creates a grid of 10x10 Can construct the player and
 * enemy grid
 * 
 * @author Rajan Gopalji
 * @author Damon Tran
 */
public class Grid extends Parent {
	private VBox rows = new VBox(); // create vertical boxes and name the
									// container rows
	private boolean enemy = false;
	public int ships = 5;
	private int health = 17;
	private Grid grid;

	public boolean getEnemy() {
		return enemy;
	}

	public void setEnemy(boolean enemy) {// , EventHandler<? super MouseEvent>
											// handler) {
		this.enemy = enemy;
		for (int y = 0; y < 10; y++) {
			HBox row = new HBox(); // same as VBox but the squares go across
			for (int x = 0; x < 10; x++) {
				Cell c = new Cell(x, y, this);
				row.getChildren().add(c);
			}

			rows.getChildren().add(row);

		}
		getChildren().add(rows);

	}

	public boolean placeShip(Ship ship, int x, int y) {
		if (canPlaceShip(ship, x, y)) {
			int length = ship.length;

			if (ship.vertical) {
				for (int i = y; i < y + length; i++) {
					Cell cell = getCell(x, i);
					cell.ship = ship;

					if (!enemy) {
						cell.setFill(Color.DARKGRAY);
						cell.setStroke(Color.GRAY);

					}
					// Shows AI Ships for testing purposes
					/*
					 * cell.setFill(Color.DARKGRAY); cell.setStroke(Color.GRAY);
					 */
				}

			} else if (ship.vertical == false) {
				for (int i = x; i < x + length; i++) {
					Cell cell = getCell(i, y);
					cell.ship = ship;
					if (!enemy) {
						cell.setFill(Color.DARKGRAY);
						cell.setStroke(Color.GRAY);
					}
					// Shows AI Ships for testing purposes
					/*
					 * cell.setFill(Color.DARKGRAY); cell.setStroke(Color.GRAY);
					 */
				}

			}
			// ships--;
		}
		return false;
	}

	public boolean setOnCellClicked(EventHandler<? super MouseEvent> handler) {
		for (Node row : rows.getChildren()) {
			for (Node nCell : ((Parent) row).getChildrenUnmodifiable()) {
				nCell.setOnMouseClicked(handler);
			}
		}
		return true;
	}

	/**
	 * public Cell getCell(int x, int y) get the components of the row
	 * container, cast it to HBox to get the component and then get X to get the
	 * cell and cast it to Cell
	 * 
	 * return (Cell)((HBox)rows.getChildren().get(y)).getChildren().get(x);
	 */
	public Cell getCell(int x, int y) {
		return (Cell) ((HBox) rows.getChildren().get(y)).getChildren().get(x);
		// get the components of the row container
	}

	/**
	 * Checks if the cell selected can hold the ship as well as the cells that
	 * surround it If ship already exists in Cell(x,y) then can't place ship
	 * 
	 * @param ship
	 *            represents any type of ship
	 * @param x
	 *            cells x coordinate
	 * @param y
	 *            cells y coordinate
	 * @return boolean to determine if the ship can be placed in the location
	 */
	public boolean canPlaceShip(Ship ship, int x, int y) {
		int length = ship.length;

		if (ship.vertical == true) {
			for (int i = y; i < y + length; i++) {
				if (!validPosition(x, i)) // if the full ship does not fit on
											// the grid do not place
					return false;

				Cell cell = getCell(x, i);
				if (cell.ship != null) // checks if a part of a ship is already
										// present in the cell
					return false; // cannot place ship
			}
		} else if (ship.vertical == false) {
			for (int i = x; i < x + length; i++) {
				if (!validPosition(i, y))
					return false;

				Cell cell = getCell(i, y);
				if (cell.ship != null)
					return false;
			}
		}

		if (ship.vertical) {
			for (int i = y; i < y + length; i++) {
				if (!validPosition(x, i))
					return false;

				Cell cell = getCell(x, i);
				if (cell.ship != null)
					return false;
			}
		}

		return true;

	}

	/**
	 * Does the ship fit within the grid
	 * 
	 * @param x
	 * @param y
	 * @return boolean false if the ship runs off the grid
	 */
	private boolean validPosition(double x, double y) {
		return x >= 0 && x < 10 && y >= 0 && y < 10;
	}

	/*
	 * public void setAlignment(Pos centerLeft) { // TODO Auto-generated method
	 * stub
	 * 
	 * }
	 */

	public int getHealth() {
		return health;
	}

	public Grid getGrid() {
		return grid;
	}

}