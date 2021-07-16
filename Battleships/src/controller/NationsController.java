package controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.MainMenuScene;
import model.ShipsScene;

/* 
 * Represents the selection of a nation by the player.
 * 
 * @author Hassan Tanveer and Rajan Gopalji
 * @Version 1.0
 * 
 */


public class NationsController implements Initializable {
	//Declares all the buttons within the nation scene
	@FXML
	private Button confirmBtn;
	@FXML
	private Button rtnMenuBtn;
	@FXML
	private ToggleGroup Nation;
	@FXML
	private ToggleButton UKBtn;
	@FXML
	private ToggleButton NKBtn;
	@FXML
	private ToggleButton USABtn;

	//Initalises the counter to zero
	private int counter = 0;
	//Instance of a media player
	private MediaPlayer mediaplayer;
	
	private OptionsMenuController omc = new OptionsMenuController();

	private static String currentNation = "";

	//Returns the name of the current nation your in
	public static String getCurrentNation() {
		return currentNation;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		confirmBtn.setDisable(true);
		confirmBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {

				Scene scene = confirmBtn.getScene();
				Stage window = (Stage) scene.getWindow();

				try {
					window.setScene(new ShipsScene());
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

		);

		/*
		 * When the UK button is selected 
		 * The confirm button should be enabled
		 * Play the corresponding audio file for a fixed amount of time
		 * 
		 * @param the users mouse event to check if the button is clicked
		 * 
		 */
		UKBtn.setOnMousePressed(event -> {

			Media musicFile = null;
			try {
				musicFile = new Media(getClass().getResource("/media/audio/UK1.mp3").toURI().toString());

			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

			mediaplayer = new MediaPlayer(musicFile);
			mediaplayer.setAutoPlay(true);
			mediaplayer.setVolume(omc.getVolume());
			mediaplayer.setStartTime(Duration.seconds(2.0));
			mediaplayer.setStopTime(Duration.seconds(7.2));
			Timeline timeline = new Timeline(
					new KeyFrame(Duration.seconds(6), new KeyValue(mediaplayer.volumeProperty(), 0)));
			timeline.play();

			counter = 1;
			if (event.getSource() != null && counter > 0) {
				confirmBtn.setDisable(false);
				currentNation = "uk";
			}
			currentNation = "uk"; // used so that the image can be displayed on the next scene
		});

		/*
		 * When the North Korea button is selected 
		 * The confirm button should be enabled
		 * Play the corresponding audio file for a fixed amount of time
		 * 
		 * @param the users mouse event to check if the button is clicked
		 * 
		 */
		
		NKBtn.setOnMousePressed(event -> {

			Media musicFile = null;
			try {
				musicFile = new Media(getClass().getResource("/media/audio/northKorea.mp3").toURI().toString());

			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

			mediaplayer = new MediaPlayer(musicFile);
			mediaplayer.setAutoPlay(true);
			mediaplayer.setVolume(omc.getVolume());
			mediaplayer.setStartTime(Duration.seconds(1));
			mediaplayer.setStopTime(Duration.seconds(6));
			Timeline timeline = new Timeline(
					new KeyFrame(Duration.seconds(5.1), new KeyValue(mediaplayer.volumeProperty(), 0)));
			timeline.play();

			counter = 1;
			if (event.getSource() != null && counter > 0) {
				confirmBtn.setDisable(false);
				currentNation = "northKorea";
			}
			currentNation = "NK";
		});

		/*
		 * When the USA button is selected 
		 * The confirm button should be enabled
		 * Play the corresponding audio file for a fixed amount of time
		 * 
		 * @param the users mouse event to check if the button is clicked
		 * 
		 */
		USABtn.setOnMousePressed(event -> {

			Media musicFile = null;
			try {
				musicFile = new Media(getClass().getResource("/media/audio/USA.mp3").toURI().toString());

			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

			mediaplayer = new MediaPlayer(musicFile);
			mediaplayer.setAutoPlay(true);
			mediaplayer.setVolume(omc.getVolume());
			mediaplayer.setStopTime(Duration.seconds(4.2));
			Timeline timeline = new Timeline(
					new KeyFrame(Duration.seconds(4.2), new KeyValue(mediaplayer.volumeProperty(), 0)));
			timeline.play();

			counter = 1;
			if (event.getSource() != null && counter > 0) {
				confirmBtn.setDisable(false);
				currentNation = "USA";
			}
			currentNation = "US";
		});

		/*
		 * Return menu button should allow the user to switch back to the main menu scene
		 * 
		 */
		rtnMenuBtn.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {

				Scene scene = rtnMenuBtn.getScene();
				Stage window = (Stage) scene.getWindow();
				try {
					window.setScene(new MainMenuScene());
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

		);
	}

}
