package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.Launch;

/*Represents a loading screen before the game commences 
 * 
 * @ author Hassan Tanveer
 * @ version 1.0
 */



public class SplashScreenController implements Initializable {
	
	
	@FXML
	private StackPane rootPane;

	/*
	 * Method used to create a loading screen which then transitions
	 * to the main menu screen
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		new SplashScreen().start();
	}

	class SplashScreen extends Thread {
		public void run() {
			try {
				Thread.sleep(8500);
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						Parent root = null;
						try {
							root = FXMLLoader.load(getClass().getResource("/view/SplashScreen2.fxml"));
						} catch (IOException e) {
							e.printStackTrace();
						}

						//fades out the opening loading screen to then transition to
						//then switch to phony page.
						
						FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), root);
						fadeOut.setFromValue(1);
						fadeOut.setToValue(0);
						fadeOut.setCycleCount(1);
						fadeOut.play();

						FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), root);
						fadeIn.setFromValue(0);
						fadeIn.setToValue(1);
						fadeIn.setCycleCount(1);
						fadeIn.play();

						Scene scene = new Scene(root);
						Stage stage = new Stage();
						stage.setScene(scene);
						stage.sizeToScene();
						stage.setResizable(false);
						stage.initStyle(StageStyle.UNDECORATED);
						stage.show();
					}
				});

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(8000);
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						Parent root = null;
						try {
							root = FXMLLoader.load(getClass().getResource("/view/MainMenuView.fxml"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						Launch.getAudio().stop();
						Scene scene = new Scene(root);
						Stage stage = new Stage();
						stage.setScene(scene);
						stage.sizeToScene();
						stage.setResizable(false);
						stage.initStyle(StageStyle.UNDECORATED);
						stage.show();
						Launch l = new Launch();
						l.startGameMusic();
						
					}
				});

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
