/*    */ package Pong;
/*    */ 
/*    */ import java.applet.Applet;
/*    */ import java.applet.AudioClip;
/*    */ import java.net.URL;
/*    */ 
/*    */ public class AudioEffects
/*    */ {
/*    */   public static void createMusic(boolean soundbytes, boolean activatesound, int duration, boolean alternate)
/*    */   {
/*  9 */     String[] args = null;
/* 10 */     if ((soundbytes) && 
/* 11 */       (activatesound))
/*    */       try {
/* 13 */         minor(args, duration, alternate);
/*    */       }
/*    */       catch (Exception e)
/*    */       {
/* 17 */         e.printStackTrace();
/*    */       }
/*    */   }
/*    */ 
/*    */   public static void minor(String[] args, int duration, boolean alternate) throws Exception
/*    */   {
/* 23 */     URL url = new URL("http://www.edu4java.com/sound/back.wav");
/* 24 */     if (alternate) {
/* 25 */       url = new URL("http://www.edu4java.com/sound/ball.wav");
/*    */     }
/* 27 */     AudioClip clip = Applet.newAudioClip(url);
/* 28 */     clip.play();
/* 29 */     Thread.sleep(0L);
/* 30 */     clip = Applet.newAudioClip(url);
/* 31 */     clip.play();
/* 32 */     Thread.sleep(0L);
/* 33 */     Thread.sleep(duration);
/*    */   }
/*    */   public static void main(String[] args) throws Exception {
/* 36 */     URL url = new URL("http://www.edu4java.com/sound/back.wav");
/* 37 */     AudioClip clip = Applet.newAudioClip(url);
/* 38 */     clip.play();
/* 39 */     Thread.sleep(100000L);
/*    */   }
/*    */ }

/* Location:           /Users/timcoker/Desktop/Pong v1.0.jar
 * Qualified Name:     Pong.AudioEffects
 * JD-Core Version:    0.6.0
 */