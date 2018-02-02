package fr.astrodev.blocks;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AABBPool;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockJump
  extends Block
{
  public BlockJump()
  {
    super(Material.grass);
    setCreativeTab(CreativeTabs.tabBlock);
  }
  
  public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1, int par2, int par3, int par4)
  {
    return AxisAlignedBB.getAABBPool().getAABB(par2, par3, par4, par2 + 1, par3 + 1 - 0.125F, par4 + 1);
  }
  
  public void onEntityCollidedWithBlock(World par1, int par2, int par3, int par4, Entity par5)
  {
    if (par5.isSneaking())
    {
      par5.motionY += 0.0D;
      par5.fallDistance = 0.0F;
    }
    else
    {
      par5.ticksExisted = 2;
      par5.motionY += 1.0D;
      par1.playSoundAtEntity(par5, "mob.slime.big", 0.5F, 1.0F);
      par5.fallDistance = 0.0F;
    }
  }
  
  public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_)
  {
    double var6 = p_149734_2_ + 0.4F + p_149734_5_.nextFloat() * 0.2F;
    double var8 = p_149734_3_ + 0.7F + p_149734_5_.nextFloat() * 0.3F;
    double var10 = p_149734_4_ + 0.4F + p_149734_5_.nextFloat() * 0.2F;
    p_149734_1_.spawnParticle("reddust", var6, var8, var10, 0.0D, 0.0D, 0.0D);
  }
}
