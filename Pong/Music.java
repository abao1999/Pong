/*    */ package Pong;
/*    */ 
/*    */ import java.applet.Applet;
/*    */ import java.applet.AudioClip;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Scanner;
/*    */ 
/*    */ public class Music
/*    */ {
/*    */   private AudioClip clip;
/*    */ 
/*    */   public Music(String filename, boolean loop)
/*    */   {
/*    */     try
/*    */     {
/* 10 */       this.clip = Applet.newAudioClip(Music.class.getResource(filename));
/*    */     } catch (Exception e) {
/* 12 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public void play(boolean loop) {
/* 16 */     if (loop)
/*    */       try {
/* 18 */         new Thread() {
/*    */           public void run() {
/* 20 */             Music.this.clip.loop();
/*    */           }
/*    */         }
/* 22 */         .start();
/*    */       } catch (Exception ex) {
/* 24 */         ex.printStackTrace();
/*    */       }
/*    */     else
/*    */       try
/*    */       {
/* 29 */         new Thread() {
/*    */           public void run() {
/* 31 */             Music.this.clip.play();
/*    */           }
/*    */         }
/* 33 */         .start();
/*    */       } catch (Exception ex2) {
/* 35 */         ex2.printStackTrace();
/*    */       }
/*    */   }
/*    */ 
/*    */   public void stop() {
/* 40 */     this.clip.stop();
/*    */   }
/*    */   public static void main(String[] args) {
/* 43 */     String filename = "/Beethoven.wav";
/* 44 */     Scanner scnr = new Scanner(System.in);
/* 45 */     System.out.println("Enter the choice (1 or 2) corresponding to your preference: ");
/* 46 */     System.out.println("1: run as loop");
/* 47 */     System.out.println("2: run only once");
/* 48 */     int choose = scnr.nextInt();
/* 49 */     int choice = Math.abs(choose - 2);
/* 50 */     switch (choice) {
/*    */     case 1:
/* 52 */       Music music1 = new Music(filename, true);
/* 53 */       music1.play(true);
/* 54 */       break;
/*    */     case 2:
/* 57 */       Music music1 = new Music(filename, false);
/* 58 */       music1.play(false);
/* 59 */       break;
/*    */     default:
/* 62 */       Music music1 = new Music(filename, false);
/* 63 */       music1.play(false);
/*    */     }
/*    */   }
/*    */ }

/* Location:           /Users/timcoker/Desktop/Pong v1.0.jar
 * Qualified Name:     Pong.Music
 * JD-Core Version:    0.6.0
 */