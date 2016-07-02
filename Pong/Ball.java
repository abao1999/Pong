/*    */ package Pong;
/*    */ 
/*    */ public class Ball
/*    */ {
/*    */   double rx;
/*    */   double ry;
/*    */   double vx;
/*    */   double vy;
/*    */   double radius;
/*    */ 
/*    */   public Ball()
/*    */   {
/* 14 */     this.rx = (Math.random() - 0.1D);
/* 15 */     this.ry = (Math.random() - 0.1D);
/* 16 */     this.vx = (0.015D - Math.random() * 0.05D);
/* 17 */     this.vy = (0.015D - Math.random() * 0.05D);
/* 18 */     this.radius = (0.025D + Math.random() * 0.05D);
/*    */   }
/*    */ 
/*    */   private void bounceOffVerticalWall() {
/* 22 */     this.vx = (-this.vx);
/*    */   }
/*    */ 
/*    */   private void bounceOffHorizontalWall()
/*    */   {
/* 27 */     this.vy = (-this.vy);
/*    */   }
/*    */ 
/*    */   public void move()
/*    */   {
/* 32 */     if (Math.abs(this.rx + this.vx) + this.radius >= 1.0D) bounceOffVerticalWall();
/* 33 */     if (Math.abs(this.ry + this.vy) + this.radius >= 1.0D) bounceOffHorizontalWall();
/* 34 */     this.rx += this.vx;
/* 35 */     this.ry += this.vy;
/*    */   }
/*    */ 
/*    */   public void draw()
/*    */   {
/* 40 */     StdDraw.filledCircle(this.rx, this.ry, this.radius);
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 49 */     Ball b1 = new Ball();
/* 50 */     b1.rx = 0.2D;
/* 51 */     Ball b2 = new Ball();
/*    */ 
/* 54 */     StdDraw.setXscale(-1.0D, 1.0D);
/* 55 */     StdDraw.setYscale(-1.0D, 1.0D);
/* 56 */     double mass1 = (b1.radius - b2.radius) / (b1.radius + b2.radius);
/* 57 */     double mass2 = 2.0D * b2.radius / (b1.radius + b2.radius);
/* 58 */     double mass3 = 2.0D * b1.radius / (b1.radius + b2.radius);
/* 59 */     double mass4 = (b2.radius - b1.radius) / (b1.radius + b2.radius);
/* 60 */     double temp1 = 0.0D;
/* 61 */     double temp2 = 0.0D;
/*    */     while (true) {
/* 63 */       StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
/* 64 */       StdDraw.filledSquare(0.0D, 0.0D, 1.0D);
/* 65 */       StdDraw.setPenColor(StdDraw.RED);
/* 66 */       b1.move();
/* 67 */       b2.move();
/* 68 */       b1.draw();
/* 69 */       b2.draw();
/* 70 */       double distance = Math.sqrt((b2.rx - b1.rx) * (b2.rx - b1.rx) + (b2.ry - b1.ry) * (b2.ry - b1.ry));
/* 71 */       if (distance <= b1.radius + b2.radius) {
/* 72 */         temp1 = b1.vx;
/* 73 */         temp2 = b1.vy;
/* 74 */         b1.vx = (mass1 * b1.vx + mass2 * b2.vx);
/* 75 */         b1.vy = (mass1 * b1.vy + mass2 * b2.vy);
/* 76 */         b2.vx = (mass3 * temp1 + mass4 * b2.vx);
/* 77 */         b2.vy = (mass3 * temp2 + mass4 * b2.vy);
/*    */       }
/* 79 */       StdDraw.show(10);
/*    */     }
/*    */   }
/*    */ }

/* Location:           /Users/timcoker/Desktop/Pong v1.0.jar
 * Qualified Name:     Pong.Ball
 * JD-Core Version:    0.6.0
 */