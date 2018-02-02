package fr.astrodev.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemVodka
  extends ItemFood
{
  public ItemVodka()
  {
    super(1, false);
    setAlwaysEdible();
    setMaxStackSize(1);
  }
  
  protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
  {
    if (!par2World.isClient)
    {
      par3EntityPlayer.addPotionEffect(new PotionEffect(
        Potion.digSpeed.id, 3600, 1));
      par3EntityPlayer.addPotionEffect(new PotionEffect(
        Potion.confusion.id, 500, 1));
    }
    else
    {
      super.onFoodEaten(par1ItemStack, par2World, par3EntityPlayer);
    }
  }
  
  public EnumAction getItemUseAction(ItemStack par1ItemStack)
  {
    return EnumAction.drink;
  }
}
