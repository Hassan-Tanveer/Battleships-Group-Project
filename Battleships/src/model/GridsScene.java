
package model;

import java.io.IOException;
import java.util.Random;

import battleship.Grid;
import controller.GameController;
import controller.NationsController;
import controller.ShipsController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GridsScene extends Scene {

	static Button perkButton = new Button("Perk");

	public GridsScene(Grid grid) throws IOException {

		super(createContent(grid));

	}

	private static Parent createContent(Grid grid) throws IOException {

		Grid grid2 = new Grid();

		BorderPane root = new BorderPane();
		root.setPrefSize(792, 400);

		System.out.println(grid);
		System.out.println(grid2);
		grid2.setEnemy(true);

		VBox player1grid2 = new VBox(50, grid2);

		root.setLeft(grid);

		HBox hbox = addHBox();
		root.setCenter(hbox);

		root.setCenter(hbox);

		Button quitBtn = new Button("QUIT GAME");

		root.setBottom(quitBtn);
		quitBtn.setTranslateY(-10);
		quitBtn.setTranslateX(680);
		quitBtn.setStyle(
				" -fx-text-fill: white; -fx-font: 14 garamond; -fx-background-color: #f27121; -fx-border-radius: 5em;");

		quitBtn.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {

				Scene scene = quitBtn.getScene();
				Stage window = (Stage) scene.getWindow();
				try {
					window.setScene(new MainMenuScene());
				} catch (IOException e) {
					e.printStackTrace();
				}

				ShipsController sc = new ShipsController();
				sc.stopMusic();

				Launch l = new Launch();
				l.startGameMusic();
			}

		});
		GameController gameController = new GameController(grid, grid2);

		System.out.print(gameController);
		root.setRight(grid2);

		gameController.startGame(grid, grid2);
		gameController.getEnemyMove();
		boolean enemyMove = gameController.getEnemyMove();

		if (enemyMove) {
			gameController.enemyMove(grid, grid2);
		} else if (enemyMove == false) {
			gameController.Move(grid, grid2);
		}

		perkButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				gameController.nuke(grid, grid2);
				perkButton.setDisable(true);

			}
		});
		root.setStyle("-fx-background-color: #1e2f3f;");

		System.out.println(player1grid2);
		return root;
	}

	public static HBox addHBox() {
		HBox hbox = new HBox();
		//hbox.setSpacing(20);
		hbox.setStyle("-fx-background-color: #1e2f3f;");

		ImageView flagView = new ImageView();
		Image image = new Image("/spritesFlags/" + NationsController.getCurrentNation() + ".png");
		flagView.setImage(image);

		String filepath;
		Random rand = new Random();
		int n = rand.nextInt(3) + 1;

		if (n == 1) {
			filepath = "/spritesFlags/NK.png";
		} else if (n == 2) {
			filepath = "/spritesFlags/US.png";
		} else {
			filepath = "/spritesFlags/uk.png";
		}

		ImageView flagViewEnemy = new ImageView();
		Image imageE = new Image(filepath);
		flagViewEnemy.setImage(imageE);

		perkButton.setTranslateY(150);
		perkButton.setStyle(
				" -fx-text-fill: white; -fx-font: 14 garamond; -fx-background-color: #f27121; -fx-border-radius: 5em;");

		hbox.getChildren().addAll(flagView, perkButton, flagViewEnemy);

		return hbox;
	}
}
