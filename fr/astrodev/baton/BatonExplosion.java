package fr.astrodev.baton;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BatonExplosion
  extends Item
{
  public BatonExplosion()
  {
    this.maxStackSize = 1;
    setMaxDamage(10);
    setCreativeTab(CreativeTabs.tabCombat);
  }
  
  public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
  {
    par1ItemStack.damageItem(1, par3EntityPlayer);
    
    par2World.newExplosion(par3EntityPlayer, par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ - 1.0D, 4.0F, true, true);
    return par1ItemStack;
  }
}
