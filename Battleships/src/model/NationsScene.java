package model;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class NationsScene extends Scene {

	public static final String FXML_PATH = "/view/NationsView.fxml";

	public NationsScene() throws IOException {
		super((Parent) FXMLLoader.load(NationsScene.class.getResource(FXML_PATH)));
	}
}
