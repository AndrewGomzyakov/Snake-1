package snakeTests;

import java.awt.Point;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.junit.*;

import direction.Dir;
import factory.FoodFactory;
import factory.HedgFactory;
import factory.PillowFactory;
import factory.TeleportFactory;
import gameCore.GameState;
import gameCore.StateParser;
import gui.Controller;
import model.IObject;

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
        game.turnSnake1(Dir.Left);
        if (!game.makeTick() && game.getMap()[2][0] == '@') {
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
        if (!(game.makeTick() && game.getHead(game.getSnake()).x == 0 && game.getHead(game.getSnake()).y == 2)) {
            throw new Exception();
        } ;
    }

    @Test
    public void test4Growing() throws Exception {
        GameState game = StateParser.makeGame("tests\\T4.txt");
        IObject[] f = new FoodFactory().create(game, new Point[] {new Point(0, 1)});
        game.setObjs(f);
        if (!(game.makeTick() && game.getMap()[0][0] == '@')) {
            throw new Exception();
        } ;
    }

    @Test
    public void test5D1Teleport() throws Exception {
        GameState game = StateParser.makeGame("tests\\T5.txt");
        IObject[] t = (new TeleportFactory()).create(game,
                new Point[] {new Point(0, 1), new Point(1, 0)});
        game.setObjs(t);
        if (!(game.makeTick() && game.getHead(game.getSnake()).x == 1 && game.getHead(game.getSnake()).y == 1)) {
            throw new Exception();
        } ;
    }

    @Test
    public void test5D2TeleportTesseract() throws Exception {
        GameState game = StateParser.makeGame("tests\\T5.txt");
        IObject[] t = (new TeleportFactory()).create(game,
                new Point[] {new Point(0, 1), new Point(1, 0)});
        game.setObjs(t);
        game.turnSnake1(Dir.Right);
        if (!(game.makeTick() && game.getHead(game.getSnake()).x == 1 && game.getHead(game.getSnake()).y == 1)) {
            throw new Exception();
        } ;
    }

    @Test
    public void test7Pillow() throws Exception {
        GameState game = StateParser.makeGame("tests\\T7.txt");
        IObject[] t = (new PillowFactory()).create(game, new Point[] {new Point(0, 1)});
        game.setObjs(t);
        game.makeTick();
        if (!(game.makeTick() && game.getHead(game.getSnake()).x == 0 && game.getHead(game.getSnake()).y == 0)) {
            throw new Exception();
        } ;
    }

    @Test
    public void test8SoftWall() throws Exception {
        GameState game = StateParser.makeGame("tests\\T8.txt");
        game.makeTick();
        if (!(game.makeTick() && game.getHead(game.getSnake()).x == 0 && game.getHead(game.getSnake()).y == 0)) {
            throw new Exception();
        } ;
        game.turnSnake1(Dir.Right);
        if (!(game.makeTick() && game.getHead(game.getSnake()).x == 1 && game.getHead(game.getSnake()).y == 0)) {
            throw new Exception();
        } ;
    }
    
    @Test
    public void testController() throws Exception{
    	GameState game = StateParser.makeGame("tests\\T1.txt");
    	Controller.snakeController(game, SWT.ARROW_RIGHT);
    	if (game.getSnake().getDir().getIntDir() != 6)
    		throw new Exception();
    	  		
    }
    
    @Test 
    public void testController2() throws Exception{
    	GameState game = StateParser.makeGame("tests\\T1.txt");
    	Controller.snakeController(game, SWT.ARROW_LEFT);
    	if (Dir.ofInt(game.getSnake().getDir().getIntDir()) != Dir.Left)
    		throw new Exception();
    	  		
    }
}
//Андрей, Селектор + двигать без циферок, 2 задача, тесты, избавится от 2468








