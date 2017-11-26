package clone;

import java.awt.Point;
import java.util.ArrayList;

import model.Snake;



public class SnakeCloner {
	public static ArrayList<Snake> clone(ArrayList<Snake> snakes) {
		Snake cloned = null;
		for (int i = 0; i < snakes.size(); i++) {
			if (snakes.get(i).getBody().size() >= 6) {
				cloned = snakes.get(i);
				snakes.remove(i);
				break;
			}
		}
		if (cloned == null)
			return snakes;
		ArrayList<Point> snakePoints1 = new ArrayList<Point>();
		ArrayList<Point> snakePoints2 = new ArrayList<Point>();
		for (int i = 2; i >= 0; i--) {
			snakePoints1.add(cloned.getBody().get(i));
		}
		for (int i = 3; i < cloned.getBody().size(); i++) {
			snakePoints2.add(cloned.getBody().get(i));
		}
		Snake snake1 = new Snake(snakePoints1.toArray(new Point[snakePoints1.size()]), cloned.getDir().getOpposit());
		Snake snake2 = new Snake(snakePoints2.toArray(new Point[snakePoints2.size()]), cloned.getDir().getDir());
		if (snakes.size() < 3)
			snakes.add(snake1);
		snakes.add(snake2);
		return snakes;
	}
}
