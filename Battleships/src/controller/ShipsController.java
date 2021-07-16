package controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import battleship.AircraftCarrier;
import battleship.Battleship;
import battleship.Cell;
import battleship.Cruiser;
import battleship.Destroyer;
import battleship.Grid;
import battleship.Ship;
import battleship.Submarine;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.GridsScene;
import model.HelpScene;
import model.Launch;
import model.NationsScene;
import model.ShipsScene;

/**
 * ShipsController.java Used to place ships on the grid in the select scene.
 * 
 * @author Raan Gopalji,, Hassan Tanveer, Damon Tran, Eric Jivraj, Amir
 *         Makanvand.
 *
 */

public class ShipsController implements Initializable {

	private int counter = 1; 
	// Initialisation of counter to 1
	private int numPieces = 0; 
	// Initialisation of numPieces to 0
	private SoundController soundController; 
	//instance of SoundController
	public static MediaPlayer mediaplayer; 
	//Instance of MediaPlayer

	/*
	 * Sets the value of ShipType with all known ships available (Aircraft
	 * Carrier, Submarine, ...) Each updates the name for the image with the
	 * file path. This is done through the ImageName method.
	 */
	enum ShipType {
		AIRCRAFT_CARRIER {
			public String imageName() {
				return "/sprites/aircraft-carrier/aircraft-carrier.png";
			}

			public Ship createShip(boolean vertical) {
				return new AircraftCarrier(vertical);
			}
		},
		SUBMARINE {
			public String imageName() {
				return "/sprites/submarine/submarine.png";
			}

			public Ship createShip(boolean vertical) {
				return new Submarine(vertical);
			}
		},
		DESTROYER {
			public String imageName() {
				return "/sprites/destroyer/destroyer.png";
			}

			public Ship createShip(boolean vertical) {
				return new Destroyer(vertical);
			}
		},
		BATTLESHIP {
			public String imageName() {
				return "/sprites/battleship/battleship.png";
			}

			public Ship createShip(boolean vertical) {
				return new Battleship(vertical);
			}
		},
		CRUISER {
			public String imageName() {
				return "/sprites/cruiser/cruiser.png";
			}

			public Ship createShip(boolean vertical) {
				return new Cruiser(vertical);
			}
		};

		public abstract String imageName(); 
		//Creates the ships from shipType into a string

		public abstract Ship createShip(boolean y); 
		//Creates the ship upon button click.
	}

	@FXML
	private Grid grid; 
	//Local instance of a grid to implement within the view.
	
	@FXML
	private ImageView imageview; 
	//Instance of the current ship within the view.

	@FXML
	private ImageView flag; 
	//Instance of current flag/nation within the view.

	@FXML
	private ToggleButton cruBtn, bsBtn, subBtn, desBtn, airBtn; 
	//Implements the ship buttons within the view.
	
	@FXML
	private Button submitBtn, backBtn, randomBtn, resetBtn, helpBtn; 
	//Implements the action buttons within the view.

	protected ShipType currentType; 
	// Current ship.

	/**
	 * Initialises the class.
	 */

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		soundController = new SoundController();
		updateFlag();

		// When cruiser button is clicked display cruiser image
		cruBtn.setOnMouseClicked(event -> {
			counter = 1;
			currentType = ShipType.CRUISER;
			updateImage();
			soundController.pickCruiser();
		});

		// When battleship button is clicked display battleship image
		bsBtn.setOnMouseClicked(event -> {
			counter = 1;
			currentType = ShipType.BATTLESHIP;
			updateImage();
			soundController.pickBattleShip();

		});

		// When submarine button is clicked display submarine image
		subBtn.setOnMouseClicked(event -> {
			counter = 1;
			currentType = ShipType.SUBMARINE;
			updateImage();
			soundController.pickSubmarine();

		});

		// When destroyer button is clicked display destroyer image
		desBtn.setOnMouseClicked(event -> {
			counter = 1;
			currentType = ShipType.DESTROYER;
			updateImage();
			soundController.pickDestroyer();

		});

		// When aircraft button is clicked display aircraft image
		airBtn.setOnMouseClicked(event -> {
			counter = 1;
			currentType = ShipType.AIRCRAFT_CARRIER;
			updateImage();
			soundController.pickCarrier();
			// System.out.println(grid);

		});
		
