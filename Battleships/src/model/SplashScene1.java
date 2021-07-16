package model;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SplashScene1 extends Scene {

	private static final String FXML_PATH = "/view/SplashScreen1.fxml";

	public SplashScene1() throws IOException {
		super((Parent) FXMLLoader.load(SplashScene1.class.getResource(FXML_PATH)));
	}
}
