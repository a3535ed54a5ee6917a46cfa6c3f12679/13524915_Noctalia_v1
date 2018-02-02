package fr.astrodev.GuiFaction;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Session;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiFaction
  extends GuiScreen
{
  private int guiTop;
  private int guiLeft;
  private int xSize = 176;
  private int ySize = 166;
  private int middleScreenX;
  private int middleScreenY;
  private String error = null;
  private long timeError = 0L;
  private long periodeError = 5000L;
  private GuiTextField textfield;
  private float field_147048_u;
  private float field_147047_v;
  private int field_147003_i;
  private int field_147009_r;
  int var100 = this.field_147003_i;
  int var200 = this.field_147009_r;
  
  public void initGui()
  {
    super.initGui();
    
    Keyboard.enableRepeatEvents(true);
    
    setReferences();
    
    this.textfield = new GuiTextField(this.fontRendererObj, this.guiLeft - 130, this.guiTop + 135, 100, 20);
    initTextField(this.textfield, 50, true, true, "", -256);
    this.textfield.func_146189_e(true);
    
    this.buttonList.add(new GuiButton(7, this.guiLeft + this.xSize - 170, this.guiTop + 20, 130, 20, "§aChanger de nom."));
    this.buttonList.add(new GuiButton(9, this.guiLeft + this.xSize - 170, this.guiTop + 40, 130, 20, "§aCreer votre Faction."));
    this.buttonList.add(new GuiButton(1, this.guiLeft + this.xSize - 170, this.guiTop + 60, 130, 20, "§aVoir les stats"));
    this.buttonList.add(new GuiButton(8, this.guiLeft + this.xSize - 170, this.guiTop + 80, 130, 20, "§aChanger la description."));
    this.buttonList.add(new GuiButton(2, this.guiLeft + this.xSize - 170, this.guiTop + 100, 130, 20, "§aInviter un Joueur."));
    this.buttonList.add(new GuiButton(4, this.guiLeft + this.xSize - 170, this.guiTop + 120, 130, 20, "§aPromouvoir Admin."));
    this.buttonList.add(new GuiButton(3, this.guiLeft + this.xSize - 170, this.guiTop + 140, 130, 20, "§aPromouvoir Modérateur."));
    this.buttonList.add(new GuiButton(13, this.guiLeft + this.xSize - 170, this.guiTop + 160, 130, 20, "§aChanger de chat."));
    
    this.buttonList.add(new GuiButton(0, this.guiLeft + this.xSize - 6, this.guiTop + 20, 130, 20, "§aAller au 'F home'."));
    this.buttonList.add(new GuiButton(12, this.guiLeft + this.xSize - 6, this.guiTop + 40, 130, 20, "§aPoser le 'F Sethome'."));
    this.buttonList.add(new GuiButton(10, this.guiLeft + this.xSize - 6, this.guiTop + 60, 130, 20, "§aClaim une zone."));
    this.buttonList.add(new GuiButton(11, this.guiLeft + this.xSize - 6, this.guiTop + 80, 130, 20, "§aUnclaim une zone."));
    this.buttonList.add(new GuiButton(6, this.guiLeft + this.xSize - 6, this.guiTop + 100, 130, 20, "§aRejoindre une Faction."));
    this.buttonList.add(new GuiButton(5, this.guiLeft + this.xSize - 6, this.guiTop + 120, 130, 20, "§aQuitter une Faction."));
    this.buttonList.add(new GuiButton(123456, this.guiLeft + this.xSize - 6, this.guiTop + 140, 130, 20, ""));
    this.buttonList.add(new GuiButton(654321, this.guiLeft + this.xSize - 6, this.guiTop + 160, 130, 20, ""));
    
    this.buttonList.add(new GuiButton(17, this.guiLeft + this.xSize - 306, this.guiTop + 160, 100, 20, "§6§lQuitter !"));
  }
  
  private void initTextField(GuiTextField obj, int size, boolean bg, boolean focus, String text, int color)
  {
    obj.func_146203_f(size);
    
    obj.func_146185_a(bg);
    
    obj.func_146205_d(true);
    
    obj.func_146204_h(color);
    
    obj.setText(text);
  }
  
  public void onGuiClosed()
  {
    super.onGuiClosed();
    
    Keyboard.enableRepeatEvents(false);
  }
  
  public void updateScreen()
  {
    super.updateScreen();
    
    setReferences();
    if (this.error != null) {
      if (System.currentTimeMillis() - this.timeError > this.periodeError) {
        this.error = null;
      }
    }
  }
  
  public static void func_147046_a(int p_147046_0_, int p_147046_1_, int p_147046_2_, float p_147046_3_, float p_147046_4_, EntityLivingBase p_147046_5_)
  {
    GL11.glEnable(2903);
    GL11.glPushMatrix();
    GL11.glTranslatef(p_147046_0_, p_147046_1_, 50.0F);
    GL11.glScalef(-p_147046_2_, p_147046_2_, p_147046_2_);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    float var6 = p_147046_5_.renderYawOffset;
    float var7 = p_147046_5_.rotationYaw;
    float var8 = p_147046_5_.rotationPitch;
    float var9 = p_147046_5_.prevRotationYawHead;
    float var10 = p_147046_5_.rotationYawHead;
    GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
    RenderHelper.enableStandardItemLighting();
    GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(-(float)Math.atan(p_147046_4_ / 40.0F) * 20.0F, 1.0F, 0.0F, 0.0F);
    p_147046_5_.renderYawOffset = ((float)Math.atan(p_147046_3_ / 40.0F) * 20.0F);
    p_147046_5_.rotationYaw = ((float)Math.atan(p_147046_3_ / 40.0F) * 40.0F);
    p_147046_5_.rotationPitch = (-(float)Math.atan(p_147046_4_ / 40.0F) * 20.0F);
    p_147046_5_.rotationYawHead = p_147046_5_.rotationYaw;
    p_147046_5_.prevRotationYawHead = p_147046_5_.rotationYaw;
    GL11.glTranslatef(0.0F, p_147046_5_.yOffset, 0.0F);
    RenderManager.instance.playerViewY = 180.0F;
    RenderManager.instance.func_147940_a(p_147046_5_, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
    p_147046_5_.renderYawOffset = var6;
    p_147046_5_.rotationYaw = var7;
    p_147046_5_.rotationPitch = var8;
    p_147046_5_.prevRotationYawHead = var9;
    p_147046_5_.rotationYawHead = var10;
    GL11.glPopMatrix();
    RenderHelper.disableStandardItemLighting();
    GL11.glDisable(32826);
    OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
    GL11.glDisable(3553);
    OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
  }
  
  public void drawScreen(int mouseX, int mouseY, float par3)
  {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    



    this.textfield.drawTextBox();
    Session var444 = this.mc.getSession();
    drawCenteredString(this.fontRendererObj, "§4§lBonjour et bienvenue, §c§l" + var444.getUsername() + " §8§ldans l'interface de gestion de Faction :", this.width / 2, 30, 16777215);
    drawCenteredString(this.fontRendererObj, "§e§lQue voulez-vous faire ?", this.width / 2, 40, 16777215);
    

    String var10 = "";
    drawString(this.fontRendererObj, var10, this.width - this.fontRendererObj.getStringWidth(var10) - 2, this.height - 10, -1);
    
    func_147046_a(this.var100 + 70, this.var200 + 160, 40, this.var100 + 10 - this.field_147048_u, this.var200 + 60 - 50 - this.field_147047_v, this.mc.thePlayer);
    super.drawScreen(mouseX, mouseY, par3);
  }
  
  protected void mouseClicked(int mouseX, int mouseY, int button)
  {
    super.mouseClicked(mouseX, mouseY, button);
    this.textfield.mouseClicked(mouseX, mouseY, button);
  }
  
  protected void keyTyped(char c, int j)
  {
    super.keyTyped(c, j);
    this.textfield.textboxKeyTyped(c, j);
  }
  
  protected void actionPerformed(GuiButton p_146284_1_)
  {
    switch (p_146284_1_.id)
    {
    case 0: 
      this.mc.thePlayer.sendChatMessage("/f home");
      this.mc.displayGuiScreen(null);
      this.mc.setIngameFocus();
      break;
    case 1: 
      this.mc.thePlayer.sendChatMessage("/f show " + this.textfield.getText());
      this.mc.displayGuiScreen(null);
      this.mc.setIngameFocus();
      break;
    case 2: 
      this.mc.thePlayer.sendChatMessage("/f invite " + this.textfield.getText());
      this.mc.displayGuiScreen(null);
      this.mc.setIngameFocus();
      break;
    case 3: 
      this.mc.thePlayer.sendChatMessage("/f mod " + this.textfield.getText());
      this.mc.displayGuiScreen(null);
      this.mc.setIngameFocus();
      break;
    case 4: 
      this.mc.thePlayer.sendChatMessage("/f admin " + this.textfield.getText());
      this.mc.displayGuiScreen(null);
      this.mc.setIngameFocus();
      break;
    case 5: 
      this.mc.thePlayer.sendChatMessage("/f leave " + this.textfield.getText());
      this.mc.displayGuiScreen(null);
      this.mc.setIngameFocus();
      break;
    case 6: 
      this.mc.thePlayer.sendChatMessage("/f join " + this.textfield.getText());
      this.mc.displayGuiScreen(null);
      this.mc.setIngameFocus();
      break;
    case 7: 
      this.mc.thePlayer.sendChatMessage("/f tag " + this.textfield.getText());
      this.mc.displayGuiScreen(null);
      this.mc.setIngameFocus();
      break;
    case 8: 
      this.mc.thePlayer.sendChatMessage("/f desc " + this.textfield.getText());
      this.mc.displayGuiScreen(null);
      this.mc.setIngameFocus();
      break;
    case 9: 
      this.mc.thePlayer.sendChatMessage("/f create " + this.textfield.getText());
      this.mc.displayGuiScreen(null);
      this.mc.setIngameFocus();
      break;
    case 10: 
      this.mc.thePlayer.sendChatMessage("/f claim");
      this.mc.displayGuiScreen(null);
      this.mc.setIngameFocus();
      break;
    case 11: 
      this.mc.thePlayer.sendChatMessage("/f unclaim");
      this.mc.displayGuiScreen(null);
      this.mc.setIngameFocus();
      break;
    case 12: 
      this.mc.thePlayer.sendChatMessage("/f sethome");
      this.mc.displayGuiScreen(null);
      this.mc.setIngameFocus();
      break;
    case 13: 
      this.mc.thePlayer.sendChatMessage("/f c");
      this.mc.displayGuiScreen(null);
      this.mc.setIngameFocus();
      break;
    case 14: 
      this.mc.thePlayer.sendChatMessage("/f create " + this.textfield.getText());
      this.mc.displayGuiScreen(null);
      this.mc.setIngameFocus();
      break;
    case 15: 
      this.mc.thePlayer.sendChatMessage("/f desc " + this.textfield.getText());
      this.mc.displayGuiScreen(null);
      this.mc.setIngameFocus();
      break;
    case 16: 
      this.mc.displayGuiScreen(null);
      this.mc.setIngameFocus();
    }
  }
  
  private int getStringWidth(String str)
  {
    return this.fontRendererObj.getStringWidth(str);
  }
  
  private void setReferences()
  {
    this.guiTop = ((this.height - this.ySize) / 2);
    this.guiLeft = ((this.width - this.xSize) / 2);
    this.middleScreenX = (this.width / 2);
    this.middleScreenY = (this.height / 2);
  }
}
