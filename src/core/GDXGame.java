package core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GDXGame {
	public int width;
	public int height;
	boolean isDisplayingNeighborCounts = false;
	int cubeSize = 35;
	int maxFPS = 30;
	long elapsedTime = 0;
	long lastFrameTime = 0;
	String title;
	
	JFrame window;
	GamePanel panel;
	Tile[][] board;
	Tile[][] blankBoard;

	Tile fakeTile = new Tile(1000, 1000, 1000, 1000, 2, this);

	public GDXGame(int w, int h, String title, GameInput input, int fps) {
		int tileScale = title.equals("SUPER CONWAY BROS") ? 100 : 32;
		cubeSize = title.equals("SUPER CONWAY BROS") ? 10 : 35;
		maxFPS = title.equals("SUPER CONWAY BROS") ? 1000 : 30;
		
		int realWidth = Math.max(Math.min(w, tileScale), 0);
		int realHeight = Math.max(Math.min(h, tileScale), 0);
		this.width = realWidth;
		this.height = realHeight;
		this.title = title;
		board = new Tile[realWidth][realHeight];
		blankBoard = new Tile[realWidth][realHeight];
		createTiles();
		window = new JFrame(title);
		int frames = Math.min(fps, maxFPS);
		panel = new GamePanel(this, input, frames);
		window.add(panel);
		window.addKeyListener(panel);
		window.addMouseListener(panel);
		window.addMouseMotionListener(panel);
		window.setPreferredSize(new Dimension(realWidth * (cubeSize) + 17, realHeight * (cubeSize) + 40));
		window.setResizable(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
		panel.startTimer();
	}

	private void createTiles() {
		int count = 0;
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				Tile t = new Tile(i, j, i * cubeSize, j * cubeSize, cubeSize, this);
				t.setColor(Color.gray);
				board[i][j] = t;
				blankBoard[i][j] = t;
				count++;
			}
		}
		System.out.println(count + " Squares created, with size " + cubeSize);
	}

	public Tile[][] getBoard() {
		return board;
	}
	
	public Tile[][] getBlankBoard(){
		return blankBoard;
	}
	
	public void setBoard(Tile[][] newBoard) {
		this.board = newBoard;
	}

	public int getBoardWidth() {
		return this.width;
	}

	public int getBoardHeight() {
		return this.height;
	}

	public int getCubeSize() {
		return this.cubeSize;
	}

	public JFrame getWindow() {
		return this.window;
	}
	
	public Tile getTileClicked(MouseEvent e) {
		int x = (int) Math.ceil((e.getX() - 10) / cubeSize);
		int y = (int) Math.ceil((e.getY() - 32) / cubeSize);
		return board[x][y];
	}

	public long getElapsedTime() {
		elapsedTime = System.currentTimeMillis() - lastFrameTime;
		lastFrameTime = System.currentTimeMillis();
		return elapsedTime;
	}
	
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String t) {
		this.getWindow().setTitle(t);
	}
	
	public void refreshDisplay() {
		panel.repaint();
	}

	public void toggleNeighborCountDisplay() {
		isDisplayingNeighborCounts = !isDisplayingNeighborCounts;
		
	}
}
