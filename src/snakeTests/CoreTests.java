package snakeTests;

import java.awt.Point;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.junit.*;

import clone.SnakeCloner;
import direction.Dir;
import factory.FoodFactory;
import factory.HedgFactory;
import factory.PillowFactory;
import factory.TeleportFactory;
import gameCore.GameState;
import gameCore.StateParser;
import gui.Controller;
import model.IObject;
import model.Snake;
import save.Saver;

public class CoreTests extends Assert {
	

	@Test 
	public void testMakeGame(){ 
	StateParser game = new StateParser(); 
	GameState result = game.makeGame("tests\\T4.txt"); 

	char[][] maze = { {'.','.'}, {'.','.'}, {'.','.'} }; 

	assertEquals(maze, result.getMap()); 
	} 

    @Test
    public void testTeleport() {
        GameState game = StateParser.makeGame("tests\\T5.txt");
        IObject[] t = (new TeleportFactory()).create(game,
                new Point[] {new Point(0, 1), new Point(1, 0)});
        game.setObjs(t);
        char inp = game.getCell(new Point(0, 1));
        assertEquals('P', inp);
    }
    
    @Test
    public void testPillow(){
        GameState game = StateParser.makeGame("tests\\T7.txt");
        IObject[] t = (new PillowFactory()).create(game, new Point[] {new Point(0, 1)});
        game.setObjs(t);
        char cell = game.getCell(new Point(0, 1));
        assertEquals('%', cell);
    }
    
    @Test
    public void testFood(){
        GameState game = StateParser.makeGame("tests\\T7.txt");
        IObject[] t = (new FoodFactory()).create(game, new Point[] {new Point(0, 1)});
        game.setObjs(t);
        char cell = game.getCell(new Point(0, 1));
        assertEquals('*', cell);
    }
    
    
   /* 
    @Test
    public void testHedg(){
        GameState game = StateParser.makeGame("tests\\T7.txt");
        IObject[] t = (new HedgFactory()).create(game, new Point[] {new Point(0, 1)});
        game.setObjs(t);
        char cell = game.getCell(new Point(0, 1));
        boolean isHedg = cell == 'A' || cell == 'S' || cell == 'W' || cell == 'D';
        assertTrue(isHedg);
    }
*/
    @Test
    public void testClone(){
    	ArrayList<Snake> snakes = new ArrayList<Snake>();
    	Point[] body = new Point[] {new Point(0, 0), 
				new Point(0, 1),
				new Point(0, 2),
				new Point(0, 3),
				new Point(0, 4),
				new Point(0, 5)};
    	snakes.add(new Snake(body, Dir.Up));
    	ArrayList<Snake> newSnakes = SnakeCloner.clone(snakes);
        assertEquals(2, newSnakes.size());
    }
    
    
    @Test
    public void testController(){
    	GameState game = StateParser.makeGame("tests\\T1.txt");
    	Controller.snakeController(game, SWT.ARROW_RIGHT);
    	assertEquals(game.getSnake().getDir().getDir(), Dir.Right);
    	  		
    }
    
    @Test 
    public void testController2(){
    	GameState game = StateParser.makeGame("tests\\T1.txt");
    	Controller.snakeController(game, SWT.ARROW_LEFT);
    	assertEquals(game.getSnake().getDir().getDir(), Dir.Left);
    	  		
    }
    
    @Test
    public void testController3(){
    	GameState game = StateParser.makeGame("tests\\T1.txt");
    	Controller.snakeController(game, SWT.ARROW_DOWN);
    	assertEquals(game.getSnake().getDir().getDir(), Dir.Down);
    	  		
    }
    
    @Test
    public void testController4(){
    	GameState game = StateParser.makeGame("tests\\T2.txt");
    	Controller.snakeController(game, SWT.ARROW_UP);
    	assertEquals(game.getSnake().getDir().getDir(), Dir.Up);  		
    }
    
    @Test
    public void testSaver(){
    	GameState game = StateParser.makeGame("tests\\save.txt");
    	Saver.save("tests\\saver.txt", game);  		
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
}








