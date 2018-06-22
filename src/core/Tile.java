package core;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Tile {
	GDXGame game;
	int num;
	int x;
	int y;
	int boardX;
	int boardY;
	int size;
	int livingNeighbors = 0;
	Color color;
	Color edgeColor;
	String displayString = "";
	Random gen = new Random();
	
	public Tile (int bX, int bY, int x, int y, int s, GDXGame g) {
		this.boardX = bX;
		this.boardY = bY;
		this.x = x;
		this.y = y;
		this.size = s;
		this.game = g;
		this.color = new Color(150,150,150);
		this.edgeColor = new Color(255, 255, 255);
	}
	
	public void setColor(Color col) {
		this.color = col;
	}
	
	public void setColor(int r, int g, int b) {
		this.color = new Color(r, g, b);
	}
	
	public void setRandomColor() {
		Random gen = new Random();
		
		this.color = new Color(gen.nextInt(255),gen.nextInt(255),gen.nextInt(255));
	}
	
	public void setEdgeColor(Color col) {
		this.edgeColor = col;
	}
	
	public void setEdgeColor(int r, int g, int b) {
		this.edgeColor = new Color(r, g, b);
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public Color getEdgeColor() {
		return this.edgeColor;
	}
	
	public String getDisplayString() {
		return this.displayString;
	}
	public void setDisplayString(String str) {
		this.displayString = str;
	}
	
	/**
	 * Randomly modifies the color of the tile by merging it with a random neighbor's color
	 */
	public void bleedColor(Tile t) {
		int r1 = this.color.getRed();
		int g1 = this.color.getGreen();
		int b1 = this.color.getBlue();
		int r2 = t.color.getRed();
		int g2 = t.color.getGreen();
		int b2 = t.color.getBlue();
		
		int col = gen.nextInt(3);
		if(col == 0) {
			r2 = convergeNumbers(r1, r2);
		}
		else if(col == 1) {
			g2 = convergeNumbers(g1, g2);
		}
		else if(col == 2) {
			b2 = convergeNumbers(b1, b2);
		}
		t.setColor(r2, g2, b2);
	}
	
	private int convergeNumbers(int a, int b) {
		if(a > b) {
			return b+1;
		}
		else if (a < b){
			return b-1;
		}
		else {
			return b;
		}
	}
	
	public int countLivingNeighbors() {
		Tile[] neighbors = getAllNeighbors();
		int nLiving = 0;
		for (Tile n : neighbors) {
			if (n.getColor().equals(Color.RED)) {
				nLiving++;
			}
		}
		this.livingNeighbors = nLiving;
		return nLiving;
	}
	
	public int getLivingNeighbors() {
		return this.livingNeighbors;
	}
	/**
	 * Gets all neighboring tiles of the tile that is calling the function.
	 * @return Tile[] object
	 */
	public Tile[] getAllNeighbors() {
		Tile[] neighbors = new Tile[8];

		neighbors[0] = getTopNeighbor();
		neighbors[1] = getRightNeighbor();
		neighbors[2] = getBottomNeighbor();
		neighbors[3] = getLeftNeighbor();
		
		neighbors[4] = getTopLeftNeighbor();
		neighbors[5] = getTopRightNeighbor();
		neighbors[6] = getBottomLeftNeighbor();
		neighbors[7] = getBottomRightNeighbor();
		return neighbors;
	}
	
	/**
	 * Gets all adjacent tiles to the tile that is calling the function.
	 * @return Tile[] object
	 */
	public Tile[] getAdjacentNeighbors() {
		Tile[] neighbors = new Tile[4];
		neighbors[0] = getTopNeighbor();
		neighbors[1] = getRightNeighbor();
		neighbors[2] = getBottomNeighbor();
		neighbors[3] = getLeftNeighbor();
		return neighbors;
	}
	
	/**
	 * Gets all diagonal tiles to the tile that is calling the function.
	 * @return Tile[] object
	 */
	public Tile[] getDiagonalNeighbors() {
		Tile[] neighbors = new Tile[4];
		neighbors[0] = getTopLeftNeighbor();
		neighbors[1] = getTopRightNeighbor();
		neighbors[2] = getBottomLeftNeighbor();
		neighbors[3] = getBottomRightNeighbor();
		return neighbors;
	}
	
	/**
	 * Gets the Tile object directly above the tile object that is calling the function.
	 * @return Tile object
	 */
	public Tile getTopNeighbor() {
		int cubeSize = game.getCubeSize();
		int x = this.getBoardX();
		int y = this.getBoardY();
		if (y-1 >= 0) {
			Tile tile = game.getBoard()[x][y-1];
			return tile;
		}
		else return game.fakeTile; 
	}
	/**
	 * Gets the Tile object directly below the tile object that is calling the function.
	 * @return Tile object
	 */
	public Tile getBottomNeighbor() {
		int cubeSize = game.getCubeSize();
		int x = this.getBoardX();
		int y = this.getBoardY();
		if (y+1 < game.getBoardHeight()) {
			Tile tile = game.getBoard()[x][y+1];
			return tile;
		}
		else return game.fakeTile; 
	}
	/**
	 * Gets the Tile object directly to the left of the tile object that is calling the function.
	 * @return Tile object
	 */
	public Tile getLeftNeighbor() {
		int cubeSize = game.getCubeSize();
		int x = this.getBoardX();
		int y = this.getBoardY();
		if (x-1 >= 0) {
			Tile tile = game.getBoard()[x-1][y];
			return tile;
		}
		else return game.fakeTile; 
	}
	/**
	 * Gets the Tile object directly to the right of the tile object that is calling the function.
	 * @return Tile object
	 */
	public Tile getRightNeighbor() {
		int cubeSize = game.getCubeSize();
		int x = this.getBoardX();
		int y = this.getBoardY();
		if (x+1 < game.getBoardWidth()) {
			Tile tile = game.getBoard()[x+1][y];
			return tile;
		}
		else return game.fakeTile; 
	}
	
	/**
	 * Gets the Tile object directly above and to the right of the tile object that is calling the function.
	 * @return Tile object
	 */
	public Tile getTopRightNeighbor() {
		int cubeSize = game.getCubeSize();
		int x = this.getBoardX();
		int y = this.getBoardY();
		if (x+1 < game.getBoardWidth() && y-1 >= 0) {
			Tile tile = game.getBoard()[x+1][y-1];
			return tile;
		}
		else return game.fakeTile; 
	}
	
	/**
	 * Gets the Tile object directly below and to the right of the tile object that is calling the function.
	 * @return Tile object
	 */
	public Tile getBottomRightNeighbor() {
		int cubeSize = game.getCubeSize();
		int x = this.getBoardX();
		int y = this.getBoardY();
		if (x+1 < game.getBoardWidth() && y+1 < game.getBoardHeight()) {
			Tile tile = game.getBoard()[x+1][y+1];
			return tile;
		}
		else return game.fakeTile; 
	}
	
	/**
	 * Gets the Tile object directly above and to the left of the tile object that is calling the function.
	 * @return Tile object
	 */
	public Tile getTopLeftNeighbor() {
		int cubeSize = game.getCubeSize();
		int x = this.getBoardX();
		int y = this.getBoardY();
		if (x-1 >= 0 && y-1 >= 0) {
			Tile tile = game.getBoard()[x-1][y-1];
			return tile;
		}
		else return game.fakeTile; 
	}
	
	/**
	 * Gets the Tile object directly below and to the left of the tile object that is calling the function.
	 * @return Tile object
	 */
	public Tile getBottomLeftNeighbor() {
		int cubeSize = game.getCubeSize();
		int x = this.getBoardX();
		int y = this.getBoardY();
		if (x-1 >= 0 && y+1 < game.getBoardHeight()) {
			Tile tile = game.getBoard()[x-1][y+1];
			return tile;
		}
		else return game.fakeTile; 
	}
	
	private int getY() {
		return y;
	}

	private int getX() {
		return x;
	}
	
	private int getBoardY() {
		return boardY;
	}

	private int getBoardX() {
		return boardX;
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, size, size);
		g.setColor(edgeColor);
		if(!displayString.equals("0") && game.isDisplayingNeighborCounts) {
			g.drawString(displayString, x, y+size);
		}
		g.drawRect(x, y, size, size);
	}
}
