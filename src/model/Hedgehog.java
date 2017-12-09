package model;

import direction.Dir;
import direction.Direction;
import factory.HedgehogFactory;
import java.awt.Point;
import java.util.Random;
import lombok.Getter;

public final class Hedgehog extends IObject {

  @Getter
  private Direction dir;
  private char icon;
  private Point location;

  public Hedgehog(HedgehogFactory factory, Point[] p) {
    this.factory = factory;
    location = p[0];
    commonInit();
  }

  public Hedgehog(HedgehogFactory fact, Point p) {
    this.factory = fact;
    location = p;
    commonInit();
  }

  public Hedgehog(HedgehogFactory fact, Point p, Point d) {
    this.factory = fact;
    location = p;
    dir = new Direction(d);
    icon = makeIcon(dir.getDir());
  }

  public Hedgehog(HedgehogFactory fact) {
    this.factory = fact;
    commonInit();
  }


  private void commonInit() {
    Dir[] dirs = {Dir.Right, Dir.Left, Dir.Up, Dir.Down};
    dir = new Direction(dirs[new Random().nextInt(4)]);
    icon = makeIcon(dir.getDir());
  }

  private char makeIcon(Dir dir) {
    if (dir == Dir.Right) {
      return 'D';
    }
    if (dir == Dir.Up) {
      return 'W';
    }
    if (dir == Dir.Down) {
      return 'S';
    }
    if (dir == Dir.Left) {
      return 'A';
    }
    return 'F';
  }

  @Override
  public Point[] getLocations() {
    return new Point[]{location};
  }

  @Override
  public char getIcon() {
    return icon;
  }

  @Override
  public void tick() {
  }

  @Override
  public boolean interact(Snake snake, Point p) {
    if (snake.getDir().isOpposit(dir)) {
      snake.grow(2);
      commonInit();
      return false;
    } else {
      return true;
    }
  }
}
