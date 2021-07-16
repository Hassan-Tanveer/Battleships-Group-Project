package model;

import java.io.IOException;
import java.net.URISyntaxException;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

	/*
	* Used to launch the game
	* 
	* @author Rajan
	* @Version 1.0 
	*/

public class Launch extends javafx.application.Application {

	//Instance of media player
	public static MediaPlayer mediaplayer;

	/*
	 * Launches the game in a new window
	 * Displaying the main menu
	 */
	public void start(Stage primaryStage) throws IOException {

		primaryStage.initStyle(StageStyle.UNDECORATED);
		startSplashMusic();
		primaryStage.setTitle("Naval Warfare");
		primaryStage.setScene(new SplashScene1());
		primaryStage.sizeToScene();
		primaryStage.setResizable(false);
		primaryStage.show();
		
		/*primaryStage.setScene(new SplashScene2());
		primaryStage.show();*/
		
		Image image = new Image(getClass().getResourceAsStream("/sprites/icon.png"));
		primaryStage.getIcons().add(image);
	}

	/*
	 * Starts the game
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/*
	 * When the game is launched the opening theme song will begin to play
	 */
	public void startGameMusic(){
		try {
			mediaplayer = new MediaPlayer(
					new Media(getClass().getResource("/media/audio/gameMusic.mp3").toURI().toString()));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		mediaplayer.setAutoPlay(true);
		mediaplayer.setCycleCount(Integer.MAX_VALUE);
		mediaplayer.setVolume(0.3);
	}

	public void startSplashMusic() {

		try {
			mediaplayer = new MediaPlayer(
					new Media(getClass().getResource("/media/audio/ssMusic.mp3").toURI().toString()));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		mediaplayer.setAutoPlay(true);
		mediaplayer.setCycleCount(Integer.MAX_VALUE);
		mediaplayer.setVolume(0.3);

	}
	/*
	 * Stops the music
	 */
	
	public static void stopMusic() {
		mediaplayer.stop();
	}

	/*
	 * Returns the audio file currently being played from the media folder
	 */
	public static MediaPlayer getAudio() {
		return mediaplayer;
	}
}
