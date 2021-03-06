package fr.astrodev.baton;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class BatonDeFire
  extends Item
{
  public BatonDeFire()
  {
    this.maxStackSize = 1;
    setMaxDamage(10);
    setCreativeTab(CreativeTabs.tabCombat);
  }
  
  public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
  {
    par1ItemStack.damageItem(1, par3EntityPlayer);
    par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 10000, 0));
    return par1ItemStack;
  }
}
