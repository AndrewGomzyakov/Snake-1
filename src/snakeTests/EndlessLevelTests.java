package snakeTests;

import direction.Dir;
import direction.Direction;
import endlessLevel.LevelCreator;
import gameCore.GameState;
import gameCore.Tuple;
import java.awt.Point;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EndlessLevelTests {

  private int width = 12;
  private int height = 12;
  private LevelCreator creator;
  private GameState game;

  @Before
  public void setUp() {
    game = new GameState(new char[width][height], new Point[]{new Point(0, 0)},
        new Direction(Dir.Left),
        new ArrayList<Tuple<String, Integer[]>>());
    creator = new LevelCreator(game);
  }

  @Test
  public void correctCreation() throws IllegalAccessException, NoSuchFieldException {
    Field r = LevelCreator.class.getDeclaredField("r");
    r.setAccessible(true);
    Field l = LevelCreator.class.getDeclaredField("l");
    l.setAccessible(true);
    Field u = LevelCreator.class.getDeclaredField("u");
    u.setAccessible(true);
    Field d = LevelCreator.class.getDeclaredField("d");
    d.setAccessible(true);
    Assert.assertEquals(width / 2, r.get(creator));
    Assert.assertEquals(width / 2, l.get(creator));
    Assert.assertEquals(height / 2, u.get(creator));
    Assert.assertEquals(height / 2, d.get(creator));
  }

  @Test
  public void wallsGenerator()
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Method method = LevelCreator.class.getDeclaredMethod("generateWall");
    method.setAccessible(true);
    char ch = (char) method.invoke(creator);
    Assert.assertTrue(ch == '.' || ch == '+' || ch == '#');
  }

  @Test
  public void testUpdating() {
    char[] firstState = game.getMap()[game.getMap().length - 1];
    for (int i = 0; i < width; i++) {
      creator.updateLevel();
    }
    Assert.assertNotEquals(game.getMap()[game.getMap().length - 1], firstState);
  }

}
