package gui;

import direction.Dir;
import direction.Direction;
import gameCore.GameState;
import gameCore.Tuple;
import java.util.ArrayList;
import model.Snake;
import org.eclipse.swt.SWT;

public class Selector {

  private void addTuple(int num, int a[], ArrayList<Snake> snakeClone,
      ArrayList<Tuple<Snake, int[]>> snakes) {
    if (snakeClone.size() > num) {
      snakes.add(new Tuple<>(snakeClone.get(num), a));
    } else {
      snakes.add(new Tuple<>(null, a));
    }
  }


  public Tuple<Snake, Direction> selctSnakeByKey(int keyCode, GameState game) {
    ArrayList<Snake> snakeClone = game.getSnakeClone();
    Snake snake = null;
    Direction dir = null;
    ArrayList<Tuple<Snake, int[]>> snakes = new ArrayList<>();
    ArrayList<Tuple<Direction, int[]>> dirs = new ArrayList<>();
    int first[] = {100, 97, 115, 119};
    int second[] = {102, 103, 104, 116};
    int third[] = {105, 106, 107, 108};
    int fourth[] = {SWT.ARROW_DOWN, SWT.ARROW_LEFT, SWT.ARROW_RIGHT, SWT.ARROW_UP};

    this.addTuple(0, first, snakeClone, snakes);
    this.addTuple(1, second, snakeClone, snakes);
    this.addTuple(2, third, snakeClone, snakes);

    snakes.add(new Tuple<>(game.getSnake(), fourth));

    int up[] = {119, 116, 105, SWT.ARROW_UP};
    int left[] = {97, 102, 106, SWT.ARROW_LEFT};
    int down[] = {115, 103, 107, SWT.ARROW_DOWN};
    int right[] = {100, 104, 108, SWT.ARROW_RIGHT};

    if (game.isReverseKeys()) {
      int temp[] = up;
      up = down.clone();
      down = temp.clone();
      temp = right;
      right = left.clone();
      left = temp.clone();
    }

    dirs.add(new Tuple<>(new Direction(Dir.Down), down));
    dirs.add(new Tuple<>(new Direction(Dir.Up), up));
    dirs.add(new Tuple<>(new Direction(Dir.Left), left));
    dirs.add(new Tuple<>(new Direction(Dir.Right), right));
    for (Tuple<Snake, int[]> snake1 : snakes) {
      for (int j = 0; j < snake1.y.length; j++) {
        if (keyCode == snake1.y[j]) {
          snake = snake1.x;
        }
      }
    }
    for (Tuple<Direction, int[]> dir1 : dirs) {
      for (int j = 0; j < dir1.y.length; j++) {
        if (keyCode == dir1.y[j]) {
          dir = dir1.x;
        }
      }
    }
    return new Tuple<>(snake, dir);
  }
}
