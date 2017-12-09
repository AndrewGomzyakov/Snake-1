package snakeTests;

import direction.Dir;
import factory.HedgehogFactory;
import java.awt.Point;
import java.util.ArrayList;
import model.Hedgehog;
import model.Snake;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HedgehogTests {

  private HedgehogFactory factory;
  private Hedgehog hedgehog;
  private Snake snake;
  private Point left = new Point(-1, 0);
  private ArrayList<Point> snakeLocations;

  @Before
  public void setUp() {
    factory = new HedgehogFactory();
    snakeLocations = new ArrayList<>();
    snakeLocations.add(new Point(0, 0));
  }

  @Test
  public void createHedgehog_With_Factory() {
    hedgehog = new Hedgehog(factory);
    switch (hedgehog.getDirection().getDir()) {

      case Up:
        Assert.assertEquals('W', hedgehog.getIcon());
        break;
      case Down:
        Assert.assertEquals('S', hedgehog.getIcon());
        break;
      case Left:
        Assert.assertEquals('A', hedgehog.getIcon());
        break;
      case Right:
        Assert.assertEquals('D', hedgehog.getIcon());
        break;
    }

  }

  @Test
  public void createHedgehog_With_TwoPoints() {
    hedgehog = new Hedgehog(factory, new Point(0, 1), left);
    Assert.assertEquals(new Point(0, 1), hedgehog.getLocations()[0]);
    Assert.assertEquals(Dir.Left, hedgehog.getDirection().getDir());
  }

  @Test
  public void interactionWithSnake_OnOppositeDirection_ShouldMakeItGrow() {
    hedgehog = new Hedgehog(factory, new Point(0, 1), left);
    snake = new Snake(snakeLocations, Dir.Right);
    Assert.assertEquals(0, snake.getBuffer());
    boolean snakeWillDie = hedgehog.interact(snake, new Point(0, 0));
    Assert.assertFalse(snakeWillDie);
    Assert.assertEquals(2, snake.getBuffer());

  }

  @Test
  public void interactionWithSnake_OnOtherDirection_ShouldKillIt() {
    hedgehog = new Hedgehog(factory, new Point(0, 1), left);
    for (Dir dir : Dir.values()) {
      if (dir == Dir.Right) {
        continue;
      }
      snake = new Snake(snakeLocations, dir);
      boolean snakeWillDie = hedgehog.interact(snake, new Point(0, 0));
      Assert.assertTrue(snakeWillDie);
      Assert.assertEquals(0, snake.getBuffer());
    }
  }

}
