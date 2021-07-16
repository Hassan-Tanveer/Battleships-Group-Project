package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/*
 * Class representing the help menu screen
 * 
 * @ author Hassan Tanveer and Rajan Gopalji 
 * @ Version 1.0
 */


public class HelpMenuController implements Initializable {

	@FXML
	private Button backBtn;
	//Implements the back button
	public static Scene returnScene;
	//returns help scene
	public static void setReturn(Scene p) {
		returnScene = p;
	}

	/*
	 * When back button is selected it should switch back to main menu screen 
	 * 
	 */
	public void initialize(URL location, ResourceBundle resources) {

		backBtn.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {

				Scene scene = backBtn.getScene();
				Stage window = (Stage) scene.getWindow();
				window.setScene(returnScene);

			}

		});

	}
}