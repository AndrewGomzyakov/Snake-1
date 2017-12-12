package endlessLevel;

import gameCore.GameState;
import java.util.ArrayList;
import java.util.Random;

public class LevelCreator {

  private int magic;

  private GameState gameState;
  private int r;
  private int l;
  private int u;
  private int d;
  private int[][] fig = {{0, 0, 1, 0, 1, 1, 2, 1},
      {2, 0, 2, 1, 2, 2, 2, 3, 2, 4, 0, 2, 1, 2, 3, 2, 4, 2},
      {0, 0, 1, 0, 1, 1, 1, 2},
      {0, 0, 0, 1, 1, 0, 1, 1}};
  public LevelCreator(GameState gameState) {
    this.gameState = gameState;
    magic = 9 / 2 + 3;
    r = gameState.getMap().length - gameState.getHead(gameState.getSnake()).x;
    l = gameState.getMap().length - r;
    u = gameState.getMap()[0].length - gameState.getHead(gameState.getSnake()).y;
    d = gameState.getMap()[0].length - u;
  }

  private ArrayList<int[]> a = new ArrayList<int[]>();

  public void updateLevel() {
    switch (this.gameState.getSnake().getDir().getDir()) {
      case Up:
        if (u > 0) {
          u--;
          d++;
        } else {
          updateMap(Movement.Vertical, (this.gameState.getHead(this.gameState.getSnake()).y - magic
              + this.gameState.getMap().length)
              % this.gameState.getMap().length);
        }
        break;
      case Down:
        if (d > 0) {
          d--;
          u++;
        } else {
          updateMap(Movement.Vertical, (this.gameState.getHead(this.gameState.getSnake()).y + magic)
              % this.gameState.getMap().length);
        }
        break;
      case Left:
        if (l > 0) {
          l--;
          r++;
        } else {
          updateMap(Movement.Horizontal,
              (this.gameState.getHead(this.gameState.getSnake()).x - magic
              + this.gameState.getMap()[0].length)
              % this.gameState.getMap()[0].length);
        }
        break;
      case Right:
        if (r > 0) {
          r--;
          l++;
        } else {
          updateMap(Movement.Horizontal,
              (this.gameState.getHead(this.gameState.getSnake()).x + magic
                  + this.gameState.getMap()[0].length)
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
      if (this.gameState.getMaze()[x][y] == '#' || this.gameState.getMaze()[x][y] == '+') {
        this.gameState.getMaze()[x][y] = '.';
      }
      if (this.gameState.getMaze()[x][y] == '!') {
        this.gameState.getMaze()[x][y] = '#';
      }
    }
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
      Random rnd = new Random();
      int ch = rnd.nextInt(100);
      if (ch <= 2) {
        int num = rnd.nextInt(fig.length);
        for (int j = 0; j < fig[num].length / 2; j++) {
          int cordx = (x + fig[num][j * 2 + 1]) % this.gameState.getMaze().length;
          int cordy = (y + fig[num][j * 2]) % this.gameState.getMaze()[0].length;
          if (map[cordx][cordy] == '.') {
            if (swapValues) {
              if (cordx == coordinate) {
                this.gameState.getMaze()[cordx][cordy] = '#';
              } else {
                this.gameState.getMaze()[cordx][cordy] = '!';
              }
            } else {
              if (cordy == coordinate) {
                this.gameState.getMaze()[cordx][cordy] = '#';
              } else {
                this.gameState.getMaze()[cordx][cordy] = '!';
              }
            }
          }
        }
      }
    }
    //this.gameState.getMaze()[x][y] = generateWall();
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

  private void updateMap(Movement movement, int coordinate) {
    if (movement == Movement.Horizontal) {
      createNewWalls(this.gameState.getMaze().length, coordinate, false);
    } else {
      createNewWalls(this.gameState.getMaze()[0].length, coordinate, true);
    }
  }

  private enum Movement {
    Horizontal,
    Vertical
  }
}
