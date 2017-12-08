package model;

import factory.IObjFactory;
import java.awt.Point;


public abstract class IObject {

  protected IObjFactory fact;

  public abstract Point[] getLocs();

  public abstract char getIcon();

  public abstract void tick();

  public abstract boolean interact(Snake snake, Point p);

  public IObjFactory getFact() {
    return fact;
  }
}
