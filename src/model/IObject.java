package model;

import factory.IObjFactory;
import java.awt.Point;


public abstract class IObject {

  protected IObjFactory factory;

  public abstract Point[] getLocations();

  public abstract char getIcon();

  public abstract void tick();

  public abstract boolean interact(Snake snake, Point p);

  public IObjFactory getFactory() {
    return factory;
  }
}
