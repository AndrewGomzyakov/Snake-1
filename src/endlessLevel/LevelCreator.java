package endlessLevel;

import direction.Dir;
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
  private int gameWidth;
  private int gameHeight;
  private int[][] fig = {{0, 0, 1, 0, 1, 1, 2, 1},
      {2, 0, 2, 1, 2, 2, 2, 3, 2, 4, 0, 2, 1, 2, 3, 2, 4, 2},
      {0, 0, 1, 0, 1, 1, 1, 2},
      {0, 0, 0, 1, 1, 0, 1, 1}};
  private ArrayList<int[]> a = new ArrayList<int[]>();

  public LevelCreator(GameState gameState) {
    this.gameState = gameState;
    magic = 7;
    r = gameState.getMap().length / 2;
    l = r;
    u = gameState.getMap()[0].length / 2;
    d = u;
    gameWidth = gameState.getMap().length;
    gameHeight = gameState.getMap()[0].length;
  }

  public void updateLevel() {
    switch (this.gameState.getSnake().getDir().getDir()) {
      case Up:
        if (u > 0) {
          u--;
          d++;
        } else {
          updateMap(Movement.Vertical, (this.gameState.getHead(this.gameState.getSnake()).y - magic
              + gameHeight)
              % gameHeight);
        }
        break;
      case Down:
        if (d > 0) {
          d--;
          u++;
        } else {
          updateMap(Movement.Vertical, (this.gameState.getHead(this.gameState.getSnake()).y + magic)
              % gameHeight);
        }
        break;
      case Left:
        if (l > 0) {
          l--;
          r++;
        } else {
          updateMap(Movement.Horizontal,
              (this.gameState.getHead(this.gameState.getSnake()).x - magic
                  + gameWidth)
                  % gameWidth);
        }
        break;
      case Right:
        if (r > 0) {
          r--;
          l++;
        } else {
          updateMap(Movement.Horizontal,
              (this.gameState.getHead(this.gameState.getSnake()).x + magic)
                  % gameWidth);
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
    Dir snakeDir = gameState.getSnake().getDir().getDir();
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
          int cordx = (x + fig[num][j * 2 + 1]) % gameWidth;
          int cordy = (y + fig[num][j * 2]) % gameHeight;
          if (map[cordx][cordy] == '.') {
            if (swapValues) {
              if (cordx == coordinate) {
                this.gameState.getMaze()[cordx][cordy] = '#';
              } else if (snakeDir == Dir.Down) {
                this.gameState.getMaze()[cordx][cordy] = '!';
              } else {
                this.gameState.getMaze()[cordx][cordy] = '#';
              }

            } else {
              if (cordy == coordinate) {
                this.gameState.getMaze()[cordx][cordy] = '#';
              } else if (snakeDir == Dir.Right) {
                this.gameState.getMaze()[cordx][cordy] = '!';
              } else {
                this.gameState.getMaze()[cordx][cordy] = '#';
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
      createNewWalls(gameWidth, coordinate, false);
    } else {
      createNewWalls(gameHeight, coordinate, true);
    }
  }

  private enum Movement {
    Horizontal,
    Vertical
  }
}