		randomBtn.setOnMouseClicked(new EventHandler<Event>() { 
			// Places ships on the grid when the random button is selected
			@Override
			public void handle(Event arg0) {

				Random random = new Random();
				//Calls two ints for the x and y positions
				int posX; 
				int posY;
				boolean isVertical; 
				//Calls for a boolean to keep track of the ship layout (vertical/horizontal) horizontal is default

				//randomises x/y between a scope of 10 
				posX = random.nextInt(9); 
				posY = random.nextInt(9);
				isVertical = random.nextBoolean(); 
				//randomises verticality.

				// initialises a boolean for each ship (Battleship, Aircraft Carrier, ...) defaulted as not placed.
				boolean placedBs = false; 
				boolean placedAc = false;  
				boolean placedSub = false;
				boolean placedDe = false;
				boolean placedCr = false;

				/*
				 * posX = random.nextInt(9); posY = random.nextInt(9);
				 * isVertical = random.nextBoolean();
				 */

				do {
					if (grid.canPlaceShip(new Battleship(isVertical), posX, posY)) { 
						// If the grid can place a ship in a current position, place it
						grid.placeShip(new Battleship(isVertical), posX, posY);
						placedBs = true;											 
						// set placed to true
					} else {
						posX = random.nextInt(9);									 
						// Randomise for next position and verticality.
						posY = random.nextInt(9);
						isVertical = random.nextBoolean();
					}
				} while (placedBs == false); 
				// Will loop until ship is placed
				
				posX = random.nextInt(9); 
				// Randomise next position
				posY = random.nextInt(9); 
				isVertical = random.nextBoolean();
				do {
					if (grid.canPlaceShip(new AircraftCarrier(isVertical), posX, posY)) { 
						// If the grid can place a ship in a current position, place it
						grid.placeShip(new AircraftCarrier(isVertical), posX, posY);	  
						placedAc = true;												  
						// set placed to true
					} else {
						// Randomise for next position and verticality.
						posX = random.nextInt(9);										  
						posY = random.nextInt(9);
						isVertical = random.nextBoolean();
					}
				} while (placedAc == false); 
				
				posX = random.nextInt(9); 
				posY = random.nextInt(9);
				isVertical = random.nextBoolean();
				do {
					if (grid.canPlaceShip(new Cruiser(isVertical), posX, posY)) {
						grid.placeShip(new Cruiser(isVertical), posX, posY);
						placedCr = true;
					} else {
						posX = random.nextInt(9);
						posY = random.nextInt(9);
						isVertical = random.nextBoolean();
					}
				} while (placedCr == false);

				posX = random.nextInt(9);
				posY = random.nextInt(9);
				isVertical = random.nextBoolean();
				do {
					if (grid.canPlaceShip(new Submarine(isVertical), posX, posY)) {
						grid.placeShip(new Submarine(isVertical), posX, posY);
						placedSub = true;
					} else {
						posX = random.nextInt(9);
						posY = random.nextInt(9);
						isVertical = random.nextBoolean();
					}
				} while (placedSub == false);

				posX = random.nextInt(9);
				posY = random.nextInt(9);
				isVertical = random.nextBoolean();

				do {
					if (grid.canPlaceShip(new Destroyer(isVertical), posX, posY)) {
						grid.placeShip(new Destroyer(isVertical), posX, posY);
						placedDe = true;
					} else {
						posX = random.nextInt(9);
						posY = random.nextInt(9);
						isVertical = random.nextBoolean();
					}
				} while (placedDe == false);

				airBtn.setDisable(true);
				bsBtn.setDisable(true);
				cruBtn.setDisable(true);
				desBtn.setDisable(true);
				subBtn.setDisable(true);
				randomBtn.setDisable(true);
				submitBtn.setDisable(false);
			}
		});

