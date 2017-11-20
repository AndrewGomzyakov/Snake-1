package SnakeCore;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
// import java.util.Map;

public class GameState {
    private Snake snake;
    private ArrayList<Snake> snakeClone = new ArrayList<Snake>();
    private char[][] maze;
    private char[][] map;
    private Random rnd;
    private boolean isAlive;
    private List<IObject> objs = new LinkedList<IObject>();
    private int height;
    private int width;

   
    public static HashMap<String, IObjFactory> Dic = new HashMap<String, IObjFactory>();
    static {
        Dic.put("Food", new FoodFactory());
        Dic.put("Hedg", new HedgFactory());
        Dic.put("Teleport", new TeleportFactory());
        Dic.put("Pillow", new PillowFactory());
    }
    /*
     * public GameState(String key){ SetMap(key); isAlive=true; //objs.add(e) snake=new Snake(new
     * Point[] {new Point(4,3),new Point(5,3)},0);//BBAADD!
     * 
     * }
     */

    public GameState(char[][] maze, Point[] snakePos, Direction snakeDir,
            ArrayList<Tuple<String, Integer[]>> objsCreators) {
        isAlive = true;
        this.maze = maze;
        map = new char[maze.length][];
        rnd = new Random();
        height = maze.length;
        width = maze[0].length;// bad
        snake = new Snake(snakePos, snakeDir.getDirN());
        for (Tuple<String, Integer[]> tup : objsCreators) {
            setObjs(Dic.get(tup.x).configure(this, tup.y));
        }
    }
    
    
    public char[][] getMap() {
        for (int i = 0; i < map.length; i++)
            map[i] = maze[i].clone();
        for (int i = 0; i < snake.getBody().size() - 1; i++) {
            Point p = snake.getBody().get(i);
            map[p.y][p.x] = '@';
        Point point = snake.getBody().get(snake.getBody().size() - 1);
        map[point.y][point.x] = '?';
        }
        for (int i = 0; i < getObjsArr().size(); i++) {
            Point[] ps = getObjsArr().get(i).getLocs();
            char ico = getObjsArr().get(i).getIcon();
            for (int j = 0; j < ps.length; j++) {
                Point p = ps[j];// Проверка на пересечение
                map[p.y][p.x] = ico;
            }
        }
        for (int i = 0; i < snakeClone.size(); i++) {
        	for (int j = 0; j < snakeClone.get(i).getBody().size() - 1; j++) {
        		Point p = snakeClone.get(i).getBody().get(j);
        		 map[p.y][p.x] = '@';
        	}
        	Point point = snakeClone.get(i).getBody().get(snakeClone.get(i).getBody().size() - 1);
            map[point.y][point.x] = '?';
        }
        return map;
    }

    
    public void cloneSnake() {
    	snakeClone.add(snake);
    	snakeClone = SnakeCloner.clone(snakeClone);
    	for (int i = 0; i < snakeClone.size(); i++) {
    		if (snakeClone.get(i).getHead() == this.snake.getHead()) {
    			this.snake = snakeClone.get(i);
    			snakeClone.remove(this.snake);
    			break;
    		}
    	}
    }
    
    public boolean makeTick() {
        if (!isAlive)
            return false;
        tickObjs();
        tickFactorys();
        cloneSnake();
        for (int i = 0 ; i< snakeClone.size(); i++) {
        	Point tmp = collise(snakeClone.get(i)); 
        	boolean flag = false;
        	if (!(maze[tmp.y][tmp.x] == '+' || (maze[tmp.y][tmp.x] == '.' && snakeClone.get(i).makeStep())))
        		die(snakeClone.get(i));
        	else
	        	for (int j = 0 ;j < snakeClone.size(); j++) {
	        		if (snakeClone.get(j).getBody().contains(tmp) && i != j) {
	        			die(snakeClone.get(i));
	        			//die(snakeClone.get(j));
	        			flag = true;
	        			break;
	        		}
        	}
        	if (this.snake.getBody().contains(tmp)) {
        		die(snakeClone.get(i));
        		die(this.snake);
        		flag = true;
        	}
        	if (flag) {
        		break;
        	}
        	
        		
        }
        if (!snake.isMoving())
            return true;
        
        Point next = collise(snake);
        if (maze[next.y][next.x] == '+' || (maze[next.y][next.x] == '.' && snake.makeStep()))
            return true;
        else
            return die(snake);
    }

