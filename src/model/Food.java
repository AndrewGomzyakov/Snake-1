package model;

import factory.FoodFactory;
import java.awt.Point;

public final class Food extends IObject {

  private Point location;

  public Food(FoodFactory fact, Point[] p) {
    this.factory = fact;
    location = p[0];
  }

  public Food(FoodFactory fact, Point p) {
    this.factory = fact;
    location = p;
  }

  @Override
  public Point[] getLocations() {
    return new Point[]{location};
  }

  @Override
  public char getIcon() {
    return '*';
  }

  @Override
  public void tick() {
  }

  @Override
  public boolean interact(Snake snake, Point p) {
    snake.grow(1);
    return false;
  }

}
