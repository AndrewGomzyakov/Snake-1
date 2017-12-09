package model;

import factory.PillowFactory;
import java.awt.Point;

public class Pillow extends IObject {

  private Point location;
  private int timer = 0;
  private int maxTimer = 5;
  private Snake snake;

  public Pillow(PillowFactory fact, Point[] p) {
    this.factory = fact;
    location = p[0];
  }

  public Pillow(PillowFactory fact, Point p) {
    this.factory = fact;
    location = p;
  }

  @Override
  public Point[] getLocations() {
    return new Point[]{location};
  }

  @Override
  public char getIcon() {
    return '%';
  }

  @Override
  public void tick() {
    if (snake != null) {
      timer += 1;
    }
    if (timer == maxTimer) {
      snake.start();
      snake = null;
    }
  }

  @Override
  public boolean interact(Snake snake, Point p) {
    this.snake = snake;
    snake.stop();
    return false;
  }

  public Snake getSnakeOn() {
    return snake;
  }

}
