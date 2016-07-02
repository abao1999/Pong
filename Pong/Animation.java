/*     */ package Pong;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class Animation
/*     */ {
/*     */   public static void moveIt(Ball b1, Ball b2, Ping b3)
/*     */   {
/*   8 */     Random random = new Random();
/*   9 */     if ((b3.rx - b3.radius <= -0.97D) && (b3.ry > -0.1D) && (b3.ry < 0.1D)) {
/*  10 */       int speed = random.nextInt(2);
/*  11 */       switch (speed) {
/*     */       case 1:
/*  13 */         b3.vx *= 1.0D;
/*  14 */         b3.vy *= 1.0D;
/*     */       case 2:
/*  17 */         b3.vx *= 2.0D;
/*  18 */         b3.vy *= 2.0D;
/*     */       }
/*     */     }
/*  20 */     b1.rx = 
/*  22 */       (b1.rx + 
/*  22 */       b1.vx);
/*  23 */     b2.rx += b2.vx;
/*     */ 
/*  25 */     if (Math.abs(b1.rx + b1.vx) + b1.radius > 1.0D) b1.move();
/*     */ 
/*  27 */     if (Math.abs(b2.rx + b2.vx) + b2.radius > 1.0D) b2.move();
/*     */ 
/*  29 */     double distance1 = Math.sqrt(Math.abs((b3.rx - b1.rx) * (b3.rx - b1.rx) + (b3.ry - b1.ry) * (b3.ry - b1.ry)));
/*  30 */     double distance2 = Math.sqrt(Math.abs((b3.rx - b2.rx) * (b3.rx - b2.rx) + (b3.ry - b2.ry) * (b3.ry - b2.ry)));
/*  31 */     boolean makeNoise = false;
/*  32 */     Random r1 = new Random();
/*  33 */     Random r2 = new Random();
/*  34 */     if ((distance1 <= b1.radius + b3.radius) && (b3.ry > -0.951D)) {
/*  35 */       makeNoise = true;
/*  36 */       double o1 = r1.nextInt(190) * 7.499999999999999E-05D;
/*  37 */       double o2 = r2.nextInt(190) * 7.499999999999999E-05D;
/*  38 */       b3.vx = (o1 > o2 ? -o1 : o2);
/*  39 */       b3.vy = (0.02D - Math.abs(b3.vx));
/*     */     }
/*  41 */     if ((distance2 <= b2.radius + b3.radius) && (b3.ry < 0.951D)) {
/*  42 */       makeNoise = true;
/*  43 */       double o3 = r1.nextInt(190) * 7.499999999999999E-05D;
/*  44 */       double o4 = r2.nextInt(190) * 7.499999999999999E-05D;
/*  45 */       b3.vx = (o3 > o4 ? -o3 : o4);
/*  46 */       b3.vy = (0.02D - Math.abs(b3.vx));
/*     */     }
/*     */ 
/*  49 */     if (makeNoise) {
/*  50 */       new Thread(new Runnable() {
/*  51 */         String filename3 = "/Swish.wav";
/*  52 */         boolean music3 = false;
/*  53 */         Music two = new Music(this.filename3, this.music3);
/*     */ 
/*     */         public void run()
/*     */         {
/*  57 */           this.two.play(this.music3);
/*     */         }
/*     */       }).start();
/*     */     }
/*     */ 
/*  62 */     if (distance2 <= b2.radius + b3.radius) {
/*  63 */       b3.vx = (-b3.vx);
/*  64 */       b3.vy = (-b3.vy);
/*  65 */       StdDraw.setPenColor(StdDraw.GREEN);
/*  66 */       b3.draw();
/*     */     }
/*     */ 
/*  69 */     if (distance1 <= b1.radius + b3.radius) {
/*  70 */       StdDraw.setPenColor(StdDraw.GREEN);
/*  71 */       b3.draw();
/*     */     }
/*     */ 
/*  74 */     if (b3.rx + b3.radius >= 0.97D) {
/*  75 */       StdDraw.setPenColor(StdDraw.GREEN);
/*  76 */       b3.draw();
/*     */     }
/*     */ 
/*  79 */     if (b3.rx - b3.radius <= -0.97D) {
/*  80 */       StdDraw.setPenColor(StdDraw.GREEN);
/*  81 */       b3.draw();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int pingCheats(Ping b3, int T) {
/*  86 */     boolean answer_p = StdDraw.hasNextKeyTyped();
/*  87 */     boolean action_z = StdDraw.isKeyPressed(90);
/*  88 */     boolean action_x = StdDraw.isKeyPressed(88);
/*  89 */     if (answer_p) {
/*  90 */       if (action_z) {
/*  91 */         T = 0;
/*  92 */         b3.vy *= 1.01D;
/*  93 */         b3.vx *= 1.01D;
/*     */       }
/*  95 */       else if (action_x) {
/*  96 */         T = 100;
/*     */       }
/*     */     }
/*  99 */     return T;
/*     */   }
/*     */   public static void restartButton(Ball b1, Ball b2, Ping b3) {
/* 102 */     boolean action_space = StdDraw.isKeyPressed(32);
/* 103 */     if (action_space) {
/* 104 */       b3.ry = 0.0D; b3.rx = 0.0D;
/* 105 */       b3.vx = 0.0D;
/* 106 */       if (b3.ry + b3.vy >= 1.0D) {
/* 107 */         b3.vy = 0.05D;
/*     */       }
/* 109 */       else if (b3.ry + b3.vy <= -1.0D) {
/* 110 */         b3.vy = -0.05D;
/*     */       }
/* 112 */       b1.ry = -1.0D; b1.rx = 0.0D;
/* 113 */       b2.ry = 1.0D; b2.rx = 0.0D;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void basicControlsandCheats(Ball b1, Ball b2, Ping b3) {
/* 118 */     b1.vx = 0.0D;
/* 119 */     b2.vx = 0.0D;
/* 120 */     boolean answer1 = StdDraw.hasNextKeyTyped();
/* 121 */     boolean action1 = StdDraw.isKeyPressed(37);
/* 122 */     boolean action2 = StdDraw.isKeyPressed(39);
/* 123 */     boolean action3 = StdDraw.isKeyPressed(38);
/* 124 */     boolean action4 = StdDraw.isKeyPressed(40);
/* 125 */     boolean controlaction1 = StdDraw.isKeyPressed(49);
/* 126 */     boolean answer_a = StdDraw.hasNextKeyTyped();
/* 127 */     boolean action_a = StdDraw.isKeyPressed(65);
/* 128 */     boolean action_d = StdDraw.isKeyPressed(68);
/* 129 */     boolean action_w = StdDraw.isKeyPressed(87);
/* 130 */     boolean action_s = StdDraw.isKeyPressed(83);
/* 131 */     boolean controlaction2 = StdDraw.isKeyPressed(50);
/* 132 */     boolean answer3 = StdDraw.hasNextKeyTyped();
/* 133 */     boolean action = StdDraw.isKeyPressed(48);
/* 134 */     boolean action0 = StdDraw.isKeyPressed(57);
/*     */ 
/* 136 */     boolean soundbytes = StdDraw.hasNextKeyTyped();
/* 137 */     boolean activatesound = StdDraw.isKeyPressed(78);
/* 138 */     if (answer1) {
/* 139 */       if (action1) {
/* 140 */         b1.vx = -0.01D;
/* 141 */         b1.rx += b1.vx;
/* 142 */         if (Math.abs(b1.rx - b1.rx) > 0.0D) {
/* 143 */           b1.vx = -0.05D;
/*     */         }
/*     */       }
/* 146 */       if (action2) {
/* 147 */         b1.vx = 0.01D;
/* 148 */         b1.rx += b1.vx;
/* 149 */         if (Math.abs(b1.rx - b1.rx) > 0.0D) {
/* 150 */           b1.vx = 0.05D;
/*     */         }
/*     */       }
/*     */ 
/* 154 */       if (controlaction1) {
/* 155 */         if (action3) {
/* 156 */           b1.vy = 0.01D;
/* 157 */           b1.ry += b1.vy;
/* 158 */           if (Math.abs(b1.ry - b1.ry) > 0.0D) {
/* 159 */             b1.vy = 0.05D;
/*     */           }
/*     */         }
/* 162 */         else if (action4) {
/* 163 */           b1.vy = -0.01D;
/* 164 */           b1.ry += b1.vy;
/* 165 */           if (Math.abs(b1.ry - b1.ry) > 0.0D) {
/* 166 */             b1.vy = -0.05D;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 171 */     if (answer_a) {
/* 172 */       if (action_a) {
/* 173 */         b2.vx = -0.01D;
/* 174 */         b2.rx += b2.vx;
/* 175 */         if (Math.abs(b2.rx - b2.rx) > 0.0D) {
/* 176 */           b2.vx = -0.05D;
/*     */         }
/*     */       }
/* 179 */       if (action_d) {
/* 180 */         b2.vx = 0.01D;
/* 181 */         b2.rx += b2.vx;
/* 182 */         if (Math.abs(b2.rx - b2.rx) > 0.0D) {
/* 183 */           b2.vx = 0.05D;
/*     */         }
/*     */       }
/*     */ 
/* 187 */       if (controlaction2) {
/* 188 */         if (action_w) {
/* 189 */           b2.vy = 0.01D;
/* 190 */           b2.ry += b2.vy;
/* 191 */           if ((Math.abs(b2.ry - b2.ry) > 0.0D) && (b2.ry > 0.25D)) {
/* 192 */             b2.vy = 0.05D;
/*     */           }
/*     */         }
/* 195 */         else if (action_s) {
/* 196 */           b2.vy = -0.01D;
/* 197 */           b2.ry += b2.vy;
/* 198 */           if ((Math.abs(b2.ry - b2.ry) > 0.0D) && (b2.ry > 0.25D)) {
/* 199 */             b2.vy = -0.05D;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 204 */     AudioEffects.createMusic(soundbytes, activatesound, 100, false);
/* 205 */     if (answer3) {
/* 206 */       if (action) {
/* 207 */         b3.vx /= 2.0D;
/* 208 */         b3.vy /= 2.0D;
/*     */       }
/* 210 */       if (action0) {
/* 211 */         b3.vx *= 2.0D;
/* 212 */         b3.vy *= 2.0D;
/*     */       }
/*     */     }
/*     */ 
/* 216 */     b3.draw();
/* 217 */     b3.move(false);
/* 218 */     if (b3.ry + b3.vy >= 1.0D) Screen.displayBlackVictory(b1, b2);
/* 219 */     if (b3.ry + b3.vy <= -1.0D) Screen.displayRedVictory(b1, b2);
/*     */ 
/* 221 */     Random rand = new Random();
/* 222 */     if ((Math.abs(b3.rx + b3.radius) >= 0.97D) && (b3.ry > -0.1D) && (b3.ry < 0.1D))
/*     */     {
/* 224 */       int speed = rand.nextInt(2);
/* 225 */       switch (speed) {
/*     */       case 1:
/* 227 */         b3.vx *= 1.0D;
/* 228 */         b3.vy *= 1.0D;
/*     */       case 2:
/* 231 */         b3.vx *= 2.0D;
/* 232 */         b3.vy *= 2.0D;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void ComputerMode(Ball b1, Ball b2, Ping b3) {
/* 238 */     b1.vx = 0.0D;
/* 239 */     b2.vx = 0.0D;
/* 240 */     boolean answer1 = StdDraw.hasNextKeyTyped();
/* 241 */     boolean action1 = StdDraw.isKeyPressed(37);
/* 242 */     boolean action2 = StdDraw.isKeyPressed(39);
/* 243 */     boolean answer3 = StdDraw.hasNextKeyTyped();
/* 244 */     boolean action = StdDraw.isKeyPressed(48);
/* 245 */     boolean action0 = StdDraw.isKeyPressed(57);
/* 246 */     if (answer1) {
/* 247 */       if (action1) {
/* 248 */         b1.vx = -0.01D;
/* 249 */         b1.rx += b1.vx;
/* 250 */         if (Math.abs(b1.rx - b1.rx) > 0.0D) {
/* 251 */           b1.vx = -0.05D;
/*     */         }
/*     */       }
/* 254 */       if (action2) {
/* 255 */         b1.vx = 0.01D;
/* 256 */         b1.rx += b1.vx;
/* 257 */         if (Math.abs(b1.rx - b1.rx) > 0.0D) {
/* 258 */           b1.vx = 0.05D;
/*     */         }
/*     */       }
/*     */     }
/* 262 */     if (answer3) {
/* 263 */       if (action) {
/* 264 */         b3.vx /= 2.0D;
/* 265 */         b3.vy /= 2.0D;
/*     */       }
/* 267 */       if (action0) {
/* 268 */         b3.vx *= 2.0D;
/* 269 */         b3.vy *= 2.0D;
/*     */       }
/*     */     }
/* 272 */     b3.draw();
/* 273 */     b3.move(true);
/* 274 */     if (b3.ry + b3.vy >= 1.0D) Screen.displayBlackVictory(b1, b2);
/* 275 */     if (b3.ry + b3.vy <= -1.0D) Screen.displayRedVictory(b1, b2);
/*     */ 
/* 277 */     Random rand = new Random();
/* 278 */     if ((Math.abs(b3.rx + b3.radius) >= 0.97D) && (b3.ry > -0.1D) && (b3.ry < 0.1D))
/*     */     {
/* 280 */       int speed = rand.nextInt(2);
/* 281 */       switch (speed) {
/*     */       case 1:
/* 283 */         b3.vx *= 1.0D;
/* 284 */         b3.vy *= 1.0D;
/*     */       case 2:
/* 287 */         b3.vx *= 2.0D;
/* 288 */         b3.vy *= 2.0D;
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           /Users/timcoker/Desktop/Pong v1.0.jar
 * Qualified Name:     Pong.Animation
 * JD-Core Version:    0.6.0
 */