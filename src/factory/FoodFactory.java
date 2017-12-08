package factory;

import gameCore.GameState;
import java.awt.Point;
import java.util.Arrays;
import java.util.Random;
import model.Food;
import model.IObject;

public final class FoodFactory extends IObjFactory {

  private Random rnd = new Random();
  private GameState game;
  private int foodCount;

  @Override
  public Food[] create(GameState game, Point[] ps) {
    this.game = game;
    return Arrays.stream(ps).map((Point p) -> new Food(this, p)).toArray(Food[]::new);
  }

  @Override
  public Food[] baseConf(GameState game, Integer[] args) {
    this.game = game;
    foodCount = args[0];
    Food[] tmp = new Food[foodCount];
    for (int i = 0; i < foodCount; i++) {
      tmp[i] = new Food(this, new Point[]{game.getRndFreePoint()});
    }
    return tmp;
  }

  private Food[] configure(Point[] points) {
    return new Food[]{new Food(this, points)};
  }

  @Override
  public Food[] utilize(IObject obj) {
    return null;
  }

  @Override
  public Food[] tick() {
    if (rnd.nextInt(25) <= foodCount) {
      return configure(new Point[]{game.getRndFreePoint()});
    } else {
      return null;
    }
  }

  @Override
  public Food[] getProducts() {
    return null;
  }

}
