package examples;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

import core.GDXGame;
import core.GameInput;
import core.Tile;

public class ColorBlender extends GameInput {
	static GDXGame game;
	Random gen = new Random();

	public static void main(String[] args) {
		ColorBlender test = new ColorBlender();
		game = new GDXGame(16, 16, "SUPER DOKAPON WORLD", test, 30);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getButton() == 1) {
			Tile tile = game.getTileClicked(e);
			tile.setRandomColor();
		} else if (e.getButton() == 3) {
			Tile tile = game.getTileClicked(e);
			Tile[] neighbors = tile.getAdjacentNeighbors();
			for (Tile t : neighbors) {
				t.setColor(t.getColor().getGreen(), t.getColor().getBlue(), t.getColor().getRed());
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

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
		System.out.println(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for (Tile[] tA : game.getBoard()) {
			for (Tile t : tA) {
				int x = gen.nextInt(4);
				switch (x) {
				case 0:
					t.bleedColor(t.getTopNeighbor());
				case 1:
					t.bleedColor(t.getRightNeighbor());
				case 2:
					t.bleedColor(t.getBottomNeighbor());
				case 3:
					t.bleedColor(t.getLeftNeighbor());
				}
			}
		}
	}
}
