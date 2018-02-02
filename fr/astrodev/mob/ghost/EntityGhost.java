package fr.astrodev.mob.ghost;

import java.util.Calendar;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;

public class EntityGhost
  extends EntityMob
  implements IRangedAttackMob
{
  private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
  private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.2D, false);
  private static final String __OBFID = "CL_00001697";
  
  public EntityGhost(World par1World)
  {
    super(par1World);
    this.tasks.addTask(1, new EntityAISwimming(this));
    this.tasks.addTask(2, new EntityAIRestrictSun(this));
    this.tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
    this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
    this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    this.tasks.addTask(6, new EntityAILookIdle(this));
    this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
    this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    if ((par1World != null) && (!par1World.isClient)) {
      setCombatTask();
    }
  }
  
  protected void applyEntityAttributes()
  {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
  }
  
  protected void entityInit()
  {
    super.entityInit();
    this.dataWatcher.addObject(13, new Byte((byte)0));
  }
  
  public boolean isAIEnabled()
  {
    return true;
  }
  
  protected String getLivingSound()
  {
    return "mob.skeleton.say";
  }
  
  protected String getHurtSound()
  {
    return "mob.skeleton.hurt";
  }
  
  protected String getDeathSound()
  {
    return "mob.skeleton.death";
  }
  
  protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
  {
    playSound("mob.skeleton.step", 0.15F, 1.0F);
  }
  
  public boolean attackEntityAsMob(Entity par1Entity)
  {
    if (super.attackEntityAsMob(par1Entity))
    {
      if ((getSkeletonType() == 1) && ((par1Entity instanceof EntityLivingBase))) {
        ((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(Potion.wither.id, 200));
      }
      return true;
    }
    return false;
  }
  
  public EnumCreatureAttribute getCreatureAttribute()
  {
    return EnumCreatureAttribute.UNDEAD;
  }
  
  public void onLivingUpdate()
  {
    if ((this.worldObj.isDaytime()) && (!this.worldObj.isClient))
    {
      float var1 = getBrightness(1.0F);
      if ((var1 > 0.5F) && (this.rand.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F) && (this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ))))
      {
        boolean var2 = true;
        ItemStack var3 = getEquipmentInSlot(4);
        if (var3 != null)
        {
          if (var3.isItemStackDamageable())
          {
            var3.setItemDamage(var3.getItemDamageForDisplay() + this.rand.nextInt(2));
            if (var3.getItemDamageForDisplay() >= var3.getMaxDamage())
            {
              renderBrokenItemStack(var3);
              setCurrentItemOrArmor(4, null);
            }
          }
          var2 = false;
        }
        if (var2) {
          setFire(8);
        }
      }
    }
    if ((this.worldObj.isClient) && (getSkeletonType() == 1)) {
      setSize(0.72F, 2.34F);
    }
    super.onLivingUpdate();
  }
  
  public void updateRidden()
  {
    super.updateRidden();
    if ((this.ridingEntity instanceof EntityCreature))
    {
      EntityCreature var1 = (EntityCreature)this.ridingEntity;
      this.renderYawOffset = var1.renderYawOffset;
    }
  }
  
  public void onDeath(DamageSource par1DamageSource)
  {
    super.onDeath(par1DamageSource);
    if (((par1DamageSource.getSourceOfDamage() instanceof EntityArrow)) && ((par1DamageSource.getEntity() instanceof EntityPlayer)))
    {
      EntityPlayer var2 = (EntityPlayer)par1DamageSource.getEntity();
      double var3 = var2.posX - this.posX;
      double var5 = var2.posZ - this.posZ;
      if (var3 * var3 + var5 * var5 >= 2500.0D) {
        var2.triggerAchievement(AchievementList.snipeSkeleton);
      }
    }
  }
  
  protected Item func_146068_u()
  {
    return Items.arrow;
  }
  
  protected void dropFewItems(boolean par1, int par2)
  {
    if (getSkeletonType() == 1)
    {
      int var3 = this.rand.nextInt(3 + par2) - 1;
      for (int var4 = 0; var4 < var3; var4++) {
        func_145779_a(Items.coal, 1);
      }
    }
    else
    {
      var3 = this.rand.nextInt(3 + par2);
      for (var4 = 0; var4 < var3; var4++) {
        func_145779_a(Items.arrow, 1);
      }
    }
    int var3 = this.rand.nextInt(3 + par2);
    for (int var4 = 0; var4 < var3; var4++) {
      func_145779_a(Items.bone, 1);
    }
  }
  
  protected void dropRareDrop(int par1)
  {
    if (getSkeletonType() == 1) {
      entityDropItem(new ItemStack(Items.skull, 1, 1), 0.0F);
    }
  }
  
  protected void addRandomArmor()
  {
    super.addRandomArmor();
    setCurrentItemOrArmor(0, new ItemStack(Items.bow));
  }
  
  public IEntityLivingData onSpawnWithEgg(IEntityLivingData par1EntityLivingData)
  {
    par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
    if (((this.worldObj.provider instanceof WorldProviderHell)) && (getRNG().nextInt(5) > 0))
    {
      this.tasks.addTask(4, this.aiAttackOnCollide);
      setSkeletonType(1);
      setCurrentItemOrArmor(0, new ItemStack(Items.stone_sword));
      getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
    }
    else
    {
      this.tasks.addTask(4, this.aiArrowAttack);
      addRandomArmor();
      enchantEquipment();
    }
    setCanPickUpLoot(this.rand.nextFloat() < 0.55F * this.worldObj.func_147462_b(this.posX, this.posY, this.posZ));
    if (getEquipmentInSlot(4) == null)
    {
      Calendar var2 = this.worldObj.getCurrentDate();
      if ((var2.get(2) + 1 == 10) && (var2.get(5) == 31) && (this.rand.nextFloat() < 0.25F))
      {
        setCurrentItemOrArmor(4, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.lit_pumpkin : Blocks.pumpkin));
        this.equipmentDropChances[4] = 0.0F;
      }
    }
    return par1EntityLivingData;
  }
  
  public void setCombatTask()
  {
    this.tasks.removeTask(this.aiAttackOnCollide);
    this.tasks.removeTask(this.aiArrowAttack);
    ItemStack var1 = getHeldItem();
    if ((var1 != null) && (var1.getItem() == Items.bow)) {
      this.tasks.addTask(4, this.aiArrowAttack);
    } else {
      this.tasks.addTask(4, this.aiAttackOnCollide);
    }
  }
  
  public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2)
  {
    EntityArrow var3 = new EntityArrow(this.worldObj, this, par1EntityLivingBase, 1.6F, 14 - this.worldObj.difficultySetting.getDifficultyId() * 4);
    int var4 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, getHeldItem());
    int var5 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, getHeldItem());
    var3.setDamage(par2 * 2.0F + this.rand.nextGaussian() * 0.25D + this.worldObj.difficultySetting.getDifficultyId() * 0.11F);
    if (var4 > 0) {
      var3.setDamage(var3.getDamage() + var4 * 0.5D + 0.5D);
    }
    if (var5 > 0) {
      var3.setKnockbackStrength(var5);
    }
    if ((EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, getHeldItem()) > 0) || (getSkeletonType() == 1)) {
      var3.setFire(100);
    }
    playSound("random.bow", 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
    this.worldObj.spawnEntityInWorld(var3);
  }
  
  public int getSkeletonType()
  {
    return this.dataWatcher.getWatchableObjectByte(13);
  }
  
  public void setSkeletonType(int par1)
  {
    this.dataWatcher.updateObject(13, Byte.valueOf((byte)par1));
    this.isImmuneToFire = (par1 == 1);
    if (par1 == 1) {
      setSize(0.72F, 2.34F);
    } else {
      setSize(0.6F, 1.8F);
    }
  }
  
  public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
  {
    super.readEntityFromNBT(par1NBTTagCompound);
    if (par1NBTTagCompound.func_150297_b("SkeletonType", 99))
    {
      byte var2 = par1NBTTagCompound.getByte("SkeletonType");
      setSkeletonType(var2);
    }
    setCombatTask();
  }
  
  public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
  {
    super.writeEntityToNBT(par1NBTTagCompound);
    par1NBTTagCompound.setByte("SkeletonType", (byte)getSkeletonType());
  }
  
  public void setCurrentItemOrArmor(int par1, ItemStack par2ItemStack)
  {
    super.setCurrentItemOrArmor(par1, par2ItemStack);
    if ((!this.worldObj.isClient) && (par1 == 0)) {
      setCombatTask();
    }
  }
  
  public double getYOffset()
  {
    return super.getYOffset() - 0.5D;
  }
}
