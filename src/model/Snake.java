package model;

import direction.Dir;
import direction.Direction;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Snake {

  @Getter
  private LinkedList<Point> body = new LinkedList<>();
  @Getter
  private int buffer;
  @Getter
  private Direction dir;
  @Getter
  private boolean isMoving;
  @Setter
  private Point next;

  public Snake(Point[] b, Dir dir) {
    isMoving = true;
    buffer = 0;
    //�������� �� ������������
    body.addAll(Arrays.asList(b));
    this.dir = new Direction(dir);
  }

  public Snake(ArrayList<Point> snakePoints, Dir direction) {
    isMoving = true;
    buffer = 0;
    body.addAll(snakePoints);
    this.dir = new Direction(direction);
  }

  public boolean turn(Direction newDirection) {
    if (dir.isOpposit(newDirection)) {
      return false;
    }
    dir = newDirection;
    next = null;
    next = getNext();
    return true;
  }

  public boolean makeStep() {
    if (!isMoving) {
      return true;
    }

    if (body.contains(getNext())) {
      stop();
      return false;
    }
    body.add(getNext());
    if (buffer > 0) {
      buffer -= 1;
    } else {
      body.removeFirst();
    }
    next = null;
    return true;
  }

  public void stop() {
    isMoving = false;
  }

  public void start() {
    isMoving = true;
  }

  public void grow(int val) {
    buffer += val;
  }

  public void setNext(Point newNext) {
	    next = newNext;
	  }
  public List<Point> getBody() {
	    return body;
	  }
  
  public Direction getDir() {
	    return dir;
	  }

  public boolean isMoving() {
	    return isMoving;
	  }
  
  public Point getNext() {
    if (next == null) {
      Point d = dir.getPointDir();
      next = new Point(getHead().x + d.x, getHead().y + d.y);
    }
    return next;
  }

  public Point getHead() {
    return body.getLast();
  }
}

	