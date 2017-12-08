package save;

import direction.Dir;
import gameCore.GameState;
import gameCore.Tuple;
import java.awt.Point;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Saver {

  public static void addTelep(Map<Character, Tuple<Point, Point>> map, Point p, char c) {
    if (map.containsKey(c)) {
      map.put(c, new Tuple<Point, Point>(map.get(c).x, p));
    } else {
      map.put(c, new Tuple<Point, Point>(p, null));
    }
  }

  public static void save(String path, GameState game) {
    StringBuffer rezultStr = new StringBuffer();
    List<Point> food = new ArrayList<Point>();
    List<Point> pil = new ArrayList<Point>();
    List<Point> hedg = new ArrayList<Point>();
    Map<Character, Tuple<Point, Point>> tels = new HashMap<Character, Tuple<Point, Point>>();

    char[][] a = game.getMap();
    rezultStr.append(Integer.toString(a[1].length - 4) + ' ' + Integer.toString(a.length) + '\n');
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < a[i].length; j++) {
        switch (a[i][j]) {
          case ('A'):
            Point p1 = new Point(i, j);
            hedg.add(p1);
            p1 = new Point(-1, 0);
            hedg.add(p1);
            rezultStr.append('.');
            break;
          case ('W'):
            Point p2 = new Point(i, j);
            hedg.add(p2);
            p2 = new Point(0, -1);
            hedg.add(p2);
            rezultStr.append('.');
            break;
          case ('S'):
            Point p3 = new Point(i, j);
            hedg.add(p3);
            p3 = new Point(1, 0);
            hedg.add(p3);
            rezultStr.append('.');
            break;
          case ('D'):
            Point p4 = new Point(i, j);
            hedg.add(p4);
            p4 = new Point(0, 1);
            hedg.add(p4);
            rezultStr.append('.');
            break;
          case ('%'):
            Point p = new Point(i, j);
            pil.add(p);
            rezultStr.append('.');
            break;
          case ('P'):
            Point p6 = new Point(i, j);
            rezultStr.append('.');
            addTelep(tels, p6, 'P');
            break;
          case ('T'):
            Point p7 = new Point(i, j);
            rezultStr.append('.');
            addTelep(tels, p7, 'T');
            break;
          case ('t'):
            Point p8 = new Point(i, j);
            rezultStr.append('.');
            addTelep(tels, p8, 't');
            break;
          case ('p'):
            Point p9 = new Point(i, j);
            rezultStr.append('.');
            addTelep(tels, p9, 'p');
            break;
          case ('*'):
            Point p5 = new Point(i, j);
            food.add(p5);
            rezultStr.append('.');
            break;
          case ('.'):
            rezultStr.append('.');
            break;
          case ('#'):
            rezultStr.append('#');
            break;
          case ('+'):
            rezultStr.append('+');
            break;
          case ('@'):
            rezultStr.append('.');
            break;
          case ('?'):
            rezultStr.append('.');
            break;
        }
      }
      rezultStr.append('\n');
    }
    List<Point> snake = game.getSnake().getBody();
    for (int i = 0; i < snake.size(); i++) {
      int x = (int) snake.get(i).getX();
      int y = (int) snake.get(i).getY();
      rezultStr.append(Integer.toString(x) + ' ' + Integer.toString(y) + ' ');

    }
    rezultStr.append('\n');
    Dir dir = game.getSnakeDir(game.getSnake()).getDir();
    int num = 0;
    if (dir == Dir.Right) {
      num = 6;
    }
    if (dir == Dir.Left) {
      num = 4;
    }
    if (dir == Dir.Up) {
      num = 8;
    }
    if (dir == Dir.Down) {
      num = 2;
    }
    rezultStr.append(Integer.toString(num) + '\n');
    if (pil.size() != 0) {
      rezultStr.append("Pillow -1 ");
      for (Point p : pil) {
        rezultStr.append(Integer.toString(p.y) + ' ' + Integer.toString(p.x) + ' ');
      }
      rezultStr.append('\n');
    }
    if (food.size() != 0) {
      rezultStr.append("Food -1 ");
      for (Point p : food) {
        rezultStr.append(Integer.toString(p.y) + ' ' + Integer.toString(p.x) + ' ');
      }
      rezultStr.append('\n');
    }

    if (tels.size() != 0) {
      rezultStr.append("Teleport -1 ");
      for (Tuple<Point, Point> p : tels.values()) {
        rezultStr.append(Integer.toString(p.x.y) + ' ' + Integer.toString(p.x.x) + ' ');
        rezultStr.append(Integer.toString(p.y.y) + ' ' + Integer.toString(p.y.x) + ' ');
      }
      rezultStr.append('\n');
    }

    if (hedg.size() != 0) {
      rezultStr.append("Hedg -1 ");
      for (int i = 0; i < hedg.size(); i += 2) {
        rezultStr
            .append(Integer.toString(hedg.get(i).y) + ' ' + Integer.toString(hedg.get(i).x) + ' ');
        rezultStr.append(
            Integer.toString(hedg.get(i + 1).y) + ' ' + Integer.toString(hedg.get(i + 1).x) + ' ');
      }
    }
    try {
      @SuppressWarnings("resource")
      FileWriter writer = new FileWriter(path, false);
      writer.write(rezultStr.toString());
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}