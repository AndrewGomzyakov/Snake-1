package model;

import java.awt.Point;
import java.util.Random;

import direction.Dir;
import direction.Direction;
import factory.HedgFactory;

public final class Hedg extends IObject {

    private Direction dir;
    private char ico;
    private Point loc;
    private Random rnd;

    public Hedg(HedgFactory fact, Point[] p) {
        this.fact = fact;
        loc = p[0];
        commonInit();
    }

    public Hedg(HedgFactory fact, Point p) {
        this.fact = fact;
        loc = p;
        commonInit();
    }
    public Hedg(HedgFactory fact, Point p,Point d) {
        this.fact = fact;
        loc = p;
        dir = new Direction(d);
        ico = makeIco(dir.getDir());
    }

    public Hedg(HedgFactory fact) {
        this.fact = fact;
        commonInit();
        // replace();
    }


    private void commonInit() {
        rnd = new Random();
        Dir[] dirs = {Dir.Right, Dir.Left, Dir.Up, Dir.Down};
        dir = new Direction(dirs[rnd.nextInt(4)]);
        ico = makeIco(dir.getDir());
    }

    private char makeIco(Dir dir) {
    	if (dir == Dir.Right)
                return 'D';
    	if (dir == Dir.Up)
                return 'W';
    	if (dir == Dir.Down)
                return 'S';
    	if (dir == Dir.Left)
                return 'A';
        return 'F';
    }

    @Override
    public Point[] getLocs() {
        return new Point[] {loc};
    }

    @Override
    public char getIcon() {
        return ico;
    }

    @Override
    public void tick() {}

    /*
     * private void replace() { }
     */
    @Override
    public boolean interact(Snake snake, Point p) {
        if (snake.getDir().isOpposit(dir)) {
            snake.grow(2);
            commonInit();
            // replace();
            return false;
        } else {
            return true;
        }
    }

    public Direction getDir() {
        return dir;
    }

}
