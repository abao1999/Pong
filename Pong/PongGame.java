package Pong;

/**
 BLACK IS PLAYER 1: Controls: ARROW keys
 RED IS PLAYER 2: Controls: WSAD keys
 CHEATS: 	
 For Black, hold down '1' to move up and down
 For RED, hold down '2' to move up and down (you can let go of '2' key after a while)
 TO PAUSE: Hit '0' key
 TO UNPAUSE: Hit '9' key
 INSTANT KILL: Press '0' '9' '9' key combination
 Note: Pause does not pause the game... It only slows down the ball by a large factor
 *If ball hits cyan bars on side of the board, there is a random chance that it will accelerate temporarily until hit by a paddle
 HIT 'Z' to accelerate the ball, or to speed up the game
 HIT 'X' to slow down the ball
 */
public class PongGame extends Ball	{	
	public static void main(String[] args) {
		Music one = new Music("/Beethoven.wav", true);
		one.play(true);
		Music two = new Music("/PlantsVsZombies.wav",true);
	    
        // set boundary to box with coordinates between -1 and +1
        StdDraw.setXscale(-1, +1);
        StdDraw.setYscale(-1, +1);
        
        // create an array of N random balls
        Ball b1 = new Ball();
        	b1.radius = 0.1;
        	b1.rx = 0;
        	b1.ry = -1;
        Ball b2 = new Ball();
        	b2.radius = 0.1;
        	b2.rx = 0;
        	b2.ry = 1;
        Ping b3 = new Ping(false);
        Ping pong = new Ping(true);
        
        while (true) {
         	Screen.displayHomeScreen();
            final boolean play = StdDraw.isKeyPressed(80);
            final boolean playComputer = StdDraw.isKeyPressed(67);
            boolean soundbytes = StdDraw.hasNextKeyTyped();
            boolean activatesound = StdDraw.isKeyPressed(78);
            boolean display1v1Message = false;
            while (play) {
            	one.stop();
            	int T = 10;
            	Screen.displayGameScreen(b1, b2, play, false);
          		Animation.basicControlsandCheats(b1, b2, b3);
          		Animation.moveIt(b1, b2, b3);
                T = Animation.pingCheats(b3, T);
    	        Animation.restartButton(b1, b2, b3);
                StdDraw.show(T); 
            }
        	two.play(true); //can't put in while loop for some reason...
            while (playComputer)	{
            	one.stop();
            	display1v1Message = true; //if true, then replaces RED PLAYER text with new text
            	int T = 10;
            	b2.radius = 0;
            	Screen.displayGameScreen(b1, b2, play/**=false*/, display1v1Message);
                Animation.ComputerMode(b1, b2, pong);
                Animation.moveIt(b1, b2, pong);
                T = Animation.pingCheats(pong, T);
                Animation.restartButton(b1, b2, pong);
                StdDraw.show(T);
            }
            AudioEffects.createMusic(soundbytes, activatesound, 10, true);
        }   
    }
}



	  
