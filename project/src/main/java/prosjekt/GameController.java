package prosjekt;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class GameController {
	
	
	private Game game;	
	private GridPane aiBoardGridPane;
	private Pane[][] playerBoardPanes;
	private GridPane playerBoardGridPane;
	private Text text;

	@FXML
	private GridPane boardsParent;

	@FXML
	private void initialize() {
		System.out.println("Running initialize in GameController");
		
		int[][] playerBoats = {
				{3, 7, 3, 8},
				{2, 2, 4, 2},
				{4, 4, 4, 7},
				{7, 3, 7, 7},
		};		
		
		game = new Game(10, 10);
		game.generateAiBoard();
		game.generatePlayerBoard(playerBoats);
		
		playerBoardGridPane = generatePlayerBoardGridPane(game);
		aiBoardGridPane = generateAiBoardGridPane(game);
		
		boardsParent.setVgap(30.0);
		
		// create a background fill
        BackgroundFill background_fill = new BackgroundFill(Color.web("0x252525"), 
                                      CornerRadii.EMPTY, Insets.EMPTY);

        // create Background
        Background background = new Background(background_fill);
		boardsParent.setBackground(background);
		boardsParent.addRow(0, playerBoardGridPane);
		
		// create text
		text = new Text();
		text.setStyle("-fx-font-size: 20px");
		text.setFill(Color.WHITE);
		text.setTextAlignment(TextAlignment.CENTER);
		setWinningText();
		
		boardsParent.addRow(1, text);
		boardsParent.addRow(2, aiBoardGridPane);
	}
	
	private void setCellColor(Tile tile, Pane cell, boolean showBoats) {
        if (tile.isBombHit()) {
        	cell.setStyle("-fx-background-color: black, red; -fx-background-insets: 0, 1 1 1 1;");
        } else if (tile.isBomb()) {
        	cell.setStyle("-fx-background-color: black, black; -fx-background-insets: 0, 1 1 1 1;");
        } else if (tile.isBoat() && showBoats) {
        	cell.setStyle("-fx-background-color: black, gray; -fx-background-insets: 0, 1 1 1 1;");
        } else {                	
        	cell.setStyle("-fx-background-color: black, blue; -fx-background-insets: 0, 1 1 1 1;");
        }
	}
	
	private void playAiMove() {
		int[] playerBombCoordinates = game.aiAddBomb();
    	int aiX = playerBombCoordinates[0];
    	int aiY = playerBombCoordinates[1];
    	Pane playerCell = playerBoardPanes[aiX][aiY];
    	setCellColor(game.getPlayerBoard().getTile(aiX, aiY), playerCell, false);
	}
	
	private GridPane generatePlayerBoardGridPane(Game game) {
		GridPane gridPane = new GridPane();
		playerBoardPanes = new Pane[game.getWidth()][game.getHeight()];
        for (int x = 0; x < game.getWidth(); x++) {
            for (int y = 0; y < game.getHeight(); y++) {
                Pane cell = new Pane();
                playerBoardPanes[x][y] = cell;
                cell.setPrefSize(20, 20);
                setCellColor(game.getPlayerBoard().getTile(x, y), cell, true);
                gridPane.add(cell, x, y);
            }
        }
        return gridPane;
	}
	
	
	private GridPane generateAiBoardGridPane(Game game) {
		GridPane gridPane = new GridPane();
        for (int x = 0; x < game.getWidth(); x++) {
            for (int y = 0; y < game.getHeight(); y++) {
                Pane cell = new Pane();
                cell.setPrefSize(20, 20);

                final int x_final = x;
                final int y_final = y;

                setCellColor(game.getAiBoard().getTile(x_final, y_final), cell, false);

                cell.setOnMouseClicked(e -> {
                	System.out.println("Clicked on " + x_final + ", " + y_final + " in AI board");
                	Tile tile = game.getAiBoard().getTile(x_final, y_final);
                	if (tile.isSea()|| tile.isBoat()) {                		
                		game.playerAddBomb(x_final, y_final);
                		setCellColor(tile, cell, false);
                		playAiMove();
                		setWinningText();
                		
                	}
                });

                gridPane.add(cell, x, y);
            }
        }
        return gridPane;
	}
	
	private void setWinningText() {
		if (game.winner() == 0) {
			text.setText("💣Playing Battleship 💣");
		} else if (game.winner() == 1) {
            text.setFill(Color.GREEN);
			text.setText("💣💣💣You won!💣💣💣");
		} else {
            text.setFill(Color.RED);
			text.setText("😥😥😥😥You lost😥😥😥😥");
		}
	}
}

