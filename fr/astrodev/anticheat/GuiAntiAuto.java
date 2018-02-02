

java.util.List
net.minecraft.client.Minecraft
net.minecraft.client.entity.EntityClientPlayerMP
net.minecraft.client.gui.FontRenderer
net.minecraft.client.gui.GuiButton
net.minecraft.client.gui.GuiScreen
net.minecraft.client.gui.GuiTextField
net.minecraft.util.Session
org.lwjgl.input.Keyboard
org.lwjgl.opengl.GL11

GuiAntiAuto
  

  guiTop
  guiLeft
  xSize = 176
  ySize = 166
  middleScreenX
  middleScreenY
  error = 
  timeError = 0L
  periodeError = 5000L
  textfield
  
  initGui
  
    initGui()
    
    enableRepeatEvents
    
    setReferences()
    
    textfield = new GuiTextField(this.fontRendererObj, this.guiLeft - 130, this.guiTop + 135, 100, 20);
    initTextField(this.textfield, 50, true, true, "", -256);
    this.textfield.func_146189_e(true);
    
    this.buttonList.add(new GuiButton(7, this.guiLeft + this.xSize - 170, this.guiTop + 20, 130, 20, "§aReconnection au Serveur"));
    this.buttonList.add(new GuiButton(9, this.guiLeft + this.xSize - 170, this.guiTop + 40, 130, 20, "§aMenu principal."));
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
  
  public void drawScreen(int mouseX, int mouseY, float par3)
  {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    



    this.textfield.drawTextBox();
    Session var444 = this.mc.getSession();
    drawCenteredString(this.fontRendererObj, "§4§lVeillez désactiver votre logiciel d'autoclick! §c§l" + var444.getUsername() + " §8§ldans l'interface de gestion de Faction :", this.width / 2, 30, 16777215);
    
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