		resetBtn.setOnMouseClicked(new EventHandler<Event>() { 
			// Resets ships on the grid when the reset button is selected

			@Override
			public void handle(Event event) { 
				Scene scene = resetBtn.getScene(); 
				// initialise a new scene instance the resetBtn is on
				Stage window = (Stage) scene.getWindow(); 
				// initialise a new window scene from scene
				try {
					window.setScene(new ShipsScene()); 
					// call the ShipsScene again and set it
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		submitBtn.setDisable(true); 
		// By default the submit button will be disabled until all ships are true and on the grid

		submitBtn.setOnMouseClicked(new EventHandler<Event>() { 
			// Submits ships on the grid when the submit button is selected

			@Override
			public void handle(Event arg0) {

				Scene scene = submitBtn.getScene();  
				// initialise a new scene instance the submit button is on
				Stage window = (Stage) scene.getWindow(); 
				// initialise a new window scene from scene
				try {
					window.setScene(new GridsScene(grid));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				// call the GridScene whilst passing through the players grid
				Launch.stopMusic(); 
				// Stop the music playing (preference)

				try {
					mediaplayer = new MediaPlayer( 
							// create a new instance of MediaPlayer into mediaplayer
							new Media(getClass().getResource("/media/audio/battleMusic.mp3").toURI().toString())); 
					// The media will be in the folder and converted to a string
				} catch (URISyntaxException e) {
					e.printStackTrace();

				}
				OptionsMenuController omc = new OptionsMenuController(); 
				mediaplayer.setAutoPlay(true);
				mediaplayer.setCycleCount(Integer.MAX_VALUE);
				mediaplayer.setVolume(omc.getVolume());

			}

		});

		helpBtn.setOnMouseClicked(new EventHandler<Event>() { 
			// Help menu when help button is selected

			@Override
			public void handle(Event arg0) {
				Scene scene = helpBtn.getScene(); 
				// initialise a new scene instance the help button is on
				Stage window = (Stage) scene.getWindow(); 
				// initialise a new window scene from scene
				try {
					HelpMenuController.setReturn(new ShipsScene()); 
					// tell the HelpMenu where to return to (So user can return)
					window.setScene(new HelpScene()); 
					// Set the window to HelpScene
				} catch (IOException e) { 
					e.printStackTrace();
				}

			}

		});

		backBtn.setOnMouseClicked(new EventHandler<Event>() { 
			// Nation menu when back button is selected
			@Override
			public void handle(Event arg0) {

				Scene scene = backBtn.getScene(); 
				// initialise a new scene instance the back button is on
				Stage window = (Stage) scene.getWindow(); 
				// initialise a new window scene from scene
				try {
					window.setScene(new NationsScene()); 
					// Set the window to NationScene
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		});

		grid.setOnCellClicked(event -> { 
			// Set the grid to react on cell click
			if (event.getSource() instanceof Cell) { 
				// If the event is the same as a cell
				Cell c = (Cell) event.getSource();   
				// initialise c of type cell event 
				if (counter == 1) { 
					// if the counter is equal to 1
					if (grid.canPlaceShip(currentType.createShip(event.getButton() == MouseButton.PRIMARY), 
							// if the grid can place the ship the mouse clicked
							c.getColumn(), c.getRow())) { 
						// get the coloumn and row of c
						counter = 0;					  
						// make counter = 0
						grid.placeShip(currentType.createShip(event.getButton() == MouseButton.PRIMARY), c.getColumn(), 
								c.getRow());
						//place the ship selected on the column and row
						if (numPieces == 4) { 
							// if numPieces has 5 "pieces", disable the submit button
							submitBtn.setDisable(false);
						}

						switch (currentType) { 
						/* Switch the current type (Submarine, Cruiser, ... )
						 * // Each ship will play a place sound effect
						 * // ship (submarine) button will disable
						 * // counter will return to 0
						 * // the amount of pieces on grid will increment
						 * // randomBtn will disable to prevent users from selecting a random set
						 */
						
						case SUBMARINE:
							soundController.defaultPlaceShip(); 
							subBtn.setDisable(true);			
							counter = 0;						
							numPieces++;						
							randomBtn.setDisable(true);			
							break;
						case CRUISER:
							soundController.defaultPlaceShip();
							cruBtn.setDisable(true);
							counter = 0;
							numPieces++;
							randomBtn.setDisable(true);
							break;
						case DESTROYER:
							soundController.defaultPlaceShip();
							desBtn.setDisable(true);
							counter = 0;
							numPieces++;
							randomBtn.setDisable(true);
							break;
						case BATTLESHIP:
							soundController.defaultPlaceShip();
							bsBtn.setDisable(true);
							counter = 0;
							numPieces++;
							randomBtn.setDisable(true);
							break;
						case AIRCRAFT_CARRIER:
							soundController.defaultPlaceShip();
							airBtn.setDisable(true);
							counter = 0;
							numPieces++;
							randomBtn.setDisable(true);
							break;
						default:
							break;
						}

					}
				}

			}
		});
	}

	/*
	 * public Grid getGrid() { // if (grid == null) System.out.println(
	 * "null grid"); else System.out.println(grid); return grid; }
	 */

	private void updateImage() { 
		// Updates the current ship image when the user clicks on the corresponding ship via imageView
		Image image = new Image(currentType.imageName()); 
		// creates a local instance of the image by passing through the cureent ship selected
		imageview.setImage(image); 
		// sets imageView
	}
	
	public void stopMusic(){ 
		// Used to stop the music
		mediaplayer.stop();
	}

	private void updateFlag() { 
		// Updates the current nation's flag in correspondence to the current nation via flag
		Image image = new Image("/spritesFlags/" + NationsController.getCurrentNation() + ".png");
		flag.setImage(image);
	}

}
