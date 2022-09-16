package prosjekt;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
	private Board playerBoard;
	private Board aiBoard;
	private int width = 10;
	private int height = 10;
	private String name = "BattleShip";
	
	public Game(int height, int width) {
		this.playerBoard = null;
		this.aiBoard = null;
		this.width = width;
		this.height = height;
	}
	
	public void setPlayerBoard(Board playerBoard) {
		this.playerBoard = playerBoard;
	}
	
	public void setAiBoard(Board aiBoard) {
		this.aiBoard = aiBoard;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Board getAiBoard() {
		return aiBoard;
	}
	
	public Board getPlayerBoard() {
		return playerBoard;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void generatePlayerBoard(int[][] listOfBoats) {
		Board board = new Board(height, width);
		for (int i = 0; i < listOfBoats.length; i++) {
			int x0 = listOfBoats[i][0];
			int y0 = listOfBoats[i][1];
			int x1 = listOfBoats[i][2];
			int y1 = listOfBoats[i][3];
			
			board.addBoat(x0, y0, x1, y1);
		}	
		playerBoard = board;
	}
	
	public void generateAiBoard() {
		//lager en bank med forskjellige listOfBoats
			int[][] playerBoats1 = new int[4][4];
			playerBoats1[0][0] = 3;
			playerBoats1[0][1] = 7;
			playerBoats1[0][2] = 3;
			playerBoats1[0][3] = 8;
			
			playerBoats1[1][0] = 0;
			playerBoats1[1][1] = 2;
			playerBoats1[1][2] = 2;
			playerBoats1[1][3] = 2;
			
			playerBoats1[2][0] = 4;
			playerBoats1[2][1] = 4;
			playerBoats1[2][2] = 4;
			playerBoats1[2][3] = 7;
			
			playerBoats1[3][0] = 7;
			playerBoats1[3][1] = 3;
			playerBoats1[3][2] = 7;
			playerBoats1[3][3] = 7;
			
			int[][] playerBoats2 = new int[4][4];
			playerBoats2[0][0] = 2;
			playerBoats2[0][1] = 7;
			playerBoats2[0][2] = 2;
			playerBoats2[0][3] = 8;
			
			playerBoats2[1][0] = 2;
			playerBoats2[1][1] = 9;
			playerBoats2[1][2] = 4;
			playerBoats2[1][3] = 9;
			
			playerBoats2[2][0] = 7;
			playerBoats2[2][1] = 3;
			playerBoats2[2][2] = 7;
			playerBoats2[2][3] = 6;
			
			playerBoats2[3][0] = 5;
			playerBoats2[3][1] = 3;
			playerBoats2[3][2] = 5;
			playerBoats2[3][3] = 7;
			
			int[][] playerBoats3 = new int[4][4];
			playerBoats3[0][0] = 5;
			playerBoats3[0][1] = 7;
			playerBoats3[0][2] = 5;
			playerBoats3[0][3] = 8;
			
			playerBoats3[1][0] = 2;
			playerBoats3[1][1] = 8;
			playerBoats3[1][2] = 4;
			playerBoats3[1][3] = 8;
			
			playerBoats3[2][0] = 4;
			playerBoats3[2][1] = 1;
			playerBoats3[2][2] = 4;
			playerBoats3[2][3] = 4;
			
			playerBoats3[3][0] = 1;
			playerBoats3[3][1] = 2;
			playerBoats3[3][2] = 1;
			playerBoats3[3][3] = 6;
			
			int[][] playerBoats4 = new int[4][4];
			playerBoats4[0][0] = 2;
			playerBoats4[0][1] = 7;
			playerBoats4[0][2] = 2;
			playerBoats4[0][3] = 8;
			
			playerBoats4[1][0] = 2;
			playerBoats4[1][1] = 9;
			playerBoats4[1][2] = 4;
			playerBoats4[1][3] = 9;
		
			playerBoats4[2][0] = 1;
			playerBoats4[2][1] = 4;
			playerBoats4[2][2] = 1;
			playerBoats4[2][3] = 7;
			
			playerBoats4[3][0] = 7;
			playerBoats4[3][1] = 1;
			playerBoats4[3][2] = 7;
			playerBoats4[3][3] = 5;
			
			int [][][] options = new int[4][4][4];
			options[0] = playerBoats1;
			options[1] = playerBoats2;
			options[2] = playerBoats3;
			options[3] = playerBoats4;
			
			int randomIndex = ThreadLocalRandom.current().nextInt(0, 3 + 1);
			//randomIndex = 3;
			
			Board board = new Board(height, width);
			for (int i = 0; i < options[randomIndex].length; i++) {
				int x0 = options[randomIndex][i][0];
				int y0 = options[randomIndex][i][1];
				int x1 = options[randomIndex][i][2];
				int y1 = options[randomIndex][i][3];
		
				board.addBoat(x0, y0, x1, y1);
			}	
			aiBoard = board;
	}	
	
	public void playerAddBomb(int x, int y) {
		Tile tile = aiBoard.getTile(x, y);
		if (tile.isBomb() || tile.isBombHit()) {
			throw new IllegalArgumentException("field already bombeb");
		} else {
			aiBoard.addBomb(x, y);
		}
	}
	
	public int[] aiAddBomb() {
		int width = playerBoard.getWidth();
		int height = playerBoard.getHeight();
		int randomX = ThreadLocalRandom.current().nextInt(0, width);
		int randomY = ThreadLocalRandom.current().nextInt(0, height);
		Tile tile = playerBoard.getTile(randomX, randomY);
		while (tile.isBomb()) {
			randomX = ThreadLocalRandom.current().nextInt(0, width);
			randomY = ThreadLocalRandom.current().nextInt(0, height);
			tile = playerBoard.getTile(randomX, randomY);
		}
		playerBoard.addBomb(randomX, randomY);
		int[] returnCoordinates = {randomX, randomY};
		return returnCoordinates;
	}
	
	public Integer winner() {
		boolean hasAiWon = true;
		boolean hasPlayerWon = true;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Tile tile = aiBoard.getTile(x,y); 
				if (tile.isBoat()) {
					hasPlayerWon = false;
				}
			}
		}
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Tile tile = playerBoard.getTile(x,y); 
				if (tile.isBoat()) {
					hasAiWon = false;
				}
			}
		}
		if (!hasAiWon && !hasPlayerWon) {
			return 0;
		}
		if (hasAiWon) {
			return 2;
		}
		else {
			return 1;
		}
	}
	
	public static void main(String[] args) {
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
	

		Scanner scanner = new Scanner (System.in);
		while (game.winner() == 0) {
			System.out.println("motspillers brett");
			System.out.println(game.getAiBoard().getBoardString(false));
			System.out.println("Ditt brett");
			System.out.println(game.getPlayerBoard().getBoardString(true));
			System.out.println("Enter coordinates");
			System.out.print("x: ");
			int x = scanner.nextInt();
			System.out.print("y: ");
			int y = scanner.nextInt();
			game.playerAddBomb(x,y);
			game.aiAddBomb();	
		}
		if (game.winner() == 1) {
			System.out.println("You Win!");
		} else if (game.winner() == 2) {
			System.out.println("You loose!");
		}
		scanner.close();
	}
	
}
