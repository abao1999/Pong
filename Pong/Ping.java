/*    */ package Pong;
/*    */ 
/*    */ public class Ping
/*    */ {
/*    */   double rx;
/*    */   double ry;
/*    */   double vx;
/*    */   double vy;
/*    */   double radius;
/*    */ 
/*    */   public Ping(boolean playComputer)
/*    */   {
/* 12 */     this.rx = 0.0D;
/* 13 */     this.ry = 0.0D;
/* 14 */     this.vx = 0.0D;
/* 15 */     this.vy = -0.02D;
/* 16 */     this.radius = 0.05D;
/*    */   }
/*    */ 
/*    */   void bounceOffVerticalWall()
/*    */   {
/* 21 */     this.vx = (-this.vx);
/*    */   }
/*    */ 
/*    */   void bounceOffHorizontalWall()
/*    */   {
/* 26 */     this.vy = (-this.vy);
/*    */   }
/*    */ 
/*    */   public void move(boolean playComputer)
/*    */   {
/* 31 */     if (Math.abs(this.rx + this.vx) + this.radius > 1.0D) bounceOffVerticalWall();
/* 32 */     if ((playComputer) && 
/* 33 */       (this.ry + this.vy + this.radius > 1.0D)) bounceOffHorizontalWall();
/*    */ 
/* 35 */     this.rx += this.vx;
/* 36 */     this.ry += this.vy;
/*    */   }
/*    */ 
/*    */   public void draw()
/*    */   {
/* 41 */     StdDraw.filledCircle(this.rx, this.ry, this.radius);
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 47 */     boolean playComputer = false;
/* 48 */     Ping b1 = new Ping(playComputer);
/*    */ 
/* 50 */     StdDraw.setXscale(-1.0D, 1.0D);
/* 51 */     StdDraw.setYscale(-1.0D, 1.0D);
/*    */     while (true) {
/* 53 */       StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
/* 54 */       StdDraw.filledSquare(0.0D, 0.0D, 1.0D);
/* 55 */       StdDraw.setPenColor(StdDraw.BLACK);
/* 56 */       b1.move(playComputer);
/* 57 */       b1.draw();
/* 58 */       StdDraw.show(20);
/*    */     }
/*    */   }
/*    */ }

/* Location:           /Users/timcoker/Desktop/Pong v1.0.jar
 * Qualified Name:     Pong.Ping
 * JD-Core Version:    0.6.0
 */