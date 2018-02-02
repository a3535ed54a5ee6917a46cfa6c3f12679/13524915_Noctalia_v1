package fr.astrodev.mob.ghost;

import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderGhost
  extends RenderBiped
{
  private static final ResourceLocation skeletonTextures = new ResourceLocation("textures/entity/Ghost/Ghost.png");
  private static final ResourceLocation witherSkeletonTextures = new ResourceLocation("textures/entity/Ghost/Ghost_wither.png");
  private static final String __OBFID = "CL_00001023";
  
  public RenderGhost()
  {
    super(new ModelGhost(), 0.5F);
  }
  
  protected void preRenderCallback(EntityGhost par1EntityGhost, float par2)
  {
    if (par1EntityGhost.getSkeletonType() == 1) {
      GL11.glScalef(1.2F, 1.2F, 1.2F);
    }
  }
  
  protected void func_82422_c()
  {
    GL11.glTranslatef(0.09375F, 0.1875F, 0.0F);
  }
  
  protected ResourceLocation getEntityTexture(EntityGhost par1EntityGhost)
  {
    return par1EntityGhost.getSkeletonType() == 1 ? witherSkeletonTextures : skeletonTextures;
  }
  
  protected ResourceLocation getEntityTexture(EntityLiving par1EntityLiving)
  {
    return getEntityTexture((EntityGhost)par1EntityLiving);
  }
  
  protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
  {
    preRenderCallback((EntityGhost)par1EntityLivingBase, par2);
  }
  
  protected ResourceLocation getEntityTexture(Entity par1Entity)
  {
    return getEntityTexture((EntityGhost)par1Entity);
  }
}
