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
    public void testController1(){
    	GameState game = StateParser.makeGame("tests\\T1.txt");
    	Controller.snakeController(game, SWT.ARROW_RIGHT);
    	assertEquals(game.getSnake().getDir().getDir(), Dir.Left);
    	  		
    }
    
    @Test 
    public void testController2(){
    	GameState game = StateParser.makeGame("tests\\T1.txt");
    	Controller.snakeController(game, SWT.ARROW_LEFT);
    	assertEquals(game.getSnake().getDir().getDir(), Dir.Right);
    	  		
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

}








