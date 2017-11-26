package snakeTests;

import java.awt.Point;

import org.eclipse.swt.SWT;
import org.junit.*;

import direction.Dir;
import factory.FoodFactory;
import factory.PillowFactory;
import factory.TeleportFactory;
import gameCore.GameState;
import gameCore.StateParser;
import gui.Controller;
import model.IObject;
import model.Snake;

public class CoreTests extends Assert {
    @Test
    public void test1D1Dying() throws Exception {
        GameState game = StateParser.makeGame("tests\\T1.txt");
        if (game.makeTick()) {
            throw new Exception();
        } ;
    }
  
    @Test
    public void test1D2Moving() throws Exception {
        GameState game = StateParser.makeGame("tests\\T1.txt");
        Snake snake = game.getSnake();
        game.turnSnake(snake, Dir.Left);
        boolean tick = game.makeTick();
        Point head = game.getHead(game.getSnake());
        if (!tick && head.x == 2 && head.y == 0) {
            throw new Exception();
        } ;
    }


    @Test
    public void test2SelfKilling() throws Exception {
        GameState game = StateParser.makeGame("tests\\T2.txt");
        if (game.makeTick()) {
            throw new Exception();
        } ;
    }

    @Test
    public void test3Tesseract() throws Exception {
        GameState game = StateParser.makeGame("tests\\T3.txt");
        boolean tick = game.makeTick();
        Point head = game.getHead(game.getSnake());
        if (!(tick && head.x == 0 && head.y == 2)) {
            throw new Exception();
        } ;
    }

    @Test
    public void test4Growing() throws Exception {
        GameState game = StateParser.makeGame("tests\\T4.txt");
        IObject[] f = new FoodFactory().create(game, new Point[] {new Point(0, 1)});
        game.setObjs(f);
        boolean tick = game.makeTick();
        Point head = game.getHead(game.getSnake());
        if (!tick && head.x == 0 && head.y == 0) {
            throw new Exception();
        } ;
    }

    @Test
    public void test5D1Teleport() throws Exception {
        GameState game = StateParser.makeGame("tests\\T5.txt");
        IObject[] t = (new TeleportFactory()).create(game,
                new Point[] {new Point(0, 1), new Point(1, 0)});
        game.setObjs(t);
        boolean tick = game.makeTick();
        Point head = game.getHead(game.getSnake());
        if (!tick && head.x == 1 && head.y == 1) {
            throw new Exception();
        } ;
    }

    @Test
    public void test5D2TeleportTesseract() throws Exception {
        GameState game = StateParser.makeGame("tests\\T5.txt");
        IObject[] t = (new TeleportFactory()).create(game,
                new Point[] {new Point(0, 1), new Point(1, 0)});
        game.setObjs(t);
        Snake snake = game.getSnake();
        game.turnSnake(snake, Dir.Right);
        boolean tick = game.makeTick();
        Point head = game.getHead(game.getSnake());
        if (!tick && head.x == 1 && head.y == 1) {
            throw new Exception();
        } ;
    }
    
    @Test
    public void test7Pillow() throws Exception {
        GameState game = StateParser.makeGame("tests\\T7.txt");
        IObject[] t = (new PillowFactory()).create(game, new Point[] {new Point(0, 1)});
        game.setObjs(t);
        game.makeTick();
        boolean tick = game.makeTick();
        Point head = game.getHead(game.getSnake());
        if (!tick && head.x == 0 && head.y == 0) {
            throw new Exception();
        } ;
    }

    @Test
    public void test8SoftWall() throws Exception {
        GameState game = StateParser.makeGame("tests\\T8.txt");
        game.makeTick();
        boolean tick = game.makeTick();
        Point head = game.getHead(game.getSnake());
        if (!tick && head.x == 0 && head.y == 0) {
            throw new Exception();
        } ;
    }
    
    @Test
    public void testController() throws Exception{
    	GameState game = StateParser.makeGame("tests\\T1.txt");
    	Controller.snakeController(game, SWT.ARROW_RIGHT);
    	if (game.getSnake().getDir().getDir() != Dir.Right)
    		throw new Exception();
    	  		
    }
    
    @Test 
    public void testController2() throws Exception{
    	GameState game = StateParser.makeGame("tests\\T1.txt");
    	Controller.snakeController(game, SWT.ARROW_LEFT);
    	if (game.getSnake().getDir().getDir() != Dir.Left)
    		throw new Exception();
    	  		
    }
}








