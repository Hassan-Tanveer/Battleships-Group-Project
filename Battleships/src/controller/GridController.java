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
import model.MainMenuScene;
/*
 * Class utilised for the quit button
 * 
 * @ Author Rajan Gopalji and Hassan Tanveer
 * @ Version 1.0 
 */

public class GridController implements Initializable {

	//Implements the quit button
	@FXML
	private Button quitBtn;

	/*
	 * When quit button is selected it will return back to main menu screen
	 * 
	 */
	
	public void initialize(URL location, ResourceBundle resources) {

		quitBtn.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {

				Scene scene = quitBtn.getScene();
				Stage window = (Stage) scene.getWindow();
				try {
					window.setScene(new MainMenuScene());
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		});
	}
}
