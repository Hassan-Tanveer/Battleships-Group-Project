package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import model.Launch;
import model.MainMenuScene;
import model.OptionsScene;
/*
 * Represents a class for the Options menu
 * 
 * @author Hassan Tanveer and Eric Jivraj
 * @version 1.0
 */


/*
 * Implements all the buttons within the options menu
 */
public class OptionsMenuController implements Initializable {

	// private Launch launch;
	@FXML
	private Button cancelBtn;
	@FXML
	private Button defaultBtn;
	@FXML
	private RadioButton easyBtn;
	@FXML
	private RadioButton hardBtn;

	@FXML
	private Slider volBtn;

	/*
	 * Method used to control the volume slider 
	 */
	
	public void initialize(URL location, ResourceBundle resources) {
		// CODE THAT MAKES THE VOLUME SLIDER WORK: BEGINNING
		volBtn.valueProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(javafx.beans.Observable arg0) {
				if (volBtn.isValueChanging())
					Launch.getAudio().setVolume(volBtn.getValue() / 100);

				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(".\\test.txt"));
//					writer.write(volBtn.getValue() / 100);
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});
		// END
		//Takes you back to the main menu page
		cancelBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {

				Scene scene = cancelBtn.getScene();
				Stage window = (Stage) scene.getWindow();
				try {
					window.setScene(new MainMenuScene());
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		});

		/*
		 * This applies the default settings which is applied to the difficulty of the game
		 * Sets the volume at the default settings
		 */
		
		defaultBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				Scene scene = defaultBtn.getScene();
				Stage window = (Stage) scene.getWindow();
				Launch.getAudio().setVolume(0.3);
				try {
					window.setScene(new OptionsScene());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		/*
		 * Applies the easy version of the game
		 */
		easyBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				String eString = "easy";

				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(".\\test.txt"));
					writer.write(eString);
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});
		
		/*
		 * Applies the hard version of the game
		 */

		hardBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				String eString = "hard";

				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(".\\test.txt"));
					writer.write(eString);
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});

	}

	public double getVolume() {
		return Launch.getAudio().getVolume();
	}

}
