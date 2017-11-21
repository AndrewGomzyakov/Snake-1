package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;

import snakeCore.Dir;
import snakeCore.GameState;

public class Controller {
	public static void sankeController(GameState gameState, KeyEvent e) {
		switch(e.keyCode){
		case(100)://d
			gameState.turnSnake2(Dir.Right);
			break;
		case(115)://s
			gameState.turnSnake2(Dir.Down);
			break;
		case(119)://w
			gameState.turnSnake2(Dir.Up);
			break;
		case(97)://a
			gameState.turnSnake2(Dir.Left);
			break;
		case(116)://t
			gameState.turnSnake3(Dir.Up);
			break;
		case(102)://f
			gameState.turnSnake3(Dir.Left);
			break;
		case(103)://g
			gameState.turnSnake3(Dir.Down);
			break;
		case(104)://h
			gameState.turnSnake3(Dir.Right);
			break;
		case(105)://i
			gameState.turnSnake4(Dir.Up);
			break;
		case(106)://j
			gameState.turnSnake4(Dir.Left);
			break;
		case(107)://k
			gameState.turnSnake4(Dir.Down);
			break;
		case(108)://l
			gameState.turnSnake4(Dir.Right);
			break;
		case(SWT.ARROW_LEFT):
			gameState.turnSnake1(Dir.Left);
			break;
		case(SWT.ARROW_RIGHT):
			gameState.turnSnake1(Dir.Right);
			break;
		case(SWT.ARROW_DOWN):
			gameState.turnSnake1(Dir.Down);
			break;
		case(SWT.ARROW_UP):
			gameState.turnSnake1(Dir.Up);
			break;
		}
	}
}