package Pong;

import java.util.Random;

public class Animation {
	
	public static void moveIt(Ball b1, Ball b2, Ping b3)	{
		Random random = new Random();
        if (((b3.rx - b3.radius)<=-0.97) && ((b3.ry>-0.1)&&(b3.ry<0.1)))	{
        	int speed = random.nextInt(2);
        	switch (speed){
        		case 1: {
        			b3.vx *= 1;
        			b3.vy *= 1;
        		}
        		case 2: {
        			b3.vx *= 2;
        			b3.vy *= 2;
        		}
        	}
        }
     		b1.rx += b1.vx;
            b2.rx += b2.vx;
           
            if (Math.abs(b1.rx + b1.vx) + b1.radius > 1.0) b1.move();
            
            if (Math.abs(b2.rx + b2.vx) + b2.radius > 1.0) b2.move();

            double distance1 = Math.sqrt(Math.abs((b3.rx - b1.rx)*(b3.rx - b1.rx)+(b3.ry - b1.ry)*(b3.ry - b1.ry)));
            double distance2 = Math.sqrt(Math.abs((b3.rx - b2.rx)*(b3.rx - b2.rx)+(b3.ry - b2.ry)*(b3.ry - b2.ry)));
            boolean makeNoise = false;
            Random r1 = new Random();
            Random r2 = new Random();
            if (((distance1) <= b1.radius+b3.radius)&&(b3.ry>-0.951)) {
            	makeNoise = true;
            	double o1 = r1.nextInt(190)*0.000075; //straightness of path
         		double o2 = r2.nextInt(190)*0.000075;
         		b3.vx = (o1 >o2)? -o1:o2;
         		b3.vy = 0.02 - (Math.abs(b3.vx));           	
            }
         	if (((distance2) <= b2.radius + b3.radius)&&(b3.ry<0.951))	{
         		makeNoise = true;
         		double o3 = r1.nextInt(190)*0.000075;
         		double o4 = r2.nextInt(190)*0.000075;
         		b3.vx = (o3 >o4)? -o3:o4;
         		b3.vy = 0.02 - (Math.abs(b3.vx));
            }
         	//and now... for the hitting sound...
         	if (makeNoise)	{
         		 new Thread(new Runnable()	{
         	    	String filename3 = "/Swish.wav";
         	    	boolean music3 = false;
         	    	Music two = new Music(filename3, music3);
         	    	@Override
         	    	public void run()	{
         	    		//TODO Auto-generated method stub
         	    		two.play(music3);
         	    	}
         	    }).start();
         	}
            
            	if ((distance2) <= b2.radius + b3.radius)	{
            		b3.vx = -b3.vx;
            		b3.vy = -b3.vy;
                	StdDraw.setPenColor(StdDraw.GREEN);
                	b3.draw();
              
                }
            	if ((distance1) <= b1.radius + b3.radius)	{
                	StdDraw.setPenColor(StdDraw.GREEN);
                	b3.draw();
              
                }
            	if (b3.rx + b3.radius >= 0.97)	{
                	StdDraw.setPenColor(StdDraw.GREEN);
                	b3.draw();
              
                }
            	if (b3.rx - b3.radius <= -0.97)	{
                	StdDraw.setPenColor(StdDraw.GREEN);
                	b3.draw();
              
                }
	}
	public static int pingCheats(Ping b3, int T)	{
		boolean answer_p = StdDraw.hasNextKeyTyped();
        boolean action_z = StdDraw.isKeyPressed(90);
        boolean action_x = StdDraw.isKeyPressed(88);
		if (answer_p)	{
        	if (action_z)	{
        		 T = 0;
        		 b3.vy *=1.01;
        		 b3.vx *=1.01; ////FIX This
        		}
        	else if (action_x)	{
        		 T = 100;
        	}
        }
		return T;
	}
	public static void restartButton(Ball b1, Ball b2, Ping b3)	{
        boolean action_space = StdDraw.isKeyPressed(32);
        if (action_space)	{
    		b3.ry = 0; b3.rx = 0;
    		b3.vx = 0;
    		if ((b3.ry+b3.vy) >= 1)	{
    		b3.vy = 0.05;
    		}
    		else if ((b3.ry+b3.vy) <= -1)	{
    		b3.vy = -0.05;
    		}
    		b1.ry = -1; b1.rx = 0;
    		b2.ry = 1; b2.rx = 0;
    		
    	}
	}
	public static void basicControlsandCheats(Ball b1, Ball b2, Ping b3)	{
		b1.vx = 0;
	    b2.vx = 0;
	    boolean answer1 = StdDraw.hasNextKeyTyped();
	    boolean action1 = StdDraw.isKeyPressed(37);
	    boolean action2 = StdDraw.isKeyPressed(39);
	    boolean action3 = StdDraw.isKeyPressed(38);
	    boolean action4 = StdDraw.isKeyPressed(40);
        boolean controlaction1 = StdDraw.isKeyPressed(49);
	    boolean answer_a = StdDraw.hasNextKeyTyped();
	    boolean action_a = StdDraw.isKeyPressed(65);
	    boolean action_d = StdDraw.isKeyPressed(68);
	    boolean action_w = StdDraw.isKeyPressed(87);
        boolean action_s = StdDraw.isKeyPressed(83);
	    boolean controlaction2 = StdDraw.isKeyPressed(50);
	    boolean answer3 = StdDraw.hasNextKeyTyped();
	    boolean action = StdDraw.isKeyPressed(48);
	    boolean action0 = StdDraw.isKeyPressed(57);
	     //music and sound
	    boolean soundbytes = StdDraw.hasNextKeyTyped();
	    boolean activatesound = StdDraw.isKeyPressed(78);
         if (answer1)	{
         	if (action1)	{
         		b1.vx = -0.01;
         		b1.rx += b1.vx;
         		if (Math.abs(b1.rx-b1.rx)>0)	{ 
             		b1.vx = -0.05;
         		}
         	}
         	if (action2)	{
         		b1.vx = 0.01;
         		b1.rx += b1.vx;
         		if (Math.abs(b1.rx-b1.rx)>0)	{
             		b1.vx = 0.05;
         		}
         	}
         	//some CHEATS
         	if (controlaction1)	{ // CHEATING LIKE A CHEETAH
         	if (action3)	{
         		b1.vy = 0.01;
         		b1.ry += b1.vy;
         		if (Math.abs(b1.ry-b1.ry)>0)	{
             		b1.vy = 0.05;
         		}
         	}
         	else if (action4)	{ //used to just be if statement
         		b1.vy = -0.01;
         		b1.ry += b1.vy;
         		if (Math.abs(b1.ry-b1.ry)>0)	{
             		b1.vy = -0.05;
         		}
         	}
         	}
         }
         if (answer_a)	{
         	if (action_a)	{
         		b2.vx = -0.01;
         		b2.rx+=b2.vx;	
         		if (Math.abs(b2.rx-b2.rx)>0)	{ 
         			b2.vx = -0.05;
         		}
         	}
         	if (action_d)	{
         		b2.vx = 0.01;
         		b2.rx+=b2.vx;	
         		if (Math.abs(b2.rx-b2.rx)>0)	{ 
         			b2.vx = 0.05;
         		}
         	}
         	//EVEN MORE CHEATS
         	if (controlaction2)	{ // CHEATING LIKE A CHEETAH
         	if (action_w)	{
         		b2.vy = 0.01;
         		b2.ry += b2.vy;
         		if ((Math.abs(b2.ry-b2.ry)>0)&&(b2.ry>0.25))	{
             		b2.vy = 0.05;
         		}
         	}
         	else if (action_s)	{ //used to be just if statement
         		b2.vy = -0.01;
         		b2.ry += b2.vy;
         		if ((Math.abs(b2.ry-b2.ry)>0)&&(b2.ry>0.25))	{
             		b2.vy = -0.05;
         		}
         	}
         	}
         }
         AudioEffects.createMusic(soundbytes, activatesound, 100, false);
         if (answer3)	{
     	    if (action)	{
     	    	b3.vx /=2;
     	    	b3.vy /=2;
     	    }
     	    if(action0)	{
     	    	b3.vx*=2;
     	    	b3.vy *=2;
             }
         }
    
         b3.draw();
         b3.move(false);
         if ((b3.ry+b3.vy) >= 1) Screen.displayBlackVictory(b1, b2);
         if ((b3.ry+b3.vy) <= -1) Screen.displayRedVictory(b1, b2);
         
         Random rand = new Random();
         if ((Math.abs(b3.rx + b3.radius)>=0.97) && ((b3.ry>-0.1)&&(b3.ry<0.1)))	{
         	
           	int speed = rand.nextInt(2);
         	switch (speed){
         		case 1: {
         		 b3.vx *= 1;
             	 b3.vy *= 1;
         		}
         		case 2: {
         		 b3.vx *= 2;
             	 b3.vy *= 2;
         		}
         	}
        }
	}
	public static void ComputerMode(Ball b1, Ball b2, Ping b3)	{ //same as method basicControlsandCheats, but with cheats disabled 
		b1.vx = 0;
	    b2.vx = 0;
	    boolean answer1 = StdDraw.hasNextKeyTyped();
	    boolean action1 = StdDraw.isKeyPressed(37);
	    boolean action2 = StdDraw.isKeyPressed(39);
	    boolean answer3 = StdDraw.hasNextKeyTyped();
	    boolean action = StdDraw.isKeyPressed(48);
	    boolean action0 = StdDraw.isKeyPressed(57);
         if (answer1)	{
         	if (action1)	{
         		b1.vx = -0.01;
         		b1.rx += b1.vx;
         		if (Math.abs(b1.rx-b1.rx)>0)	{ 
             		b1.vx = -0.05;
         		}
         	}
         	if (action2)	{
         		b1.vx = 0.01;
         		b1.rx += b1.vx;
         		if (Math.abs(b1.rx-b1.rx)>0)	{
             		b1.vx = 0.05;
         		}
         	}         
         }
         if (answer3)	{
     	    if (action)	{
     	    	b3.vx /=2;
     	    	b3.vy /=2;
     	    }
     	    if(action0)	{
     	    	b3.vx*=2;
     	    	b3.vy *=2;
             }
         }
         b3.draw();
         b3.move(true);
         if ((b3.ry+b3.vy) >= 1) Screen.displayBlackVictory(b1, b2);
         if ((b3.ry+b3.vy) <= -1) Screen.displayRedVictory(b1, b2);
         
         Random rand = new Random();
         if ((Math.abs(b3.rx + b3.radius)>=0.97) && ((b3.ry>-0.1)&&(b3.ry<0.1)))	{
         	
           	int speed = rand.nextInt(2);
         	switch (speed){
         		case 1: {
         		 b3.vx *= 1;
             	 b3.vy *= 1;
         		}
         		case 2: {
         		 b3.vx *= 2;
             	 b3.vy *= 2;
         		}
         	}
        }
	}		
}
