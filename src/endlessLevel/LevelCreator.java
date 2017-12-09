package endlessLevel;

import gameCore.GameState;
import java.util.Random;

public class LevelCreator {

  private GameState gameState;
  private int r;
  private int l;
  private int u;
  private int d;

  public LevelCreator(GameState gameState) {
    this.gameState = gameState;
    r = gameState.getMap().length / 2 - 6;
    l = r;
    u = gameState.getMap()[0].length / 2 - 6;
    d = u;
  }

  public void updateLevel() {
    switch (this.gameState.getSnake().getDir().getDir()) {
      case Up:
        if (u > 0) {
          u--;
          d++;
        } else {
          updateMap(0, (this.gameState.getHead(this.gameState.getSnake()).y + 7)
              % this.gameState.getMap().length);
        }
        break;
      case Down:
        if (d > 0) {
          d--;
          u++;
        } else {
          updateMap(0, (this.gameState.getHead(this.gameState.getSnake()).y - 7
              + this.gameState.getMap().length)
              % this.gameState.getMap().length);
        }
        break;
      case Left:
        if (l > 0) {
          l--;
          r++;
        } else {
          updateMap(1, (this.gameState.getHead(this.gameState.getSnake()).x - 1 - 6
              + this.gameState.getMap()[0].length)
              % this.gameState.getMap()[0].length);
        }
        break;
      case Right:
        if (r > 0) {
          r--;
          l++;
        } else {
          updateMap(1, (this.gameState.getHead(this.gameState.getSnake()).x + 1 + 6)
              % this.gameState.getMap()[0].length);
        }
        break;
    }
  }

  private void deleteOldWalls(int maxLength, int coordinate, boolean swapValues) {
    int x;
    int y;
    for (int i = 0; i < maxLength; i++) {
      if (swapValues) {
        x = coordinate;
        y = i;
      } else {
        x = i;
        y = coordinate;
      }
      this.gameState.getMaze()[x][y] = '.';
    }
  }

  private char generateWall() {
    Random rnd = new Random();
    int ch = rnd.nextInt(100);
    if (ch <= 10) {
      return '#';
    }
    if (ch <= 20) {
      return '+';
    }
    return '.';
  }

  private void createNewWalls(int maxLength, int coordinate, boolean swapValues) {
    deleteOldWalls(maxLength, coordinate, swapValues);
    char[][] map = this.gameState.getMap();
    int x;
    int y;
    for (int i = 0; i < maxLength; i++) {
      if (swapValues) {
        x = coordinate;
        y = i;
      } else {
        x = i;
        y = coordinate;
      }
      if (map[x][y] != '.') {
        continue;
      }
      this.gameState.getMaze()[x][y] = generateWall();

    }
  }

  private void updateMap(int type, int coordinate) {
    if (type == 1) {
      createNewWalls(this.gameState.getMaze().length, coordinate, false);
    } else {
      createNewWalls(this.gameState.getMaze()[0].length, coordinate, true);
    }
  }
}
