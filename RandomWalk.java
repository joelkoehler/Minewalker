import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
* @author Joel Koehler
**/

public class RandomWalk implements RandomWalkInterface {
	private int x;
	private int y;
	private int gridSize;
	private int stepInterval;
	private Point start; 
	private Point current;
	private Point end;
	private boolean done;
	private ArrayList<Point> path = new ArrayList<Point>();
	private Point p1 = new Point(x, y);
	private Random rand;

/**
* Starts a random walk at a starting point
**/
	public RandomWalk(int gridSize) {
		this.gridSize = gridSize;
		rand = new Random();
		start = new Point(0, gridSize - 1);
		current = new Point(0, gridSize - 1);
		end = new Point(gridSize - 1, 0);
		path.add(start);
		boolean done = false;
	}

/**
* Starts a random walk at a starting point
**/
	public RandomWalk(int gridSize, long seed) {
		this.gridSize = gridSize;
		rand = new Random(seed);
		start = new Point(0, gridSize - 1);
		current = new Point(0, gridSize - 1);
		end = new Point(gridSize - 1, 0);
		path.add(start);
		boolean done = false;
	}

/**
* Takes a step up or right
**/
	@Override
	public void step() {
		// TODO Auto-generated method stub
		if (current.x == gridSize - 1 && current.y == 0) {
			done = true;
		} else {
			stepInterval = rand.nextInt(2);
			if (stepInterval == 0 && current.x != gridSize - 1) {
				current.x += 1;
			} else if (stepInterval == 0 && current.x == gridSize - 1) {
				current.y -= 1;
			}
			if (stepInterval == 1 && current.y != 0) {
				current.y -= 1;
			} else if (stepInterval == 1 && current.y == 0) {
				current.x += 1;
			}
			Point newPoint = new Point(current.x, current.y);
			path.add(newPoint);
		}
	}

/**
*  Takes a series of steps
**/
	@Override
	public void createWalk() {
		// TODO Auto-generated method stub
		while (done == false) {
			step();
		}
		System.out.println(this.toString());
	}

/**
* returns if the walk is done
**/
	@Override
	public boolean isDone() {
		return done;
	}

/**
* returns grid size
**/
	@Override
	public int getGridSize() {
		// TODO Auto-generated method stub
		return gridSize;
	}

/**
* returns start point
**/
	@Override
	public Point getStartPoint() {
		// TODO Auto-generated method stub
		return start;
	}

/**
* returns end point
**/
	@Override
	public Point getEndPoint() {
		return end;
	}

/**
* returns current point
**/
	@Override
	public Point getCurrentPoint() {
		// TODO Auto-generated method stub
		return current;
	}

/**
* returns a copy of the path
**/
	@Override
	public ArrayList<Point> getPath() {
		// TODO Auto-generated method stub
		ArrayList<Point> copy = new ArrayList<Point>();
		copy = (ArrayList<Point>) path.clone();
		return copy;
	}

/**
* returns a string of the path
**/
	public String toString() {
		String str = "";
		for (Point start : path) {
			str = str + "[" + start.x + "," + start.y + "]";
		}
		return str;
	}

}
