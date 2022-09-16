package prosjekt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;



public class FileSupport implements IGameFileReader {
    
	public final static String GAME_EXTENSION = "txt";
	
	//bestemmer mappen filen er lagret på
	private static Path getGameUserFolderPath() {
		return Path.of(System.getProperty("user.home"), "tdt4100", "prosjekt");
	}
	
	//Lager ny mappe om det ikke finnes en 
	private boolean ensureGameUserFolder() {
		try {
			Files.createDirectories(getGameUserFolderPath());
			return true;
		} catch (IOException ioe) {
			return false;
		}
	}
	
	//Finner filnavnet med sti fra getGameUserFolderPath
	private Path getGamePath(String name) {
		return getGameUserFolderPath().resolve(name + "." + GAME_EXTENSION);
	}
	
	@Override
	public void write(Game game, OutputStream os) {
		try (var writer = new PrintWriter(os)) {
			writer.println(game.getHeight());
			writer.println(game.getWidth());
			writer.println(game.getPlayerBoard().toString());
			writer.println(game.getAiBoard().toString());
		}
	}
	
	@Override
	public void write(Game game) throws IOException {
		var gamePath = getGamePath(game.getName());
        ensureGameUserFolder();
        try (var output = new FileOutputStream(gamePath.toFile())) {
        	write(game, output);
        }
	}
	
	@Override
	public Game read(InputStream is) {
		Scanner scanner = new Scanner(is);
		
		int height = scanner.nextInt();
		int width = scanner.nextInt();
		String playerBoardString = "";
		scanner.nextLine();
		for (int x = 0; x < width; x++) {
			playerBoardString += scanner.nextLine();
			playerBoardString += '\n';
		}
		scanner.nextLine();
		
		String aiBoardString = "";
		for (int x = 0; x < width; x++) {
			aiBoardString += scanner.nextLine();
			aiBoardString += '\n';
		}
		scanner.close();
		
		Board aiBoard = new Board(width, height, aiBoardString);
		Board playerBoard = new Board(width, height,playerBoardString);
		
		Game game = new Game(width, height);
		game.setAiBoard(aiBoard);
		game.setPlayerBoard(playerBoard);
		
		return game;
	}


	@Override
	public Game read(String name) throws IOException {
		var gamePath = getGamePath(name);
		var input = new FileInputStream(gamePath.toFile());
		return read(input);
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
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
		
	/*
		Path filePath = fileSupport.getGamePath("test");
		
		fileSupport.ensureGameUserFolder();
		
		try (FileOutputStream fout = new FileOutputStream(filePath.toFile())) {
			fileSupport.write(game, fout);
		}
		*/
		fileSupport.write(game);
		
		Game gameTest = fileSupport.read("BattleShip");
		System.out.println(gameTest.getPlayerBoard().toString());
	}
}
