package model;

import factory.MushroomFactory;
import java.awt.Point;

public class Mushroom extends IObject {

  private final int effectDuration = 15;
  private Point location;

  public Mushroom(MushroomFactory fact, Point[] p) {
    this.factory = fact;
    location = p[0];
  }

  public Mushroom(MushroomFactory fact, Point p) {
    this.factory = fact;
    location = p;
  }

  @Override
  public Point[] getLocations() {
    return new Point[]{location};
  }

  @Override
  public char getIcon() {
    return '&';
  }

  @Override
  public void tick() {

  }

  @Override
  public boolean interact(Snake snake, Point p) {
    snake.setEffect(Effect.randomEffect());
    snake.setEffectTimer(effectDuration);
    return false;
  }
}
