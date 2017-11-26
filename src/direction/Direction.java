package direction;

import java.awt.Point;

public class Direction{

	private Dir dir;
	
	public Direction(int d) {
		setIntDir(d);
	} 
	
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

    public boolean IsOpposit(Direction newDir){
        return (this.dir == Dir.Up && newDir.dir == Dir.Down) ||
                (this.dir == Dir.Down && newDir.dir == Dir.Up) ||
                (this.dir == Dir.Left && newDir.dir == Dir.Right) ||
                (this.dir == Dir.Right && newDir.dir == Dir.Left);
    }

	public int getIntDir() {
		if(dir == Dir.Right)
			return 6;
		if(dir == Dir.Left) 
			return 4;
		if(dir == Dir.Down) 
			return 2;
		if(dir == Dir.Up) 
			return 8;
		int exept = 1/0; 
		return -1;
	}
	
	public void setIntDir(int d) {
		switch (d) {
		case 6: dir = Dir.Right;
				break;
		case 4: dir = Dir.Left;
				break;
		case 8: dir = Dir.Up;
				break;
		case 2: dir = Dir.Down;
				break;
		default: dir = Dir.Right;//Need exception
		}
	}
}