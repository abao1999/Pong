package Pong;
import java.applet.Applet;
import java.applet.AudioClip;
import java.util.Scanner;

public class Music	{
	private AudioClip clip;
	public Music(String filename, boolean loop)	{
		try	{
			clip = Applet.newAudioClip(Music.class.getResource(filename));
		} catch (Exception e)	{
			e.printStackTrace();
		}
	}
	public void play(boolean loop)	{
		if (loop)	{
			try	{
				new Thread()	{
					public void run()	{
						clip.loop();
					}
				}.start();
			} catch	(Exception ex)	{
				ex.printStackTrace();
			}
		}
		else {
			try	{
				new Thread()	{
					public void run()	{
						clip.play();
					}
				}.start();
			} catch (Exception ex2)	{
				ex2.printStackTrace();
			}
		}
	}
	public void stop()	{
		clip.stop();
	}
	public static void main(String[] args)	{
		String filename = "/Beethoven.wav";
		Scanner scnr = new Scanner(System.in);
		System.out.println("Enter the choice (1 or 2) corresponding to your preference: ");
		System.out.println("1: run as loop"); //true
		System.out.println("2: run only once"); //false
		int choose = scnr.nextInt();
		int choice = Math.abs(choose-2);
		switch (choice)	{
		case 1:	{
			Music music1 = new Music(filename, true);
			music1.play(true);
			break;
		}
		case 2:	{
			Music music1 = new Music(filename, false); //constructor
			music1.play(false);
			break;
		}
		default:	{
			Music music1 = new Music(filename, false); //constructor
			music1.play(false);
			break;
		}
		}
	}
}
