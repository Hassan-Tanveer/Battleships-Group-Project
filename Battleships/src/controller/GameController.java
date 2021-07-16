package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import battleship.AircraftCarrier;
import battleship.Battleship;
import battleship.Cell;
import battleship.Cruiser;
import battleship.Destroyer;
import battleship.Grid;
import battleship.Submarine;
import model.PopUpLose;
import model.PopUpWin;

public class GameController {
	private String difficulty;
	private boolean HardDiff = false;
	private Grid grid, grid2;
	private boolean running;
	private boolean enemyTurn;
	private int count;
	private Random random;
	private GameController gameController;
	private int health;
	private boolean playerTurn;
	private int pHealth, cHealth;
	private PopUpWin pop;
	private PopUpLose pop2;
	private int c1;
	private ShipsController shipController;
	private int index;
	private int[][][] positions;
	private int[][][] userHits;
	private int x, y;
	private int previousX, previousY;
	private boolean previousHit;

	private boolean AiusedPerk;
	private boolean usedPerk;
	private static final String FILENAME = ".\\test.txt";
	private SoundController soundController;

	public GameController(Grid grid, Grid grid2) throws IOException {
		SplashScreenController splashScreenController = new SplashScreenController();
		previousHit = false;
		random = new Random();
		PopUpWin pop = new PopUpWin();
		positions = new int[100][2][100];
		userHits = new int[100][2][100];
		c1 = 0;

		boolean AiusedPerk = false;
		positions = new int[100][2][100];
		userHits = new int[100][2][100];
		index = 0;
		boolean usedPerk = false;
		shipController = new ShipsController();
		health = 17;
		running = false;
		cHealth = 17;
		pHealth = 17;
		int count = 0;
		playerTurn = true;

		this.grid = grid.getGrid();
		grid = this.grid;

		this.grid2 = grid2.getGrid();
		grid2 = this.grid2;

		soundController = new SoundController();

		readFile();
		difficulty = readFile();

	}

