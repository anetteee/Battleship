package prosjekt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {
	
	private Board board;
	
	@BeforeEach
	public void setUp() {
		board = new Board(10, 10);
	}
	
	@Test
	public void testIsInBoard() {
		Assertions.assertEquals(false, board.isInBoard(11,1));
		Assertions.assertEquals(true, board.isInBoard(1,1));
	}
	
	//tester om båten ligger på linje
	@Test
	public void addBoatTestLinje() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			board.addBoat(1,2,3,4);
		});
	}
	
	//Tester at det ikke går å legge båt der det allerede er en båt
	@Test
	public void addBoatTestOnBoat() {
		board.addBoat(0, 1, 0, 2);
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			board.addBoat(0, 1, 0, 2);
		});
	}
	
	@Test
	public void addBoatTest() {
		Tile tile = board.getTile(0,1);
		board.addBoat(0, 1, 0, 2);
		Assertions.assertEquals(true, tile.isBoat());
	}
	
	@Test
	public void addBombTest() {
		Tile tile = board.getTile(1, 1);
		board.addBomb(1, 1);
		Assertions.assertEquals(true, tile.isBomb());
	}
	/*
	
	
	
	public static void main(String[] args) {
		Board board = new Board(10, 10);
		
		System.out.println(board);
		
		board.addBoat(1,1, 5,1);
		
		System.out.println(board);
		
		board.addBoat(6,3, 6,8);
		
		System.out.println(board);
		
		board.addBomb(1,1);
		
		System.out.println(board);
		
		board.addBomb(4,4);
		
		System.out.println(board);
		
		System.out.println(board.getBoardString(false));
		
		// TODO: gjør om til JUnit teter
	}
*/
}
