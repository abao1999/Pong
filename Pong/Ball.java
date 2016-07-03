package Pong;
public class Ball {

	// NOTE TO THE USER: This program accounts for perfect conservation of momentum. 
	// Therefore, colliding balls will have different changes in velocity in accordance with relative mass and initial velocity

	    // instance variables
	     double rx, ry;        // position
	     double vx, vy;        // velocity
	     double radius;  // radius

	    // constructor
	    public Ball() {
	    	rx = Math.random()- 0.1;
	    	ry = Math.random()-0.1;
	         vx     = 0.015 - Math.random() * 0.05;
	         vy     = 0.015 - Math.random() * 0.05;
	        radius = 0.025 + Math.random() * 0.05;
	    }
	    // bounce of vertical wall by reflecting x-velocity
	    private void bounceOffVerticalWall() {
	        vx = -vx;
	    }

	    // bounce of horizontal wall by reflecting y-velocity
	    private void bounceOffHorizontalWall() {
	        vy = -vy;
	    }

	    // move the ball one step
	    public void move() {
	        if (Math.abs(rx + vx) + radius >= 1.0) bounceOffVerticalWall();
	        if (Math.abs(ry + vy) + radius >= 1.0) bounceOffHorizontalWall();
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
	        Ball b1 = new Ball();
	        b1.rx = 0.2;
	        Ball b2 = new Ball();
	 
	        // animate them
	        StdDraw.setXscale(-1.0, +1.0);
	        StdDraw.setYscale(-1.0, +1.0);
	        double mass1 = (b1.radius-b2.radius)/(b1.radius+b2.radius);
	        double mass2 = (2*b2.radius)/(b1.radius+b2.radius);
	        double mass3 = (2*b1.radius)/(b1.radius+b2.radius);
	        double mass4 = (b2.radius-b1.radius)/(b1.radius+b2.radius);
	        double temp1 = 0;
	        double temp2 = 0;
	        while (true) {
	            StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
	            StdDraw.filledSquare(0.0, 0.0, 1.0);
	            StdDraw.setPenColor(StdDraw.RED);
	            b1.move();
	            b2.move();
	            b1.draw();
	            b2.draw();
	            double distance = Math.sqrt((b2.rx - b1.rx)*(b2.rx - b1.rx)+(b2.ry - b1.ry)*(b2.ry - b1.ry));
	            if ((distance) <= (b1.radius + b2.radius))	{
	            	temp1 = b1.vx;
	            	temp2 = b1.vy;
	            	b1.vx = mass1*(b1.vx)+mass2*(b2.vx);
	            	b1.vy = mass1*(b1.vy)+mass2*(b2.vy);
	            	b2.vx = mass3*(temp1)+mass4*(b2.vx);
	            	b2.vy = mass3*(temp2)+mass4*(b2.vy);
	            }
	            StdDraw.show(10);
	        	        	
	        }
	    }
	}
