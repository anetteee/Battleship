package prosjekt;

public class Board {
	
	private int height;
	private int width;
	private Tile[][] board;
	
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		
		this.board = new Tile[height][width];
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				board[y][x] = new Tile(x, y);
			}	
		}  
	}
	
	public Board(int width, int height, String boardString) {
		this(width, height);
		int x = 0;
		int y = 0;
				
		for (int i = 0; i < boardString.length(); i++) {
			char c = boardString.charAt(i);
			if (c == ' ') {
				continue;
			}
			//System.out.println(x + "," + y);
			if (c == '_') {
				Tile tile = getTile(x,y);
				tile.setSea();
				x++;
			}
			else if (c == '@') {
				Tile tile = getTile(x,y);
				tile.setBoat();	
				x++;
			}
			else if (c == '*') {
				Tile tile = getTile(x,y);
				tile.setBoat();
				tile.setBomb();	
				x++;
			}
			else if (c == '#') {
				Tile tile = getTile(x,y);
				tile.setSea();
				tile.setBomb();	
				x++;
			}
			else if (c == '\n') {	
				y++;
				x = 0;
				continue;
			}
		}
	}
	
	
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public Tile getTile(int x, int y) {
		if (!isInBoard(x,y)) {
			throw new IllegalArgumentException("Out of bounds");
		}
		return this.board[y][x];
	}
	
	public boolean isInBoard(int x, int y) {
		return x >= 0 && y >= 0 && x < getWidth() && y < getHeight();
	}
	
	public void addBoat(int x0, int y0, int x1, int y1) {
		if (!(x0 == x1 || y0 == y1)) {
			throw new IllegalArgumentException("Boat must be vertical or horizontal");
		}
		if (y0 == y1) {
			// horisontal
			int xMin = Math.min(x0, x1);
			int xMax = Math.max(x0, x1);
			for (int x = xMin; x <= xMax; x++) {
				Tile tile = this.getTile(x, y0);
				if (!tile.isSea()) {
					throw new IllegalArgumentException("Illegal boat position ("+ x + ", " + y0 + ") for boat: " + x0 + y0 + x1 + y1);
				}
				tile.setBoat();	
			}
		} else {
			// vertikal
			int yMin = Math.min(y0, y1);
			int yMax = Math.max(y0, y1);
			for (int y = yMin; y <= yMax; y++) {
				Tile tile = this.getTile(x0, y);
				if (!tile.isSea()) {
					throw new IllegalArgumentException("Illegal boat position("+ x0 + "," + y + ") for boat: " + x0 + y0 + x1 + y1);
				}
				tile.setBoat();
			}		
		}
	}
	
	public void addBomb(int x, int y) {
		Tile tile = this.getTile(x,y);
		tile.setBomb();
	}
	
	public String getBoardString(boolean showBoats) {
		String boardString = "";
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				Tile tile = getTile(x, y);
				if (!showBoats && tile.isBoat()) {
					boardString += '_';
				} else {
					boardString += tile.toString();		
				}
				boardString += " ";
			}
			boardString += "\n";
		}
		return boardString;
	}
	
	@Override
	public String toString() {
		return this.getBoardString(true);
	}
	
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

}
