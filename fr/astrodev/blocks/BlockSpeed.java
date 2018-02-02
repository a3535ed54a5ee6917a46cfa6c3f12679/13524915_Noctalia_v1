package fr.astrodev.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AABBPool;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockSpeed
  extends Block
{
  public BlockSpeed()
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
    par5.motionX *= 3.0D;
    par5.motionZ *= 3.0D;
  }
}
