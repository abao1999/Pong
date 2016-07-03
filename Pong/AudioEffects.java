package Pong;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class AudioEffects {
	public static void createMusic(boolean soundbytes, boolean activatesound, int duration, boolean alternate)	{
		String[] args = null;
		 if (soundbytes)	{ //m
         	if (activatesound) {
                 try {
                    	AudioEffects.minor(args, duration, alternate);
                 } 
                 catch (Exception e) {
                		// TODO Auto-generated catch block
                   	e.printStackTrace();
                 }
             }
		 }
	}
	public static void minor(String[] args, int duration, boolean alternate) throws Exception {
		URL url = new URL("http://www.edu4java.com/sound/back.wav");
		if (alternate)	{
			url = new URL ("http://www.edu4java.com/sound/ball.wav");
		}
		AudioClip clip = Applet.newAudioClip(url);
		clip.play();
		Thread.sleep(0);
	    clip = Applet.newAudioClip(url);
		clip.play();
		Thread.sleep(0);
		Thread.sleep(duration);
	}
	public static void main(String[] args) throws Exception	{
		URL url = new URL("http://www.edu4java.com/sound/back.wav");
		AudioClip clip = Applet.newAudioClip(url);
		clip.play();
		Thread.sleep(100000);
	}
}
