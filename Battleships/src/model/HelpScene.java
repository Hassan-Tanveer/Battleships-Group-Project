package model;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class HelpScene extends Scene{

	public static final String FXML_PATH = "/view/HelpMenuView.fxml";
	
	public HelpScene() throws IOException{
		super((Parent)FXMLLoader.load(HelpScene.class.getResource(FXML_PATH)));
	}
	
}
