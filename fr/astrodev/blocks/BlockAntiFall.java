package fr.astrodev.blocks;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AABBPool;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockAntiFall
  extends Block
{
  public BlockAntiFall()
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
    par5.fallDistance = 0.0F;
  }
  
  public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_)
  {
    double var6 = p_149734_2_ + 0.001F + p_149734_5_.nextFloat() * 1.0F;
    double var8 = p_149734_3_ + 0.7F + p_149734_5_.nextFloat() * 2.0F;
    double var10 = p_149734_4_ + 0.001F + p_149734_5_.nextFloat() * 1.0F;
    p_149734_1_.spawnParticle("snowshovel", var6, var8, var10, 0.0D, 0.0D, 0.0D);
  }
}
