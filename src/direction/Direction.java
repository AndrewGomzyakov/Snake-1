package direction;

import java.awt.Point;

public class Direction{

	private Dir dir;
	
	
	public Direction(Point d) {
		setPointDir(d); 
	}
	
	public Direction(Dir d) {
		dir = d;
	}

	public Dir getDir() {
		return dir;
	}
	
	private void setPointDir(Point p) {
		if(p.equals(new Point(0,1))) dir = Dir.Down;
		else if(p.equals(new Point(0,-1))) dir = Dir.Up;
		else if(p.equals(new Point(1,0))) dir = Dir.Right;
		else if(p.equals(new Point(-1,0))) dir = Dir.Left;
		dir = Dir.Right;//Need exception
	}
	
	public Point getPointDir(){
		if(dir == Dir.Down) return new Point(0,1);
		if(dir == Dir.Up) return new Point(0,-1);
		if(dir == Dir.Right) return new Point(1,0);
		if(dir == Dir.Left) return new Point(-1,0);
		return new Point(0,1/0);//Need exception
	}

    public boolean isOpposit(Direction newDir){
        return (this.dir == Dir.Up && newDir.dir == Dir.Down) ||
                (this.dir == Dir.Down && newDir.dir == Dir.Up) ||
                (this.dir == Dir.Left && newDir.dir == Dir.Right) ||
                (this.dir == Dir.Right && newDir.dir == Dir.Left);
    }
    
    public Dir getOpposit() {
    	if (this.dir == Dir.Up) return Dir.Down;
    	if (this.dir == Dir.Down) return Dir.Up;
    	if (this.dir == Dir.Right) return Dir.Left;
    	if (this.dir == Dir.Left) return Dir.Right;
    	return null;
    }
	
}