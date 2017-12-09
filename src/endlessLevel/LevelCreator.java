package endlessLevel;

import direction.Dir;
import gameCore.GameState;

public class LevelCreator {
	private GameState gameSate;
	private int r;
	private int l;
	private int u;
	private int d;
	public LevelCreator(GameState gameState) {
		this.gameSate = gameState;
		r = gameState.getMap().length / 2 - 6;
		l = r;
		u = gameState.getMap()[0].length / 2 - 6;
		d = u;
	}
	
	public void updateLevel() {
		if (this.gameSate.getSnake().getDir().getDir() == Dir.Right) {
			if (r > 0) {
				r--;
				l++;
			}
			else {
				updateMap(1, (this.gameSate.getHead(this.gameSate.getSnake()).x + 1 + 6) % this.gameSate.getMap()[0].length);
			}
		}
		if (this.gameSate.getSnake().getDir().getDir() == Dir.Left) {
			if (l > 0) {
				l--;
				r++;
			}
			else {
				updateMap(1, (this.gameSate.getHead(this.gameSate.getSnake()).x - 1 - 6 + this.gameSate.getMap()[0].length) % this.gameSate.getMap()[0].length);
			}
		}
		if (this.gameSate.getSnake().getDir().getDir() == Dir.Up) {
			if (u > 0) {
				u--;
				d++;
			}
			else {
				updateMap(0, (this.gameSate.getHead(this.gameSate.getSnake()).y + 7) % this.gameSate.getMap().length);
			}
		}
		if (this.gameSate.getSnake().getDir().getDir() == Dir.Down) {
			if (d > 0) {
				d--;
				u++;
			}
			else {
				updateMap(0, (this.gameSate.getHead(this.gameSate.getSnake()).y - 7 + this.gameSate.getMap().length) % this.gameSate.getMap().length);
			}
		}
		
	}
	private void updateMap(int type, int cord) {
		if (type == 1) {
			
			for (int i = 0; i < this.gameSate.getMaze().length; i++) {
				this.gameSate.getMaze()[i][cord] = '#';
			}
		}
		else {
			for (int i = 0; i < this.gameSate.getMaze()[0].length; i++) {
				this.gameSate.getMaze()[cord][i] = '#';
			}
		}
	}
}
