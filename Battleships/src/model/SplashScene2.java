package model;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SplashScene2 extends Scene {
	private static final String FXML_PATH = "/view/SplashScreen2.fxml";

	public SplashScene2() throws IOException {
		super((Parent) FXMLLoader.load(SplashScene1.class.getResource(FXML_PATH)));
	}

}
