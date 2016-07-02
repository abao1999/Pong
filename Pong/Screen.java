/*     */ package Pong;
/*     */ 
/*     */ import java.awt.Font;
/*     */ 
/*     */ public class Screen
/*     */ {
/*     */   public static void displayHomeScreen()
/*     */   {
/*   7 */     StdDraw.setPenColor(StdDraw.BLUE);
/*   8 */     Font title = new Font("Futura", 0, 34);
/*   9 */     StdDraw.setFont(title);
/*  10 */     StdDraw.text(0.0D, 0.7D, "PONG!!! (Version 2.0)");
/*  11 */     Font subTitle = new Font("Futura", 0, 17);
/*  12 */     StdDraw.setFont(subTitle);
/*  13 */     StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
/*  14 */     StdDraw.text(0.0D, 0.55D, "And With Audio Too???");
/*  15 */     StdDraw.setPenColor(StdDraw.BLACK);
/*  16 */     Font producer = new Font("Times", 2, 16);
/*  17 */     StdDraw.setFont(producer);
/*  18 */     StdDraw.text(0.0D, 0.4D, "Created By AwesomeToad, est. 2014; © (main file) 2014");
/*  19 */     StdDraw.setPenColor(StdDraw.GREEN);
/*  20 */     StdDraw.square(0.0D, -0.2D, 0.5D);
/*  21 */     StdDraw.setPenRadius(0.02D);
/*  22 */     StdDraw.setPenColor(StdDraw.ORANGE);
/*  23 */     Font instructions = new Font("Arial", 1, 16);
/*  24 */     StdDraw.setFont(instructions);
/*  25 */     StdDraw.text(0.0D, 0.2D, "INSTRUCTIONS");
/*  26 */     StdDraw.setPenColor(StdDraw.BLACK);
/*  27 */     StdDraw.text(0.0D, 0.0D, "Player 1: Black: ARROWS");
/*  28 */     StdDraw.text(0.0D, -0.05D, "Player 2: RED: WSAD");
/*  29 */     StdDraw.text(0.0D, -0.15D, "Cheats:");
/*  30 */     StdDraw.text(0.0D, -0.2D, "P1: hold 1; P2: hold 2");
/*  31 */     StdDraw.text(0.0D, -0.25D, "Inc/Dec ball speed: press 'z'; hold 'x'");
/*  32 */     StdDraw.text(0.0D, -0.35D, "To pause: 0; to unpause: 9");
/*  33 */     StdDraw.text(0.0D, -0.4D, "To shoot ball, '0'+'9'+'9' OR hold 'z'");
/*  34 */     StdDraw.setFont();
/*  35 */     StdDraw.setPenColor(StdDraw.MAGENTA);
/*  36 */     StdDraw.text(0.0D, -0.8D, "Press 'p' for PVP and 'c' for PVC and press 'n' for gun shooting sound");
/*  37 */     StdDraw.text(0.0D, -0.9D, "Press 'spacebar' to replay");
/*     */   }
/*     */   public static void displayGameScreen(Ball b1, Ball b2, boolean play, boolean display1v1message) {
/*  40 */     StdDraw.setPenColor(StdDraw.WHITE);
/*  41 */     StdDraw.filledSquare(0.0D, 0.0D, 1.5D);
/*  42 */     StdDraw.filledRectangle(-0.99D, 0.0D, 0.01D, 0.2D);
/*  43 */     StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
/*  44 */     StdDraw.filledRectangle(0.0D, 0.5D, 1.0D, 0.49D);
/*  45 */     StdDraw.setPenColor(StdDraw.GRAY);
/*  46 */     StdDraw.filledRectangle(0.0D, 0.0D, 1.0D, 0.01D);
/*  47 */     StdDraw.setPenColor(StdDraw.BLUE);
/*  48 */     StdDraw.filledRectangle(0.0D, -0.5D, 1.0D, 0.49D);
/*  49 */     StdDraw.setPenColor(StdDraw.YELLOW);
/*  50 */     StdDraw.filledRectangle(0.0D, 0.99D, 1.0D, 0.01D);
/*  51 */     StdDraw.setPenColor(StdDraw.YELLOW);
/*  52 */     StdDraw.filledRectangle(0.0D, -0.99D, 1.0D, 0.01D);
/*  53 */     StdDraw.setPenColor(StdDraw.CYAN);
/*  54 */     StdDraw.filledRectangle(0.99D, 0.0D, 0.01D, 0.2D);
/*  55 */     StdDraw.setPenColor(StdDraw.CYAN);
/*  56 */     StdDraw.filledRectangle(-0.99D, 0.0D, 0.01D, 0.2D);
/*  57 */     StdDraw.setPenColor(StdDraw.WHITE);
/*     */ 
/*  59 */     StdDraw.setPenColor(StdDraw.BLACK);
/*  60 */     b1.draw();
/*  61 */     if (play) {
/*  62 */       StdDraw.setPenColor(StdDraw.RED);
/*  63 */       b2.draw();
/*     */     }
/*  65 */     StdDraw.setPenColor(StdDraw.WHITE);
/*  66 */     StdDraw.filledRectangle(0.0D, -1.25D, 1.5D, 0.25D);
/*  67 */     StdDraw.filledRectangle(0.0D, 1.25D, 1.5D, 0.25D);
/*  68 */     StdDraw.setFont();
/*  69 */     StdDraw.setPenColor(StdDraw.MAGENTA);
/*  70 */     if (display1v1message) StdDraw.text(0.0D, 1.05D, "HOW LONG CAN YOU LAST?"); else
/*  71 */       StdDraw.text(0.0D, 1.05D, "RED PLAYER");
/*  72 */     StdDraw.text(0.0D, -1.07D, "BLACK PLAYER");
/*  73 */     StdDraw.setPenColor(StdDraw.WHITE);
/*     */   }
/*     */   public static void displayBlackVictory(Ball b1, Ball b2) {
/*  76 */     StdDraw.setPenColor(StdDraw.WHITE);
/*  77 */     b1.draw();
/*  78 */     StdDraw.setPenColor(StdDraw.GREEN);
/*  79 */     StdDraw.filledRectangle(0.0D, -0.5D, 1.0D, 0.49D);
/*  80 */     StdDraw.setPenColor(StdDraw.BLACK);
/*  81 */     StdDraw.filledRectangle(0.0D, 0.5D, 1.0D, 0.49D);
/*  82 */     b1.vx = 0.0D;
/*  83 */     b2.vx = 0.0D;
/*  84 */     StdDraw.setPenColor(StdDraw.BLACK);
/*  85 */     StdDraw.setFont();
/*  86 */     StdDraw.text(0.0D, -0.5D, "BLACK WINS !!!");
/*  87 */     StdDraw.setPenColor(StdDraw.WHITE);
/*  88 */     StdDraw.filledRectangle(0.0D, -1.25D, 1.5D, 0.25D);
/*  89 */     StdDraw.filledRectangle(0.0D, 1.25D, 1.5D, 0.25D);
/*     */   }
/*     */   public static void displayRedVictory(Ball b1, Ball b2) {
/*  92 */     StdDraw.setPenColor(StdDraw.WHITE);
/*  93 */     b2.draw();
/*  94 */     StdDraw.setPenColor(StdDraw.GREEN);
/*  95 */     StdDraw.filledRectangle(0.0D, 0.5D, 1.0D, 0.49D);
/*  96 */     StdDraw.setPenColor(StdDraw.BLACK);
/*  97 */     StdDraw.filledRectangle(0.0D, -0.5D, 1.0D, 0.49D);
/*  98 */     b1.vx = 0.0D;
/*  99 */     b2.vx = 0.0D;
/* 100 */     StdDraw.setPenColor(StdDraw.RED);
/* 101 */     StdDraw.setFont();
/* 102 */     StdDraw.text(0.0D, 0.5D, "RED WINS !!!");
/* 103 */     StdDraw.setPenColor(StdDraw.WHITE);
/* 104 */     StdDraw.filledRectangle(0.0D, -1.25D, 1.5D, 0.25D);
/* 105 */     StdDraw.filledRectangle(0.0D, 1.25D, 1.5D, 0.25D);
/*     */   }
/*     */ }

/* Location:           /Users/timcoker/Desktop/Pong v1.0.jar
 * Qualified Name:     Pong.Screen
 * JD-Core Version:    0.6.0
 */