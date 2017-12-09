package factory;

import gameCore.GameState;
import java.awt.Point;
import java.util.Arrays;
import model.Hedgehog;
import model.IObject;

public class HedgehogFactory extends IObjFactory {

  private GameState game;

  @Override
  public Hedgehog[] create(GameState game, Point[] ps) {
    return Arrays.stream(ps).map((Point p) -> new Hedgehog(this, p))
        .toArray(Hedgehog[]::new);
  }

  public Hedgehog[] baseConf(GameState game, Integer[] args) {
    this.game = game;
    Hedgehog[] tmp = new Hedgehog[args[0]];
    for (int i = 0; i < args[0]; i++) {
      tmp[i] = new Hedgehog(this, new Point[]{game.getRndFreePoint()});
    }
    return tmp;
  }

  @Override
  public Hedgehog[] utilize(IObject obj) {
    Hedgehog hedg;
    if (obj instanceof Hedgehog) {
      hedg = Hedgehog.class.cast(obj);
    } else {
      return null;
    }
    Point loc = game.getRndFreePoint();
    while (game.getCell(game.getBoundedCord(game.getSnake(), // TODO what
        new Point(loc.x + hedg.getDirection().getPointDir().x,
            loc.y + hedg.getDirection().getPointDir().y))) != '.') {
      loc = game.getRndFreePoint();
    }
    return new Hedgehog[]{new Hedgehog(this, loc)};
  }

  @Override
  public Hedgehog[] tick() {
    return null;
  }

  @Override
  public Hedgehog[] getProducts() {
    return null;
  }

}
