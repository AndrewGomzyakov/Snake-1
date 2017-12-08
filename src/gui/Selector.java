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
      snakes.add(new Tuple<Snake, int[]>(snakeClone.get(num), a));
    } else {
      snakes.add(new Tuple<Snake, int[]>(null, a));
    }
  }


  public Tuple<Snake, Direction> selctSnakeByKey(int keyCode, GameState game) {
    ArrayList<Snake> snakeClone = game.getSnakeClone();
    Snake snake = null;
    Direction dir = null;
    ArrayList<Tuple<Snake, int[]>> snakes = new ArrayList<Tuple<Snake, int[]>>();
    ArrayList<Tuple<Direction, int[]>> dirs = new ArrayList<Tuple<Direction, int[]>>();
    int first[] = {100, 97, 115, 119};
    int second[] = {102, 103, 104, 116};
    int third[] = {105, 106, 107, 108};
    int fourth[] = {SWT.ARROW_DOWN, SWT.ARROW_LEFT, SWT.ARROW_RIGHT, SWT.ARROW_UP};

    this.addTuple(0, first, snakeClone, snakes);
    this.addTuple(1, second, snakeClone, snakes);
    this.addTuple(2, third, snakeClone, snakes);

    snakes.add(new Tuple<Snake, int[]>(game.getSnake(), fourth));

    int up[] = {119, 116, 105, SWT.ARROW_UP};
    int left[] = {97, 102, 106, SWT.ARROW_LEFT};
    int down[] = {115, 103, 107, SWT.ARROW_DOWN};
    int right[] = {100, 104, 108, SWT.ARROW_RIGHT};

    dirs.add(new Tuple<Direction, int[]>(new Direction(Dir.Down), down));
    dirs.add(new Tuple<Direction, int[]>(new Direction(Dir.Up), up));
    dirs.add(new Tuple<Direction, int[]>(new Direction(Dir.Left), left));
    dirs.add(new Tuple<Direction, int[]>(new Direction(Dir.Right), right));
    for (int i = 0; i < snakes.size(); i++) {
      for (int j = 0; j < snakes.get(i).y.length; j++) {
        if (keyCode == snakes.get(i).y[j]) {
          snake = snakes.get(i).x;
        }
      }
    }
    for (int i = 0; i < dirs.size(); i++) {
      for (int j = 0; j < dirs.get(i).y.length; j++) {
        if (keyCode == dirs.get(i).y[j]) {
          dir = dirs.get(i).x;
        }
      }
    }
    return new Tuple<Snake, Direction>(snake, dir);
  }
}
