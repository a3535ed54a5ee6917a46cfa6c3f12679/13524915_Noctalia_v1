package fr.astrodev.dev;

import java.io.PrintStream;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.multiplayer.GuiConnecting;

public class GuiTest
  extends GuiScreen
{
  private GuiScreen parentScreen;
  public static GuiTextField textPassword;
  public boolean autorisation = false;
  public static boolean buttonconnexion = false;
  public static String password;
  
  public GuiTest(GuiScreen par1GuiScreen) {}
  
  public void initGui()
  {
    this.buttonList.add(new GuiButton(30, 150, 130, 120, 20, "Connexion"));
    this.buttonList.add(new GuiButton(31, 5, 215, 130, 20, "Retour au menu principal"));
    
    textPassword = new GuiTextField(this.fontRendererObj, 145, 100, 130, 20);
    textPassword.setFocused(false);
  }
  
  protected void actionPerformed(GuiButton button)
  {
    if (button.id == 31) {
      this.mc.displayGuiScreen(this.parentScreen);
    }
    if (button.id == 30)
    {
      password = textPassword.getText();
      buttonconnexion = true;
      
      writePlayerName("", "");
      
      System.out.println("appuye sur bouton connexion");
      if (this.autorisation) {
        this.mc.displayGuiScreen(new GuiConnecting(this, this.mc, "77.111.199.125", 25565));
      }
    }
  }
  
  private void writePlayerName(String string, String string2) {}
  
  public void updateScreen() {}
  
  public void onGuiClosed() {}
  
  public void keyTyped(char c, int i)
  {
    super.keyTyped(c, i);
    textPassword.textboxKeyTyped(c, i);
  }
  
  public void mouseClicked(int i, int j, int k)
  {
    super.mouseClicked(i, j, k);
    textPassword.mouseClicked(i, j, k);
  }
  
  public void drawScreen(int par1, int par2, float par3)
  {
    drawDefaultBackground();
    
    textPassword.drawTextBox();
    super.drawScreen(par1, par2, par3);
  }
}