    private Point collise(Snake snake) {
        IObject col = objsCollision(snake.getNext());
        for (Point nextTmp = null; nextTmp==null || (nextTmp.x != snake.getNext().x && nextTmp.y != snake.getNext().y);) {
            if (col == null) {
                snake.setNext(getBoundedCord(snake, snake.getNext()));
                return snake.getNext();
            }
            nextTmp = (Point) snake.getNext().clone();
            if (col.interact(snake, snake.getNext())) { //TODO make interact better
                col=null;
                die(snake);
            } else {
                setObjs(col.getFact().utilize(col));//TODO Make it better
                snake.setNext(getBoundedCord(snake, snake.getNext()));
            }
            col = objsCollision(snake.getNext());
        }
        if (col != null)
            objs.remove(col);
        return snake.getNext();
    }

    private void tickFactorys() {
        for (IObjFactory fact : Dic.values())
            setObjs(fact.tick());
    }

    private void tickObjs() {
        for (IObject obj : objs)
            obj.tick();
    }

    public boolean die(Snake snake) {
    	if (snake == this.snake)
    		isAlive = false;
    	if (snakeClone.contains(snake))
    		snakeClone.remove(snake);
        return false; // cuz datz kool
    }

    protected Point getBoundedCord(Snake snake, Point p) {
        if (!(p.x >= 0 && p.x < width && p.y >= 0 && p.y < height)) {
            p = new Point((p.x + width) % width, (p.y + height) % height);
            snake.setNext(p);
        }
        return p;
    }

    private IObject objsCollision(Point p) {
        for (IObject obj : getObjsArr())
            for (Point el : obj.getLocs())
                if (p.x == el.x && p.y == el.y)
                    return obj;
        return null;
    }

    public void feedSnake(Snake snake, int val) {
        snake.grow(val); //TODO Закрыть!
    }
    
    

    public boolean turnSnake1(int dir) {
        return snake.turn(new Direction(dir));
    }
    
    public boolean turnSnake2(int dir) {
    	if (snakeClone.size() >= 1)
    		return snakeClone.get(0).turn(new Direction(dir));
    	return false;
    }
    
    public boolean turnSnake3(int dir) {
    	if (snakeClone.size() >= 2)
    		return snakeClone.get(1).turn(new Direction(dir));
    	return false;
    }
    
    public boolean turnSnake4(int dir) {
    	if (snakeClone.size() >= 3)
    		return snakeClone.get(2).turn(new Direction(dir));
    	return false;
    }
    
    public boolean turnSnake(Snake snake, Point dir) {
        return snake.turn(new Direction(dir));
    }

    protected char getCell(Point p) {
        if (maze[p.y][p.x] == '#')
            return '#';
        if (maze[p.y][p.x] == '+')
            return '+';
        for (Point part : snake.getBody())
            if (part.x == p.x && part.y == p.y)
                return '@';
        for (int i = 0; i < snakeClone.size(); i++)
        	for (Point part : snakeClone.get(i).getBody())
        		 if (part.x == p.x && part.y == p.y)
                     return '@';
        IObject obj = objsCollision(p);
        if (obj != null)
            return obj.getIcon();
        return '.';
    }

    protected Point getRndFreePoint() {
        Point loc = new Point(rnd.nextInt(width), rnd.nextInt(height));
        while (getCell(loc) != '.')
            loc = new Point(rnd.nextInt(width), rnd.nextInt(height));
        return loc;
    }

    protected void teleportHead(Snake snake, Point newHead) {
        snake.setNext(newHead);
    }

    public Point getHead(Snake snake) {
        return snake.getHead();
    }

    public Direction getSnakeDir(Snake snake) {
        return snake.getDir();
    }

    public int getLenght(Snake snake) {
        return snake.getBody().size();
    }

    protected List<IObject> getObjsArr() {
        return objs;
    }

    public Point getSize() {
        return new Point(width, height);
    }

    public Snake getSnake(Snake snake) {
        return snake;
    }

    public void setObjs(IObject[] objs) {
        if (objs == null)
            return;
        for (IObject obj : objs) {
            this.objs.add(obj);
        }
    }
    /*
     * private void setObjs(IObject obj) { this.objs.add(obj); }
     */
}


