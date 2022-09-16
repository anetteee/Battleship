package prosjekt;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileSupportTest {
	private FileSupport fileSupport;
	
	@Test
	public void writeTest() throws IOException {
		FileSupport fileSupport = new FileSupport();
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
		game.generateAiBoard();
		
		fileSupport.write(game);
		Game gameTest = fileSupport.read(game.getName());
		Assertions.assertTrue(gameTest.toString().equals(gameTest.toString()));
	}

}
