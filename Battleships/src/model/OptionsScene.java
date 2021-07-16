package model;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class OptionsScene extends Scene{

	public static final String FXML_PATH = "/view/OptionsMenuView.fxml";
	
	public OptionsScene() throws IOException{
		super((Parent)FXMLLoader.load(OptionsScene.class.getResource(FXML_PATH)));
	}
	
}
