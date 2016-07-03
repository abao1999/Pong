package Pong;

import java.awt.Font;

public class Screen {
	public static void displayHomeScreen()	{
		StdDraw.setPenColor(StdDraw.BLUE);
 		Font title = new Font("Futura", Font.PLAIN, 34);
    	StdDraw.setFont(title);
    	StdDraw.text(0,0.7,"PONG!!! (Version 2.0)");
    	Font subTitle = new Font("Futura", Font.PLAIN, 17);
    	StdDraw.setFont(subTitle);
    	StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
    	StdDraw.text(0, 0.55, "And With Audio Too???");
 		StdDraw.setPenColor(StdDraw.BLACK);
 		Font producer = new Font("Times", Font.ITALIC, 16);
 		StdDraw.setFont(producer);
    	StdDraw.text(0, 0.4, "Created By AwesomeToad, est. 2014; � (main file) 2014");
    	StdDraw.setPenColor(StdDraw.GREEN);
    	StdDraw.square(0.0, -0.2, 0.50);
    	StdDraw.setPenRadius(0.02);
    	StdDraw.setPenColor(StdDraw.ORANGE);
    	Font instructions = new Font("Arial", Font.BOLD, 16);
    	StdDraw.setFont(instructions);
    	StdDraw.text(0.0, 0.2, "INSTRUCTIONS");
    	StdDraw.setPenColor(StdDraw.BLACK);
    	StdDraw.text(0.0, 0.0, "Player 1: Black: ARROWS");
    	StdDraw.text(0.0, -0.05, "Player 2: RED: WSAD");
    	StdDraw.text(0.0, -0.15, "Cheats:");
    	StdDraw.text(0.0, -0.2, "P1: hold 1; P2: hold 2");
    	StdDraw.text(0.0, -0.25, "Inc/Dec ball speed: press 'z'; hold 'x'");
    	StdDraw.text(0.0, -0.35, "To pause: 0; to unpause: 9");
    	StdDraw.text(0.0, -0.4, "To shoot ball, '0'+'9'+'9' OR hold 'z'");
    	StdDraw.setFont();
 		StdDraw.setPenColor(StdDraw.MAGENTA);
    	StdDraw.text(0, - 0.8, "Press 'p' for PVP and 'c' for PVC and press 'n' for gun shooting sound");
    	StdDraw.text(0, -0.9, "Press 'spacebar' to replay");
	}
	public static void displayGameScreen(Ball b1, Ball b2, boolean play, boolean display1v1message)	{
		StdDraw.setPenColor (StdDraw.WHITE);
 		StdDraw.filledSquare(0.0, 0.0, 1.5);
		StdDraw.filledRectangle(-0.99, 0.0, 0.01, 0.2);
 		StdDraw.setPenColor (StdDraw.BOOK_LIGHT_BLUE);
    	StdDraw.filledRectangle(0.0, 0.5, 1, 0.49);
    	StdDraw.setPenColor (StdDraw.GRAY);
    	StdDraw.filledRectangle(0.0, 0.0, 1, 0.01);
		StdDraw.setPenColor (StdDraw.BLUE);
		StdDraw.filledRectangle(0.0, -0.5, 1, 0.49);
		StdDraw.setPenColor (StdDraw.YELLOW);
		StdDraw.filledRectangle(0.0, 0.99, 1, 0.01);
		StdDraw.setPenColor (StdDraw.YELLOW);
		StdDraw.filledRectangle(0.0,-0.99, 1, 0.01);
		StdDraw.setPenColor (StdDraw.CYAN);
		StdDraw.filledRectangle(0.99, 0.0, 0.01, 0.2);
		StdDraw.setPenColor (StdDraw.CYAN);
		StdDraw.filledRectangle(-0.99, 0.0, 0.01, 0.2);
    	StdDraw.setPenColor(StdDraw.WHITE);
    	
    	StdDraw.setPenColor(StdDraw.BLACK);
    	b1.draw();
    	if (play)	{
    	StdDraw.setPenColor(StdDraw.RED);
    	b2.draw();
    	}
    	StdDraw.setPenColor(StdDraw.WHITE);
    	StdDraw.filledRectangle(0, -1.25, 1.5 ,0.25);
    	StdDraw.filledRectangle(0, 1.25, 1.5 ,0.25);
    	StdDraw.setFont();
    	StdDraw.setPenColor(StdDraw.MAGENTA);
    	if (display1v1message) StdDraw.text(0,1.05,"HOW LONG CAN YOU LAST?");
    	else StdDraw.text(0,1.05,"RED PLAYER");
    	StdDraw.text(0,-1.07,"BLACK PLAYER");
    	StdDraw.setPenColor(StdDraw.WHITE);
	}
	public static void displayBlackVictory(Ball b1, Ball b2)	{
		StdDraw.setPenColor (StdDraw.WHITE);
		b1.draw();
    	StdDraw.setPenColor (StdDraw.GREEN);
		StdDraw.filledRectangle(0.0,-0.5, 1, 0.49);
		StdDraw.setPenColor (StdDraw.BLACK);
		StdDraw.filledRectangle(0.0, 0.5, 1, 0.49);
		b1.vx = 0;
		b2.vx = 0;
		StdDraw.setPenColor(StdDraw.BLACK);
    	StdDraw.setFont();
    	StdDraw.text(0,-0.5,"BLACK WINS !!!");
    	StdDraw.setPenColor(StdDraw.WHITE);
    	StdDraw.filledRectangle(0, -1.25, 1.5 ,0.25);
    	StdDraw.filledRectangle(0, 1.25, 1.5 ,0.25);
	}
	public static void displayRedVictory(Ball b1, Ball b2)	{
		StdDraw.setPenColor (StdDraw.WHITE);
		b2.draw();
			StdDraw.setPenColor (StdDraw.GREEN);
		StdDraw.filledRectangle(0.0, 0.5, 1, 0.49);
		StdDraw.setPenColor (StdDraw.BLACK);
		StdDraw.filledRectangle(0.0, -0.5, 1, 0.49);
		b1.vx = 0;
		b2.vx = 0;
		StdDraw.setPenColor(StdDraw.RED);
    	StdDraw.setFont();
    	StdDraw.text(0,0.5,"RED WINS !!!");
    	StdDraw.setPenColor(StdDraw.WHITE);
    	StdDraw.filledRectangle(0, -1.25, 1.5 ,0.25);
    	StdDraw.filledRectangle(0, 1.25, 1.5 ,0.25);
	}

}
