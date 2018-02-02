package fr.astrodev.anticheat;

import javax.swing.Timer;
import net.minecraft.client.Minecraft;

public class TaskThread
  extends Thread
{
  protected Minecraft mc;
  public static int clics = 0;
  public static int maxclicks = 85;
  
  public TaskThread(Minecraft client, String name)
  {
    new Timer(5000, new AntiAutoThread()).start();
  }
}
