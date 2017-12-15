package factory;

import gameCore.GameState;
import java.awt.Point;
import java.util.Arrays;
import java.util.Random;
import model.IObject;
import model.Mushroom;

public class MushroomFactory extends IObjFactory {

  private Random rnd = new Random();
  private GameState game;
  private int mushroomsCount;

  @Override
  public IObject[] utilize(IObject obj) {
    return null;
  }

  @Override
  public IObject[] tick() {
    if (rnd.nextInt(25) <= mushroomsCount) {
      return configure(new Point[]{game.getRndFreePoint()});
    } else {
      return null;
    }
  }

  @Override
  public IObject[] create(GameState game, Point[] ps) {
    this.game = game;
    return Arrays.stream(ps).map((Point p) -> new Mushroom(this, p)).toArray(Mushroom[]::new);
  }

  @Override
  protected IObject[] baseConf(GameState game, Integer[] args) {
    this.game = game;
    mushroomsCount = args[0];
    Mushroom[] tmp = new Mushroom[mushroomsCount];
    for (int i = 0; i < mushroomsCount; i++) {
      tmp[i] = new Mushroom(this, new Point[]{game.getRndFreePoint()});
    }
    return tmp;
  }

  private Mushroom[] configure(Point[] points) {
    return new Mushroom[]{new Mushroom(this, points)};
  }

  @Override
  public IObject[] getProducts() {
    return null;
  }
}
