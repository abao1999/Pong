package Pong;

public class Ping {

    // instance variables
     double rx, ry;        // position
     double vx, vy;        // velocity
     double radius;  // radius

    // constructor
    public Ping(boolean playComputer) {
    	rx = 0;
    	ry = 0;
        vx = 0;
        vy = -0.02;
        radius = 0.05;
    }

    // bounce of vertical wall by reflecting x-velocity
     void bounceOffVerticalWall() {
        vx = -vx;
    }

    // bounce of horizontal wall by reflecting y-velocity
     void bounceOffHorizontalWall() {
        vy = -vy;
    }

    // move the ball one step
    public void move(boolean playComputer) {
        if (Math.abs(rx + vx) + radius > 1.0) bounceOffVerticalWall();
        if (playComputer)	{
        	if ((ry+vy)+radius>1.0) bounceOffHorizontalWall();
        }
        rx = rx + vx;
        ry = ry + vy;
    }

    // draw the ball
    public void draw() {
        StdDraw.filledCircle(rx, ry, radius);
    }
    // test client
    public static void main(String[] args) {

        // create and initialize two balls
    	boolean playComputer = false;
        Ping b1 = new Ping(playComputer);
        // animate them
        StdDraw.setXscale(-1.0, +1.0);
        StdDraw.setYscale(-1.0, +1.0);
        while (true) {
            StdDraw.setPenColor (StdDraw.BOOK_LIGHT_BLUE);
            StdDraw.filledSquare(0.0, 0.0, 1.0);
            StdDraw.setPenColor(StdDraw.BLACK);
            b1.move(playComputer);
            b1.draw();
            StdDraw.show(20);
        }
    }
}
