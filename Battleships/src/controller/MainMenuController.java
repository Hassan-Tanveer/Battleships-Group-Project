package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.HelpScene;
import model.MainMenuScene;
import model.NationsScene;
import model.OptionsScene;

/*
 * Class representing the main menu screen
 * 
 * @author Hassan Tanveer, Rajan Gopalji and Vijesh Tharmabala  
 * @Version 1.0
 */



public class MainMenuController extends javafx.application.Application implements Initializable {

	@FXML
	private Button newGameBtn, optionsBtn, helpBtn, exitBtn;
	//Implements the main menu buttons

	
	/*
	 * Method used to start a new game
	 * Opens nation option menu
	 * 
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		newGameBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				System.out.println("New Game");
				Scene scene = newGameBtn.getScene();
				Stage window = (Stage) scene.getWindow();
				try {
					window.setScene(new NationsScene());
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		});

		/*
		 * Options button will switch to the options menu
		 * This opened in the new window
		 */
		optionsBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				System.out.println("Options");
				Scene scene = optionsBtn.getScene();
				Stage window = (Stage) scene.getWindow();
				try {
					window.setScene(new OptionsScene());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});

		/*
		 * Help button will switch to the help menu 
		 * providing instructions for the game
		 */
		helpBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				System.out.println("Help");
				Scene scene = helpBtn.getScene();
				Stage window = (Stage) scene.getWindow();
				try {
					HelpMenuController.setReturn(new MainMenuScene());
					window.setScene(new HelpScene());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});


		/*
		 * Exit button will end the game
		 */
		
		exitBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				System.exit(0);
			}

		});

	}

	@Override
		
	/*
	 *This method beigns the game
	 */
		
	public void start(Stage primaryStage) throws Exception {
		Scene scene = newGameBtn.getScene();
		scene.getStylesheets().clear();
		primaryStage.setTitle("Naval Warfare");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
