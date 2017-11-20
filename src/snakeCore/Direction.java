package snakeCore;

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

	
	private Dir setPointDir(Point p) {
		if(p.equals(new Point(1,0))) return Dir.Right;
		else if(p.equals(new Point(-1,0))) return Dir.Left;
		else if(p.equals(new Point(0,1))) return Dir.Down;
		else if(p.equals(new Point(0,-1))) return Dir.Up;
		return Dir.Right;//Need exception
	}
	
	public Point getPointDir(){
		if(dir == Dir.Right) return new Point(1,0);
		if(dir == Dir.Left) return new Point(-1,0);
		if(dir == Dir.Down) return new Point(0,1);
		if(dir == Dir.Up) return new Point(0,-1);
		return new Point(0,1/0);//Need exception
	}

    public boolean IsOpposit(Direction newDir){
        return (this.getIntDir()==8 && newDir.getIntDir()==2) ||
                (this.getIntDir()==2 && newDir.getIntDir()==8) ||
                (this.getIntDir()==4 && newDir.getIntDir()==6) ||
                (this.getIntDir()==6 && newDir.getIntDir()==4);
    }

	public int getIntDir() {
		if(dir == Dir.Right) return 6;
		if(dir == Dir.Left) return 4;
		if(dir == Dir.Down) return 2;
		if(dir == Dir.Up) return 8;
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