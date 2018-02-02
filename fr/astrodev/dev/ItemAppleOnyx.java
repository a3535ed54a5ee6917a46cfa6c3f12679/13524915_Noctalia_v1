package fr.astrodev.dev;

import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemAppleOnyx
  extends ItemFood
{
  private static final String __OBFID = "CL_00000037";
  
  public ItemAppleOnyx(int p_i45341_1, float p_i45341_2, boolean p_i45341_3_)
  {
    super(p_i45341_1, p_i45341_2, p_i45341_3_);
    setHasSubtypes(true);
  }
  
  public boolean hasEffect(ItemStack par1ItemStack)
  {
    return par1ItemStack.getItemDamage() > 0;
  }
  
  public EnumRarity getRarity(ItemStack par1ItemStack)
  {
    return par1ItemStack.getItemDamage() == 0 ? EnumRarity.rare : EnumRarity.epic;
  }
  
  protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
  {
    if (!par2World.isClient) {
      par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 2400, 0));
    }
    if (par1ItemStack.getItemDamage() > 0)
    {
      if (!par2World.isClient)
      {
        par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.regeneration.id, 700, 4));
        par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.resistance.id, 6000, 0));
        par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 10800, 0));
        par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 3600, 1));
        par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 7200, 1));
      }
    }
    else {
      super.onFoodEaten(par1ItemStack, par2World, par3EntityPlayer);
    }
  }
  
  public void getSubItems(Item p_150895_1, CreativeTabs p_150895_2, List p_150895_3_)
  {
    p_150895_3_.add(new ItemStack(p_150895_1, 1, 1));
  }
}
