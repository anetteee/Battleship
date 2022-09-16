package prosjekt;

public class Tile {
	private char type = '_';
	private int x;
	private int y;
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setBoat() {
		type = '@';
	}
	
	public void setSea() {
		type = '_';
	}
	
	public void setBomb() {
		if (type == '*' || type == '#') {
			return;
		}
		if (type == '@') {
			type = '*';
		} else {
			type = '#';
		}
	}
	
	public boolean isBoat() {
		return type == '@';
	}
	
	public boolean isSea() {
		return type == '_';
	}
	
	public boolean isBomb() {
		return type =='#' || type =='*';
	}
	
	public boolean hasHitten() {
		return isBoat() || isBomb();
	}
	
	public boolean isBombHit() {
		return type == '*';
	}
	
	@Override
	public String toString() {
		return Character.toString(type);
	}
	
}
