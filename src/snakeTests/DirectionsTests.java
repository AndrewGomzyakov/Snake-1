package snakeTests;

import direction.Dir;
import direction.Direction;
import java.awt.Point;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DirectionsTests {

  private Point down = new Point(0, 1);
  private Point up = new Point(0, -1);
  private Point right = new Point(1, 0);
  private Point left = new Point(-1, 0);

  private Direction leftDirection;
  private Direction rightDirection;
  private Direction upDirection;
  private Direction downDirection;

  @Before
  public void SetUp() {
    leftDirection = new Direction(left);
    rightDirection = new Direction(right);
    upDirection = new Direction(up);
    downDirection = new Direction(down);
  }

  @Test
  public void createDirection_WithPoint() {
    Assert.assertEquals(Dir.Left, leftDirection.getDir());
    Assert.assertEquals(Dir.Right, rightDirection.getDir());
    Assert.assertEquals(Dir.Up, upDirection.getDir());
    Assert.assertEquals(Dir.Down, downDirection.getDir());
  }

  @Test
  public void gettingOppositeDirection() {
    Assert.assertEquals(Dir.Right, leftDirection.getOpposit());
    Assert.assertEquals(Dir.Left, rightDirection.getOpposit());
    Assert.assertEquals(Dir.Down, upDirection.getOpposit());
    Assert.assertEquals(Dir.Up, downDirection.getOpposit());
  }
}
