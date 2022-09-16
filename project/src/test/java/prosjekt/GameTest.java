package prosjekt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {
	private Game game;
	
	@BeforeEach
	public void SetUp() {
		game = new Game(10,10);
	}
	
	@Test
	public void generatePlayerBoardTest() {
		Game game = new Game(10,10); 
		int[][] playerBoats = new int[4][4];
		playerBoats[0][0] = 3;
		playerBoats[0][1] = 4;
		playerBoats[0][2] = 3;
		playerBoats[0][3] = 8;
		
		playerBoats[1][0] = 0;
		playerBoats[1][1] = 2;
		playerBoats[1][2] = 4;
		playerBoats[1][3] = 2;
		
		playerBoats[2][0] = 4;
		playerBoats[2][1] = 3;
		playerBoats[2][2] = 4;
		playerBoats[2][3] = 7;
		
		playerBoats[3][0] = 7;
		playerBoats[3][1] = 3;
		playerBoats[3][2] = 7;
		playerBoats[3][3] = 7;
		
		game.generatePlayerBoard(playerBoats);
		Board board = game.getPlayerBoard();
		Tile tile1 = board.getTile(3, 4);
		Tile tile2 = board.getTile(0, 2);
		Tile tile3 = board.getTile(4, 3);
		Tile tile4 = board.getTile(7, 3);
		
		Assertions.assertTrue(tile1.isBoat());
		Assertions.assertTrue(tile2.isBoat());
		Assertions.assertTrue(tile3.isBoat());
		Assertions.assertTrue(tile4.isBoat());	
	}
	@Test
	public void generateAiBoardTest() {
		game.generateAiBoard();
		Board aiBoard = game.getAiBoard();
		int boats = 0;
		for (int x = 0; x < 10;x++) {
			for (int y = 0; y < 10; y++) {
				Tile tile = aiBoard.getTile(x, y);
				if (tile.isBoat()) {
					boats++;
				}
			}
		}
		// hvis alle båtene ble lagt til skal det være 14 Tiles av typen boat
		Assertions.assertEquals(14, boats);
		
	}
	
	@Test
	public void playerAddBombTest() {
		game.generateAiBoard();
		Board aiBoard = game.getAiBoard();
		game.playerAddBomb(1, 1);
		Tile tile = aiBoard.getTile(1, 1);
		Assertions.assertEquals(true, tile.isBomb());
		game.playerAddBomb(0, 0);
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			game.playerAddBomb(0, 0);
		});
	}
	
	@Test
	public void aiAddBombTest() {
		int[][] playerBoats = new int[4][4];
		playerBoats[0][0] = 3;
		playerBoats[0][1] = 4;
		playerBoats[0][2] = 3;
		playerBoats[0][3] = 8;
		
		playerBoats[1][0] = 0;
		playerBoats[1][1] = 2;
		playerBoats[1][2] = 4;
		playerBoats[1][3] = 2;
		
		playerBoats[2][0] = 4;
		playerBoats[2][1] = 3;
		playerBoats[2][2] = 4;
		playerBoats[2][3] = 7;
		
		playerBoats[3][0] = 7;
		playerBoats[3][1] = 3;
		playerBoats[3][2] = 7;
		playerBoats[3][3] = 7;

		game.generatePlayerBoard(playerBoats);
		Board playerBoard = game.getPlayerBoard();
		game.aiAddBomb();
		int bombs = 0;
		for (int x = 0; x < 10;x++) {
			for (int y = 0; y < 10; y++) {
				Tile tile = playerBoard.getTile(x, y);
				if (tile.isBomb() || tile.isBombHit()) {
					bombs++;
				}
			}
		}
		// boats skal være 1 etter å ha kalt på aiAddBomb() en gang
		Assertions.assertEquals(1, bombs);
	}
	
	
	@Test
	public void winnerTest() {
		int[][] playerBoats = new int[4][4];
		playerBoats[0][0] = 3;
		playerBoats[0][1] = 4;
		playerBoats[0][2] = 3;
		playerBoats[0][3] = 8;
		
		playerBoats[1][0] = 0;
		playerBoats[1][1] = 2;
		playerBoats[1][2] = 4;
		playerBoats[1][3] = 2;
		
		playerBoats[2][0] = 4;
		playerBoats[2][1] = 3;
		playerBoats[2][2] = 4;
		playerBoats[2][3] = 7;
		
		playerBoats[3][0] = 7;
		playerBoats[3][1] = 3;
		playerBoats[3][2] = 7;
		playerBoats[3][3] = 7;

		game.generatePlayerBoard(playerBoats);
		game.generateAiBoard();
		for (int x = 0; x <10;x++) {
			for (int y = 0; y < 10; y++) {
				game.playerAddBomb(x, y);
			}
		}
		Assertions.assertEquals(1, game.winner());
	}
	
	/*
	@Test
	public void LoserTest() {
		int[][] playerBoats = new int[4][4];
		playerBoats[0][0] = 3;
		playerBoats[0][1] = 4;
		playerBoats[0][2] = 3;
		playerBoats[0][3] = 8;
		
		playerBoats[1][0] = 0;
		playerBoats[1][1] = 2;
		playerBoats[1][2] = 4;
		playerBoats[1][3] = 2;
		
		playerBoats[2][0] = 4;
		playerBoats[2][1] = 3;
		playerBoats[2][2] = 4;
		playerBoats[2][3] = 7;
		
		playerBoats[3][0] = 7;
		playerBoats[3][1] = 3;
		playerBoats[3][2] = 7;
		playerBoats[3][3] = 7;

		game.generatePlayerBoard(playerBoats);
		
		int x = 0;
		while(x <= 10) {
			game.aiAddBomb();
			x++;
		}
		Assertions.assertEquals(2, game.winner());
	}
*/
}
