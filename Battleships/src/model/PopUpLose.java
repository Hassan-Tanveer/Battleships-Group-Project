package model;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class PopUpLose {

	public static void display() {
		Stage popupwindow = new Stage();

		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("GAME OVER");

		Label winLbl = new Label("YOU  LOSE");
		winLbl.setStyle(" -fx-text-fill: white; -fx-font-weight: bold; -fx-font: 14 garamond; -fx-border-radius: 5em;");

		Button quitBtn = new Button("RETURN");
		quitBtn.setStyle(
				" -fx-text-fill: white; -fx-font: 14 garamond; -fx-background-color: #f27121; -fx-border-radius: 5em;");

		quitBtn.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				popupwindow.hide();
			}
		});

		// quitBtn.setOnMouseClicked(new EventHandler<Event>() {
		// @Override
		// public void handle(Event arg0) {
		// Scene scene = quitBtn.getScene();
		// Stage window = (Stage) scene.getWindow();
		// try {
		// window.setScene(new MainMenuScene());
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		//
		// });

		VBox layout = new VBox(10);
		layout.setStyle("-fx-background-color: #1e2f3f");
		layout.getChildren().addAll(winLbl, quitBtn);
		layout.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(layout, 300, 250);

		popupwindow.setScene(scene1);
		popupwindow.showAndWait();

	}

}