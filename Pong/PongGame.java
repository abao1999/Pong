package Pong;

public class PongGame extends Ball
{
  public static void main(String[] args)
  {
    Music one = new Music("/Beethoven.wav", true);
    one.play(true);
    Music two = new Music("/PlantsVsZombies.wav", true);

    StdDraw.setXscale(-1.0D, 1.0D);
    StdDraw.setYscale(-1.0D, 1.0D);

    Ball b1 = new Ball();
    b1.radius = 0.1D;
    b1.rx = 0.0D;
    b1.ry = -1.0D;
    Ball b2 = new Ball();
    b2.radius = 0.1D;
    b2.rx = 0.0D;
    b2.ry = 1.0D;
    Ping b3 = new Ping(false);
    Ping pong = new Ping(true);
    while (true)
    {
      Screen.displayHomeScreen();
      boolean play = StdDraw.isKeyPressed(80);
      boolean playComputer = StdDraw.isKeyPressed(67);
      boolean soundbytes = StdDraw.hasNextKeyTyped();
      boolean activatesound = StdDraw.isKeyPressed(78);
      boolean display1v1Message = false;
      while (play) {
        one.stop();
        int T = 10;
        Screen.displayGameScreen(b1, b2, play, false);
        Animation.basicControlsandCheats(b1, b2, b3);
        Animation.moveIt(b1, b2, b3);
        T = Animation.pingCheats(b3, T);
        Animation.restartButton(b1, b2, b3);
        StdDraw.show(T);
      }
      two.play(true);
      while (playComputer) {
        one.stop();
        display1v1Message = true;
        int T = 10;
        b2.radius = 0.0D;
        Screen.displayGameScreen(b1, b2, play, display1v1Message);
        Animation.ComputerMode(b1, b2, pong);
        Animation.moveIt(b1, b2, pong);
        T = Animation.pingCheats(pong, T);
        Animation.restartButton(b1, b2, pong);
        StdDraw.show(T);
      }
      AudioEffects.createMusic(soundbytes, activatesound, 10, true);
    }
  }
}

/* Location:           /Users/timcoker/Desktop/Pong v1.0.jar
 * Qualified Name:     Pong.PongGame
 * JD-Core Version:    0.6.0
 */