package model;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ShipsScene extends Scene {

	public static final String FXML_PATH = "/view/ShipsView.fxml";

	public ShipsScene() throws IOException {
		super((Parent)FXMLLoader.load(ShipsScene.class.getResource(FXML_PATH)));
	}
}
