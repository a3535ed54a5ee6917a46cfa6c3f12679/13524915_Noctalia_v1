package fr.astrodev.antispeedhack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;

public class AntiSpeedHack
  extends Thread
{
  public Minecraft applet;
  public HashSet name = new HashSet(32);
  private static final Pattern COMPILEouPAS = Pattern.compile(",");
  
  public AntiSpeedHack(Minecraft client, String name)
  {
    super(name);
    
    this.applet = client;
    this.name.add("\"cheatengine-i386.exe\"");
    this.name.add("\"cheatengine-x86_64.exe\"");
    this.name.add("\"Cheat Engine.exe\"");
    this.name.add("\"AutoClick.exe\"");
    this.name.add("\"SuperRapidFire.exe\"");
    this.name.add("\"SuperRapidFire.exe\"");
    this.name.add("\"RAMCheat.exe\"");
    this.name.add("\"HunterFight.exe\"");
    this.name.add("\"hunterfight.exe\"");
    this.name.add("\"kfaction.exe\"");
    this.name.add("\"fight.exe\"");
    this.name.add("\"cheat.exe\"");
    this.name.add("\"Cheat.exe\"");
    this.name.add("\"cheat engine.exe\"");
    this.name.add("\"speed.exe\"");
    this.name.add("\"a.exe\"");
    this.name.add("\"azerty.exe\"");
    this.name.add("\"z.exe\"");
  }
  
  public void run()
  {
    if (checkProcess())
    {
      this.applet.shutdown();
      System.exit(0);
    }
    try
    {
      Thread.sleep(10000L);
    }
    catch (InterruptedException localInterruptedException) {}
  }
  
  private boolean checkProcess()
  {
    InputStreamReader reader = null;
    BufferedReader buffer = null;
    try
    {
      Process process = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe /fo csv /nh");
      reader = new InputStreamReader(process.getInputStream());
      buffer = new BufferedReader(reader);
      String current;
      while ((current = buffer.readLine()) != null)
      {
        String current;
        if (this.name.contains(COMPILEouPAS.split(current)[0]))
        {
          buffer.close();
          reader.close();
          int i = 1;
          return true;
        }
      }
      buffer.close();
      reader.close();
    }
    catch (IOException localIOException1)
    {
      try
      {
        if (buffer != null) {
          buffer.close();
        }
        if (reader != null) {
          reader.close();
        }
      }
      catch (IOException localIOException2) {}
    }
    finally
    {
      try
      {
        if (buffer != null) {
          buffer.close();
        }
        if (reader != null) {
          reader.close();
        }
      }
      catch (IOException localIOException3) {}
    }
    return false;
  }
}
