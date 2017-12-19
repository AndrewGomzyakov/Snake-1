package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Effect {
  ReduceField,  //наверное, должен обрабатываться где то в отрисовке
  InvertButtons, 
  SmallField; //обрабатывается в селекторе

  private static final List<Effect> VALUES =
      Collections.unmodifiableList(Arrays.asList(Effect.values()));
  private static final int SIZE = VALUES.size();
  private static final Random RANDOM = new Random();

  public static Effect randomEffect() {
    return VALUES.get(RANDOM.nextInt(SIZE));
  }
}
