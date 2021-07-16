package controller;

import java.net.URISyntaxException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
/*
 * Represents a class for controlling the sound within the game
 * @ author Eric Jivraj and Humza Malik
 * @ Version 1.0
 */


/*
 * 
 */
public class SoundController {

	public MediaPlayer soundEffect;
	OptionsMenuController omc = new OptionsMenuController();

	public SoundController() {

	}

	public void defaultSoundSettings() {
		soundEffect.play();
		soundEffect.setVolume(omc.getVolume());
	}

	/*
	 * When a ship has been hit, call the corresponding audio file
	 * At the specified volume
	 * 
	 */
	public void defaultHit() {

		try {
			soundEffect = new MediaPlayer(
					new Media(getClass().getResource("/media/audio/defaultHit.mp3").toURI().toString()));
			soundEffect.play();
			soundEffect.setVolume(omc.getVolume());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/*
	 * When a shot has been missed then call the corresponding audio file
	 * That is associated with a miss from media file
	 */
	
	public void defaultMiss() {

		try {
			soundEffect = new MediaPlayer(
					new Media(getClass().getResource("/media/audio/defaultMiss.mp3").toURI().toString()));
			defaultSoundSettings();
			soundEffect.setVolume(omc.getVolume());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/*
	 * When ship placed on the grid then call the audio file
	 * that corresponds to the placement of ships
	 */
	public void defaultPlaceShip() {
		try {
			soundEffect = new MediaPlayer(
					new Media(getClass().getResource("/media/audio/defaultPlaceShip.mp3").toURI().toString()));
			defaultSoundSettings();
			soundEffect.setVolume(omc.getVolume());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/*
	 * When submarine is selected, play the audio file
	 * For a set amount of time
	 */
	
	public void pickSubmarine() {
		try {
			soundEffect = new MediaPlayer(
					new Media(getClass().getResource("/media/audio/submarine.mp3").toURI().toString()));
			soundEffect.play();
			soundEffect.setVolume(omc.getVolume());
			soundEffect.setStartTime(Duration.seconds(2.0));
			soundEffect.setStopTime(Duration.seconds(4.0));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/*
	 * When Carrier is selected play the corresponding audio file for a fixed amount of time
	 * Then switch back to the default background music
	 */
	
	public void pickCarrier() {
		try {
			soundEffect = new MediaPlayer(
					new Media(getClass().getResource("/media/audio/carrier.mp3").toURI().toString()));
			defaultSoundSettings();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/*
	 * When Cruiser is selected play the cruiser audio file.
	 * 
	 */
	
	public void pickCruiser() {
		try {
			soundEffect = new MediaPlayer(
					new Media(getClass().getResource("/media/audio/cruiser.mp3").toURI().toString()));
			soundEffect.play();
			soundEffect.setVolume(omc.getVolume());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/*
	 * When battleship is selected play the corresponding audio file 
	 * At the default sound settings
	 */
	
	public void pickBattleShip() {
		try {
			soundEffect = new MediaPlayer(
					new Media(getClass().getResource("/media/audio/battleship.mp3").toURI().toString()));
			defaultSoundSettings();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/*
	 * When Destroyer is selected play the audio file
	 * At the specified volume
	 */
	
	public void pickDestroyer() {
		try {
			soundEffect = new MediaPlayer(
					new Media(getClass().getResource("/media/audio/destroyer.mp3").toURI().toString()));
			soundEffect.play();
			soundEffect.setVolume(omc.getVolume());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

}