	/**
	 * Author Damon Tran
	 * 
	 * @return String reads the text file and returns the first line and sets
	 *         the clas variable difficulty to what is read
	 */
	public String readFile() {
		BufferedReader br = null;
		FileReader fr = null;
		String sCurrentLine = null;
		try {

			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			sCurrentLine = br.readLine();
			difficulty = sCurrentLine;
			System.out.println(sCurrentLine);

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		return sCurrentLine;
	}

	/**
	 * @author Damon Tran Rolls random values for it to shoot and creates a cell
	 *         object tpo shoot at the given co-ordinates. Inside the method has
	 *         several if statements to see if the AI has been set to hard mode,
	 *         if it has it calls two methods called shootAdjacent and AiNuke.
	 *         Based on the conditions AiNuke should only be called once the
	 *         boolean AiusedPerk is false and the dice roll is 1.
	 * 
	 *         Otherwise it shoots cells as normal. Easy mode does this normally
	 *         as it doesn't satisfy the above conditionals stated above.
	 * @param grid
	 *            first grid A.K.A player grid
	 * @param grid2
	 *            second grid A.K.A Computer grid
	 */
	public void enemyMove(Grid grid, Grid grid2) {
		// random roll values
		x = random.nextInt(10);
		y = random.nextInt(10);
		int chanceOfPerk = random.nextInt(9);
		// chance of perk being rolled
		System.out.println("Index values b4 Increment: " + index);

		// EnemyTurn set
		enemyTurn = true;

		if (enemyTurn) {
			// If AI is set to hard and the dice roll is 1 Fire the Nuke.
			if (difficulty.equals("hard") && chanceOfPerk == 1 && AiusedPerk == false) {
				aiNuke(grid);
			}
			// If Ai is hard and perk buton alraedy used, calls ShootAdjacent
			// Method
			else if (difficulty.equals("hard") && AiusedPerk == true) {
				shootAdjacent(grid);
			} else
				System.out.println("Position index, C2 : with value of x + y: " + positions[index][0][0] + "  "
						+ positions[index][0][1]);

			Cell cell = grid.getCell(x, y);
			// Read hardmode then activate hardmode
			shootAdjacent(grid);

			// Stores the AI shot values into a 3d Array
			positions[index][0][0] = x;
			positions[index][0][1] = y;
			positions[index][0][2] = 0;

			// If the x and y co-ordinates has not ben hit yet shoot as normal.
			if (!checkIfAlreadyHit(grid)) {
				if (cell.wasShot)

					// continue;

					enemyTurn = cell.shoot();

				positions[index][0][2] = 1;

				System.out.println("VALUES OF POINTS :" + x + " " + y);
				// These variables are used to track the previous shot so
				// shootAdjacent knows where to shoot.
				previousX = x;
				previousY = y;

				// If statement used to decrement the Player's Health.
				if (grid.getCell(x, y).shoot() == true) {
					soundController.defaultHit();
					pHealth--;
					System.out.println("YOUR HEALTH IS " + pHealth);
					previousHit = true;
				} else {
					soundController.defaultMiss();
					previousHit = false;
				}

				// If Player's Health reaches 0 then the player loses.
				if (pHealth == 0) {
					System.out.println("YOU LOSE");
					pop2.display();
				}

			}

		}
		enemyTurn = false;
		System.out.println("ACTUAL VALUES OF INDEXES X: " + positions[index][0] + " " + "y: " + positions[index][1]);

		index++;

		System.out.println("Index values after Increment : " + index);

	}

	public boolean checkIfAlreadyHit(Grid grid) {
		boolean isHit = false;
		System.out.println(isHit);
		int temp = getIndex();
		System.out.println(" value of x and Y: " + x + " " + y);

		// Loop used to go over the array and check if any of the indexes match
		// the x and y values if so it sets isHit boolean to true.
		for (int i = 0; i < positions.length; i++) {
			if (positions[i][0][0] == x && positions[i][0][1] == y && positions[i][0][2] == 1) {
				System.out.println("Same Cell been shot at index : " + i);

				isHit = true;
				System.out.println(isHit);
			}

			// re-roll
		}

		// If isHit is true then shootSomewhereElse.
		if (isHit == true) {
			shootSomewhereElse(grid);

			// If player the cell is a hit then the player's health is
			// decremented.
			if (grid.getCell(x, y).shoot() == true) {

				pHealth--;
				System.out.println("YOUR HEALTH IS : " + pHealth);

				// Boolean used to check whether the AI has landed a shot, where
				// shootAdjacent uses this boolean value to determine when to be
				// called.
				previousHit = true;
			} else {
				previousHit = false;
			}

			// If Player health reaches 0 then player loses the game.
			if (pHealth == 0) {
				System.out.println("YOU LOSE");
				pop2.display();
			}
		}
		printArray();

		System.out.println("NEW X : " + x + " " + "Y: " + y);
		System.out.println(isHit);
		return isHit;

	}

	/**
	 * @author Damon Tran Method used to shoot a different cell given that the
	 *         AI shoots a random shot that has already been Hit.
	 * @param grid
	 */
	public void shootSomewhereElse(Grid grid) {
		boolean unique = false;
		int temp = getIndex();
		int count = 0;

		// Re -rolls the values.
		x = random.nextInt(10);

		y = random.nextInt(10);

		System.out.println("Rerolled x and Y values " + x + " " + y);

		// Loop used to check if x and y are already in the Aray.
		for (int i = 0; i < positions.length; i++) {

			// If the values aren't in the Array then it exits the nested if
			// statement and shoots as normal.
			if (x != positions[i][0][0] && y != positions[i][0][1]) {
				System.out.println(x + "and " + y + "  are not in the index " + i);

				// re-roll
				// If Re-rolled values are already in the array then it goes
				// into a do while loop.
			} else if (x == positions[i][0][0] && y == positions[i][0][1] && positions[i][0][2] == 1
					|| x == positions[0][0][0] && y == positions[0][0][1] && positions[0][0][2] == 1) {
				i = i - i;
				do {
					// Values are constantly re -rolled and compared against all
					// indexes in the array. Until it gets a value that doesn't
					// exist and shoots it.
					x = random.nextInt(10);
					y = random.nextInt(10);

					if (positions[i][0][0] != x && positions[i][0][1] != y) {

						System.out.println("Re reroll 2: " + x + " " + y);

						unique = true;
					}

					System.out.println(i);

				} while (unique = false);
				System.out.println(i);
			}

		}
		// Shoots the cell
		Cell cell2 = grid.getCell(x, y);
		cell2.shoot();

		// Stores the shot in the aray.
		positions[temp][0][0] = x;
		positions[temp][0][1] = y;
		positions[temp][0][2] = 1;

		printArray();

	}

	public void startGame(Grid grid, Grid grid2) {
		running = true;
		AiPlaceShips(grid2);

	}

	/**
	 * @author Damon Places the ships on AI grid at random places
	 * @param grid2
	 */
	public void AiPlaceShips(Grid grid2) {

		Random random = new Random();
		int posX;
		int posY;
		boolean isVertical;

		Random rando = new Random();
		posX = random.nextInt(9);
		posY = random.nextInt(9);
		isVertical = random.nextBoolean();

		boolean placedBs = false;
		boolean placedAc = false;
		boolean placedSub = false;
		boolean placedDe = false;
		boolean placedCr = false;

		posX = random.nextInt(9);
		posY = random.nextInt(9);
		isVertical = random.nextBoolean();

		do {
			if (grid2.canPlaceShip(new Battleship(isVertical), posX, posY)) {
				grid2.placeShip(new Battleship(isVertical), posX, posY);
				placedBs = true;
			} else {
				posX = random.nextInt(9);
				posY = random.nextInt(9);
				isVertical = random.nextBoolean();
			}
		} while (placedBs == false);

		System.out.println(posX + "+ " + posY);

		posX = random.nextInt(9);
		posY = random.nextInt(9);
		isVertical = random.nextBoolean();
		do {
			if (grid2.canPlaceShip(new AircraftCarrier(isVertical), posX, posY)) {
				grid2.placeShip(new AircraftCarrier(isVertical), posX, posY);
				placedAc = true;
			} else {
				posX = random.nextInt(9);
				posY = random.nextInt(9);
				isVertical = random.nextBoolean();
			}
		} while (placedAc == false);

		System.out.println(posX + "+ " + posY);

		posX = random.nextInt(9);
		posY = random.nextInt(9);
		isVertical = random.nextBoolean();
		do {
			if (grid2.canPlaceShip(new Cruiser(isVertical), posX, posY)) {
				grid2.placeShip(new Cruiser(isVertical), posX, posY);
				placedCr = true;
			} else {
				posX = random.nextInt(9);
				posY = random.nextInt(9);
				isVertical = random.nextBoolean();
			}
		} while (placedCr == false);

		System.out.println(posX + "+ " + posY);

		posX = random.nextInt(9);
		posY = random.nextInt(9);
		isVertical = random.nextBoolean();
		do {
			if (grid2.canPlaceShip(new Submarine(isVertical), posX, posY)) {
				grid2.placeShip(new Submarine(isVertical), posX, posY);
				placedSub = true;
			} else {
				posX = random.nextInt(9);
				posY = random.nextInt(9);
				isVertical = random.nextBoolean();
			}
		} while (placedSub == false);

		System.out.println(posX + "+ " + posY);

		posX = random.nextInt(9);
		posY = random.nextInt(9);
		isVertical = random.nextBoolean();

		do {
			if (grid2.canPlaceShip(new Destroyer(isVertical), posX, posY)) {
				grid2.placeShip(new Destroyer(isVertical), posX, posY);
				placedDe = true;
			} else {
				posX = random.nextInt(9);
				posY = random.nextInt(9);
				isVertical = random.nextBoolean();
			}
		} while (placedDe == false);

		System.out.println(posX + "+ " + posY);
	}

	public GameController getGameContoller() {
		return gameController;
	}

	/**
	 * @author Damon Tran Method used to allow the user to shoot at enemy Grid.
	 * @param grid
	 * @param grid2
	 */

	public void Move(Grid grid, Grid grid2) {
		// I hit , he hits, i hits, he hits

		// If the user clicks on the enemy grid then...
		if (grid2.setOnCellClicked(event -> {

			// Sets boolean to false so it knows it is the user's turn.
			enemyTurn = false;

			// Get the cell where the user shoots.
			if (event.getSource() instanceof Cell) {
				Cell c = (Cell) event.getSource();

				// If the user shoots a shot that's already been shot then
				// prints statement.
				if (c.wasShot) {

					System.out.println("Cant shoot twice in same cell");

				} else {
					// Shoots the cell and sets the boolean for that cell.
					c.shoot();
					c.wasShot = true;
					Cell cell = (Cell) event.getSource();

					// Calls enemyMove method so AI shoots back when the user
					// fires.
					enemyMove(grid, grid2);

					// Conditionals used to decrement the AI Health.
					if (c.shoot() == true) {
						soundController.defaultHit();
						cHealth--;
						System.out.println("CPU Health is : " + cHealth);
					} else {
						soundController.defaultMiss();

					}

					// If the Computer's health reaches 0 then the player wins.
					if (cHealth == 0) {
						System.out.println("YOU WIN");
						pop.display();
					}

				}
			}
		}))
			;

		System.out.println(enemyTurn);
		System.out.println("user array : " + userHits[0][0][0] + userHits[0][0][1] + userHits[0][0][2]);

	}

	public boolean getEnemyMove() {
		return enemyTurn;
	}

	/**
	 * @author Damon
	 * @return int (index)
	 */

	public int getIndex() {
		return index;
	}

	public void printArray() {
		for (int i = 0; i < positions.length; i++) {
			System.out.println(positions[i][0][0] + " " + positions[i][0][1] + " " + positions[i][0][2]);
		}
	}

	public String readDifficulty() {
		return difficulty;
	}

	/**
	 * @author Damon Tran Method Used to shoot adjacent grids given the AI is
	 *         hard.
	 * @param grid
	 */
	public void shootAdjacent(Grid grid) {
		// roll value is used to decide which cell the AI shoots adjacent from
		// previous shot.
		int roll = random.nextInt(3);
		int adjacentX = 0;
		int adjacentY = 0;
		int temp = getIndex();
		System.out.println("Previous hit status : " + previousHit);
		// 0 = up, 1= left, 2 = right, 3 = down

		// If previousHit is true it activates switch case
		// Switch cases are used to determine where to shoot based on the value
		// rolled.
		if (previousHit == true) {
			switch (roll) {
			case 0:

				adjacentX = previousX;
				adjacentY = previousY + 1;

				x = adjacentX;
				y = adjacentY;
				System.out.println("Adjacent X and Y : " + adjacentX + adjacentY);
				if (adjacentY == 10) {
					shootSomewhereElse(grid);
				} else {
					if (!checkIfAlreadyHit(grid)) {
						Cell cell0 = grid.getCell(x, y);
						cell0.shoot();
						positions[temp][0][0] = x;
						positions[temp][0][0] = y;
						positions[temp][0][0] = 1;

					} else
						// re-roll roll value 1-3
						do {
							roll = random.nextInt(3);
						} while (roll == 0);
				}
				break;

			case 1:

				adjacentX = previousX - 1;
				adjacentY = previousY;
				x = adjacentX;
				y = adjacentY;
				if (adjacentX == -1) {
					shootSomewhereElse(grid);
				} else {
					if (!checkIfAlreadyHit(grid)) {
						Cell cell0 = grid.getCell(x, y);
						cell0.shoot();
						positions[temp][0][0] = x;
						positions[temp][0][0] = y;
						positions[temp][0][0] = 1;
						System.out.println("Adjacent X and Y : " + adjacentX + adjacentY);
					} else
						// re-roll roll value 2-3
						do {
							roll = random.nextInt(3);
						} while (roll == 1);
				}
				break;

			case 2:

				adjacentX = previousX + 1;
				adjacentY = previousY;
				x = adjacentX;
				y = adjacentY;
				if (adjacentX == 10) {
					shootSomewhereElse(grid);
				} else {
					if (!checkIfAlreadyHit(grid)) {

						Cell cell0 = grid.getCell(x, y);
						cell0.shoot();
						positions[temp][0][0] = x;
						positions[temp][0][0] = y;
						positions[temp][0][0] = 1;
						System.out.println("Adjacent X and Y : " + adjacentX + adjacentY);
					} else
						do {
							roll = random.nextInt(3);
						} while (roll == 1);
				}
				break;

			case 3:

				adjacentX = previousX;
				adjacentY = previousY + 1;
				x = adjacentX;
				y = adjacentY;
				if (adjacentY == 10) {
					shootSomewhereElse(grid);
				} else {

					if (!checkIfAlreadyHit(grid)) {
						Cell cell0 = grid.getCell(x, y);
						cell0.shoot();
						positions[temp][0][0] = x;
						positions[temp][0][0] = y;
						positions[temp][0][0] = 1;
						System.out.println("Adjacent X and Y : " + adjacentX + adjacentY);
					} else
						do {
							roll = random.nextInt(3);

						} while (roll == 2);
				}
				// go into array look at the latest value
			}

		}

		System.out.println("Adjacent X and Y : " + adjacentX + adjacentY);

	}

	/**
	 * @author Damon Tran Nuke method for the player to activate his perk.
	 * @param grid
	 * @param grid2
	 */
	public void nuke(Grid grid, Grid grid2) {
		// If the AI grid is pressed by the player then...
		if (grid2.setOnCellClicked(event -> {
			// Count is used as a class variable to check whether the player has
			// shot already or not (Could have used a boolean)
			if (count == 0) {

				// If the perk hasn't already been used then it shoots at the
				// cell the player targets and the subsequent 2 cells over.
				if (event.getSource() instanceof Cell) {
					Cell c = (Cell) event.getSource();
					c.shoot();
					// Calls the enemoyMove method so it fires back if player
					// uses perk.
					enemyMove(grid, grid2);
					// Sets the next shots to be x+1
					int yNuke = c.getRow();
					int xNuke = c.getColumn() + 1;

					// Sets counter to 0
					int counter = 0;

					// Loop used to fire the next 2 shots from the target cell.
					while (counter < 2) {

						xNuke = c.getColumn() + 1;
						c = grid2.getCell(xNuke, yNuke);
						c.shoot();
						counter++;
					}
				}
			} else {
				// Calls the player Move Method. to re-initialise normal
				// shooting.
				Move(grid, grid2);
			}
			// Sets counter to 0 as it knows the perk has now been used.
			count = 1;
		}))
			;

	}

	/**
	 * @author Damon Tran AiNuke method used to allow the AI to use the Nuke
	 *         perk.
	 * @param grid
	 */

	public void aiNuke(Grid grid) {
		// Sets the boolean to true so it knows perk has been used.

		AiusedPerk = true;
		// Gets the index and stores it as a local class variable.
		int temp = getIndex();

		// Randomises the shots where the AI is going to shoot.
		int ranX = random.nextInt(9);
		int ranY = random.nextInt(9);

		// Creates cell object and shoots it at given location.
		Cell cell = grid.getCell(ranX, ranY);
		cell.shoot();
		System.out.println("ran X :" + ranX + ranY);

		// Stores the Shots in the array.
		positions[temp + 1][0][0] = ranX;
		positions[temp + 1][0][1] = ranY;
		positions[temp + 1][0][2] = 1;

		// Sets the value counter to 0 so to increment in loop.
		int counter = 0;

		// While loop used to increment the shots.
		while (counter < 2) {
			ranX += 1;

			// If the shot is out of bounds we just ignore it.
			if (ranX == 10) {
				ranX -= 2;
			}

			cell = grid.getCell(ranX, ranY);
			cell.shoot();

			// Stores the subsequent nuked values in the array.
			positions[temp + counter + 2][0][0] = ranX;
			positions[temp + counter + 2][0][1] = ranY;
			positions[temp + counter + 2][0][2] = 1;

			// Increment counter.
			counter++;
		}

	}

}