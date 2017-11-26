package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;

import direction.Dir;
import direction.Direction;
import gameCore.GameState;
import gameCore.Tuple;
import model.Snake;

public class Controller {
	public static void snakeController(GameState gameState, int e) {
		Selector a = new Selector();
		Tuple<Snake, Direction> b = a.selctSnakeByKey(e, gameState);
		if (b.x != null)
			gameState.turnSnake(b.x, b.y.getDir());
	}
}