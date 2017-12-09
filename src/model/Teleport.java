package model;

import factory.IObjFactory;
import factory.TeleportFactory;
import java.awt.Point;

public class Teleport extends IObject {

  private char icon;
  private Point enterA;
  private Point enterB;

  public Teleport(TeleportFactory fact, char icon, Point[] ps) {
    this.factory = fact;
    enterA = ps[0];
    enterB = ps[1];
    this.icon = icon;
  }

  public Teleport(TeleportFactory fact, char icon, Point a, Point b) {
    this.factory = fact;
    enterA = a;
    enterB = b;
    this.icon = icon;
  }

  @Override
  public Point[] getLocations() {
    return new Point[]{enterA, enterB};
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
    Point target;
    if (p.x == enterA.x && p.y == enterA.y) {
      target = enterB;
    } else // (p.x==enterB.x && p.y==enterB.y)
    {
      target = enterA;
    }
    Point dir = snake.getDir().getPointDir();
    snake.setNext(new Point(target.x + dir.x, target.y + dir.y));
    return false;
  }

  @Override
  public IObjFactory getFactory() {
    return factory;
  }

}
