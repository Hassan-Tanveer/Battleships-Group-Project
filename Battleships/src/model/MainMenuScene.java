package model;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainMenuScene extends Scene {

	private static final String FXML_PATH = "/view/MainMenuView.fxml";

	public MainMenuScene() throws IOException {
		super((Parent) FXMLLoader.load(MainMenuScene.class.getResource(FXML_PATH)));
	}

}
