package examples;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JOptionPane;

import core.GDXGame;
import core.GameInput;
import core.Tile;

public class Conways extends GameInput {
	static GDXGame game;
	Random gen = new Random();
	boolean isPaused = true;
	boolean isMouseLeftDown = false;
	boolean isMouseRightDown = false;
	public static void main(String[] args) {
		Conways test = new Conways();
		game = new GDXGame(120, 60, "SUPER CONWAY BROS", test, 60);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if (isMouseLeftDown) {
			Tile tile = game.getTileClicked(e);
			tile.setColor(Color.RED);
			tile.setDisplayString(tile.countNeighborsWithColor(Color.RED)+"");
		}
		if (isMouseRightDown) {
			Tile tile = game.getTileClicked(e);
			tile.setColor(Color.gray);
			tile.setDisplayString(tile.countNeighborsWithColor(Color.RED)+"");
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getButton() == 1) {
			Tile tile = game.getTileClicked(e);
			tile.setColor(Color.RED);
			tile.setDisplayString(tile.countNeighborsWithColor(Color.RED)+"");
		}
		if (e.getButton() == 3) {
			Tile tile = game.getTileClicked(e);
			tile.setColor(Color.gray);
			tile.setDisplayString(tile.countNeighborsWithColor(Color.RED)+"");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getButton() == 1) {
			isMouseLeftDown = true;
		}
		if (e.getButton() == 3) {
			isMouseRightDown = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == 1) {
			isMouseLeftDown = false;
		}
		if (e.getButton() == 3) {
			isMouseRightDown = false;
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyChar() == 'p' && !isPaused) {
			isPaused = true;
			System.out.println("Paused!");
		} else if (e.getKeyChar() == 'p' && isPaused) {
			isPaused = false;
			System.out.println("Unpaused!");
		} else if (e.getKeyChar() == 'u') {
			updateLivingCounts();
		}else if (e.getKeyChar() == 'd') {
			game.toggleNeighborCountDisplay();
		}else {
			for (Tile[] tA : game.getBoard()) {
				for (Tile t : tA) {
					int x = new Random().nextInt(10);
					if (x < 1) {
						t.setColor(Color.RED);
					}
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (isPaused == false) {

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Tile[][] newBoard;
		if (isPaused) {

		} else {
			Tile[][] board = game.getBoard();
			updateLivingCounts();
			newBoard = game.getBlankBoard();
			for (int x = 0; x < board.length; x++) {
				for (int y = 0; y < board[0].length; y++) {
					int nLiving = board[x][y].getLivingNeighbors();
					if (board[x][y].getColor().equals(Color.RED)) {
						if (nLiving < 2 || nLiving > 3) {
							newBoard[x][y].setColor(Color.gray);
						} else {
							newBoard[x][y].setColor(Color.RED);
						}
					} else {
						if (nLiving == 3) {
							newBoard[x][y].setColor(Color.RED);
						} else {
							newBoard[x][y].setColor(Color.gray);
						}
					}
				}
			}
			game.setBoard(newBoard);
			//updateLivingCounts();
		}
	}
	
	public void updateLivingCounts() {
		for (Tile[] tA : game.getBoard()) {
			for (Tile t : tA) {
				t.setDisplayString(t.countNeighborsWithColor(Color.RED)+"");
			}
		}
	}
}